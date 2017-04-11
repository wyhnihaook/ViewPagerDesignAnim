package com.sjxz.viewpagerdesignanim;

/**
 * @author WYH_Healer
 * @email 3425934925@qq.com
 * Created by xz on 2017/4/6.
 * Role:
 */
public class CardItem {


    private int mTextResource;
    private int mTitleResource;

    public CardItem(int title, int text) {
        mTitleResource = title;
        mTextResource = text;
    }

    public int getText() {
        return mTextResource;
    }

    public int getTitle() {
        return mTitleResource;
    }
}
