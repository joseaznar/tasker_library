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

import org.json.JSONException;
import org.json.JSONObject;

public class ComplaintCategory implements JSONable {

    public static final String JSON_WRAPPER = "complaint_category";
    public static final String JSON_ID = "id";
    public static final String JSON_NAME = "name";
    public static final String JSON_ICON = "icon";

    protected long mId;
    protected String mName;
    protected String mIcon;

    public ComplaintCategory() {
        mId = -1;
        mName = null;
        mIcon = null;
    }

    public ComplaintCategory(JSONObject json) throws JSONException {
        mId = json.getLong(JSON_ID);
        mName = json.getString(JSON_NAME);
        if (json.has(JSON_ICON) && !json.isNull(JSON_ICON)) {
            mIcon = json.getString(JSON_ICON);
        }
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

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject object = new JSONObject();
        object.put(JSON_ID, mId);
        object.put(JSON_NAME, mName);
        object.put(JSON_ICON, mIcon);
        return object;
    }

}
