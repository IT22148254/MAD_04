package com.example.mad_lab_04
import android.app.Application
import android.util.Log
import androidx.room.Room

class MyApp : Application() {
    companion object {
        lateinit var database: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "my-database"
        ).build()
        Log.d("MyApp", "Room database initialized")
    }
}

