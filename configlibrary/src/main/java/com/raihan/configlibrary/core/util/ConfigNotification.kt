package com.raihan.configlibrary.core.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import com.raihan.configlibrary.R
import com.raihan.configlibrary.ui.screen.home.HomeActivity

class ConfigNotification {

    companion object {
        private const val chanelID = "app_config"
        private const val chanelName = "IST Application Config"
        private const val chanelDescription = "Configuration for mobile application"
        private const val title = "Application Config"
        private const val desc = "Click here for open application configuration"
        private const val PENDING_INTENT_CODE = 2024
        private const val NOTIFICATION_ID = 20

        fun init(context: Context) {
            ConfigNotification().initNotification(context)
        }
    }

    private fun initNotification(context: Context) {
        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val nc = NotificationCompat
            .Builder(context, chanelID)
            .setAutoCancel(true)
            .setStyle(NotificationCompat.BigTextStyle().bigText(desc))
            .setContentTitle(title)
            .setSmallIcon(R.drawable.img_config)
            .setContentText(desc)
            .setContentIntent(notifPendingIntent(context))
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))

        val audio = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .setUsage(AudioAttributes.USAGE_ALARM)
            .build()
        val channel = NotificationChannel(
            chanelID,
            chanelName,
            NotificationManager.IMPORTANCE_LOW,
        )
        channel.description = chanelDescription
        channel.setSound(
            RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION),
            audio
        )
        nm.createNotificationChannel(channel)
        nm.notify(NOTIFICATION_ID, nc.build())
    }

    private fun notifIntent(context: Context): Intent {
        return Intent(context, HomeActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    private fun notifPendingIntent(context: Context): PendingIntent? {
        return PendingIntent.getActivity(
            context,
            PENDING_INTENT_CODE,
            notifIntent(context).setAction(Intent.ACTION_VIEW),
            PendingIntent.FLAG_IMMUTABLE
        )
    }
}