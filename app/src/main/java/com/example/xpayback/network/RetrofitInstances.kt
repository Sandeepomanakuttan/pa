package com.example.xpayback.network

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import androidx.viewbinding.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInstances {

    companion object{
        const val baseUrl = "http://xpayback.in:8000/xpayback/"
        var cokkies:HashSet<String> = HashSet()

        fun getRetrofitClient():Retrofit {

            var retrofit:Retrofit?=null

            val logging =HttpLoggingInterceptor()
            logging.level=(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
            client.addInterceptor(logging)

            if (retrofit ==null){
                retrofit = Retrofit.Builder().
                baseUrl(baseUrl)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }


    }

    fun <Api> buildApi(
        api: Class<Api>,
        authToken: String? = null
    ):Api{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                .addInterceptor {chain ->
                    chain.proceed(chain.request().newBuilder().also{
                        authToken?.let { it1 -> it.addHeader("Authorization","Bearer $authToken") }
                    }.build())
                }.also {client ->

                if (BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                    client.addInterceptor(logging)
                        client.addInterceptor(ReceivedCookiesInterceptor())
                                client.addInterceptor(AddCookiesInterceptor())
                }

            }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }


}

class ReceivedCookiesInterceptor() : Interceptor {
//    private val context: Context

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse: Response = chain.proceed(chain.request())
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
//            val cookies = PreferenceManager.getDefaultSharedPreferences(context)
//                .getStringSet("PREF_COOKIES", HashSet()) as HashSet<String>?
            for (header in originalResponse.headers("Set-Cookie")) {
                RetrofitInstances.cokkies!!.add(header)
            }
//            val memes = PreferenceManager.getDefaultSharedPreferences(context).edit()
//            memes.putStringSet("PREF_COOKIES", RetrofitInstances.cokkies).apply()
//            memes.commit()
        }

        Log.e("hashset",originalResponse.toString())
        Log.e("cookkies",RetrofitInstances.cokkies.toString())
        return originalResponse
    }

}

class AddCookiesInterceptor() : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
//        val preferences = PreferenceManager.getDefaultSharedPreferences(
//            context).getStringSet(PREF_COOKIES, HashSet()) as HashSet<String>?
        val original: Request = chain.request()
//        if (original.url.toString().contains("distributor")) {
            for (cookie in RetrofitInstances.cokkies!!) {
                builder.addHeader("Cookie", cookie)
            }
//        }
        return chain.proceed(builder.build())
    }

    companion object {
        const val PREF_COOKIES = "PREF_COOKIES"
    }
}