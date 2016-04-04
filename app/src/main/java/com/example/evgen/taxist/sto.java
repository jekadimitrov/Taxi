package com.example.evgen.taxist;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;

/**
 * Created by Evgen on 11.03.2016.
 */
public class sto extends MainActivity {
    public double latitude;
    public double longitude;
    private LocationManager LM;
    GoogleMap googleMap;

    String provider;
    LocationManager locationManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stomap);
        createMapView();

        MapView mapView = (MapView) findViewById(R.id.mapView); //определяем карту

        LM = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    private void createMapView() {

        try {
            if (null == googleMap) {
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.mapView)).getMap();


                if (null == googleMap) {
                    Toast.makeText(getApplicationContext(),
                            "Error creating map", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception) {
            Log.e("mapApp", exception.toString());
        }
    }


    private void setUpMap() {// выводим значок своего местоположения
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        //проверка на пустоту провайдера
        if ((provider != null) && !provider.equals("")) {
            //определяем местоположение
            Location location = locationManager.getLastKnownLocation(provider);
            locationManager.requestLocationUpdates(provider, 20000, 1, this);
            if (location != null) {
                //задаем координаты
                GeoPoint myLication = new GeoPoint((int) latitude, (int) longitude);
                // перемещаемся туда при помощи данной функции mc - MapController
                mc.animateTo(myLication);
                // рисуем картинку на экране
                Drawable makerDefault = getResources().getDrawable(R.drawable.pushpin);
                //выводим маркер на карте
                MirItemizedOverlay itemizedOverlay = new MirItemizedOverlay(makerDefault);
                //задаем координаты маркеру
                itemizedOverlay.addOverlayItem((int) latitude, (int) longitude, "My Location");
                //выводи картинку по заданным координатам
                mapView.getOverlays().add(itemizedOverlay);
                //перемещаемся к центру экрана
                mc.setCenter(new GeoPoint((int) latitude, (int) longitude));
            } else {
                Toast.makeText(getBaseContext(), "Location can't be retrieved", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getBaseContext(), "No Provider Found", Toast.LENGTH_SHORT).show();
        }
    }
}