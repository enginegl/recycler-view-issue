package com.github.enginegl.recyclerviewissue

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView

internal class Adapter(
    private val itemClickListener: (item: ListItem) -> Unit,
    private val itemLongClickListener: (item: ListItem) -> Unit
) : RecyclerView.Adapter<ItemViewHolder>() {

    var isInSelectionMode: Boolean = false
        set(value) {
            if (field != value) {
                field = value
                notifyDataSetChanged()
            }
        }

    private val listDiffer: AsyncListDiffer<ListItem> = AsyncListDiffer(this, FileManagerDiffCallback())

    override fun getItemCount() = listDiffer.currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(view, itemClickListener, itemLongClickListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = listDiffer.currentList[position]
        holder.bind(item, isInSelectionMode)
    }

    fun submitList(items: List<ListItem>) {
        listDiffer.submitList(items)
    }
}