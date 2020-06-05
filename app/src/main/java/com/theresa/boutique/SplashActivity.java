package com.theresa.boutique;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.theresa.boutique.base.BaseActivity;

import static com.theresa.boutique.util.Constants.LOGGEDIN;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
if(getSharedPreferenceHelper().getBoolean(LOGGEDIN,false)){
    startActivity(new Intent(SplashActivity.this, CustomerRegistrationActivity.class));
    finish();
}else {
    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
    finish();
}
            }
        }, 2000);
    }
}
