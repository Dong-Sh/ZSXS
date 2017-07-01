package com.huida.zsxs.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.huida.zsxs.R;
import com.huida.zsxs.bean.LoginUserIdBean;
import com.huida.zsxs.utils.MD5;
import com.huida.zsxs.utils.SpUtils;
import com.huida.zsxs.utils.StaticValue;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by lenovo on 2017/6/27.
 */

public class LoginActivity extends Activity {

    private static final String TAG = "Login";
    private TextView forget;
    private Button login;
    private LinearLayout save;
    private EditText password;
    private EditText username;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        username = (EditText) findViewById(R.id.login_et_username);
        password = (EditText) findViewById(R.id.login_et_password);
        save = (LinearLayout) findViewById(R.id.login_ll_save);
        login = (Button) findViewById(R.id.login_button);
        forget = (TextView) findViewById(R.id.login_tv_forget);
    }

    private void initData() {

    }
    RequestParams entity = new RequestParams(StaticValue.Address);
    private void initEvent() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                entity.clearParams();
                entity.addQueryStringParameter("Action","Login");
                entity.addQueryStringParameter("user",username.getText().toString().trim());
                entity.addQueryStringParameter("pwd", MD5.getMd5Value(password.getText().toString().trim()));
                Log.d(TAG, "onClick: "+username.getText().toString()+password.getText().toString());
                Log.d(TAG, "onClick: "+StaticValue.getId());
                Log.d(TAG, "onClick: "+MD5.getMd5Value(password.toString().trim()));
                entity.addQueryStringParameter("ip",StaticValue.getId());

                x.http().get(entity, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Log.d(TAG, "onSuccess: "+result);
                        Gson gson = new Gson();
                        LoginUserIdBean loginUserIdBean = gson.fromJson(result, LoginUserIdBean.class);

                        SpUtils.setString(LoginActivity.this,SpUtils.USERID,result);
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
            }
        });
    }
}
