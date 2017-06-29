package com.huida.zsxs.fragment;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.huida.zsxs.R;
import com.huida.zsxs.adapter.HomeGridViewAdapter;
import com.huida.zsxs.adapter.MainTopViewPagerAdapter;
import com.huida.zsxs.bean.Course100Bean;
import com.huida.zsxs.bean.HomeListViewBean;
import com.huida.zsxs.bean.TopSlidesBean;
import com.huida.zsxs.utils.StaticValue;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by lenovo on 2017/6/8.
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class MainFragment extends BaseFragment {

    private static final String TAG = "Dongsh";
    private ViewPager home_top_vp;
    private MainTopViewPagerAdapter mainTopViewPagerAdapter;
    private RadioGroup home_top_rg;
    private LinearLayout home_4_ll;
    private LinearLayout home_100_ll;
    private GridView home_gv;
    private List<TopSlidesBean.SlidesBean> slidesBeanList;
    private ScrollView scrollView;


    public MainFragment(Activity mActivity) {
        super(mActivity);
    }

    protected int getLayouId() {
        return R.layout.fragment_home;
    }

    protected void initView() {
        handler.removeMessages(0);
        super.initView();
        home_top_vp = (ViewPager) view.findViewById(R.id.home_top_vp);
        home_top_rg = (RadioGroup) view.findViewById(R.id.home_top_ll);
        home_4_ll = (LinearLayout) view.findViewById(R.id.home_4_ll);
        home_100_ll = (LinearLayout) view.findViewById(R.id.home_100_ll);
        home_gv = (GridView) view.findViewById(R.id.home_gv);
        home_gv.setFocusable(false);
        scrollView = (ScrollView) view.findViewById(R.id.fragment_home_scrollview);
        initData();

    }


    private void initData() {

        getHttpData();
        initEvent();
    }

    private void initEvent() {
        home_top_vp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        handler.removeMessages(0);
                        break;
                    case MotionEvent.ACTION_UP:
                        handler.removeMessages(0);
                        handler.sendEmptyMessageDelayed(0, 2000);
                        break;
                    case MotionEvent.ACTION_MOVE:


                        break;
                }
                return false;
            }
        });
        home_top_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                home_top_rg.check(home_top_rg.getChildAt(position % (slidesBeanList.size() == 0 ? position + 1 : slidesBeanList.size())).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void getHttpData() {
        RequestParams entity;
        final Gson gson = new Gson();
        final ImageOptions imageoptions = new ImageOptions.Builder()
                .build();

        {//首页幻灯片信息
            entity = new RequestParams(StaticValue.Address);
            entity.addQueryStringParameter("Action", "GetSlides");
            x.http().get(entity, new Callback.CommonCallback<String>() {
                public void onSuccess(String result) {

                    if(slidesBeanList!=null && slidesBeanList.size()!=0){
                        return;
                    }
                    slidesBeanList = gson.fromJson(result, TopSlidesBean.class).getSlides();
                    Log.d(TAG, "onSuccess: " + slidesBeanList.size());
                    handler.removeMessages(0);
                    handler.sendEmptyMessageDelayed(0, 2000);
                    handler.sendEmptyMessage(-1);
                    handler.sendEmptyMessage(1);

                    mainTopViewPagerAdapter.notifyDataSetChanged();
                    Log.d(TAG, "onSuccess: " + slidesBeanList);

                }

                public void onError(Throwable ex, boolean isOnCallback) {
                    Log.d(TAG, "onError: 首页幻灯片信息" + ex);
                }

                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        }

        //四小图标

        {
            for (int i = 0; i < home_4_ll.getChildCount(); i++) {
                LinearLayout ll = (LinearLayout) home_4_ll.getChildAt(i);
                ll.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        LinearLayout ll = (LinearLayout) v;
                        TextView childAt = (TextView) ll.getChildAt(1);
                        //TODO 四个小图标跳转
                    }
                });
                final ImageView childAt = (ImageView) ll.getChildAt(0);
                x.image().bind(childAt, "http://www.chinaplat.com/images/app0" + (i + 1) + ".png");

            }
        }

        {//获取百分课程
            entity = new RequestParams(StaticValue.Address);
            entity.addQueryStringParameter("Action", "GetCourse100");

            x.http().get(entity, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {

                    Course100Bean course100Bean = gson.fromJson(result, Course100Bean.class);

                    for (int i = 0; i < course100Bean.getCourse().size(); i++) {

                        Course100Bean.CourseBean courseBean = course100Bean.getCourse().get(i);

                        LinearLayout ll = (LinearLayout) home_100_ll.getChildAt(i);

                        final ImageView icon = (ImageView) ll.getChildAt(0);
                        final TextView name = (TextView) ll.getChildAt(1);
                        name.setText(courseBean.getTitle());


                        x.image().loadDrawable(courseBean.getImg(), imageoptions, new CommonCallback<Drawable>() {

                            @Override
                            public void onSuccess(Drawable result) {
                                icon.setBackground(result);
//                            icon.setImageDrawable(result);

                            }

                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {

                            }

                            @Override
                            public void onCancelled(CancelledException cex) {

                            }

                            @Override
                            public void onFinished() {

                            }
                        });


                    }
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Log.d(TAG, "onError: " + ex);
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }

            });
        }

        {//获取推荐课程
            entity = new RequestParams(StaticValue.Address);
            entity.addQueryStringParameter("Action", "GetTJCourseList");
            x.http().get(entity, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    homeListViewBean = gson.fromJson(result, HomeListViewBean.class);
                    handler.sendEmptyMessage(2);
                    Log.d(TAG, "onSuccess: " + homeListViewBean);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Log.d(TAG, "onCancelled: " + ex);
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        }
    }

    private HomeListViewBean homeListViewBean;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0://开启轮播图循环播放
                    home_top_vp.setCurrentItem((home_top_vp.getCurrentItem() + 1));
                    handler.removeMessages(0);
                    handler.sendEmptyMessageDelayed(0, 2000);
                    break;
                case 1://动态添加轮播图底部点
                    RadioButton rb;
                    RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(15, 15);
                    params.rightMargin = (int) (5 * getResources().getDisplayMetrics().density);

                    for (int i = 0; i < slidesBeanList.size(); i++) {
                        rb = new RadioButton(mActivity);
                        rb.setButtonDrawable(null);
                        rb.setLayoutParams(params);
                        rb.setBackgroundResource(R.drawable.home_top_rb_seleter);
                        home_top_rg.addView(rb);
                    }
                    home_top_rg.check(home_top_rg.getChildAt(home_top_vp.getCurrentItem() % slidesBeanList.size()).getId());

                    home_top_vp.setCurrentItem(slidesBeanList.size() * 50);//设置ViewPager位置
                    break;
                case 2:
                    home_gv.setAdapter(new HomeGridViewAdapter(homeListViewBean, mActivity));
                    break;
                case -1:
                    mainTopViewPagerAdapter = new MainTopViewPagerAdapter(slidesBeanList, mActivity);
                    home_top_vp.setAdapter(mainTopViewPagerAdapter);
                    break;
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        handler.removeMessages(0);
        handler.sendEmptyMessage(0);
    }
}
