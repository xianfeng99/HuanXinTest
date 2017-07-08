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

public class ContactHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.contact_user)
    public TextView contact_user;

    public ContactHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
