package com.example.kotlin.fragment

import android.graphics.Canvas
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.R
import com.example.kotlin.adapter.CategoryAdapter
import com.example.kotlin.adapter.CategoryDetailAdapter
import com.example.kotlin.base.BaseFragment
import com.example.kotlin.model.bean.HomeBean
import com.example.kotlin.mvp.contract.RankContract
import com.example.kotlin.mvp.presenter.RankPresenter
import com.example.kotlin.net.exception.ErrorStatus
import com.example.kotlin.showToast
import com.example.kotlin.utils.DisplayManager
import kotlinx.android.synthetic.main.fragment_rank.*

class RankFragment :BaseFragment(),RankContract.View {

    private val mPresenter by lazy { RankPresenter() }

    private val mAdapter by lazy { activity?.let {
        CategoryDetailAdapter(
            it,
            itemList,
            R.layout.item_category_detail
        )
    } }

    private var itemList = ArrayList<HomeBean.Issue.Item>()

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

    init {
        mPresenter.attachView(this)
    }
    override fun getLayoutId(): Int  = R.layout.fragment_rank

    override fun initView() {

        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = mAdapter
        mLayoutStatusView = multipleStatusView
        mRecyclerView.addItemDecoration(object :RecyclerView.ItemDecoration(){
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
//                super.getItemOffsets(outRect, view, parent, state)
                val offset = DisplayManager.dip2px(2f)!!
                val position = parent.getChildAdapterPosition(view)
                outRect.set(offset, if(position==0) offset else 0, offset , offset)

            }
        })


    }

    override fun lazyLoad() {
        if(!apiUrl.isNullOrEmpty()){
            mPresenter.requestRankList(apiUrl!!)

        }
    }

    override fun setRankList(itemList: ArrayList<HomeBean.Issue.Item>) {
        multipleStatusView.showContent()
        mAdapter?.addData(itemList)
    }

    override fun showError(errorMsg: String, errorCode: Int) {
        showToast(errorMsg)
        if(errorCode == ErrorStatus.NETWORK_ERROR){
            multipleStatusView.showNoNetwork()
        }else{
            multipleStatusView.showError()
        }
    }

    override fun showLoading() {
        multipleStatusView.showLoading()
    }

    override fun dismissLoading() {

    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}