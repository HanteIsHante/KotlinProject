package com.example.ht.kotlinproject;

import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * 关键：通过两个重叠的framelayout  match_parent
 */
public class TabActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {

    private FrameLayout frame_layout_one;
    private FrameLayout frame_layout_two;
    private FrameLayout frame_layout_three;
    private float mClickX;
    private float mClickY;
    private FrameLayout ToolBar_Top;
    private FrameLayout root_view;
    private int belowColor;
    private LinearLayout buttom_1;
    private LinearLayout buttom_2;
    private LinearLayout buttom_3;
    private Toolbar ToolBar_Style;
    private FrameLayout fragment_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_tab_layout);
        initView();
    }

    private void initView() {

        frame_layout_one = (FrameLayout) findViewById(R.id.frame_layout_one);
        frame_layout_two = (FrameLayout) findViewById(R.id.frame_layout_two);
        frame_layout_three = (FrameLayout) findViewById(R.id.frame_layout_three);
        frame_layout_one.setOnTouchListener(this);
        frame_layout_two.setOnTouchListener(this);
        frame_layout_three.setOnTouchListener(this);
        frame_layout_one.setOnClickListener(this);
        frame_layout_two.setOnClickListener(this);
        frame_layout_three.setOnClickListener(this);
        ToolBar_Top = (FrameLayout) findViewById(R.id.ToolBar_Top);
        ToolBar_Top.setOnClickListener(this);
        root_view = (FrameLayout) findViewById(R.id.root_view);
        root_view.setOnClickListener(this);
        buttom_1 = (LinearLayout) findViewById(R.id.buttom_1);
        buttom_1.setOnClickListener(this);
        buttom_2 = (LinearLayout) findViewById(R.id.buttom_2);
        buttom_2.setOnClickListener(this);
        buttom_3 = (LinearLayout) findViewById(R.id.buttom_3);
        buttom_3.setOnClickListener(this);
        buttom_1.setVisibility(View.VISIBLE);
        ToolBar_Style = (Toolbar) findViewById(R.id.ToolBar_Style);
        ToolBar_Style.setOnClickListener(this);

        setSupportActionBar(ToolBar_Style);
        if (getSupportActionBar() == null) return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ToolBar_Style.setTitle("大魔王");
        ToolBar_Style.setTitleTextColor(getResources().getColor(R.color.msg_center_tab));
        fragment_layout = (FrameLayout) findViewById(R.id.fragment_layout);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mClickX = event.getRawX();
        mClickY = event.getRawY();
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        if (v == frame_layout_one) {
            startAnim(1);
            buttom_1.setVisibility(View.VISIBLE);
            buttom_2.setVisibility(View.GONE);
            buttom_3.setVisibility(View.GONE);
        } else if (v == frame_layout_two) {
            startAnim(2);
            buttom_1.setVisibility(View.GONE);
            buttom_2.setVisibility(View.VISIBLE);
            buttom_3.setVisibility(View.GONE);
        } else if (v == frame_layout_three) {
            startAnim(3);
            buttom_1.setVisibility(View.GONE);
            buttom_2.setVisibility(View.GONE);
            buttom_3.setVisibility(View.VISIBLE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void startAnim(final int position) {
        final int width = ToolBar_Top.getWidth();
        final int height = ToolBar_Top.getHeight();
        final double radio = Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
        float centerX = mClickX;
        float centerY = mClickY;
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(ToolBar_Top,
                (int) centerX,
                (int) centerY, 0, (float) radio);
        circularReveal.setInterpolator(new AccelerateInterpolator());
        circularReveal.setDuration(375);
        circularReveal.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (position == 1) {
                    ToolBar_Top.setBackgroundColor(getResources().getColor(R.color.msg_num_bg));
                } else if (position == 2) {
                    ToolBar_Top.setBackgroundColor(getResources().getColor(R.color.msg_activity_bg));
                } else if (position == 3) {
                    ToolBar_Top.setBackgroundColor(getResources().getColor(R.color.msg_system_bg_color));
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (position == 1) {
                    root_view.setBackgroundColor(getResources().getColor(R.color.msg_num_bg));
                } else if (position == 2) {
                    root_view.setBackgroundColor(getResources().getColor(R.color.msg_activity_bg));
                } else if (position == 3) {
                    root_view.setBackgroundColor(getResources().getColor(R.color.msg_system_bg_color));
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        circularReveal.start();
    }
}

