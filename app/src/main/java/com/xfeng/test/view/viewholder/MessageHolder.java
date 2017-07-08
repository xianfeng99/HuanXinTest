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

public class MessageHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.msg_txt_user)
    public TextView txt_user;
    @BindView(R.id.msg_txt_message)
    public TextView txt_message;

    public MessageHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
