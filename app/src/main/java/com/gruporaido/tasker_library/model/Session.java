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

public class Session implements JSONable {

    public static final String JSON_WRAPPER = "session";
    public static final String JSON_PLATFORM = "platform";
    public static final String JSON_PUSH_ID = "push_id";

    private String mPlatform;
    private String mPushId;

    public Session() {
        mPlatform = "android";
        mPushId = null;
    }

    public Session(JSONObject json) throws JSONException {
        mPlatform = json.getString(JSON_PLATFORM);
        mPushId = json.getString(JSON_PUSH_ID);
    }

    public String getPlatform() {
        return mPlatform;
    }

    public void setPlatform(String platform) {
        mPlatform = platform;
    }

    public String getPushId() {
        return mPushId;
    }

    public void setPushId(String pushId) {
        mPushId = pushId;
    }

    public boolean isEmpty() {
        return mPushId == null || mPushId.isEmpty();
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(JSON_PLATFORM, mPlatform);
        object.put(JSON_PUSH_ID, mPushId);
        return object;
    }

    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> object = new HashMap<>();
        object.put(JSON_PLATFORM, mPlatform);
        object.put(JSON_PUSH_ID, mPushId);
        return object;
    }

    public RequestParams buildParams() {
        HashMap<String, Object> object = toHashMap();

        RequestParams params = new RequestParams();
        params.put(JSON_WRAPPER, object);
        return params;
    }

}
