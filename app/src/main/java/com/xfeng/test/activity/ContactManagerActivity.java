package com.xfeng.test.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.xfeng.test.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by a11 on 7/7/17.
 */

public class ContactManagerActivity extends AppCompatActivity {

    private final String TAG = "ContactManagerActivity";

    @BindView(R.id.add_edt_username)
    EditText addEdtUser;
    @BindView(R.id.del_edt_username)
    EditText delEdtUser;
    @BindView(R.id.add_btn_user)
    Button addBtnUser;
    @BindView(R.id.del_btn_user)
    Button delBtnUser;
    @BindView(R.id.show_btn_userlist)
    Button showBtnUserlist;
    @BindView(R.id.contact_rcv_users)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        ButterKnife.bind(this);

    }

    @OnClick({R.id.add_btn_user, R.id.del_btn_user, R.id.show_btn_userlist})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.add_btn_user:
                addUser();
                break;
            case R.id.del_btn_user:
                deleteUser();
                break;
            case R.id.show_btn_userlist:
                showUserList();
                break;
        }
    }


    private void addUser(){
        String addUser = addEdtUser.getText().toString();
        if(!TextUtils.isEmpty(addUser)){
            //参数为要添加的好友的username和添加理由
            try {
                EMClient.getInstance().contactManager().addContact(addUser, "cece");
            } catch (HyphenateException e) {
                Log.e(TAG, "errCode:" + e.getErrorCode() + ", errDesc:" + e.getDescription());
                e.printStackTrace();
            }
        }else {
            Log.d("TAG", "addUser: user info is null");
        }
    }


    private void deleteUser(){
        String addUser = delEdtUser.getText().toString();
        if(!TextUtils.isEmpty(addUser)){
            //参数为要添加的好友的username和添加理由
            try {
                EMClient.getInstance().contactManager().deleteContact(addUser);
            } catch (HyphenateException e) {
                Log.e(TAG, "errCode:" + e.getErrorCode() + ", errDesc:" + e.getDescription());
                e.printStackTrace();
            }
        }else {
            Log.d("TAG", "addUser: user info is null");
        }
    }

    private void showUserList(){

        new Thread(){
            @Override
            public void run() {
                try {
                    List<String> usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    for(String user : usernames){
                        Log.e(TAG, "user:" + user);
                    }
                } catch (HyphenateException e) {
                    Log.e(TAG, "errCode:" + e.getErrorCode() + ", errDesc:" + e.getDescription());
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
