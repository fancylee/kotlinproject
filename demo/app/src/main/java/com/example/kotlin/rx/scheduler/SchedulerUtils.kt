package com.example.kotlin.rx.scheduler

object SchedulerUtils {
    fun<T> ioToMain():IoMainScheduler<T>{
        return IoMainScheduler<T>()
    }
}