package me.index197511.notificationdepot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import me.index197511.notificationdepot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var drawer: DrawerLayout
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        drawer = binding.drawerMenu
        navController = this.findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawer)
        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawer)
    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        Log.i("DebugPrint", "onNavigationItemSelected")
//        when (item.itemId) {
//            R.id.notification_list_fragment ->
//                NotificationListFragment()
//            R.id.settings_fragment ->
//                NotificationDepotSettingsFragment()
//            else -> null
//        }?.let {
//            val ft = supportFragmentManager.beginTransaction()
//            ft.replace(R.id.nav_host_fragment, it)
//            ft.commit()
//        }
//        drawer.closeDrawer(GravityCompat.START)
//        return true
//    }
}
