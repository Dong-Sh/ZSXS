package com.huida.zsxs.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.zxing.activity.CaptureActivity;
import com.huida.zsxs.R;
import com.huida.zsxs.fragment.BaseFragment;
import com.huida.zsxs.fragment.MainFragment;
import com.huida.zsxs.fragment.MyCenterFragment;
import com.huida.zsxs.fragment.MyClassFragment;
import com.huida.zsxs.fragment.SeleterCourseFragment;
import com.huida.zsxs.view.TitleView;

import java.util.ArrayList;
import java.util.List;
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class MainActivity extends Activity {
    private RadioGroup center;
    private List<BaseFragment> fragments;
    private TitleView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        center = (RadioGroup) findViewById(R.id.main_rg_center);
        title = (TitleView) findViewById(R.id.main_tv_title);
    }


    private void initData() {
        fragments = new ArrayList<>();

        fragments.add(new MainFragment(this));
        fragments.add(new SeleterCourseFragment(this));
        fragments.add(new MyClassFragment(this));
        fragments.add(new MyCenterFragment(this));

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        for (int i = 0; i < 4; i++) {
            transaction.add(R.id.main_fragment,fragments.get(i), i + "");
            transaction.hide(fragments.get(i));
        }

        transaction.show(fragments.get(0));

        transaction.commit();
    }

    private void initEvent() {
        center.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                Log.d("Dongsh", "onCheckedChanged: " + checkedId);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                switch (checkedId) {
                    case R.id.main_rb_home:
                        break;
                    case R.id.main_rb_course:
                        title.setTitle("视频中心");
                        break;
                    case R.id.main_rb_myclass:
                        title.setTitle("我的课");
                        break;
                    case R.id.main_rb_center:
                        title.setTitle("中仕个人中心");
                        break;
                }

                if (checkedId - 2131427425 == 0) {
                    title.setVisible(View.VISIBLE, View.INVISIBLE, View.VISIBLE, View.VISIBLE, View.VISIBLE);
                } else {
                    title.setVisible(View.INVISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                }

//                transaction.replace(R.id.main_fragment, );

                for (int i = 0; i < 4; i++) {
                    transaction.hide(fragments.get(i));
                }
                transaction.show(fragments.get(checkedId - 2131427425));

                transaction.commit();
            }

        });
        title.setRight2Listener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent, -1);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { //RESULT_OK = -1
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            Toast.makeText(MainActivity.this, scanResult, Toast.LENGTH_LONG).show();
        }
    }

}
