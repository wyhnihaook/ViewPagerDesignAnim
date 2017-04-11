package com.sjxz.viewpagerdesignanim;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener, View.OnTouchListener {

    private CustomView customView;


    private ViewPager mViewPager;

    private AnimTransformer animTransformer;//viewpager展示动画效果
    private CardPagerAdapter mCardAdapter;

    private CheckBox checkScale;

    private SeekBar seekbar;
    private TextView text_progress;

    private int progress=50;//显示初始化定义的进度


    /**
     * 欢迎界面相关数据
     * */

    private ViewPager viewPager;

    private GuidePagerAdapter guidePagerAdapter;

    private List<SFragment> datas;

    private GestureDetector gestureDetector;

    private int mFlaggingWidth;

    Animation animation;

    boolean scrollEnable=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        checkScale=(CheckBox)findViewById(R.id.checkScale);
        checkScale.setOnCheckedChangeListener(this);

        seekbar=(SeekBar)findViewById(R.id.seekbar);
        text_progress=(TextView)findViewById(R.id.text_progress);
        seekbar.setOnSeekBarChangeListener(this);

        customView= (CustomView) findViewById(R.id.customView);

        customView.setDataProgress(progress*1.0f,"自定义控件哦","欧巴进度","50%");

        seekbar.setProgress(progress);//初始化当前数据


        //Viewpager的setAdapter内的对象item有两种实现方式，相对简单的对象item，一个布局就能实现，不需要任何实时加载的数据；需要fragment进行搭档
        mCardAdapter = new CardPagerAdapter();
        mCardAdapter.addCardItem(new CardItem(R.string.title, R.string.text));
        mCardAdapter.addCardItem(new CardItem(R.string.title, R.string.text));
        mCardAdapter.addCardItem(new CardItem(R.string.title, R.string.text));
        mCardAdapter.addCardItem(new CardItem(R.string.title, R.string.text));

        animTransformer = new AnimTransformer(mViewPager, mCardAdapter);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, animTransformer);
        mViewPager.setOffscreenPageLimit(3);


        initSplash();

    }

    /**
     * 使欢迎界面更加友好
     * */
    private void initSplash() {
        viewPager=(ViewPager)findViewById(R.id.viewPager_splash);

        animation= AnimationUtils.loadAnimation(this,R.anim.splash);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                scrollEnable=false;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewPager.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        mFlaggingWidth = dm.widthPixels / 5;


        datas=new ArrayList<>();
        datas.add(new SFragment());
        datas.add(new SFragment());
        datas.add(new SFragment());
        datas.add(new SFragment());
        datas.add(new SFragment());
        datas.add(new SFragment());

        guidePagerAdapter=new GuidePagerAdapter(getSupportFragmentManager(),datas);

        gestureDetector=new GestureDetector(new WelcomePagerTouchListener());


        viewPager.setOnTouchListener(this);
        viewPager.setAdapter(guidePagerAdapter);
        mViewPager.setOffscreenPageLimit(6);
        viewPager.setPageTransformer(false,new SplashAnimTransformer());

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.checkScale:
                animTransformer.initAnim(isChecked,0);
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        //滚动数据监听
        customView.changeProgress(progress*1.0f);
        text_progress.setText("当前进度："+progress+"%");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //开始移动
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //停止移动
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return scrollEnable?this.gestureDetector.onTouchEvent(event):false;
    }

    /**
     * 页面手势滑动监听：监听最后一页的横向滑动，进入应用
     */
    private class WelcomePagerTouchListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(viewPager.getCurrentItem() == (guidePagerAdapter.getCount() - 1)) {
                if(Math.abs(e1.getX() - e2.getX()) > Math.abs(e1.getY() - e2.getY()) && (e1.getX() - e2.getX() <= (-mFlaggingWidth) || e1.getX() - e2.getX() >= mFlaggingWidth)) {
                    if(e1.getX() - e2.getX() >= mFlaggingWidth) {
                        //渐变消失
                        viewPager.startAnimation(animation);
                        return true;
                    }
                }
            }
            return false;
        }
    }

    //为了防止在动画过程中进行界面滑动效果
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return scrollEnable?super.onTouchEvent(event):false;
    }
}
