package com.example.ht.kotlinproject;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import io.intercom.android.sdk.Intercom;

/**
 * Created By HanTe
 */

public class MyApplictaion extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        Intercom.initialize(this, "android_sdk-44b03e24ca1dbce18678546e75f6b98eff348c20", "h46rtesz");
        // Application 中注册
        PushAgent mPushAgent = PushAgent.getInstance(this);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                Log.d("", "onSuccess:友盟成功注册：： " + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {

            }
        });
        PushAgent.getInstance(getApplicationContext()).onAppStart();
//  友盟后续动作，收到通知用户点击通知栏后才会执行UmengNotificationClickHandler 接收自定义的参数
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                Log.d("", "dealWithCustomAction: 自定义行为参数处理 " + msg.custom);
                for (Map.Entry<String, String> entry : msg.extra.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    Log.d("", "dealWithCustomAction: 自定义参数 " + key + " ///// " + value);
                }
            }
        };
        mPushAgent.setNotificationClickHandler(notificationClickHandler);

// 友盟自定义消息接收
        UmengMessageHandler messageHandler = new UmengMessageHandler(){
            @Override
            public void dealWithCustomMessage(final Context context, final UMessage msg) {
                new Handler(getMainLooper()).post(new Runnable() {

                    @Override
                    public void run() {
                       /* // 对于自定义消息，PushSDK默认只统计送达。若开发者需要统计点击和忽略，则需手动调用统计方法。
                        boolean isClickOrDismissed = true;
                        if(isClickOrDismissed) {
                            //自定义消息的点击统计
                            UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
                        } else {
                            //自定义消息的忽略统计
                            UTrack.getInstance(getApplicationContext()).trackMsgDismissed(msg);
                        }*/
                        String custom = msg.custom;
                        Log.d("", "友盟自定义消息接收》》》》 "  + custom);
                        try {
                            JSONObject jsonObject = new JSONObject(custom);
                            JSONArray people = jsonObject.optJSONArray("people");
                            for (int i = 0; i < people.length(); i++) {
                                JSONObject jB = people.optJSONObject(i);
                                String firstName = jB.optString("firstName");
                                String lastName = jB.optString("lastName");
                                Log.d("", "友盟自定义消息接收: 解析消息内容 " + firstName + " / " + lastName);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(context, custom, Toast.LENGTH_LONG).show();
                        for (Map.Entry<String, String> entry : msg.extra.entrySet()) {
                            String key = entry.getKey();
                            String value = entry.getValue();
                            Log.d("", "友盟自定义消息接收// 自定义参数 " + key + " //??/// "  + value );
                        }
                    }
                });
            }
        };
        mPushAgent.setMessageHandler(messageHandler);
    }
}
