/**
 * GRUPO RAIDO CONFIDENTIAL
 * __________________
 * <p/>
 * [2015] - [2015] Grupo Raido Incorporated
 * All Rights Reserved.
 * <p/>
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
import android.support.annotation.NonNull;
import android.view.View;

import com.dd.CircularProgressButton;
import com.gruporaido.tasker_library.R;
import com.gruporaido.tasker_library.http.APIResponseHandler;
import com.rey.material.app.Dialog;
import com.rey.material.app.DialogFragment;
import com.rey.material.app.SimpleDialog;


public class FailedConnectionDialogFragment extends DialogFragment {

    protected static final String EXTRA_RESPONSE_HANDLER = "com.gruporaido.tasker.response_handler";

    protected CircularProgressButton mActionButton;
    protected APIResponseHandler mAPIResponseHandler;

    public static FailedConnectionDialogFragment newInstance(APIResponseHandler handler) {
        Bundle arguments = new Bundle();
        arguments.putSerializable(EXTRA_RESPONSE_HANDLER, handler);

        FailedConnectionDialogFragment fragment = new FailedConnectionDialogFragment();
        fragment.setArguments(arguments);

        SimpleDialog.Builder builder = new SimpleDialog.Builder();
        builder.style(R.style.SimpleDialogLight)
                .contentView(R.layout.fragment_dialog_failed_connection);

        fragment.setCancelable(false);
        fragment.mBuilder = builder;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAPIResponseHandler = (APIResponseHandler) getArguments().getSerializable(EXTRA_RESPONSE_HANDLER);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = this.mBuilder.build(this.getActivity());

        mActionButton = (CircularProgressButton) dialog.findViewById(R.id.failed_connection_actionButton);
        mActionButton.setIndeterminateProgressMode(true);
        mActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lockView();
                mAPIResponseHandler.onRetry();
                dismiss();
            }
        });

        return dialog;
    }

    private void lockView() {
        mActionButton.setProgress(0);
        mActionButton.setProgress(1);
    }

    public interface Callbacks {
        void onRetry();
    }

}

