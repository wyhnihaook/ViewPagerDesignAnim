package com.sjxz.viewpagerdesignanim;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;

/**
 * @author WYH_Healer
 * @email 3425934925@qq.com
 * Created by xz on 2017/4/6.
 * Role:动画效果展示
 */
public class AnimTransformer implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer {

    private ViewPager mViewPager;//设置滑动时候的监听
    private CardAdapter mAdapter;//获取相应的Item用于动画展示
    private float mLastOffset;//记录最后的位置，用于判断左滑还是右滑

    public boolean checkScale;


    public AnimTransformer(ViewPager viewPager, CardAdapter adapter) {
        mAdapter = adapter;
        mViewPager = viewPager;
        viewPager.addOnPageChangeListener(this);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //动画展示在滚动的过程中

        //对滚动事件监听之后发现，往右滑动的时候position会立刻变为当前-1的状态，而往左滑需要完全确认（操作手势为up的情况）才会累加+1
        //总结出如果往右滚动position需要在原有上+1才是目前的position，而当前显示的position则是下个可能切换的position
        //往左滚动的position一直是当前的position直到up手势操作之后进行切换，而position+1是下一个可能切换的position

        //1.需一个记录当前真是position的对象
        int realCurrentPosition;
        //2.需要对下一个item进行操作，所以也需要获取nextPosition的坐标
        int nextPosition;
        //3.由于使用了cardView存在阴影部分，需要获取宽度对其进行渐变
        float baseElevation = mAdapter.getBaseElevation();
        //4.根据打印的log发现，向左滑动是从1->0，往右滑动是从0->1，移动到某一数据时候即可进行界面切换，可以将其视为滑动动画渐变过程
        float realOffset;
        //5.判断左滑右滑，用于做item的渐变操作
        // 往右滑减小（从1开始---减小到一定比例即（1-左滑切换的比例0.6=0.4））
        // 往左滑增加(从0开始增加---增加到一定比例切换item例如0.6)
        boolean goingLeft = mLastOffset > positionOffset;//判断是左滑右滑

        //由于滑动事件的规律，已经左滑右滑界面的不同性进行界面操作
        if (goingLeft) {
            //右滑，显示左边模块
            realCurrentPosition = position + 1;
            nextPosition = position;
            realOffset = 1 - positionOffset;//这里保持数据一致，默认都是从1->0数据进行改变
        } else {
            //左滑,显示右边模块
            nextPosition = position + 1;

            realCurrentPosition = position;
            realOffset = positionOffset;//这里保持数据一致，默认都是从1->0数据进行改变
        }

        //判断下一个显示的position是否是最后一个item的index
        if (nextPosition > mAdapter.getCount() - 1
                || realCurrentPosition > mAdapter.getCount() - 1) {
            return;
        }

        //获取当前显示相应的item
        CardView cardView = mAdapter.getCardViewAt(realCurrentPosition);

        if (cardView != null) {

            if (checkScale) {
                cardView.setScaleX((float) (1 + 0.1 * (1 - realOffset)));//渐变过程是1.1->1
                cardView.setScaleY((float) (1 + 0.1 * (1 - realOffset)));//渐变过程是1.1->1
            }


            //字体渐变,
            mAdapter.getViewAlpha(cardView).setAlpha((1 - realOffset));

            //设置阴影处的渐变,将阴影区间从最大值渐变成最小值，即阴影宽度
            cardView.setCardElevation(baseElevation + baseElevation * (mAdapter.MAX_ELEVATION_FACTOR) * (1 - realOffset));
        }

        CardView cardViewNext = mAdapter.getCardViewAt(nextPosition);

        if (cardViewNext != null) {
            if (checkScale) {
                cardViewNext.setScaleX((float) (1 + 0.1 * (realOffset)));
                cardViewNext.setScaleY((float) (1 + 0.1 * (realOffset)));
            }


            //字体逐渐渐变显示
            mAdapter.getViewAlpha(cardViewNext).setAlpha((realOffset));

            //将要显示的数据逐渐变大
            cardViewNext.setCardElevation((baseElevation + baseElevation
                    * (CardAdapter.MAX_ELEVATION_FACTOR) * (realOffset)));
        }

        mLastOffset = positionOffset;

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void transformPage(View page, float position) {

    }

    //组合动画点击选中效果
    public void initAnim(boolean checkScale, int index) {

        CardView currentCard = mAdapter.getCardViewAt(mViewPager.getCurrentItem());
        if (currentCard == null) {
            return;
        }
        if (index == 0) {
            currentCard.animate().scaleY(checkScale ? 1.1f : 1);
            currentCard.animate().scaleX(checkScale ? 1.1f : 1);

            this.checkScale = checkScale;
        }

    }


}
