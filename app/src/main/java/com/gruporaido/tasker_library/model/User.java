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

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import com.gruporaido.tasker_library.util.CachedBitmap;

public class User implements JSONable, Parcelable {

    public static final String JSON_WRAPPER = "user";
    public static final String JSON_ID = "id";
    public static final String JSON_GLOBAL_USER_ID = "globali   _user_id";
    public static final String JSON_ACCESS_TOKEN = "access_token";
    public static final String JSON_NAME = "name";
    public static final String JSON_LAST_NAMES = "last_names";
    public static final String JSON_EMAIL = "email";
    public static final String JSON_PHONE = "phone";
    public static final String JSON_TYPE = "type";
    public static final String JSON_AVAILABLE = "available";
    public static final String JSON_RATING = "rating";
    public static final String JSON_PASSWORD = "password";
    public static final String JSON_PASSWORD_CONFIRMATION = "password_confirmation";
    public static final String JSON_PHONE_CONFIRMATION_TOKEN = "phone_confirmation_token";
    public static final String JSON_PHOTO = "photo_url";
    public static final String JSON_PROFILE_PICTURE = "photo";
    public static final String JSON_USER_ID = "uid";
    public static final String JSON_AUTH_TOKEN = "auth_token";
    public static final String JSON_CARDS = "cards";
    public static final String JSON_PROMOTION = "promotion";

    public static final class Type {
        public static final String Client = "Client";
        public static final String Provider = "Provider";
        public static final HashMap<String, String> List = new HashMap<>();
    }

    private long mId;
    private String mGlobalUserId;
    private String mAccessToken;
    private String mName;
    private String mLastNames;
    private String mEmail;
    private String mPhone;
    private String mType;
    private boolean mAvailable;
    private String mPassword;
    private String mPasswordConfirmation;
    private String mPhoto;
    private String mPhoneConfirmationToken;
    private CachedBitmap mProfilePicture;
    private Long mUserId;
    private String mAuthToken;
    private Double mRating;
    private ArrayList<Card> mCards;
    private Promotion mPromotion;

    public User() {
        mId = -1;
        mGlobalUserId = null;
        mAccessToken = null;
        mName = null;
        mLastNames = null;
        mEmail = null;
        mPhone = null;
        mType = null;
        mAvailable = false;
        mPassword = null;
        mPasswordConfirmation = null;
        mPhoto = null;
        mProfilePicture = null;
        mRating = 0.0;
        mCards = null;
        mPromotion = null;
        mPhoneConfirmationToken = null;
        buildHashMap();
    }

    public User(Parcel in) {
        mId = in.readLong();
        mGlobalUserId = in.readString();
        mAccessToken = in.readString();
        mName = in.readString();
        mLastNames = in.readString();
        mEmail = in.readString();
        mPhone = in.readString();
        mType = in.readString();
        mAvailable = (Boolean) in.readValue(null);
        mPassword = in.readString();
        mPasswordConfirmation = in.readString();
        mPhoto = in.readString();
        mRating = in.readDouble();
    }

