package com.example.ht.kotlinproject.notify;

import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mobstat.StatService;
import com.example.ht.kotlinproject.R;

import io.intercom.android.sdk.Intercom;
import io.intercom.android.sdk.identity.Registration;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView2;
    private String TEXT_STRING = "我是好人,我怕谁";
    private String TEXT_KEY = "好人";
    private Button button2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        StatService.start(this);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.conversation_fragment, new Fragment());
        ft.commitAllowingStateLoss();
        ft.commit();


        Registration registration = Registration.create().withUserId("123456");
        Intercom.client().registerIdentifiedUser(registration);

        initView();
    }

    private void initView() {
        int startIndex = TEXT_STRING.indexOf(TEXT_KEY);
        int endIndex = TEXT_KEY.length() + startIndex;

        SpannableStringBuilder builder =
                new SpannableStringBuilder(TEXT_STRING);
        builder.setSpan(new Clickable(this), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        textView2.setText(builder);


        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intercom.client().displayMessenger();
            }
        });
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "点击谁呢", Toast.LENGTH_SHORT).show();
    }

    private class Clickable extends ClickableSpan implements View.OnClickListener {
        private View.OnClickListener mOnclickListener;

        public Clickable(View.OnClickListener onclickListener) {
            this.mOnclickListener = onclickListener;
        }

        @Override

        public void onClick(View widget) {
            mOnclickListener.onClick(widget);
        }
    }
    private void snackBar(){
        Snackbar.make(button2, "", Snackbar.LENGTH_INDEFINITE)
                .addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        super.onDismissed(transientBottomBar, event);
                    }

                    @Override
                    public void onShown(Snackbar transientBottomBar) {
                        super.onShown(transientBottomBar);
                    }
                })
                .setAction("", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setText("")
                .setDuration(1000000)
                .removeCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        super.onDismissed(transientBottomBar, event);
                    }

                    @Override
                    public void onShown(Snackbar transientBottomBar) {
                        super.onShown(transientBottomBar);
                    }
                })
                .show();



    }
}
