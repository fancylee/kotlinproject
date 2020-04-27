package com.example.kotlin.activity

import android.annotation.TargetApi
import android.os.Build
import android.transition.Transition
import androidx.core.view.ViewCompat
import com.example.kotlin.R
import com.example.kotlin.base.BaseActivity
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_video_detail.*

class VideoDetailActivity :BaseActivity() {


    companion object {
        const val IMG_TRANSITION = "IMG_TRANSITION"
        const val TRANSITION = "TRANSITION"
    }

    private var isTransition: Boolean = false

    private var transition: Transition? = null
    override fun start() {
    }

    override fun layoutId(): Int = R.layout.activity_video_detail

    override fun initData() {
    }

    override fun initView() {

        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            postponeEnterTransition()
            ViewCompat.setTransitionName(mVideoView,IMG_TRANSITION)
            addTransitionListener()
            startPostponedEnterTransition()

        }else{

        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun addTransitionListener() {
        transition = window.sharedElementEnterTransition
        transition?.addListener(object : Transition.TransitionListener {
            override fun onTransitionResume(p0: Transition?) {
            }

            override fun onTransitionPause(p0: Transition?) {
            }

            override fun onTransitionCancel(p0: Transition?) {
            }

            override fun onTransitionStart(p0: Transition?) {
            }

            override fun onTransitionEnd(p0: Transition?) {
                Logger.d("onTransitionEnd()------")

                transition?.removeListener(this)
            }

        })
    }
}