package com.example.apple.zhbjnews.fragment;


import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.apple.zhbjnews.R;
import com.example.apple.zhbjnews.activity.HomeActivity;
import com.example.apple.zhbjnews.page.BasePager;
import com.example.apple.zhbjnews.page.GovAffairsPager;
import com.example.apple.zhbjnews.page.HomePager;
import com.example.apple.zhbjnews.page.NewsCenterPager;
import com.example.apple.zhbjnews.page.SettingPager;
import com.example.apple.zhbjnews.page.SmartServicePager;
import com.example.apple.zhbjnews.view.NoScrollViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;

/**
 *   主页面fragment
 */
public class ContentFragment extends BaseFragment {

    private NoScrollViewPager nsp_content;
    private RadioGroup rg_group;
    private ArrayList<BasePager> mPagers;// 五个标签页的集合
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content, null);
        nsp_content = (NoScrollViewPager) view.findViewById(R.id.nsp_content);
        rg_group = (RadioGroup) view.findViewById(R.id.rg_group);
        return view;
    }

    @Override
    public void initData() {
        mPagers = new ArrayList<BasePager>();
        // 添加五个标签页
        mPagers.add(new HomePager(mActivity));
        mPagers.add(new NewsCenterPager(mActivity));
        mPagers.add(new SmartServicePager(mActivity));
        mPagers.add(new GovAffairsPager(mActivity));
        mPagers.add(new SettingPager(mActivity));

        nsp_content.setAdapter(new myPageAdapter());

        // 底栏标签切换监听
        rg_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_fragmentcontent_home:
                        // 参2:表示是否具有滑动动画
                        nsp_content.setCurrentItem(0,false);
                        setSlidingMenuEnable(false);
                        break;
                    case R.id.rb_fragmentcontent_news:
                        nsp_content.setCurrentItem(1,false);
                        setSlidingMenuEnable(true);
                        NewsCenterPager newspager = (NewsCenterPager) mPagers.get(1);
                        newspager.getDataFromNet();
                        break;
                    case R.id.rb_fragmentcontent_smart:
                        nsp_content.setCurrentItem(2,false);
                        setSlidingMenuEnable(false);
                        break;
                    case R.id.rb_fragmentcontent_gov:
                        nsp_content.setCurrentItem(3,false);
                        setSlidingMenuEnable(false);
                        break;
                    case R.id.rb_fragmentcontent_setting:
                        nsp_content.setCurrentItem(4,false);
                        setSlidingMenuEnable(false);
                        break;

                }
            }
        });
        nsp_content.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                BasePager pager = mPagers.get(position);
                pager.initData();
                if (position == 0 || position == mPagers.size() - 1) {
                    // 首页和设置页要禁用侧边栏
                    setSlidingMenuEnable(false);
                } else {
                    // 其他页面开启侧边栏
                    setSlidingMenuEnable(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        //
        // 手动加载第一页数据
        mPagers.get(0).initData();
        // 首页禁用侧边栏
        setSlidingMenuEnable(false);
        //让首页高亮
        rg_group.check(R.id.rb_fragmentcontent_home);
    }
    /**
     * 开启或禁用侧边栏
     *
     * @param enable
     */
    protected void setSlidingMenuEnable(boolean enable) {
        // 获取侧边栏对象
        HomeActivity mainUI = (HomeActivity) mActivity;
        SlidingMenu slidingMenu = mainUI.getSlidingMenu();
        if (enable) {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        } else {
            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }
    }
    /**
     * 获取新闻中心页面
     *
     * @return
     */
    public NewsCenterPager getNewsCenterPager() {
        return (NewsCenterPager) mPagers.get(1);
    }

    class myPageAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mPagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager pager = mPagers.get(position);
            View view = pager.mRootView;
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}

