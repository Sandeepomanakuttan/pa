package com.example.xpayback.ui.home.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xpayback.databinding.StoreItemsBinding

class StoreRecycleView() : RecyclerView.Adapter<HomeRecyclerViewHolder.StoreViewHolder>() {

    var item = listOf<HomeRecyclerViewItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeRecyclerViewHolder.StoreViewHolder {

       return HomeRecyclerViewHolder.StoreViewHolder(
            StoreItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder.StoreViewHolder, position: Int) {

       holder.bind(item[position] as HomeRecyclerViewItem.Store)

    }

    override fun getItemCount() = item.size

}