    public User(JSONObject json) throws JSONException {
        mName = json.getString(JSON_NAME);
        mLastNames = json.getString(JSON_LAST_NAMES);

        if (json.has(JSON_EMAIL) && !json.isNull(JSON_EMAIL)) {
            mEmail = json.getString(JSON_EMAIL);
        }

        if (json.has(JSON_PHONE) && !json.isNull(JSON_PHONE)) {
            mPhone = json.getString(JSON_PHONE);
        }

        if (json.has(JSON_TYPE) && !json.isNull(JSON_TYPE)) {
            mType = json.getString(JSON_TYPE);
        }

        if (json.has(JSON_AVAILABLE) && !json.isNull(JSON_AVAILABLE)) {
            mAvailable = json.getBoolean(JSON_AVAILABLE);
        }

        if (json.has(JSON_ID) && !json.isNull(JSON_ID)) {
            mId = json.getLong(JSON_ID);
        }
        if (json.has(JSON_GLOBAL_USER_ID) && !json.isNull(JSON_GLOBAL_USER_ID)) {
            mGlobalUserId = json.getString(JSON_GLOBAL_USER_ID);
        }

        if (json.has(JSON_ACCESS_TOKEN) && !json.isNull(JSON_ACCESS_TOKEN)) {
            mAccessToken = json.getString(JSON_ACCESS_TOKEN);
        }

        if (json.has(JSON_PASSWORD)) {
            mPassword = json.getString(JSON_PASSWORD);
        }

        if (json.has(JSON_PASSWORD_CONFIRMATION)) {
            mPasswordConfirmation = json.getString(JSON_PASSWORD_CONFIRMATION);
        }

        if (json.has(JSON_PHOTO)) {
            mPhoto = json.getString(JSON_PHOTO);
        }

        if (json.has(JSON_RATING)) {
            mRating = json.getDouble(JSON_RATING);
        }

        if (json.has(JSON_PROMOTION)) {
            try {
                mPromotion = new Promotion(json.getJSONObject(JSON_PROMOTION));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (json.has(JSON_AUTH_TOKEN) && !json.isNull(JSON_AUTH_TOKEN)) {
            mAuthToken = json.getString(JSON_AUTH_TOKEN);
        }

        if (json.has(JSON_USER_ID) && !json.isNull(JSON_USER_ID)) {
            mUserId = json.getLong(JSON_USER_ID);
        }

        buildHashMap();
    }

    private void buildHashMap() {
        Type.List.put(Type.Client, "Client");
        Type.List.put(Type.Provider, "Tasker");
    }

    public String getGlobalUserId() {
        return mGlobalUserId;
    }

    public void setGlobalUserId(String globalUserId) {
        mGlobalUserId = globalUserId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getLastNames() {
        return mLastNames;
    }

    public void setLastNames(String lastNames) {
        mLastNames = lastNames;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getType() {
        return mType;
    }

    public String getTypeText() {
        return Type.List.get(mType);
    }

    public void setType(String type) {
        mType = type;
    }

    public boolean isAvailable() {
        return mAvailable;
    }

    public void setAvailable(boolean available) {
        mAvailable = available;
    }

    public Double getRating() {
        return mRating;
    }

    public void setRating(Double rating) {
        mRating = rating;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getPasswordConfirmation() {
        return mPasswordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        mPasswordConfirmation = passwordConfirmation;
    }

    public String getPhoto() {
        return mPhoto;
    }

    public void setPhoto(String photo) {
        mPhoto = photo;
    }

    public Bitmap getProfilePicture() {
        if (mProfilePicture != null) {
            return mProfilePicture.getBitmap();
        }
        return null;
    }

    public void setProfilePicture(Bitmap profilePicture) {
        mProfilePicture = new CachedBitmap(profilePicture);
    }

    public Promotion getPromotion() {
        return mPromotion;
    }

    public void setPromotion(Promotion promotion) {
        mPromotion = promotion;
    }

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String accessToken) {
        mAccessToken = accessToken;
    }

    public Long getUserId() {
        return mUserId;
    }

    public void setUserId(Long userId) {
        mUserId = userId;
    }

    public String getAuthToken() {
        return mAuthToken;
    }

    public void setAuthToken(String authToken) {
        mAuthToken = authToken;
    }

    public String getPhoneConfirmationToken() {
        return mPhoneConfirmationToken;
    }

    public void setPhoneConfirmationToken(String phoneConfirmationToken) {
        mPhoneConfirmationToken = phoneConfirmationToken;
    }

    public boolean isClient() {
        return Type.Client.equals(mType);
    }

    public boolean isTasker() {
        return Type.Provider.equals(mType);
    }

    public boolean isEmpty() {
        return mId == -1 && mGlobalUserId == null;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(JSON_ID, mId);
        object.put(JSON_GLOBAL_USER_ID, mGlobalUserId);
        object.put(JSON_ACCESS_TOKEN, mAccessToken);
        object.put(JSON_NAME, mName);
        object.put(JSON_LAST_NAMES, mLastNames);
        object.put(JSON_EMAIL, mEmail);
        object.put(JSON_PHONE, mPhone);
        object.put(JSON_TYPE, mType);
        object.put(JSON_AVAILABLE, mAvailable);
        object.put(JSON_PASSWORD, mPassword);
        object.put(JSON_PASSWORD_CONFIRMATION, mPasswordConfirmation);
        object.put(JSON_PHOTO, mPhoto);
        object.put(JSON_RATING, mRating);
        object.put(JSON_USER_ID, mUserId);
        object.put(JSON_AUTH_TOKEN, mAuthToken);

        if (mPromotion != null) {
            object.put(JSON_PROMOTION, mPromotion.toJSON());
        }

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
        object.put(JSON_NAME, mName);
        object.put(JSON_LAST_NAMES, mLastNames);
        object.put(JSON_EMAIL, mEmail);
        object.put(JSON_PHONE, mPhone);
        object.put(JSON_PASSWORD, mPassword);
        object.put(JSON_PASSWORD_CONFIRMATION, mPasswordConfirmation);
        object.put(JSON_USER_ID, mUserId);
        object.put(JSON_AUTH_TOKEN, mAuthToken);
        object.put(JSON_PHONE_CONFIRMATION_TOKEN, mPhoneConfirmationToken);

        /*if (mProfilePicture != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            mProfilePicture.getBitmap().compress(Bitmap.CompressFormat.JPEG, 100, stream);
            params.put(JSON_PROFILE_PICTURE, new ByteArrayInputStream(stream.toByteArray()), "photo.jpg", "image/jpeg");
        }*/
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
        out.writeLong(mId);
        out.writeString(mGlobalUserId);
        out.writeString(mAccessToken);
        out.writeString(mName);
        out.writeString(mLastNames);
        out.writeString(mEmail);
        out.writeString(mPhone);
        out.writeString(mType);
        out.writeValue(mAvailable);
        out.writeString(mPassword);
        out.writeString(mPasswordConfirmation);
        out.writeString(mPhoto);
        out.writeDouble(mRating);
    }

    public static final Creator CREATOR = new Creator() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

}
