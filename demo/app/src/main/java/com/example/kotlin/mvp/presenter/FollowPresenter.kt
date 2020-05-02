package com.example.kotlin.mvp.presenter

import com.example.kotlin.base.BasePresenter
import com.example.kotlin.model.bean.HomeBean
import com.example.kotlin.mvp.contract.FollowContract
import com.example.kotlin.mvp.model.FollowModel
import com.example.kotlin.net.exception.ExceptionHandle

class FollowPresenter :BasePresenter<FollowContract.View>() ,FollowContract.Presenter {

    private  val followModel by lazy {
        FollowModel()
    }
    private var nextPageUrl:String?=null
    override fun requestFollowList() {
        checkViewAttached()
        mRootView?.showLoading()

        val disposable = followModel.requestFollowList()
            .subscribe({
                mRootView?.apply {
                    dismissLoading()
                    nextPageUrl = it.nextPageUrl
                    setFollowInfo(it)
                }
            },{
                mRootView?.apply {
                    showError(ExceptionHandle.handleException(it),ExceptionHandle.errorCode)

                }
            })

        addSubscription(disposable)
    }

    override fun loadMoreData() {

        val disposable = nextPageUrl?.let {
            followModel.loadMoreData(it)
                .subscribe(
                    {
                        mRootView?.apply {
                            nextPageUrl = it.nextPageUrl
                            setFollowInfo(it)
                        }
                    },{
                        mRootView?.apply {
                            showError(ExceptionHandle.handleException(it),ExceptionHandle.errorCode)
                        }
                    }
                )
        }
        if (disposable != null) {
            addSubscription(disposable)
        }
    }


}