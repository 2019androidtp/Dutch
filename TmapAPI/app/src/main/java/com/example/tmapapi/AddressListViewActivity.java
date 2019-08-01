package com.example.tmapapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class AddressListViewActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_address_list_view);

    ListView AddressListView;
    ListViewAdapter adapter;
    String[] getPOI = new String[100];
    String[] getAddress = new String[100];


    adapter = new ListViewAdapter();

    AddressListView = (ListView)findViewById(R.id.Addresslistview);
    AddressListView.setAdapter(adapter);
    getPOI = getIntent().getStringArrayExtra("POIResult");
    getAddress = getIntent().getStringArrayExtra("AddressResult");

    for(int i=0; i< 20; i++) {
      adapter.addItem(getPOI[i], getAddress[i]);
    }


    AddressListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //        // 값을 넘겨준다.
      }
    });


    AddressListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView parent, View view, int position, long id) {
        ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);


      }
    });

  }
}


