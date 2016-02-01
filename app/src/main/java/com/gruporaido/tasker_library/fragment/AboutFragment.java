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
import android.text.Html;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gruporaido.tasker_library.R;
import com.gruporaido.tasker_library.util.ApplicationComponent;

public class AboutFragment extends BaseFragment {

    private static final String TAG = "AboutFragment";

    protected TextView mVersionTextView;
    protected TextView mTrademarkTextView;
    protected TextView mLicensesTextView;
    protected TextView mTermsTextView;
    protected TextView mPrivacyTextView;

    @Override
    public void injectFragment(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, parent, false);

        String versionName = "----";
        try {
            versionName = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        mVersionTextView = (TextView) view.findViewById(R.id.about_versionTextView);
        mVersionTextView.setText(versionName);

        mTrademarkTextView = (TextView) view.findViewById(R.id.about_trademarkTextView);
        mTrademarkTextView.setText(Html.fromHtml(getString(R.string.about_trademark)));

        mLicensesTextView = (TextView) view.findViewById(R.id.about_licensesTextView);
        mLicensesTextView.setText(Html.fromHtml(getString(R.string.about_licenses)));
        mLicensesTextView.setMovementMethod(LinkMovementMethod.getInstance());
        stripUnderlines(mLicensesTextView);

        mTermsTextView = (TextView) view.findViewById(R.id.about_termsTextView);
        mTermsTextView.setText(Html.fromHtml(getString(R.string.about_terms)));
        mTermsTextView.setMovementMethod(LinkMovementMethod.getInstance());
        stripUnderlines(mTermsTextView);

        mPrivacyTextView = (TextView) view.findViewById(R.id.about_privacyTextView);
        mPrivacyTextView.setText(Html.fromHtml(getString(R.string.about_privacy)));
        mPrivacyTextView.setMovementMethod(LinkMovementMethod.getInstance());
        stripUnderlines(mPrivacyTextView);

        return view;
    }

    protected void stripUnderlines(TextView textView) {
        Spannable s = (Spannable) textView.getText();
        URLSpan[] spans = s.getSpans(0, s.length(), URLSpan.class);
        for (URLSpan span : spans) {
            int start = s.getSpanStart(span);
            int end = s.getSpanEnd(span);
            s.removeSpan(span);
            span = new URLSpanNoUnderline(span.getURL());
            s.setSpan(span, start, end, 0);
        }
        textView.setText(s);
    }

    protected class URLSpanNoUnderline extends URLSpan {
        public URLSpanNoUnderline(String url) {
            super(url);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(false);
        }
    }
}
