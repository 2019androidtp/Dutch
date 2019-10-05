# 2019androidTP  
![project_start_date](https://img.shields.io/badge/Project%20Start%20Date-2019--07--25-informational.svg)  

[앱 소개 영상](https://youtu.be/vk3_f0Nti6o)


***
<2019androidTeamProject - Interface Programming Exhibition>
***
2019년도 인터페이스 안드로이드 팀 프로젝트 깃허브입니다.\
JAVA를 기반으로 안드로이드 앱을 제작하여 실제 Google Play Store에 게시하는 것을 목표로 합니다.\
JAVA에 대한 이해를 위한 기초적인 스터디와 주마다 정기적으로 회의 및 스터디를 진행하며 해당 프로젝트의 팀원들이\
안드로이드의 기초적인 지식들을 습득하고 오픈소스를 충분히 이해할 수 있게 진행됩니다.\
개발기간은 7월 중순부터 12월 중순까지 진행됩니다.  
시험기간 2주전부터는 회의와 스터디는 진행하지 않습니다.

공개SW 개발자대회 학생부문으로 모바일 부문에 참가합니다.  
[대회 사이트](https://www.oss.kr/dev_competition/) - 9월 15일 참가접수 마감 / 10월 6일까지 프로젝트 제출

# Member
### 팀장 강근우 - 기획, 주요 알고리즘 개발
##### 팀원 주이식 - PM(Project Manager), 핵심 기능 개발, GitHub 관리
##### 팀원 이현주 - 프론트엔드 개발
##### 팀원 이주현 - UI/UX 디자인 총괄



# Application name: 더치(Dutch)

<개발목표>
---------
자신의 위치와 상대방의 위치를 비교하여 중간지점을 구하고 중간지점에서 갈 수 있는 주변시설들을 제공하고, 주변시설의 위치를 카카오톡 메시지로 공유할 수 있게 한다. 

<개발에 필요한 기능>
-------------------
1. 지도 API를 사용한 위치정보 제공 -> T map API 사용
2. 중간지점 찾기 알고리즘을 통한 최적의 지점을 찾아줌
3. 찾은 중간지점에서 주변 부대시설을 찾아서 사용자에게 제공
4. 찾은 주변시설을 카카오톡 메시지로 공유 -> Kakao Link API 사용

<사용한 OPENSOURCE 목록>
-----------------------
- [T map API](http://tmapapi.sktelecom.com/main.html#android/guide/androidGuide)
- [Kakao Link API](https://developers.kakao.com/docs/android/kakaotalk-link)  

<참고한 문서 및 사이트>
---------------------
- [위도 경도로 주소 반환](http://blog.naver.com/PostView.nhn?blogId=robotluv1226&logNo=220851639125)  
- [다중마커 구현](https://community.openapi.sk.com/t/tmap/6715)  
- [카카오링크 API 개발가이드](https://developers.kakao.com/docs/js-reference#kakao_link_createdefaultbutton)  

<주차별 진행사항>
----------------
- 사전준비(7월 25일): 안드로이드를 위한 기초 JAVA강의 + 깃허브 강의, 어플리케이션 기획 및 개발 시 필요사항 정리  
- 1주차(8월 1일): 안드로이드 이벤트 처리(OnclickListener, Intent, AndroidManifest Permission주기) 및 예외처리(try catch)  
- 2주차(8월 8일): Google 지도 API 사용, AndroidManifest 오류 및 SDK버전 오류 정리  
- 3주차(8월 16일): Google Place API사용, 지오코딩 구현, AndroidMenifest오류 해결  
- 4주차(8월 19일): Marker에 이벤트 구현 test, 다중마커 관련 T map API 서치, 어플리케이션 플로우 차트  
- 5주차(8월 26일): 다중마커 구현, 풍선뷰 구현, 어플리케이션 색상 기획 및 레이아웃 샘플 업로드  
- 6주차(9월 11일)[온라인회의]:  시간상의 이유로 온라인회의로 대체 -> Xml 수정, 중간지점 기능 기획  
- 7주차(9월 20일): Xml 추가수정, 중간지점 기능구현, 주변시설 기능구현
- 8주차(9월 26일): 공개 SW개발자대회 기술 멘토링 참가, 버그 픽스, 데이터구조 수정, XML 픽스
- 9주차(10월 3일): Kakao Link API를 활용한 카카오톡으로 공유하기 기능 추가, XML 픽스, 결과 보고서 작성, 시연 동영상 촬영
