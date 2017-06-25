package com.huida.zsxs.fragment;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.huida.zsxs.R;
import com.huida.zsxs.bean.Course100Bean;
import com.huida.zsxs.bean.HomeListViewBean;
import com.huida.zsxs.bean.SlidesBean;
import com.huida.zsxs.utils.StaticValue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.LinkedList;
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
        initData();

    }

    private List<SlidesBean> slidesBeanList = new LinkedList<>();

    private void initData() {

        mainTopViewPagerAdapter = new MainTopViewPagerAdapter();
        home_top_vp.setAdapter(mainTopViewPagerAdapter);
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
                home_top_rg.check(home_top_rg.getChildAt(position % slidesBeanList.size()).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void getHttpData() {
        RequestParams entity;
        final Gson gson = new Gson();
        if (slidesBeanList.size() != 0) {
            handler.sendEmptyMessage(1);
            mainTopViewPagerAdapter.notifyDataSetChanged();
            handler.sendEmptyMessageDelayed(0, 2000);
        } else {
            entity = new RequestParams(StaticValue.Address);
            entity.addQueryStringParameter("Action", "GetSlides");//首页幻灯片信息
            x.http().get(entity, new Callback.CommonCallback<String>() {
                public void onSuccess(String result) {

                    if(slidesBeanList.size()==0)
                        return;

                    try {
                        slidesBeanList.clear();
                        JSONObject object = new JSONObject(result);
                        JSONArray array = new JSONArray(object.get("Slides").toString());

                        for (int i = 0; i < array.length(); i++) {
                            slidesBeanList.add(gson.fromJson(array.getString(i), SlidesBean.class));
                        }
                        handler.sendEmptyMessageDelayed(0, 2000);
                        handler.sendEmptyMessage(1);
                        mainTopViewPagerAdapter.notifyDataSetChanged();
                        home_top_vp.setCurrentItem(slidesBeanList.size() * 50);
                        Log.d(TAG, "onSuccess: " + slidesBeanList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                public void onError(Throwable ex, boolean isOnCallback) {
                    Log.d(TAG, "onError: " + ex);
                }

                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        }
        final ImageOptions imageoptions = new ImageOptions.Builder()
                .build();

        for (int i = 0; i < home_4_ll.getChildCount(); i++) {//四小图标
            LinearLayout ll = (LinearLayout) home_4_ll.getChildAt(i);

            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LinearLayout ll = (LinearLayout) v;
                    TextView childAt = (TextView) ll.getChildAt(1);
                    Log.d(TAG, "onClick: " + childAt.getText());
                    //TODO 四个小图标跳转
                }
            });

            final ImageView childAt = (ImageView) ll.getChildAt(0);


            x.image().loadDrawable("http://www.chinaplat.com/images/app0" + (i + 1) + ".png", imageoptions, new Callback.CommonCallback<Drawable>() {
                @Override
                public void onSuccess(Drawable result) {
                    Log.d(TAG, "onSuccess: " + result);
                    childAt.setBackground(result);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    Log.d(TAG, "onSuccess: loadDrawable" + ex);
                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }
            });
        }
        entity = new RequestParams(StaticValue.Address);//获取百分课程
        entity.addQueryStringParameter("Action","GetCourse100");

        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d(TAG, "onSuccess: course100Bean"+result);

                Course100Bean course100Bean = gson.fromJson(result, Course100Bean.class);

                for(int i =0;i<course100Bean.getCourse().size();i++){

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


                Log.d(TAG, "onSuccess: course100Bean"+course100Bean);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.d(TAG, "onError: "+ex);
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

        });

        entity = new RequestParams(StaticValue.Address);
        entity.addQueryStringParameter("Action","GetTJCourseList");
        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {

                homeListViewBean = gson.fromJson(result, HomeListViewBean.class);

                Log.d(TAG, "onSuccess: "+homeListViewBean);
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
    private HomeListViewBean homeListViewBean;
    private class MainTopViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 10000;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {


            if (slidesBeanList.size() != 0) {
                position %= slidesBeanList.size();
                ImageView im = new ImageView(mActivity);
                im.setBackgroundResource(R.mipmap.guodu_icon);

                im.setLayoutParams(params);

                if (position < 0) {
                    position = slidesBeanList.size() + position;
                }

                SlidesBean slidesBean = slidesBeanList.get(position);

                x.image().bind(im, slidesBean.getPic());


                container.addView(im);
                return im;
            }


            return null;
        }

        ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    home_top_vp.setCurrentItem((home_top_vp.getCurrentItem() + 1));

                    handler.sendEmptyMessageDelayed(0, 2000);
                    break;
                case 1:
                    RadioButton rb;
                    RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(20, 20);
                    params.rightMargin = (int) (5 * getResources().getDisplayMetrics().density);
                    for (int i = 0; i < slidesBeanList.size(); i++) {
                        rb = new RadioButton(mActivity);
                        rb.setButtonDrawable(null);
                        rb.setLayoutParams(params);
                        rb.setBackgroundResource(R.drawable.home_top_rb_seleter);
                        home_top_rg.addView(rb);
                    }

                    home_top_rg.check(home_top_rg.getChildAt(home_top_vp.getCurrentItem() % slidesBeanList.size()).getId());
                    break;
            }
        }
    };
    
}
