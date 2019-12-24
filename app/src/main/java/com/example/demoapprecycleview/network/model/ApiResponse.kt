package com.chartshealthcare.network.model

import com.kotlinusermodule.network.model.ResponseWrapper


class ApiResponse(
    val data: ResponseWrapper<*>?,
    val errorMessage: String
)