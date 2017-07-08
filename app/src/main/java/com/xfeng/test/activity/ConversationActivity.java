package com.xfeng.test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.xfeng.test.R;
import com.xfeng.test.adapter.ConversationAdapter;
import com.xfeng.test.bean.ConversationBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lixianfeng on 8/7/17.
 */

public class ConversationActivity extends AppCompatActivity {

    @BindView(R.id.conversation_btn_refresh)
    Button mRefresh;
    @BindView(R.id.conversation_rcy_users)
    RecyclerView mRecyclerView;
    ConversationAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        ButterKnife.bind(this);

        mAdapter = new ConversationAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @OnClick(R.id.conversation_btn_refresh)
    public void onClick(View view){
        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();

        List<ConversationBean> list = new ArrayList<>();
        for (Map.Entry<String, EMConversation> entry : conversations.entrySet()) {
            if (entry != null) {
                ConversationBean bean = new ConversationBean();
                bean.conversation = entry.getValue();
                bean.userName = entry.getKey();
                bean.msgCount = entry.getValue().getUnreadMsgCount();
                list.add(bean);
            }
        }
        mAdapter.refreshConversation(list);
    }

}
