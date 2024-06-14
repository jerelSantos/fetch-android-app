package com.example.fetch_android_app

import android.os.Bundle
import android.widget.Toast
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetch_android_app.model.Item
import com.example.fetch_android_app.api.RetrofitInstance
import com.example.fetch_android_app.adapter.ItemAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        print("LOG API")

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchItems()
    }

    // call the retrofit instance's getItems() method to call fetch's api to receive items
    private fun fetchItems() {
        print("Run API")
        RetrofitInstance.api.getItems().enqueue(object : Callback<List<Item>> {
            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                if (response.isSuccessful && response.body() != null) {
                    val items = response.body()!!
                        .filter { !it.name.isNullOrBlank() }
                        .sortedWith(compareBy({ it.listId }, { it.name }))
                    itemAdapter = ItemAdapter(groupItemsByListId(items))
                    print(items)
                    recyclerView.adapter = itemAdapter
                } else {
                    Toast.makeText(this@MainActivity, "Failed to retrieve data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun groupItemsByListId(items: List<Item>): Map<Int, List<Item>> {
        return items.groupBy { it.listId }
    }
}