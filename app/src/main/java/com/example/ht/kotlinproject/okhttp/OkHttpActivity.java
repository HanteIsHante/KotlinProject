package com.example.ht.kotlinproject.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ht.kotlinproject.R;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dns;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private TextView textView;
    String mUrl = "https://www.baidu.com/";
    OkHttpClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
//        initView();

        optionDate();
    }

    private void optionDate() {
        List<String> mList = new ArrayList<>();
        mList.add("1.22.234.223");
        mList.add("2.234.234.23");
        mList.add("3");
        mList.add("3");
        Map<String, List<String>> map = new Hashtable<String, List<String>>(){
            {
                put("a", Collections.singletonList("123"));// 初始数据
            }
        };
        map.put("wo", mList);
//        for (String key : map.keySet()) {

            List<String> stringList = map.get("wo");

            Log.d("", "optionDate: >>>>>" + mList.hashCode());
            mList.add("4");
            mList.add("5");
            for (String s : mList) {
                Log.d("", "optionDate:22222 " + stringList.hashCode());
//            stringList.add(s);
                if (!stringList.contains(s)) {
//                    stringList.add(s);
                    stringList.add(s);
                }
            }
        for (String s : map.keySet()) {
            Log.d("", "optionDate:5555||||||| " + s);
        }
//            Log.d("", "optionDate:5555 " + stringList.size() + "  ??? " + stringList);
//        }
    }


    private void initView() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.dns()
        mClient = new OkHttpClient.Builder()
