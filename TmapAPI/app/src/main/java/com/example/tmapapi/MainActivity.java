package com.example.tmapapi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayoutTmap = (LinearLayout)findViewById(R.id.linearLayoutTmap);
        TMapView tMapView = new TMapView(this);

        tMapView.setSKTMapApiKey( "f8e29016-57fd-4d05-b929-ebf16128f93f" );
        linearLayoutTmap.addView(tMapView);



        TMapMarkerItem markerItem1 = new TMapMarkerItem();

        TMapPoint tMapPoint1 = new TMapPoint(37.570841, 126.985302); // SKT타워

        // 마커 아이콘
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_pin_drop_black_24dp);

        markerItem1.setIcon(bitmap); // 마커 아이콘 지정
        markerItem1.setPosition(0.5f, 1.0f); // 마커의 중심점을 중앙, 하단으로 설정
        markerItem1.setTMapPoint( tMapPoint1 ); // 마커의 좌표 지정
        markerItem1.setName("SKT타워"); // 마커의 타이틀 지정
        tMapView.addMarkerItem("markerItem1", markerItem1); // 지도에 마커 추가

        tMapView.setCenterPoint( 126.985302, 37.570841 );
    }


}
