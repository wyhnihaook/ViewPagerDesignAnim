package com.sjxz.viewpagerdesignanim;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author WYH_Healer
 * @email 3425934925@qq.com
 * Created by xz on 2017/4/7.
 * Role:
 */
public class SFragment extends Fragment {

    private CardView mCardView;

    public TextView tv;

    public static SFragment sFragment;

    //比较特殊的类。每次getInstance都需要新建
    public synchronized static SFragment getInstance(Bundle bundle) {

        sFragment = new SFragment();
        sFragment.setArguments(bundle);
        return sFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_adapter, container, false);
        mCardView = (CardView) view.findViewById(R.id.cardView);
        mCardView.setMaxCardElevation(mCardView.getCardElevation()
                * CardAdapter.MAX_ELEVATION_FACTOR);
        tv=(TextView)view.findViewById(R.id.tv);
        return view;
    }
    public CardView getCardView() {
        return mCardView;
    }

    public void initYM(int position){
        tv.setText("页码："+(position+1));
    }
}
