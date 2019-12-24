package com.kotlinusermodule.network.client

import android.content.Context
import com.basekotlin.network.model.ErrorWrapper
import com.commonlibrary.util.Utils
import com.example.demoapprecycleview.utils.DebugLog
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import retrofit2.HttpException

import java.io.IOException


abstract class CustomSubscriber<T>(private val mContext: Context) : Observer<T>, Subscriber<T> {

    override fun onSubscribe(disposable: Disposable) {
        try {
            CompositeDisposalProvider.instance.add(disposable)
        } catch (e: Exception) {
            DebugLog.print(e)
        }

    }

    override fun onSubscribe(subscription: Subscription) {

    }

    override fun onNext(t: T) {
        Utils.hideProgressBar()
    }

    override fun onError(t: Throwable) {
        Utils.hideProgressBar()
        parseErrorMessage(t)
    }

    /**
     * parse API Error Message
     */
    private fun parseErrorMessage(t: Throwable) {
        var message = t.message
        var code = 1007 // dummy code for timeout
        try {
            if (t is HttpException) {
                val res = t.response()?.errorBody()!!.string()
                val responseWrapper = Gson().fromJson<ErrorWrapper>(res, ErrorWrapper::class.java)
                code = responseWrapper.meta!!.status_code!!
                if (responseWrapper.meta!!.status_code!! === 422) {
                    if (responseWrapper.errors != null) {
                        message = HttpCommonMethod.getErrorMessage(responseWrapper.errors)
                    } else {
                        message = responseWrapper.meta!!.message
                    }
                } else {
                    message = responseWrapper.meta!!.message
                }
            }
        } catch (e: IOException) {
            DebugLog.print(e)
        } finally {
            onFailure(code, message!!)
        }
    }

    override fun onComplete() {
        CompositeDisposalProvider.instance.clear()
    }

    /**
     * Abstract method to get error message
     *
     * @param message  Error message
     * @param code  Http Status code
     */
    abstract fun onFailure(code: Int, message: String)

}