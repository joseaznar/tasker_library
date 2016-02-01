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

public class BankAccount implements JSONable {

    public static final String JSON_WRAPPER = "bank_account";
    public static final String JSON_CODE = "code";
    public static final String JSON_HOLDER_NAME = "holder_name";

    private String mCode;
    private String mHolderName;

    public BankAccount() {
        mCode = null;
        mHolderName = null;
    }

    public BankAccount(JSONObject json) throws JSONException {
        mCode = json.getString(JSON_CODE);
        mHolderName = json.getString(JSON_HOLDER_NAME);
    }

    public String getCode() {
        return mCode;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public String getHolderName() {
        return mHolderName;
    }

    public void setHolderName(String holderName) {
        mHolderName = holderName;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(JSON_CODE, mCode);
        object.put(JSON_HOLDER_NAME, mHolderName);
        return object;
    }

    public RequestParams buildParams() {
        RequestParams params = new RequestParams();
        params.put(JSON_CODE, mCode);
        params.put(JSON_HOLDER_NAME, mHolderName);
        return params;
    }
}
