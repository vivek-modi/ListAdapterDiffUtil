package com.example.listadapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listadapter.databinding.AdapterLayoutBinding

class AdapterViewHolder(private val binding: AdapterLayoutBinding, abc: Abc) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun bindView(parent: ViewGroup, abc: Abc): AdapterViewHolder {
            return AdapterViewHolder(
                AdapterLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                abc
            )
        }
    }

    fun bindItem(item: XYZ, listener: Listener<XYZ>) {
        binding.text1.text = item.id
        binding.text2.text = item.status
        binding.text3.text = item.title

        Log.e("item ", "$item")
    }

}