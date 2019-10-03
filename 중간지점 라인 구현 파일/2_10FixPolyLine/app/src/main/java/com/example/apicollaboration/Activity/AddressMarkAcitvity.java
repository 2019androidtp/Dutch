package com.example.apicollaboration.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PointF;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apicollaboration.R;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapMarkerItem2;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import static com.example.apicollaboration.Activity.MainActivity.POIResult;
import static com.example.apicollaboration.Activity.array_saving_class.POIName;
import static com.example.apicollaboration.Activity.array_saving_class.alTMapPoint;

public class AddressMarkAcitvity extends AppCompatActivity {

  TMapView tMapView;
  TextView address_textView;
  TextView name_textView;
  Button yesBtn;
  Button noBtn;
  Bitmap bitmap;
  Bitmap temp;
  TMapPoint centerP;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_address_mark_acitvity);
    LinearLayout linearLayoutTmap = (LinearLayout) findViewById(R.id.mapview);
    tMapView = new TMapView(this);
    name_textView = (TextView) findViewById(R.id.nameOfLocation);
    address_textView = (TextView) findViewById(R.id.nameOfAddress);

    //tMapView.setSKTMapApiKey("f8e29016-57fd-4d05-b929-ebf16128f93f"); // api key 설정
    linearLayoutTmap.addView(tMapView);

    bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_assistant_photo_black_24dp);

    temp = BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher);

    markReturn();


    if (array_saving_class.centerOfIt == false) {
      address_textView.setText(array_saving_class.addressOfIt.get(array_saving_class.addressOfIt.size() - 1));
      name_textView.setText(array_saving_class.nameOfIt.get(array_saving_class.nameOfIt.size() - 1));

      tMapView.setCenterPoint(alTMapPoint.get(alTMapPoint.size() - 1).getLongitude(), alTMapPoint.get(alTMapPoint.size() - 1).getLatitude());

    }
    else{
        // getLatitude랑 getLongitude부분이 반대로 적혀있음. 수정해야함.
        tMapView.setCenterPoint(array_saving_class.center_point.getLatitude(), array_saving_class.center_point.getLongitude());

        TMapMarkerItem center = new TMapMarkerItem();
        centerP = new TMapPoint(array_saving_class.center_point.getLongitude(), array_saving_class.center_point.getLatitude());

        center.setIcon(bitmap); // 마커 아이콘 지정
        center.setCanShowCallout(true); // 풍선뷰의 사용여부를 설정한다.
        center.setCalloutSubTitle("중간지점 입니다."); // 풍선뷰 보조메시지 설정
        center.setCalloutTitle("중간지점 "); // 풍선뷰 제목 설정
        center.setCalloutRightButtonImage(temp); // 풍선뷰 오른쪽 이미지 설정
        center.setAutoCalloutVisible(true); // 풍선뷰 자동 활성화

        // 마커의 좌표 지정
        center.setTMapPoint(centerP); // 마커 위,경도 설정
        center.setVisible(TMapMarkerItem.VISIBLE); // 아이콘 보이게
        //지도에 마커 추가
        tMapView.addMarkerItem("markerItem", center);

        // 중간지점에 자동차경로 추가
        drawline();

    }

        yesBtn = (Button) findViewById(R.id.yesBtn);
        noBtn = (Button) findViewById(R.id.noBtn);

        yesBtn.setOnClickListener(new Button.OnClickListener() {
          @Override
          public void onClick(View view) {
            array_saving_class.final_location.add(array_saving_class.nameOfIt.get(array_saving_class.addressOfIt.size() - 1));
            array_saving_class.final_Point.add(new TMapPoint(alTMapPoint.get(alTMapPoint.size() - 1).getLongitude(), alTMapPoint.get(alTMapPoint.size() - 1).getLatitude()));
            Intent goToMain = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(goToMain);
          }
        });

        noBtn.setOnClickListener(new Button.OnClickListener() {
          @Override
          public void onClick(View view) {
            array_saving_class.addressOfIt.remove(array_saving_class.addressOfIt.get(array_saving_class.addressOfIt.size() - 1));
            alTMapPoint.remove(alTMapPoint.get(alTMapPoint.size() - 1));
            Intent goToMain = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(goToMain);
          }
        });
        // 중간지점 체크 후 테스트를 위해 yes no 버튼을 빼놨습니다. 수정해야합니다.

  }

  // 자동차 경로 호출시 외부에서 Thread를 통해서 호출해줘야 정상적으로 실행

  public void drawline() {

    new Thread(new Runnable() {
      @Override public void run() {
         try {
           for(int i=0; i< alTMapPoint.size(); i++) {

             TMapPolyLine tMapPolyLine = new TMapData().findPathData(alTMapPoint.get(i), centerP);
             tMapPolyLine.setLineColor(Color.BLUE);
             tMapPolyLine.setLineWidth(2);
             tMapView.addTMapPolyLine("Line1" + i, tMapPolyLine);

           }

         }catch(Exception e) {
          e.printStackTrace();
         }
      }
    }).start();

  }

  public void markReturn() {

    for (int i = 0; i < alTMapPoint.size(); i++) {

      TMapMarkerItem markerItem1 = new TMapMarkerItem();
      // 마커 아이콘 지정
      markerItem1.setIcon(bitmap);
      markerItem1.setCanShowCallout(true);
      markerItem1.setCalloutSubTitle("위치:" + array_saving_class.nameOfIt.get(array_saving_class.nameOfIt.size() - 1)); // 위치 자동으로 찍히게 수정해야함
      markerItem1.setCalloutTitle("좌표 " + Integer.toString(i + 1) + "번");
      markerItem1.setCalloutRightButtonImage(temp);

      markerItem1.setAutoCalloutVisible(true);
      // 마커의 좌표 지정
      markerItem1.setTMapPoint(alTMapPoint.get(i));
      //지도에 마커 추가
      tMapView.addMarkerItem("markerItem" + i, markerItem1);
      tMapView.setCenterPoint(alTMapPoint.get(i).getLatitude(), alTMapPoint.get(i).getLatitude());

    }
  }

  @Override
  public void onBackPressed() {
    Intent backMainIntent = new Intent(getApplicationContext(), MainActivity.class);
    startActivity(backMainIntent);
  }
}

