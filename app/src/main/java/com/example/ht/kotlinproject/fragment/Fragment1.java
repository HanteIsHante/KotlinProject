package com.example.ht.kotlinproject.fragment;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.example.ht.kotlinproject.R;

/**
 * Created By HanTe
 */

public class Fragment1 extends Fragment implements View.OnClickListener {
    View view;
    Button mButton;
    private TextView  mTextView;
    double radio;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_layout, container, false);
        view = inflate.findViewById(R.id.view);
        mButton = (Button) inflate.findViewById(R.id.bt_action);
        mTextView = (TextView) inflate.findViewById(R.id.msg_num_size);
        mTextView.setText("9+");
        mButton.setOnClickListener(this);
        return inflate;
    }


    @Override
    public void onClick(View v) {
        if (v == mButton) {
            createAnimation(view).start();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Animator createAnimation(View v) {

        Animator animator;
        if (radio == 0L) {
            radio = Math.sqrt(Math.pow(view.getWidth(), 2) + Math.pow(view.getHeight(), 2));
        }
        if (isOn) {
            animator = ViewAnimationUtils.createCircularReveal(
                    v,// 操作的视图
                    0,// 动画开始的中心点X
                    0,// 动画开始的中心点Y
                    (float) radio,// 动画开始半径
                    0);// 动画结束半径
        } else {
            animator = ViewAnimationUtils.createCircularReveal(
                    v,// 操作的视图
                    0,// 动画开始的中心点X
                    0,// 动画开始的中心点Y
                    0,// 动画开始半径
                    (float) radio);// 动画结束半径
        }
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (!isOn) {
                    view.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (isOn) {
                    view.setVisibility(View.INVISIBLE);
                }
                isOn = !isOn;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setDuration(500);
        return animator;
    }
    private boolean isOn = true;//记录view的状态,第一次进去是可见的,记为true,不可见记为false

}
