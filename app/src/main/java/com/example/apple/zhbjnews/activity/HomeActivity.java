package com.example.apple.zhbjnews.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.apple.zhbjnews.R;
import com.example.apple.zhbjnews.fragment.ContentFragment;
import com.example.apple.zhbjnews.fragment.LeftMenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class HomeActivity extends SlidingFragmentActivity {
    private static final String FRAG_CONTENT = "frag_content";// 主页面Fragment的标记
    private static final String FRAG_MENU_LEFT = "frag_menu_left";// 侧边栏Fragment的标记
    private SlidingMenu slidingMenu;
    private FragmentManager fm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // 配置左侧菜单
        setBehindContentView(R.layout.fragment_left_menu);
        // 设置菜单模式
        slidingMenu = getSlidingMenu();
        slidingMenu.setMode(SlidingMenu.LEFT);
        // 设置触摸模式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        // 设置左侧菜单显示宽度
        slidingMenu.setBehindOffset(350);
        initFragment();
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        fm = getFragmentManager();
        // 开启事务
        FragmentTransaction ft = fm.beginTransaction();
        // 用fragment替换帧布局;参1:帧布局容器的id;参2:是要替换的fragment;参3:标记
        ft.replace(R.id.fl_left_menu,new LeftMenuFragment(),FRAG_MENU_LEFT);
        ft.replace(R.id.fl_home,new ContentFragment(),FRAG_CONTENT);
        // 提交事务
        ft.commit();
    }
    /**
     * 获取侧边栏对象
     * @return
     */
    public LeftMenuFragment getLeftMenuFragment() {
        LeftMenuFragment fragment = (LeftMenuFragment) fm
                .findFragmentByTag(FRAG_MENU_LEFT);//根据tag获取Fragment对象
        return fragment;
    }
    /**
     * 获取主页对象
     * @return
     */
    public ContentFragment getContentFragment() {
        ContentFragment fragment = (ContentFragment) fm
                .findFragmentByTag(FRAG_CONTENT);//根据tag获取Fragment对象
        return fragment;
    }

}
