package com.wujs.bottomnavigation;


import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by wujs on 2017/2/10-11:15.
 * Email:wujs@paxsz.com
 */

public class TabItemView extends LinearLayout implements View.OnClickListener {
    private ColorStateList colorStateList;
    private ImageView mTabIcon;
    private TextView mTabText;

    public TabItemView(Context context) {
        this(context, null);
    }

    public TabItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        Log.e("TabItemView","init view");
    }

    public void setDefaultTextColor(ColorStateList colorStateList) {
        this.colorStateList = colorStateList;
    }

    private void initView(Context context) {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        LayoutInflater.from(context).inflate(R.layout.tab_item_view, this, true);
        mTabIcon = (ImageView) findViewById(R.id.tab_icon);
        mTabText = (TextView) findViewById(R.id.tab_text);

    }


    public void initData(TabItem tabItem) {
        if (mTabIcon != null && mTabText!= null && colorStateList != null){
            mTabIcon.setImageResource(tabItem.getImageResId());
            mTabText.setTextColor(colorStateList);
            mTabText.setText(tabItem.getTextResId());
        }
    }

    @Override
    public void onClick(View v) {

    }
}
