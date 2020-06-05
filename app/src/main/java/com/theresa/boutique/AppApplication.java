package com.theresa.boutique;

import android.app.Application;

import com.theresa.boutique.util.ApiService;
import com.theresa.boutique.util.NetworkUtil;

public class AppApplication extends Application {

    private static final String TAG = AppApplication.class.getSimpleName();
    private static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService == null) {
            synchronized (TAG) {
                apiService = NetworkUtil.getApiService();
                return apiService;
            }
        }
        return apiService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
