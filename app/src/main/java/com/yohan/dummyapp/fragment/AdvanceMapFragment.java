package com.yohan.dummyapp.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.yohan.dummyapp.R;
import com.yohan.dummyapp.activity.NavigationActivityMain;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utils.CommonUtils;

/**
 * Created by Admin on 09-01-17.
 */

public class AdvanceMapFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, LocationListener {
    private static final int PERMISSION_LOCATION_REQUEST_CODE = 1111;
    private Geocoder geocoder;
    GoogleMap mGoogleMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private boolean refreshMyLocation;

    @BindView(R.id.editText_search_map)
    EditText et_search;
    SupportMapFragment mMapFragment;
    @OnClick(R.id.button_search_location)
    public void searchLocation() {
        Toast.makeText(getActivity(), "Searching", Toast.LENGTH_SHORT).show();
        try{
            List<Address> addresses = geocoder.getFromLocationName(et_search.getText().toString(),5);
            if(addresses!=null)
                for(Address address: addresses)
                    addMarker(new LatLng(address.getLatitude(), address.getLongitude()), address.getAddressLine(0));

        } catch (IOException e){
            e.printStackTrace();
        }
        InputMethodManager manager = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(this.getView().getWindowToken(), 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_advance_map, container, false);
        ButterKnife.bind(this, view);
        mMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.advanceMap);
        geocoder = new Geocoder(getActivity(), Locale.getDefault());
        mMapFragment.getMapAsync(this);

        return view;
    }

    public synchronized void buildApiClient(){
        googleApiClient = new GoogleApiClient.Builder((NavigationActivityMain)getActivity()).addApi(LocationServices.API).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
        googleApiClient.connect();
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
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mGoogleMap.setMyLocationEnabled(true);
        mGoogleMap.setOnMyLocationButtonClickListener(this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                buildApiClient();
            } else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_LOCATION_REQUEST_CODE);
            }
        } else {
            buildApiClient();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==PERMISSION_LOCATION_REQUEST_CODE && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            buildApiClient();
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        refreshMyLocation = true;
        locationRequest = new LocationRequest();
        locationRequest.setFastestInterval(2000);
        locationRequest.setInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        } else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_LOCATION_REQUEST_CODE);
        }
        return true;
    }

    @Override
    public void onLocationChanged(Location location) {
       if(refreshMyLocation) {
           LatLng myLatLng = new LatLng(location.getLatitude(), location.getLongitude());
           addMarker(myLatLng, "You are here");
           refreshMyLocation = false;
       }
    }

    private void addMarker(LatLng locationLatLng,  String title) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(locationLatLng);
        markerOptions.title(title);
        mGoogleMap.addMarker(markerOptions);
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(12));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationLatLng, 12));
    }

}
