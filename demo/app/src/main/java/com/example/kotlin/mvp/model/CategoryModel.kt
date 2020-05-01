package com.example.kotlin.mvp.model

import com.example.kotlin.mvp.model.bean.CategoryBean
import com.example.kotlin.net.RetrofitManager
import com.example.kotlin.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

class CategoryModel {

    fun getCategoryData(): Observable<ArrayList<CategoryBean>> {
        return RetrofitManager.service.getCategory()
            .compose(SchedulerUtils.ioToMain())
    }
}