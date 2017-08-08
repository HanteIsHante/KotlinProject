package com.example.ht.kotlinproject.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ht.kotlinproject.R;
import com.example.ht.kotlinproject.fragment.Fragment1;
import com.example.ht.kotlinproject.fragment.Fragment2;
import com.example.ht.kotlinproject.fragment.Fragment3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By HanTe
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mListFragment;
//    private List<String> mListTitle;
    private Context mContext;

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
        mListFragment = new ArrayList<>();
        mListFragment.add(new Fragment1());
        mListFragment.add(new Fragment2());
        mListFragment.add(new Fragment3());
//        mListTitle = new ArrayList<>();
//        mListTitle.add("Tab 1");
//        mListTitle.add("Tab 2");
//        mListTitle.add("Tab 3");

    }

    @Override
    public Fragment getItem(int position) {
        return mListFragment.get(position);
    }

    @Override
    public int getCount() {
        return mListFragment.size();
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return mListTitle.get(position);
//    }

    public View getTabView(int size) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.custom_tab_layout, null);
        ImageView tab_icon = (ImageView) inflate.findViewById(R.id.tab_icon);
        TextView tab_num = (TextView) inflate.findViewById(R.id.msg_size);
        if (size > 0) {
            tab_num.setVisibility(View.VISIBLE);
            if (size >= 10) {
                tab_num.setText("...");
            } else {
                tab_num.setText(String.valueOf(size));
            }
        } else {
            tab_num.setVisibility(View.GONE);
        }
        return inflate;
    }
}
