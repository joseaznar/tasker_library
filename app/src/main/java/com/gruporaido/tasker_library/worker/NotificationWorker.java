/**
 * GRUPO RAIDO CONFIDENTIAL
 * __________________
 *
 * [2015] - [2015] Grupo Raido Incorporated
 * All Rights Reserved.
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

package com.gruporaido.tasker_library.worker;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.google.android.gms.gcm.Task;
import com.gruporaido.tasker_library.R;
import com.gruporaido.tasker_library.TaskerLibrary;
import com.gruporaido.tasker_library.model.Job;
import com.gruporaido.tasker_library.model.Location;
import com.gruporaido.tasker_library.model.Request;
import com.gruporaido.tasker_library.util.ApplicationComponent;
import com.squareup.otto.Bus;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

public class NotificationWorker {

    public static final int NOTIFICATION_ID = 10;

    public static class Type {
        public static final String Request = "request";
        public static final String Incoming = "incoming";
        public static final String Quoting = "quoting";
        public static final String QuoteAccepted = "quote_accepted";
        public static final String Working = "working";
        public static final String Rating = "rating";
        public static final String Finished = "finished";
        public static final String Rejected = "rejected";
        public static final String Cancelled = "cancelled";
        public static final String Failed = "failed";
        public static final String Request_Cancelled = "request_cancelled";
        public static final String Location = "location";
    }

    @Inject
    protected Bus mBus;

    protected Context mContext;
    protected Intent mIntent;
    protected Bundle mExtras;
    protected boolean mIsInForeground;
    protected Intent mResultIntent;

    public NotificationWorker(Context context, Intent intent, boolean isInForeground) {
        mContext = context;
        mIntent = intent;
        mExtras = intent.getExtras();
        mIsInForeground = isInForeground;

        TaskerLibrary application = (TaskerLibrary) context.getApplicationContext();
        injectWorker(application.getComponent());
    }

    public void injectWorker(ApplicationComponent component) {
        component.inject(this);
    }

    public void call() {
        call(getType());
    }

    public void call(String type) {

    }

    protected void finish() {
        if (mResultIntent != null && !mIsInForeground) {
            buildNotification();
        }
    }

    protected String getType() {
        return mExtras.getString("type");
    }

    protected void buildNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(mExtras.getString("title"))
                .setContentText(mExtras.getString("message"))
                .setPriority(Notification.PRIORITY_MAX);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(mContext);
        stackBuilder.addNextIntent(mResultIntent);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

    protected void launchActivityWithJob(Class klass, String key, Job job) throws Exception {
        checkResultIntentEmpty();
        mResultIntent = new Intent(mContext, klass);
        mResultIntent.putExtra(key, job);
    }

    protected void launchActivityWithRequest(Class klass, String key, Request request) throws Exception {
        checkResultIntentEmpty();
        mResultIntent = new Intent(mContext, klass);
        mResultIntent.putExtra(key, request);
    }

    protected void checkResultIntentEmpty() throws Exception {
        if (mResultIntent != null) {
            throw new Exception("Result intent already has a value. You are launching an activity twice.");
        }
    }

    protected Job getJob() throws JSONException {
        return new Job(new JSONObject(mExtras.getString(Job.JSON_WRAPPER)));
    }

    protected Request getRequest() throws JSONException {
        return new Request(new JSONObject(mExtras.getString(Request.JSON_WRAPPER)));
    }

    protected Location getLocation() throws JSONException {
        return new Location(new JSONObject(mExtras.getString(Location.JSON_WRAPPER)));
    }
}
