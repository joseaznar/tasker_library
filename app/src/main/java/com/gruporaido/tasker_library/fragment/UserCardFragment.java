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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gruporaido.tasker_library.R;
import com.gruporaido.tasker_library.model.User;
import com.gruporaido.tasker_library.util.ApplicationComponent;
import com.gruporaido.tasker_library.util.Helper;

import javax.inject.Inject;

public class UserCardFragment extends BaseFragment {

    private static final String TAG = "UserDetailsFragment";

    public static final String EXTRA_USER = "com.gruporaido.tasker.extra_user";
    public static final String EXTRA_FLAGS = "com.gruporaido.tasker.extra_flags";

    @Inject
    protected Helper mHelper;

    protected User mUser;
    protected int mFlags;

    protected TextView mUserIdentityTextView;
    protected TextView mUserNameTextView;
    protected ImageView mUserPhotoImageView;
    protected LinearLayout mUserRatingLayout;

    public static class Flags {
        public static final int TYPE = 1;
        public static final int RATINGS = 2;
    }

    public static UserCardFragment newInstance(User user, int flags) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_USER, user);
        bundle.putInt(EXTRA_FLAGS, flags);

        UserCardFragment fragment = new UserCardFragment();
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
        mUser = (User) getArguments().getSerializable(EXTRA_USER);
        mFlags = getArguments().getInt(EXTRA_FLAGS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_card, parent, false);

        mUserIdentityTextView = (TextView) view.findViewById(R.id.user_card_userIdentityTextView);
        mUserNameTextView = (TextView) view.findViewById(R.id.user_card_userNameTextView);
        mUserPhotoImageView = (ImageView) view.findViewById(R.id.user_card_userPhotoImageView);
        mUserRatingLayout = (LinearLayout) view.findViewById(R.id.user_card_userRatingLayout);

        Glide.with(getActivity()).load(mUser.getPhoto()).crossFade().into(mUserPhotoImageView);

        drawLocationLayout();

        return view;
    }

    private void drawLocationLayout() {
        if (mUser == null) return;

        mUserNameTextView.setText(mUser.getName() + " " + mUser.getLastNames());
        if (mUser.getPhoto() != null) {
            Glide.with(getActivity()).load(mUser.getPhoto()).crossFade().into(mUserPhotoImageView);
        }

        if (mHelper.isFlagActivated(mFlags, Flags.TYPE)) {
            mUserIdentityTextView.setVisibility(View.VISIBLE);
            mUserIdentityTextView.setText(mUser.getTypeText());
        }

        if (mHelper.isFlagActivated(mFlags, Flags.RATINGS)) {
            Integer rating = mUser == null ? 0 : mUser.getRating().intValue();
            for (int i = 0; i < rating; ++i) {
                mUserRatingLayout.addView(createImageView(R.mipmap.ic_star));
            }

            for (int i = rating; i < 5; ++i) {
                mUserRatingLayout.addView(createImageView(R.mipmap.ic_starline));
            }
        }

    }

    private ImageView createImageView(int resourceId) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(resourceId);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 16, 0);
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        imageView.setLayoutParams(layoutParams);
        return imageView;
    }

}
