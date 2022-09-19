package com.example.flmysqlconnect.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * API для обращения к серверу. GET-запрос для получения данных, POST - для устаонвки значения.
 * */
interface DjangoAPI {
    @GET("/")
    fun select(): Call<Table1>
    @POST("/")
    fun insert(@Body value: Table1):Call<Status>
}