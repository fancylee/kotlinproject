package com.example.kotlin.mvp.model

import com.example.kotlin.model.bean.HomeBean
import com.example.kotlin.net.RetrofitManager
import com.example.kotlin.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

class RankModel {

    fun requestRankList(apiUrl:String) : Observable<HomeBean.Issue>{
        return  RetrofitManager.service.getIssueData(apiUrl)
            .compose(SchedulerUtils.ioToMain())
    }
}