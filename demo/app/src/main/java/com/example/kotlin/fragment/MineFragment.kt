package com.example.kotlin.fragment

import android.os.Bundle
import com.example.kotlin.R
import com.example.kotlin.base.BaseFragment

class MineFragment : BaseFragment() {

    private var mTitle: String? = null

    companion object {
        fun getInstance(title: String): MineFragment {
            val fragment = MineFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }
    override fun getLayoutId(): Int  = R.layout.fragment_mine

    override fun initView() {
    }

    override fun lazyLoad() {
    }
}