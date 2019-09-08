package com.example.apicollaboration.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.apicollaboration.Activity.MainActivity.mainBackFlag;
import static com.example.apicollaboration.Activity.MainActivity.markerInt;

import com.example.apicollaboration.R;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;

public class AddressMarkActivity extends AppCompatActivity {
	static double MarkLat;
	static double MarkLon;
	static ArrayList allTmapPoint = new ArrayList();
	int i = 0;
	static TMapPoint tMapPoint1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address_mark_acitvity);
		LinearLayout linearLayoutTmap = (LinearLayout) findViewById(R.id.mapview);
		TMapView tMapView = new TMapView(this);

		final AlertDialog.Builder MarkBuilder = new AlertDialog.Builder(this);
		final TextView checkView = new TextView(this);

		MarkLat = getIntent().getDoubleExtra("LvLat", 1);
		MarkLon = getIntent().getDoubleExtra("LvLon", 1);


		tMapPoint1 = new TMapPoint(MarkLat, MarkLon);


		tMapView.setSKTMapApiKey("f8e29016-57fd-4d05-b929-ebf16128f93f");
		linearLayoutTmap.addView(tMapView);

		allTmapPoint.add(new TMapPoint(MarkLat, MarkLon));

		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.group6);

		// 다중마커 구현


		TMapMarkerItem markerItem1 = new TMapMarkerItem();
		markerItem1.setIcon(bitmap); // 마커 아이콘 지정

		markerItem1.setTMapPoint(tMapPoint1); // 마커의 좌표 지정
		markerItem1.setName("검색한 주소"); // 마커의 타이틀 지정
		tMapView.addMarkerItem("markerItem1" + markerInt, markerItem1); // 지도에 마커 추가
		markerInt++;
		markerItem1.setAutoCalloutVisible(true);
		markerItem1.setCanShowCallout(true); // 풍선뷰 사용
		markerItem1.setCalloutTitle("타이틀 테스트"); // 풍선뷰 주 메시지
		markerItem1.setCalloutSubTitle("서브타이틀 테스트"); // 풍선뷰 보조 메시지
		markerItem1.setPosition(0.5f, 1.0f); // 마커의 중심점을 중앙, 하단으로 설정
		//Log.d("Location", "Lot: " + MarkLat + "Lon :" + MarkLon);

		tMapView.setCenterPoint(MarkLon, MarkLat);  // Lon, Lat 순으로 되어있으니 주의!!!!

		allTmapPoint.add(markerInt,new TMapPoint(MarkLat,MarkLon));


    /*
    markerItem1.setIcon(bitmap); // 마커 아이콘 지정
    markerItem1.setPosition(0.5f, 1.0f); // 마커의 중심점을 중앙, 하단으로 설정
    markerItem1.setTMapPoint( tMapPoint1 ); // 마커의 좌표 지정
    markerItem1.setName("검색한 주소"); // 마커의 타이틀 지정
    tMapView.addMarkerItem("markerItem1", markerItem1); // 지도에 마커 추가
    */

    /*
    markerItem1.setAutoCalloutVisible(true);
    markerItem1.setCanShowCallout(true); // 풍선뷰 사용
    markerItem1.setCalloutTitle("타이틀 테스트"); // 풍선뷰 주 메시지
    markerItem1.setCalloutSubTitle("서브타이틀 테스트"); // 풍선뷰 보조 메시지
    */


		tMapView.setOnClickListenerCallBack(new TMapView.OnClickListenerCallback() {
			@Override
			public boolean onPressEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint, PointF pointF) {
				for (TMapMarkerItem item : arrayList) {

					// 클릭했을 때 이벤트 처리하는 부분
					MarkBuilder.setView(checkView);
					MarkBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {

							// 다시 메인페이지로(인텐트)
							Intent MainBackIntent = new Intent(getApplicationContext(), MainActivity.class);
							mainBackFlag = 1; // 메인 페이지에 텍스트뷰에 주소를 띄워주는 flag를 설정
							startActivity(MainBackIntent);
						}
					});
					MarkBuilder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					});

					// Toast.makeText(getApplicationContext(), String.valueOf(item.getTMapPoint()), Toast.LENGTH_LONG).show();

					MarkBuilder.show();
				}
				Log.d("EndTest", " EndTest");
				return false;
			}


			@Override
			public boolean onPressUpEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint, PointF pointF) {
				return false;
			}
		});


	}
}
