package com.example.apple.zhbjnews.page.menupage;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.apple.zhbjnews.bean.NewsMenu;

/**
 * Created by apple on 16/8/24.
 */
public class InteractMenuDetailPager extends BaseMenuDetailPager {

    public InteractMenuDetailPager(Activity activity, NewsMenu.NewsMenuData menuDataInfo) {
        super(activity, menuDataInfo);
    }

    @Override
    public View initView() {
        TextView textView = new TextView(mActivity);
        textView.setText(menuDataInfo.title);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.BLACK);
        return textView;
    }
}
