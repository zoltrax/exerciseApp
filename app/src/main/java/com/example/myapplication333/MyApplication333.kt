package com.example.myapplication333

import android.content.Context

abstract class MyApplication333 {

    companion object {

        private lateinit var context: Context

        fun setContext(con: Context) {
            context=con
        }
        fun getContext(): Context {
            return context
        }
    }
}