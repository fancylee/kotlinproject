package com.example.kotlin.base

interface IPresenter<in V:IBaseView> {
    fun attachView(mRootView:V)

    fun detachView()
}