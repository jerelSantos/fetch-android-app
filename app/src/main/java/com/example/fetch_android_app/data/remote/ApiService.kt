package com.example.fetch_android_app.data.remote
import retrofit2.Call
import retrofit2.http.GET
interface ApiService {
    @GET("hiring.json")
    fun getItems(): Call<List<Item>>
}