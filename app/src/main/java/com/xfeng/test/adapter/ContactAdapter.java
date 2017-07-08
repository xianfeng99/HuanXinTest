package com.xfeng.test.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessageBody;
import com.hyphenate.chat.EMTextMessageBody;
import com.xfeng.test.R;
import com.xfeng.test.bean.UserMessageBean;
import com.xfeng.test.view.viewholder.ContactHolder;
import com.xfeng.test.view.viewholder.MessageHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixianfeng on 8/7/17.
 */

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> mList;

    public ContactAdapter() {
        mList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_message, parent, false);
        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ContactHolder contactHolder = (ContactHolder) holder;
        String userName = mList.get(position);
        contactHolder.contact_user.setText(userName);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}
