package com.example.myapplication333.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LoadItem::class], version = 1)
abstract class LoadDB : RoomDatabase() {
    abstract fun loadDao(): LoadDBDao
    companion object {
        private var INSTANCE: LoadDB? = null
        fun getInstance(context: Context): LoadDB {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LoadDB::class.java,
                        "load_barbells_db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}