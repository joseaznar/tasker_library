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

public class Quote implements JSONable {

    public static final String JSON_WRAPPER = "quote";
    public static final String JSON_LABOR_COST = "labor_cost";
    public static final String JSON_MATERIALS_COST = "materials_cost";
    public static final String JSON_LABOR_TOTAL = "labor_total";
    public static final String JSON_MATERIALS_TOTAL = "materials_total";
    public static final String JSON_PROMOTION = "promotion";
    public static final String JSON_TOTAL = "total";

    protected float mLaborCost;
    protected float mMaterialsCost;
    protected float mLaborTotal;
    protected float mMaterialsTotal;
    protected float mPromotion;
    protected float mTotal;

    public Quote() {
        mLaborCost = -1;
        mMaterialsCost = -1;
        mPromotion = -1;
        mLaborTotal = -1;
        mMaterialsTotal = -1;
        mTotal = -1;
    }

    public Quote(JSONObject json) throws JSONException {
        if (json.has(JSON_LABOR_COST)) {
            mLaborCost = (float) json.getDouble(JSON_LABOR_COST);
        }

        if (json.has(JSON_MATERIALS_COST)) {
            mMaterialsCost = (float) json.getDouble(JSON_MATERIALS_COST);
        }

        if (json.has(JSON_LABOR_TOTAL)) {
            mLaborTotal = (float) json.getDouble(JSON_LABOR_TOTAL);
        }

        if (json.has(JSON_MATERIALS_TOTAL)) {
            mMaterialsTotal = (float) json.getDouble(JSON_MATERIALS_TOTAL);
        }

        if (json.has(JSON_PROMOTION)) {
            mPromotion = (float) json.getDouble(JSON_PROMOTION);
        }

        if (json.has(JSON_TOTAL)) {
            mTotal = (float) json.getDouble(JSON_TOTAL);
        }
    }

    public float getLaborCost() {
        return mLaborCost;
    }

    public void setLaborCost(float laborCost) {
        mLaborCost = laborCost;
    }

    public float getMaterialsCost() {
        return mMaterialsCost;
    }

    public void setMaterialsCost(float materialsCost) {
        mMaterialsCost = materialsCost;
    }

    public float getLaborTotal() {
        return mLaborTotal;
    }

    public float getMaterialsTotal() {
        return mMaterialsTotal;
    }

    public float getPromotion() {
        return mPromotion;
    }

    public void setPromotion(float promotion) {
        mPromotion = promotion;
    }

    public float getTotal() {
        return mTotal;
    }

    public boolean isEmpty() {
        return mLaborCost == -1 || mMaterialsCost == -1;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(JSON_LABOR_COST, mLaborCost);
        object.put(JSON_MATERIALS_COST, mMaterialsCost);
        object.put(JSON_LABOR_TOTAL, mLaborTotal);
        object.put(JSON_MATERIALS_TOTAL, mMaterialsTotal);
        object.put(JSON_PROMOTION, mPromotion);
        object.put(JSON_TOTAL, mTotal);
        return object;
    }

    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> object = new HashMap<>();
        object.put(JSON_LABOR_COST, mLaborCost);
        object.put(JSON_MATERIALS_COST, mMaterialsCost);
        return object;
    }

    public RequestParams buildParams() {
        HashMap<String, Object> object = toHashMap();

        RequestParams params = new RequestParams();
        params.put(JSON_WRAPPER, object);
        return params;
    }

}
