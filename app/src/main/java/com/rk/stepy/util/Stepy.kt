package com.rk.stepy.util

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.O
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Stepy : Application() {

    override fun onCreate() {

        if (SDK_INT >= O) {

            val channel = NotificationChannel(
                "aeri-step",
                "Adım Bildirimi",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                setShowBadge(false)
            }

            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            manager.createNotificationChannel(channel)
        }

        super.onCreate()
    }
}