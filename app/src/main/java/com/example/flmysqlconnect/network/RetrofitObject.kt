package com.example.flmysqlconnect.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton для работы с сетью.
 * Потокобеозопасный.
 * */
object RetrofitObject{
    private lateinit var retrofit:Retrofit
    fun getInstance(): Retrofit{
        synchronized(RetrofitObject){
            if(!this::retrofit.isInitialized)
                retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://bknd-fl.herokuapp.com/").build()
        }
        return retrofit
    }
}