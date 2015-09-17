package com.dpapayas.cukur;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

/**
 * Created by dpapayas on 9/16/15.
 */

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class FindBarberActivity extends FragmentActivity implements GoogleMap.OnMyLocationChangeListener {

    GoogleMap googleMap;
    double latitude = 0, longitude = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isGooglePlayServicesAvailable()) {
            finish();
        }
        setContentView(R.layout.layout_find_barber);

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        if(status!=ConnectionResult.SUCCESS){

            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();

        }else {

            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);

            googleMap = fm.getMap();

            googleMap.setMyLocationEnabled(true);

            googleMap.setOnMyLocationChangeListener(this);


            googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble("-6.21044219"), Double.parseDouble("106.73771381"))).icon(BitmapDescriptorFactory.fromResource(R.drawable.lampu_merah2)));
            googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble("-6.21174343"), Double.parseDouble("106.73762798"))).icon(BitmapDescriptorFactory.fromResource(R.drawable.lampu_merah2)));
            googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble("-6.21492184"), Double.parseDouble("106.7326498"))).icon(BitmapDescriptorFactory.fromResource(R.drawable.lampu_merah2)));
            googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble("-6.21522048"), Double.parseDouble("106.72973156"))).icon(BitmapDescriptorFactory.fromResource(R.drawable.lampu_merah2)));
            googleMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble("-6.21080483"), Double.parseDouble("106.73541784"))).icon(BitmapDescriptorFactory.fromResource(R.drawable.lampu_merah2)));

            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {

//                    if(marker.getTitle().equals("MyHome")) // if marker source is clicked
                        Toast.makeText(FindBarberActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();// display toast

                    return true;
                }
            });


        }
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

    @Override
    public void onMyLocationChange(Location location) {

        if (latitude != 0 && longitude != 0) {

            latitude = location.getLatitude();

            longitude = location.getLongitude();

            LatLng latLng = new LatLng(latitude, longitude);

            MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Nyari Jodoh");

            googleMap.addMarker(marker);

        } else {
            latitude = location.getLatitude();

            longitude = location.getLongitude();

            LatLng latLng = new LatLng(latitude, longitude);

            MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Nyari Jodoh");

            googleMap.addMarker(marker);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

            googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        }
    }
}