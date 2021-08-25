package com.github.enginegl.recyclerviewissue

import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.checkbox.MaterialCheckBox

class ItemViewHolder(
    itemView: View,
    private val itemClickListener: (item: ListItem) -> Unit,
    private val itemLongClickListener: (item: ListItem) -> Unit,
) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: ListItem, isInSelectionMode: Boolean) {
        bindSelectableMode(item, isInSelectionMode)
        itemView.findViewById<TextView>(R.id.title).text = item.name
        itemView.setOnClickListener { itemClickListener(item) }
        itemView.setOnLongClickListener {
            itemLongClickListener(item)
            true
        }
    }

    private fun bindSelectableMode(item: ListItem, inSelectionMode: Boolean) {
        val previewContainer = itemView.findViewById<FrameLayout>(R.id.previewContainer)
        previewContainer.findViewById<View>(R.id.selectCheckBoxLayout)?.apply {
            isVisible = inSelectionMode
        }
        previewContainer.findViewById<MaterialCheckBox>(R.id.selectCheckBox).isChecked = item.isSelected
        previewContainer.findViewById<View>(R.id.checkBoxOverlay).isVisible = item.isSelected
    }
}