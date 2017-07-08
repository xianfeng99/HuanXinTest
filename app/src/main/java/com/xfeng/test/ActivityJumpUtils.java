package com.xfeng.test;

import android.app.Activity;
import android.content.Intent;

import com.xfeng.test.activity.ContactManagerActivity;
import com.xfeng.test.activity.ConversationActivity;
import com.xfeng.test.activity.LoginAtivity;
import com.xfeng.test.activity.RegisterAtivity;
import com.xfeng.test.activity.SendMessageActivity;

/**
 * Created by a11 on 7/7/17.
 */

public class ActivityJumpUtils {


    public static void JumpLoginActivity(Activity activity){
        Intent intent = new Intent(activity, LoginAtivity.class);
        activity.startActivity(intent);
    }

    public static void JumpRegisterActivity(Activity activity){
        Intent intent = new Intent(activity, RegisterAtivity.class);
        activity.startActivity(intent);
    }

    public static void JumpContactManagerActivity(Activity activity){
        Intent intent = new Intent(activity, ContactManagerActivity.class);
        activity.startActivity(intent);
    }
    public static void JumpSendMessageActivity(Activity activity){
        Intent intent = new Intent(activity, SendMessageActivity.class);
        activity.startActivity(intent);
    }
    public static void JumpConversationActivity(Activity activity){
        Intent intent = new Intent(activity, ConversationActivity.class);
        activity.startActivity(intent);
    }
}
