package me.index197511.notificationdepot.db.dao

import androidx.room.*
import me.index197511.notificationdepot.db.entity.NotificationEntity

@Dao
interface NotificationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNotification(notification: NotificationEntity)

    @Delete
    suspend fun deleteNotification(notification: NotificationEntity)

    @Query("SELECT * FROM notifications ORDER BY id DESC")
    suspend fun getAll(): List<NotificationEntity>
}