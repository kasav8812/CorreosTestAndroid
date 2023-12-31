package com.example.correostestandroid

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class MailApp : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}