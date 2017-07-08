package com.xfeng.test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.xfeng.test.R;

/**
 * Created by a11 on 7/7/17.
 */

public class LoginAtivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edt_username;
    private EditText edt_password;
    private Button btn_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        edt_username = (EditText) findViewById(R.id.lgn_edt_username);
        edt_password = (EditText) findViewById(R.id.lgn_edt_password);
        btn_login = (Button) findViewById(R.id.lgn_btn_login);

        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String username = edt_username.getText().toString();
        String password = edt_password.getText().toString();

        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            Toast.makeText(this, "Input info contain null string", Toast.LENGTH_LONG).show();
            return;
        }
        EMClient.getInstance().login(username,password,new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Log.d("main", "登录聊天服务器成功！");
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Log.d("main", "登录聊天服务器失败！");
            }
        });

        //进入主页面后本地会话和群组都 load
//        EMClient.getInstance().chatManager().loadAllConversations();
//        EMClient.getInstance().groupManager().loadAllGroups();

    }
}
