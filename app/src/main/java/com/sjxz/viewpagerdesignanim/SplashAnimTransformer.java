package com.sjxz.viewpagerdesignanim;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * @author WYH_Healer
 * @email 3425934925@qq.com
 * Created by xz on 2017/4/7.
 * Role:
 */
public class SplashAnimTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;
    /**
     * 举个例子

     a 是第一页
     b 是第二页
     当前页为 a, 当  a  向左滑动,  直到滑到 b 时:
     a 的position变化是  [-1 ,   0]   由  0  慢慢变到 -1
     b 的position变化是  ( 0 ,   1]   由  1  慢慢变到  0

     当前页为b,  当 b 向右滑动, 直到滑到a 时:
     a 的position变化是  [-1 ,   0]   由  -1  慢慢变到 0
     b 的position变化是  ( 0 ,   1]   由   0   慢慢变到 1
     * */
    @Override
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page
            view.setAlpha(1);
            view.setTranslationX(0);
            view.setScaleX(1);
            view.setScaleY(1);

        } else if (position <= 1) { // (0,1]
            // Fade the page out.
            view.setAlpha(1 - position);

            // Counteract the default slide transition 这个是和滑动事件不相关的，就是保证第二个view在当前页面展示
            view.setTranslationX(pageWidth * -position);

            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
    }
}
