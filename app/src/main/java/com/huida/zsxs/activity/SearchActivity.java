package com.huida.zsxs.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.huida.zsxs.R;
import com.huida.zsxs.adapter.SpecialListViewAdapter;
import com.huida.zsxs.bean.SpecialBean;
import com.huida.zsxs.utils.SpUtils;
import com.huida.zsxs.utils.StaticValue;
import com.huida.zsxs.view.HotSearchView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by lenovo on 2017/6/21.
 */

public class SearchActivity extends Activity {

    private static final String TAG = "SearchActivity";
    private ViewPager vp;
    private EditText et;
    private RadioGroup rg;
    private Map<Integer, LinearLayout> mapLinearLayout = new TreeMap<>();
    private Map<Integer, HotSearchView> mapHotSearch = new TreeMap<>();
    private Map<Integer, GridView> mapGridView = new TreeMap<>();
    private TextView search_tv;
    private ImageView search_back;
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            et.setText(((TextView) v).getText());
            et.setSelection(et.getText().length());
        }
    };
    private OldSearchAdapter oldSearchAdapter;
    private static String OLDSEARCH = "oldsearch";
    private SpecialListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        search_tv = (TextView) findViewById(R.id.search_tv);
        vp = (ViewPager) findViewById(R.id.search_vp);
        et = (EditText) findViewById(R.id.search_et);
        rg = (RadioGroup) findViewById(R.id.search_rg);

        search_back = (ImageView) findViewById(R.id.search_back);


    }

    private void initData() {
        oldSearchAdapter = new OldSearchAdapter(SpUtils.getString(this, OLDSEARCH));
        vp.setAdapter(new SearchViewPagerAdapter());
    }

    private void initEvent() {
        search_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    manager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                manager.hideSoftInputFromWindow(et.getWindowToken(), 0);
//                Log.d(TAG, "onCheckedChanged: "+checkedId);
                vp.setCurrentItem(checkedId - R.id.search_rb_0);//TODO
            }

        });
        et.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {

                    case MotionEvent.ACTION_UP:
                        Drawable drawable = et.getCompoundDrawables()[2];

                        if (drawable != null) {
                            if (event.getX() > et.getWidth() - drawable.getIntrinsicWidth()) {
                                et.setText("");
                                et.setCompoundDrawables(null, null, null, null);
                                return true;
                            }
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_DOWN:
                        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                            manager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                        manager.showSoftInput(et, 0);

                        break;

                }
                return false;
            }
        });

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et.getText().length() > 0) {
                    Drawable drawable = getResources().getDrawable(R.mipmap.clear_edit);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    et.setCompoundDrawables(null, null, drawable, null);
                } else {
                    et.setCompoundDrawables(null, null, null, null);
                }
                Log.d(TAG, "onFocusChange: ");
            }
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected: " + vp.getChildCount());
                rg.check(R.id.search_rb_0 + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        search_tv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(TAG, "onClick: 搜索了" + vp.getCurrentItem() + et.getText().toString());


                if (SpUtils.setSearchString(SearchActivity.this, OLDSEARCH, et.getText().toString())) {
                    oldSearchAdapter.setValues(SpUtils.getString(SearchActivity.this, OLDSEARCH).substring(1).split(","));
                    oldSearchAdapter.notifyDataSetChanged();
                } else {
                    return;
                }

                getSearchData();

            }
        });
    }



    public void getSearchData() {
        RequestParams entity = new RequestParams(StaticValue.Address);

        entity.addParameter("Action", "searchCourse");
        entity.addParameter("kc_types", vp.getCurrentItem());
        entity.addParameter("keywords", et.getText().toString());
        entity.addParameter("Page", 0);
//TODO search
        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d(TAG, "onSuccess: " + result);

                SpecialBean searchBean = new Gson().fromJson(result, SpecialBean.class);

                listViewAdapter = new SpecialListViewAdapter(searchBean, SearchActivity.this);

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
    private class SearchDataPagerAdapter extends PagerAdapter{
        private SpecialBean bean;

        public SearchDataPagerAdapter(SpecialBean bean) {
            this.bean = bean;
        }



        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View inflate = View.inflate(SearchActivity.this, R.layout.special_lv_item, null);
            ImageView special_icon = (ImageView) inflate.findViewById(R.id.special_icon);
            TextView special_title = (TextView) inflate.findViewById(R.id.special_title);
            TextView search_title = (TextView) inflate.findViewById(R.id.search_title);
            TextView special_time = (TextView) inflate.findViewById(R.id.special_time);
            TextView special_jifen = (TextView) inflate.findViewById(R.id.special_jifen);
            TextView hot = (TextView) inflate.findViewById(R.id.special_hot);

            return super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);

        }
    }
    private class SearchViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if (mapLinearLayout.get(position) == null) {
                LinearLayout search = (LinearLayout) View.inflate(SearchActivity.this, R.layout.search_bottm, null);

                GridView g1 = (GridView) search.findViewById(R.id.search_bottm_gv1);

                ImageView search_iv_item_delete = (ImageView) search.findViewById(R.id.search_iv_item_delete);

                search_iv_item_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SpUtils.clearSearchString(SearchActivity.this, OLDSEARCH);
                        oldSearchAdapter.setValues(new String[]{});
                        oldSearchAdapter.notifyDataSetChanged();
                    }
                });

                g1.setAdapter(oldSearchAdapter);

                HotSearchView hotSearchView = (HotSearchView) search.findViewById(R.id.search_hotsearch);
                mapHotSearch.put(position, hotSearchView);
                mapGridView.put(position, g1);

                getHotSearch(position);

                container.addView(search);

                mapLinearLayout.put(position, search);
            } else {

            }

            return mapLinearLayout.get(position);
        }
    }


    protected void getHotSearch(final int position) {
        RequestParams params = new RequestParams(StaticValue.Address);
        params.addQueryStringParameter("Action", "getKeywords");
        params.addQueryStringParameter("kc_types", position + "");

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                String replace = result.replace("\"", "");
                String[] split = replace.substring(1, replace.length() - 1).split(",");
                Log.d(TAG, "onSuccess: " + Arrays.toString(split));

                mapHotSearch.get(position).setValues(split, listener);

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


    private class OldSearchAdapter extends BaseAdapter {
        private String[] values;
        LinearLayout.LayoutParams params;

        OldSearchAdapter(String value) {
            if (!TextUtils.isEmpty(value)) {
                values = value.substring(1).split(",");
            } else {
                values = new String[]{};
            }
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 20;
            params.setMargins(20, 0, 0, 0);
        }

        void setValues(String[] values) {
            this.values = values;
        }

        @Override
        public int getCount() {
            return values.length;
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
            TextView textView = new TextView(SearchActivity.this);
            textView.setText(values[values.length - 1 - position]);
            textView.setLayoutParams(params);
            textView.setTextSize(15);
            textView.setTextColor(Color.parseColor("#7d7f81"));
            textView.setOnClickListener(listener);
            return textView;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        oldSearchAdapter = null;
        listener = null;
        mapGridView = null;
        mapHotSearch = null;
    }
}
