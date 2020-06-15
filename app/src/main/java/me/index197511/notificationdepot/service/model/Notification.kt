package me.index197511.notificationdepot.service.model

data class Notification(
    val pk: Int,
    val packageId: Int,
    val packageName: String,
    val content: String
)