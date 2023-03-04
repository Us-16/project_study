package com.example.a16sserver;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a16sserver.springdo.ContainerDO;
import com.example.a16sserver.springdo.Question;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class quiz2_solve extends AppCompatActivity {

    private ImageView imageView1; //문제 이미지뷰 변수
    CountDownTimer countDownTimer;//카운트다운 변수
    private final String URL = "http://10.0.2.2:8080";
    private int page;
    private ArrayList<Question> questionArrayList;
    private boolean isNext;
    private boolean isBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2_solve);

        Context mContext = this;

        ContainerDO data = (ContainerDO) getIntent().getSerializableExtra("dataContent");
        this.page = 0;
        this.isNext = true;

        System.out.println("data: " + data.toString());
        Button btn_quiz_next = findViewById(R.id.btn_quiz_next); //다음문제 클릭부분
        Button btn_quiz_back = findViewById(R.id.btn_quiz_back); //이전문제 클릭부분


        TextView id_quiz_name = findViewById(R.id.id_quiz_name); //exam title
        id_quiz_name.setText(data.getName());
        TextView id_quiz_score = findViewById(R.id.id_quiz_score); //모의고사 점수부분
        id_quiz_score.setText(String.valueOf(data.getScore()));


        ImageView img_quiz_content = (ImageView) findViewById(R.id.img_quiz_content); //image

        TextView id_quiz_content = (TextView) findViewById(R.id.id_quiz_content); //Content
        TextView txt_quiz2_num = (TextView) findViewById(R.id.txt_quiz2_num); // Exam Number


        questionArrayList = data.getQuestions();

        final int questionsAmount = questionArrayList.size(); //문제 수
        Log.d(TAG, "Total Amount of Questions: " + questionsAmount);

        showQuestions(this.questionArrayList.get(this.page));

        btn_quiz_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adder();
            }
        });
        btn_quiz_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subtractor();
            }
        });
//        System.out.println(questionsAmount);
//        for(Question question : questions){
//            System.out.println("ID: " + question.getId() + " ::: " + question.getSubject() + " ::: " + question.getFilepath());
//            System.out.println("Choices: " + question.getChoice1() + ":::"+ question.getChoice2() + ":::"+ question.getChoice3() + ":::"+ question.getChoice4() + ":::"+ question.getChoice5());
//
//            txt_quiz2_num.setText("1");
//            Glide.with(this).load(this.URL + question.getFilepath()).override(300, 200).into(img_quiz_content);
//
//            question_num = txt_quiz2_num.getText().toString();
//            if(question_num.equals("1")){
//                btn_quiz_back.setEnabled(false);
//            }else if(question_num.equals(String.valueOf(questionsAmount))){
//                btn_quiz_next.setEnabled(false);
//            } //이전문제 or 다음문제 버튼 비활성화 관련
//        }

//
        TextView id_quiz_timer1 = (TextView) findViewById(R.id.id_quiz_timer1); //카운트다운 할 곳.

        String min = String.format("%02d", Integer.parseInt(data.getTimerMin()));//모의고사 시간(분(04이렇게)) 가져옴
        String sec = String.format("%02d", Integer.parseInt(data.getTimerSec()));//모의고사 시간(초) 가져옴 30
        System.out.println("풀기 눌렀을 때 모의고사 분 : " + min);
        System.out.println("풀기 눌렀을 때 모의고사 초 : " + sec);
        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }
            @Override
            public void onFinish() {
                countDownTimer.start();
            }
        };
        countDownTimer.start();
    }

    public int adder(){

        if(this.page >= 5){
            return -1;
        }
        this.page += 1;
        statusCheck();
        showQuestions(this.questionArrayList.get(this.page));
        return this.page;
    }

    public int subtractor(){ //adder 가산기 -> subtractor 감산기

        if(this.page <= 0){
            return -1;
        }
        this.page -= 1;
        statusCheck();
        showQuestions(this.questionArrayList.get(this.page));
        return this.page;
    }
    private void statusCheck(){
        Button btn_back = findViewById(R.id.btn_quiz_back);
        Button btn_next = findViewById(R.id.btn_quiz_next);
        if(this.page <= 0){
            isBack = false;
        }else if(this.page >= 5){
            isNext = false;
        }else{
            isBack = true;
            isNext = true;
        }
        btn_back.setEnabled(isBack);
        btn_next.setEnabled(isNext);
    }

    private void showQuestions(Question question) {
        System.out.println("PAGE: " + this.page);
        System.out.println("ADDER TEST: " + this.questionArrayList.get(this.page).getId() + " ::: " + this.questionArrayList.get(this.page).getFilepath());
    }

    private String getTime(){
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        Intent intent = getIntent();

        ArrayList<String> data= (ArrayList<String>) intent.getSerializableExtra("quiz2_content");


        int year = calendar.get(Calendar.YEAR); // 현재날짜 년도 등등 얻기위한 변수설정
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int c_hour = calendar.get(Calendar.HOUR_OF_DAY);
        int c_min = calendar.get(Calendar.MINUTE);
        int c_sec = calendar.get(Calendar.SECOND);

        Calendar baseCal = new GregorianCalendar(year,month,day,c_hour,0,0); //기준날짜 00:00:00
        Calendar targetCal = new GregorianCalendar(year,month,day,0,Integer.parseInt(data.get(2)),Integer.parseInt(data.get(3)));

        long diffSec = (targetCal.getTimeInMillis() - baseCal.getTimeInMillis()) / 1000;
        long diffDays = diffSec / (24*60*60); // 초단위를 일단위로 변환

        targetCal.add(Calendar.DAY_OF_MONTH, (int)(-diffDays));

        int hourTime = (int)Math.floor((double)(diffSec/3600));
        int minTime = (int)Math.floor((double)(((diffSec - (3600 * hourTime)) / 60)));
        int secTime = (int)Math.floor((double)(((diffSec - (3600 * hourTime)) - (60 * minTime))));

        String hour = String.format("%02d", hourTime);
        String min = String.format("%02d", minTime);
        String sec = String.format("%02d", secTime);

        return min + ":"+ sec ;
    }

}