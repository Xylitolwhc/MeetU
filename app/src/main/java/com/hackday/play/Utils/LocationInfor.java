package com.hackday.play.Utils;

import org.litepal.crud.DataSupport;

/**
 * Created by victor on 17-6-3.
 */

public class LocationInfor extends DataSupport{
    private String time;

    private String name;
    private String sex;
    private String building;
    private String addr;
    private double longtitude;
    private double latitude;

    private String specific_infor;
    public void setSpecific_infor(String specific_infor) {
        this.specific_infor = specific_infor;
    }

    public String getSpecific_infor() {
        return specific_infor;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public void setBuilding(String building) {
        this.building = building;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getTime() {
        return time;
    }



    public String getBuilding() {
        return building;
    }

    public String getAddr() {
        return addr;
    }
}
