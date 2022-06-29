package com.example.xpayback.response

data class MerchantRegistrationResponse(
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String,
    val location: String,
    val owner_name: String,
    val phone: Int,
    val referral_code: String,
    val sales_channel: String,
    val shop_category: String,
    val shop_name: String,
    val username: String
)