package com.example.kotlin.fragment

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.R
import com.example.kotlin.adapter.CategoryAdapter
import com.example.kotlin.base.BaseFragment
import com.example.kotlin.mvp.contract.CategoryContract
import com.example.kotlin.mvp.model.bean.CategoryBean
import com.example.kotlin.mvp.presenter.CategoryPresenter
import com.example.kotlin.net.exception.ErrorStatus
import com.example.kotlin.showToast
import com.example.kotlin.utils.DisplayManager
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.fragment_hot.*
import kotlinx.android.synthetic.main.fragment_hot.multipleStatusView

class CategoryFragment :BaseFragment(),CategoryContract.View {

    private val mPresenter by lazy { CategoryPresenter() }

    private var mCategoryList = ArrayList<CategoryBean>()

    private var mTitle: String? = null

    private val mAdapter by lazy { activity?.let { CategoryAdapter(it, mCategoryList, R.layout.item_category) } }


    companion object{
        fun getInstance(title:String) :CategoryFragment{
            val fragment = CategoryFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return  fragment
        }
    }
    override fun getLayoutId(): Int = R.layout.fragment_category

    override fun initView() {
        mPresenter.attachView(this)
        mLayoutStatusView = multipleStatusView

        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = GridLayoutManager(activity,2)

        mRecyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                val position = parent.getChildPosition(view)
                val offset = DisplayManager.dip2px(2f)!!
                outRect.set(if (position % 2 == 0) 0 else offset, offset,
                    if (position % 2 == 0) offset else 0, offset)
            }
        })
    }

    override fun lazyLoad() {
        mPresenter.getCategoryData()
    }

    override fun showCategory(categoryList: ArrayList<CategoryBean>) {
        mCategoryList = categoryList
        mAdapter?.setData(mCategoryList)
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
        multipleStatusView.showContent()
    }
}