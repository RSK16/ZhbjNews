package com.example.apple.zhbjnews.page;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by apple on 16/8/24.
 */
public class SmartServicePager extends BasePager {
    public SmartServicePager(Activity mActivity) {
        super(mActivity);
    }
    @Override
    public void initData() {
        // 要给帧布局填充布局对象
        TextView view = new TextView(mActivity);
        view.setText("智慧服务页面");
        view.setTextColor(Color.RED);
        view.setTextSize(22);
        view.setGravity(Gravity.CENTER);

        fl_content.addView(view);
        // 修改页面标题
        tvTitle.setText("智慧服务");
    }
}
