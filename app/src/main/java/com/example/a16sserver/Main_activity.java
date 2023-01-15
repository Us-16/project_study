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
    private fragment_home fragment_home_1 = new fragment_home();
    private fragment_addquiz fragment_addquiz_2 = new fragment_addquiz();
    private fragment_myquiz fragment_myquiz_3 = new fragment_myquiz();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.menu_frame_layout, fragment_home_1).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.menu_bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                FragmentTransaction transaction = fragmentManager.beginTransaction();

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
        });

    }
}