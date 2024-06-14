package com.example.fetch_android_app.api
import com.example.fetch_android_app.model.Item
import retrofit2.Call
import retrofit2.http.GET

// ApiService used to define endpoints
interface ApiService {
    @GET("hiring.json")
    fun getItems(): Call<List<Item>>
}