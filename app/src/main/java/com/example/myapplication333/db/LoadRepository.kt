package com.example.myapplication333.db

import androidx.lifecycle.LiveData

class LoadRepository(private val todoDatabaseDao: LoadDBDao) {
    val readAllData : List<LoadItem> =  todoDatabaseDao.getAll()
    suspend fun addTodo(todoItem: LoadItem) {
        todoDatabaseDao.insert(todoItem)
    }
    suspend fun updateTodo(todoItem: LoadItem) {
        todoDatabaseDao.update(todoItem)
    }
    suspend fun deleteTodo(todoItem: LoadItem) {
        todoDatabaseDao.delete(todoItem)
    }
    suspend fun deleteAllTodos() {
        todoDatabaseDao.deleteAllTodos()
    }
}