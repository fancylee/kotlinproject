package com.example.kotlin.mvp.presenter

import com.example.kotlin.base.BasePresenter
import com.example.kotlin.mvp.contract.HotTabContract
import com.example.kotlin.mvp.model.HotTabModel
import com.example.kotlin.mvp.model.bean.TabInfoBean
import com.example.kotlin.net.exception.ExceptionHandle

class HotTabPresenter :BasePresenter<HotTabContract.View>(),HotTabContract.Presenter  {

    private val hotTabModel by lazy { HotTabModel() }
    override fun getTabInfo() {
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = hotTabModel.getTabInfo()
            .subscribe({
                t: TabInfoBean->
                mRootView?.setTabInfo(t)
            },{
                t: Throwable ->
                mRootView?.showError(ExceptionHandle.handleException(t),ExceptionHandle.errorCode)
            })
        addSubscription(disposable)
    }
}