package me.index197511.notificationdepot.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import me.index197511.notificationdepot.service.model.Notification

@Entity(tableName = "notifications")
data class NotificationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "package_id")
    val packageId: Int,

    @ColumnInfo(name = "package_name")
    val packageName: String,

    @ColumnInfo(name = "content")
    val content: String
) {
    fun toModel(): Notification =
        Notification(id, packageId, packageName, content)
}

fun Notification.toEntity(): NotificationEntity =
    NotificationEntity(pk, packageId, packageName, content)