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

package com.gruporaido.tasker_library.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gruporaido.tasker_library.R;
import com.gruporaido.tasker_library.model.Job;

//TODO this fragment shouldn't exist, move functionality to other place.
public class JobAddressFragment extends BaseFragment {

    private static final String TAG = "JobAddressFragment";

    public static final String EXTRA_JOB = "com.gruporaido.tasker.extra_job";

    protected Job mJob;

    protected TextView mAddressTextView;

    public static JobAddressFragment newInstance(Job job) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_JOB, job);

        JobAddressFragment fragment = new JobAddressFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mJob = (Job) getArguments().getSerializable(EXTRA_JOB);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_address, parent, false);

        mAddressTextView = (TextView) view.findViewById(R.id.job_card_addressTextView);

        drawLocationLayout();

        return view;
    }

    private void drawLocationLayout() {
        mAddressTextView.setText(mJob.getAddress());
    }

}
