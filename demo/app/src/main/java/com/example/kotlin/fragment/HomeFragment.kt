package com.example.kotlin.fragment

import com.example.kotlin.R
import com.example.kotlin.base.BaseFragment
import com.example.kotlin.model.bean.HomeBean
import com.example.kotlin.mvp.contract.HomeContract

class HomeFragment :BaseFragment(),HomeContract.View {

    override fun getLayoutId(): Int  = R.layout.fragment_home

    override fun initView() {
    }

    override fun lazyLoad() {
    }

    override fun setHomeData(homeBean: HomeBean) {
    }

    override fun setMoreData(itemList: ArrayList<HomeBean.Issue.Item>) {
    }

    override fun showError(msg: String, errorCode: Int) {
    }

    override fun showLoading() {
    }

    override fun dismissLoading() {
    }
}