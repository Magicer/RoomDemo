package xyz.magicer.roomdemo

import android.app.Application
import com.facebook.stetho.Stetho

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        Stetho.initializeWithDefaults(this)
    }

    companion object {
        @Volatile
        private var instance: App? = null
        fun getInstance() = instance

    }
}
