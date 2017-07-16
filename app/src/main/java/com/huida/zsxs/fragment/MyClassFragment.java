package com.huida.zsxs.fragment;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.huida.zsxs.R;
import com.huida.zsxs.adapter.RecycleListAdapter;

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

    private int screenwidth;
    private LinearLayout.LayoutParams params;
    private  int  CurrentPosition=0;
    private View vpview;
    private ListView myclass_lv;
    private TextView tv_audio;
    private TextView tv_video;
    private TextView tv_readbook;
    private TextView tv_article;
    private  ArrayList<TextView> tablist = new ArrayList<>();
    private RecycleListAdapter listAdapter;
    private ListView lv;
    private ArrayList<String> data;
    private ArrayList<ListViewBean> listViewBeen;

    public MyClassFragment(Activity mActivity) {
        super(mActivity);
    }
    protected int getLayouId() {
        return R.layout.fragment_mycass;
    }

    @Override
    protected void initView() {
        tv_username = (TextView) view.findViewById(R.id.tv_username);
        ib_hasbuy = (ImageButton) view.findViewById(R.id.ib_hasbuy);
        ib_recent = (ImageButton) view.findViewById(R.id.ib_recent);
        ib_offline = (ImageButton) view.findViewById(R.id.ib_offline);
        vp_myclass = (ViewPager) view.findViewById(R.id.vp_myclass);
        tv_audio = (TextView) view.findViewById(R.id.tv_audio);
        tv_video = (TextView) view.findViewById(R.id.tv_video);
        tv_readbook = (TextView) view.findViewById(R.id.tv_readbook);
        tv_article = (TextView) view.findViewById(R.id.tv_article);
        view_line = view.findViewById(R.id.view_line);

        initData();
        initListener();
    }

    /**
     * 设置监听
     */

    public void initListener() {
        ib_hasbuy.setOnClickListener(this);
        ib_recent.setOnClickListener(this);
        ib_offline.setOnClickListener(this);
        vp_myclass.addOnPageChangeListener(new MyClassPagerChangeListener());

    }
    /**
     * 初始化数据
     */
    public void initData() {
        Log.e("ling............","initdata");
        //设置标题的个数
        tablist.add(tv_audio);
        tablist.add(tv_video);
        tablist.add(tv_readbook);
        tv_article.setVisibility(View.GONE);

        data = new ArrayList<>();
        data.add("1");
        data.add("2");
        data.add("3");
        data.add("4");
        data.add("5");
        data.add("6");


        listViewBeen = new ArrayList<>();
        for (int i = 0; i < tablist.size(); i++) {
            ListViewBean bean =  new ListViewBean();
            bean.view = View.inflate(mActivity, R.layout.vp_item_layout,null);
            bean.lv = (ListView) bean.view.findViewById(R.id.myclass_lv);
            listAdapter = new RecycleListAdapter(mActivity, data);
            bean.lv.setAdapter(listAdapter);
            listViewBeen.add(bean);
           Log.e("ling..............", bean+"");
        }
        Log.e("ling..............", listViewBeen.size()+"");

        ib_hasbuy.setSelected(true);
        ib_recent.setSelected(false);
        ib_offline.setSelected(false);
//        menuList = new ArrayList<>();



        screenwidth = mActivity.getWindowManager().getDefaultDisplay().getWidth();
        params = (LinearLayout.LayoutParams) view_line.getLayoutParams();
        params.width =screenwidth/4;
        view_line.setLayoutParams(params);




        Log.e("ling","---------------tablist"+tablist.size());
        vp_myclass.setAdapter(new MyClassPageAdater());
        vp_myclass.setCurrentItem(0);



    }
    @Override
    public void onClick(View view) {
        Log.d("Dongsh", "onClick: ");
        switch (view.getId()){
            case  R.id.ib_hasbuy:
                //设置点击样式
                ib_hasbuy.setSelected(true);
                ib_recent.setSelected(false);
                ib_offline.setSelected(false);
                //设置标题的个数

                listAdapter = new RecycleListAdapter(mActivity, data);
                if (tablist.size()==4){
                    listViewBeen.remove(listViewBeen.size());
                }

                break;
            case  R.id.ib_recent:
                ib_recent.setSelected(true);
                ib_hasbuy.setSelected(false);
                ib_offline.setSelected(false);
                //显示文章标题
                tv_article.setVisibility(View.VISIBLE);
                //把viewpage条目数加上文章条目
                tablist.add(tv_article);
                //添加文章的listview
                ListViewBean bean =  new ListViewBean();
                bean.view = View.inflate(mActivity, R.layout.vp_item_layout,null);
                bean.lv = (ListView) bean.view.findViewById(R.id.myclass_lv);
                listAdapter = new RecycleListAdapter(mActivity, data);
                bean.lv.setAdapter(listAdapter);
                listViewBeen.add(bean);
                //重新设置适配器
                vp_myclass.setAdapter(new MyClassPageAdater());
                vp_myclass.setCurrentItem(0);


                break;

            case  R.id.ib_offline:
                ib_offline.setSelected(true);
                ib_hasbuy.setSelected(false);
                ib_recent.setSelected(false);

                tv_article.setVisibility(View.GONE);
                tablist.remove(tv_article);
                if (tablist.size()==4){
                    listViewBeen.remove(listViewBeen.size());
                }

                break;

        }
    }



    private class MyClassPagerChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //vp_myclass.setCurrentItem(position);
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
                return tablist.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                Log.e("ling","--------------------position");
                Log.e("ling",".................."+listViewBeen.get(position));
                container.addView(listViewBeen.get(position).view);
                return listViewBeen.get(position).view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        }

        class ListViewBean{
            View view;
            ListView lv;
        }

}
