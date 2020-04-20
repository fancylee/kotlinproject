package com.example.kotlin

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import com.example.kotlin.utils.DisplayManager
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.LogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.squareup.leakcanary.LeakCanary
import com.squareup.leakcanary.RefWatcher
import kotlin.properties.Delegates

class MyApplication:Application(){

    var refWatcher:RefWatcher? = null

    companion object{
        val TAG = "MyApplication"

        var context: Context by Delegates.notNull()

        fun getRefWatcher(context: Context):RefWatcher?{

            val myApplication = context.applicationContext as MyApplication
            return  myApplication.refWatcher
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        refWatcher = setupLeakCanary()
        DisplayManager.init(context)
        initConfig()
        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
    }

    private fun setupLeakCanary(): RefWatcher {
        return if (LeakCanary.isInAnalyzerProcess(this)) {
            RefWatcher.DISABLED
        } else LeakCanary.install(this)
    }

    fun initConfig(){
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)
            .methodCount(0)
            .methodOffset(7)
            .tag("kotlindemo")
            .build()
        Logger.addLogAdapter(object:AndroidLogAdapter(formatStrategy){

            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

    val mActivityLifecycleCallbacks = object: ActivityLifecycleCallbacks{
        override fun onActivityCreated(p0: Activity?, p1: Bundle?) {
            Logger.d(TAG,"onCreated: " +p0?.componentName?.className)
        }

        override fun onActivityStarted(p0: Activity?) {
        }

        override fun onActivityResumed(p0: Activity?) {
        }

        override fun onActivityPaused(p0: Activity?) {
        }

        override fun onActivityDestroyed(p0: Activity?) {
            Logger.d(TAG,"onActivityDestroyed: " +p0?.componentName?.className)
        }

        override fun onActivityStopped(p0: Activity?) {
        }

        override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {
        }
    }
}