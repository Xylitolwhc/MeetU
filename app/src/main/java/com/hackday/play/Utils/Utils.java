package com.hackday.play.Utils;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.hackday.play.MyApplication;

import java.util.List;

/**
 * Created by victor on 17-6-3.
 */

public class Utils {
    public static void getLocation(final LocationInfor locationInfor, final Handler handler){
        LocationClient locationClient = new LocationClient(MyApplication.getContext());
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span=1000;
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
        BDLocationListener listener = new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation location) {
                //获取定位结果
                locationInfor.setTime(location.getTime());
                locationInfor.setLatitude(location.getLatitude());
                locationInfor.setLongtitude(location.getLongitude());
//                sb.append(location.getLongitude());    //获取经度信息

                if (location.getLocType() == BDLocation.TypeGpsLocation){

                    // GPS定位结果
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){

                    // 网络定位结果
                    locationInfor.setAddr(location.getAddrStr());
                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {

                    // 离线定位结果
                } else if (location.getLocType() == BDLocation.TypeServerError) {


                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {

//
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {


                }

                List<Poi> list = location.getPoiList();    // POI数据
                if (list != null) {
                    for (Poi p : list) {
                        String name = p.getName();
                    }
                        locationInfor.setSpecific_infor(list.get(0).getName());
                }
                handler.sendEmptyMessage(0x123);
            }

            @Override
            public void onConnectHotSpotMessage(String s, int i) {

            }
        };
        locationClient.registerLocationListener(listener);
        locationClient.start();
        locationClient.requestLocation();
    }
}
