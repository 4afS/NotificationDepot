package me.index197511.notificationdepot.ui.notificationlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.notification_list_fragment.*
import me.index197511.notificationdepot.R
import me.index197511.notificationdepot.service.model.Notification
import me.index197511.notificationdepot.ui.notificationlist.notificationitem.NotificationListItem
import org.koin.android.viewmodel.ext.android.viewModel

class NotificationListFragment : Fragment() {
    private val viewModel by viewModel<NotificationListViewModel>()
    private val adapter = GroupAdapter<GroupieViewHolder>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.notification_list_fragment, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        notification_list.adapter = adapter
        viewModel.notifications.observe(viewLifecycleOwner, Observer {
            it?.let { updateNotificationList(it) }
        })

        val isEnableSwitch: CompoundButton = is_enable as CompoundButton
        isEnableSwitch.setOnCheckedChangeListener { _, isChecked ->
            Log.i("DebugPrint", "isEnableSwitch = $isChecked")
            viewModel.switchObserverEnabled(isChecked)
        }
    }

    private fun updateNotificationList(notificationList: List<Notification>) {
        adapter.update(mutableListOf<Group>().apply {
            notificationList.forEach { notification ->
                NotificationListItem(notification)
            }
        })
    }
}
