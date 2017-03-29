package com.wujs.bottomnavigation;

import android.support.v4.app.Fragment;

/**
 * Created by wujs on 2017/2/10-11:03.
 * Email:wujs@paxsz.com
 */

public class TabItem {
    /**
     * icon
     */
    private int imageResId;
    /**
     * the text
     */
    private int textResId;
    private Class<? extends Fragment> tagFramgentClz;

    public TabItem(int imageResId, int lableResId) {
        this.imageResId = imageResId;
        this.textResId = lableResId;
    }

    public TabItem(int imageResId, int lableResId, Class<? extends Fragment> tagFramgentClz) {
        this.imageResId = imageResId;
        this.textResId = lableResId;
        this.tagFramgentClz = tagFramgentClz;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public int getTextResId() {
        return textResId;
    }

    public void setTextResId(int textResId) {
        this.textResId = textResId;
    }

    public Class<? extends Fragment> getTagFramgentClz() {
        return tagFramgentClz;
    }

    public void setTagFramgentClz(Class<? extends Fragment> tagFramgentClz) {
        this.tagFramgentClz = tagFramgentClz;
    }
}
