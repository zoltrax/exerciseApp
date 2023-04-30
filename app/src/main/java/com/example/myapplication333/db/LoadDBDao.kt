package com.example.myapplication333.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface LoadDBDao {
    @Query("SELECT * from load_list")
    fun getAll(): List<LoadItem>
    @Query("SELECT * from load_list where itemId = :id")
    fun getById(id: Int) : LoadItem?
    @Insert
    suspend fun insert(item:LoadItem)
    @Update
    suspend fun update(item:LoadItem)
    @Delete
    suspend fun delete(item:LoadItem)
    @Query("DELETE FROM load_list")
    suspend fun deleteAllTodos()
}