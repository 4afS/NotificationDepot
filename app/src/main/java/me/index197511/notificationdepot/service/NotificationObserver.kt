package me.index197511.notificationdepot.service

import android.app.Service
import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import me.index197511.notificationdepot.notification.ObservingNotificationProducer

class NotificationObserver : NotificationListenerService() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i("DebugPrint", "onStartCommand called.")
        with(ObservingNotificationProducer) {
            startForeground(NOTIFICATION_ID, get())
        }
        return Service.START_NOT_STICKY
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        Log.i("DebugPrint", "onNotificationPosted called")
        notificationLog(sbn)
        super.onNotificationPosted(sbn)
    }

    override fun onDestroy() {
        Log.i("DebugPrint", "onDestroy called")
        super.onDestroy()
    }

    private fun notificationLog(sbn: StatusBarNotification?) {
        Log.i(
            "DebugPrint",
            "id: ${sbn?.id}, packageName: ${sbn?.packageName}, time: ${sbn?.postTime}"
        )
    }
}