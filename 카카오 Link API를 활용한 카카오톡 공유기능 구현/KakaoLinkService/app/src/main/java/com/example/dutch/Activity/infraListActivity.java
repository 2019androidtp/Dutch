package com.example.dutch.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

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

import static com.example.dutch.Activity.CenterActivity.infraSize;

public class infraListActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_infra_list);

    final ListView InfraListView;
    ListViewAdapter listViewAdapter = new ListViewAdapter();

    InfraListView = (ListView) findViewById(R.id.infraListView);
    InfraListView.setAdapter(listViewAdapter); // 리스트뷰에 어답터 연결

    for (int i = 0; i < infraSize; i++) {
      listViewAdapter.addItem(array_saving_class.infraList[i], array_saving_class.infraAddress[i], array_saving_class.infraLat[i], array_saving_class.infraLon[i]);
    } // 어답터에 주변시설 주소의 이름과 상세주소, 위도와 경도 추가

    // infra 리스트뷰 이벤트 처리
    InfraListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
        new Thread(new Runnable() { // convertGpsToAddress와 kakaoLinkService를 안정적으로 쓰려면 Thread를 써야한다.
          @Override
          public void run() {
            try {

              String kakaoName  = array_saving_class.infraList[i]; // 카카오톡에 공유할 때 제목에 사용될 String값을 infralList에 i번에서 가져옴

              String getKakaoAddress = null; // 주소값을 가져옴

              getKakaoAddress = new TMapData().convertGpsToAddress(array_saving_class.infraTMapPoint[i].getLatitude(), array_saving_class.infraTMapPoint[i].getLongitude());

              String[] kakaoAddress  = getKakaoAddress.split(" "); // 위도와 경도를 주소로 옮긴 값인 getKakaoAddress에 스페이스가 포함되어 있어 url에 데이터를 입력하면 페이지가 정상적으로 띄워지지않아서 스페이스를 뺀 주소를 제대로 얻어오기위한 String 배열을 생성.
              String kakaoAddressResult = new String(); // 최종적으로 배열에 담긴 값을 반환받음

              for(int i = 0; i < kakaoAddress.length; i++){
                  kakaoAddressResult += kakaoAddress[i]; // kakoAddress[i]에 닮긴 값들을 kakaoAddressResult에 옮겨담음
              }

              // LcationTemplate에 왼쪽 버튼을 설정
              ButtonObject leftButtonObject = new ButtonObject("앱으로 들어가기", // 왼쪽 버튼의 표시될 텍스트를 설정
                                                                LinkObject.newBuilder()
                                                                        .setWebUrl("https://map.kakao.com/link/map/"+ kakaoAddress + "," + kakaoName) //
                                                                        .setMobileWebUrl("https://map.kakao.com/link/map/"+ kakaoAddress + "," + kakaoName)
                                                                        .build());

              LocationTemplate params = LocationTemplate.newBuilder(kakaoAddressResult , // 위치 확인시 보여줄 주소칸에 표시될 값을 설정해줌
                                                                    ContentObject.newBuilder(kakaoName, // 카카오톡 공유시 보여줄 ContentObject를 생성, 제목란에 선택한 주변시설의 명칭을 띄워줌
                                                                                             "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fk.kakaocdn.net%2Fdn%2FcN141f%2FbtqyK9q3rqe%2FK8UNSjqsKMBIT32jLFL1r1%2Fimg.png", // 카카오톡 공유시 보여지는 이미지 설정
                                                                                             LinkObject.newBuilder()

                                                                                                     .setWebUrl("https://map.kakao.com/link/map/"+ kakaoAddressResult + "," + array_saving_class.infraTMapPoint[i].getLatitude()+ "," + array_saving_class.infraTMapPoint[i].getLongitude()) // 만약 웹에서 버튼들의 기능이 수행이 안된다면 주어진 링크로 이동
                                                                                                     .setMobileWebUrl("https://map.kakao.com/link/map/"+ kakaoAddressResult + "," + array_saving_class.infraTMapPoint[i].getLatitude()+ "," + array_saving_class.infraTMapPoint[i].getLongitude()) // 만약 모바일에서 버튼들의 기능이 수행이 안된다면 주어진 링크로 이동
                                                                                                     .build())

                                                                            .setDescrption("https://map.kakao.com/link/map/"+ kakaoAddressResult + "," +array_saving_class.infraTMapPoint[i].getLatitude()+ "," + array_saving_class.infraTMapPoint[i].getLongitude()) // 카카오톡 공유시 설명칸에 하이퍼링크형식으로 웹상에서 지도를 띄워주게 설정
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

