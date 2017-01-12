package com.yohan.dummyapp.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.yohan.dummyapp.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_screen);

        ImageView imgLogo = (ImageView)findViewById(R.id.imageView_splash_logo);
        // Step1 : create the  RotateAnimation object
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.splash_anim);
        imgLogo.startAnimation(shake);
        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {


                Timer timer = new Timer();
                long delay = 1000;

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class );
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        finish();
                    }
                }, delay);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

       /* */
    }
}
