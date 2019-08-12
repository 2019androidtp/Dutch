package com.example.moai_kang.geocoding;

import android.app.Activity;
import android.database.DataSetObserver;
import android.location.Address;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Moai_Kang on 2019-08-05.
 */

public class second_activity extends Activity{

    ListView my_listview;
    ArrayList<String> test_Array = new ArrayList<String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        my_listview =(ListView)findViewById(R.id.listView);


        test_Array.add("근우");
        test_Array.add("이식");
        test_Array.add("현주");
        test_Array.add("주현");

        ArrayAdapter my_adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1, test_Array);
        my_listview.setAdapter(my_adapter);


    }
}
