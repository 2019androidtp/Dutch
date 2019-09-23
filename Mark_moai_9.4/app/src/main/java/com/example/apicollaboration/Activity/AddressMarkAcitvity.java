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
import android.widget.TextView;
import android.widget.Toast;

import com.example.apicollaboration.R;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapMarkerItem2;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.example.apicollaboration.Activity.array_saving_class.addressOfIt;
import static com.example.apicollaboration.Activity.array_saving_class.alTMapPoint;
import static com.example.apicollaboration.Activity.array_saving_class.centerPointArr;
import static com.example.apicollaboration.Activity.array_saving_class.center_point;

public class AddressMarkAcitvity extends AppCompatActivity{
    public static ArrayList<TMapPoint> pointOfAll = new ArrayList<TMapPoint>();
    public static List<TMapMarkerItem> markerItem1 = new ArrayList<>();
    TextView address_textView;
    TextView name_textView;
    Button yesBtn;
    Button infraBtn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_address_mark_acitvity);
    LinearLayout linearLayoutTmap = (LinearLayout) findViewById(R.id.mapview);
    final TMapView tMapView = new TMapView(this);
    name_textView = (TextView) findViewById(R.id.nameOfLocation);
    address_textView = (TextView) findViewById(R.id.nameOfAddress);

    tMapView.setSKTMapApiKey("f8e29016-57fd-4d05-b929-ebf16128f93f"); // api key 설정
    linearLayoutTmap.addView(tMapView);

    Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_assistant_photo_black_24dp);

    Bitmap temp = BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher);
    ArrayList<String> id_marker = new ArrayList<>();
    ArrayList<String> id_marker_final = new ArrayList<>();

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


    if (array_saving_class.centerOfIt == false) {
      address_textView.setText(array_saving_class.addressOfIt.get(array_saving_class.addressOfIt.size() - 1));
      name_textView.setText(array_saving_class.nameOfIt.get(array_saving_class.nameOfIt.size() - 1));

      tMapView.setCenterPoint(alTMapPoint.get(alTMapPoint.size() - 1).getLongitude(), alTMapPoint.get(alTMapPoint.size() - 1).getLatitude());

      yesBtn = (Button) findViewById(R.id.yesBtn);

      yesBtn.setOnClickListener(new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
          array_saving_class.final_location.add(array_saving_class.nameOfIt.get(array_saving_class.addressOfIt.size() - 1));
          array_saving_class.final_Point.add(new TMapPoint(alTMapPoint.get(alTMapPoint.size() - 1).getLongitude(), alTMapPoint.get(alTMapPoint.size() - 1).getLatitude()));
          Intent goToMain = new Intent(getApplicationContext(), MainActivity.class);
          startActivity(goToMain);
        }
      });

    }

    else
    {
      address_textView.setText(array_saving_class.center_address);
      tMapView.setCenterPoint(array_saving_class.center_point.getLatitude(),array_saving_class.center_point.getLongitude());
      centerPointArr.add(center_point);
      Toast.makeText(this,array_saving_class.center_address,Toast.LENGTH_SHORT).show();
      infraBtn = (Button)findViewById(R.id.nearInfraBtn);

      array_saving_class.infraFlag = true;

//
      TMapMarkerItem markerItem2 = new TMapMarkerItem();
      // 마커 아이콘 지정
      markerItem2.setIcon(bitmap);
      markerItem2.setAutoCalloutVisible(true);
      // 마커의 좌표 지정
      markerItem2.setTMapPoint(centerPointArr.get(0));
      //지도에 마커 추가
      tMapView.addMarkerItem("markerItem2" + 13, markerItem2);
      id_marker_final.add("marker2" + 13);

      //


      infraBtn.setOnClickListener(new Button.OnClickListener() {
        @Override
        public void onClick(View view) {
          TMapData tmapdata = new TMapData();
          TMapPoint point = tMapView.getCenterPoint();


          tmapdata.findAroundNamePOI(point, "카페;편의점;주요시설물", 1, 10, new TMapData.FindAroundNamePOIListenerCallback() {
            @Override
            public void onFindAroundNamePOI(ArrayList<TMapPOIItem> POI_item) {
              for(int i = 0; i < POI_item.size() ; i++)
              {
                TMapPOIItem item = POI_item.get(i);
                array_saving_class.infraList.add(item.getPOIName());
              }

              Intent InfraListIntent = new Intent(getApplicationContext(),infraListActivity.class);
              startActivity(InfraListIntent);
              array_saving_class.infraFlag = false;
            }
          });
        }
      });
    }
  }
}

