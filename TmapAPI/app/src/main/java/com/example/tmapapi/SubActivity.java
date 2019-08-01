package com.example.tmapapi;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;
import java.util.ArrayList;

public class SubActivity extends AppCompatActivity implements TMapGpsManager.onLocationChangedCallback, View.OnClickListener {
  private Context mContext = null;
  private boolean m_bTrackingMode = true;

  private TMapData tmapdata = null;
  private TMapGpsManager tmapgps = null;
  private TMapView tmapview = null;
  private static String mApiKey = "f8e29016-57fd-4d05-b929-ebf16128f93f";
  private static int mMarkerID;

  private ArrayList<TMapPoint> m_tmapPoint = new ArrayList<TMapPoint>();
  private ArrayList<String> mArrayMarkerID = new ArrayList<String>();
  private ArrayList<MapPoint> m_mapPoint = new ArrayList<MapPoint>();

  private String address;
  private Double lat = null;
  private Double lon = null;

  private Button bt_find;
  private Button bt_fac;
  String  POIResult[] = new String[100];
  String  AddressResult[] = new String[100];
  int POIitemSize;

  @Override
  public void onLocationChange(Location location) {
    if(m_bTrackingMode){
      tmapview.setLocationPoint(location.getLongitude(), location.getLatitude());
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sub);

    mContext = this;

    //버튼 선언
    bt_find = (Button) findViewById(R.id.bt_findadd);
    bt_fac = (Button) findViewById(R.id.bt_findfac);

    //Tmap 각종 객체 선언
    tmapdata = new TMapData();
    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.mapview);
    tmapview = new TMapView(this);

    linearLayout.addView(tmapview);
    tmapview.setSKTMapApiKey(mApiKey);

    addPoint();
    showMarkerPoint();

    //현재 보는 방향
    tmapview.setCompassMode(true);
    //현 위치 아이콘 표시
    tmapview.setIconVisibility(true);
    //줌레벨
    tmapview.setZoomLevel(15);
    //지도 타입
    tmapview.setMapType(TMapView.MAPTYPE_STANDARD);
    //언어 설정
    tmapview.setLanguage(TMapView.LANGUAGE_KOREAN);


    tmapgps = new TMapGpsManager(SubActivity.this); //단말의 위치탐색을 위한 클래스
    tmapgps.setMinTime(1000); //위치변경 인식 최소시간설정
    tmapgps.setMinDistance(5); //위치변경 인식 최소거리설정
    tmapgps.setProvider(tmapgps.NETWORK_PROVIDER); //네트워크 기반의 위치탐색
    // tmapgps.setProvider(tmapgps.GPS_PROVIDER); 위성기반의 위치탐색
    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
      }
      return;
    }
    tmapgps.OpenGps();

    //화면중심을 단말의 현재위치로 이동
    tmapview.setTrackingMode(true);
    tmapview.setSightVisible(true);

    tmapview.setOnCalloutRightButtonClickListener(new TMapView.OnCalloutRightButtonClickCallback() {
      @Override
      public void onCalloutRightButton(TMapMarkerItem tMapMarkerItem) {
        lat = tMapMarkerItem.latitude;
        lon = tMapMarkerItem.longitude;

        tmapdata.convertGpsToAddress(lat, lon, new TMapData.ConvertGPSToAddressListenerCallback() {
          @Override
          public void onConvertToGPSToAddress(String strAddress) {
            address = strAddress;
          }
        });

        Toast.makeText(SubActivity.this, "주소: " + address, Toast.LENGTH_SHORT).show();


      }
    });
    bt_find.setOnClickListener(this);
    bt_fac.setOnClickListener(this);


  }

  //핀 찍을 data
  public void addPoint(){
    // 강남
    m_mapPoint.add(new MapPoint("강남", 37.510350, 127.066847));
  }

  public void showMarkerPoint(){
    for(int i = 0; m_mapPoint.size() > i; i++){
      TMapPoint point = new TMapPoint(m_mapPoint.get(i).getLatitude(),
                                      m_mapPoint.get(i).getLongitude());
      TMapMarkerItem item1 = new TMapMarkerItem();
      Bitmap bitmap = null;
      //핀 이미지
      bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_pin_drop_black_24dp);

      item1.setCalloutTitle(m_mapPoint.get(i).getName());
      item1.setCalloutSubTitle("서울");
      item1.setCanShowCallout(true);
      item1.setAutoCalloutVisible(true);

      //풍선 안 우측버튼
      Bitmap bitmap_i = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.ic_perm_identity_black_24dp);

      item1.setCalloutRightButtonImage(bitmap_i);

      String strID = String.format("pmarker%d", mMarkerID++);

      tmapview.addMarkerItem(strID, item1);
      mArrayMarkerID.add(strID);
    }
  }
  @Override
  public void onClick(View view) {
    switch (view.getId()){
      case R.id.bt_findadd:
        convertTodAddress();
        break;
      case R.id.bt_findfac:
        getAroundBizPoi();
        break;
    }
  }

  public  void convertTodAddress(){

    //다이얼로그 띄워서, 검색창에 입력받음
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("POI 통합 검색");

    final EditText input = new EditText(this);
    builder.setView(input);

    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        final String strData = input.getText().toString();
        TMapData tmapdata = new TMapData();

        tmapdata.findAllPOI(strData, new TMapData.FindAllPOIListenerCallback() {
          @Override
          public void onFindAllPOI(ArrayList<TMapPOIItem> poiItem) {
            Intent AddressIntent = null;
            for (int i = 0; i < poiItem.size(); i++) {
              TMapPOIItem item = poiItem.get(i);

              Log.d("주소로찾기", "POI Name: " + item.getPOIName().toString() + "," +
                      "Address: " + item.getPOIAddress().replace("null", "") + "," +
                      "Point: " + item.getPOIPoint().toString());
              POIResult[i] =  item.getPOIName().toString();
              AddressResult[i] =  item.getPOIAddress().replace("null", "");
              POIitemSize = poiItem.size();
              AddressIntent = new Intent(SubActivity.this, AddressListViewActivity.class);
              AddressIntent.putExtra("POIResult", POIResult);
              AddressIntent.putExtra("AddressResult", AddressResult);
              AddressIntent.putExtra("POIItemSize",POIitemSize);


            }
            SubActivity.this.startActivity(AddressIntent);
          }
        });

      }
    });
    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        dialog.cancel();
      }
    });

    builder.show();
  }

  //2. 주변 편의시설 검색하기

  public void getAroundBizPoi(){
    TMapData tmapdata = new TMapData();

    TMapPoint point = tmapview.getCenterPoint();

    tmapdata.findAroundNamePOI(point, "편의점;은행", 1, 99, new TMapData.FindAroundNamePOIListenerCallback() {
      @Override
      public void onFindAroundNamePOI(ArrayList<TMapPOIItem> poiItem) {
        for(int i = 0; i < poiItem.size(); i++){
          TMapPOIItem item = poiItem.get(i);
          Log.d("편의시설","POI Name: " + item.getPOIName() + "," + "Address: "
                  + item.getPOIAddress().replace("null",""));
        }
      }
    });
  }

}
