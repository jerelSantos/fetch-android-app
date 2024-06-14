package com.example.fetch_android_app.model

// Item class to hold items values gathered from fetch API
data class Item (
    val id: Int,
    val listId: Int,
    val name: String?
)