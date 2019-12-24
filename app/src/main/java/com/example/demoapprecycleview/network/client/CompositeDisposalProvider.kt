package com.kotlinusermodule.network.client


import io.reactivex.disposables.CompositeDisposable

class CompositeDisposalProvider {

    companion object {
        private var compositeDisposable: CompositeDisposable? = null

        val instance: CompositeDisposable
            @Synchronized get() {
                if (compositeDisposable == null) {
                    compositeDisposable = CompositeDisposable()
                }
                return compositeDisposable!!
            }
    }

    fun clearDisposable() {
        compositeDisposable!!.clear()
    }
}
