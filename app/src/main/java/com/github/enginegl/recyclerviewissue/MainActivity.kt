package com.github.enginegl.recyclerviewissue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var listItems = (1..40).map {
        ListItem(
            name = "Item $it",
            isSelected = false
        )
    }

    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = Adapter(
            ::onItemClicked,
            ::onItemLongClicked,
        )
        findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter
        adapter.submitList(listItems)
    }

    private fun onItemClicked(item: ListItem) {
        toggleItemSelection(item)
    }

    private fun onItemLongClicked(item: ListItem) {
        adapter.isInSelectionMode = true
        toggleItemSelection(item)
    }

    private fun toggleItemSelection(item: ListItem) {
        val newItems = listItems.map {
            if (it.name == item.name) {
                it.copy(isSelected = !item.isSelected)
            } else {
                it
            }
        }
        listItems = newItems
        adapter.submitList(listItems)
    }
}