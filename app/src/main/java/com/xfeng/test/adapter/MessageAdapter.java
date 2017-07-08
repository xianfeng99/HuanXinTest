package com.xfeng.test.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessageBody;
import com.hyphenate.chat.EMTextMessageBody;
import com.xfeng.test.R;
import com.xfeng.test.view.viewholder.MessageHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixianfeng on 8/7/17.
 */

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<EMMessage> mList;

    public MessageAdapter() {
        mList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_message, parent, false);
        return new MessageHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MessageHolder messageHolder = (MessageHolder) holder;

        EMMessage emmsg = mList.get(position);
        if (emmsg != null && emmsg.getBody() != null) {
            EMMessageBody emmsgBody = emmsg.getBody();
            if(emmsgBody instanceof EMTextMessageBody){
                messageHolder.txt_user.setText(emmsg.getFrom());
                messageHolder.txt_message.setText(((EMTextMessageBody)emmsgBody).getMessage());
            }
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addMessage(EMMessage msgBean) {
        mList.add(msgBean);
        notifyDataSetChanged();
    }

    public void addMessages(List<EMMessage> messages) {
        mList.addAll(messages);
        notifyDataSetChanged();
    }

}
