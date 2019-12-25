package com.example.demoapprecycleview.network.client

import com.example.demoapprecycleview.model.HomeDataResponse
import com.example.demoapprecycleview.network.model.ResponseWrapper
import io.reactivex.Observable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @FormUrlEncoded
    @POST("Home")
    fun homeData(@FieldMap stringStringMap: Map<String, String>?, @Header("Authorization") token: String): Observable<ResponseWrapper<HomeDataResponse>>

}

