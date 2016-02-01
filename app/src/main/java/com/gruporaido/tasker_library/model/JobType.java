/**
 * GRUPO RAIDO CONFIDENTIAL
 * __________________
 * <p/>
 * [2015] - [2015] Grupo Raido SAPI de CV
 * All Rights Reserved.
 * <p/>
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

import org.json.JSONException;
import org.json.JSONObject;

public class JobType implements JSONable, Parcelable {

    public static final String JSON_WRAPPER = "job_type";
    public static final String JSON_ID = "id";
    public static final String JSON_NAME = "name";
    public static final String JSON_CATEGORY_ID = "job_category_id";

    protected long mId;
    protected String mName;
    protected long mCategoryId;
    protected JobCategory mJobCategory;

    public JobType() {
        mId = -1;
        mName = null;
        mCategoryId = -1;
    }

    public JobType(JSONObject json) throws JSONException {
        if (json.has(JSON_ID)) {
            mId = json.getLong(JSON_ID);
        }

        if (json.has(JSON_NAME)) {
            mName = json.getString(JSON_NAME);
        }

        if (json.has(JSON_CATEGORY_ID)) {
            mCategoryId = json.getLong(JSON_CATEGORY_ID);
        }
    }

    public JobType(Parcel in) {
        mId = in.readLong();
        mName = in.readString();
        mCategoryId = in.readLong();
    }

    public long getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public long getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(long categoryId) {
        mCategoryId = categoryId;
    }

    public JobCategory getJobCategory() {
        return mJobCategory;
    }

    public void setJobCategory(JobCategory jobCategory) {
        mJobCategory = jobCategory;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(JSON_ID, mId);
        object.put(JSON_NAME, mName);
        object.put(JSON_CATEGORY_ID, mCategoryId);
        return object;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(mId);
        out.writeString(mName);
        out.writeLong(mCategoryId);
    }

    public static final Creator CREATOR = new Creator() {
        public JobType createFromParcel(Parcel in) {
            return new JobType(in);
        }

        public JobType[] newArray(int size) {
            return new JobType[size];
        }
    };

}