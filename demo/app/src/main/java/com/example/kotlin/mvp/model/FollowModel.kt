package com.example.kotlin.mvp.model

import com.example.kotlin.model.bean.HomeBean
import com.example.kotlin.net.RetrofitManager
import com.example.kotlin.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

class FollowModel
{
    fun requestFollowList():Observable<HomeBean.Issue>{

        return RetrofitManager.service.getFollowInfo()
            .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 加载更多
     */
    fun loadMoreData(url:String):Observable<HomeBean.Issue>{
        return RetrofitManager.service.getIssueData(url)
            .compose(SchedulerUtils.ioToMain())
    }
}