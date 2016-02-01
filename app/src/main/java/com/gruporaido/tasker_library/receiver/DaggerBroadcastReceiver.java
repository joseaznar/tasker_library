package com.gruporaido.tasker_library.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.gruporaido.tasker_library.TaskerLibrary;
import com.gruporaido.tasker_library.util.ApplicationComponent;

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
public abstract class DaggerBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ApplicationComponent component = getComponent(context);
        injectReceiver(component);
    }

    protected ApplicationComponent getComponent(Context context) {
        return ((TaskerLibrary) context.getApplicationContext()).getComponent();
    }

    public abstract void injectReceiver(ApplicationComponent component);
}
