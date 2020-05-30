package com.example.kotlin.mvp.presenter

import com.example.kotlin.base.BasePresenter
import com.example.kotlin.mvp.contract.RankContract
import com.example.kotlin.mvp.model.RankModel
import com.example.kotlin.net.exception.ExceptionHandle

class RankPresenter :BasePresenter<RankContract.View>(),RankContract.Presenter {

    private val rankModel by lazy {
        RankModel()
    }
    override fun requestRankList(apiUrl: String) {

        checkViewAttached()
        this.mRootView?.showLoading()
        val disposable = rankModel.requestRankList(apiUrl)
            .subscribe({

                mRootView?.apply {
                    dismissLoading()
                    setRankList(it.itemList)
                }
            },{

                mRootView?.apply {
                    showError(ExceptionHandle.handleException(it),ExceptionHandle.errorCode)
                }
            })

        addSubscription(disposable)
    }
}