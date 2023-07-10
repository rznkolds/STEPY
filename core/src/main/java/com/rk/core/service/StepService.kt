package com.rk.core.service

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.Sensor.TYPE_STEP_COUNTER
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.hardware.SensorManager.SENSOR_DELAY_FASTEST
import android.icu.text.SimpleDateFormat
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.O
import android.os.IBinder
import android.os.PowerManager
import android.os.PowerManager.PARTIAL_WAKE_LOCK
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.rk.core.R
import com.rk.core.common.Resource
import com.rk.core.common.collect
import com.rk.core.common.convert
import com.rk.core.common.toast
import com.rk.core.data.dto.Step
import com.rk.core.domain.usecase.service.AddStateServiceUseCase
import com.rk.core.domain.usecase.steps.step.AddStepUseCase
import com.rk.core.domain.usecase.steps.step.GetStepUseCase
import com.rk.core.domain.usecase.steps.temp.AddTempDataUseCase
import com.rk.core.domain.usecase.user.body.GetBodyDataUseCase
import com.rk.core.domain.usecase.user.target.GetTargetUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Calendar.DAY_OF_MONTH
import java.util.Calendar.MONTH
import java.util.Calendar.WEEK_OF_YEAR
import java.util.Calendar.YEAR
import java.util.Locale
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlin.time.Duration.Companion.seconds

@AndroidEntryPoint
class StepService : Service(), SensorEventListener {

    @Inject lateinit var addStateServiceUseCase: AddStateServiceUseCase
    @Inject lateinit var getBodyDataUseCase: GetBodyDataUseCase
    @Inject lateinit var addTempDataUseCase: AddTempDataUseCase
    @Inject lateinit var getTargetUseCase: GetTargetUseCase
    @Inject lateinit var addStepUseCase: AddStepUseCase
    @Inject lateinit var getStepUseCase: GetStepUseCase

    private var notifications: NotificationManager? = null
    private val scope = CoroutineScope(Dispatchers.IO)
    private lateinit var lock: PowerManager.WakeLock
    private lateinit var manager: SensorManager
    private lateinit var sensor: Sensor
    private var target = 0
    private var height = 0
    private var weight = 0
    private var step = 0

    @SuppressLint(
        "InvalidWakeLockTag",
        "WakelockTimeout"
    )
    override fun onCreate() {

        notification(0, 0.0, 0.0)

        lock = (getSystemService(POWER_SERVICE) as PowerManager).run {
            newWakeLock(PARTIAL_WAKE_LOCK, "lock-label").apply { acquire() }
        }

        if (SDK_INT >= O) {
            notifications = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }

        manager = getSystemService(SENSOR_SERVICE) as SensorManager

        if (manager.getDefaultSensor(TYPE_STEP_COUNTER) != null) {

            sensor = manager.getDefaultSensor(TYPE_STEP_COUNTER)

            manager.registerListener(this, sensor, SENSOR_DELAY_FASTEST)

        } else {
            toast("Adım sensörü bulunamadı")
            stopSelf()
        }

        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        getTargetUseCase().collect(scope) { it?.let { target = it } }

        getBodyDataUseCase().collect(scope) {
            if (it.height != null && it.weight != null) {
                height = it.height
                weight = it.weight
            }
        }

        val calendar = Calendar.getInstance()

        getStepUseCase(
            calendar[DAY_OF_MONTH],
            calendar[WEEK_OF_YEAR],
            calendar[YEAR]
        ).collect(scope) {
            when (it) {

                is Resource.Success -> {
                    it.data?.let { v -> update(v.step, false) }
                }

                else -> {
                    update(check = false)
                }
            }
        }

        scope.launch(Dispatchers.IO) { addStateServiceUseCase(true) }

        scope.run(Dispatchers.IO) {
            save()
            update(check = false)
        }

        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? = null

    override fun onSensorChanged(p0: SensorEvent?) {

        if (p0?.sensor == sensor) update(check = true)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}

    override fun onDestroy() {

        scope.launch(Dispatchers.IO) {
            addStateServiceUseCase(false)
            save()
        }

        lock.release()

        super.onDestroy()
    }

    private suspend fun save() {

        val date = Calendar.getInstance()

        addStepUseCase(
            Step(
                0,
                date.name(),
                date[DAY_OF_MONTH],
                date[MONTH] + 1,
                date[YEAR],
                step,
                calories(),
                kilocalories(),
                meter(),
                kilometer(),
                target,
                step >= target
            )
        )
    }

    private fun update(number: Int = 0, check: Boolean) {

        if (!check) step = number else step++

        scope.launch { addTempDataUseCase(step, kilocalories(), kilometer()) }

        notification(step, kilocalories(), kilometer())
    }

    private fun notification(step: Int, calorie: Double, meter: Double) {

        val remote = RemoteViews(packageName, R.layout.notification).apply {
            setTextViewText(R.id.calorie, calorie.toString())
            setTextViewText(R.id.kilometer, meter.toString())
            setTextViewText(R.id.step, step.toString())
        }

        val builder = if (SDK_INT >= O) {
            NotificationCompat.Builder(this, "aeri-step")
        } else {
            NotificationCompat.Builder(this)
        }

        builder.apply {
            priority = NotificationCompat.PRIORITY_MAX
            setSmallIcon(R.drawable.shoe_icon)
            setCustomBigContentView(remote)
            setCustomContentView(remote)
            setShowWhen(false)
            setOngoing(true)
        }

        if (SDK_INT >= O) {
            startForeground(1, builder.build())
        } else {
            notifications?.notify(1, builder.build())
        }
    }

    private fun meter(): Int = ((height * 0.415) / 100 * step).toInt()

    private fun kilometer() = String.format("%.1f", (height * 0.415) / 100000 * step).replace(",",".").toDouble()

    private fun calories(): Int = (step * weight * 0.04).toInt()

    private fun kilocalories() = String.format("%.1f", step * weight * 0.04 / 1000.0).replace(",",".").toDouble()

    private fun Calendar.name(): String = SimpleDateFormat("EEE", Locale.getDefault()).format(time)

    private fun CoroutineScope.run(context: CoroutineContext, block: suspend () -> Unit) {

        launch(context) {

            var saved = false

            while (isActive) {
                val date = Calendar.getInstance()

                if (date[Calendar.HOUR_OF_DAY] == 23 && date[Calendar.MINUTE] == 59) {
                    if (!saved) {
                        saved = true
                        block()
                    }
                } else {
                    saved = false
                }

                delay(40.seconds)
            }
        }
    }
}


