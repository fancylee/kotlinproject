package com.example.kotlin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.kotlin.R
import com.example.kotlin.adapter.BaseFragmentAdapter
import com.example.kotlin.base.BaseFragment
import com.example.kotlin.mvp.contract.HotTabContract
import com.example.kotlin.mvp.model.bean.TabInfoBean
import com.example.kotlin.mvp.presenter.HotTabPresenter
import com.example.kotlin.utils.StatusBarUtil
import com.example.kotlin.view.TabLayoutHelper
import kotlinx.android.synthetic.main.fragment_hot.*

class HotFragment : BaseFragment(),HotTabContract.View {

    /**
     * 存放tab标题
     */
    private val mTabTitleList = ArrayList<String>()

    private val mFragmentList = ArrayList<Fragment>()

    private val mPresenter by lazy { HotTabPresenter() }

    init {
        mPresenter.attachView(this)
    }

    private var mTitle: String? = null

    companion object {
        fun getInstance(title: String): HotFragment {
            val fragment = HotFragment()
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
        mLayoutStatusView = multipleStatusView
        activity?.let { StatusBarUtil.darkMode(it) }
        activity?.let { StatusBarUtil.setPaddingSmart(it, toolbar) }

    }

    override fun lazyLoad() {
        mPresenter.getTabInfo()
    }

    override fun setTabInfo(tabInfoBean: TabInfoBean) {

        multipleStatusView.showContent()
        tabInfoBean.tabInfo.tabList.mapTo(mTabTitleList){it.name}
        tabInfoBean.tabInfo.tabList.mapTo(mFragmentList){RankFragment.getInstance(it.apiUrl)}

        mViewPager.adapter = BaseFragmentAdapter(childFragmentManager,mFragmentList,mTabTitleList)
        mTabLayout.setupWithViewPager(mViewPager)
        TabLayoutHelper.setUpIndicatorWidth(mTabLayout)



    }

    override fun showError(errorMsg: String, errorCode: Int) {
    }

    override fun showLoading() {
        multipleStatusView.showLoading()
    }

    override fun dismissLoading() {

    }
}