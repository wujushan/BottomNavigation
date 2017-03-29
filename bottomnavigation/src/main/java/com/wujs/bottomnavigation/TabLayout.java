package com.wujs.bottomnavigation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by wujs on 2017/2/10-11:06.
 * Email:wujs@paxsz.com
 */

public class TabLayout extends LinearLayout implements View.OnClickListener {
    private List<TabItem> tabs;
    private View selectedView;
    private int tabCount;
    private ColorStateList colorStateList;
    private ViewPager mViewPager;

    public TabLayout(Context context) {
        this(context, null);
    }

    public TabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        setOrientation(HORIZONTAL);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TabLayout);
        for (int i = 0; i < a.getIndexCount(); i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.TabLayout_textColorSelector) {
                colorStateList = a.getColorStateList(attr);
            }
        }
        a.recycle();
    }

    @Override
    public void onClick(View v) {
        tabClickListener.onTabClick((TabItem) v.getTag());
    }

    public void intData(List<TabItem> tabItems, OnTabClickListener listener) {
        this.tabClickListener = listener;
        this.tabs = tabItems;
        LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        params.setMargins(12,9,12,10);
        TabItemView itemView = null;
        if (tabs != null && tabs.size() > 0) {
            tabCount = tabs.size();
            for (int index = 0; index < tabs.size(); index++) {
                itemView = new TabItemView(getContext());
                itemView.setDefaultTextColor(colorStateList);
                itemView.setTag(tabs.get(index));
                itemView.initData(tabs.get(index));
                itemView.setOnClickListener(this);
                addView(itemView, params);
            }

        } else {
            throw new IllegalArgumentException("tabs can not be empty");
        }

    }

    public void setCurrentTab(int position) {
        if (position >= 0 && position < tabCount) {
            View view = getChildAt(position);
            if (selectedView != view) {
                view.setSelected(true);
                if (selectedView != null) {
                    selectedView.setSelected(false);
                }
                selectedView = view;
            }
        }
    }
    public void setTextColorSelected(ColorStateList colorStateList){
        this.colorStateList = colorStateList;
    }
    public interface OnTabClickListener {
        void onTabClick(TabItem tabItem);
    }

    OnTabClickListener tabClickListener;
}
