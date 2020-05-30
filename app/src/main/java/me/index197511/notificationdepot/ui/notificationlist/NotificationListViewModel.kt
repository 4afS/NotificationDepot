package me.index197511.notificationdepot.ui.notificationlist

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.index197511.notificationdepot.service.model.Notification
import me.index197511.notificationdepot.service.repository.NotificationObserverRepository
import me.index197511.notificationdepot.service.repository.NotificationRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class NotificationListViewModel : ViewModel(), KoinComponent {
    private val context by inject<Context>()
    private val notificationRepository by inject<NotificationRepository>()
    private val notificationObserverRepository by inject<NotificationObserverRepository>()
    private val _notifications = MutableLiveData<List<Notification>>()

    val notifications: LiveData<List<Notification>>
        get() {
            return _notifications
        }

    init {
        getAllNotification()
    }

    private fun getAllNotification() {
        viewModelScope.launch {
            _notifications.value = notificationRepository.loadAll()
        }
    }

    fun updateNotificationList() {
        getAllNotification()
    }

    fun switchObserverEnabled() {
        if (NotificationManagerCompat.getEnabledListenerPackages(context)
                .contains("me.index197511.notificationdepot")
        ) notificationObserverRepository.enableObserver()
        else notificationObserverRepository.disableObserver()
    }


}
