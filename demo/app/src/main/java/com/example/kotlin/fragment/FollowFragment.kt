package com.example.kotlin.fragment

import android.os.Bundle
import com.example.kotlin.R
import com.example.kotlin.base.BaseFragment

class FollowFragment :BaseFragment() {

    private var mTitle:String? = null

    companion object{
        fun getInstance(title:String) :FollowFragment{
            val fragment = FollowFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    override fun getLayoutId(): Int = R.layout.layout_recyclerview

    override fun initView() {
    }

    override fun lazyLoad() {
    }
}