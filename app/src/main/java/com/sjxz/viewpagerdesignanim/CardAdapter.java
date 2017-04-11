package com.sjxz.viewpagerdesignanim;

import android.support.v7.widget.CardView;
import android.view.View;

/**
 * @author WYH_Healer
 * @email 3425934925@qq.com
 * Created by xz on 2017/4/6.
 * Role:
 */
public interface CardAdapter {

    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();//  <!-- 用来决定阴影尺寸大小以显示真实描绘的深度 -->

    CardView getCardViewAt(int position);

    int getCount();

    View getViewAlpha( View view);
}
