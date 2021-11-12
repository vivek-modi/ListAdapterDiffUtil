package com.example.listadapter

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.listadapter.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ActivityViewModel by inject()
    private val adapterListener = object : Listener<XYZ> {
        override fun selectedItem(item: XYZ) {
            TODO("Not yet implemented")
        }
    }
    private val exampleAdapter = viewModel.abc?.let { Adapter(it, adapterListener) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerview.adapter = exampleAdapter
    }

    private fun setupViewModel() {
        viewModel.liveData.observe(this, { list ->
            Log.e("list", "$list")
            exampleAdapter?.submitList(list.toMutableList())
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchData()
    }
}
