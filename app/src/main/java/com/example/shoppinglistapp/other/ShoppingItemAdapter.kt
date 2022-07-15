package com.example.shoppinglistapp.other

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglistapp.data.db.entities.ShoppingItem
import com.example.shoppinglistapp.databinding.ShoppingItemBinding
import com.example.shoppinglistapp.ui.shoppinglist.ShoppingViewModel

class ShoppingItemAdapter(
    var items : List<ShoppingItem>,
    private val viewModel:ShoppingViewModel
) :RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>(){
    inner class ShoppingViewHolder(val binding : ShoppingItemBinding) : RecyclerView.ViewHolder (binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ShoppingItemBinding.inflate(layoutInflater,parent,false)
        return ShoppingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            tvName.text = item.name
            tvAmount.text = item.amount.toString()
            ivDelete.setOnClickListener {
                viewModel.delete(item)
            }
            ivPlus.setOnClickListener {
                item.amount++
                viewModel.upsert(item)
            }
            ivMinus.setOnClickListener {
                if (item.amount > 0) {
                    item.amount--
                    viewModel.upsert(item)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}