package com.xfeng.test.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessageBody;
import com.hyphenate.chat.EMTextMessageBody;
import com.xfeng.test.R;
import com.xfeng.test.bean.ConversationBean;
import com.xfeng.test.view.viewholder.ConversationHolder;
import com.xfeng.test.view.viewholder.MessageHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lixianfeng on 8/7/17.
 */

public class ConversationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ConversationBean> mList;

    public ConversationAdapter() {
        mList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_conversation, parent, false);
        return new ConversationHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ConversationHolder conversationHolder = (ConversationHolder) holder;
        ConversationBean conversationbean = mList.get(position);
        conversationHolder.txt_user.setText(conversationbean.userName);

        if(conversationbean.conversation.getLastMessage() != null){
            EMMessageBody emmsgBody = conversationbean.conversation.getLastMessage().getBody();
            String msg = ((EMTextMessageBody)emmsgBody).getMessage();
            conversationHolder.txt_message.setText(TextUtils.isEmpty(msg) ? "no new message" : msg);
            conversationHolder.txt_msgcount.setText(String.valueOf(conversationbean.msgCount));
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void refreshConversation(List<ConversationBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }


}
