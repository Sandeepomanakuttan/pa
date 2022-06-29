package com.example.xpayback.ui.home.model

sealed class HomeRecyclerViewItem{



    class Wallet(val img:Int,val title: String): HomeRecyclerViewItem()

    class Store(val img: Int,val title: String): HomeRecyclerViewItem()

    class Sponsor(val img: Int,val title: String): HomeRecyclerViewItem()

    class Ads(val img : Int) : HomeRecyclerViewItem()

    class Brands(val img: Int,val title: String): HomeRecyclerViewItem()

    class BillPay(val img: Int,val title: String): HomeRecyclerViewItem()

    class Donate(val img: Int,val title: String): HomeRecyclerViewItem()





}
