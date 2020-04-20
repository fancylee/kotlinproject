package com.example.kotlin.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.example.kotlin.MainActivity
import com.example.kotlin.MyApplication
import com.example.kotlin.R
import com.example.kotlin.base.BaseActivity
import com.example.kotlin.utils.AppUtils
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_splash.*
import pub.devrel.easypermissions.EasyPermissions

class SplashActivity : BaseActivity(){

    private var textTypeface: Typeface?=null

    private var descTypeFace: Typeface?=null

    private var alphaAnimation: AlphaAnimation?=null


    init {
        textTypeface = Typeface.createFromAsset(MyApplication.context.assets, "fonts/Lobster-1.4.otf")
        descTypeFace = Typeface.createFromAsset(MyApplication.context.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun layoutId(): Int {

        return R.layout.activity_splash
    }

    override fun initData() {
    }

    @SuppressLint("SetTextI18n")
    override fun initView() {

        tv_app_name.typeface = textTypeface
        tv_splash_desc.typeface = descTypeFace
        tv_version_name.text = "v${AppUtils.getVerName(MyApplication.context)}"

        alphaAnimation = AlphaAnimation(0.3f,1.0f)
        alphaAnimation?.duration =2000
        alphaAnimation?.setAnimationListener(object:Animation.AnimationListener{
            override fun onAnimationRepeat(p0: Animation?) {


            }

            override fun onAnimationEnd(p0: Animation?) {
                redirectTo()
            }

            override fun onAnimationStart(p0: Animation?) {

            }

        })
        checkPermission()
    }

    fun redirectTo(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun checkPermission(){
        var perms = arrayOf(Manifest.permission.READ_PHONE_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
//        Logger.d("1111",*perms)
        EasyPermissions.requestPermissions(this,"应用需要以下权限，请允许",0,Manifest.permission.READ_PHONE_STATE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
//        super.onPermissionsGranted(requestCode, perms)
        if(requestCode == 0){
            perms.let {
                if (perms.contains(Manifest.permission.READ_PHONE_STATE)
                    && perms.contains(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    if (alphaAnimation != null) {
                        iv_web_icon.startAnimation(alphaAnimation)
                    }
                }
            }
        }
    }

}