package com.example.dutch.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dutch.ArraySavingClass.array_saving_class;
import com.example.dutch.CreateMethodClass.Dutch_Mark;
import com.example.dutch.R;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;

import static com.example.dutch.Activity.AddressMarkActivity.markerImage;
import static com.example.dutch.Activity.MainActivity.btnClickSize;
import static com.example.dutch.ArraySavingClass.array_saving_class.alTMapPoint;
import static com.example.dutch.ArraySavingClass.array_saving_class.alTMapPoint_size;
import static com.example.dutch.ArraySavingClass.array_saving_class.buttonNum;
import static com.example.dutch.ArraySavingClass.array_saving_class.buttonNumArr;
import static com.example.dutch.ArraySavingClass.array_saving_class.nameOfIt;

public class CenterActivity extends AppCompatActivity {

    TMapView tMapView;
    TextView address_textView;
    static TextView name_textView;
    Bitmap centerMarkerImage;
    TMapPoint centerP;
    Button findInfraBtn;
    boolean findInfraFlag = false;
    static int infraSize = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);

        LinearLayout linearLayoutTmap = (LinearLayout) findViewById(R.id.mapviewCenter);
        tMapView = new TMapView(this);
        name_textView = (TextView) findViewById(R.id.nameOfLocation);
        address_textView = (TextView) findViewById(R.id.nameOfAddress);
        findInfraBtn =(Button) findViewById(R.id.findInfraBtn);
        linearLayoutTmap.addView(tMapView);

        centerMarkerImage = BitmapFactory.decodeResource(this.getResources(), R.drawable.group6);

        tMapView.setCenterPoint(array_saving_class.center_point.getLongitude(), array_saving_class.center_point.getLatitude()); // 중간지점의 좌표값을 지정해줌


        TMapMarkerItem center = new TMapMarkerItem();

        // 중간지점 좌표 설정
        centerP = new TMapPoint(array_saving_class.center_point.getLongitude(), array_saving_class.center_point.getLatitude());

        center.setIcon(centerMarkerImage); // 중간지점 마커 아이콘 지정
        center.setCanShowCallout(true); // 풍선뷰의 사용여부를 설정(사용).
        center.setCalloutSubTitle("중간지점 입니다."); // 풍선뷰 보조메시지 설정



        center.setCalloutTitle("중간지점 "); // 풍선뷰 제목 설정
        center.setAutoCalloutVisible(true); // 풍선뷰 자동 활성화

        // 마커의 좌표 지정
        center.setTMapPoint(centerP); // 마커 위,경도 설정
        center.setVisible(TMapMarkerItem.VISIBLE); // 마커 아이콘을 보이게 설정

        //지도에 마커 추가
        tMapView.addMarkerItem("markerItem", center);
        tMapView.setCenterPoint(centerP.getLongitude(), centerP.getLatitude()); // 위도경도 바뀐거 조심
        tMapView.setZoomLevel(10); // 줌레벨 설정


        // 중간지점에 자동차경로 추가
        Dutch_Mark.polyLineReturn(tMapView, alTMapPoint_size, centerP);

        // 모든마크 표시
        Dutch_Mark.allMarkReturn(markerImage, tMapView, alTMapPoint, nameOfIt, buttonNum, btnClickSize, buttonNumArr);

        TMapData forFindStation = new TMapData(); // 인근 지하철을 찾은 데이터를 넣을 TMapData
        TMapPoint centerPoint = tMapView.getCenterPoint();


        // 주변에 있는 지하철역을 검색
        forFindStation.findAroundNamePOI(centerPoint, "지하철", 50, 10, new TMapData.FindAroundNamePOIListenerCallback() {
            @Override
            public void onFindAroundNamePOI(ArrayList<TMapPOIItem> POI_item) {
               try{
                   if(POI_item == null) {
                       name_textView.setText("중간지점의 주소가 없습니다."); // 중간지점에서 주변시설을 검색했을 때 결과값이 하나도 없다면 textView에 중간지점의 주소가
                   }
                   else {
                       name_textView.setText("인근 지하철 역 : " + POI_item.get(0).getPOIName()); // 중간지점에서 가장 가까운 지하철역을 textView에 보여줌
                   }
               }catch (Exception e){
                   e.printStackTrace();
               }
            }
        });

        // 주변시설 확인하기 버튼클릭 이벤트 처리
        findInfraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(findInfraFlag == false) {
                    findInfraFlag = true;
                    TMapData tmapdata = new TMapData();
                    TMapPoint point = tMapView.getCenterPoint(); // 현재 TmapView에 CenterPoint를 가져옴

                    // CenterPoint를 기준으로 놀거리와 식음료 관련 주변시설들을 검색함. (주변 검색 허용거리를 50, 찾을 수 있는 주변시설의 개수를 10개로 설정)
                    tmapdata.findAroundNamePOI(point, "놀거리;식음료", 50, 10, new TMapData.FindAroundNamePOIListenerCallback() {
                        @Override
                        public void onFindAroundNamePOI(ArrayList<TMapPOIItem> POI_item) {

                            if (POI_item == null) { // 주변시설을 검색할 수 없을 때


                                // 토스트로 검색할 주변시설이 없음을 표시
                                Toast.makeText(getApplicationContext(), "근처에 검색할 주변시설이 없습니다.", Toast.LENGTH_SHORT).show();


                            }
                            else { // 주변시설이 정상적으로 검색되었을 때

                                for (int i = 0; i < POI_item.size(); i++) {

                                    TMapPOIItem item = POI_item.get(i);
                                    array_saving_class.infraList[i] = item.getPOIName(); // 주변시설 리스트에 주변 주변시설 데이터들을 추가
                                    array_saving_class.infraAddress[i] = item.getPOIAddress().replace("null", ""); // 주변시설 주소 리스트에 주변시설 주소 데이터들을 추가
                                    array_saving_class.infraTMapPoint[i] = item.getPOIPoint(); // 주변시설 좌표 리스트에 주변시설 좌표 데이터들을 추가
                                    array_saving_class.infraLat[i]= item.getPOIPoint().getLatitude(); // 주변시설 위도 리스트에 주변시설 위도 데이터들을 추가
                                    array_saving_class.infraLon[i]= item.getPOIPoint().getLongitude(); // 주변시설 경도 리스트에 주변시설 경도 데이터들을 추가
                                    infraSize = POI_item.size(); // 주변시설을 검색한 크기를 infraSize에 담음

                                }

                                Intent InfraListIntent = new Intent(getApplicationContext(), infraListActivity.class);
                                startActivity(InfraListIntent);
                                findInfraFlag = false; // infraList에 계속해서 값이 씌워지는걸 방지하는 flag

                            }
                        }
                    });
                }
                else{

                }
            }
        });

    }



}
