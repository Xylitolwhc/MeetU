package com.hackday.play;

import android.location.LocationListener;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.hackday.play.Utils.LocationInfor;
import com.hackday.play.Utils.Utils;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;
    private BaiduMap baiduMap;
    private BMapManager bmapManager;
    private LocationClient locationClient;
    private MyLocationData locationData;
    private BDLocationListener listener;
    private static final String TAG = "MainActivity";
    private LocationInfor infor = new LocationInfor();
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                Log.e(TAG, infor.getAddr());
                Log.e(TAG, infor.getSpecific_infor());
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapView = (MapView) findViewById(R.id.baidu);
        Utils.getLocation(infor,handler);
    }

    }
