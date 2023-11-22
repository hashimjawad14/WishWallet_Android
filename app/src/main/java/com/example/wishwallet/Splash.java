package com.example.wishwallet;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView splash_logo = findViewById(R.id.splash_logo);

        Animation splash_animation = AnimationUtils.loadAnimation(this, R.anim.splash_animation);

        splash_logo.startAnimation(splash_animation);

        Intent splash_intent = new Intent(Splash.this, Start1.class);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(splash_intent);
                finish();
            }
        }, 4000);


        statusbarColour();
    }

    private void statusbarColour(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white, this.getTheme()));
        }
    }
}