package com.example.maks.discoduck.activities;


import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.maks.discoduck.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {
    private static final long SPLASH_DISPLAY_LENGTH = 2000;

    @BindView(R.id.ac_splash_iv_duck)
    ImageView duck;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_splash);
        ButterKnife.bind(this);

        duck.post(() -> ((AnimationDrawable) duck.getDrawable()).start());
        new Handler().postDelayed(this::openNavigation, SPLASH_DISPLAY_LENGTH);
    }

    private void openNavigation() {
        startActivity(new Intent(this, MainActivity.class));
        if (null != duck) {
            ((AnimationDrawable) duck.getDrawable()).stop();
        }
        finish();
    }
}
