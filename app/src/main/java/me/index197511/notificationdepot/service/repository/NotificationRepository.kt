package me.index197511.notificationdepot.service.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.index197511.notificationdepot.db.dao.NotificationDao
import me.index197511.notificationdepot.db.entity.toEntity
import me.index197511.notificationdepot.service.model.Notification
import org.koin.core.KoinComponent
import org.koin.core.inject

interface INotificationRepository {
    suspend fun add(notification: Notification)
    suspend fun remove(notification: Notification)
    suspend fun removeAll()
    suspend fun loadAll(): List<Notification>
}

class NotificationRepository : INotificationRepository, KoinComponent {
    private val notificationDao by inject<NotificationDao>()

    override suspend fun add(notification: Notification) {
        withContext(Dispatchers.IO) {
            notificationDao.insertNotification(notification.toEntity())
        }
    }

    override suspend fun remove(notification: Notification) {
        withContext(Dispatchers.IO) {
            notificationDao.deleteNotification(notification.toEntity())
        }
    }

    override suspend fun removeAll() {
        withContext(Dispatchers.IO) {
            notificationDao.deleteAll()
        }
    }

    override suspend fun loadAll(): List<Notification> {
        return withContext(Dispatchers.IO) {
            notificationDao.getAll().map { e -> e.toModel() }
        }
    }

}