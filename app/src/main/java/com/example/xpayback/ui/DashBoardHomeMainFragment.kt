package com.example.xpayback.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.*
import com.example.xpayback.R
import com.example.xpayback.databinding.*
import com.example.xpayback.network.Api
import com.example.xpayback.ui.auth.AuthRepository
import com.example.xpayback.ui.auth.AuthViewModel
import com.example.xpayback.ui.base.BaseFragment
import com.example.xpayback.ui.home.model.HomeRecyclerviewAdaptor
import kotlinx.android.synthetic.main.fragment_dash_board_home_main.*
import kotlinx.android.synthetic.main.kyc_card.view.*


class DashBoardHomeMainFragment : BaseFragment<AuthViewModel, FragmentDashBoardHomeMainBinding,AuthRepository>() {

    var arrayListDashBoard=ArrayList<DashBoardRecyclerItem.trendingCardDataClass>()
    var arrayListDashBoards=ArrayList<DashBoardRecyclerItem.topCashbackCardDataClass>()
    var arrayListGIftCard=ArrayList<DashBoardRecyclerItem.giftCardDataClass>()
    var arrayListDonateCard=ArrayList<DashBoardRecyclerItem.DonateCardDataClass>()
    var arrayListBrands=ArrayList<DashBoardRecyclerItem.Brands>()
    private val donateRView = HomeRecyclerviewAdaptor()


    override fun getViewModel()=AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    )=FragmentDashBoardHomeMainBinding.inflate(inflater,container,false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var doubleBackToExitPressedOnce = false

        requireActivity().onBackPressedDispatcher.addCallback {

            if (doubleBackToExitPressedOnce) {
                requireActivity().finish()
            }
            doubleBackToExitPressedOnce = true
            Toast.makeText(requireContext(), "Please click BACK again to exit", Toast.LENGTH_SHORT)
                .show()

            Handler(Looper.getMainLooper()).postDelayed({
                doubleBackToExitPressedOnce = false
            }, 2000)
        }
        trendingCard()

    }
    fun trendingCard(){

        arrayListDashBoard.add(DashBoardRecyclerItem.trendingCardDataClass(R.drawable.ternding_image1,"Sony max 32 tv","sony company 50000","$3000","$1000","5",R.drawable.amazonsmall,"","85%"))
        arrayListDashBoard.add(DashBoardRecyclerItem.trendingCardDataClass(R.drawable.trendingcardimg1,"Sony max 32 tv","sony company 50000 ","$3000","$1000","5",R.drawable.amazonsmall,"","85%"))
        arrayListDashBoard.add(DashBoardRecyclerItem.trendingCardDataClass(R.drawable.ternding_image1,"Sony max 32 tv","sony company 50000","$3000","$1000","5",R.drawable.amazonsmall,"","85%"))


        trendingRecyclerViewFragmentDashHomeMain.apply {
            layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,true)

            adapter=DashBoardAdapterTrending(arrayListDashBoard)
        }

        arrayListDashBoards.add(DashBoardRecyclerItem.topCashbackCardDataClass(R.drawable.mobile_icon,"F3 oppo mobile","oppo company","$2500","$500","$2000 Cashback"))
        arrayListDashBoards.add(DashBoardRecyclerItem.topCashbackCardDataClass(R.drawable.mobile_icon,"F3 oppo mobile","oppo company","$2500","$500","$2000 Cashback"))
        arrayListDashBoards.add(DashBoardRecyclerItem.topCashbackCardDataClass(R.drawable.mobile_icon,"F3 oppo mobile","oppo company","$2500","$500","$2000 Cashback"))


        topCashbackRecycler.apply {
            layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,true)

            adapter=DashBoardAdapterTopCashback(arrayListDashBoards)
        }


        arrayListGIftCard.add(DashBoardRecyclerItem.giftCardDataClass(R.drawable.star,"gift Card 1"))
        arrayListGIftCard.add(DashBoardRecyclerItem.giftCardDataClass(R.drawable.star,"gift Card 1"))
        arrayListGIftCard.add(DashBoardRecyclerItem.giftCardDataClass(R.drawable.star,"gift Card 1"))
        arrayListGIftCard.add(DashBoardRecyclerItem.giftCardDataClass(R.drawable.star,"gift Card 1"))


        giftCardsRecyclerDashHomeMain.apply {
            layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,true)
            adapter=GiftCardRecyclerAdapter(arrayListGIftCard,requireContext())
        }

        arrayListDonateCard.add(DashBoardRecyclerItem.DonateCardDataClass(R.drawable.earth,"World day"))
        arrayListDonateCard.add(DashBoardRecyclerItem.DonateCardDataClass(R.drawable.d,"Donate meals"))
        arrayListDonateCard.add(DashBoardRecyclerItem.DonateCardDataClass(R.drawable.nah,"Nanhikali"))

        donateRecyclerView.apply {
            layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,true)

            adapter=DonateCardRecyclerAdapter(arrayListDonateCard,requireContext())
        }

        arrayListBrands.add(DashBoardRecyclerItem.Brands(R.drawable.amazon))
        arrayListBrands.add(DashBoardRecyclerItem.Brands(R.drawable.flipcart))
        arrayListBrands.add(DashBoardRecyclerItem.Brands(R.drawable.mintra))
        arrayListBrands.add(DashBoardRecyclerItem.Brands(R.drawable.ajoi))


        brandVouchersRecyclerView.apply {
            layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,true)

            adapter=BrandsRecyclerAdapter(arrayListBrands,requireContext())
        }


    }

    override fun getFragmentRepository() =
        AuthRepository(retrofitInstances.buildApi(Api::class.java))


}

