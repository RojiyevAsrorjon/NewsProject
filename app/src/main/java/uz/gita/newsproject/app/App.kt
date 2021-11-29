package uz.gita.newsproject.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import uz.gita.newsproject.BuildConfig
import uz.gita.newsproject.data.room.AppDatabase


@HiltAndroidApp
class App : Application() {
    companion object{
        lateinit var instance : App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppDatabase.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}