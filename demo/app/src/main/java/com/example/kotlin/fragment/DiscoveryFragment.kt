package com.example.kotlin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.kotlin.R
import com.example.kotlin.adapter.BaseFragmentAdapter
import com.example.kotlin.base.BaseFragment
import com.example.kotlin.utils.StatusBarUtil
import com.example.kotlin.view.TabLayoutHelper
import kotlinx.android.synthetic.main.fragment_hot.*

class DiscoveryFragment : BaseFragment() {


    private var mTitle: String? = null

    private val tabList = ArrayList<String>()

    private val fragments = ArrayList<Fragment>()


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
        activity?.let { StatusBarUtil.darkMode(it) }
        activity?.let { StatusBarUtil.setPaddingSmart(it, toolbar) }

        tv_header_title.text = mTitle
        tabList.add("关注")
        tabList.add("分类")
        fragments.add(FollowFragment.getInstance("关注"))
        fragments.add(CategoryFragment.getInstance("分类"))

        mViewPager.adapter = BaseFragmentAdapter(childFragmentManager,fragments,tabList)
        mTabLayout.setupWithViewPager(mViewPager)

        TabLayoutHelper.setUpIndicatorWidth(mTabLayout)

    }

    override fun lazyLoad() {
    }
}