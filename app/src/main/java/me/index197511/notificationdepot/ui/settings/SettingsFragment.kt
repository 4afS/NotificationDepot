package me.index197511.notificationdepot.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import me.index197511.notificationdepot.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.notification_settings, rootKey)
    }
}