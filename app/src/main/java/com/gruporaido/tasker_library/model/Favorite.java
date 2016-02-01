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

public class Favorite implements JSONable {

    public static final String JSON_WRAPPER = "favorite";
    public static final String JSON_ALIAS = "alias";
    public static final String JSON_ADDRESS = "address";
    public static final String JSON_EXTERIOR_NUMBER = "exterior_number";
    public static final String JSON_INTERIOR_NUMBER = "interior_number";

    protected String mAlias;
    protected String mAddress;
    protected int mExteriorNumber;
    protected String mInteriorNumber;

    public Favorite() {
        mAlias = null;
        mAddress = null;
        mInteriorNumber = null;
    }

    public Favorite(JSONObject json) throws JSONException {
        mAlias = json.getString(JSON_ALIAS);
        mAddress = json.getString(JSON_ADDRESS);
        mExteriorNumber = json.getInt(JSON_EXTERIOR_NUMBER);
        mInteriorNumber = json.getString(JSON_INTERIOR_NUMBER);
    }

    public String getAlias() {
        return mAlias;
    }

    public void setAlias(String alias) {
        mAlias = alias;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public int getExteriorNumber() {
        return mExteriorNumber;
    }

    public void setExteriorNumber(int exteriorNumber) {
        mExteriorNumber = exteriorNumber;
    }

    public String getInteriorNumber() {
        return mInteriorNumber;
    }

    public void setInteriorNumber(String interiorNumber) {
        mInteriorNumber = interiorNumber;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(JSON_ALIAS, mAlias);
        object.put(JSON_ADDRESS, mAddress);
        object.put(JSON_EXTERIOR_NUMBER, mExteriorNumber);
        object.put(JSON_INTERIOR_NUMBER, mInteriorNumber);
        return object;
    }

    public RequestParams buildParams() {
        HashMap<String, Object> object = new HashMap<>();
        object.put(JSON_ALIAS, mAlias);
        object.put(JSON_ADDRESS, mAddress);
        object.put(JSON_EXTERIOR_NUMBER, mExteriorNumber);
        object.put(JSON_INTERIOR_NUMBER, mInteriorNumber);

        RequestParams params = new RequestParams();
        params.put(JSON_WRAPPER, object);
        return params;
    }
}
