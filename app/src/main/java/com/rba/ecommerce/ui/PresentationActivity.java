package com.rba.ecommerce.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.nightonke.wowoviewpager.Eases.EaseType;
import com.nightonke.wowoviewpager.ViewAnimation;
import com.nightonke.wowoviewpager.WoWoTranslationAnimation;
import com.nightonke.wowoviewpager.WoWoUtil;
import com.nightonke.wowoviewpager.WoWoViewPager;
import com.nightonke.wowoviewpager.WoWoViewPagerAdapter;
import com.rba.ecommerce.R;

public class PresentationActivity extends AppCompatActivity {

    private WoWoViewPager wowo;
    private WoWoViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_presentation);

        wowo = (WoWoViewPager)findViewById(R.id.wowo_viewpager);
        adapter = new WoWoViewPagerAdapter(getSupportFragmentManager());
        adapter.setFragmentsNumber(4);
        adapter.setColorRes(R.color.colorPrimary);
        wowo.setAdapter(adapter);

    }

    private int screenW = 1;
    private int screenH = 1;
    private int circleR = 1;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        screenW = WoWoUtil.getScreenWidth(this);
        screenH = WoWoUtil.getScreenHeight(this);
//
        RelativeLayout base = (RelativeLayout)findViewById(R.id.lnlGeneral);
        ViewGroup.LayoutParams layoutParams = base.getLayoutParams();
//        layoutParams.height = circleR * 2;
//        layoutParams.width = circleR * 2;
//        base.setLayoutParams(layoutParams);
//
        setSmileStickAnimation();
        setWhatIsSmile();
        setWhatIsSmileDescription();
    }

    private void setSmileStickAnimation() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.imgSmile));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 1,
                0,
                0,
                -screenW,
                0,
                EaseType.Linear,
                true
        ));
        wowo.addAnimation(animation);
    }

    private void setWhatIsSmile() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.lblWhatIsSmile));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 1,
                findViewById(R.id.lblWhatIsSmile).getTranslationX(),
                findViewById(R.id.lblWhatIsSmile).getTranslationY(),
                -screenW,
                findViewById(R.id.lblWhatIsSmile).getTranslationY(),
                EaseType.EaseInBack,
                false
        ));
        wowo.addAnimation(animation);
    }


    private void setWhatIsSmileDescription() {
        ViewAnimation animation = new ViewAnimation(findViewById(R.id.lblWhatIsSmileDescription));
        animation.addPageAnimaition(new WoWoTranslationAnimation(
                0, 0, 1,
                findViewById(R.id.lblWhatIsSmileDescription).getTranslationX(),
                findViewById(R.id.lblWhatIsSmileDescription).getTranslationY(),
                -screenW,
                findViewById(R.id.lblWhatIsSmileDescription).getTranslationY(),
                EaseType.EaseInBack,
                false
        ));
        wowo.addAnimation(animation);
    }







}
