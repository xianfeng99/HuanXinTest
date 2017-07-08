package com.xfeng.test.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.xfeng.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lixianfeng on 8/7/17.
 */

public class ConversationHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.conversation_username)
    public TextView txt_user;
    @BindView(R.id.conversation_last_msg)
    public TextView txt_message;
    @BindView(R.id.conversation_msgcount)
    public TextView txt_msgcount;

    public ConversationHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
