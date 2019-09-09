package com.example.apicollaboration.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import com.example.apicollaboration.R;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import static com.example.apicollaboration.Activity.AddressMarkActivity.*;

public class SearchAllMarkerActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_all_marker);
		LinearLayout S_linearLayoutTmap = (LinearLayout) findViewById(R.id.SearchMarkmapview);
		TMapView S_tMapView = new TMapView(this);
		S_linearLayoutTmap.addView(S_tMapView);




		final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.group6);

		for (int k = 0; k < allTmapPoint.size(); k++) {
			TMapMarkerItem S_markerItem = new TMapMarkerItem();

			S_markerItem.setIcon(bitmap);
			S_markerItem.setTMapPoint((TMapPoint) allTmapPoint.get(k)); // allTmapPoint에 있는 
			S_tMapView.addMarkerItem("markeritem" + k, S_markerItem);
			S_tMapView.setCenterPoint(MarkLat, MarkLon);

		}

	}

}
