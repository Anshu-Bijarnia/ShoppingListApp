package com.example.shoppinglistapp.ui.shoppinglist

import androidx.lifecycle.ViewModel
import com.example.shoppinglistapp.data.db.entities.ShoppingItem
import com.example.shoppinglistapp.data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(
    private val repository: ShoppingRepository
) : ViewModel(){
    fun upsert(item: ShoppingItem) = CoroutineScope(Dispatchers.IO).launch {
        repository.upsert(item)
    }

    fun delete (item :ShoppingItem) = CoroutineScope(Dispatchers.IO).launch {
        repository.delete(item)
    }
    fun getAllShoppingItems() = repository.getAllShopppingItems()
}