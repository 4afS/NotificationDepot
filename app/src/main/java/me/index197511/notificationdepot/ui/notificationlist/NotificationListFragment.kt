package me.index197511.notificationdepot.ui.notificationlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.notification_list_fragment.*
import me.index197511.notificationdepot.R
import me.index197511.notificationdepot.service.model.Notification
import me.index197511.notificationdepot.ui.notificationlist.notificationitem.NotificationListItem
import org.koin.android.viewmodel.ext.android.viewModel

class NotificationListFragment : Fragment() {
    private val viewModel by viewModel<NotificationListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.notification_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val groupAdapter = GroupAdapter<GroupieViewHolder>()

        notification_list.adapter = groupAdapter
        val items =
            listOf(Notification(1, "title1", "content1"), Notification(2, "title2", "content2"))
        items.forEach { item -> groupAdapter.add(NotificationListItem(item)) }
    }
}
