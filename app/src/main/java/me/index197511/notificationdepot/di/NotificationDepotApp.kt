package me.index197511.notificationdepot.di

import android.app.Application
import androidx.room.Room
import me.index197511.notificationdepot.db.NotificationDepotDatabase
import me.index197511.notificationdepot.ext.notificationManager
import me.index197511.notificationdepot.notification.ObservingNotificationProducer
import me.index197511.notificationdepot.service.repository.NotificationObserverRepository
import me.index197511.notificationdepot.service.repository.NotificationRepository
import me.index197511.notificationdepot.ui.notificationlist.NotificationListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class NotificationDepotApp : Application() {
    private val module = module {

        // ViewModel
        viewModel { NotificationListViewModel() }

        // Repository
        single { NotificationObserverRepository() }
        single { NotificationRepository() }

        // Database
        single {
            Room.databaseBuilder(
                get(),
                NotificationDepotDatabase::class.java,
                "notification-database"
            ).build()
        }
        single { get<NotificationDepotDatabase>().notificationDao() }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(module)
        }
        notificationManager.createNotificationChannel(ObservingNotificationProducer.channel)
    }
}