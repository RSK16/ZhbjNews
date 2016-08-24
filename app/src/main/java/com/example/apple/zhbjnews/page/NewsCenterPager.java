package com.example.apple.zhbjnews.page;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.example.apple.zhbjnews.activity.HomeActivity;
import com.example.apple.zhbjnews.bean.NewsMenu;
import com.example.apple.zhbjnews.fragment.LeftMenuFragment;
import com.example.apple.zhbjnews.global.GlobaoContants;
import com.example.apple.zhbjnews.page.menupage.BaseMenuDetailPager;
import com.example.apple.zhbjnews.page.menupage.InteractMenuDetailPager;
import com.example.apple.zhbjnews.page.menupage.NewsMenuDetailPager;
import com.example.apple.zhbjnews.page.menupage.PhotosMenuDetailPager;
import com.example.apple.zhbjnews.page.menupage.TopicMenuDetailPager;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;

/**
 * Created by apple on 16/8/24.
 */
public class NewsCenterPager extends BasePager {
    private static final String TAG = "NewsCenterPager";
    private ArrayList<NewsMenu.NewsMenuData> mLeftMenuList;
    private ArrayList<BaseMenuDetailPager> mMenuDetailPagers;// 侧边栏详情页列表
    public NewsCenterPager(Activity mActivity) {
        super(mActivity);
    }
    @Override
    public void initData() {
        // 要给帧布局填充布局对象
        TextView view = new TextView(mActivity);
        view.setText("新闻中心页面");
        view.setTextColor(Color.RED);
        view.setTextSize(22);
        view.setGravity(Gravity.CENTER);

        fl_content.addView(view);
        // 修改页面标题
        tvTitle.setText("新闻中心");
    }
    /**
     * 从网络获取数据  使用XUtils
     */
    public void getDataFromNet() {
        //使用XUtils从网络获取数据
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, GlobaoContants.NEWS_URL,
                new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                //Log.i(TAG, "onSuccess 网络Json数据: "+responseInfo.result);
                parseJsonString(responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {

                Log.i(TAG, "onFailure: 网络Json数据");
            }
        });
    }

    /**
     * 使用GSON框架来解析Json
     * @param result
     */
    private void parseJsonString(String result) {
        Gson gson = new Gson();
        NewsMenu news = gson.fromJson(result, NewsMenu.class);
        //Log.i(TAG, "新闻中心数据: " + news);
        mLeftMenuList = news.data;

        HomeActivity mainUI = (HomeActivity) mActivity;// 获取Activity对象
        LeftMenuFragment fragment = mainUI.getLeftMenuFragment();// 获取侧边栏对象
        fragment.setNewsMenuData(mLeftMenuList);// 设置侧边栏数据

        // 初始化详情页数据
        mMenuDetailPagers = new ArrayList<BaseMenuDetailPager>();
        mMenuDetailPagers.add(new NewsMenuDetailPager(mActivity, news.data.get(0)));
        mMenuDetailPagers.add(new TopicMenuDetailPager(mActivity, news.data.get(0)));
        mMenuDetailPagers.add(new PhotosMenuDetailPager(mActivity, news.data.get(0)));
        mMenuDetailPagers.add(new InteractMenuDetailPager(mActivity, news.data.get(0)));

        setCurrentDetailPager(0);// 设置新闻页面为初始页面
    }

    /**
     * 设置初始页面
     * @param position
     */
    public void setCurrentDetailPager(int position) {
        Log.d(TAG, "详情页面:" + position);
        BaseMenuDetailPager detailPager = mMenuDetailPagers.get(position);
        fl_content.removeAllViews();// 填充界面前,先把以前的界面清空
        fl_content.addView(detailPager.mRootView);// 添加当前详情页的布局
        detailPager.initData();// 初始化数据

        tvTitle.setText(mLeftMenuList.get(position).title);
    }
}
