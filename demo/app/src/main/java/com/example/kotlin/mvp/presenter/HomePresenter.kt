package com.example.kotlin.mvp.presenter

import com.example.kotlin.base.BasePresenter
import com.example.kotlin.model.HomeModel
import com.example.kotlin.model.bean.HomeBean
import com.example.kotlin.mvp.contract.HomeContract
import com.example.kotlin.net.exception.ExceptionHandle
import io.reactivex.functions.Function

class HomePresenter :BasePresenter<HomeContract.View>(),HomeContract.Presenter {

    private var bannerHomeBean: HomeBean? = null

    private var nextPageUrl:String?=null     //加载首页的Banner 数据+一页数据合并后，nextPageUrl没 add

    private val homeModel:HomeModel by lazy {
        HomeModel()
    }

    /**
     * 获取首页精选数据 banner 加 一页数据
     */
    override fun requestHomeData(num: Int) {

        checkViewAttached()
        mRootView?.showLoading()

        val disposable = homeModel.requestHomeData(num)
            .flatMap{ t: HomeBean ->
                val bannerItemList = t.issueList[0].itemList

                bannerItemList.filter { item ->
                    item.type=="banner2"|| item.type=="horizontalScrollCard"
                }.forEach { it-> bannerItemList.remove(it) }

                bannerHomeBean = t //记录第一页是当做 banner 数据
                homeModel.loadMoreData(t.nextPageUrl)
            }.subscribe({homeBean ->
                mRootView?.apply {
                    dismissLoading()

                    nextPageUrl = homeBean.nextPageUrl
                    //过滤掉 Banner2(包含广告,等不需要的 Type), 具体查看接口分析
                    val newBannerItemList = homeBean.issueList[0].itemList

                    newBannerItemList.filter { item ->
                        item.type=="banner2"||item.type=="horizontalScrollCard"
                    }.forEach{ item ->
                        //移除 item
                        newBannerItemList.remove(item)
                    }
                    //赋值过滤后的数据 + banner 数据
                    bannerHomeBean?.issueList!![0].itemList.addAll(newBannerItemList)

                    setHomeData(bannerHomeBean!!)
                }

            }, {e->
                mRootView?.apply {
                    dismissLoading()
                    showError(ExceptionHandle.handleException(e),ExceptionHandle.errorCode)
                }

            })

        addSubscription(disposable)
    }

    /**
     * 加载更多
     */
    override fun loadMoreData() {

        var disposable = nextPageUrl?.let {
            homeModel.loadMoreData(it)
                .subscribe({ homeBean->
                    mRootView?.apply {
                        //过滤掉 Banner2(包含广告,等不需要的 Type), 具体查看接口分析
                        val newItemList = homeBean.issueList[0].itemList

                        newItemList.filter { item ->
                            item.type=="banner2"||item.type=="horizontalScrollCard"
                        }.forEach{ item ->
                            //移除 item
                            newItemList.remove(item)
                        }

                        nextPageUrl = homeBean.nextPageUrl
                        setMoreData(newItemList)
                    }

                },{ t ->
                    mRootView?.apply {
                        showError(ExceptionHandle.handleException(t),ExceptionHandle.errorCode)
                    }
                })
        }

        if (disposable != null) {
            addSubscription(disposable)
        }
    }
}