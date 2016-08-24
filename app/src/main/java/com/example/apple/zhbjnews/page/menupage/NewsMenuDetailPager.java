package com.example.apple.zhbjnews.page.menupage;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.apple.zhbjnews.R;
import com.example.apple.zhbjnews.bean.NewsMenu;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

/**
 * Created by apple on 16/8/24.
 */
public class NewsMenuDetailPager extends BaseMenuDetailPager {


    private static final String TAG = "NewsMenuDetailPager";
    private ViewPager vp_newsdetail_content;
    private TabPageIndicator indicator_newsdetail_title;
    private ArrayList<TextView> newsdetailpagelist;
    public NewsMenuDetailPager(Activity activity, NewsMenu.NewsMenuData menuDataInfo) {
        super(activity, menuDataInfo);
    }

    @Override
    public View initView() {
//        TextView textView = new TextView(mActivity);
//        textView.setText(menuDataInfo.title);
//        textView.setGravity(Gravity.CENTER);
//        textView.setTextColor(Color.BLACK);
        View view = View.inflate(mActivity, R.layout.newsdetai_page, null);
        vp_newsdetail_content = (ViewPager) view.findViewById(R.id.vp_newsdetail_content);
        indicator_newsdetail_title = (TabPageIndicator) view.findViewById(R.id.indicator_newsdetail_title);
        return view;
    }

    @Override
    public void initData() {
        Log.i(TAG, "initData: ...........");
        newsdetailpagelist = new ArrayList<TextView>();
        for (int i=0;i<menuDataInfo.children.size();i++) {
            TextView textView = new TextView(mActivity);
            NewsMenu.NewsTabData newsTabData = menuDataInfo.children.get(i);
            Log.i(TAG, "initData: "+newsTabData.title);
            textView.setText(newsTabData.title);
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(50);
            textView.setTextColor(Color.BLACK);
            newsdetailpagelist.add(textView);
        }
        vp_newsdetail_content.setAdapter(new MyNewsdetailAdapter());
        indicator_newsdetail_title.setViewPager(vp_newsdetail_content);
    }

    class MyNewsdetailAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return newsdetailpagelist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView textView = newsdetailpagelist.get(position);
            container.addView(textView);
            return textView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return newsdetailpagelist.get(position).getText().toString();
        }
    }
}
