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

package com.gruporaido.tasker_library.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.gruporaido.tasker_library.R;
import com.gruporaido.tasker_library.model.Job;
import com.gruporaido.tasker_library.model.User;
import com.gruporaido.tasker_library.util.ApplicationComponent;
import com.gruporaido.tasker_library.util.Helper;

import java.lang.reflect.Field;

import javax.inject.Inject;

//TODO rewrite this so it doesn't make use of child fragments.
public class JobDetailsFragment extends BaseFragment {

    private static final String TAG = "JobDetailsFragment";

    public static final String EXTRA_JOB = "com.gruporaido.tasker_library.extra_job";
    public static final String EXTRA_ACTION = "com.gruporaido.tasker_library.extra_action";
    public static final String EXTRA_FLAGS = "com.gruporaido.tasker_library.extra_flags";

    public static class Flags {
        public static final int USER_TOP = 1;
        public static final int USER_BOTTOM = 2;
        public static final int JOB_BOTTOM = 4;
        public static final int TASKER = 8;
    }

    @Inject
    protected Helper mHelper;

    protected Job mJob;
    protected User mUser;
    protected int mFlags;

    protected TextView mIdentityTextView;
    protected FrameLayout mTopLayout;
    protected FrameLayout mBottomLayout;

    public static JobDetailsFragment newInstance(Job job, int flags) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_JOB, job);
        bundle.putInt(EXTRA_FLAGS, flags);

        JobDetailsFragment fragment = new JobDetailsFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void injectFragment(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mJob = (Job) getArguments().getSerializable(EXTRA_JOB);
        //TODO change solution to parcelable error
        getArguments().remove(EXTRA_ACTION);
        mUser = mJob.getUser();
        mFlags = getArguments().getInt(EXTRA_FLAGS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_job_details, parent, false);

        mIdentityTextView = (TextView) view.findViewById(R.id.job_details_identityTextView);
        mTopLayout = (FrameLayout) view.findViewById(R.id.job_details_topLayout);
        mBottomLayout = (FrameLayout) view.findViewById(R.id.job_details_bottomLayout);

        drawLocationLayout();

        return view;
    }

    private void drawLocationLayout() {
        if (mJob.getJobCategory() != null) {
            mIdentityTextView.setText(mJob.getJobCategory().getName());
        }

        if (mHelper.isFlagActivated(mFlags, Flags.USER_TOP)) {
            mTopLayout.setVisibility(View.VISIBLE);
            final int flags;
            if (mHelper.isFlagActivated(mFlags, Flags.TASKER)) {
                flags = UserCardFragment.Flags.TYPE;
            } else {
                flags = UserCardFragment.Flags.RATINGS;
            }
            inflateFragment(R.id.job_details_topLayout, new Helper.FragmentCreator() {
                @Override
                public Fragment createFragment() {
                    return UserCardFragment.newInstance(mUser, flags);
                }
            }, R.animator.no_animation, R.animator.no_animation);
        }

        if (mHelper.isFlagActivated(mFlags, Flags.USER_BOTTOM)) {
            mBottomLayout.setVisibility(View.VISIBLE);
            inflateFragment(R.id.job_details_bottomLayout, new Helper.FragmentCreator() {
                @Override
                public Fragment createFragment() {
                    return UserCardFragment.newInstance(mUser, UserCardFragment.Flags.TYPE);
                }
            }, R.animator.no_animation, R.animator.no_animation);
            if (!mHelper.isFlagActivated(mFlags, Flags.TASKER)) {
                mIdentityTextView.setBackgroundResource(R.drawable.bottom_border);
                mIdentityTextView.setPadding(0, 0, 0, mHelper.dpToPx(8));
            }
        }

        if (mHelper.isFlagActivated(mFlags, Flags.JOB_BOTTOM)) {
            mBottomLayout.setVisibility(View.VISIBLE);
            inflateFragment(R.id.job_details_bottomLayout, new Helper.FragmentCreator() {
                @Override
                public Fragment createFragment() {
                    return JobAddressFragment.newInstance(mJob);
                }
            }, R.animator.no_animation, R.animator.no_animation);
            mIdentityTextView.setBackgroundResource(R.drawable.bottom_border);
            mIdentityTextView.setPadding(0, 0, 0, mHelper.dpToPx(8));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
