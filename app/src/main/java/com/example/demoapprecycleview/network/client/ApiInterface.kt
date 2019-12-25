package com.example.demoapprecycleview.network.client

import com.example.demoapprecycleview.model.HomeDataResponse
import com.example.demoapprecycleview.model.Rss
import com.example.demoapprecycleview.model.RssFeed
import com.example.demoapprecycleview.network.model.ResponseWrapper
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @FormUrlEncoded
    @POST("Home")
    fun homeData(@FieldMap stringStringMap: Map<String, String>?, @Header("Authorization") token: String): Observable<ResponseWrapper<HomeDataResponse>>

    @GET("feed")
    fun getFeed(): Call<RssFeed>

    @GET("data/read.xml")
    fun getData(): Call<Rss>

}

