package me.index197511.notificationdepot.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import me.index197511.notificationdepot.ext.notificationManager
import me.index197511.notificationdepot.notification.ObservingNotificationProducer
import org.koin.core.KoinComponent

class NotificationObserver : Service(), KoinComponent {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        with(ObservingNotificationProducer) {
            startForeground(NOTIFICATION_ID, get())
        }
        super.onStartCommand(intent, flags, startId)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        notificationManager.cancel(ObservingNotificationProducer.NOTIFICATION_ID)
    }

    override fun onBind(intent: Intent?): IBinder? = null

}