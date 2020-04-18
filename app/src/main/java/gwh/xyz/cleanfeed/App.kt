package gwh.xyz.cleanfeed

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Dependencies.inject()
    }
}