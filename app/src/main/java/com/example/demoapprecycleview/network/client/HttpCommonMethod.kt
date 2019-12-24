package com.kotlinusermodule.network.client

import android.content.Context
import com.commonlibrary.util.Utils
import com.example.demoapprecycleview.utils.DebugLog
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken


object HttpCommonMethod {

    /**
     * check whether API response is success or not
     */
    fun isSuccessResponse(responseCode: Int): Boolean {

        return responseCode in 200..300
    }

    /**
     * check Error Message
     */
    fun getErrorMessage(error: JsonObject?): String {
        var value = ""
        if (error != null) {
            val obj = error.asJsonObject //since you know it's a JsonObject
            try {
                if (obj != null) {
                    val entries = obj.entrySet()//will return members of your object
                    for ((key, value1) in entries) {
                        println(key)
                        /*if (Validation.isNotNull(value)) {
                            value = value + "," + Utils.removeArrayBrace(value1.asJsonArray.toString())
                        } else {
                            value = Utils.removeArrayBrace(value1.asJsonArray.toString())
                        }*/
                    }
                }
            } catch (e: Exception) {
                DebugLog.print(e)
                value = ""
            }

        }
        return value
    }

    fun getToken(mContext: Context): String {
        return "Basic QnZzdGVybGl0ZTpzdGVybGl0ZUBCdjIwMTk="
    }

}
