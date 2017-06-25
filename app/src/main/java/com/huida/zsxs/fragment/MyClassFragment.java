package com.huida.zsxs.fragment;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.huida.zsxs.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/6/8.
 */

public class MyClassFragment extends BaseFragment implements View.OnClickListener {

    private TextView tv_username;
    private ImageButton ib_hasbuy;
    private ImageButton ib_recent;
    private ImageButton ib_offline;
    private ViewPager vp_myclass;
    private List<TextView> menuList;
    private View view_line;
    private  int tabsize=4;
    private int screenwidth;
    private LinearLayout.LayoutParams params;
    private  int  CurrentPosition=0;

    public MyClassFragment(Activity mActivity) {
        super(mActivity);
    }
    protected int getLayouId() {

        return R.layout.fragment_mycass;
    }

    public View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        // View  view=View.inflate(mActivity, R.layout.myclass_layout,null);
        View view = inflater.inflate(R.layout.fragment_mycass, container, false);
        tv_username = (TextView) view.findViewById(R.id.tv_username);
        ib_hasbuy = (ImageButton) view.findViewById(R.id.ib_hasbuy);
        ib_recent = (ImageButton) view.findViewById(R.id.ib_recent);
        ib_offline = (ImageButton) view.findViewById(R.id.ib_offline);
        vp_myclass = (ViewPager) view.findViewById(R.id.vp_myclass);
        TextView tv_audio =  (TextView) view.findViewById(R.id.tv_audio);
        TextView tv_video = (TextView) view.findViewById(R.id.tv_video);
        TextView tv_readbook = (TextView) view.findViewById(R.id.tv_readbook);
        view_line = view.findViewById(R.id.view_line);
        initData();
        initListener();
        return view;
    }

    /**
     * 设置监听
     */

    public void initListener() {
        ib_hasbuy.setOnClickListener(this);
        ib_recent.setOnClickListener(this);
        ib_offline.setOnClickListener(this);
        vp_myclass.setOnPageChangeListener(new MyClassPagerChangeListener());
        vp_myclass.setAdapter(new MyClassPageAdater());
    }
    /**
     * 初始化数据
     */
    public void initData() {
        ib_hasbuy.setSelected(true);
        ib_recent.setSelected(false);
        ib_offline.setSelected(false);
        menuList = new ArrayList<>();
        TextView  tv;
        for (int i = 0; i < 10; i++) {
            tv= new  TextView(mActivity);
            tv.setText("第"+i+"个");
            tv.setWidth(20);
            tv.setHeight(20);
            menuList.add(tv);
        }

        screenwidth = mActivity.getWindowManager().getDefaultDisplay().getWidth();
        params = (LinearLayout.LayoutParams) view_line.getLayoutParams();
        params.leftMargin=screenwidth/4;
        view_line.setLayoutParams(params);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.ib_hasbuy:
                ib_hasbuy.setSelected(true);
                ib_recent.setSelected(false);
                ib_offline.setSelected(false);
                tabsize=3;
                Toast.makeText(mActivity, "1", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.ib_recent:
                ib_recent.setSelected(true);
                ib_hasbuy.setSelected(false);
                ib_offline.setSelected(false);
                tabsize=4;
                Toast.makeText(mActivity, "2", Toast.LENGTH_SHORT).show();

                break;

            case  R.id.ib_offline:
                ib_offline.setSelected(true);
                ib_hasbuy.setSelected(false);
                ib_recent.setSelected(false);
                tabsize=3;
                Toast.makeText(mActivity, "3", Toast.LENGTH_SHORT).show();

                break;

        }
    }



    private class MyClassPagerChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //指示器移动的距离=一个指示器的宽度*页面移动的比例+position*一个指示器的宽度
            //移动指示器
            int offsetX = (int) (screenwidth/ 4*positionOffset+(position*screenwidth/4));
            params.leftMargin=offsetX;
            view_line.setLayoutParams(params);
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
        class  MyClassPageAdater extends PagerAdapter{

            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = View.inflate(mActivity, R.layout.list_item_layout,null);
               // TextView s = menuList.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        }



}
