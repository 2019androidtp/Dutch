package com.example.moai_kang.geocoding;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.example.moai_kang.geocoding.MainActivity;

import java.util.List;

/**
 * Created by Moai_Kang on 2019-08-05.
 */

public class third_activity extends Activity{


    Geocoder coder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);
        coder = new Geocoder(this);
    }

    public void onClick(View v)
    {
        List<Address> list = null;

    }



}

