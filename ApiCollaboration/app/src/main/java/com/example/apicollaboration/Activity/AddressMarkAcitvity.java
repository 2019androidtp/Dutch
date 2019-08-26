package com.example.apicollaboration.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.apicollaboration.R;
import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapMarkerItem2;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;
import java.util.List;

import static com.example.apicollaboration.Activity.array_saving_class.alTMapPoint;

public class AddressMarkAcitvity extends AppCompatActivity{
  public static ArrayList<TMapPoint> pointOfAll = new ArrayList<TMapPoint>();
  public static List<TMapMarkerItem> markerItem1 = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_address_mark_acitvity);
    LinearLayout linearLayoutTmap = (LinearLayout) findViewById(R.id.mapview);
    TMapView tMapView = new TMapView(this);

    tMapView.setSKTMapApiKey("f8e29016-57fd-4d05-b929-ebf16128f93f"); // api key 설정
    linearLayoutTmap.addView(tMapView);

    Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_assistant_photo_black_24dp);

    Bitmap temp = BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher);
    ArrayList<String> id_marker = new ArrayList<>();



    for (int i = 0; i < alTMapPoint.size(); i++) {
      TMapMarkerItem markerItem1 = new TMapMarkerItem();
      // 마커 아이콘 지정
      markerItem1.setIcon(bitmap);
      markerItem1.setCanShowCallout(true);
      markerItem1.setCalloutSubTitle("다른 좌표를 찍으려면 클릭");
      markerItem1.setCalloutTitle("좌표 " + Integer.toString(i + 1) + "번");
      markerItem1.setCalloutRightButtonImage(temp);

      markerItem1.setAutoCalloutVisible(true);
      // 마커의 좌표 지정
      markerItem1.setTMapPoint(alTMapPoint.get(i));
      //지도에 마커 추가
      tMapView.addMarkerItem("markerItem" + i, markerItem1);
      id_marker.add("marker" + i);
    }


  }

}