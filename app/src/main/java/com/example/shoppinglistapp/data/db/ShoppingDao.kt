package com.example.shoppinglistapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shoppinglistapp.data.db.entities.ShoppingItem
import androidx.room.Query as Query1

@Dao
interface ShoppingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert (item: ShoppingItem)

    @Delete
    fun delete (item : ShoppingItem)

    @Query1("SELECT * FROM shopping_items")
    fun getAllShoppingItems() : LiveData<List<ShoppingItem>>
}