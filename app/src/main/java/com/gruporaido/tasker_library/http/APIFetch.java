/*
 * [2015] - [2015] Grupo Raido SAPI de CV.
 * All Rights Reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * Created by Pablo Cárdenas on 25/10/15.
 */

package com.gruporaido.tasker_library.http;

import android.content.Context;
import android.os.Looper;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import com.gruporaido.tasker_library.R;


/**
 * This class provides services related to doing API requests
 * to some Restful back-end.
 */
public class APIFetch {
    protected String mProtocol = "http";
    protected String mDomainName;
    protected String mEndpoint;

    protected Context mContext;

    public APIFetch(Context context) {
        mContext = context;
        mDomainName = context.getString(R.string.domain);
        mEndpoint = context.getString(R.string.end_point);
    }

    /**
     * Builds a URL relative to the {@link #mDomainName}
     *
     * @param url
     * @return
     */
    protected String getAbsoluteUrl(String url) {

        Log.d("APIFetch", mProtocol + "://" + mDomainName + "/" + mEndpoint + url);
        return mProtocol + "://" + mDomainName + "/" + url;
    }

    /**
     * Same as {@link #getAbsoluteUrl(String)} but relative to {@link #mEndpoint}
     *
     * @param url
     * @return
     */
    protected String getApiAbsoluteUrl(String url) {
        Log.d("APIFetch", mProtocol + "://" + mDomainName + "/" + mEndpoint + url);
        return mProtocol + "://" + mDomainName + "/" + mEndpoint + url;
    }

    /**
     * Do a GET HTTP request relative to {@link #getApiAbsoluteUrl(String)}.
     *
     * @param url
     * @param params
     * @param responseHandler
     */
    public void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        getClient().get(getApiAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * Download a file from an absolute URL.
     *
     * @param url
     * @param params
     * @param responseHandler
     */
    public void getAbsoluteFile(String url, RequestParams params, FileAsyncHttpResponseHandler responseHandler) {
        getClient(false).get(url, params, responseHandler);
    }

    /**
     * Same as {@link #getAbsoluteFile(String, RequestParams, FileAsyncHttpResponseHandler)} but relative
     * to {@link #getApiAbsoluteUrl(String)}.
     *
     * @param url
     * @param params
     * @param responseHandler
     */
    public void getFile(String url, RequestParams params, FileAsyncHttpResponseHandler responseHandler) {
        getClient().get(getAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * Same as {@link #get(String, RequestParams, AsyncHttpResponseHandler)} but for the POST HTTP verb.
     *
     * @param url
     * @param params
     * @param responseHandler
     */
    public void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        getClient().post(getApiAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * Same as {@link #post(String, RequestParams, AsyncHttpResponseHandler)} with an extra option for
     * request timeout specification.
     *
     * @param url
     * @param params
     * @param responseHandler
     * @param timeOut
     */
    public void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler, int timeOut) {
        AsyncHttpClient client = getClient();
        client.setTimeout(timeOut);
        client.post(getApiAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * Same as {@link #get(String, RequestParams, AsyncHttpResponseHandler)} but for the PATCH HTTP verb.
     *
     * @param url
     * @param params
     * @param responseHandler
     */
    public void patch(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        getClient().patch(getApiAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * Same as {@link #patch(String, RequestParams, AsyncHttpResponseHandler)} with an extra option for
     * request timeout specification.
     *
     * @param url
     * @param params
     * @param responseHandler
     * @param timeOut
     */
    public void patch(String url, RequestParams params, AsyncHttpResponseHandler responseHandler, int timeOut) {
        AsyncHttpClient client = getClient();
        client.setTimeout(timeOut);
        client.patch(getApiAbsoluteUrl(url), params, responseHandler);
    }


    /**
     * Same as {@link #get(String, RequestParams, AsyncHttpResponseHandler)} but for the DELETE HTTP verb.
     *
     * @param url
     * @param params
     * @param responseHandler
     */
    public void delete(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        getClient().delete(getApiAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * Same as {@link #delete(String, RequestParams, AsyncHttpResponseHandler)} with an extra option for
     * request timeout specification.
     *
     * @param url
     * @param params
     * @param responseHandler
     * @param timeOut
     */
    public void delete(String url, RequestParams params, AsyncHttpResponseHandler responseHandler, int timeOut) {
        AsyncHttpClient client = getClient();
        client.setTimeout(timeOut);
        client.delete(getApiAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * HTTP client that will make the network requests. It may return an async or sync client.
     * Depending on the current network requests state.
     * Uses Authorization headers to send the Authorization token.
     *
     * @return
     */
    protected AsyncHttpClient getClient(boolean headers, String token) {
        AsyncHttpClient client = new AsyncHttpClient();
        if (Looper.myLooper() == null) {
            client = new SyncHttpClient();
        }
        client.setTimeout(200000);
        if (headers) {
            client.addHeader("AUTHORIZATION", "Token token=" + token);
        } else {
            client.removeHeader("AUTHORIZATION");
        }
        return client;
    }


    /**
     * Wrapper for #getClient
     *
     * @return
     */
    protected AsyncHttpClient getClient(boolean headers) {
        return getClient(headers, getToken());
    }

    /**
     * Wrapper for #getClient
     *
     * @return
     */
    protected AsyncHttpClient getClient() {
        return getClient(true, getToken());
    }

    protected String getToken() {
        return "";
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }
}