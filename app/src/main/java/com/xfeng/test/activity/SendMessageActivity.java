package com.xfeng.test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.xfeng.test.R;
import com.xfeng.test.adapter.MessageAdapter;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lixianfeng on 8/7/17.
 */

public class SendMessageActivity extends AppCompatActivity{

    private final String TAG = "SendMessageActivity";

    @BindView(R.id.send_edt_username)
    EditText edt_username;
    @BindView(R.id.send_edt_message)
    EditText edt_message;
    @BindView(R.id.send_btn_message)
    Button btn_message;
    @BindView(R.id.show_rcy_messages)
    RecyclerView rcy_messages;
    MessageAdapter mMessageAdapter;
    SMEMMessageListener mMessageListener;

    static class SMEMMessageListener implements EMMessageListener{

        WeakReference<SendMessageActivity> mActivity;
        public SMEMMessageListener(SendMessageActivity activity){
            mActivity = new WeakReference<SendMessageActivity>(activity);
        }

        @Override
        public void onMessageReceived(final List<EMMessage> messages) {
            final SendMessageActivity activity = mActivity.get();
            //收到消息
            if(activity!= null){
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        activity.mMessageAdapter.addMessages(messages);
                    }
                });
            }
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            //收到透传消息
        }

        @Override
        public void onMessageRead(List<EMMessage> messages) {
            //收到已读回执
        }

        @Override
        public void onMessageDelivered(List<EMMessage> message) {
            //收到已送达回执
        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {
            //消息状态变动
            if(mActivity.get() != null){

            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendmsg);

        ButterKnife.bind(this);
        mMessageAdapter = new MessageAdapter();
        rcy_messages.setAdapter(mMessageAdapter);
        rcy_messages.setLayoutManager(new LinearLayoutManager(this));
        mMessageListener = new SMEMMessageListener(this);
        EMClient.getInstance().chatManager().addMessageListener(mMessageListener);


    }

    @OnClick(R.id.send_btn_message)
    public void onClick(View view){
        String sendUser = edt_username.getText().toString();
        String sendMsg = edt_message.getText().toString();

        if(TextUtils.isEmpty(sendUser) || TextUtils.isEmpty(sendMsg)){
            Toast.makeText(this, "Please input username or message", Toast.LENGTH_LONG).show();
            return;
        }
        //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
        EMMessage message = EMMessage.createTxtSendMessage(sendMsg, sendUser);
////如果是群聊，设置chattype，默认是单聊
//        if (chatType == CHATTYPE_GROUP)
//            message.setChatType(EMMessage.ChatType.GroupChat);
//发送消息
        EMClient.getInstance().chatManager().sendMessage(message);
    }

    @Override
    protected void onDestroy() {
        EMClient.getInstance().chatManager().removeMessageListener(mMessageListener);
        super.onDestroy();
    }
}
