package com.example.a16sserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity_myquiz extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    //각 다른 두 화면 선언(모든문제, 즐겨찾기 문제 화면)

    private final int Fragment_1 = 1;
    private final int Fragment_2 = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_myquiz);

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        ////---------------모든문제, 내문제 버튼 클릭 시 해당 fragment 호출---------------------------------
        findViewById(R.id.btn_myquiz_all).setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Fragmentview(Fragment_1);
            }

        });

        findViewById(R.id.btn_myquiz_wishlist).setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) { Fragmentview(Fragment_2); }

        });
        Fragmentview(Fragment_1);
        ///////////-----------------------------------------------------------------------------------
    }

    //////-------------fragment 함수 선언--------------------------------------------------------------
    private void Fragmentview(int fragment){

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        switch(fragment){
            case 1:
                fragment_myquiz_all  fragment1 = new fragment_myquiz_all();
                transaction.replace(R.id.myquiz_frame_layout, fragment1);
                transaction.commit();
                break;
            case 2:
                fragment_myquiz_wishlist  fragment2 = new fragment_myquiz_wishlist();
                transaction.replace(R.id.myquiz_frame_layout, fragment2);
                transaction.commit();
                break;

        }

    }
    //////-----------------------------------------------------------------------------
}