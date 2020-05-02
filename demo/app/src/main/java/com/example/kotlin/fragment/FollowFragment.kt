package com.example.kotlin.fragment

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.R
import com.example.kotlin.adapter.FollowAdapter
import com.example.kotlin.base.BaseFragment
import com.example.kotlin.model.bean.HomeBean
import com.example.kotlin.mvp.contract.FollowContract
import com.example.kotlin.mvp.presenter.FollowPresenter
import com.example.kotlin.net.exception.ErrorStatus
import com.example.kotlin.showToast
import kotlinx.android.synthetic.main.layout_recyclerview.*

class FollowFragment :BaseFragment(),FollowContract.View {

    private var mTitle:String? = null

    private var itemList = ArrayList<HomeBean.Issue.Item>()

    private val mPresenter by lazy { FollowPresenter() }

    private val mFollowAdapter by lazy { activity?.let { FollowAdapter(it,itemList) } }


    /**
     * 是否加载更多
     */
    private var loadingMore = false

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
        mPresenter.attachView(this)
        mLayoutStatusView = multipleStatusView
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = mFollowAdapter
        mRecyclerView.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val itemCount = (mRecyclerView.layoutManager as LinearLayoutManager).itemCount
                val lastVisibleItem = (mRecyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (!loadingMore && lastVisibleItem == (itemCount - 1)) {
                    loadingMore = true
                    mPresenter.loadMoreData()
                }

            }
        })

    }

    override fun lazyLoad() {
        mPresenter.requestFollowList()
    }

    override fun setFollowInfo(issue: HomeBean.Issue) {
        loadingMore = false
        itemList = issue.itemList
        mFollowAdapter?.addData(itemList)
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        showToast(errorMsg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            multipleStatusView.showNoNetwork()
        } else {
            multipleStatusView.showError()
        }
    }

    override fun showLoading() {
        multipleStatusView.showLoading()
    }

    override fun dismissLoading() {
        multipleStatusView.showContent()

    }
    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}