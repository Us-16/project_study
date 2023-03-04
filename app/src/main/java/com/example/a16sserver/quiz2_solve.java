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

public class quiz2_solve extends AppCompatActivity {

    private ImageView imageView1; //문제 이미지뷰 변수
    CountDownTimer countDownTimer;//카운트다운 변수
    private final String URL = "http://10.0.2.2:8080";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2_solve);

        Context mContext = this;

        ContainerDO data = (ContainerDO) getIntent().getSerializableExtra("dataContent");

        System.out.println("data: " + data.toString());
        Button btn_quiz_next = findViewById(R.id.btn_quiz_next); //다음문제 클릭부분
        Button btn_quiz_back = findViewById(R.id.btn_quiz_back); //이전문제 클릭부분

        HeaderComponents();

        TextView id_quiz_name = findViewById(R.id.id_quiz_name); //exam title
        id_quiz_name.setText(data.getName());
        TextView id_quiz_score = findViewById(R.id.id_quiz_score); //모의고사 점수부분
        id_quiz_score.setText(String.valueOf(data.getScore()));


        ImageView img_quiz_content = (ImageView) findViewById(R.id.img_quiz_content); //image

        TextView id_quiz_content = (TextView) findViewById(R.id.id_quiz_content); //Content
        TextView txt_quiz2_num = (TextView) findViewById(R.id.txt_quiz2_num); // Exam Number

        String question_num = "0";

        ArrayList<Question> questions = data.getQuestions();

        final int questionsAmount = questions.size(); //문제 수
        Log.d(TAG, "Total Amount of Questions: " + questionsAmount);

        System.out.println(questionsAmount);
        for(Question question : questions){
            System.out.println("ID: " + question.getId() + " ::: " + question.getSubject() + " ::: " + question.getFilepath());
            System.out.println("Choices: " + question.getChoice1() + ":::"+ question.getChoice2() + ":::"+ question.getChoice3() + ":::"+ question.getChoice4() + ":::"+ question.getChoice5());

            txt_quiz2_num.setText("1");
            Glide.with(this).load(this.URL + question.getFilepath()).override(300, 200).into(img_quiz_content);

            question_num = txt_quiz2_num.getText().toString();
            if(question_num.equals("1")){
                btn_quiz_back.setEnabled(false);
            }else if(question_num.equals(String.valueOf(questionsAmount))){
                btn_quiz_next.setEnabled(false);
            } //이전문제 or 다음문제 버튼 비활성화 관련

            btn_quiz_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 버튼을 누르면 화면이 바뀌는 게 아니라 내용만 바뀌는 거다
                    // 즉, content, 보기, 숫자, 그림만 변하면 됨
                    // 시작인지, 끝인지를 구분하고 그에 맞게 버튼의 동작도 변경하면 될 거 같음
                    // btn_quiz_next -> 다음 문제로 이동 / btn_quiz_back -> 이전 문제로 이동
                    // 아마 이를 해결하기 위한 좋은 모던하면서도 있어보이는 방법으로는 Iterator를 최대한 활용해볼 것을 권장힘 -> 무엇보다 있어보인다는 것이 크다
                }
            });
        }

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



    private void HeaderComponents(){

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