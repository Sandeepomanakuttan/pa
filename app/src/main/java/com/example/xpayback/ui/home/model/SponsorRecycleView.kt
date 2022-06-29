package com.example.xpayback.ui.home.model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xpayback.databinding.SponsorItemsBinding

class SponsorRecycleView : RecyclerView.Adapter<HomeRecyclerViewHolder.SponsorViewHolder>() {

    var item = listOf<HomeRecyclerViewItem>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeRecyclerViewHolder.SponsorViewHolder {

       return HomeRecyclerViewHolder.SponsorViewHolder(
            SponsorItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder.SponsorViewHolder, position: Int) {

      // holder.bind(item[position] as HomeRecyclerViewItem.Store)

    }

    override fun getItemCount() = item.size

}