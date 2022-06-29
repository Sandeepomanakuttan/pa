package com.example.xpayback.ui.home.model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.xpayback.R
import com.example.xpayback.databinding.*

class HomeRecyclerviewAdaptor : RecyclerView.Adapter<HomeRecyclerViewHolder>() {


    var itemClickListener : ((view: View , item: HomeRecyclerViewItem , position : Int) -> Unit) ? = null

    var items = listOf<HomeRecyclerViewItem>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeRecyclerViewHolder {
         return when(viewType){
             R.layout.wallet_items -> HomeRecyclerViewHolder.WalletViewHolder(WalletItemsBinding.inflate(
                 LayoutInflater.from(parent.context),
                 parent,
                 false
             ))

             R.layout.store_items ->  HomeRecyclerViewHolder.StoreViewHolder(
                 StoreItemsBinding.inflate(
                 LayoutInflater.from(parent.context),
                 parent,
                 false
             ))

             R.layout.sponsor_items-> HomeRecyclerViewHolder.SponsorViewHolder(
                 SponsorItemsBinding.inflate(LayoutInflater.from(parent.context),
                 parent,
                 false)
             )

             R.layout.ads_items -> HomeRecyclerViewHolder.AdsViewHolder(
                 AdsItemsBinding.inflate(LayoutInflater.from(parent.context),
                 parent,
                 false)
             )

             R.layout.brands_items -> HomeRecyclerViewHolder.BrandsViewHolder(
                 BrandsItemsBinding.inflate(LayoutInflater.from(parent.context),
                     parent,
                     false)
             )

             R.layout.bill_pay_items -> HomeRecyclerViewHolder.BillPayHolder(
                 BillPayItemsBinding.inflate(LayoutInflater.from(parent.context),
                 parent,
                 false)
             )

             R.layout.donate_items -> HomeRecyclerViewHolder.DonateHolder(
                 DonateItemsBinding.inflate(LayoutInflater.from(parent.context),
                     parent,
                     false)
             )

             else -> throw IllegalArgumentException("Invalid view class")
         }
    }

    override fun onBindViewHolder(holder: HomeRecyclerViewHolder, position: Int) {

        holder.itemClickListener = itemClickListener
        return when(holder){
            is HomeRecyclerViewHolder.StoreViewHolder -> holder.bind(items[position] as HomeRecyclerViewItem.Store)
            is HomeRecyclerViewHolder.WalletViewHolder -> holder.bind(items[position] as HomeRecyclerViewItem.Wallet)
            is HomeRecyclerViewHolder.SponsorViewHolder -> holder.bind(items[position] as HomeRecyclerViewItem.Sponsor)
            is HomeRecyclerViewHolder.AdsViewHolder -> holder.bind(items[position] as HomeRecyclerViewItem.Ads)
            is HomeRecyclerViewHolder.BrandsViewHolder -> holder.bind(items[position] as HomeRecyclerViewItem.Brands)
            is HomeRecyclerViewHolder.BillPayHolder -> holder.bind(items[position] as HomeRecyclerViewItem.BillPay)
            is HomeRecyclerViewHolder.DonateHolder -> holder.bind(items[position] as HomeRecyclerViewItem.Donate)
        }
    }

    override fun getItemCount()= items.size

    override fun getItemViewType(position: Int): Int {
       return when (items[position]){
           is HomeRecyclerViewItem.Wallet -> R.layout.wallet_items
           is HomeRecyclerViewItem.Store -> R.layout.store_items
           is HomeRecyclerViewItem.Sponsor -> R.layout.sponsor_items
           is HomeRecyclerViewItem.Ads -> R.layout.ads_items
           is HomeRecyclerViewItem.BillPay -> R.layout.bill_pay_items
           is HomeRecyclerViewItem.Brands -> R.layout.brands_items
           is HomeRecyclerViewItem.Donate -> R.layout.donate_items
       }
    }
}