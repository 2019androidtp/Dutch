package com.example.moai_kang.geocoding;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.text.Editable;



public class MainActivity extends AppCompatActivity {

    EditText address;
    android.text.Editable address_text;
    Button search_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        address = (EditText)findViewById(R.id.address_EditBox) ;
        search_btn = (Button)findViewById(R.id.searchBtn);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                address_text = address.getText();
                Intent third_intent = new Intent(getApplicationContext(),third_activity.class);
                startActivity(third_intent);
            }
        });


    }
}
