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

public class SetTextActivity extends AppCompatActivity {

    private String MarkName;
    private TextView mTextView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MarkName = getIntent().getStringExtra("LvName");
        // String text = "I want to change it";
        Log.d("Sample", "Log - Sample : " + MarkName); // 여기까지 값 저장돼!

        mTextView1 = findViewById (R.id.textView1);
        mTextView1.setText(MarkName);
        Log.d("mTextView", "Log - findViewById1" + MarkName);

        Intent GoToHomeIntent = new Intent(getApplicationContext(), GoToHomeActivity.class);
        GoToHomeIntent.putExtra("LvName", MarkName);
        startActivity(GoToHomeIntent); //


    }



}

