package com.hackday.play.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.hackday.play.Adapters.MyRecyAdapter;
import com.hackday.play.MyApplication;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
                        locationInfor.setSpecific_infor(list.get(1).getName());
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
    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }
    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米
     */
    public static double GetDistance(double lat1, double lng1, double lat2, double lng2)
    {
        double EARTH_RADIUS = 6378.137;
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    public static void sortByDistance(List<LocationInfor> locationInfors, MyRecyAdapter adapter,LocationInfor user) {
        double x = user.getLatitude();
        double y = user.getLongtitude();
        LocationInfor[] infors = (LocationInfor[])locationInfors.toArray();
        for (int i = 0; i < locationInfors.size(); i++) {
            for (int j = i+1; j <locationInfors.size() ; j++) {
                if (GetDistance(x, y, infors[i].getLatitude(), infors[i].getLongtitude()) < GetDistance(x, y, infors[j].getLatitude(), infors[j].getLongtitude())) {
                    LocationInfor temp =infors[i] ;
                    infors[i] = infors[j];
                    infors[j] = temp;
                }
            }
        }
        List<LocationInfor> s = new ArrayList<>();
        for (int i = 0; i <infors.length ; i++) {
            s.add(infors[i]);
        }
        adapter.setLocationInforList(s);
    }


    public static String formatDate(String time) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd hh:mm:ss");
        Date date = sdf.parse(time);
        long tm = date.getTime();
        long current = System.currentTimeMillis();
        long s = current - tm;
        if (s >= 0 && s < 60) {
            return s + "秒前";
        } else if (s >= 60 && s < 600) {
            int minute = (int)s / 60;
            return minute + "分钟前";
        } else  {
            DateFormat format = new SimpleDateFormat("mmdd hh:mm");
            return format.format(date);
        }
    }
    public static boolean isBefore(String time1, String time2) throws ParseException{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //将字符串形式的时间转化为Date类型的时间
        Date a=sdf.parse(time1);
        Date b=sdf.parse(time2);
        //Date类的一个方法，如果a早于b返回true，否则返回false
        if(a.before(b))
            return true;
        else
            return false;

    }

    /**
     * 设置SharedPreference 值
     *
     * @param context
     * @param key
     * @param value
     */
    public static final boolean putIntValue(Context context, String key,
                                            int value) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putInt(key, value);
        boolean result = editor.commit();
        if (!result) {
            return false;
        }
        return true;
    }
    /**
     * 设置SharedPreference 值
     *
     * @param context
     * @param key
     * @param value
     */
    public static final boolean putValue(Context context, String key,
                                         String value) {
        value = value == null ? "" : value;
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(key, value);
        boolean result = editor.commit();
        if (!result) {
            return false;
        }
        return true;
    }

    /**
     * 移除SharedPreference
     *
     * @param context
     * @param key
     */
    public static final void RemoveValue(Context context, String key) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.remove(key);
        boolean result = editor.commit();
        if (!result) {
            Log.e("移除Shared", "save " + key + " failed");
        }
    }
    private static final SharedPreferences getSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * 获取SharedPreference 值
     *
     * @param context
     * @param key
     * @return
     */
    public static final String getValue(Context context, String key) {
        return getSharedPreference(context).getString(key, "");
    }
    public static final Boolean getBooleanValue(Context context, String key) {
        return getSharedPreference(context).getBoolean(key, false);
    }

    public static final void putBooleanValue(Context context, String key,
                                             boolean bl) {
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.putBoolean(key, bl);
        edit.commit();
    }

    public static final int getIntValue(Context context, String key) {
        return getSharedPreference(context).getInt(key, 0);
    }

    public static final long getLongValue(Context context, String key,
                                          long default_data) {
        return getSharedPreference(context).getLong(key, default_data);
    }

    public static final boolean putLongValue(Context context, String key,
                                             Long value) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putLong(key, value);
        return editor.commit();
    }

}
