package com.aram.picsartexam.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aram.picsartexam.databinding.ItemLayoutBinding

class RVAdapter : RecyclerView.Adapter<RVAdapter.ItemsViewHolder>() {

    var itemsList = mutableListOf<Item>()
    set(value) {
        field.clear()
        field.addAll(value)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind(itemsList[position])
    }


    inner class ItemsViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

         fun bind(item: Item){
             binding.item = item
        }
    }
}