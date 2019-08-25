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

public class GoToHomeActivity extends AppCompatActivity {

    private String MarkName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MarkName = getIntent().getStringExtra("LvName");
        setContentView(R.layout.activity_main);


        Log.d("gotoHome", "Log - gotoHome");
        Intent intentHome = new Intent(this, MainActivity.class);

        //이거떄문에 초기화 되는 거일수도 있어서 지워봤음, 근데 여전히 초기화됨
        //intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intentHome.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intentHome.putExtra("LvName", MarkName);
        startActivity(intentHome);


        finish();
    }

}

