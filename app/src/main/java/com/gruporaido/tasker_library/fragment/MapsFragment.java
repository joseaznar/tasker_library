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

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.android.airmapview.AirMapInterface;
import com.airbnb.android.airmapview.AirMapMarker;
import com.airbnb.android.airmapview.AirMapView;
import com.airbnb.android.airmapview.listeners.OnMapInitializedListener;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.gruporaido.tasker_library.R;
import com.gruporaido.tasker_library.event.FromToMarkersEvent;
import com.gruporaido.tasker_library.event.LocationEvent;
import com.gruporaido.tasker_library.event.TextMarkerEvent;
import com.gruporaido.tasker_library.util.ApplicationComponent;
import com.gruporaido.tasker_library.util.Helper;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;


public class MapsFragment extends FragmentResponder implements OnMapInitializedListener {

    private static final String TAG = "MapsFragment";

    @Inject
    protected Helper mHelper;

    protected AirMapView mMapView;
    protected AirMapInterface mAirMapInterface;

    protected boolean mMapViewLoaded;

    protected ArrayList<AirMapMarker> mMarkers;

    @Override
    public void injectFragment(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMarkers = new ArrayList<>();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, parent, false);

        try {
            MapsInitializer.initialize(getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView = (AirMapView) view.findViewById(R.id.maps_googleMap);

        mMapView.setOnMapInitializedListener(this);
        mMapView.initialize(getChildFragmentManager());

        return view;
    }

    @Override
    public void onMapInitialized() {
        mAirMapInterface = mMapView.getMapInterface();
    }

    protected void moveMapToLocation(double latitude, double longitude) {
        if (mAirMapInterface != null) {
            mAirMapInterface.setCenterZoom(new LatLng(latitude, longitude), 16);
        }
    }

    protected void updateMarker(double latitude, double longitude, String string, int textSize) {
        clearMarkers();
        Bitmap dialogBitmap = mHelper.drawTextOnDrawable(R.mipmap.map_dialog_l, string, textSize, 7, -5);
        AirMapMarker marker = new AirMapMarker.Builder()
                .position(new LatLng(latitude, longitude))
                .bitmap(dialogBitmap)
                .build();
        addMarkerToMap(marker);

        if (mAirMapInterface != null) {
            mAirMapInterface.setCenterZoom(new LatLng(latitude, longitude), 16);
        }
    }

    public void updateLocation(double fromLatitude, double fromLongitude, double toLatitude, double toLongitude, int driveTime) {
        clearMarkers();
        AirMapMarker marker = new AirMapMarker.Builder()
                .position(new LatLng(fromLatitude, fromLongitude))
                .iconId(R.mipmap.map_location)
                .build();
        addMarkerToMap(marker);
        Bitmap dialogBitmap = mHelper.drawTextOnDrawable(R.mipmap.map_dialog_s, getString(R.string.maps_time, driveTime), 14, 0, -5);

        marker = new AirMapMarker.Builder()
                .position(new LatLng(toLatitude, toLongitude))
                .bitmap(dialogBitmap)
                .build();
        addMarkerToMap(marker);

        if (mMapViewLoaded) {
            resizeToMarkers(mMarkers);
        }
    }

    @Subscribe
    public void onLocationEvent(LocationEvent event) {
        moveMapToLocation(event.getLatitude(), event.getLongitude());
    }

    @Subscribe
    public void onTextMarkerEvent(TextMarkerEvent event) {
        updateMarker(event.getLatitude(), event.getLongitude(), event.getText(), 12);
    }

    @Subscribe
    public void onFromToMarkersEvent(FromToMarkersEvent event) {
        updateLocation(event.getFromLatitude(),
                event.getFromLongitude(),
                event.getToLatitude(),
                event.getToLongitude(),
                event.getDriveTime());
        resizeToMarkers(mMarkers);
    }

    protected void clearMarkers() {
        if (mAirMapInterface != null) {
            mAirMapInterface.clearMarkers();
            mMarkers.clear();
        }
    }

    protected void addMarkerToMap(AirMapMarker marker) {
        if (mAirMapInterface != null) {
            mAirMapInterface.addMarker(marker);
            mMarkers.add(marker);
        }
    }

    protected void resizeToMarkers(ArrayList<AirMapMarker> markers) {
        if (markers.size() == 0) return;
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (AirMapMarker marker : markers) {
            builder.include(marker.getLatLng());
        }
        if (mAirMapInterface != null) {
            mAirMapInterface.setCenter(builder.build(), mHelper.dpToPx(100));
        }
    }
}
