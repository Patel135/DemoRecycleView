package com.example.demoapprecycleview.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chartshealthcare.network.model.ApiResponse

/**
 * Created by Admin on 25-12-2019 00:39
 */
class HomeViewModel : ViewModel() {

    val responseLiveDataFavoriteList = MutableLiveData<ApiResponse>()

    fun homeAPI() {

    }
}