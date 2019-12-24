package com.example.demoapprecycleview.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.basekotlin.network.client.ApiClient1
import com.basekotlin.network.exeception.NoConnectionException
import com.chartshealthcare.network.model.ApiResponse
import com.commonlibrary.util.Utils
import com.example.demoapprecycleview.model.HomeDataRequest
import com.example.demoapprecycleview.model.HomeDataResponse
import com.kotlinusermodule.network.client.CustomSubscriber
import com.kotlinusermodule.network.client.HttpCommonMethod
import com.kotlinusermodule.network.model.RequestWrapper
import com.kotlinusermodule.network.model.ResponseWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object HomeRepository {

    fun getFavoriteList(
        context: Context,
        bean: HomeDataRequest,
        responseLiveData: MutableLiveData<ApiResponse>
    ) {

        try {
            val apiInterface = ApiClient1.getApiClient(context)
            Utils.showProgressBar(context, "Loading")
            // Using Rx Java
            apiInterface
                .homeData(
                    RequestWrapper<HomeDataRequest>().convertRequestToMap(bean)!!,
                    HttpCommonMethod.getToken(context)
                )
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : CustomSubscriber<ResponseWrapper<HomeDataResponse>>(context) {
                    override fun onFailure(code: Int, message: String) {
//                        Utils.showToast(context, message, 0, Toast.LENGTH_SHORT)
                    }

                    override fun onNext(t: ResponseWrapper<HomeDataResponse>) {
                        super.onNext(t)
                        responseLiveData.value = ApiResponse(t, "")
                        Log.e("====response", t.toString())

                    }

                    override fun onError(t: Throwable) {
                        super.onError(t)
                        Log.e("====onError", t.toString())
                    }

                })
        } catch (e: NoConnectionException) {
//            Utils.showToast(context, e.message.toString(), 0, Toast.LENGTH_SHORT)
        }
    }
}