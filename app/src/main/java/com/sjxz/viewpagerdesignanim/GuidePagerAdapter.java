package com.sjxz.viewpagerdesignanim;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author WYH_Healer
 * @email 3425934925@qq.com
 * Created by xz on 2017/4/7.
 * Role:
 */
public class GuidePagerAdapter extends FragmentPagerAdapter {


    List<SFragment> datas ;


    public GuidePagerAdapter(FragmentManager fm,List<SFragment> datas) {
        super(fm);
        this.datas=datas;
    }


    @Override
    public int getCount() {
        return datas.size();
    }


    @Override
    public SFragment getItem(int position) {
        return datas.get(position);
    }

}
