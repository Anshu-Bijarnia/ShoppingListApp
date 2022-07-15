package com.example.shoppinglistapp.ui.shoppinglist

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglistapp.data.db.ShoppingDatabase
import com.example.shoppinglistapp.data.db.entities.ShoppingItem
import com.example.shoppinglistapp.data.repositories.ShoppingRepository
import com.example.shoppinglistapp.databinding.ActivityShoppingBinding
import com.example.shoppinglistapp.other.ShoppingItemAdapter

class ShoppingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingBinding
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)
        val viewModel = ViewModelProvider(this,factory).get(ShoppingViewModel::class.java)

        val adapter = ShoppingItemAdapter(listOf(), viewModel)
        binding.apply {
            rvShoppingItems.layoutManager = LinearLayoutManager(this@ShoppingActivity)
            rvShoppingItems.adapter = adapter
        }
        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })
        binding.fb.setOnClickListener {
            AddShoppingItemDialog(this,
            object : AddDialogListener{
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }
}