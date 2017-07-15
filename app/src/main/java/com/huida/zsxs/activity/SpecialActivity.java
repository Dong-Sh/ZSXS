package com.huida.zsxs.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.huida.zsxs.R;
import com.huida.zsxs.bean.SpecialBean;
import com.huida.zsxs.bean.TopSlidesBean;
import com.huida.zsxs.utils.MyBitmapUtils;
import com.huida.zsxs.utils.StaticValue;
import com.huida.zsxs.view.BackView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by lenovo on 2017/6/26.
 */

public class SpecialActivity extends Activity {

    private static final String TAG = "Special";
    private BackView special_back;
    private ListView special_lv;
    private TopSlidesBean.SlidesBean bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sepcial);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        special_back = (BackView) findViewById(R.id.sepcial_back);
        special_lv = (ListView) findViewById(R.id.special_lv);
    }

    private void initData() {
        Intent intent = getIntent();

        String pic = intent.getStringExtra("pic");
        String title = intent.getStringExtra("title");
        String types = intent.getStringExtra("Types");
        String page = intent.getStringExtra("Page");
        String picURL = intent.getStringExtra("picURL");


        Log.d(TAG, "initData1: " + types);

        getHttpData(pic, picURL, types, page);


        special_back.setTitle(title);
    }

    private void initEvent() {
        special_back.setActivity(this);
    }

    public void getHttpData(String pic, String picURL, String types, String page) {
        if (!TextUtils.isEmpty(pic)) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT, 500));
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            x.image().bind(imageView, pic, new ImageOptions.Builder().setSize(0, 0).build());
            special_lv.addHeaderView(imageView);
        }

        RequestParams entity = new RequestParams(StaticValue.Address);

        entity.addQueryStringParameter("Action", "getZTShow");
        entity.addQueryStringParameter("ztid", picURL);


        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String str = result.replaceAll("\t", "").replaceAll("\n", "");

                SpecialBean specialBean = new Gson().fromJson(str, SpecialBean.class);

                Log.d(TAG, "SpecialonSuccess: " + specialBean);

                special_back.setTitle(specialBean.getZTtitle());
                lvAdapter = new SpecialListViewAdapter(specialBean.getCourse(), SpecialActivity.this);
                special_lv.setAdapter(lvAdapter);
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

    private SpecialListViewAdapter lvAdapter;

    class SpecialListViewAdapter extends BaseAdapter {
        private Context context;
        private List<SpecialBean.CourseBean> lists;

        public SpecialListViewAdapter(List<SpecialBean.CourseBean> lists, Context context) {
            this.context = context;
            this.lists = lists;
        }

        public void setLists(List<SpecialBean.CourseBean> lists) {
            this.lists = lists;
        }

        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Handler handler;
            convertView = View.inflate(context, R.layout.special_lv_item, null);
            handler = new Handler();
            handler.icon = (ImageView) convertView.findViewById(R.id.special_icon);
            handler.title = (TextView) convertView.findViewById(R.id.special_title);
            handler.time = (TextView) convertView.findViewById(R.id.special_time);
            handler.jifen = (TextView) convertView.findViewById(R.id.special_jifen);
            handler.hot = (TextView) convertView.findViewById(R.id.special_hot);

            handler.icon.setBackgroundResource(R.mipmap.duanwang_icon);
            convertView.setTag(handler);


            SpecialBean.CourseBean bean = lists.get(position);
            Log.d(TAG, "getView: " + bean);


            //x.image().bind(handler.icon, bean.getImg());
            MyBitmapUtils.display(bean.getImg(),handler.icon);


            handler.title.setText(bean.getTitle());
            handler.time.setText(bean.getKeshi() + "课时");
            handler.hot.setText(bean.getHot() + "");
            handler.jifen.setText(bean.getMoney() + "积分");

            return convertView;
        }

        class Handler {
            ImageView icon;
            TextView title;
            TextView time;
            TextView jifen;
            TextView hot;
        }
    }
}
