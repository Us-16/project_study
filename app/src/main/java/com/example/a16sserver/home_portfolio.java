package com.example.a16sserver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class home_portfolio extends AppCompatActivity {

    BottomNavigationView BottomNavigationView; //네비게이션바 선언
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_portfolio);

        BottomNavigationView = findViewById(R.id.bottomNavi);

        getSupportFragmentManager().beginTransaction().add(R.id.home_frame_empty,new home_fragment()).commit();
        // 네비게이션 바 있는 화면이랑 fragement 화면이랑 잇는거 같음(확실x 더 알아보기)

        BottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) { //네비게이션 누를때 (onclick 이랑 같은거인듯)
                switch ((menuitem.getItemId())){ //네비게이션 메뉴 뭐 눌러왔는지 선택)
                    case R.id.home_frame_1: // 홈화면 눌렀으면!(홈화면 아이디는 home_fragement.xml에 있음)
                        getSupportFragmentManager().beginTransaction().replace(R.id.home_frame_empty, new home_fragment()).commit();
                        // 홈화면으로 가주기.
                        break;
                }
                return true;
            }
        });
    }
}