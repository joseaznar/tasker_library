/**
 *
 * GRUPO RAIDO CONFIDENTIAL
 * __________________
 *
 *  [2015] - [2015] Grupo Raido SAPI de CV
 *  All Rights Reserved.
 *
 * NOTICE: All information contained herein is, and remains
 * the property of Grupo Raido SAPI de CV and its suppliers,
 * if any. The intellectual and technical concepts contained
 * herein are proprietary to Grupo Raido SAPI de CV and its
 * suppliers and may be covered by México and Foreign Patents,
 * patents in process, and are protected by trade secret or
 * copyright law. Dissemination of this information or
 * reproduction of this material is strictly forbidden unless
 * prior written permission is obtained from Grupo Raido SAPI
 * de CV.
 */

package com.gruporaido.tasker_library.model;

import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Location implements JSONable {

    public static final String JSON_WRAPPER = "location";
    public static final String JSON_LATITUDE = "latitude";
    public static final String JSON_LONGITUDE = "longitude";
    public static final String JSON_DRIVE_TIME = "drive_time";

    private Double mLatitude;
    private Double mLongitude;
    private Integer mDriveTime;

    public Location() {
        mLatitude = null;
        mLongitude = null;
        mDriveTime = null;
    }

    public Location(android.location.Location location) {
        mLatitude = location.getLatitude();
        mLongitude = location.getLongitude();
        mDriveTime = null;
    }

    public Location(JSONObject json) throws JSONException {
        mLatitude = json.getDouble(JSON_LATITUDE);
        mLongitude = json.getDouble(JSON_LONGITUDE);

        if (json.has(JSON_DRIVE_TIME) && !json.isNull(JSON_DRIVE_TIME)) {
            mDriveTime = json.getInt(JSON_DRIVE_TIME);
        }
    }

    public Location(Double latitude, Double longitude) {
        mLatitude = latitude;
        mLongitude = longitude;
        mDriveTime = null;
    }

    public Double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(Double latitude) {
        mLatitude = latitude;
    }

    public Double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(Double longitude) {
        mLongitude = longitude;
    }

    public Integer getDriveTime() {
        return mDriveTime;
    }

    public void setDriveTime(Integer driveTime) {
        mDriveTime = driveTime;
    }

    public boolean isEmpty() {
        return mLatitude == null || mLongitude == null;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(JSON_LATITUDE, mLatitude);
        object.put(JSON_LONGITUDE, mLongitude);
        object.put(JSON_DRIVE_TIME, mDriveTime);
        return object;
    }

    public RequestParams buildParams() {
        HashMap<String, Object> object = new HashMap<>();
        object.put(JSON_LATITUDE, mLatitude);
        object.put(JSON_LONGITUDE, mLongitude);

        RequestParams params = new RequestParams();
        params.put(JSON_WRAPPER, object);
        return params;
    }

}
