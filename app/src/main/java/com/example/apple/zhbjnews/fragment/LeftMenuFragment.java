package com.example.apple.zhbjnews.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.apple.zhbjnews.R;
import com.example.apple.zhbjnews.activity.HomeActivity;
import com.example.apple.zhbjnews.bean.NewsMenu;
import com.example.apple.zhbjnews.page.NewsCenterPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;

/**
 * 侧边栏
 */
public class LeftMenuFragment extends BaseFragment {

    private ListView lv_menufragment_list;
    private ArrayList<NewsMenu.NewsMenuData> mNewsMenuData;// 侧边栏网络数据对象

    private int mCurrentPos;// 当前被选中的item的位置
    private MyLeftMenuAdapter myLeftMenuAdapter;

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_left_menu, null);
        lv_menufragment_list = (ListView) view.findViewById(R.id.lv_menufragment_list);
        //ViewUtils.inject(this, view);// 注入view和事件
        lv_menufragment_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentPos = position;
                myLeftMenuAdapter.notifyDataSetChanged();// 刷新listview
                // 收起侧边栏
                toggle();
                // 侧边栏点击之后, 要修改新闻中心的FrameLayout中的内容
                setCurrentDetailPager(position);
            }
        });
        return view;
    }

    @Override
    public void initData() {
    }

    /**
     * 打开或者关闭侧边栏
     */
    protected void toggle() {
        HomeActivity mainUI = (HomeActivity) mActivity;
        SlidingMenu slidingMenu = mainUI.getSlidingMenu();
        slidingMenu.toggle();// 如果当前状态是开, 调用后就关; 反之亦然
    }
    // 给侧边栏设置数据
    public void setNewsMenuData(ArrayList<NewsMenu.NewsMenuData> data) {
        mNewsMenuData = data;
        myLeftMenuAdapter = new MyLeftMenuAdapter();
        lv_menufragment_list.setAdapter(myLeftMenuAdapter);
    }
    /**
     * 设置当前菜单详情页
     *
     * @param position
     */
    private void setCurrentDetailPager(int position) {
        HomeActivity mainUI = (HomeActivity) mActivity;
        ContentFragment fragment = mainUI.getContentFragment();//获取主页Fragment
        NewsCenterPager newsCenterPager = fragment.getNewsCenterPager();//获取新闻中心页面
        newsCenterPager.setCurrentDetailPager(position);//设置新闻中心的详情页面

    }

    class MyLeftMenuAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mNewsMenuData.size();
        }

        @Override
        public Object getItem(int position) {
            return mNewsMenuData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View inflate = View.inflate(getActivity(), R.layout.item_menulist, null);
            TextView tv_menulist_title = (TextView) inflate.findViewById(R.id.tv_menulist_title);
            NewsMenu.NewsMenuData item = (NewsMenu.NewsMenuData) getItem(position);
            tv_menulist_title.setText(item.title);
            if (mCurrentPos != position) {
                // 未被选中
                tv_menulist_title.setEnabled(false);//文字变为黑色
            } else {
                // 被选中
                tv_menulist_title.setEnabled(true);//文字变为红色
            }
            return inflate;
        }
    }
}

