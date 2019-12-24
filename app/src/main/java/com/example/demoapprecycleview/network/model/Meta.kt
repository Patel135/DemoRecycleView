package com.kotlinusermodule.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Meta: Serializable {

    @SerializedName("status")
    @Expose
    var status: Boolean? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("message_code")
    @Expose
    var messageCode: String? = null
    @SerializedName("status_code")
    @Expose
    var statusCode: Int? = null

}