package com.example.apicollaboration.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.apicollaboration.R;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;

public class AddressMarkAcitvity extends AppCompatActivity {
  static double MarkLat;
  static double MarkLon;

  static ArrayList alTMapPoint = new ArrayList();

  @Override
  public void onBackPressed() {
    super.onBackPressed(); // back버튼 눌렸을때 이전 액티비티 이동
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_address_mark_acitvity);
    LinearLayout linearLayoutTmap = (LinearLayout)findViewById(R.id.mapview);
    TMapView tMapView = new TMapView(this);

    tMapView.setSKTMapApiKey( "f8e29016-57fd-4d05-b929-ebf16128f93f" );
    linearLayoutTmap.addView( tMapView );

    MarkLat = getIntent().getDoubleExtra("LvLat", 1);
    MarkLon = getIntent().getDoubleExtra("LvLon", 1);

    alTMapPoint.add(new TMapPoint(MarkLat,MarkLon));
/*
for(int i=0; i< alTMapPoint.size(); i++){
  TMapMarkerItem markerItem1 = new TMapMarkerItem();
  markerItem1.setTMapPoint(alTMapPoint.get(i));
    tMapView.addMarkerItem("markerItem1" + i, markerItem1); // 지도에 마커 추가
}*/



    Log.d("Location", "Lot: " + MarkLat + "Lon :" + MarkLon);

    TMapMarkerItem markerItem1 = new TMapMarkerItem();
    TMapPoint tMapPoint1 = new TMapPoint(MarkLat, MarkLon);

    Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_assistant_photo_black_24dp);

    markerItem1.setIcon(bitmap); // 마커 아이콘 지정
    markerItem1.setPosition(0.5f, 1.0f); // 마커의 중심점을 중앙, 하단으로 설정
    markerItem1.setTMapPoint( tMapPoint1 ); // 마커의 좌표 지정
    markerItem1.setName("검색한 주소"); // 마커의 타이틀 지정
    tMapView.addMarkerItem("markerItem1", markerItem1); // 지도에 마커 추가

    tMapView.setCenterPoint(MarkLon,MarkLat);  // Lon, Lat 순으로 되어있으니 주의!!!!

  }
}
