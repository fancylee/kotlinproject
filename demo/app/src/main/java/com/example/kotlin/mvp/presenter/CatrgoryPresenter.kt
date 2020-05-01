package com.example.kotlin.mvp.presenter

import com.example.kotlin.base.BasePresenter
import com.example.kotlin.mvp.contract.CategoryContract
import com.example.kotlin.mvp.model.CategoryModel
import com.example.kotlin.net.exception.ExceptionHandle

class CategoryPresenter :BasePresenter<CategoryContract.View>(),CategoryContract.Presenter {

    private val categoryModel:CategoryModel by lazy {
        CategoryModel()
    }

    override fun getCategoryData() {

        checkViewAttached()
        mRootView?.showLoading()

        val disposable = categoryModel.getCategoryData()
            .subscribe({
                run {
                    mRootView?.apply {
                        dismissLoading()
                        showCategory(it)
                    }
                }

            },{
                run {
                    mRootView?.apply {
                        showError(ExceptionHandle.handleException(it), ExceptionHandle.errorCode)
                    }
                }

            })

        addSubscription(disposable)
    }
}