package com.example.myapplication333.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "load_list")
data class LoadItem(
    @PrimaryKey(autoGenerate = true)
    var itemId: Long? = 0L,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "weight")
    var weight: Float = 0f
)