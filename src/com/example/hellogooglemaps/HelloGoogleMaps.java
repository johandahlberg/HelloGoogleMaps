package com.example.hellogooglemaps;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class HelloGoogleMaps extends MapActivity {

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// Create the view
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		MapView mapView = (MapView) findViewById(R.id.mapview);

		// Adding my location as an overlay
		HelloMyLocationOverlay mylocationOverlay = new HelloMyLocationOverlay(this, mapView);
		mylocationOverlay.enableMyLocation();
		mapView.getOverlays().add(mylocationOverlay);
		
	}
}
