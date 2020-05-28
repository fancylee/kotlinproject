package com.example.kotlin.fragment

import android.os.Bundle
import com.example.kotlin.R
import com.example.kotlin.base.BaseFragment

class RankFragment :BaseFragment() {

    private var apiUrl: String? = null

    companion object{
        fun getInstance(apiUrl:String):RankFragment{
            val fragment = RankFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.apiUrl = apiUrl
            return fragment
        }
    }
    override fun getLayoutId(): Int  = R.layout.fragment_rank

    override fun initView() {
    }

    override fun lazyLoad() {
    }
}