package com.example.xpayback.ui.base

import com.example.xpayback.network.Resource
import com.example.xpayback.ui.auth.APiException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.lang.StringBuilder

abstract class BaseRepository {

    suspend fun <T> safeApiCall(
        apiCall : suspend  () ->T) :Resource<T> {
        return withContext(Dispatchers.IO){
            try {
                Resource.Success(apiCall.invoke())
            }catch (throwable : Throwable){
                when(throwable){
                    is HttpException -> {
                        Resource.Failure(false,throwable.code(),throwable.response()?.errorBody())
                    }
                    else -> {
                        Resource.Failure(true,null,null)
                    }
                }
            }
        }
    }

    suspend fun <T : Any> apiRequest (call: suspend () -> Response<T>) : T {
        val response = call.invoke()

        if (response.isSuccessful){
            return response.body()!!
        }else{
            val error = response.errorBody()?.string()

            val message = StringBuilder()
            error?.let {
                try {
                    message.append(
                        JSONObject(it).getString("message"))
                }catch (e : JSONException){}
                message.append("\n")

            }
            message.append("Error Code : ${response.code()}")

            throw APiException(message = message.toString())
        }
    }
}