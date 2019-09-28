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

public class AllMarkSearchActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_mark_search);

		LinearLayout linearLayoutTmap = (LinearLayout)findViewById(R.id.AllMarkView);
		TMapView tMapView = new TMapView(this);
		tMapView.setSKTMapApiKey( "f8e29016-57fd-4d05-b929-ebf16128f93f" );

		linearLayoutTmap.addView( tMapView );


		for(int i=0; i<alTMapPoint.size(); i++){
			TMapMarkerItem markerItem = new TMapMarkerItem();

			Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_assistant_photo_black_24dp);

			markerItem.setIcon(bitmap);
			markerItem.setPosition(0.5f,1.0f);
			markerItem.setTMapPoint((TMapPoint) alTMapPoint.get(i));
			markerItem.setName("markitem");
			tMapView.addMarkerItem("markerItem"+ i, markerItem);
			tMapView.setCenterPoint(MarkLon, MarkLat);
		}





	}

}
