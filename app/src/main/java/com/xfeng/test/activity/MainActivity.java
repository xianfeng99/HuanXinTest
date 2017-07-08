package com.xfeng.test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.util.NetUtils;
import com.xfeng.test.ActivityJumpUtils;
import com.xfeng.test.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }


    @Override
    public void onContentChanged() {
        super.onContentChanged();
        //实现ConnectionListener接口
        //注册一个监听连接状态的listener
        EMClient.getInstance().addConnectionListener(new MyConnectionListener());
    }

    private class MyConnectionListener implements EMConnectionListener {
        @Override
        public void onConnected() {
        }

        @Override
        public void onDisconnected(final int error) {
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    if (error == EMError.USER_REMOVED) {
                        // 显示帐号已经被移除
                        Log.e("EMConnectionListener", "显示帐号已经被移除");
                    } else if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                        // 显示帐号在其他设备登录
                        Log.e("EMConnectionListener", "显示帐号在其他设备登录");
                    } else {
                        if (NetUtils.hasNetwork(MainActivity.this)) {
                            //连接不到聊天服务器
                            Log.e("EMConnectionListener", "连接不到聊天服务器");
                        }
                        else {
                            //当前网络不可用，请检查网络设置
                            Log.e("EMConnectionListener", "当前网络不可用，请检查网络设置");
                        }
                    }
                }
            });
        }
    }


    @OnClick({R.id.main_btn_register, R.id.main_btn_login, R.id.main_btn_contacts, R.id.main_btn_sendmsg, R.id.main_btn_conversation})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.main_btn_register:
                ActivityJumpUtils.JumpRegisterActivity(this);
                break;
            case R.id.main_btn_login:
                ActivityJumpUtils.JumpLoginActivity(this);
                break;
            case R.id.main_btn_contacts:
                ActivityJumpUtils.JumpContactManagerActivity(this);
                break;
            case R.id.main_btn_sendmsg:
                ActivityJumpUtils.JumpSendMessageActivity(this);
                break;
            case R.id.main_btn_conversation:
                ActivityJumpUtils.JumpConversationActivity(this);
                break;
        }

    }
}
