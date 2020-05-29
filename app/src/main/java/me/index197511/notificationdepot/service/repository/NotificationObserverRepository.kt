package me.index197511.notificationdepot.service.repository

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import me.index197511.notificationdepot.service.NotificationObserver
import org.koin.core.KoinComponent
import org.koin.core.inject

interface INotificationObserverRepository {
    fun enableObserver()
    fun disableObserver()
}

class NotificationObserverRepository : INotificationObserverRepository, KoinComponent {
    private val context by inject<Context>()
    private val intent = Intent(context, NotificationObserver::class.java)

    override fun enableObserver() {
        Log.i("DebugPrint", "enableObserver called.")
//        if (!NotificationManagerCompat.getEnabledListenerPackages(context)
//                .contains("me.index197511.notificationdepot")
//        )
//            navToSettingNotificationListener()
//        if (!NotificationManagerCompat.getEnabledListenerPackages(context)
//                .contains("me.index197511.notificationdepot")
//        )
            context.startForegroundService(intent)
    }

    override fun disableObserver() {
        Log.i("DebugPrint", "disableObserver called.")
//        if (NotificationManagerCompat.getEnabledListenerPackages(context)
//                .contains("me.index197511.notificationdepot")
//        ) navToSettingNotificationListener()
//        if (NotificationManagerCompat.getEnabledListenerPackages(context)
//                .contains("me.index197511.notificationdepot")
//        ) context.stopService(intent)
    }

    private fun navToSettingNotificationListener() {
        val intentToSettingNotificationListener =
            Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
        intentToSettingNotificationListener.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intentToSettingNotificationListener)
    }
}