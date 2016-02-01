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

package com.gruporaido.tasker_library.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.gruporaido.tasker_library.util.ApplicationComponent;
import com.squareup.leakcanary.RefWatcher;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import com.gruporaido.tasker_library.TaskerLibrary;
import com.gruporaido.tasker_library.util.Helper;

public abstract class BaseFragment extends DaggerFragment {

    @Inject
    protected Bus mBus;

    @Inject
    protected Helper mHelper;

    @Override
    public void injectFragment(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBus.register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBus.unregister(this);
        TaskerLibrary taskerLibrary = ((TaskerLibrary) getContext().getApplicationContext());
        RefWatcher refWatcher = taskerLibrary.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }

    protected void inflateFragment(int resourceId, Helper.FragmentCreator creator, int inAnimation, int outAnimation) {
        mHelper.inflateFragment(getChildFragmentManager(), resourceId, creator, inAnimation, outAnimation);
    }

    protected void removeFragment(int resourceId) {
        mHelper.removeFragment(getChildFragmentManager(), resourceId);
    }
}