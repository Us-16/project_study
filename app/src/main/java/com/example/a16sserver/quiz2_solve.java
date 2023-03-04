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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a16sserver.springdo.ContainerDO;
import com.example.a16sserver.springdo.Question;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class quiz2_solve extends AppCompatActivity {
    CountDownTimer countDownTimer;//카운트다운 변수
    private int page;
    private int QuestionAmount;
    private ArrayList<Question> questionArrayList;
    private boolean isNext;
    private boolean isBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2_solve);

        ContainerDO data = (ContainerDO) getIntent().getSerializableExtra("dataContent");

        Button btn_quiz_next = findViewById(R.id.btn_quiz_next); //다음문제 클릭부분
        Button btn_quiz_back = findViewById(R.id.btn_quiz_back); //이전문제 클릭부분
        TextView id_quiz_name = findViewById(R.id.id_quiz_name); //exam title
        TextView id_quiz_score = findViewById(R.id.id_quiz_score); //모의고사 점수부분

        id_quiz_name.setText(data.getName());
        id_quiz_score.setText(String.valueOf(data.getScore()));

        questionArrayList = data.getQuestions();

        this.QuestionAmount = questionArrayList.size(); //문제 수
        this.page = 0;
        this.isNext = true;
        this.isBack = false; //어차피 인스턴스 변수로 두면 자동으로 false로 선언되는거 알고 계시나요>? -> 응, 명시적으로 알려줌으로써 혼동 방지하는거야~ 이게 다아 의도된거다 이 말이야

        statusCheck();
        showQuestions(this.questionArrayList.get(this.page)); //최초에는 0으로 들어가짐

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

    /**
     * 주의! adder, subtractor 두 메서드는 버튼이 눌러져야만 수행되기 때문에 그 전에는 this.page의 값이 얼마인지 파악해주지 않음
     * 현재까지는 두 메서드의 동작 중 예상치 않은 오류를 발생시키지는 않았습니다!
     * @return -1이 나오는 순간 범위 벗어났다는 뜻입니다
     */
    private int adder(){
        if(this.page >= QuestionAmount-1){
            return -1;
        }
        this.page += 1;
        statusCheck();
        showQuestions(this.questionArrayList.get(this.page));
        return this.page;
    }
    /**
     * 주의! adder, subtractor 두 메서드는 버튼이 눌러져야만 수행되기 때문에 그 전에는 this.page의 값이 얼마인지 파악해주지 않음
     * 현재까지는 두 메서드의 동작 중 예상치 않은 오류를 발생시키지는 않았습니다!
     * @return -1이 나오는 순간 범위 벗어났다는 뜻입니다
     */
    private int subtractor(){ //adder 가산기 -> subtractor 감산기
        if(this.page <= 0){
            this.page=0;
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
        }else if(this.page >= QuestionAmount-1){
            isNext = false; //나중에 필요하면 빼셈
            btn_next.setText("채점하기");
        }else{
            isBack = true;
            isNext = true;
        }
        btn_back.setEnabled(isBack);
        btn_next.setEnabled(isNext);
    }

    /**
     * 버튼 누를 때마다 문제 내용이 바뀌는 것을 보여주는 메서드입니다.
     * @param question 해당 페이지의 문제를 담은 DO
     */
    private void showQuestions(Question question) {
        TextView question_num = findViewById(R.id.txt_quiz2_num);
        TextView question_content = findViewById(R.id.id_quiz_content);
        ImageView question_image = findViewById(R.id.img_quiz_content);
        TextView[] choicesView = {findViewById(R.id.id_quiz_ans_1),findViewById(R.id.id_quiz_ans_2),findViewById(R.id.id_quiz_ans_3),findViewById(R.id.id_quiz_ans_4),findViewById(R.id.id_quiz_ans_5)};
        String[] choices = {question.getChoice1(), question.getChoice2(), question.getChoice3(), question.getChoice4(), question.getChoice5()};
        allCheckBoxInit();
        System.out.println("PAGE: " + this.page);
        System.out.println("ADDER TEST: " + question.getId() + " ::: " + question.getFilepath());

        question_num.setText(String.valueOf(this.page + 1));
        question_content.setText(question.getContent());
        String URL = "http://10.0.2.2:8080";
        Glide.with(this).load(URL + question.getFilepath()).into(question_image);
        for(int i=0; i<5; i++){
            choicesView[i].setText(choices[i]);
        }
    }

    /**
     * 문제를 넘길때마다 체크버튼들이 눌러져 있는 것을 방지하기 위해 모두 다 제거해주는 메서드입니다.
     * 문제는 정답을 골랐더라도 그대로 남기 때문에 정답을 선택하고 다시 되돌아가도 남도록 만드는 로직은 추후에 만들어주시면 감사하겠습니다
     */
    private void allCheckBoxInit() {
        CheckBox[] checkBoxViews = {findViewById(R.id.quiz_check1), findViewById(R.id.quiz_check2), findViewById(R.id.quiz_check3), findViewById(R.id.quiz_check4), findViewById(R.id.quiz_check5)};
        for(CheckBox checkBox : checkBoxViews){
            checkBox.setChecked(false);
        }
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