package com.chartshealthcare.network.model

import com.example.demoapprecycleview.network.model.ResponseWrapper


class ApiResponse(
    val data: ResponseWrapper<*>?,
    val errorMessage: String
)