sealed class DashBoardRecyclerItem{

    class trendingCardDataClass(val image:Int,val itemName:String, val description:String, val oldRate:String,val newRate:String, val rating:String,val onlineSellerImg:Int,val priceComparison:String,val offer:String)

    class topCashbackCardDataClass(val image:Int,val itemName:String, val description:String, val oldRate:String,val newRate:String, val cashback:String)

    class giftCardDataClass(val color:Int,val nameStar:String)

    class DonateCardDataClass(val images:Int,val nameStar:String)
    class Brands(val image : Int)


}

class DashBoardAdapterTrending(var listData: List<DashBoardRecyclerItem.trendingCardDataClass>):ListAdapter<DashBoardRecyclerItem.trendingCardDataClass,DashBoardAdapterTrending.MyViewHolder>(UserDiffUtil()) {
    class MyViewHolder(binding: FragmentDashBoardMainRecyclerCardTrendingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var binds = binding

        fun initialize(item: DashBoardRecyclerItem.trendingCardDataClass) {
            binds.modelNameTrendingDashMainRecyCard.text = item.itemName
            binds.descriptionDashCardRecy.text = item.description
            binds.oldPricedashMainRecyCardID.text = item.oldRate
            binds.currentPriceCardDashBoardhome.text = item.newRate
            binds.ratingTextId.text = "${item.rating}/5"
            binds.dashMainRecyCardImgId.setImageResource(item.image)
            binds.purchaseCompanyIconCard.setImageResource(item.onlineSellerImg)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var bindingD = FragmentDashBoardMainRecyclerCardTrendingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(bindingD)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.initialize(listData[position])
    }

    override fun getItemCount(): Int = listData.size



    class UserDiffUtil : DiffUtil.ItemCallback<DashBoardRecyclerItem.trendingCardDataClass>() {
        override fun areItemsTheSame(
            oldItem: DashBoardRecyclerItem.trendingCardDataClass,
            newItem: DashBoardRecyclerItem.trendingCardDataClass
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: DashBoardRecyclerItem.trendingCardDataClass,
            newItem: DashBoardRecyclerItem.trendingCardDataClass
        ): Boolean {
            return oldItem.itemName == newItem.itemName
        }

    }
}


class DashBoardAdapterTopCashback(var listData: List<DashBoardRecyclerItem.topCashbackCardDataClass>):ListAdapter<DashBoardRecyclerItem.trendingCardDataClass,DashBoardAdapterTopCashback.MyViewHolder>(UserDiffUtil()) {
    class MyViewHolder(binding: TopCashbackRecyclerCardDashboardHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var binds = binding

        @SuppressLint("SetTextI18n")
        fun initialize(item: DashBoardRecyclerItem.topCashbackCardDataClass) {

            binds.imageTopCashbackCard.setImageResource(item.image)
            binds.trendingcashbackCardtitle.text=item.itemName
            binds.topcashbackTvOldamount.text =item.oldRate
            binds.currentPriceCardDashBoardhome.text=item.newRate
            //binds.cashbackdetailsdash.text="$ ${item.cashback} Cashback"


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var bindingD = TopCashbackRecyclerCardDashboardHomeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(bindingD)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.initialize(listData[position])
    }

    override fun getItemCount(): Int = listData.size



    class UserDiffUtil : DiffUtil.ItemCallback<DashBoardRecyclerItem.trendingCardDataClass>() {
        override fun areItemsTheSame(
            oldItem: DashBoardRecyclerItem.trendingCardDataClass,
            newItem: DashBoardRecyclerItem.trendingCardDataClass
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: DashBoardRecyclerItem.trendingCardDataClass,
            newItem: DashBoardRecyclerItem.trendingCardDataClass
        ): Boolean {
            return oldItem.itemName == newItem.itemName
        }

    }
}

class GiftCardRecyclerAdapter(var listdata:List<DashBoardRecyclerItem.giftCardDataClass>,var context:Context):ListAdapter<DashBoardRecyclerItem.giftCardDataClass,GiftCardRecyclerAdapter.MyViewHolder>(UserDiffUtil()) {
    var cnx=context
    inner class MyViewHolder(binding: GiftCardDashRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var binds = binding

        fun initialize(item: DashBoardRecyclerItem.giftCardDataClass) {

            binds.img.setImageResource(item.color)
            binds.giftCardsTextID.text=item.nameStar

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var bindingD = GiftCardDashRecyclerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(bindingD)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.initialize(listdata[position])
    }

    override fun getItemCount(): Int = listdata.size



    class UserDiffUtil : DiffUtil.ItemCallback<DashBoardRecyclerItem.giftCardDataClass>() {
        override fun areItemsTheSame(
            oldItem: DashBoardRecyclerItem.giftCardDataClass,
            newItem: DashBoardRecyclerItem.giftCardDataClass
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: DashBoardRecyclerItem.giftCardDataClass,
            newItem: DashBoardRecyclerItem.giftCardDataClass
        ): Boolean {
            return oldItem.nameStar == newItem.nameStar
        }

    }
}

class DonateCardRecyclerAdapter(var listdatas:List<DashBoardRecyclerItem.DonateCardDataClass>,var context:Context):ListAdapter<DashBoardRecyclerItem.DonateCardDataClass,DonateCardRecyclerAdapter.MyViewHolder>(UserDiffUtil()) {
    var cnx=context
    inner class MyViewHolder(binding: DonateRecyclerCardDashboardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var binds = binding

        fun initialize(item: DashBoardRecyclerItem.DonateCardDataClass) {

        binds.donaterecyclercardImage.setImageResource(item.images)
        binds.donaterecyclercardTV.text=item.nameStar

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var bindingD =DonateRecyclerCardDashboardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(bindingD)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.initialize(listdatas[position])
    }

    override fun getItemCount(): Int = listdatas.size



    class UserDiffUtil : DiffUtil.ItemCallback<DashBoardRecyclerItem.DonateCardDataClass>() {
        override fun areItemsTheSame(
            oldItem: DashBoardRecyclerItem.DonateCardDataClass,
            newItem:DashBoardRecyclerItem.DonateCardDataClass
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: DashBoardRecyclerItem.DonateCardDataClass,
            newItem:DashBoardRecyclerItem.DonateCardDataClass
        ): Boolean {
            return oldItem.nameStar == newItem.nameStar
        }

    }


}

class BrandsRecyclerAdapter(var listdata:List<DashBoardRecyclerItem.Brands>,var context:Context):ListAdapter<DashBoardRecyclerItem.Brands,BrandsRecyclerAdapter.MyViewHolder>(UserDiffUtil()) {
    var cnx=context
    inner class MyViewHolder(binding: BrandVouchersRecyclerCardDashBoardMainBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var binds = binding

        fun initialize(item: DashBoardRecyclerItem.Brands) {

           binds.img.setImageResource(item.image)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var bindingD = BrandVouchersRecyclerCardDashBoardMainBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(bindingD)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.initialize(listdata[position])
    }

    override fun getItemCount(): Int = listdata.size



    class UserDiffUtil : DiffUtil.ItemCallback<DashBoardRecyclerItem.Brands>() {
        override fun areItemsTheSame(
            oldItem: DashBoardRecyclerItem.Brands,
            newItem: DashBoardRecyclerItem.Brands
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: DashBoardRecyclerItem.Brands,
            newItem: DashBoardRecyclerItem.Brands
        ): Boolean {
            return oldItem.image == newItem.image
        }

    }
}
