package com.example.moai_kang.geocoding;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Moai_Kang on 2019-08-05.
 */

public class third_activity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        Uri uri = Uri.parse(String.format("geo:%f, %f?z=10",37.30,127.2));
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}
