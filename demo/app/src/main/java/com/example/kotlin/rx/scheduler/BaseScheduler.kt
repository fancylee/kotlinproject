package com.example.kotlin.rx.scheduler

import io.reactivex.*

  abstract class BaseScheduler<T> protected  constructor(private val subscribeOnScheduler:Scheduler,
                                                         private val observerOnSubscriber:Scheduler
                                           ):ObservableTransformer<T,T>

{
    override fun apply(upstream: Observable<T>): ObservableSource<T> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return upstream.subscribeOn(subscribeOnScheduler)
            .observeOn(observerOnSubscriber)

    }

}