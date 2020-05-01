package com.example.kotlin.mvp.model

import com.example.kotlin.model.bean.HomeBean
import com.example.kotlin.net.RetrofitManager
import com.example.kotlin.rx.scheduler.SchedulerUtils

import io.reactivex.Observable

/**
 * Created by xuhao on 2017/11/25.
 * desc:
 */
class VideoDetailModel {

    fun requestRelatedData(id:Long):Observable<HomeBean.Issue>{

        return RetrofitManager.service.getRelatedData(id)
                .compose(SchedulerUtils.ioToMain())
    }

}