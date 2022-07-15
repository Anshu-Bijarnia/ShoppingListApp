package com.example.shoppinglistapp.ui.shoppinglist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglistapp.data.db.entities.ShoppingItem
import com.example.shoppinglistapp.databinding.DialogAddShoppingItemBinding

class AddShoppingItemDialog(context: Context,var addDialogListener: AddDialogListener) : AppCompatDialog(context) {
    lateinit var dialogBinding : DialogAddShoppingItemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogBinding = DialogAddShoppingItemBinding.inflate(layoutInflater)
        setContentView(dialogBinding.root)
        dialogBinding.apply {
            tvAdd.setOnClickListener {
                val name = etName.text.toString()
                val amount = etAmount.text.toString()
                if (name.isEmpty() || amount.isEmpty()){
                    Toast.makeText(context,"Please enter all the information",Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                val item = ShoppingItem(name,amount.toInt())
                addDialogListener.onAddButtonClicked(item)
                dismiss()
            }
            tvCancel.setOnClickListener {
                cancel()
            }
        }
    }
}