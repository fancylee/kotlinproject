package com.example.kotlin.mvp.contract

import com.example.kotlin.base.IBaseView
import com.example.kotlin.base.IPresenter
import com.example.kotlin.mvp.model.bean.CategoryBean

interface CategoryContract {

    interface View:IBaseView{

        /**
         * 显示分类的信息
         */
        fun showCategory(categoryList: ArrayList<CategoryBean>)


        /**
         * 显示错误信息
         */
        fun showError(errorMsg:String,errorCode:Int)
    }

    interface Presenter:IPresenter<View>{

        fun getCategoryData()
    }

}