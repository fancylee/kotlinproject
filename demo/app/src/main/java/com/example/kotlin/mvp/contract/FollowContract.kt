package com.example.kotlin.mvp.contract

import com.example.kotlin.base.IBaseView
import com.example.kotlin.base.IPresenter
import com.example.kotlin.model.bean.HomeBean

interface FollowContract {

    interface View :IBaseView{

        fun setFollowInfo(issue: HomeBean.Issue)

        fun showError(errorMsg: String, errorCode: Int)

    }

    interface Presenter:IPresenter<View>{

        fun requestFollowList()

        fun loadMoreData()
    }
}