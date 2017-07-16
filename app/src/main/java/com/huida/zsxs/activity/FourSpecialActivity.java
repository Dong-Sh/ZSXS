package com.huida.zsxs.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.huida.zsxs.R;
import com.huida.zsxs.bean.ZTBean;
import com.huida.zsxs.utils.StaticValue;
import com.huida.zsxs.view.BackView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


/**
 * Created by lenovo on 2017/7/2.
 */

public class FourSpecialActivity extends Activity {

    private ListView lv;
    private BackView back;
    private String TAG ="Foru";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourspecial);
        lv = (ListView) findViewById(R.id.four_lv);
        back = (BackView) findViewById(R.id.four_back);
        getHttpData();

    }

    public void getHttpData() {


        Intent intent = getIntent();

        back.setTitle(intent.getStringExtra("title"));
        back.setActivity(this);

        RequestParams entity = new RequestParams(StaticValue.Address);
        entity.addParameter("Action","GetZT");
        entity.addParameter("types",intent.getStringExtra("types"));

        x.http().get(entity, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.d(TAG, "FouronSuccess: "+result);
                lv.setAdapter(new FourLVAdapter(new Gson().fromJson(result,ZTBean.class)));
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

    class FourLVAdapter extends BaseAdapter{
        ZTBean ztBean;
        public FourLVAdapter(ZTBean ztBean){
            this.ztBean=ztBean;
        }
        @Override
        public int getCount() {
            return ztBean.getList().size();
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
            if(convertView==null){
                ImageView imageView = new ImageView(FourSpecialActivity.this);

                imageView.setPadding(30,130,30,130);

                convertView = imageView;
            }
            final ZTBean.ListBean bean = ztBean.getList().get(position);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(FourSpecialActivity.this,SpecialActivity.class);
                    intent.putExtra("pic",bean.getImgURL());
                    intent.putExtra("picURL",bean.getZtid()+"");
                    startActivity(intent);

                }
            });

            x.image().bind((ImageView) convertView,bean.getImgURL());

            return convertView;
        }
    }
}
