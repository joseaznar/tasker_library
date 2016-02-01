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
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gruporaido.tasker_library.R;
import com.gruporaido.tasker_library.model.Job;

//TODO refactor this class, it's
public class JobCardFragment extends BaseFragment {

    private static final String TAG = "JobCardFragment";

    public static final String EXTRA_JOB = "com.gruporaido.tasker.extra_job";

    protected Job mJob;

    protected ImageView mLocationImageView;
    protected TextView mStateTextView;
    protected TextView mJobCategoryTextView;
    protected TextView mJobTypeTextView;
    protected TextView mAddressTextView;
    protected TextView mDescriptionTextView;
    protected LinearLayout mDescriptionLayout;
    protected TextView mExteriorNumberTextView;
    protected TextView mInteriorNumberTextView;
    protected TextView mInteriorNumberLabelTextView;

    public static JobCardFragment newInstance(Job job) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_JOB, job);

        JobCardFragment fragment = new JobCardFragment();
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
        final View view = inflater.inflate(R.layout.fragment_job_card, parent, false);

        mLocationImageView = (ImageView) view.findViewById(R.id.job_card_locationImageView);

        mStateTextView = (TextView) view.findViewById(R.id.job_card_stateTextView);
        //TODO add localization to job state
        mStateTextView.setText(mJob.getState());

        mJobCategoryTextView = (TextView) view.findViewById(R.id.job_card_jobCategoryTextView);
        mJobCategoryTextView.setText(mJob.getJobCategory().getName());

        mJobTypeTextView = (TextView) view.findViewById(R.id.job_card_jobTypeTextView);
        mJobTypeTextView.setText(mJob.getJobType().getName());

        mDescriptionLayout = (LinearLayout) view.findViewById(R.id.job_card_jobDescriptionLayout);
        mDescriptionTextView = (TextView) view.findViewById(R.id.job_card_jobDescriptionTextView);
        if (mJob.getDescription() != null && !mJob.getDescription().isEmpty()) {
            mDescriptionTextView.setText(mJob.getDescription());
        } else {
            mDescriptionLayout.setVisibility(View.GONE);
        }

        mExteriorNumberTextView = (TextView) view.findViewById(R.id.job_card_exteriorNumberTextView);
        mExteriorNumberTextView.setText(mJob.getExteriorNumber());

        mInteriorNumberTextView = (TextView) view.findViewById(R.id.job_card_interiorNumberTextView);
        mInteriorNumberLabelTextView = (TextView) view.findViewById(R.id.job_card_interiorNumberLabelTextView);
        if (mJob.getInteriorNumber() != null && !mJob.getInteriorNumber().isEmpty()) {
            mInteriorNumberLabelTextView.setVisibility(View.VISIBLE);
            mInteriorNumberTextView.setVisibility(View.VISIBLE);
            mInteriorNumberTextView.setText(mJob.getInteriorNumber());
        }

        mAddressTextView = (TextView) view.findViewById(R.id.job_card_addressTextView);
        mAddressTextView.setText(mJob.getAddress());

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //TODO change this to own class and Builder Pattern
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int width = view.getWidth();
                int height = width / 3;
                String mapsUrl = "https://maps.googleapis.com/maps/api/staticmap?center=" + mJob.getLatitude() + "," + mJob.getLongitude() + "&zoom=15&size=" + width + "x" + height + "&scale=1";
                Glide.with(getActivity()).load(mapsUrl).crossFade().into(mLocationImageView);
            }
        });

        return view;
    }

}