package com.hackday.play;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.hackday.play.Adapters.MyFragAdapter;
import com.hackday.play.Fragments.MyFragment;
import com.hackday.play.Fragments.SquareFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;
    private BaiduMap baiduMap;
    private BMapManager bmapManager;
    private LocationClient locationClient;
    private MyLocationData locationData;
    private BDLocationListener listener;
    private static final String TAG = "MainActivity";

    private ViewPager viewPager;
    private MyFragAdapter myFragAdapter;
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.mViewPager);
        SquareFragment fragment = new SquareFragment();
        fragmentList.add(fragment);
        MyFragment myFragment = new MyFragment();
        fragmentList.add(myFragment);
        myFragAdapter = new MyFragAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(myFragAdapter);

        View view=getLayoutInflater().inflate(R.layout.alert_dialog, null);
        Button positive=(Button) view.findViewById(R.id.dialog_positive);
        final AlertDialog dialog=new AlertDialog.Builder(this).setView(view).setCancelable(false).create();
        positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "I konw!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    private void init2() {

    }

    private void init() {
        mapView = (MapView) findViewById(R.id.baidu);
        locationClient = new LocationClient(getApplicationContext());
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span = 1000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        locationClient.setLocOption(option);
        listener = new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                StringBuilder builder = new StringBuilder();
                builder.append("时间：" + bdLocation.getTime());
                builder.append("\n类型: " + bdLocation.getLocType());
                builder.append("\n国家: " + bdLocation.getCountry());
                builder.append("\n省份: " + bdLocation.getProvince());
                builder.append("\n城市: " + bdLocation.getCity());
                builder.append("\n" + bdLocation.getAddrStr());
                Log.e(TAG, builder.toString());
            }

            @Override
            public void onConnectHotSpotMessage(String s, int i) {

            }
        };
        locationClient.registerLocationListener(listener);
        locationClient.start();
        locationClient.requestLocation();
        locationClient.registerNotifyLocationListener(listener);
//        baiduMap.setMyLocationData(myLocationData);
//        bmapManager = new BMapManager();
//        locationClient  = new LocationClient(getApplicationContext());
//        locationClient.requestLocation();
//        bmapManager.init("0dPG1ffxM3BzoPCTYj9o9hyPTC6Kexlr",new MK);

    }

}
