package com.sebastianamhj.messagingapp

import android.app.Application
import android.util.Log
import io.realm.Realm
import io.realm.log.LogLevel
import io.realm.log.RealmLog
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

lateinit var realmApp: App

// global Kotlin extension that resolves to the short version
// of the name of the current class. Used for labelling logs.
inline fun <reified T> T.TAG(): String = T::class.java.simpleName
const val PARTITION_EXTRA_KEY = "PARTITION"
const val PROJECT_NAME_EXTRA_KEY = "Messaging App"

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize the Realm SDK
        Realm.init(this)
        realmApp = App(
            AppConfiguration.Builder(BuildConfig.MONGODB_REALM_APP_ID)
                .defaultSyncErrorHandler { session, error ->
                    Log.e(TAG(), "Sync error: ${error.errorMessage}")
                }
                .build())

        // Enable more logging in debug mode
        if (BuildConfig.DEBUG) {
            RealmLog.setLevel(LogLevel.ALL)
        }

        Log.v(TAG(), "Initialized the Realm App configuration for: ${realmApp.configuration.appId}")

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
        }
    }
}