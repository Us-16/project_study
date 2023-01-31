package com.example.a16sserver; //하단바 생성할 메인 액티비티

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;



public class Main_activity extends AppCompatActivity {
    private FragmentManager fragmentManager = getSupportFragmentManager();
    //각 다른 세 화면 선언(홈,문제담기,내문제 화면)
    private fragment_home fragment_home_1 = new fragment_home();
    private fragment_addquiz fragment_addquiz_2 = new fragment_addquiz();
    private fragment_myquiz fragment_myquiz_3= new fragment_myquiz();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = fragmentManager.beginTransaction(); //지워도 되는지 확인해보기
        //디폴트로 띄울 화면 fragment_home_1(fragment_home의 변수)
        transaction.replace(R.id.menu_frame_layout, fragment_home_1).commitAllowingStateLoss();

        //----------네비게이션 바 연결
        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
        //--------------
            @Override
            //----------------------------네비게이션 바 눌렀을 때 동작
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                FragmentTransaction transaction = fragmentManager.beginTransaction();
                // 버튼마다 다른 동작
                switch(menuItem.getItemId()){
                    case R.id.menu_home:
                        transaction.replace(R.id.menu_frame_layout,fragment_home_1).commitAllowingStateLoss();
                        break;
                    case R.id.menu_add:
                        transaction.replace(R.id.menu_frame_layout,fragment_addquiz_2).commitAllowingStateLoss();
                        break;
                    case R.id.menu_myquiz:
                        transaction.replace(R.id.menu_frame_layout,fragment_myquiz_3).commitAllowingStateLoss();
                        break;

                }
                return true;
            }
            //-----------------------------------
        });

    }
}