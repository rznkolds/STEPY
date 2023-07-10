package com.rk.core.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.rk.core.service.StepService

class StartServiceReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        context.startService(Intent(context, StepService::class.java))
    }
}