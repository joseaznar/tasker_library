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

public class Phone implements JSONable {

    public static final String JSON_WRAPPER = "phone";
    public static final String JSON_NUMBER = "number";
    public static final String JSON_VERIFICATION_CODE = "verification_code";

    private String mNumber;
    private String mVerificationCode;

    public Phone() {
        mNumber = null;
        mVerificationCode = null;
    }

    public Phone(JSONObject json) throws JSONException {
        mNumber = json.getString(JSON_NUMBER);
        if (json.has(JSON_VERIFICATION_CODE)) {
            mVerificationCode = json.getString(JSON_VERIFICATION_CODE);
        }
    }

    public String getVerificationCode() {
        return mVerificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        mVerificationCode = verificationCode;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        mNumber = number;
    }

    public boolean isEmpty() {
        return mNumber == null || mVerificationCode.isEmpty();
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(JSON_NUMBER, mNumber);
        object.put(JSON_VERIFICATION_CODE, mVerificationCode);
        return object;
    }

    public JSONObject toJSONWrapper() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(JSON_WRAPPER, toJSON());
        return object;
    }

    public RequestParams buildParams() {
        HashMap<String, Object> object = new HashMap<>();
        object.put(JSON_NUMBER, mNumber);
        object.put(JSON_VERIFICATION_CODE, mVerificationCode);

        RequestParams params = new RequestParams();
        params.put(JSON_WRAPPER, object);
        return params;
    }

}

