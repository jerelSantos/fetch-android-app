package com.example.fetch_android_app.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fetch_android_app.R
import com.example.fetch_android_app.model.Item

class ItemAdapter(private val groupedItems: Map<Int, List<Item>>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    private val sortedKeys = groupedItems.keys.sorted()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listId = sortedKeys[position]
        val items = groupedItems[listId] ?: emptyList()
        holder.bind(listId, items)
    }

    override fun getItemCount(): Int {
        return groupedItems.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val listIdTextView: TextView = itemView.findViewById(R.id.listIdTextView)
        private val itemsTextView: TextView = itemView.findViewById(R.id.itemsTextView)

        fun bind(listId: Int, items: List<Item>) {
            listIdTextView.text = "List ID: $listId"
            itemsTextView.text = items.joinToString("\n") { it.name ?: "Unnamed" }
        }
    }
}