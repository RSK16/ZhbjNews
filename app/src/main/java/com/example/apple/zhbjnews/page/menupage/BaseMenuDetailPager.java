package com.example.apple.zhbjnews.page.menupage;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import com.example.apple.zhbjnews.bean.NewsMenu;

/**
 * 新闻中心下, 几个菜单子页面的基类
 * Created by apple on 16/8/24.
 */
public abstract class BaseMenuDetailPager {
    private static final String TAG ="BaseMenuDetailPager" ;
    public Activity mActivity;
    public NewsMenu.NewsMenuData menuDataInfo;
    public View mRootView;

    public BaseMenuDetailPager(Activity activity, NewsMenu.NewsMenuData menuDataInfo) {
        this.mActivity = activity;
        this.menuDataInfo = menuDataInfo;
        mRootView = initView();
    }

    /**
     * 初始化界面
     * @return
     */
    public abstract View initView();

    /**
     * 初始化数据
     */
    public void initData() {
        Log.i(TAG, "initData: ");
    }
}
