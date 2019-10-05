package com.example.apicollaboration.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.example.apicollaboration.ArraySavingClass.array_saving_class.alTMapPoint;

import com.example.apicollaboration.ArraySavingClass.array_saving_class;
import com.example.apicollaboration.R;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

public class AddressMarkActivity extends AppCompatActivity {
	TMapView tMapView;
	TextView address_textView;
	TextView name_textView;
	Button yesBtn;
	//Button noBtn;
	Bitmap bitmap;
	Bitmap temp;
	TMapPoint centerP;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address_mark_acitvity);
		LinearLayout linearLayoutTmap = (LinearLayout) findViewById(R.id.mapview);
		tMapView = new TMapView(this);
		name_textView = (TextView) findViewById(R.id.nameOfLocation);
		address_textView = (TextView) findViewById(R.id.nameOfAddress);

		linearLayoutTmap.addView(tMapView);

		bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.group6);

		temp = BitmapFactory.decodeResource(this.getResources(), R.drawable.group6);

		markReturn();



			address_textView.setText(array_saving_class.addressOfIt.get(array_saving_class.addressOfIt.size() - 1));
			name_textView.setText(array_saving_class.nameOfIt.get(array_saving_class.nameOfIt.size() - 1));

			tMapView.setCenterPoint(alTMapPoint.get(alTMapPoint.size() - 1).getLongitude(), alTMapPoint.get(alTMapPoint.size() - 1).getLatitude());



		yesBtn = (Button) findViewById(R.id.yesBtn);
		//noBtn = (Button) findViewById(R.id.noBtn);

		yesBtn.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View view) {
				array_saving_class.final_location.add(array_saving_class.nameOfIt.get(array_saving_class.addressOfIt.size() - 1));
				array_saving_class.final_Point.add(new TMapPoint(alTMapPoint.get(alTMapPoint.size() - 1).getLongitude(), alTMapPoint.get(alTMapPoint.size() - 1).getLatitude()));
				Intent goToMain = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(goToMain);
			}
		});

		/*
		noBtn.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View view) {
				array_saving_class.addressOfIt.remove(array_saving_class.addressOfIt.get(array_saving_class.addressOfIt.size() - 1));
				alTMapPoint.remove(alTMapPoint.get(alTMapPoint.size() - 1));
				Intent goToMain = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(goToMain);
			}
		});
		*/
		// 중간지점 체크 후 테스트를 위해 yes no 버튼을 빼놨습니다. 수정해야합니다.
		// no 버튼 주석 처리 해놨습니다.

	}

	// 자동차 경로 호출시 외부에서 Thread를 통해서 호출해줘야 정상적으로 실행



	public void markReturn() {

		for (int i = 0; i < alTMapPoint.size(); i++) {

			TMapMarkerItem markerItem1 = new TMapMarkerItem();
			// 마커 아이콘 지정
			markerItem1.setIcon(bitmap);
			markerItem1.setCanShowCallout(true);
			markerItem1.setCalloutSubTitle("위치:" + array_saving_class.nameOfIt.get(array_saving_class.nameOfIt.size() - 1)); // 위치 자동으로 찍히게 수정해야함
			markerItem1.setCalloutTitle("좌표 " + Integer.toString(i + 1) + "번");
			markerItem1.setCalloutRightButtonImage(temp);

			markerItem1.setAutoCalloutVisible(true);
			// 마커의 좌표 지정
			markerItem1.setTMapPoint(alTMapPoint.get(i));
			//지도에 마커 추가
			tMapView.addMarkerItem("markerItem" + i, markerItem1);
			tMapView.setCenterPoint(alTMapPoint.get(i).getLatitude(), alTMapPoint.get(i).getLatitude());

		}
	}

	@Override
	public void onBackPressed() {
		Intent backMainIntent = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(backMainIntent);
	}
}