//                .addInterceptor(new RetryIntercepter(3))//重试
                .retryOnConnectionFailure(true)
                .dns(new Dns() {
                    @Override
                    public List<InetAddress> lookup(String hostname) throws UnknownHostException {
                        List<InetAddress> mListIP = new ArrayList<>();
//                        byte[] bytes = ipToBytesByInet("220.181.111.188");
//                        byte[] bytes = ipToBytesByInet("220.181.112.244");
                        byte[] bytes1 = ipToBytesByInet("240.1.112.244");
                        byte[] bytes2 = ipToBytesByInet("240.81.112.244");
                        byte[] bytes3 = ipToBytesByInet("240.18.112.244");
                        byte[] bytes4 = ipToBytesByInet("240.101.112.244");
                        byte[] bytes5 = ipToBytesByInet("240.181.112.244");
//                        if (true) {
//                            byte[] bytesOne = {59, (byte) 24, (byte) 3, (byte) 173};
//                            byte[] bytesTwo = {8, (byte) 7, (byte) 198, (byte) 45};
//                            byte[] bytesThree = {46, 82, (byte) 174, (byte) 68};
//                            byte[] bytesFour = {93, 46, 8, (byte) 89};
//                            byte[] bytesSix = {(byte) 180, (byte) 149, (byte) 132, (byte) 151};
                        String s = Inet4Address.getByAddress(bytes1).toString();
                        String replace = s.replace("/", "");
                        Log.d("", "lookup: IP 列表 1" + replace);
                        mListIP.add(Inet4Address.getByAddress(bytes1));
                        mListIP.add(Inet4Address.getByAddress(bytes2));
                        mListIP.add(Inet4Address.getByAddress(bytes3));
                        mListIP.add(Inet4Address.getByAddress(bytes4));
                        mListIP.add(Inet4Address.getByAddress(bytes5));
                        String str = "";
                        for (InetAddress inetAddress : mListIP) {
                            Log.d("", "lookup: IPPPPPPPPPPPPPPPPPPPPPPP " + inetAddress);
                            str += inetAddress;
                        }
                        Log.d("", "lookup: str str str  " + str);
                        return mListIP;
//                        } else { // 返回系统解析
//                            List<InetAddress> lookup = SYSTEM.lookup(hostname);
//                            Log.d("", "lookup: inetAddress 1 " + hostname);
//                            for (InetAddress inetAddress : lookup) {
//                                Log.d("", "lookup: inetAddress 2 " + inetAddress);
//                            }
//                            return lookup;
//                        }
                    }
                })
                .build();

        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);

        button.setOnClickListener(this);
    }


    /**
     * 把IP地址转化为字节数组
     */
    public byte[] ipToBytesByInet(String ipAddr) {
        try {
            return InetAddress.getByName(ipAddr).getAddress();
        } catch (Exception e) {
            throw new IllegalArgumentException(ipAddr + " is invalid IP");
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:

                List<Inet4Address> mListIP = new ArrayList<>();
//                        byte[] bytes = ipToBytesByInet("220.181.111.188");
//                        byte[] bytes = ipToBytesByInet("220.181.112.244");
                byte[] bytes1 = ipToBytesByInet("240.1.112.244");
                byte[] bytes2 = ipToBytesByInet("240.81.112.244");
                byte[] bytes3 = ipToBytesByInet("240.18.112.244");
                byte[] bytes4 = ipToBytesByInet("240.101.112.244");
                byte[] bytes5 = ipToBytesByInet("240.181.112.244");
//                        if (true) {
//                            byte[] bytesOne = {59, (byte) 24, (byte) 3, (byte) 173};
//                            byte[] bytesTwo = {8, (byte) 7, (byte) 198, (byte) 45};
//                            byte[] bytesThree = {46, 82, (byte) 174, (byte) 68};
//                            byte[] bytesFour = {93, 46, 8, (byte) 89};
//                            byte[] bytesSix = {(byte) 180, (byte) 149, (byte) 132, (byte) 151};
                try {
                    mListIP.add((Inet4Address) Inet4Address.getByAddress(bytes1));
                    mListIP.add((Inet4Address) Inet4Address.getByAddress(bytes2));
                    mListIP.add((Inet4Address) Inet4Address.getByAddress(bytes3));
                    mListIP.add((Inet4Address) Inet4Address.getByAddress(bytes4));
                    mListIP.add((Inet4Address) Inet4Address.getByAddress(bytes5));
                    String str = "";
                    for (InetAddress inetAddress : mListIP) {
                        String s = inetAddress.toString();
                        Log.d("", "lookup: 240.1.112.244 IPPPPPPPPPPPPPPPPPPPPPPP" + s.replace("/", ""));
                        str += inetAddress;
                    }
                    Log.d("", "lookup: str str str  " + str);
                    String[] split = str.split("/");
                    List<String> listStr = new ArrayList<>();
                    listStr.addAll(Arrays.asList(split).subList(1, split.length));
                    for (String s : listStr) {
                        Log.d("", "lookup: >>>>>>>>>  " + s);
                    }
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }


//                byte[] bytes3 = ipToBytesByInet("46.82.174.68");
//                for (byte b : bytes3) {
//                    Log.d("", "onClick: bytes3 >>>>>>>  " + b);
//                }
                getHttp();
                break;
        }
    }

    public void getHttp() {


        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url(mUrl)
                        .build();
                Response response = null;
                mClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("请求失败", "onFailure: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String string = response.body().string();
                        Log.d("", "onResponse: 成功 " + string);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("", "run: " + string);
                                textView.setText("onResponse: " + string);
                            }
                        });
                    }
                });

            }
        }).start();
    }

    /**
     * 重试拦截器
     */
    public class RetryIntercepter implements Interceptor {

        public int maxRetry;//最大重试次数
        private int retryNum = 0;//假如设置为3次重试的话，则最大可能请求4次（默认1次+3次重试）

        public RetryIntercepter(int maxRetry) {
            this.maxRetry = maxRetry;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            Request build = request.newBuilder().url("Https://www.jianshu.com").build();

            while (!response.isSuccessful() && retryNum < maxRetry) {
                retryNum++;
                System.out.println("retryNum=" + retryNum);
                response = chain.proceed(build);
                Log.d("", "intercept:拦截响应 " + response.body().string());
            }
            return response;
        }
    }

}
