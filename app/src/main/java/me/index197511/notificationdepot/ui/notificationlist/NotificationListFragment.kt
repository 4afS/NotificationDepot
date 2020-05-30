package me.index197511.notificationdepot.ui.notificationlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.notification_list_fragment.*
import me.index197511.notificationdepot.databinding.NotificationListFragmentBinding
import me.index197511.notificationdepot.service.model.Notification
import me.index197511.notificationdepot.ui.notificationlist.notificationitem.NotificationListItem
import org.koin.android.viewmodel.ext.android.viewModel

class NotificationListFragment : Fragment() {
    private val viewModel by viewModel<NotificationListViewModel>()
    private val adapter = GroupAdapter<GroupieViewHolder>()
    private lateinit var binding: NotificationListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NotificationListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        notification_list.adapter = adapter

        viewModel.notifications.observe(viewLifecycleOwner, Observer {
            it?.let { updateNotificationList(it) }
        })

        binding.buttonNavToNotificationSettings.setOnClickListener {
            navToSettingNotificationListener()
        }
    }

    override fun onResume() {
        viewModel.updateNotificationList()
        viewModel.switchObserverEnabled()
        super.onResume()
    }

    private fun updateNotificationList(notificationList: List<Notification>) {
        adapter.update(mutableListOf<Group>().apply {
            notificationList.forEach { notification ->
                run {
                    add(NotificationListItem(notification))
                }
            }
        })
    }

    private fun navToSettingNotificationListener() {
        val intentToSettingNotificationListener =
            Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
        intentToSettingNotificationListener.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context?.startActivity(intentToSettingNotificationListener)
    }


}
