package com.example.xpayback.ui.wallet

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xpayback.R
import kotlinx.android.synthetic.main.kyc_card.view.*

class KycRecyclerView : RecyclerView.Adapter<KycRecyclerView.ViewHolder>() {

    class ViewHolder(item:View):RecyclerView.ViewHolder(item) {
        val image: ImageView = item.img
        val texts:TextView =item.text

    }

    var item = ArrayList<Doc>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.kyc_card,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.texts.text=item[position].title
        holder.image.setBackgroundResource(item[position].img)

    }

    override fun getItemCount()= item.size

}