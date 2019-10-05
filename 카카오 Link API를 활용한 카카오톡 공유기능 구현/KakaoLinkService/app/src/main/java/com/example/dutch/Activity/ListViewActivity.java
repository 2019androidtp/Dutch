package com.example.dutch.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dutch.ArraySavingClass.array_saving_class;
import com.example.dutch.ListViewFunctionClass.ListViewAdapter;
import com.example.dutch.R;
import com.skt.Tmap.TMapPoint;

import static com.example.dutch.Activity.MainActivity.AddressResult;
import static com.example.dutch.Activity.MainActivity.POILat;
import static com.example.dutch.Activity.MainActivity.POILon;
import static com.example.dutch.Activity.MainActivity.POIResult;
import static com.example.dutch.Activity.MainActivity.POIitemSize;
import static com.example.dutch.Activity.MainActivity.btnClickSize;
import static com.example.dutch.ArraySavingClass.array_saving_class.buttonNum;

public class ListViewActivity extends AppCompatActivity {

	static ListViewAdapter listViewAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);

		final ListView AddressListView;


		listViewAdapter = new ListViewAdapter();

		AddressListView = (ListView) findViewById(R.id.Addresslistview);
		AddressListView.setAdapter(listViewAdapter); // 리스트뷰에 어답터 연결

		for (int i = 0; i < POIitemSize; i++) {
			listViewAdapter.addItem(POIResult[i], AddressResult[i], POILat[i], POILon[i]);
		} // 어답터에 주소의 이름과 상세주소, 위도 경도 추가
		AddressListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) { //리스트뷰를 클릭했을때

				TMapPoint tMapPoint = new TMapPoint(POILat[position],POILon[position]); //각 아이템의 좌표

				String resultAddress = POIResult[position]; //각 아이템의 주소
				boolean isExist = false; //전에 설정한 주소중에 같은 주소가 있는지를 판단하는 변수

				for(int i = 0 ; i< 5; i++) { // 전에 설정한 주소 중 같은 주소가 있으면 isExist를 true로 바꿈

					if(resultAddress.equals(array_saving_class.nameOfIt[i])) {
						isExist = true;
						break;
					}

				}

				if(isExist == true){ 				// 전에 설정한 주소 중 같은 주소가 있을 때
					Toast.makeText(getApplicationContext(), "이미 이 주소는 설정이 되었습니다!", Toast.LENGTH_SHORT).show();
					isExist = false;
				}
				else { //같은 주소가 없으면

					array_saving_class.alTMapPoint[buttonNum] = tMapPoint; //alTMapPoint의 버튼 인덱스 위치에 tMapPoint 저장
					array_saving_class.nameOfIt[buttonNum] = POIResult[position]; //nameOfIt의 버튼 인덱스 위치에 POIResult의 position 인덱스에 저장된 값 저장
					array_saving_class.addressOfIt[buttonNum]= AddressResult[position]; //addressOfIt의 버튼 인덱스 위치에 AddressResult의 position 인덱스에 저장된 값 저장

					if(btnClickSize[buttonNum] == 1) { // 기존의 배열에 값이 있으면

					}
					else { // 기존의 배열에 값이 없으면
						array_saving_class.alTMapPoint_size++; //lTMapPoint_size에 1을 더한다.
						array_saving_class.nameOfIt_size++; //nameOfIt_size에 1을 더한다.
						array_saving_class.addressOfIt_size++; //addressOfIt_size에 1을 더한다.
					}
						Intent MarkIntent = new Intent(getApplicationContext(), AddressMarkActivity.class); //검색한 위치를 띄우는 화면으로 보낸다.
						startActivity(MarkIntent);


				}

			}
		});

	}

}



