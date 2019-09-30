# 2019androidTP  
![project_start_date](https://img.shields.io/badge/Project%20Start%20Date-2019--07--25-informational.svg)  

***
<2019androidTeamProject - Interface Programming Exhibition>
***
2019년도 인터페이스 안드로이드 팀 프로젝트 깃허브입니다.\
JAVA를 기반으로 안드로이드 앱을 제작하여 실제 Google Play Store에 게시하는 것을 목표로 합니다.\
JAVA에 대한 이해를 위한 기초적인 스터디와 주마다 정기적으로 회의 및 스터디를 진행하며 해당 프로젝트의 팀원들이\
안드로이드의 기초적인 지식들을 습득하고 오픈소스를 충분히 이해할 수 있게 진행됩니다.\
개발기간은 7월 중순부터 12월 중순까지 진행됩니다. 시험기간 2주전부터는 회의와 스터디는 진행하지 않습니다.

공개SW 개발자대회 학생부문으로 모바일 부문에 참가합니다. <https://www.oss.kr/dev_competition/>
9월 15일 참가접수 마감 / 10월 7일까지 프로젝트 제출

# Member
### 팀장 강근우 - 개발, 기획
##### 팀원 주이식 - 개발, Github 관리 
##### 팀원 이현주 - 개발, 서기
##### 팀원 이주현 - UI/UX 디자인



# Application name: 더치(Dutch)

<개발목표>
---------
자신의 위치와 상대방의 위치를 비교해서 중간지점에서 어떤 지점이 가장 놀기 적합한 곳인지 찾아주는 어플리케이션을\
개발한다.

<개발에 필요한 기능>
-------------------
1. 지도 API를 사용한 위치정보 제공 -> T map API 사용
2. 중간지점 찾기 알고리즘을 통한 최적의 지점을 찾아줌
3. 최적의 지점에서 가장 놀기 적합한 곳을 찾아서 사용자에게 제공
4. 네비게이션 드로어(탭)을 사용하여 화면전환

<사용한 OPENSOURCE 목록>
-----------------------
- [T map API](http://tmapapi.sktelecom.com/main.html#android/guide/androidGuide)

- [kakao 우편번호 서비스](http://postcode.map.daum.net/guide#usage)

<참고한 문서 및 사이트>
---------------------
- [위도 경도로 주소 반환](http://blog.naver.com/PostView.nhn?blogId=robotluv1226&logNo=220851639125)  
- [다음 우편번호 서비스 API 사용하기](https://codeman77.tistory.com/55)  
- [다중마커 구현](https://community.openapi.sk.com/t/tmap/6715)  
- [네비게이션 드로어 사용](https://liveonthekeyboard.tistory.com/entry/%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EB%84%A4%EB%B9%84%EA%B2%8C%EC%9D%B4%EC%85%98-%EB%93%9C%EB%A1%9C%EC%96%B4-Navigation-drawer-%EC%82%AC%EC%9A%A9%EB%B2%95)  


<주차별 진행사항>
----------------
- 사전준비(7월 25일): 안드로이드를 위한 기초 JAVA강의 + 깃허브 강의, 어플리케이션 기획 및 개발 시 필요사항 정리  
- 1주차(8월 1일): 안드로이드 이벤트 처리(OnclickListener, Intent, AndroidManifest Permission주기) 및 예외처리(try catch)  
- 2주차(8월 8일): Google 지도 API 사용, AndroidManifest 오류 및 SDK버전 오류 정리  
- 3주차(8월 16일): Google Place API사용, 지오코딩 구현, AndroidMenifest오류 해결  
- 4주차(8월 19일): Marker에 이벤트 구현 test, 다중마커 관련 T map API 서치, 어플리케이션 플로우 차트  
- 5주차(8월 26일): 다중마커 구현, 풍선뷰 구현, 어플리케이션 색상 기획 및 레이아웃 샘플 업로드  
- 6주차(9월 11일)[온라인회의]:  시간상의 이유로 온라인회의로 대체 -> Xml 수정, 중간지점 기능 기획  
- 7주차(9월 20일): Xml 추가수정, 중간지점 기능구현, 부대시설 기능구현
