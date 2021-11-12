package com.example.listadapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class Adapter(
    private val abc: Abc,
    private val listener: Listener<XYZ>
) : ListAdapter<XYZ, AdapterViewHolder>(LIST_COMPARATOR) {

    companion object {
        private val LIST_COMPARATOR = object : DiffUtil.ItemCallback<XYZ>() {
            override fun areItemsTheSame(oldItem: XYZ, newItem: XYZ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: XYZ, newItem: XYZ): Boolean {
                return ((oldItem.title == newItem.title) && (oldItem.status == newItem.status)
                        && (oldItem.count == newItem.count)
                        && (oldItem.item == newItem.item))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder.bindView(parent, abc)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.bindItem(getItem(position), listener)
    }
}