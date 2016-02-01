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

import org.json.JSONException;
import org.json.JSONObject;

public class JobCategory implements JSONable, Parcelable {

    public static final String JSON_WRAPPER = "job_category";
    public static final String JSON_ID = "id";
    public static final String JSON_NAME = "name";
    public static final String JSON_DESCRIPTION = "description";
    public static final String JSON_ICON = "icon";
    public static final String JSON_ICON_URL = "icon_url";
    public static final String JSON_ICON_ALT_URL = "icon_alt_url";

    protected long mId;
    protected String mName;
    protected String mDescription;
    protected String mIcon;
    protected String mIconUrl;
    protected String mIconAltUrl;

    protected Bitmap mIconBitmap;
    protected Bitmap mIconAltBitmap;

    public JobCategory() {
        mId = -1;
        mName = null;
        mDescription = null;
        mIcon = null;
        mIconUrl = null;
        mIconAltUrl = null;
        mIconBitmap = null;
        mIconAltBitmap = null;
    }

    public JobCategory(JSONObject json) throws JSONException {
        if (json.has(JSON_ID)) {
            mId = json.getLong(JSON_ID);
        }

        if (json.has(JSON_NAME)) {
            mName = json.getString(JSON_NAME);
        }

        if (json.has(JSON_DESCRIPTION)) {
            mDescription = json.getString(JSON_DESCRIPTION);
        }

        if (json.has(JSON_ICON) && !json.isNull(JSON_ICON)) {
            mIcon = json.getString(JSON_ICON);
        }

        if (json.has(JSON_ICON_URL) && !json.isNull(JSON_ICON_URL)) {
            mIconUrl = json.getString(JSON_ICON_URL);
        }

        if (json.has(JSON_ICON_ALT_URL) && !json.isNull(JSON_ICON_ALT_URL)) {
            mIconAltUrl = json.getString(JSON_ICON_ALT_URL);
        }
    }

    public JobCategory(Parcel in) {
        mId = in.readLong();
        mName = in.readString();
        mDescription = in.readString();
        mIcon = in.readString();
        mIconUrl = in.readString();
        mIconAltUrl = in.readString();
        //mIconBitmap = Bitmap.CREATOR.createFromParcel(in);
        //mIconAltBitmap = Bitmap.CREATOR.createFromParcel(in);
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

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getIconUrl() {
        return mIconUrl;
    }

    public void setIconUrl(String iconUrl) {
        mIconUrl = iconUrl;
    }

    public String getIconAltUrl() {
        return mIconAltUrl;
    }

    public void setIconAltUrl(String iconAltUrl) {
        mIconAltUrl = iconAltUrl;
    }

    public Bitmap getIconBitmap() {
        return mIconBitmap;
    }

    public void setIconBitmap(Bitmap iconBitmap) {
        mIconBitmap = iconBitmap;
    }

    public Bitmap getIconErrorBitmap() {
        return mIconAltBitmap;
    }

    public void setIconErrorBitmap(Bitmap iconErrorBitmap) {
        mIconAltBitmap = iconErrorBitmap;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(JSON_ID, mId);
        object.put(JSON_NAME, mName);
        object.put(JSON_DESCRIPTION, mDescription);
        object.put(JSON_ICON, mIcon);
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
        out.writeString(mDescription);
        out.writeString(mIcon);
        out.writeString(mIconUrl);
        out.writeString(mIconAltUrl);
        if (mIconBitmap != null) mIconBitmap.writeToParcel(out, 0);
        if (mIconAltBitmap != null) mIconAltBitmap.writeToParcel(out, 0);
    }

    public static final Creator CREATOR = new Creator() {
        public JobCategory createFromParcel(Parcel in) {
            return new JobCategory(in);
        }

        public JobCategory [] newArray(int size) {
            return new JobCategory[size];
        }
    };

}