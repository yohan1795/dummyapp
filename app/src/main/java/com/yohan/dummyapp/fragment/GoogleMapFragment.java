package com.yohan.dummyapp.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.yohan.dummyapp.R;
import com.yohan.dummyapp.activity.NavigationActivityMain;

/**
 * Created by Admin on 26-12-16.
 */

public class GoogleMapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private SupportMapFragment mMap;
    private LatLng locationLatLng;
    private Bundle bundle;
    private float zoom;
    private GoogleApiClient googleApiClient;
    private Location location;
    private LocationRequest locationRequest;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map_view, container, false);
        bundle = getArguments();
        ((NavigationActivityMain) this.getActivity()).getSupportActionBar().setTitle(bundle.getString("location"));
        //((NavigationActivityMain)getActivity()).showBack();
        mMap = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_dummy));
        buildGoogleApiClient();
        locationLatLng = new LatLng(bundle.getFloat("lat"), bundle.getFloat("lng"));
        zoom = 12;
        mMap.getMapAsync(this);
        return view;
    }

    private synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder((NavigationActivityMain) getActivity()).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        locationRequest = new LocationRequest().setInterval(5000).setFastestInterval(2000).setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MarkerOptions markerOptions = new MarkerOptions().position(locationLatLng).title(bundle.getString("location"));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(locationLatLng, zoom));
        googleMap.addMarker(markerOptions);
        googleMap.setOnMyLocationButtonClickListener(this);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMap.setMyLocationEnabled(true);
        googleApiClient.connect();
    }

    @Override
    public boolean onMyLocationButtonClick() {
        locationRequest = new LocationRequest().setInterval(3000).setFastestInterval(2000).setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        //(ContextCompat.checkSelfPermission((NavigationActivityMain)getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
        return false;
    }

    @Override
    public void onConnected(Bundle bundle) {

    }
    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onDestroyView() {
        googleApiClient.disconnect();
        super.onDestroyView();
    }
}
