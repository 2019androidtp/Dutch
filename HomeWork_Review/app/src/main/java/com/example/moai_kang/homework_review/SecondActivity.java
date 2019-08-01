package com.example.moai_kang.homework_review;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Moai_Kang on 2019-07-30.
 */

public class SecondActivity extends Activity{

    Button toastBtn,internetBtn,finishBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        toastBtn = (Button)findViewById(R.id.ToastBtn);
        internetBtn = (Button)findViewById(R.id.InternetBtn);
        finishBtn = (Button)findViewById(R.id.Finish);

        toastBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "버튼을 눌러부렀어",Toast.LENGTH_SHORT).show();
            }
        });

        internetBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                try{

                    Intent internetintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.naver.com"));
                    startActivity(internetintent);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "error",Toast.LENGTH_SHORT).show();
                }


            }
        });

        finishBtn.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                finish();
            }
        });

    }
}
