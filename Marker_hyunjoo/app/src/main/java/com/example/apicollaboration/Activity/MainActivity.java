package com.example.apicollaboration.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.apicollaboration.R;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;

// 이 액티비티의 역할: 주소로 검색 버튼을 눌렀을 때 주소를 검색하는 창이 나오고 검색한 문자열을 통해 실제 주소지를 검색하고 다음 액티비티로 넘겨줌

public class MainActivity extends AppCompatActivity {
    private TMapData tMapData = null;
    static String POIResult[] = new String[100];
    static String AddressResult[] = new String[100];
    static int POIitemSize;
    static double POILat[]= new double[100];
    static double POILon[]= new double[100];
    private String MarkName;
    private TextView mTextView;
    private int PlaceNum;
    static String DaumAddressResult= null;

    //from git
    private static int mMarkerID;
    private ArrayList<String> mArrayMarkerID = new ArrayList<String>();
    private ArrayList<TMapMarkerItem> m_mapMarkerItem = new ArrayList<TMapMarkerItem>();
    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button DaumfindAddressBtn = (Button)findViewById(R.id.searchDaumBtn);
        Button findAddressbtn = (Button)findViewById(R.id.searchBtn);
        TMapView tMapView = new TMapView(this); // key값 설정을 위한 tmapView 생성
        tMapView.setSKTMapApiKey( "f8e29016-57fd-4d05-b929-ebf16128f93f" ); // api key 설정

        MarkName = getIntent().getStringExtra("LvName");


        Log.d("Main", "Log - MarkName" + MarkName);
        mTextView = findViewById (R.id.textView1);
        mTextView.setText(MarkName);

        Log.d("mTextView", "Log - " + MarkName);


        findAddressbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTodAddress(); // 주소 검색 다이얼로그 띄우는 메소드 호출
            }
        });
        // 여기 밑은 짜피 필요없음
        DaumfindAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent DaumSearchIntent = new Intent(getApplicationContext(), DaumSearchActivity.class);
                DaumSearchIntent.putExtra("PlaceNum", PlaceNum);
                startActivity(DaumSearchIntent);
            }
        });


    }


  public void convertTodAddress() {
    AlertDialog.Builder addressSearchBuilder = new AlertDialog.Builder(this);
    addressSearchBuilder.setTitle("주소 검색");

    final EditText userInput = new EditText(this);
    addressSearchBuilder.setView(userInput);

    addressSearchBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
          final String strData = userInput.getText().toString();
          TMapData tmapData = new TMapData();

          tmapData.findAllPOI(strData, new TMapData.FindAllPOIListenerCallback() {
            @Override
            public void onFindAllPOI(ArrayList<TMapPOIItem> poiItem) {
              for(int i = 0; i< poiItem.size(); i++){
                TMapPOIItem item = poiItem.get(i);

                POIResult[i] = item.getPOIName().toString();
                AddressResult[i] = item.getPOIAddress().replace("null","");
                POIitemSize = poiItem.size();
                POILat[i] = item.getPOIPoint().getLatitude();
                POILon[i] = item.getPOIPoint().getLongitude();

                Log.d("POI testing", "Log - POI 검색결과: "+ POIResult[i] +" "+ AddressResult[i] + POILat[i] + "" + POILon[i] + "\n" );

                Intent ListViewIntent = new Intent(getApplicationContext(), ListViewActivity.class);
                ListViewIntent.putExtra("PlaceNum", PlaceNum);
                startActivity(ListViewIntent); // 리스트뷰 띄우는 액티비티로 이동
              }

            }

          });

      }

    });
    addressSearchBuilder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        dialog.cancel();
      }
    });

    addressSearchBuilder.show();
  }
}


