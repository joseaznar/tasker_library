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

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.gruporaido.tasker_library.R;
import com.gruporaido.tasker_library.model.User;
import com.gruporaido.tasker_library.util.ApplicationComponent;


public class SupportFragment extends BaseFragment {

    private static final String TAG = "SupportFragment";
    public static final String EXTRA_USER = "com.gruporaido.tasker_library.user";

    protected Button mPhoneButton;
    protected Button mEmailButton;

    protected User mUser;

    public static SupportFragment newInstance(User user) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(EXTRA_USER, user);

        SupportFragment fragment = new SupportFragment();
        fragment.setArguments(arguments);

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_support, parent, false);

        mPhoneButton = (Button) view.findViewById(R.id.support_phoneButton);
        mPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + getString(R.string.support_phone)));
                startActivity(callIntent);
            }
        });

        mEmailButton = (Button) view.findViewById(R.id.support_emailButton);
        mEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = buildEmailIntent(
                        "Tasker - Soporte",
                        "ID de usuario: " + mUser.getGlobalUserId());
                try {
                    startActivity(Intent.createChooser(intent, "Send email..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getActivity(), "No tienes .", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    protected Intent buildEmailIntent(String subject, String content) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.support_email)});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Tasker - Soporte");
        intent.putExtra(Intent.EXTRA_TEXT, "ID de usuario: " + mUser.getGlobalUserId());
        return intent;
    }
}
