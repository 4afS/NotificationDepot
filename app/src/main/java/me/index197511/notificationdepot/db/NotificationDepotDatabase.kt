package me.index197511.notificationdepot.db

import androidx.room.Database
import androidx.room.RoomDatabase
import me.index197511.notificationdepot.db.dao.NotificationDao
import me.index197511.notificationdepot.db.entity.NotificationEntity

@Database(entities = [NotificationEntity::class], version = 1, exportSchema = false)
abstract class NotificationDepotDatabase : RoomDatabase() {
    abstract fun notificationDao(): NotificationDao
}