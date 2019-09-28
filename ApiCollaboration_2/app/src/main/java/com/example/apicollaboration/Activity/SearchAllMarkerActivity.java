package com.example.apicollaboration.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import com.example.apicollaboration.R;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import static com.example.apicollaboration.Activity.AddressMarkAcitvity.*;
import static com.example.apicollaboration.Activity.MainActivity.markerInt;

public class SearchAllMarkerActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_all_marker);
		LinearLayout S_linearLayoutTmap = (LinearLayout) findViewById(R.id.SearchMarkmapview);
		TMapView S_tMapView = new TMapView(this);
		S_linearLayoutTmap.addView(S_tMapView);




		final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_add_location_black_24dp);

		for (int k = 0; k < allTmapPoint.size(); k++) {
			TMapMarkerItem S_markerItem = new TMapMarkerItem();

			S_markerItem.setIcon(bitmap);
			S_markerItem.setTMapPoint((TMapPoint) allTmapPoint.get(k)); // allTmapPoint에 있는 
			S_tMapView.addMarkerItem("markeritem" + k, S_markerItem);
			S_tMapView.setCenterPoint(MarkLat, MarkLon);

		}

	}

}
