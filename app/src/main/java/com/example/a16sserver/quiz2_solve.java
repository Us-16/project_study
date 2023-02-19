package com.example.a16sserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class quiz2_solve extends AppCompatActivity {

    private TextView quiz2_id;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2_solve);
        mContext = this;

        Intent intent = getIntent(); // 모의고사 이름가져오기 위한 intent 선언

        ArrayList<String> data= (ArrayList<String>) intent.getSerializableExtra("quiz2_content");

        ///////--------모의고사 이름----------------------------------------------------------------

        String quiz2_name = data.get(0);//모의고사 이름 가져옴
        System.out.println("풀기버튼 눌렀을 때 모의고사 이름"+quiz2_name);

        TextView id_quiz_name = (TextView) findViewById(R.id.id_quiz_name);
        id_quiz_name.setText(quiz2_name);

        ///////----------모의고사 점수---------------------------------------------------------------
        String quiz2_score = data.get(1);//모의고사 점수 가져옴
        System.out.println("풀기버튼 눌렀을 때 모의고사 점수"+quiz2_score);

        TextView id_quiz_score = (TextView) findViewById(R.id.id_quiz_score);
        id_quiz_score.setText(quiz2_score);

        ///////----------------------------------------------------------------------------------


        ///////----------모의고사 시간 (00 : 00)---------------------------------------------------------------
        //string 으로 다시 바꿔야해 00 이렇게
        String quiz2_timer1 = data.get(2);//모의고사 시간(분) 가져옴
        System.out.println("풀기버튼 눌렀을 때 모의고사 분"+quiz2_timer1);
        TextView id_quiz_timer1 = (TextView) findViewById(R.id.id_quiz_timer1);
        id_quiz_timer1.setText(quiz2_timer1);

        String quiz2_timer2 = data.get(3);//모의고사 시간(초) 가져옴
        System.out.println("풀기버튼 눌렀을 때 모의고사 초"+quiz2_timer2);
        TextView id_quiz_timer2 = (TextView) findViewById(R.id.id_quiz_timer2);
        id_quiz_timer2.setText(quiz2_timer2);


        ///////----------------------------------------------------------------------------------



    }
}