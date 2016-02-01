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

import android.os.Parcel;
import android.os.Parcelable;

import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Card implements JSONable, Parcelable {

    public static final String JSON_WRAPPER = "card";
    public static final String JSON_ID = "id";
    public static final String JSON_NUMBER = "number";
    public static final String JSON_CVV = "cvv";
    public static final String JSON_EXPIRATION_MONTH = "expiration_month";
    public static final String JSON_EXPIRATION_YEAR = "expiration_year";
    public static final String JSON_HOLDER_NAME = "holder_name";
    public static final String JSON_TOKEN_ID = "token_id";
    public static final String JSON_DEVICE_SESSION_ID = "device_session_id";

    protected int mId;
    protected String mNumber;
    protected String mCVV;
    protected String mExpirationMonth;
    protected String mExpirationYear;
    protected String mHolderName;
    protected String mTokenId;
    protected String mDeviceSessionId;

    public Card() {
        mId = -1;
        mNumber = null;
        mCVV = null;
        mExpirationMonth = null;
        mExpirationYear = null;
        mHolderName = null;
        mTokenId = null;
        mDeviceSessionId = null;
    }

    public Card(JSONObject json) throws JSONException {
        mExpirationMonth = json.getString(JSON_EXPIRATION_MONTH);
        mExpirationYear = json.getString(JSON_EXPIRATION_YEAR);
        mHolderName = json.getString(JSON_HOLDER_NAME);

        if (json.has(JSON_ID)) {
            mId = json.getInt(JSON_ID);
        }

        if (json.has(JSON_NUMBER)) {
            mNumber = json.getString(JSON_NUMBER);
        }

        if (json.has(JSON_CVV)) {
            mCVV = json.getString(JSON_CVV);
        }

        if (json.has(JSON_TOKEN_ID)) {
            mTokenId = json.getString(JSON_TOKEN_ID);
        }

        if (json.has(JSON_DEVICE_SESSION_ID) && !json.isNull(JSON_DEVICE_SESSION_ID)) {
            mDeviceSessionId = json.getString(JSON_DEVICE_SESSION_ID);
        }
    }

    public Card(Parcel in) {
        mId = in.readInt();
        mNumber = in.readString();
        mCVV = in.readString();
        mExpirationMonth = in.readString();
        mExpirationYear = in.readString();
        mHolderName = in.readString();
        mTokenId = in.readString();
        mDeviceSessionId = in.readString();
    }

    public Integer getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        mNumber = number;
    }

    public String getHiddenNumber() {
        return "•••• •••• •••• •" + mNumber;
    }

    public String getCVV() {
        return mCVV;
    }

    public void setCVV(String CVV) {
        mCVV = CVV;
    }

    public String getExpirationMonth() {
        return mExpirationMonth;
    }

    public void setExpirationMonth(String expirationMonth) {
        mExpirationMonth = expirationMonth;
    }

    public String getExpirationYear() {
        return mExpirationYear;
    }

    public void setExpirationYear(String expirationYear) {
        mExpirationYear = expirationYear;
    }

    public String getHolderName() {
        return mHolderName;
    }

    public void setHolderName(String holderName) {
        mHolderName = holderName;
    }

    public String getTokenId() {
        return mTokenId;
    }

    public void setTokenId(String tokenId) {
        mTokenId = tokenId;
    }

    public String getDeviceSessionId() {
        return mDeviceSessionId;
    }

    public void setDeviceSessionId(String deviceSessionId) {
        mDeviceSessionId = deviceSessionId;
    }

    public boolean isEmpty() {
        return mId == -1;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(JSON_ID, mId);
        object.put(JSON_NUMBER, mNumber);
        object.put(JSON_CVV, mCVV);
        object.put(JSON_EXPIRATION_MONTH, mExpirationMonth);
        object.put(JSON_EXPIRATION_YEAR, mExpirationYear);
        object.put(JSON_HOLDER_NAME, mHolderName);
        object.put(JSON_TOKEN_ID, mTokenId);
        object.put(JSON_DEVICE_SESSION_ID, mDeviceSessionId);
        return object;
    }

    public JSONObject toJSONWrapper() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(JSON_WRAPPER, toJSON());
        return object;
    }

    public RequestParams buildParams() {
        HashMap<String, Object> object = new HashMap<>();
        object.put(JSON_ID, mId);
        object.put(JSON_NUMBER, mNumber);
        object.put(JSON_CVV, mCVV);
        object.put(JSON_EXPIRATION_MONTH, mExpirationMonth);
        object.put(JSON_EXPIRATION_YEAR, mExpirationYear);
        object.put(JSON_HOLDER_NAME, mHolderName);
        object.put(JSON_TOKEN_ID, mTokenId);
        object.put(JSON_DEVICE_SESSION_ID, mDeviceSessionId);

        RequestParams params = new RequestParams();
        params.put(JSON_WRAPPER, object);
        return params;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mId);
        out.writeString(mNumber);
        out.writeString(mCVV);
        out.writeString(mExpirationMonth);
        out.writeString(mExpirationYear);
        out.writeString(mHolderName);
        out.writeString(mTokenId);
        out.writeString(mDeviceSessionId);
    }

    public static final Creator CREATOR = new Creator() {
        public Card createFromParcel(Parcel in) {
            return new Card(in);
        }

        public Card [] newArray(int size) {
            return new Card[size];
        }
    };
}
