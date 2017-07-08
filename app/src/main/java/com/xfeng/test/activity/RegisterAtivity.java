package com.xfeng.test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.xfeng.test.R;

/**
 * Created by a11 on 7/7/17.
 */

public class RegisterAtivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edt_username;
    private EditText edt_password;
    private Button btn_register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        edt_username = (EditText) findViewById(R.id.reg_edt_username);
        edt_password = (EditText) findViewById(R.id.reg_edt_password);
        btn_register = (Button) findViewById(R.id.reg_btn_register);

        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String username = edt_username.getText().toString();
        final String password = edt_password.getText().toString();

        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            Toast.makeText(this, "Input info contain null string", Toast.LENGTH_LONG).show();
            return;
        }

            new Thread(){
                @Override
                public void run() {
                    try {
                        EMClient.getInstance().createAccount(username, password);//同步方法
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                }
            }.start();

    }
}
