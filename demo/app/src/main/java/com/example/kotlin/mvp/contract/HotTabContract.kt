package com.example.kotlin.mvp.contract

import com.example.kotlin.base.IBaseView
import com.example.kotlin.base.IPresenter
import com.example.kotlin.mvp.model.bean.TabInfoBean

interface HotTabContract {

    interface View:IBaseView{
        /**
         * 设置TabInfo
         */
        fun  setTabInfo(tabInfoBean: TabInfoBean)

        fun showError(errorMsg:String,errorCode:Int)
    }

    interface Presenter:IPresenter<View>{
        fun getTabInfo()
    }
}