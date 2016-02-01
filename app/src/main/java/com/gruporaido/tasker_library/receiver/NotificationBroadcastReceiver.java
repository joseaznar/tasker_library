/**
 * GRUPO RAIDO CONFIDENTIAL
 * __________________
 *
 * [2015] - [2015] Grupo Raido SAPI de CV
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

package com.gruporaido.tasker_library.receiver;

import android.content.Context;
import android.content.Intent;
import com.gruporaido.tasker_library.util.ApplicationComponent;
import com.gruporaido.tasker_library.worker.NotificationWorker;

public abstract class NotificationBroadcastReceiver extends DaggerBroadcastReceiver {

    public static final String REFRESH_DATA_INTENT = "com.gruporaido.tasker_library.refresh_data_intent";

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationWorker worker = new NotificationWorker(context, intent, true);
        worker.call();
    }
}
