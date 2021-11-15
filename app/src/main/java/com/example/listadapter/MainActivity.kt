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

    private val adapter = Adapter(adapterListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerview.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel.uiState.observe(this, { state ->
            when(state) {
                UiState.Loading -> TODO()
                is UiState.ShowList -> {
                    Log.e("list", "$state.data")
                    adapter.setAbc(state.abc)
                    adapter.submitList(state.data?.toMutableList())
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchData()
    }
}
