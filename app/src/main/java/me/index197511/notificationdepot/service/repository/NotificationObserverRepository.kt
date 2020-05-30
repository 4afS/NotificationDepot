package me.index197511.notificationdepot.service.repository

import android.content.Context
import android.content.Intent
import me.index197511.notificationdepot.service.NotificationObserver
import org.koin.core.KoinComponent
import org.koin.core.inject

interface INotificationObserverRepository {
    fun enableObserver()
    fun disableObserver()
}

class NotificationObserverRepository : INotificationObserverRepository, KoinComponent {
    private val context by inject<Context>()

    override fun enableObserver() {
        context.startForegroundService(Intent(context, NotificationObserver::class.java))
    }

    override fun disableObserver() {
        context.stopService(Intent(context, NotificationObserver::class.java))
    }
}