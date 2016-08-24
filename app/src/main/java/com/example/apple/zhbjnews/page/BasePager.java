package com.example.apple.zhbjnews.page;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.apple.zhbjnews.R;
import com.example.apple.zhbjnews.activity.HomeActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * Created by apple on 16/8/24.
 */
public class BasePager {
    public Activity mActivity;

    public TextView tvTitle;
    public ImageButton ib_title_menu;
    public FrameLayout fl_content;// 空的帧布局对象, 要动态添加布局

    public View mRootView;// 当前页面的布局对象
    private ImageButton ib_title_set;

    public BasePager(Activity activity) {
        mActivity = activity;
        mRootView = initView();
    }

    // 初始化布局
    public View initView() {
        View view = View.inflate(mActivity, R.layout.base_page, null);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        ib_title_menu = (ImageButton) view.findViewById(R.id.ib_title_menu);
        fl_content = (FrameLayout) view.findViewById(R.id.fl_content);
        ib_title_set = (ImageButton) view.findViewById(R.id.ib_title_set);

        ib_title_menu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                toggle();
            }
        });

        return view;
    }

    /**
     * 打开或者关闭侧边栏
     */
    protected void toggle() {
        HomeActivity mainUI = (HomeActivity) mActivity;
        SlidingMenu slidingMenu = mainUI.getSlidingMenu();
        slidingMenu.toggle();// 如果当前状态是开, 调用后就关; 反之亦然
    }

    // 初始化数据
    public void initData() {

    }
}
