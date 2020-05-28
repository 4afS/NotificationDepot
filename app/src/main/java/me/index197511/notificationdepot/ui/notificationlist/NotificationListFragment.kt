package me.index197511.notificationdepot.ui.notificationlist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import me.index197511.notificationdepot.R

class NotificationListFragment : Fragment() {

    companion object {
        fun newInstance() = NotificationListFragment()
    }

    private lateinit var viewModel: NotificationListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.notification_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NotificationListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
