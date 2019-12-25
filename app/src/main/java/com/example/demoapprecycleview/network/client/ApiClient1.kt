package com.basekotlin.network.client


import android.annotation.SuppressLint
import android.content.Context
import com.commonlibrary.util.Utils
import com.example.demoapprecycleview.BuildConfig
import com.example.demoapprecycleview.utils.DebugLog
import com.google.gson.JsonIOException
import com.example.demoapprecycleview.network.client.ApiInterface
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@SuppressLint("StaticFieldLeak")
class ApiClient1 {

    companion object {
        var apiInterface: ApiInterface? = null
        fun getInstance(context: Context): ApiInterface {
            if (apiInterface == null) {
                apiInterface = getApiClient(context)
            }
            return apiInterface as ApiInterface
        }

        /**
         * Generate API interface object for foreground with progress bar
         *
         * @param con             instance of Context
         * @param loaderMessage   any message want to display with progress bar as String
         * @param isForBackground true if want to execute API calling in background
         */
        fun getApiClient(con: Context): ApiInterface {

            return getRetrofit(con).create(ApiInterface::class.java)
        }


        /**
         * Generate Retrofit Client
         */
        private fun getRetrofit(con: Context): Retrofit {
            val builder = Retrofit.Builder()
            builder.baseUrl("https://sterlite.demo.brainvire.com/wp-json/custom-api/v1/")
            builder.addConverterFactory(GsonConverterFactory.create())
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            builder.client(getOkHttpClient(con))
            return builder.build()
        }

        private fun getRetrofitXML() {

        }

        /**
         * generate OKhttp client
         */
        private fun getOkHttpClient(con: Context): OkHttpClient {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val builder = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                builder.addInterceptor(logging)
            }
            builder.readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(Interceptor { chain ->
                    val request = chain.request()
                    var response: Response? = null
                    try {
                        response = chain.proceed(request)
                        if (response != null) {

                        } else {
                            Utils.hideProgressBar()
                        }
                    } catch (e: Exception) {
                        DebugLog.print(e)
                        Utils.hideProgressBar()


                    }

                    response!!
                })
                .build()
            return builder.build()

        }


        /**
         * generate custom response for exception
         */
        private fun generateCustomResponse(
            code: Int,
            message: String,
            request: Request
        ): Response? {
            try {
                val body = ResponseBody.create(
                    MediaType.parse("application/json"),
                    getJSONObjectForException(message, code).toString()
                )
                return Response.Builder()
                    .code(code)
                    .request(request)
                    .protocol(Protocol.HTTP_1_1)
                    .body(body)
                    .message(message)
                    .build()
            } catch (ex: JsonIOException) {
                DebugLog.print(ex)
                return null
            }

        }

        /**
         * generate JSON object for error case
         */
        private fun getJSONObjectForException(message: String, code: Int): JSONObject {

            try {
                val jsonMainObject = JSONObject()

                val `object` = JSONObject()
                `object`.put("status", false)
                `object`.put("message", message)
                `object`.put("message_code", code)
                `object`.put("status_code", code)

                jsonMainObject.put("meta", `object`)

                val obj = JSONObject()
                obj.put("error", JSONArray().put(message))

                jsonMainObject.put("errors", obj)

                return jsonMainObject
            } catch (e: JSONException) {
                DebugLog.print(e)
                return JSONObject()
            }

        }

    }

    fun getApiClient(): ApiInterface? {
        return apiInterface
    }

//    @Throws(NoConnectionException::class)
//    fun apiRequest(
//        activity: Context,
//        requestObject: Any,
//        reqCode: Int,
//        apiResponseListener: ApiResponseListener,
//        isProgressDialogShow: Boolean
//    ) {
//        val restClientModel = RestClientModel()
//        restClientModel.prgMsg = ""
//        restClientModel.isProgressDialogShow = isProgressDialogShow
//        when (NetworkUtil.getConnectionStatus(activity) == NetworkUtil.NOT_CONNECTED) {
//            true -> throw NoConnectionException(activity.resources.getString(R.string.connection_error))
//
//            false -> {
//                when (reqCode) {
//                    1 -> {
//                        getApiClient()
//                            ?.callWelcome(requestObject as Int)// YOUR METHOD
//                            ?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
//                            ?.subscribe(object : CustomSubscriber<JsonObject>(activity) {
//                                override fun onFailure(code: Int, message: String) {
//                                    Utils.showToast(activity, message, 0, Toast.LENGTH_SHORT)
//                                }
//
//                                override fun onNext(t: JsonObject) {
//                                    super.onNext(t)
//                                    apiResponseListener.onApiResponse(t, reqCode)
//                                }
//
//                                override fun onError(t: Throwable) {
//                                    super.onError(t)
//                                    apiResponseListener.onApiError(t, reqCode)
//                                }
//
//                            })
//                    }
//                }
//            }
//        }
//    }


}
