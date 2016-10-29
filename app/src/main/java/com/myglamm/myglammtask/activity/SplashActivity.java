package com.myglamm.myglammtask.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.myglamm.myglammtask.R;
import com.myglamm.myglammtask.customui.GradientBackgroundPainter;

/**
 * Created by Akash Wangalwar on 26-10-2016.
 */
public class SplashActivity extends BaseActivity {

    private static int SPLASH_TIME_OUT = 6500;
    private static final String TAG = SplashActivity.class.getName();

    private TextView title;
    private GradientBackgroundPainter gradientBackgroundPainter;
    private View mBackgroundImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        setContentView(R.layout.splash_activity_layout);
        setIdsToViews();
        setUpBackgroundView();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, ArtistActivity.class));
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void setIdsToViews() {
        mBackgroundImage = findViewById(R.id.root_view);
        title = (TextView) findViewById(R.id.fullscreen_content);
    }

    private void setUpBackgroundView() {

        if (Build.VERSION.SDK_INT <= 19) {
            Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Magnolia Script.ttf");
            title.setTypeface(typeface);
        }

        final int[] drawables = new int[5];
        drawables[0] = R.drawable.gradient_1;
        drawables[1] = R.drawable.gradient_2;
        drawables[2] = R.drawable.gradient_3;
        drawables[3] = R.drawable.gradient_4;
        drawables[4] = R.drawable.gradient_5;

        gradientBackgroundPainter = new GradientBackgroundPainter(mBackgroundImage, drawables);
        gradientBackgroundPainter.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gradientBackgroundPainter.stop();
    }

}