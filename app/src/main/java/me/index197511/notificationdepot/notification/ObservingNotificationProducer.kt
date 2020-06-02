package me.index197511.notificationdepot.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import org.koin.core.KoinComponent
import org.koin.core.inject

object ObservingNotificationProducer : KoinComponent {
    const val NOTIFICATION_ID = 9080
    private const val CHANNEL_ID = "NotificationObserver"
    private const val CHANNEL_NAME = "Stationed Notification"

    private val context by inject<Context>()

    val channel = NotificationChannel(
        CHANNEL_ID,
        CHANNEL_NAME,
        NotificationManager.IMPORTANCE_LOW
    ).apply {
        lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        setShowBadge(false)
    }

    fun generateNotification(): Notification {
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .apply {
                setContentText("ObservingNotification.....")
            }
            .build()
    }
}