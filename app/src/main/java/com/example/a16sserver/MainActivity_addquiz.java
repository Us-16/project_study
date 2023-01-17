package com.example.a16sserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity_addquiz extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    //각 다른 세 화면 선언(고1,고2,고3 버튼 클릭 시 화면)

    private final int Fragment_1 = 1;
    private final int Fragment_2 = 2;
    private final int Fragment_3 = 3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_addquiz);

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        ////---------------고1,고2,고3 버튼 클릭 시 해당 fragment 호출---------------------------------
        findViewById(R.id.addquiz_button_grade_1).setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Fragmentview(Fragment_1);
            }

        });

        findViewById(R.id.addquiz_button_grade_2).setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) { Fragmentview(Fragment_2); }

        });

        findViewById(R.id.addquiz_button_grade_3).setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Fragmentview(Fragment_3);
            }

        });
        Fragmentview(Fragment_1);
        ///////////-----------------------------------------------------------------------------------
    }
    //////-------------fragment 함수 선언--------------------------------------------------------------
    private void Fragmentview(int fragment){

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        switch(fragment){
            case 1:
                fragment_test_grade_1  fragment1 = new fragment_test_grade_1();
                transaction.replace(R.id.quizselect_frame_layout, fragment1);
                transaction.commit();
                break;
            case 2:
                fragment_test_grade_2  fragment2 = new fragment_test_grade_2();
                transaction.replace(R.id.quizselect_frame_layout, fragment2);
                transaction.commit();
                break;
            case 3:
                fragment_test_grade_3  fragment3 = new fragment_test_grade_3();
                transaction.replace(R.id.quizselect_frame_layout, fragment3);
                transaction.commit();
                break;

        }

    }
    //////-----------------------------------------------------------------------------
}