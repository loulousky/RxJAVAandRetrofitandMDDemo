/*
 * Copyright 2014 trinea.cn All right reserved. This software is the confidential and proprietary information of
 * trinea.cn ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with trinea.cn.
 */
package cn.gamedog.dayspeedassist.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gc.materialdesign.views.LayoutRipple;

import java.util.List;

import butterknife.Bind;

import butterknife.ButterKnife;
import cn.gamedog.dayspeedassist.R;
import cn.gamedog.dayspeedassist.bean.Itemdata;
import cn.gamedog.dayspeedassist.utils.ListUtils;

/**
 * ImagePagerAdapter
 *
 * @author mengfanlu
 */
public class ImagePagerAdapter extends RecyclingPagerAdapter {


    private Context context;
    private List<Itemdata> imageIdList;

    private int size;
  
    private static boolean        isInfiniteLoop;
    public ImagePagerAdapter(Activity context, List<Itemdata> imageIdList) {
        this.context = context;
        this.imageIdList = imageIdList;
        this.size = ListUtils.getSize(imageIdList);
        this.isInfiniteLoop = false;
    }

    @Override
    public int getCount() {
        // Infinite loop
        return isInfiniteLoop ? Integer.MAX_VALUE : ListUtils.getSize(imageIdList);
    }

    /**
     * get really position
     *
     * @param position
     * @return
     */
    private int getPosition(int position) {
        return isInfiniteLoop ? position % size : position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup container) {
        final ViewHolder holder;
        if (view == null) {

            view = View.inflate(context, R.layout.main_banner_img, null);
            holder = new ViewHolder(view);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }




        Glide.with(context).load(imageIdList.get(getPosition(position)).getLitpic()).into(holder.tuijianHeaderImg);

            holder.tvBannertitle.setText(imageIdList.get(getPosition(position)).getTitle());

            holder.tuijianHeaderImg.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(context, NewsDetailActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("aid", imageIdList.get(getPosition(position)).getAid() + "");
//                    bundle.putString("title", imageIdList.get(getPosition(position)).getTitle());
//                    bundle.putString("litpic", imageIdList.get(getPosition(position)).getLitpic());
//                    intent.putExtras(bundle);
//                    context.startActivity(intent);
                }
            });



        return view;
    }

    static



    /**
     * @return the isInfiniteLoop
     */
    public boolean isInfiniteLoop() {
        return isInfiniteLoop;
    }

    /**
     * @param isInfiniteLoop the isInfiniteLoop to set
     */
    public ImagePagerAdapter setInfiniteLoop(boolean isInfiniteLoop) {
        this.isInfiniteLoop = isInfiniteLoop;
        return this;
    }

    static  class ViewHolder {
        @Bind(R.id.tuijian_header_img)
        ImageView tuijianHeaderImg;
        @Bind(R.id.tv_bannertitle)
        TextView tvBannertitle;
        @Bind(R.id.mode_btn_layout)
        LayoutRipple modeBtnLayout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
