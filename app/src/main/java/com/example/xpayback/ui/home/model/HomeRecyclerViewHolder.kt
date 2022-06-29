package com.example.xpayback.ui.home.model

import android.util.Log
import android.view.View
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.xpayback.databinding.*

sealed class HomeRecyclerViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root){

    var itemClickListener : ((view: View, item: HomeRecyclerViewItem, position : Int) -> Unit) ? = null

    class WalletViewHolder(private val binding: WalletItemsBinding) : HomeRecyclerViewHolder(binding){

        fun bind(item : HomeRecyclerViewItem.Wallet){

              binding.img.setBackgroundResource(item.img)
              binding.title.text=item.title
            binding.img.setOnClickListener {
                itemClickListener?.invoke(it,item,adapterPosition)
            }
        }
    }

    class StoreViewHolder(private val binding: StoreItemsBinding) : HomeRecyclerViewHolder(binding){

        fun bind(item: HomeRecyclerViewItem.Store){

            binding.img.setBackgroundResource(item.img)
            binding.title.text=item.title

            binding.img.setOnClickListener {
                itemClickListener?.invoke(it,item,adapterPosition)
            }
        }
    }

    class SponsorViewHolder(private val binding: SponsorItemsBinding) : HomeRecyclerViewHolder(binding){

        fun bind(item: HomeRecyclerViewItem.Sponsor){

            binding.img.setBackgroundResource(item.img)
            binding.title.text=item.title
            binding.img.setOnClickListener {
                itemClickListener?.invoke(it,item,adapterPosition)
            }
        }
    }

    class AdsViewHolder (private val binding: AdsItemsBinding) : HomeRecyclerViewHolder(binding){

        fun bind(item: HomeRecyclerViewItem.Ads){

            binding.img.setBackgroundResource(item.img)
            binding.img.setOnClickListener {
                itemClickListener?.invoke(it,item,adapterPosition)
            }
        }
    }

    class BrandsViewHolder (private val binding: BrandsItemsBinding) : HomeRecyclerViewHolder(binding){

        fun bind(item: HomeRecyclerViewItem.Brands){

            binding.img.setBackgroundResource(item.img)
            binding.title.text=item.title
            binding.img.setOnClickListener {
                itemClickListener?.invoke(it,item,adapterPosition)
            }        }
    }

    class BillPayHolder (private val binding: BillPayItemsBinding) : HomeRecyclerViewHolder(binding){

        fun bind (item: HomeRecyclerViewItem.BillPay){
            binding.img.setBackgroundResource(item.img)
            binding.title.text=item.title
            binding.img.setOnClickListener {
                itemClickListener?.invoke(it,item,adapterPosition)
            }        }
    }

    class DonateHolder (private val binding: DonateItemsBinding) : HomeRecyclerViewHolder(binding){

        fun bind (item: HomeRecyclerViewItem.Donate){
            binding.img.setBackgroundResource(item.img)
            binding.title.text=item.title
            binding.img.setOnClickListener {
                itemClickListener?.invoke(it,item,adapterPosition)
            }        }
    }

}
