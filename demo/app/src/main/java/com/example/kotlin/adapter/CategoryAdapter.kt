package com.example.kotlin.adapter

import android.content.Context
import android.graphics.Typeface
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.kotlin.MyApplication
import com.example.kotlin.R
import com.example.kotlin.glide.GlideApp
import com.example.kotlin.mvp.model.bean.CategoryBean

class CategoryAdapter(mContext: Context,categoryList:ArrayList<CategoryBean>, layoutId:Int )  :CommonAdapter<CategoryBean>(mContext, categoryList, layoutId){


    private var textTypeface: Typeface?=null

    init {
        textTypeface = Typeface.createFromAsset(MyApplication.context.assets, "fonts/FZLanTingHeiS-DB1-GB-Regular.TTF")
    }

    /**
     * 设置新数据
     */
    fun setData(categoryList: ArrayList<CategoryBean>){
        mData.clear()
        mData = categoryList
        notifyDataSetChanged()
    }

    override fun bindData(holder: ViewHolder, data: CategoryBean, position: Int) {
        holder.setText(R.id.tv_category_name, "#${data.name}")
        holder.getView<TextView>(R.id.tv_category_name).typeface = textTypeface

        holder.setImagePath(R.id.iv_category,object :ViewHolder.HolderImageLoader(data.bgPicture){
            override fun loadImage(iv: ImageView, path: String) {
                GlideApp.with(mContext)
                    .load(path)
                    .placeholder(R.color.color_darker_gray)
                    .transition(DrawableTransitionOptions().crossFade())
                    .thumbnail(0.5f)
                    .into(iv)
            }

        })


    }
}