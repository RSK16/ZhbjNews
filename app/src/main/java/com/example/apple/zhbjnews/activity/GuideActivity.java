package com.example.apple.zhbjnews.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.apple.zhbjnews.R;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity {
    private ViewPager vp_guideactivity;
    private List<ImageView> mImgList;
    private Button bt_guideactivity_enterhome;
    private LinearLayout ll_guideactivity;
    private View view_redpoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initUI();
        initDatas();
        vp_guideactivity.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                FrameLayout.LayoutParams params =
                        (FrameLayout.LayoutParams) view_redpoint.getLayoutParams();
                params.leftMargin= (int) (40*(position+positionOffset));
                view_redpoint.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == mImgList.size() - 1) {
                    bt_guideactivity_enterhome.setVisibility(View.VISIBLE);
                } else {
                    bt_guideactivity_enterhome.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void initDatas() {
        int[] imgResIds = new int[]{R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
        mImgList = new ArrayList<ImageView>();
        for (int i =0;i<imgResIds.length;i++) {
            //加载图片
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imgResIds[i]);
            mImgList.add(imageView);
            //加载小圆点
            View point = new View(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20,20);
            if (i != 0) {
                params.leftMargin = 20;
            }
            point.setLayoutParams(params);
            point.setBackgroundResource(R.drawable.gray_point);
            ll_guideactivity.addView(point);
        }
        vp_guideactivity.setAdapter(new MyPagerviewAdapter());
    }
    private void initUI() {
        vp_guideactivity = (ViewPager) findViewById(R.id.vp_guideactivity);
        bt_guideactivity_enterhome = (Button) findViewById(R.id.bt_guideactivity_enterhome);
        ll_guideactivity = (LinearLayout) findViewById(R.id.ll_guideactivity);
        view_redpoint = findViewById(R.id.view_redpoint);
    }

    class MyPagerviewAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mImgList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = mImgList.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView)object);
        }
    }

    public void enterHome(View view) {
        startActivity(new Intent(this,HomeActivity.class));
    }
}
