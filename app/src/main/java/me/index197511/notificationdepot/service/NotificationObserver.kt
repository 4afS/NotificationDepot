package me.index197511.notificationdepot.service

import android.app.Service
import android.content.Intent
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.index197511.notificationdepot.ext.notificationManager
import me.index197511.notificationdepot.notification.ObservingNotificationProducer
import me.index197511.notificationdepot.service.model.Notification
import me.index197511.notificationdepot.service.repository.NotificationRepository
import org.koin.android.ext.android.inject

class NotificationObserver : NotificationListenerService() {
    private val notificationRepository by inject<NotificationRepository>()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        with(ObservingNotificationProducer) {
            startForeground(NOTIFICATION_ID, get())
        }
        return Service.START_NOT_STICKY
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        notificationLog(sbn)
        addNotification(sbn)
        super.onNotificationPosted(sbn)
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        notificationLog(sbn)
        removeNotification(sbn)
        super.onNotificationRemoved(sbn)
    }

    override fun onDestroy() {
        super.onDestroy()
        notificationManager.cancel(ObservingNotificationProducer.NOTIFICATION_ID)
    }

    private fun addNotification(sbn: StatusBarNotification?) {
        GlobalScope.launch {
            sbn?.let {
                notificationRepository.add(
                    Notification(
                        id = it.id,
                        packageName = it.packageName,
                        content = it.notification?.tickerText.toString()
                    )
                )
            }
        }
    }

    private fun removeNotification(sbn: StatusBarNotification?) {
        GlobalScope.launch {
            sbn?.let {
                notificationRepository.remove(
                    Notification(
                        id = it.id,
                        packageName = it.packageName,
                        content = it.notification?.tickerText.toString()
                    )
                )
            }
        }
    }

    // use for debug
    private fun notificationLog(sbn: StatusBarNotification?) {
        Log.i(
            "DebugPrint",
            "id: ${sbn?.id}, packageName: ${sbn?.packageName}, time: ${sbn?.postTime}, content: ${sbn?.notification?.tickerText}"
        )
    }
}