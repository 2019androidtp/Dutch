package com.example.dutch.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dutch.ArraySavingClass.array_saving_class;
import com.example.dutch.R;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;

import static com.example.dutch.ArraySavingClass.array_saving_class.buttonNumArr;
import static com.example.dutch.ArraySavingClass.array_saving_class.final_Point_size;


// 이 액티비티의 역할: 주소로 검색 버튼을 눌렀을 때 주소를 검색하는 창이 나오고 검색한 문자열을 통해 실제 주소지를 검색하고 다음 액티비티로 넘겨줌

public class MainActivity extends AppCompatActivity {

	static int POIitemSize;
	static String POIResult[] = new String[100]; //POI의 이름을 담는 배열
	static String AddressResult[] = new String[100]; //POI의 주소를 담는 배열
	static double POILat[]= new double[100]; //POI의 Latitude를 담는 배열
	static double POILon[]= new double[100]; //POI의 Longitude를 담는 배열
	static int btnClickSize[] = new int[6]; // 배열의 인덱스에 해당하는 버튼을 눌러 값을 저장했었는지 체크하는 배열 ex) btnClickSize[1] = 1 이면 첫번째 버튼에 값이 들어갔었다는 것.
	static Button findAddressbtn[] = new Button[6]; //위치를 검색하기 위한 5개의 버튼, 구현상의 편의를 위해 index 0은 사용하지 않는다.

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button findCenterBtn = (Button)findViewById(R.id.searchAllMarkerBtn);

		TMapView tMapView = new TMapView(this); // key값 설정을 위한 tmapView 생성
		tMapView.setSKTMapApiKey( "f8e29016-57fd-4d05-b929-ebf16128f93f" ); // api key 설정

		findAddressbtn[1] = (Button)findViewById(R.id.searchBtn1);
		findAddressbtn[2] = (Button)findViewById(R.id.searchBtn2);
		findAddressbtn[3] = (Button)findViewById(R.id.searchBtn3);
		findAddressbtn[4] = (Button)findViewById(R.id.searchBtn4);
		findAddressbtn[5] = (Button)findViewById(R.id.searchBtn5);

		findCenterBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) { //버튼을 누르면 좌표들의 무게중심 계산

				if(array_saving_class.centerOfIt > 1) { // 입력된 주소가 2개 이상 일때
					for(int i = 0 ; i < final_Point_size ; i++){
						array_saving_class.totalLat += array_saving_class.final_Point[buttonNumArr.get(i)].getLatitude(); // 마지막 지점의 latitude값을 totalLat에 더한다.
						array_saving_class.totalLon += array_saving_class.final_Point[buttonNumArr.get(i)].getLongitude(); // 마지막 지점의 longitude totalLon에 더한다.


					}

					array_saving_class.center_point.setLatitude( array_saving_class.totalLat / final_Point_size); // 중앙지점을 이제까지 다 더한 값에 지점들의 크기로 나눈다.
					array_saving_class.center_point.setLongitude( array_saving_class.totalLon / final_Point_size);


					array_saving_class.totalLat = array_saving_class.totalLon = 0; // 다음번에 중간지점을 찾을 수 있게 값을 초기화

					Intent letsGoToCenterPoint = new Intent(getApplicationContext(), CenterActivity.class);
					startActivity(letsGoToCenterPoint);
				}
				else{ //입력된 주소가 한 개 이하일 때
					Toast.makeText(MainActivity.this, "주소 검색을 먼저 해주세요!", Toast.LENGTH_LONG).show();
				}


			}


		});

		findAddressbtn[1].setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				array_saving_class.buttonNum = 1; // 해당 버튼이 몇번째 버튼인지 넘겨줌
				findAddress(); // 주소 검색 다이얼로그 띄우는 메소드 호출
			}

		});

		findAddressbtn[2].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				array_saving_class.buttonNum = 2;
				findAddress();
			}
		});

		findAddressbtn[3].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				array_saving_class.buttonNum = 3;
				findAddress();
			}
		});

		findAddressbtn[4].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				array_saving_class.buttonNum = 4;
				findAddress();
			}
		});

		findAddressbtn[5].setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				array_saving_class.buttonNum = 5;
				findAddress();
			}
		});

		for(int i = 1; i<=5 ; i++) {

			if(array_saving_class.buttonName[i] != null) { // 버튼에 해당하는 텍스트가 주어지지 않았으면
				findAddressbtn[i].setText(array_saving_class.buttonName[i]); // buttonName[i]에 해당하는 String값을 버튼에 할당
			}
		}

	}

	public void findAddress() { // 주소 검색을 하는 메소드

		AlertDialog.Builder addressSearchBuilder = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
		addressSearchBuilder.setTitle("주소 검색");

		final EditText userInput = new EditText(this); // EditText로 사용자에게서 받음
		addressSearchBuilder.setView(userInput);

		addressSearchBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				final String strData = userInput.getText().toString(); // EditText에서 받은 String값을 strData에 저장

				TMapData tmapData = new TMapData();

				tmapData.findAllPOI(strData, new TMapData.FindAllPOIListenerCallback() {
					@Override
					public void onFindAllPOI(final ArrayList<TMapPOIItem> poiItem) {

						if(poiItem.size() == 0){ // 검색결과 없을 시
							Handler toastHandler = new Handler(Looper.getMainLooper());
							toastHandler.postDelayed(new Runnable() {
								@Override
								public void run() {
									Toast.makeText(getApplicationContext(), "검색 결과가 없습니다.", Toast.LENGTH_LONG).show();
								}
							}, 0);
						}
						else {
							for (int i = 0; i < poiItem.size(); i++) {

								TMapPOIItem item = poiItem.get(i);

								POIResult[i] = item.getPOIName(); // POIResult[i]에 item에서 가져온 POI 이름을 저장
								AddressResult[i] = item.getPOIAddress().replace("null", ""); // AddressResult[i]에 item에서 가져온 POI 주소를 저장
								POILat[i] = item.getPOIPoint().getLatitude(); // POI지점에 위도를 POILat[i]에 저장
								POILon[i] = item.getPOIPoint().getLongitude(); // POI지점에 경도를 POILon[i]에 저장
								POIitemSize = poiItem.size(); // poiItem값을 전해주기 위해 POIitemSize에 저장

							}


							Intent ListViewIntent = new Intent(getApplicationContext(), ListViewActivity.class);
							startActivity(ListViewIntent); // 리스트뷰 띄우는 액티비티로 이동

						}

					}

				});

			}

		});

		addressSearchBuilder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		addressSearchBuilder.show();

	}

}


