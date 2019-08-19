package com.example.apicollaboration.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.apicollaboration.ListViewFunctionClass.ListViewAdapter;
import com.example.apicollaboration.R;

import static com.example.apicollaboration.Activity.MainActivity.AddressResult;
import static com.example.apicollaboration.Activity.MainActivity.POILat;
import static com.example.apicollaboration.Activity.MainActivity.POILon;
import static com.example.apicollaboration.Activity.MainActivity.POIResult;
import static com.example.apicollaboration.Activity.MainActivity.POIitemSize;

public class ListViewActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list_view);

    final ListView AddressListView;
    ListViewAdapter listViewAdapter;

    listViewAdapter = new ListViewAdapter();

    AddressListView = (ListView) findViewById(R.id.Addresslistview);
    AddressListView.setAdapter(listViewAdapter); // 리스트뷰에 어답터 연결

    for (int i = 0; i < POIitemSize; i++) {
      listViewAdapter.addItem(POIResult[i], AddressResult[i], POILat[i], POILon[i]);
    } // 어답터에 주소의 이름과 상세주소, 위도 경도 추가
    AddressListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        Intent MarkIntent = new Intent(getApplicationContext(),AddressMarkAcitvity.class);
        MarkIntent.putExtra("LvLat", POILat[position]); // 선택한 postion에 따른 위도 경도를 인텐트로 넘겨줌
        MarkIntent.putExtra("LvLon", POILon[position]);

        startActivity(MarkIntent);

        // Log.d("Position", "Position : " + position);
      }
    });

  }
}

