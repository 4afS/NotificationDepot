package me.index197511.notificationdepot.service.repository

import android.content.Context
import android.content.Intent
import android.util.Log
import me.index197511.notificationdepot.service.NotificationObserver
import org.koin.core.KoinComponent
import org.koin.core.inject

interface INotificationObserverManager {
    fun enableObserver()
    fun disableObserver()
}

class NotificationObserverManager : INotificationObserverManager, KoinComponent {
    private val context by inject<Context>()

    override fun enableObserver() {
        context.startForegroundService(Intent(context, NotificationObserver::class.java))
    }

    override fun disableObserver() {
        Log.i("DebugPrint", " isStop ${context.stopService(Intent(context, NotificationObserver::class.java))}")
    }
}