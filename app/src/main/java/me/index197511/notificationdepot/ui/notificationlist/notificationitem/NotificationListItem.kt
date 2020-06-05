package me.index197511.notificationdepot.ui.notificationlist.notificationitem

import androidx.recyclerview.widget.ItemTouchHelper
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.notification_list_item.*
import me.index197511.notificationdepot.R
import me.index197511.notificationdepot.service.model.Notification

class NotificationListItem(private val notification: Notification) : Item() {
    override fun getLayout(): Int = R.layout.notification_list_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.notification_title.text = notification.packageName
        viewHolder.notification_content.text = notification.content
    }

    override fun getDragDirs(): Int =
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
}