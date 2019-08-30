package com.example.apicollaboration.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

import com.example.apicollaboration.R;
import com.skt.Tmap.TMapData;
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
    static String DaumAddressResult= null;
    ListView listview = null ; // 리스트뷰 객체 생성
    Intent allsearchMarkIntent;
    @Override
    public void onBackPressed() {
        finish(); // back버튼눌렸을시 앱 종료
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    //네비게이션 드로어
        final String[] items = {"주소 검색", "모든 마크 검색"} ; // 탭에 보여질 텍스트 설정
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items) ; // 어뎁터에 연결

        listview = (ListView) findViewById(R.id.drawer_menulist) ;
        listview.setAdapter(adapter) ;
        listview.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {


                switch (position) {
                    case 0 : // 주소검색
                        convertTodAddress(); // POI 검색 실행

                        break ;
                    case 1 : // 모든 마크 검색
                        allsearchMarkIntent = new Intent(getApplicationContext(), AllMarkSearchActivity.class);
                        startActivity(allsearchMarkIntent);

                        break ;
                    case 2 : // 추가기능

                        break ;
                    case 3 : // 추가기능

                        break ;
                    case 4 : // 추가기능

                        break ;
                }


                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer) ;
                drawer.closeDrawer(Gravity.LEFT) ; // 아이템 선택시 드로어창 닫힘
            }
        });
       // 네비게이션 드로어 마침

        Button allMarkSearchBtn = (Button)findViewById(R.id.AllMarkBtn);
        Button DaumfindAddressBtn = (Button)findViewById(R.id.searchDaumBtn);
        Button findAddressbtn = (Button)findViewById(R.id.searchBtn);
        TMapView tMapView = new TMapView(this); // key값 설정을 위한 tmapView 생성
        tMapView.setSKTMapApiKey( "f8e29016-57fd-4d05-b929-ebf16128f93f" ); // api key 설정

      findAddressbtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
          convertTodAddress(); // 주소 검색 다이얼로그 띄우는 메소드 호출
        }
        });
      DaumfindAddressBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent DaumSearchIntent = new Intent(getApplicationContext(),DaumSearchActivity.class);
          startActivity(DaumSearchIntent);
        }
      });
      allMarkSearchBtn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              allsearchMarkIntent = new Intent(getApplicationContext(), AllMarkSearchActivity.class);
              startActivity(allsearchMarkIntent);
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

                Log.d("POI testing", "POI 검색결과: "+ POIResult[i] +" "+ AddressResult[i] + POILat[i] + "" + POILon[i] + "\n" );

                Intent ListViewIntent = new Intent(getApplicationContext(), ListViewActivity.class);
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


