package com.example.dutch.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dutch.ArraySavingClass.array_saving_class;
import com.example.dutch.ListViewFunctionClass.ListViewAdapter;
import com.example.dutch.R;
import com.kakao.kakaolink.v2.KakaoLinkResponse;
import com.kakao.kakaolink.v2.KakaoLinkService;
import com.kakao.message.template.ButtonObject;
import com.kakao.message.template.ContentObject;
import com.kakao.message.template.LinkObject;
import com.kakao.message.template.LocationTemplate;
import com.kakao.network.ErrorResult;
import com.kakao.network.callback.ResponseCallback;
import com.kakao.util.helper.log.Logger;
import com.skt.Tmap.TMapData;



import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import static com.example.dutch.Activity.CenterActivity.cafeBtnClicked;
import static com.example.dutch.Activity.CenterActivity.cafeExistData;
import static com.example.dutch.Activity.CenterActivity.cultureBtnClicked;
import static com.example.dutch.Activity.CenterActivity.cultureExistData;
import static com.example.dutch.Activity.CenterActivity.foodBtnClicked;
import static com.example.dutch.Activity.CenterActivity.foodExistData;
import static com.example.dutch.Activity.CenterActivity.transBtnClicked;
import static com.example.dutch.Activity.CenterActivity.transExistData;
import static com.example.dutch.Activity.CenterActivity.transSize;
import static com.example.dutch.Activity.CenterActivity.cafeSize;
import static com.example.dutch.Activity.CenterActivity.foodSize;
import static com.example.dutch.Activity.CenterActivity.cultureSize;

public class infraListActivity extends AppCompatActivity {


  @Override
  protected void onResume() {
    super.onResume();
    overridePendingTransition(0,0); // 액티비티를 종료할 때 애니메이션을 없애줌
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_infra_list);

    RelativeLayout infraRelativeLayout = (RelativeLayout)findViewById(R.id.InfraRelativeLayout);

    final ImageButton transBtn = (ImageButton)findViewById(R.id.transBtn);
    final ImageButton cafeBtn = (ImageButton)findViewById(R.id.cafeBtn);
    final ImageButton foodBtn = (ImageButton)findViewById(R.id.foodBtn);
    final ImageButton cultureBtn = (ImageButton)findViewById(R.id.cultureBtn);

    final ListView transListView;
    final ListView cafeListView;
    final ListView foodListView;
    final ListView cultureListView;


    ListViewAdapter transListViewAdapter = new ListViewAdapter();
    ListViewAdapter cafeListViewAdapter = new ListViewAdapter();
    ListViewAdapter foodListViewAdapter = new ListViewAdapter();
    ListViewAdapter cultureListViewAdapter = new ListViewAdapter();

    transListView = (ListView) findViewById(R.id.transListView);
    cafeListView = (ListView) findViewById(R.id.cafeListView);
    foodListView = (ListView) findViewById(R.id.foodListView);
    cultureListView = (ListView) findViewById(R.id.cultureListView);

    transListView.setAdapter(transListViewAdapter);
    cafeListView.setAdapter(cafeListViewAdapter);
    foodListView.setAdapter(foodListViewAdapter);
    cultureListView.setAdapter(cultureListViewAdapter);

    if(transBtnClicked == true){ // 대중교통 버튼이 클릭되었는지 확인하고 클릭되었다면

      infraRelativeLayout.setBackground(null); // 처음 화면에 표시되는 텍스트를 없앰
      transBtn.setSelected(false); // false 일때 빨간색으로 대중교통 버튼의 이미지가 세팅됨
      cafeBtn.setSelected(true);
      foodBtn.setSelected(true);
      cultureBtn.setSelected(true);
      // 나머지 버튼들을 눌리지 않은 상태로 초기화시킴
      transBtn.setImageResource(R.drawable.tabicontrafficorg); // 인텐트를 띄워 이미지를 로딩할 때 대중교통 버튼을 눌려져있다는 모습의 빨간색 이미지로 세팅
      transListView.setVisibility(View.VISIBLE); // 대중교통 검색 리스트만 표시
      cafeListView.setVisibility(View.GONE);
      foodListView.setVisibility(View.GONE);
      cultureListView.setVisibility(View.GONE);
      // 나머지는 숨김

    }

