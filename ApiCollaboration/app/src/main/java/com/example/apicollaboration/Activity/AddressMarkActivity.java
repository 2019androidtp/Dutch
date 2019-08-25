package com.example.apicollaboration.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apicollaboration.R;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;
import com.skt.Tmap.TMapView.OnLongClickListenerCallback;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AddressMarkActivity extends AppCompatActivity {
    private double MarkLat;
    private double MarkLon;
    private String MarkName;
    //public String mystring = getResources().getString(R.string.test_txt1);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_mark_acitvity);
        LinearLayout linearLayoutTmap = (LinearLayout) findViewById(R.id.mapview);
        final TMapView tMapView = new TMapView(this);

        tMapView.setSKTMapApiKey("f8e29016-57fd-4d05-b929-ebf16128f93f");
        linearLayoutTmap.addView(tMapView);

        MarkLat = getIntent().getDoubleExtra("LvLat", 1);
        MarkLon = getIntent().getDoubleExtra("LvLon", 1);

        //여기부터
        MarkName = getIntent().getStringExtra("LvName");

        Log.d("Sample", "Sample" + MarkName);


        Log.d("Location", "Lot: " + MarkLat + "Lon :" + MarkLon);
        //Log.d("MarkName", "Name: " + MarkName);
        TMapMarkerItem markerItem1 = new TMapMarkerItem();
        TMapPoint tMapPoint1 = new TMapPoint(MarkLat, MarkLon);

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_assistant_photo_black_24dp);

        markerItem1.setIcon(bitmap); // 마커 아이콘 지정
        markerItem1.setPosition(0.5f, 1.0f); // 마커의 중심점을 중앙, 하단으로 설정
        markerItem1.setTMapPoint(tMapPoint1); // 마커의 좌표 지정
        markerItem1.setName("검색한 주소"); // 마커의 타이틀 지정
        tMapView.addMarkerItem("markerItem1", markerItem1); // 지도에 마커 추가
        tMapView.setCenterPoint(MarkLon, MarkLat);  // Lon, Lat 순으로 되어있으니 주의!!!!

        CreateDialog(tMapView, markerItem1, tMapPoint1);

    }

    public void CreateDialog(TMapView TMView, TMapMarkerItem TMMItem, TMapPoint TMPoint) {

        Log.d("Location", "Log - Lot: " + MarkLat + "Lon :" + MarkLon);

        TMView.setOnLongClickListenerCallback(new OnLongClickListenerCallback() {
            @Override
            public void onLongPressEvent(ArrayList<TMapMarkerItem> arrayList, ArrayList<TMapPOIItem> arrayList1, TMapPoint tMapPoint) {
                show();
            }

        });
    }

    void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddressMarkActivity.this);
        builder.setTitle("마커 위치 확인");
        builder.setMessage("이 위치로 정하겠습니까?");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "예를 선택했습니다.", Toast.LENGTH_LONG).show();

                        Intent SetTextIntent = new Intent(getApplicationContext(), SetTextActivity.class);
                        SetTextIntent.putExtra("LvName", MarkName);
                        startActivity(SetTextIntent); // text 변경하는 activity로 이동
                        // SetTextIntent 안에 goHome();

                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "아니오를 선택했습니다.", Toast.LENGTH_LONG).show();
                    }
                });
        builder.show();
    }



}

