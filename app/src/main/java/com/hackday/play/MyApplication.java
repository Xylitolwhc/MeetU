package com.hackday.play;

import android.app.Application;
import android.content.Context;

import com.baidu.mapapi.SDKInitializer;
import com.hackday.play.Utils.LocationInfor;

/**
 * Created by victor on 17-6-3.
 */

public class MyApplication extends Application{
    private static Context context;
    private static String name,id;
    private static LocationInfor locationInfor;

    public static LocationInfor getLocationInfor() {
        return locationInfor;
    }

    public static void setLocationInfor(LocationInfor locationInfor) {
        MyApplication.locationInfor = locationInfor;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        MyApplication.name = name;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        MyApplication.id = id;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        SDKInitializer.initialize(this);
    }

    public static Context getContext() {
        return context;
    }
}
