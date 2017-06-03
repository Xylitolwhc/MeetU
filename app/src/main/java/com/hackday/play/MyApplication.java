package com.hackday.play;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by victor on 17-6-3.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
    }
}
