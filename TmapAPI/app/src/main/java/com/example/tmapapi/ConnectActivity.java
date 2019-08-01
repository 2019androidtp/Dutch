package com.example.tmapapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConnectActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_connect);

    Button MainButton = (Button)findViewById(R.id.MainBtn);
    Button SubButton = (Button)findViewById(R.id.SubBtn);

    MainButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent MainIntent = new Intent(ConnectActivity.this,MainActivity.class);
        ConnectActivity.this.startActivity(MainIntent);

      }
    });
    SubButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent SubIntent = new Intent(ConnectActivity.this,SubActivity.class);
        ConnectActivity.this.startActivity(SubIntent);


      }
    });



  }
}