    else if(cafeBtnClicked == true){ // 카페 버튼이 클릭되었는지 확인하고 클릭되었다면

      infraRelativeLayout.setBackground(null); // 처음 화면에 표시되는 텍스트를 없앰
      transBtn.setSelected(true);
      cafeBtn.setSelected(false); // false 일때 빨간색으로 카페 버튼의 이미지가 세팅됨
      foodBtn.setSelected(true);
      cultureBtn.setSelected(true);
      // 나머지 버튼들을 눌리지 않은 상태로 초기화시킴
      cafeBtn.setImageResource(R.drawable.tabiconcafeorg); // 인텐트를 띄워 이미지를 로딩할 때 카페 버튼을 눌려져있다는 모습의 빨간색 이미지로 세팅
      transListView.setVisibility(View.GONE);
      cafeListView.setVisibility(View.VISIBLE); // 카페 검색 리스트만 표시
      foodListView.setVisibility(View.GONE);
      cultureListView.setVisibility(View.GONE);
      // 나머지는 숨김

    }
    else if(foodBtnClicked == true){ // 음식점 버튼이 클릭되었는지 확인하고 클릭되었다면

      infraRelativeLayout.setBackground(null); // 처음 화면에 표시되는 텍스트를 없앰
      transBtn.setSelected(true);
      cafeBtn.setSelected(true);
      foodBtn.setSelected(false);
      cultureBtn.setSelected(true);
      // 나머지 버튼들을 눌리지 않은 상태로 초기화시킴
      foodBtn.setImageResource(R.drawable.tabiconrestaurantorg); // 인텐트를 띄워 이미지를 로딩할 때 음식점 버튼을 눌려져있다는 모습의 빨간색 이미지로 세팅
      transListView.setVisibility(View.GONE);
      cafeListView.setVisibility(View.GONE);
      foodListView.setVisibility(View.VISIBLE); // 음식점 검색 리스트만 표시
      cultureListView.setVisibility(View.GONE);
      // 나머지는 숨김

    }
    else if(cultureBtnClicked == true){ // 문화시설 버튼이 클릭되었는지 확인하고 클릭되었다면

      infraRelativeLayout.setBackground(null); // 처음 화면에 표시되는 텍스트를 없앰
      transBtn.setSelected(true);
      cafeBtn.setSelected(true);
      foodBtn.setSelected(true);
      cultureBtn.setSelected(false);
      // 나머지 버튼들을 눌리지 않은 상태로 초기화시킴
      cultureBtn.setImageResource(R.drawable.tabiconcultureorg); // 인텐트를 띄워 이미지를 로딩할 때 음식점 버튼을 눌려져있다는 모습의 빨간색 이미지로 세팅
      transListView.setVisibility(View.GONE);
      cafeListView.setVisibility(View.GONE);
      foodListView.setVisibility(View.GONE);
      cultureListView.setVisibility(View.VISIBLE); // 문화시설 검색 리스트만 표시
      // 나머지는 숨김

    }
    else{

      transBtn.setSelected(true);
      cafeBtn.setSelected(true);
      foodBtn.setSelected(true);
      cultureBtn.setSelected(true);
      // 모든 버튼을 눌리지 않은 상태로 초기화


    }



    for (int i = 0; i < transSize; i++) {
      transListViewAdapter.addItem(array_saving_class.transList[i], array_saving_class.transAddress[i], array_saving_class.transLat[i], array_saving_class.transLon[i]);
    }
    for (int i = 0; i < cafeSize; i++) {
      cafeListViewAdapter.addItem(array_saving_class.cafeList[i], array_saving_class.cafeAddress[i], array_saving_class.cafeLat[i], array_saving_class.cafeLon[i]);
    }
    for (int i = 0; i < foodSize; i++) {
      foodListViewAdapter.addItem(array_saving_class.foodList[i], array_saving_class.foodAddress[i], array_saving_class.foodLat[i], array_saving_class.foodLon[i]);
    }
    for (int i = 0; i < cultureSize; i++) {
      cultureListViewAdapter.addItem(array_saving_class.cultureList[i], array_saving_class.cultureAddress[i], array_saving_class.cultureLat[i], array_saving_class.cultureLon[i]);
    }
    // 어답터에 주변시설 주소의 이름과 상세주소, 위도와 경도 추가

