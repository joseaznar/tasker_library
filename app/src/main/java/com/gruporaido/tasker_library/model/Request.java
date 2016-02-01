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

import java.util.Date;

public class Request implements JSONable {

    public static final String JSON_WRAPPER = "request";
    public static final String JSON_ID = "id";
    public static final String JSON_STATE = "state";
    public static final String JSON_DRIVE_TIME = "drive_time";
    public static final String JSON_JOB_ID = "job_id";
    public static final String JSON_JOB = "job";
    public static final String JSON_CREATED_AT = "created_at";

    protected int mId;
    protected String mState;
    protected Integer mDriveTime;
    protected int mJobId;
    protected Job mJob;
    protected Date mCreatedAt;

    public static final class States {
        public static final String Pending = "pending";
        public static final String Accepted = "accepted";
        public static final String Rejected = "rejected";
        public static final String Ignored = "ignored";
    }

    public Request() {
        mId = -1;
        mState = null;
        mDriveTime = null;
        mJobId = -1;
        mJob = null;
        mCreatedAt = null;
    }

    public Request(JSONObject json) throws JSONException {
        if (json.has(JSON_JOB_ID)) {
            mJobId = json.getInt(JSON_JOB_ID);
        }

        mState = json.getString(JSON_STATE);

        if (json.has(JSON_DRIVE_TIME)) {
            mDriveTime = json.getInt(JSON_DRIVE_TIME);
        }

        if (json.has(JSON_ID)) {
            mId = json.getInt(JSON_ID);
        }

        if (json.has(JSON_JOB)) {
            mJob = new Job(json.getJSONObject(JSON_JOB));
        }

        //mCreatedAt = new Date(json.getString(JSON_CREATED_AT));
    }

    public Integer getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public Date getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        mCreatedAt = createdAt;
    }

    public int getJobId() {
        return mJobId;
    }

    public void setJobId(int jobId) {
        mJobId = jobId;
    }

    public Integer getDriveTime() {
        return mDriveTime;
    }

    public Job getJob() {
        return mJob;
    }

    public void setJob(Job mJob) {
        this.mJob = mJob;
    }

    public boolean isEmpty() {
        return mId == -1;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(JSON_ID, mId);
        object.put(JSON_STATE, mState);
        object.put(JSON_DRIVE_TIME, mDriveTime);
        object.put(JSON_JOB_ID, mJobId);
        object.put(JSON_JOB, mJob.toJSON());
        if(mCreatedAt != null) object.put(JSON_CREATED_AT, mCreatedAt.toString());
        return object;
    }

    public RequestParams buildParams() {
        RequestParams params = new RequestParams();
        params.put(JSON_ID, mId);
        params.put(JSON_STATE, mState);
        params.put(JSON_JOB_ID, mJobId);
        return params;
    }
}
