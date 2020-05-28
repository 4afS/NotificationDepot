package me.index197511.notificationdepot.ui.notificationlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.index197511.notificationdepot.service.model.Notification
import me.index197511.notificationdepot.service.repository.NotificationRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class NotificationListViewModel : ViewModel(), KoinComponent {
    private val notificationRepository by inject<NotificationRepository>()
    private val _notifications = MutableLiveData<List<Notification>>()
    val notifications: LiveData<List<Notification>>
        get() {
            return _notifications
        }

    init {
        getAllNotification()
    }

    fun getAllNotification() {
        viewModelScope.launch {
            _notifications.value = notificationRepository.loadAll()
        }
    }
}