    transBtn.setOnClickListener(new ImageButton.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(transExistData = false){ // 대중교통 검색결과 데이터가 존재하는지 확인하고 값이 존재하지 않으면
          Handler toastHandler = new Handler(Looper.getMainLooper());
          toastHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
              Toast.makeText(getApplicationContext(), "근처에 검색된 대중교통이 없습니다.", Toast.LENGTH_SHORT).show(); // 근처 대중교통이 없다고 표시

            }
          }, 0);
        }
        transListView.setVisibility(View.VISIBLE); // 대중교통 검색 리스트만 표시
        cafeListView.setVisibility(View.GONE);
        foodListView.setVisibility(View.GONE);
        cultureListView.setVisibility(View.GONE);

        view.postInvalidate(); // 뷰에 반영

        transBtnClicked = true; // 대중교통 버튼이 눌렸다는것을 true로 설정
        cafeBtnClicked = false;
        foodBtnClicked = false;
        cultureBtnClicked = false;
        // 나머지 버튼은 안눌려있는 것으로 값을 초기화

        Intent intent = getIntent(); // 리스트 갱신을 위해 인텐트로 액티비티를 다시 띄워줌
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); // 다시 액티비티를 띄울때 애니메이션을 없애줌
        finish(); // 현재 액티비티 종료
        startActivity(intent); // 새로운 액티비티 실행

      }
    }) ;

    cafeBtn.setOnClickListener(new ImageButton.OnClickListener() {
      @Override
      public void onClick(View view) {

        if(cafeExistData = false){ // 카페 검색결과 데이터가 존재하는지 확인하고 값이 존재하지 않으면
          Handler toastHandler = new Handler(Looper.getMainLooper());
          toastHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
              Toast.makeText(getApplicationContext(), "근처에 검색된 카페가 없습니다.", Toast.LENGTH_SHORT).show(); // 근처 카페가 없다고 표시

            }
          }, 0);
        }

        transListView.setVisibility(View.GONE);
        cafeListView.setVisibility(View.VISIBLE); // 카페 검색 리스트만 표시
        foodListView.setVisibility(View.GONE);
        cultureListView.setVisibility(View.GONE);

        transBtnClicked = false;
        cafeBtnClicked = true;
        foodBtnClicked = false;
        cultureBtnClicked = false;


        view.postInvalidate(); // 뷰에 반영

        transBtnClicked = false;
        cafeBtnClicked = true; // 카페 버튼이 눌렸다는것을 true로 설정
        foodBtnClicked = false;
        cultureBtnClicked = false;
        // 나머지 버튼은 안눌려있는 것으로 값을 초기화

        Intent intent = getIntent(); // 리스트 갱신을 위해 인텐트로 액티비티를 다시 띄워줌
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); // 다시 액티비티를 띄울때 애니메이션을 없애줌
        finish(); // 현재 액티비티 종료
        startActivity(intent); // 새로운 액티비티 실행

      }
    }) ;

    foodBtn.setOnClickListener(new ImageButton.OnClickListener() {
      @Override
      public void onClick(View view) {

        if(foodExistData = false){ // 음식점 검색결과 데이터가 존재하는지 확인하고 값이 존재하지 않으면
          Handler toastHandler = new Handler(Looper.getMainLooper());
          toastHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
              Toast.makeText(getApplicationContext(), "근처에 검색된 음식점이 없습니다.", Toast.LENGTH_SHORT).show(); // 근처 음식점이 없다고 표시

            }
          }, 0);
        }

        transListView.setVisibility(View.GONE);
        cafeListView.setVisibility(View.GONE);
        foodListView.setVisibility(View.VISIBLE); // 음식점 검색 리스트만 표시
        cultureListView.setVisibility(View.GONE);

        transBtnClicked = false;
        cafeBtnClicked = false;
        foodBtnClicked = true; // 음식점 버튼이 눌렸다는것을 true로 설정
        cultureBtnClicked = false;

        view.postInvalidate(); // 뷰에 반영

        transBtnClicked = false;
        cafeBtnClicked = false;
        foodBtnClicked = true;
        cultureBtnClicked = false;
        // 나머지 버튼은 안눌려있는 것으로 값을 초기화

        Intent intent = getIntent(); // 리스트 갱신을 위해 인텐트로 액티비티를 다시 띄워줌
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); // 다시 액티비티를 띄울때 애니메이션을 없애줌
        finish(); // 현재 액티비티 종료
        startActivity(intent); // 새로운 액티비티 실행

      }
    }) ;

    cultureBtn.setOnClickListener(new ImageButton.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(cultureExistData = false){ // 문화시설 검색결과 데이터가 존재하는지 확인하고 값이 존재하지 않으면
          Handler toastHandler = new Handler(Looper.getMainLooper());
          toastHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
              Toast.makeText(getApplicationContext(), "근처에 검색된 문화시설이 없습니다.", Toast.LENGTH_SHORT).show(); // 근처 문화시설이 없다고 표시

            }
          }, 0);
        }

        transListView.setVisibility(View.GONE);
        cafeListView.setVisibility(View.GONE);
        foodListView.setVisibility(View.GONE);
        cultureListView.setVisibility(View.VISIBLE); // 문화시설 검색 리스트만 표시

        view.postInvalidate(); // 뷰에 반영

          transBtnClicked = false;
          cafeBtnClicked = false;
          foodBtnClicked = false;
          cultureBtnClicked = true; // 문화시설 버튼이 눌렸다는것을 true로 설정
        // 나머지 버튼은 안눌려있는 것으로 값을 초기화

          Intent intent = getIntent(); // 리스트 갱신을 위해 인텐트로 액티비티를 다시 띄워줌
          intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP); // 다시 액티비티를 띄울때 애니메이션을 없애줌
          finish(); // 현재 액티비티 종료
          startActivity(intent); // 새로운 액티비티 실행

      }
    }) ;






    // transListView에 아이템이 클릭되면 카카오톡 공유로 넘어가는 기능
    transListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
        new Thread(new Runnable() { // convertGpsToAddress와 kakaoLinkService를 안정적으로 쓰려면 Thread를 써야한다.
          @Override
          public void run() {
            try {

              String kakaoName  = array_saving_class.transList[i]; // 카카오톡에 공유할 때 제목에 사용될 String값을 transList i번에서 가져옴

              String getKakaoAddress = null; // 주소값을 가져옴

              getKakaoAddress = new TMapData().convertGpsToAddress(array_saving_class.transTMapPoint[i].getLatitude(), array_saving_class.transTMapPoint[i].getLongitude());

              String[] kakaoAddress  = getKakaoAddress.split(" "); // 위도와 경도를 주소로 옮긴 값인 getKakaoAddress에 스페이스가 포함되어 있어 url에 데이터를 입력하면 페이지가 정상적으로 띄워지지않아서 스페이스를 뺀 주소를 제대로 얻어오기위한 String 배열을 생성.
              String kakaoAddressResult = new String(); // 최종적으로 배열에 담긴 값을 반환받음

              for(int i = 0; i < kakaoAddress.length; i++){
                  kakaoAddressResult += kakaoAddress[i]; // kakoAddress[i]에 닮긴 값들을 kakaoAddressResult에 옮겨담음
              }

              // LocationTemplate에 왼쪽 버튼을 설정
              ButtonObject leftButtonObject = new ButtonObject("앱으로 돌아가기", // 왼쪽 버튼의 표시될 텍스트를 설정
                                                                LinkObject.newBuilder()
                                                                        .setWebUrl("https://map.kakao.com/link/map/"+ kakaoAddress + "," + kakaoName) //
                                                                        .setMobileWebUrl("https://map.kakao.com/link/map/"+ kakaoAddress + "," + kakaoName)
                                                                        .build());

              LocationTemplate params = LocationTemplate.newBuilder(kakaoAddressResult , // 위치 확인시 보여줄 주소칸에 표시될 값을 설정해줌
                                                                    ContentObject.newBuilder(kakaoName, // 카카오톡 공유시 보여줄 ContentObject를 생성, 제목란에 선택한 주변시설의 명칭을 띄워줌
                                                                                             "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fk.kakaocdn.net%2Fdn%2FcN141f%2FbtqyK9q3rqe%2FK8UNSjqsKMBIT32jLFL1r1%2Fimg.png", // 카카오톡 공유시 보여지는 이미지 설정
                                                                                             LinkObject.newBuilder()

                                                                                                     .setWebUrl("https://map.kakao.com/link/map/"+ kakaoAddressResult + "," + array_saving_class.transTMapPoint[i].getLatitude()+ "," + array_saving_class.transTMapPoint[i].getLongitude()) // 만약 웹에서 버튼들의 기능이 수행이 안된다면 주어진 링크로 이동
                                                                                                     .setMobileWebUrl("https://map.kakao.com/link/map/"+ kakaoAddressResult + "," + array_saving_class.transTMapPoint[i].getLatitude()+ "," + array_saving_class.transTMapPoint[i].getLongitude()) // 만약 모바일에서 버튼들의 기능이 수행이 안된다면 주어진 링크로 이동
                                                                                                     .build())

                                                                            .setDescrption("https://map.kakao.com/link/map/"+ kakaoAddressResult + "," +array_saving_class.transTMapPoint[i].getLatitude()+ "," + array_saving_class.transTMapPoint[i].getLongitude()) // 카카오톡 공유시 설명칸에 하이퍼링크형식으로 웹상에서 지도를 띄워주게 설정
                                                                            .build())

                      .setAddressTitle(kakaoName) // 위치 확인시 보여줄 제목칸에 선택한 주변시설의 명칭을 띄워줌
                      .addButton(leftButtonObject) // 왼쪽버튼에 해당하는 ButtonObject를 추가

                      .build();

              Map<String, String> serverCallbackArgs = new HashMap<String, String>();
              serverCallbackArgs.put("user_id", "${current_user_id}");
              serverCallbackArgs.put("product_id", "${shared_product_id}");
              // 카카오 서버에 저장된 값들을 가져옴

              KakaoLinkService.getInstance().sendDefault(getApplicationContext(), params, serverCallbackArgs, new ResponseCallback<KakaoLinkResponse>() {
                @Override
                public void onFailure(ErrorResult errorResult) {
                  Logger.e(errorResult.toString());
                }

                @Override
                public void onSuccess(KakaoLinkResponse result) {
                  // 템플릿 밸리데이션과 쿼터 체크가 성공적으로 끝남. 톡에서 정상적으로 보내졌는지 보장은 할 수 없다. 전송 성공 유무는 서버콜백 기능을 이용하여야 한다.
                }
              });



            } catch (IOException e) {
              e.printStackTrace();
            } catch (ParserConfigurationException e) {
              e.printStackTrace();
            } catch (SAXException e) {
              e.printStackTrace();
            }
          }
        }).start();

      }


    });

    // cafeListView에 아이템이 클릭되면 카카오톡 공유로 넘어가는 기능
    cafeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
        new Thread(new Runnable() { // convertGpsToAddress와 kakaoLinkService를 안정적으로 쓰려면 Thread를 써야한다.
          @Override
          public void run() {
            try {

              String kakaoName  = array_saving_class.cafeList[i]; // 카카오톡에 공유할 때 제목에 사용될 String값을 cafeList i번에서 가져옴

              String getKakaoAddress = null; // 주소값을 가져옴

              getKakaoAddress = new TMapData().convertGpsToAddress(array_saving_class.cafeTMapPoint[i].getLatitude(), array_saving_class.cafeTMapPoint[i].getLongitude());

              String[] kakaoAddress  = getKakaoAddress.split(" "); // 위도와 경도를 주소로 옮긴 값인 getKakaoAddress에 스페이스가 포함되어 있어 url에 데이터를 입력하면 페이지가 정상적으로 띄워지지않아서 스페이스를 뺀 주소를 제대로 얻어오기위한 String 배열을 생성.
              String kakaoAddressResult = new String(); // 최종적으로 배열에 담긴 값을 반환받음

              for(int i = 0; i < kakaoAddress.length; i++){
                kakaoAddressResult += kakaoAddress[i]; // kakoAddress[i]에 닮긴 값들을 kakaoAddressResult에 옮겨담음
              }

              // LocationTemplate에 왼쪽 버튼을 설정
              ButtonObject leftButtonObject = new ButtonObject("앱으로 돌아가기", // 왼쪽 버튼의 표시될 텍스트를 설정
                                                               LinkObject.newBuilder()
                                                                       .setWebUrl("https://map.kakao.com/link/map/"+ kakaoAddress + "," + kakaoName) //
                                                                       .setMobileWebUrl("https://map.kakao.com/link/map/"+ kakaoAddress + "," + kakaoName)
                                                                       .build());

              LocationTemplate params = LocationTemplate.newBuilder(kakaoAddressResult , // 위치 확인시 보여줄 주소칸에 표시될 값을 설정해줌
                                                                    ContentObject.newBuilder(kakaoName, // 카카오톡 공유시 보여줄 ContentObject를 생성, 제목란에 선택한 주변시설의 명칭을 띄워줌
                                                                                             "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fk.kakaocdn.net%2Fdn%2FcN141f%2FbtqyK9q3rqe%2FK8UNSjqsKMBIT32jLFL1r1%2Fimg.png", // 카카오톡 공유시 보여지는 이미지 설정
                                                                                             LinkObject.newBuilder()

                                                                                                     .setWebUrl("https://map.kakao.com/link/map/"+ kakaoAddressResult + "," + array_saving_class.cafeTMapPoint[i].getLatitude()+ "," + array_saving_class.cafeTMapPoint[i].getLongitude()) // 만약 웹에서 버튼들의 기능이 수행이 안된다면 주어진 링크로 이동
                                                                                                     .setMobileWebUrl("https://map.kakao.com/link/map/"+ kakaoAddressResult + "," + array_saving_class.cafeTMapPoint[i].getLatitude()+ "," + array_saving_class.cafeTMapPoint[i].getLongitude()) // 만약 모바일에서 버튼들의 기능이 수행이 안된다면 주어진 링크로 이동
                                                                                                     .build())

                                                                            .setDescrption("https://map.kakao.com/link/map/"+ kakaoAddressResult + "," +array_saving_class.cafeTMapPoint[i].getLatitude()+ "," + array_saving_class.cafeTMapPoint[i].getLongitude()) // 카카오톡 공유시 설명칸에 하이퍼링크형식으로 웹상에서 지도를 띄워주게 설정
                                                                            .build())

                      .setAddressTitle(kakaoName) // 위치 확인시 보여줄 제목칸에 선택한 주변시설의 명칭을 띄워줌
                      .addButton(leftButtonObject) // 왼쪽버튼에 해당하는 ButtonObject를 추가

                      .build();

              Map<String, String> serverCallbackArgs = new HashMap<String, String>();
              serverCallbackArgs.put("user_id", "${current_user_id}");
              serverCallbackArgs.put("product_id", "${shared_product_id}");
              // 카카오 서버에 저장된 값들을 가져옴

              KakaoLinkService.getInstance().sendDefault(getApplicationContext(), params, serverCallbackArgs, new ResponseCallback<KakaoLinkResponse>() {
                @Override
                public void onFailure(ErrorResult errorResult) {
                  Logger.e(errorResult.toString());
                }

                @Override
                public void onSuccess(KakaoLinkResponse result) {
                  // 템플릿 밸리데이션과 쿼터 체크가 성공적으로 끝남. 톡에서 정상적으로 보내졌는지 보장은 할 수 없다. 전송 성공 유무는 서버콜백 기능을 이용하여야 한다.
                }
              });



            } catch (IOException e) {
              e.printStackTrace();
            } catch (ParserConfigurationException e) {
              e.printStackTrace();
            } catch (SAXException e) {
              e.printStackTrace();
            }
          }
        }).start();

      }


    });

    // foodListView에 아이템이 클릭되면 카카오톡 공유로 넘어가는 기능
    foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
        new Thread(new Runnable() { // convertGpsToAddress와 kakaoLinkService를 안정적으로 쓰려면 Thread를 써야한다.
          @Override
          public void run() {
            try {

              String kakaoName  = array_saving_class.foodList[i]; // 카카오톡에 공유할 때 제목에 사용될 String값을 foodList에 i번에서 가져옴

              String getKakaoAddress = null; // 주소값을 가져옴

              getKakaoAddress = new TMapData().convertGpsToAddress(array_saving_class.foodTMapPoint[i].getLatitude(), array_saving_class.foodTMapPoint[i].getLongitude());

              String[] kakaoAddress  = getKakaoAddress.split(" "); // 위도와 경도를 주소로 옮긴 값인 getKakaoAddress에 스페이스가 포함되어 있어 url에 데이터를 입력하면 페이지가 정상적으로 띄워지지않아서 스페이스를 뺀 주소를 제대로 얻어오기위한 String 배열을 생성.
              String kakaoAddressResult = new String(); // 최종적으로 배열에 담긴 값을 반환받음

              for(int i = 0; i < kakaoAddress.length; i++){
                kakaoAddressResult += kakaoAddress[i]; // kakoAddress[i]에 닮긴 값들을 kakaoAddressResult에 옮겨담음
              }

              // LocationTemplate에 왼쪽 버튼을 설정
              ButtonObject leftButtonObject = new ButtonObject("앱으로 돌아가기", // 왼쪽 버튼의 표시될 텍스트를 설정
                                                               LinkObject.newBuilder()
                                                                       .setWebUrl("https://map.kakao.com/link/map/"+ kakaoAddress + "," + kakaoName) //
                                                                       .setMobileWebUrl("https://map.kakao.com/link/map/"+ kakaoAddress + "," + kakaoName)
                                                                       .build());

              LocationTemplate params = LocationTemplate.newBuilder(kakaoAddressResult , // 위치 확인시 보여줄 주소칸에 표시될 값을 설정해줌
                                                                    ContentObject.newBuilder(kakaoName, // 카카오톡 공유시 보여줄 ContentObject를 생성, 제목란에 선택한 주변시설의 명칭을 띄워줌
                                                                                             "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fk.kakaocdn.net%2Fdn%2FcN141f%2FbtqyK9q3rqe%2FK8UNSjqsKMBIT32jLFL1r1%2Fimg.png", // 카카오톡 공유시 보여지는 이미지 설정
                                                                                             LinkObject.newBuilder()

                                                                                                     .setWebUrl("https://map.kakao.com/link/map/"+ kakaoAddressResult + "," + array_saving_class.foodTMapPoint[i].getLatitude()+ "," + array_saving_class.foodTMapPoint[i].getLongitude()) // 만약 웹에서 버튼들의 기능이 수행이 안된다면 주어진 링크로 이동
                                                                                                     .setMobileWebUrl("https://map.kakao.com/link/map/"+ kakaoAddressResult + "," + array_saving_class.foodTMapPoint[i].getLatitude()+ "," + array_saving_class.foodTMapPoint[i].getLongitude()) // 만약 모바일에서 버튼들의 기능이 수행이 안된다면 주어진 링크로 이동
                                                                                                     .build())

                                                                            .setDescrption("https://map.kakao.com/link/map/"+ kakaoAddressResult + "," +array_saving_class.foodTMapPoint[i].getLatitude()+ "," + array_saving_class.foodTMapPoint[i].getLongitude()) // 카카오톡 공유시 설명칸에 하이퍼링크형식으로 웹상에서 지도를 띄워주게 설정
                                                                            .build())

                      .setAddressTitle(kakaoName) // 위치 확인시 보여줄 제목칸에 선택한 주변시설의 명칭을 띄워줌
                      .addButton(leftButtonObject) // 왼쪽버튼에 해당하는 ButtonObject를 추가

                      .build();

              Map<String, String> serverCallbackArgs = new HashMap<String, String>();
              serverCallbackArgs.put("user_id", "${current_user_id}");
              serverCallbackArgs.put("product_id", "${shared_product_id}");
              // 카카오 서버에 저장된 값들을 가져옴

              KakaoLinkService.getInstance().sendDefault(getApplicationContext(), params, serverCallbackArgs, new ResponseCallback<KakaoLinkResponse>() {
                @Override
                public void onFailure(ErrorResult errorResult) {
                  Logger.e(errorResult.toString());
                }

                @Override
                public void onSuccess(KakaoLinkResponse result) {
                  // 템플릿 밸리데이션과 쿼터 체크가 성공적으로 끝남. 톡에서 정상적으로 보내졌는지 보장은 할 수 없다. 전송 성공 유무는 서버콜백 기능을 이용하여야 한다.
                }
              });



            } catch (IOException e) {
              e.printStackTrace();
            } catch (ParserConfigurationException e) {
              e.printStackTrace();
            } catch (SAXException e) {
              e.printStackTrace();
            }
          }
        }).start();

      }


    });
    // cultureListView에 아이템이 클릭되면 카카오톡 공유로 넘어가는 기능
    cultureListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
        new Thread(new Runnable() { // convertGpsToAddress와 kakaoLinkService를 안정적으로 쓰려면 Thread를 써야한다.
          @Override
          public void run() {
            try {

              String kakaoName  = array_saving_class.cultureList[i]; // 카카오톡에 공유할 때 제목에 사용될 String값을 cultureList i번에서 가져옴

              String getKakaoAddress = null; // 주소값을 가져옴

              getKakaoAddress = new TMapData().convertGpsToAddress(array_saving_class.cultureTMapPoint[i].getLatitude(), array_saving_class.cultureTMapPoint[i].getLongitude());

              String[] kakaoAddress  = getKakaoAddress.split(" "); // 위도와 경도를 주소로 옮긴 값인 getKakaoAddress에 스페이스가 포함되어 있어 url에 데이터를 입력하면 페이지가 정상적으로 띄워지지않아서 스페이스를 뺀 주소를 제대로 얻어오기위한 String 배열을 생성.
              String kakaoAddressResult = new String(); // 최종적으로 배열에 담긴 값을 반환받음

              for(int i = 0; i < kakaoAddress.length; i++){
                kakaoAddressResult += kakaoAddress[i]; // kakoAddress[i]에 닮긴 값들을 kakaoAddressResult에 옮겨담음
              }

              // LocationTemplate에 왼쪽 버튼을 설정
              ButtonObject leftButtonObject = new ButtonObject("앱으로 돌아가기", // 왼쪽 버튼의 표시될 텍스트를 설정
                                                               LinkObject.newBuilder()
                                                                       .setWebUrl("https://map.kakao.com/link/map/"+ kakaoAddress + "," + kakaoName) //
                                                                       .setMobileWebUrl("https://map.kakao.com/link/map/"+ kakaoAddress + "," + kakaoName)
                                                                       .build());

              LocationTemplate params = LocationTemplate.newBuilder(kakaoAddressResult , // 위치 확인시 보여줄 주소칸에 표시될 값을 설정해줌
                                                                    ContentObject.newBuilder(kakaoName, // 카카오톡 공유시 보여줄 ContentObject를 생성, 제목란에 선택한 주변시설의 명칭을 띄워줌
                                                                                             "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fk.kakaocdn.net%2Fdn%2FcN141f%2FbtqyK9q3rqe%2FK8UNSjqsKMBIT32jLFL1r1%2Fimg.png", // 카카오톡 공유시 보여지는 이미지 설정
                                                                                             LinkObject.newBuilder()

                                                                                                     .setWebUrl("https://map.kakao.com/link/map/"+ kakaoAddressResult + "," + array_saving_class.cultureTMapPoint[i].getLatitude()+ "," + array_saving_class.cultureTMapPoint[i].getLongitude()) // 만약 웹에서 버튼들의 기능이 수행이 안된다면 주어진 링크로 이동
                                                                                                     .setMobileWebUrl("https://map.kakao.com/link/map/"+ kakaoAddressResult + "," + array_saving_class.cultureTMapPoint[i].getLatitude()+ "," + array_saving_class.cultureTMapPoint[i].getLongitude()) // 만약 모바일에서 버튼들의 기능이 수행이 안된다면 주어진 링크로 이동
                                                                                                     .build())

                                                                            .setDescrption("https://map.kakao.com/link/map/"+ kakaoAddressResult + "," +array_saving_class.cultureTMapPoint[i].getLatitude()+ "," + array_saving_class.cultureTMapPoint[i].getLongitude()) // 카카오톡 공유시 설명칸에 하이퍼링크형식으로 웹상에서 지도를 띄워주게 설정
                                                                            .build())

                      .setAddressTitle(kakaoName) // 위치 확인시 보여줄 제목칸에 선택한 주변시설의 명칭을 띄워줌
                      .addButton(leftButtonObject) // 왼쪽버튼에 해당하는 ButtonObject를 추가

                      .build();

              Map<String, String> serverCallbackArgs = new HashMap<String, String>();
              serverCallbackArgs.put("user_id", "${current_user_id}");
              serverCallbackArgs.put("product_id", "${shared_product_id}");
              // 카카오 서버에 저장된 값들을 가져옴

              KakaoLinkService.getInstance().sendDefault(getApplicationContext(), params, serverCallbackArgs, new ResponseCallback<KakaoLinkResponse>() {
                @Override
                public void onFailure(ErrorResult errorResult) {
                  Logger.e(errorResult.toString());
                }

                @Override
                public void onSuccess(KakaoLinkResponse result) {
                  // 템플릿 밸리데이션과 쿼터 체크가 성공적으로 끝남. 톡에서 정상적으로 보내졌는지 보장은 할 수 없다. 전송 성공 유무는 서버콜백 기능을 이용하여야 한다.
                }
              });



            } catch (IOException e) {
              e.printStackTrace();
            } catch (ParserConfigurationException e) {
              e.printStackTrace();
            } catch (SAXException e) {
              e.printStackTrace();
            }
          }
        }).start();

      }


    });


  }



}

