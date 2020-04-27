package com.example.kotlin.fragment

import android.os.Bundle
import com.example.kotlin.R
import com.example.kotlin.base.BaseFragment

class DiscoveryFragment : BaseFragment() {


    private var mTitle: String? = null

    companion object {
        fun getInstance(title: String): DiscoveryFragment {
            val fragment = DiscoveryFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }
    override fun getLayoutId(): Int {
        return  R.layout.fragment_hot
    }

    override fun initView() {
    }

    override fun lazyLoad() {
    }
}