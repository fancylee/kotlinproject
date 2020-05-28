package com.example.kotlin.mvp.model

import com.example.kotlin.net.RetrofitManager
import com.example.kotlin.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

import com.example.kotlin.mvp.model.bean.TabInfoBean as TabInfoBean1

class HotTabModel {
    
    fun getTabInfo():Observable<TabInfoBean1>{

        return RetrofitManager.service.getRankList()
            .compose(SchedulerUtils.ioToMain())
    }
}