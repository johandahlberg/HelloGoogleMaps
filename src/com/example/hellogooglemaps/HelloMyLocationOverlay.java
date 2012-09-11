package com.example.hellogooglemaps;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class HelloMyLocationOverlay extends MyLocationOverlay {

	private Context mContext;
	private HelloItemizedOverlay mOverlay = null;	

	public HelloMyLocationOverlay(Context context, MapView mapView) {
		super(context, mapView);
		mContext = context;		
	}

	@Override
	public boolean onTap(GeoPoint geoPoint, final MapView mapView) {		

		DialogInterface.OnClickListener lightingClickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which){
				case DialogInterface.BUTTON_POSITIVE:
					//Yes button clicked
					lightBeacon(mapView);

				case DialogInterface.BUTTON_NEGATIVE:
					//No button clicked
					break;
				}
			}
		};

		DialogInterface.OnClickListener quenchingClickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which){
				case DialogInterface.BUTTON_POSITIVE:
					//Yes button clicked
					quenchBeacon(mapView);

				case DialogInterface.BUTTON_NEGATIVE:
					//No button clicked
					break;
				}
			}
		};

		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);

		if(mOverlay == null)
			dialog.setMessage("Do you want to light a beacon at your location?").setPositiveButton("Yes", lightingClickListener)
				.setNegativeButton("No", lightingClickListener).show();
		else
			dialog.setMessage("Do you want to quench your current beacon?").setPositiveButton("Yes", quenchingClickListener)
				.setNegativeButton("No", lightingClickListener).show();

		return true;

	}

	private void lightBeacon(MapView mapView) {			
		// Add the beacon			
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = mapView.getResources().getDrawable(R.drawable.androidmarker);
		HelloItemizedOverlay itemizedoverlay = new HelloItemizedOverlay(drawable);

		GeoPoint point = this.getMyLocation();
		OverlayItem overlayitem = new OverlayItem(point, "This is it!", "I created a beacon at this location.");

		itemizedoverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedoverlay);

		mOverlay = itemizedoverlay; 
	}

	private void quenchBeacon(MapView mapView) {
		// Or remove it			
		List<Overlay> mapOverlays = mapView.getOverlays();		
		mapOverlays.remove(mOverlay);			
		mOverlay = null;	
	}
}
