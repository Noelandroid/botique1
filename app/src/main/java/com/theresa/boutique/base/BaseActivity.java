package com.theresa.boutique.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.theresa.boutique.util.SharedPreferenceHelper;

public class BaseActivity extends AppCompatActivity {

    private SharedPreferenceHelper sharedPreferenceHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public SharedPreferenceHelper getSharedPreferenceHelper() {

        if (sharedPreferenceHelper == null) {
            sharedPreferenceHelper = new SharedPreferenceHelper(this);
        }
        return sharedPreferenceHelper;
    }
}
