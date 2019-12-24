package com.kotlinusermodule.network.model

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType
import okhttp3.RequestBody

import java.util.HashMap

class RequestWrapper<T> {

    fun convertRequestToMap(t: T): Map<String, String>? {
        val jsonString = Gson().toJson(t)
        return Gson().fromJson<HashMap<String, String>>(jsonString, object : TypeToken<HashMap<String, String>>() {

        }.type)
    }

    fun prepareRequestBodyPart(param: String): RequestBody {
        return RequestBody.create(MediaType.parse("text/plain"), param)
    }

}
