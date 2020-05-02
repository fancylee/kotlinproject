package com.example.kotlin.adapter

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.kotlin.R
import com.example.kotlin.glide.GlideApp
import com.example.kotlin.model.bean.HomeBean

class FollowAdapter(context: Context,dataList:ArrayList<HomeBean.Issue.Item>) :CommonAdapter<HomeBean.Issue.Item> (context,dataList,object: MultipleType<HomeBean.Issue.Item>{
    override fun getLayoutId(item: HomeBean.Issue.Item, position: Int): Int {

        return when{
            item.type == "videoCollectionWithBrief"  -> R.layout.item_fellow

            else ->
                throw IllegalAccessException("Api 解析出错了，出现其他类型")

        }
    }

}){


    fun addData(dataList:ArrayList<HomeBean.Issue.Item>){
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }


    override fun bindData(holder: ViewHolder, data: HomeBean.Issue.Item, position: Int) {

        when {
            data.type == "videoCollectionWithBrief" -> setAuthorInfo(data, holder)
        }
    }

    private fun setAuthorInfo(item: HomeBean.Issue.Item, holder: ViewHolder) {
        val headerData = item.data?.header
        /**
         * 加载作者头像
         */
        holder.setImagePath(R.id.iv_avatar, object : ViewHolder.HolderImageLoader(headerData?.icon!!) {
            override fun loadImage(iv: ImageView, path: String) {
                GlideApp.with(mContext)
                    .load(path)
                    .placeholder(R.mipmap.default_avatar).circleCrop()
                    .transition(DrawableTransitionOptions().crossFade())
                    .into(holder.getView(R.id.iv_avatar))
            }

        })
        holder.setText(R.id.tv_title, headerData.title)
        holder.setText(R.id.tv_desc, headerData.description)

        val recyclerView = holder.getView<RecyclerView>(R.id.fl_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(mContext as Activity,LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = FollowHorizontalAdapter(mContext,item.data.itemList,R.layout.item_follow_horizontal)

    }
}