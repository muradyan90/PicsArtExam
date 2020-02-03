package com.aram.picsartexam

import android.app.Application
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ExamApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        if (INSTANCE == null) {
            INSTANCE = this
        }
        startKoin {
            androidContext(this@ExamApplication)
            androidFileProperties()
            androidLogger()
            modules(koinModule)
        }
    }

    companion object {
        private var INSTANCE: ExamApplication? = null

        //@Suppress("Deprecated")
        fun isNetworkConnected(): Boolean {
            val connMen =
                INSTANCE?.applicationContext?.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = connMen.activeNetworkInfo

            return netInfo != null && netInfo.isConnectedOrConnecting

        }
    }
}