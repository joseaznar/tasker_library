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

public class Promotion implements JSONable {

    public static final String JSON_WRAPPER = "promotion";
    public static final String JSON_CODE = "code";
    public static final String JSON_AMOUNT = "amount";
    public static final String JSON_MESSAGE = "message";

    private String mCode;
    private Double mAmount;
    private String mMessage;

    public Promotion() {
        mCode = null;
        mAmount = null;
        mMessage = null;
    }

    public Promotion(JSONObject json) throws JSONException {
        mCode = json.getString(JSON_CODE);
        mAmount = json.getDouble(JSON_AMOUNT);
        mMessage = json.getString(JSON_MESSAGE);
    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public void setAmount(Double amount) {
        mAmount = amount;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(JSON_CODE, mCode);
        object.put(JSON_AMOUNT, mAmount);
        object.put(JSON_MESSAGE, mMessage);
        return object;
    }

    public RequestParams buildParams() {
        HashMap<String, Object> object = new HashMap<>();
        object.put(JSON_CODE, mCode);

        RequestParams params = new RequestParams();
        params.put(JSON_WRAPPER, object);
        return params;
    }

}
