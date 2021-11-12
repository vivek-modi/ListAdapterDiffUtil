package com.example.listadapter

interface Listener<T> {
    fun selectedItem(item: T)
}