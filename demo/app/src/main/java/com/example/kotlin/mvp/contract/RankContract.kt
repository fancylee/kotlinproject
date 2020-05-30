package com.example.kotlin.mvp.contract

import com.example.kotlin.base.IBaseView
import com.example.kotlin.base.IPresenter
import com.example.kotlin.model.bean.HomeBean

interface RankContract {

    interface View:IBaseView{

        fun setRankList(itemList:ArrayList<HomeBean.Issue.Item>)

        fun showError(errorMsg:String,errorCode:Int)
    }

    interface Presenter:IPresenter<View>{


        fun requestRankList(apiUrl:String)
    }
}