/**
 * GRUPO RAIDO CONFIDENTIAL
 * __________________
 * <p>
 * [2015] - [2015] Grupo Raido SAPI de CV
 * All Rights Reserved.
 * <p>
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

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Job implements JSONable {

    public static final String JSON_WRAPPER = "job";
    public static final String JSON_ID = "id";
    public static final String JSON_CARD_ID = "card_id";
    public static final String JSON_LATITUDE = "latitude";
    public static final String JSON_LONGITUDE = "longitude";
    public static final String JSON_STATE = "state";
    public static final String JSON_DESCRIPTION = "description";
    public static final String JSON_INTERIOR_NUMBER = "interior_number";
    public static final String JSON_EXTERIOR_NUMBER = "exterior_number";
    public static final String JSON_ADDRESS = "address";
    public static final String JSON_USER = "user";
    public static final String JSON_JOB_TYPE_ID = "job_type_id";
    public static final String JSON_JOB_TYPE = "job_type";
    public static final String JSON_JOB_CATEGORY = "job_category";
    public static final String JSON_LAST_LOCATION = "last_location";
    public static final String JSON_QUOTE = "quote";
    public static final String JSON_FINISHED_AT = "finished_at";

    protected Integer mId;
    protected Integer mCardId;
    protected Double mLatitude;
    protected Double mLongitude;
    protected String mState;
    protected String mDescription;
    private String mInteriorNumber;
    protected String mExteriorNumber;
    protected String mAddress;
    protected User mUser;
    protected Location mLastLocation;
    protected JobType mJobType;
    protected JobCategory mJobCategory;
    protected Quote mQuote;
    protected Calendar mFinishedAt;

    private boolean mValid;

    public static class State {
        public static final String Requested = "requested";
        public static final String Incoming = "incoming";
        public static final String Quoting = "quoting";
        public static final String QuoteAcceptance = "quote_acceptance";
        public static final String Working = "working";
        public static final String Rating = "rating";
        public static final String Finished = "finished";
        public static final String Rejected = "rejected";
        public static final String UserCancelled = "user_cancelled";
        public static final String ProviderCancelled = "provider_cancelled";
        public static final String Failed = "failed";
    }

    public Job() {
        mId = null;
        mCardId = null;
        mLatitude = null;
        mLongitude = null;
        mState = null;
        mDescription = null;
        mInteriorNumber = null;
        mExteriorNumber = null;
        mAddress = null;
        mValid = false;
        mUser = null;
        mLastLocation = null;
        mJobType = null;
        mJobCategory = null;
        mQuote = null;
        mFinishedAt = new GregorianCalendar();
    }

    public Job(JSONObject json) throws JSONException {
        if (json.has(JSON_LATITUDE) && !json.isNull(JSON_LATITUDE)) {
            mLatitude = json.getDouble(JSON_LATITUDE);
        }

        if (json.has(JSON_LONGITUDE) && !json.isNull(JSON_LONGITUDE)) {
            mLongitude = json.getDouble(JSON_LONGITUDE);
        }

        if (json.has(JSON_STATE) && !json.isNull(JSON_STATE)) {
            mState = json.getString(JSON_STATE);
        }

        if (json.has(JSON_ADDRESS)) {
            mAddress = json.getString(JSON_ADDRESS);
        }

        if (json.has(JSON_ID)) {
            mId = json.getInt(JSON_ID);
        }

        if (json.has(JSON_CARD_ID)) {
            mCardId = json.getInt(JSON_CARD_ID);
        }

        if (json.has(JSON_DESCRIPTION)) {
            mDescription = json.getString(JSON_DESCRIPTION);
        }

        if (json.has(JSON_EXTERIOR_NUMBER)) {
            mExteriorNumber = json.getString(JSON_EXTERIOR_NUMBER);
        }

        if (json.has(JSON_INTERIOR_NUMBER)) {
            mInteriorNumber = json.getString(JSON_INTERIOR_NUMBER);
        }

        if (json.has(JSON_USER) && !json.isNull(JSON_USER)) {
            mUser = new User(json.getJSONObject(JSON_USER));
        }

        if (json.has(JSON_LAST_LOCATION)) {
            mLastLocation = new Location(json.getJSONObject(JSON_LAST_LOCATION));
        }

        if (json.has(JSON_JOB_TYPE) && !json.isNull(JSON_JOB_TYPE)) {
            mJobType = new JobType(json.getJSONObject(JSON_JOB_TYPE));
        }

        if (json.has(JSON_JOB_CATEGORY) && !json.isNull(JSON_JOB_CATEGORY)) {
            mJobCategory = new JobCategory(json.getJSONObject(JSON_JOB_CATEGORY));
        }

        if (json.has(JSON_QUOTE) && !json.isNull(JSON_QUOTE)) {
            mQuote = new Quote(json.getJSONObject(JSON_QUOTE));
        }

        mValid = false;
    }

    public Integer getId() {
        return mId;
    }

    public Integer getCardId() {
        return mCardId;
    }

    public void setCardId(Integer cardId) {
        mCardId = cardId;
    }

    public Double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public Double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getInteriorNumber() {
        return mInteriorNumber;
    }

    public void setInteriorNumber(String interiorNumber) {
        mInteriorNumber = interiorNumber;
    }

    public String getExteriorNumber() {
        return mExteriorNumber;
    }

    public void setExteriorNumber(String exteriorNumber) {
        mExteriorNumber = exteriorNumber;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public Location getLastLocation() {
        return mLastLocation;
    }

    public void setLastLocation(Location location) {
        mLastLocation = location;
    }

    public JobType getJobType() {
        return mJobType;
    }

    public void setJobType(JobType jobType) {
        mJobType = jobType;
    }

    public JobCategory getJobCategory() {
        return mJobCategory;
    }

    public void setJobCategory(JobCategory jobCategory) {
        mJobCategory = jobCategory;
    }

    public Quote getQuote() {
        return mQuote;
    }

    public void setQuote(Quote quote) {
        mQuote = quote;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public void setValid(boolean valid) {
        mValid = valid;
    }

    public boolean isValid() {
        return mValid && mJobType != null;
    }

    public boolean isEmpty() {
        return mId == null;
    }

    public boolean isNew() {
        return mId == null;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(JSON_ID, mId);
        object.put(JSON_CARD_ID, mCardId);
        object.put(JSON_LATITUDE, mLatitude);
        object.put(JSON_LONGITUDE, mLongitude);
        object.put(JSON_DESCRIPTION, mDescription);
        object.put(JSON_STATE, mState);
        object.put(JSON_ADDRESS, mAddress);
        object.put(JSON_INTERIOR_NUMBER, mInteriorNumber);
        object.put(JSON_EXTERIOR_NUMBER, mExteriorNumber);
        object.put(JSON_ADDRESS, mAddress);
        if (mJobType != null) object.put(JSON_JOB_TYPE, mJobType.toJSON());
        if (mJobCategory != null) object.put(JSON_JOB_CATEGORY, mJobCategory.toJSON());
        if (mUser != null) object.put(JSON_USER, mUser.toJSON());
        if (mLastLocation != null) object.put(JSON_LAST_LOCATION, mLastLocation.toJSON());
        if (mQuote != null) object.put(JSON_QUOTE, mQuote.toJSON());
        return object;
    }

    public RequestParams buildParams() {
        HashMap<String, Object> object = new HashMap<>();
        object.put(JSON_ID, mId);
        object.put(JSON_DESCRIPTION, mDescription);
        object.put(JSON_CARD_ID, mCardId);
        object.put(JSON_LATITUDE, mLatitude);
        object.put(JSON_LONGITUDE, mLongitude);
        object.put(JSON_ADDRESS, mAddress);
        object.put(JSON_EXTERIOR_NUMBER, mExteriorNumber);
        object.put(JSON_INTERIOR_NUMBER, mInteriorNumber);
        if (mJobType != null) object.put(JSON_JOB_TYPE_ID, mJobType.getId());

        RequestParams params = new RequestParams();
        params.put(JSON_WRAPPER, object);
        return params;
    }
}
