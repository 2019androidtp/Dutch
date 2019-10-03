package com.example.apicollaboration.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.apicollaboration.ListViewFunctionClass.ListViewAdapter;
import com.example.apicollaboration.R;
import com.skt.Tmap.TMapPoint;

import static com.example.apicollaboration.Activity.MainActivity.AddressResult;
import static com.example.apicollaboration.Activity.MainActivity.POILat;
import static com.example.apicollaboration.Activity.MainActivity.POILon;
import static com.example.apicollaboration.Activity.MainActivity.POIResult;
import static com.example.apicollaboration.Activity.MainActivity.POIitemSize;

public class infraListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infra_list);

        final ListView InfraListView;
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array_saving_class.infraList) ;


        InfraListView = (ListView) findViewById(R.id.infraListView);
        InfraListView.setAdapter(arrayAdapter); // 리스트뷰에 어답터 연결

        InfraListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str  = array_saving_class.infraList.get(i);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?q="+str));
                startActivity(intent);
            }


        });


    }

}

