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

public class Complaint implements JSONable {

    public static final String JSON_WRAPPER = "complaint";
    public static final String JSON_ID = "id";
    public static final String JSON_DESCRIPTION = "description";
    public static final String JSON_TYPE = "type";
    public static final String JSON_COMPLAINT_CATEGORY_ID = "complaint_category_id";
    public static final String JSON_JOB_ID = "job_id";
    public static final String JSON_COMPLAINT_CATEGORY = "complaint_category";

    public static final class Type {
        public static final String JOB = "JobComplaint";
        public static final String WARRANTY = "WarrantyComplaint";
    }

    protected long mId;
    protected String mDescription;
    protected String mType;
    protected long mComplaintCategoryId;
    protected long mJobId;
    protected ComplaintCategory mComplaintCategory;

    public Complaint() {
        mId = -1;
        mDescription = null;
        mType = null;
        mComplaintCategoryId = -1;
        mJobId = -1;
        mComplaintCategory = null;
    }

    public Complaint(JSONObject json) throws JSONException {
        if (json.has(JSON_ID)) {
            mId = json.getInt(JSON_ID);
        }

        mDescription = json.getString(JSON_DESCRIPTION);

        if (json.has(JSON_COMPLAINT_CATEGORY_ID)) {
            mComplaintCategoryId = json.getInt(JSON_COMPLAINT_CATEGORY_ID);
        }

        if (json.has(JSON_JOB_ID)) {
            mJobId = json.getInt(JSON_JOB_ID);
        }

        if (json.has(JSON_COMPLAINT_CATEGORY)) {
            mComplaintCategory = new ComplaintCategory(json.getJSONObject(JSON_COMPLAINT_CATEGORY));
        }
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public long getComplaintCategoryId() {
        return mComplaintCategoryId;
    }

    public void setComplaintCategoryId(long complaintCategoryId) {
        mComplaintCategoryId = complaintCategoryId;
    }

    public long getJobId() {
        return mJobId;
    }

    public void setJobId(long jobId) {
        mJobId = jobId;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public ComplaintCategory getComplaintCategory() {
        return mComplaintCategory;
    }

    public void setComplaintCategory(ComplaintCategory complaintCategory) {
        mType = Type.JOB;
        mComplaintCategoryId = complaintCategory.getId();
        mComplaintCategory = complaintCategory;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(JSON_ID, mId);
        object.put(JSON_DESCRIPTION, mDescription);
        object.put(JSON_JOB_ID, mJobId);
        object.put(JSON_TYPE, mType);
        object.put(JSON_COMPLAINT_CATEGORY_ID, mComplaintCategoryId);
        object.put(JSON_COMPLAINT_CATEGORY, mComplaintCategory);
        return object;
    }

    public RequestParams buildParams() {
        HashMap<String, Object> object = new HashMap<>();
        object.put(JSON_ID, mId);
        object.put(JSON_DESCRIPTION, mDescription);
        object.put(JSON_TYPE, mType);
        object.put(JSON_COMPLAINT_CATEGORY_ID, mComplaintCategoryId);
        object.put(JSON_JOB_ID, mJobId);

        RequestParams params = new RequestParams();
        params.put(JSON_WRAPPER, object);
        return params;
    }

}
