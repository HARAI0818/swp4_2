package com.example.main3;

import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class coronaAct extends AppCompatActivity implements OnMapReadyCallback {


    private FragmentManager fragmentManager;
    private MapFragment mapFragment;
    private PolylineOptions polylineOptions;
    private GoogleMap mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coroan);

        fragmentManager = getFragmentManager();
        mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.googlemap);
        mapFragment.getMapAsync(this);

    }
        // 마커 생성
    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap mMap = googleMap;
        MarkerOptions markerOptions;
        mapView = googleMap;

        Double[] lat = {35.192023, 35.192034, 35.168936, 35.260902, 35.151954, 35.164856, 35.260742, 35.208467, 35.160133, 35.165010, 35.214854};
        Double[] lon = {128.118732, 128.120321, 128.063600, 127.995301, 128.054129, 128.126973, 127.995719, 128.154312, 128.106705, 128.128923, 128.122958};
        String[] title = {"진주 1번 확진자", "진주 2번 확진자", "진주 3번 확진자", "진주 4번 확진자", "진주 5번 확진자(4번과 부부)", "진주 6번 확진자(5번과 직장동료)", "진주 7번 확진자(4번과 차량 동승자)", "진주 8번 확진자(7번 확진자의 며느리)", "진주 9번 확진자(윙스 스파 이용자)", "진주 10번 확진자(8번 확진자의 딸)", "진주 보건소" };

        String[] information = {"대구 신천지 교회 방문", "대구 신천지 교회 방문", "평거동 문타이", "진주 스파랜드", "성지원 골프연습장", "혁신도시 윙스타워", "진주 스파랜드 관련", "일노브 식당", "가호동 행정복지센터", "동선상의 특이사항 없음", "코로나 검진 ㅁㄴㅇㄻㄴㅇㄻㄴㄹㅇㅁㄴㄹㅇㅁㄴㄹㅇ"};

        for (int i = 0; i < title.length; i++) {

            markerOptions = new MarkerOptions();
            LatLng name = new LatLng(lat[i], lon[i]);
            markerOptions.position(name);

            markerOptions.title(title[i]);
            markerOptions.snippet(information[i]);

            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(name));

            // 1번과 2번 확진자의 동선은 대구 신천지 방문이후 공개가 되지 않아 표기하지 않음

            /* 전 버전
             Double[] lat = {35.192023, 35.192034, 35.168936, 35.260902, 35.151954, 35.164856, 35.260742, 35.208467, 35.160133, 35.165010, 35.214854};
        Double[] lon = {128.118732, 128.120321, 128.063600, 127.995301, 128.054129, 128.126973, 127.995719, 128.154312, 128.106705, 128.128923, 128.122958};
        String[] title = {"진주 1번 확진자", "진주 2번 확진자", "진주 3번 확진자", "진주 4번 확진자", "진주 5번 확진자(4번과 부부)", "진주 6번 확진자(5번과 직장동료)", "진주 7번 확진자(4번과 차량 동승자)", "진주 8번 확진자(7번 확진자의 며느리)", "진주 9번 확진자(윙스 스파 이용자)", "진주 10번 확진자(8번 확진자의 딸)", "진주 보건소" };
        String[] number
        String[] information = {"대구 신천지 교회 방문", "대구 신천지 교회 방문", "평거동 문타이", "진주 스파랜드", "성지원 골프연습장", "혁신도시 윙스타워", "진주 스파랜드 관련", "일노브 식당", "가호동 행정복지센터", "동선상의 특이사항 없음", "코로나 검진"};
             */

           // 3번 확진자 동선
           Polyline polyline1 = googleMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .add(
                            new LatLng(35.168936, 128.063600), // 평거동 문타이
                            new LatLng(35.155571, 128.111501), // 가좌 센트럴 웰가
                            new LatLng(35.214854, 128.122958)) // 진주 보건소
                    .color(Color.RED));


            // 4번 확진자 동선
            Polyline polyline2 = googleMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .add(
                            new LatLng(35.260902, 127.995301), // 진주 스파랜드
                            new LatLng(35.166450, 128.128628), // 한일 병원
                            new LatLng(35.167537, 128.127749)) // 옵티마 미소 약국
                    .color(Color.BLUE));

            // 5번 확진자 동선
            Polyline polyline3 = googleMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .add(
                            new LatLng(35.151954, 128.054129), // 성지원 골프 연습장
                            new LatLng(35.166450, 128.128628), // 한일 병원
                            new LatLng(35.167537, 128.127749)) // 옵티마 미소
                    .color(Color.GREEN));

            // 6번 확진자 동선
            Polyline polyline4 = googleMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .add(
                            new LatLng(35.164856, 128.126973), // 윙스 타워
                            new LatLng(35.214872, 128.122958))); // 진주 보건소

            // 7번 확진자 동선
            Polyline polyline5 = googleMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .add(
                            new LatLng(35.260902,  127.995301), // 진주 스파랜드
                            new LatLng(35.214872, 128.122958)) // 진주 보건소
                    .color(Color.GRAY));

            // 8번 확진자 동선
            Polyline polyline6 = googleMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .add(
                            new LatLng(35.208467,  128.154312), // 일노브 식당
                            new LatLng(35.209834, 128.123728), // 다이소 초전점
                            new LatLng(35.185253, 128.087492)) // 강남동 새미래 약국
                    .color(Color.CYAN));

            // 9번 확진자 동선
            Polyline polyline7 = googleMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .add(
                            new LatLng(35.160133,  128.106705), // 가호동 행정복지센터
                            new LatLng(35.164323, 128.125996), // 탑 유황스파
                            new LatLng(35.214872, 128.122958)) // 진주 보건소
            .color(Color.YELLOW));

            // 10번 확진자는 영유아이고 8번 확진자의 딸이고, 8번 확진자의 영향덕에 걸리기 전후의 동선이 없으므로 표기하지 않음


        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(35.153356, 128.099415), 16)); // 경상대학교 좌표

    }


}