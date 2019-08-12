package com.example.moai_kang.geocoding;

import android.content.Intent;
import android.location.Address;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class MainActivity extends AppCompatActivity {

    EditText search_editText;
    Button search_btn;
    public static String address_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        search_editText = (EditText)findViewById(R.id.address_EditBox) ;
        search_btn = (Button)findViewById(R.id.searchBtn);


        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                address_text = search_editText.getText().toString(); //버튼 클릭시 EditText에 있는 Text값을 address_text에 String자료형으로 저장

                Intent second_intent = new Intent(getApplicationContext(),second_activity.class);
                startActivity(second_intent);
            }
        });


    }
}
