package com.example.a16sserver;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


public class fragment_home extends Fragment {
    private Context mContext;
    CountDownTimer countDownTimer;//카운트다운 변수
    TextView txt_countdown;//홈화면 내 카운트다운 텍스트 변수

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_home,container,false);
        mContext = rootView.getContext();



        ////////----닉네임불러와서 넣기------------------------------------------------------
        String text1 = SharedPreferences_class.getString(mContext,"key_nickname_s");
        System.out.println("닉네임 : "+text1);

        TextView id_student_nickname = (TextView) rootView.findViewById(R.id.id_student_nickname);
        id_student_nickname.setText(text1);
        ////////----------------------------------------------------------------------

        //////-----이전에 풀었던 과목-------------------------------------------------------
        // 데이터베이스 안에 있는거 긁어와야하니까 임시 데이터 만들어두기.
        // 데베에 넣을때는 과목 id를 받아서 과목 이름 뿌리기
        SharedPreferences_class.setString(mContext,"key_subject","영어");

        String text2 = SharedPreferences_class.getString(mContext,"key_subject");
        System.out.println("과목 : "+text2);

        TextView id_quiz_subject = (TextView) rootView.findViewById(R.id.id_quiz_subject);
        id_quiz_subject.setText(text2);
        ///////----------------------------------------------------------------------------------

        ///////--------이전 문제 맞은 점수, 총점수----------------------------------------------------------------
        SharedPreferences_class.setString(mContext,"key_correct_score","73"); //이전문제 맞은점수
        SharedPreferences_class.setString(mContext,"key_total_score","100"); //이전문제 총점수

        String text3 = SharedPreferences_class.getString(mContext,"key_correct_score");
        String text4 = SharedPreferences_class.getString(mContext,"key_total_score");
        System.out.println("이전문제 맞은점수 : "+text3);
        System.out.println("이전문제 총점수 : "+text4);

        TextView id_quiz_correct_score = (TextView) rootView.findViewById(R.id.id_quiz_correct_score);
        TextView id_quiz_total_score = (TextView) rootView.findViewById(R.id.id_quiz_total_score);
        id_quiz_correct_score.setText(text3);
        id_quiz_total_score.setText(text4);

        ///////----------------------------------------------------------------------------------

        ///////--------이전 문제 맞은 갯수, 총갯수----------------------------------------------------------------
        SharedPreferences_class.setString(mContext,"key_correct_num","14"); //이전문제 맞은갯수
        SharedPreferences_class.setString(mContext,"key_total_num","20"); //이전문제 총갯수

        String text5 = SharedPreferences_class.getString(mContext,"key_correct_num");
        String text6 = SharedPreferences_class.getString(mContext,"key_total_num");
        System.out.println("이전문제 맞은갯수 : "+text5);
        System.out.println("이전문제 총갯수 : "+text6);

        TextView id_quiz_correct_num = (TextView) rootView.findViewById(R.id.id_quiz_correct_num);
        TextView id_quiz_total_num = (TextView) rootView.findViewById(R.id.id_quiz_total_num);
        id_quiz_correct_num.setText(text5);
        id_quiz_total_num.setText(text6);

        ///////----------------------------------------------------------------------------------

        ///////-----------------------------------------------------------

        txt_countdown = (TextView) rootView.findViewById(R.id.txt_countdown);
        int date_minus_sysdate = 10;


        countDownTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txt_countdown.setText(getTime());
            }

            @Override
            public void onFinish() {
                countDownTimer.start(); //반복실행되게 끝나자마자 다시 시작하게했음.
            }
        };
        countDownTimer.start();



        ///////-----------------------------------------------------------

        return rootView;

        }

    private String getTime(){
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int c_hour = calendar.get(Calendar.HOUR_OF_DAY);
        int c_min = calendar.get(Calendar.MINUTE);
        int c_sec = calendar.get(Calendar.SECOND);

        //현재시간 -------------------------------
        long mNow = System.currentTimeMillis();
        Date mReDate = new Date(mNow);
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatDate = mFormat.format(mReDate);
        System.out.println("현재시간 : "+formatDate);
        //현재시간 -------------------------------

        Calendar baseCal = new GregorianCalendar(year,month,day,c_hour,c_min,c_sec); //기준날짜 00:00:00
        Calendar targetCal = new GregorianCalendar(year,month,day,12,59,59);  //비교대상날짜


        long diffSec = (targetCal.getTimeInMillis() - baseCal.getTimeInMillis()) / 1000;
        long diffDays = diffSec / (24*60*60); // 초단위를 일단위로 변환

        targetCal.add(Calendar.DAY_OF_MONTH, (int)(-diffDays));

        int hourTime = (int)Math.floor((double)(diffSec/3600));
        int minTime = (int)Math.floor((double)(((diffSec - (3600 * hourTime)) / 60)));
        int secTime = (int)Math.floor((double)(((diffSec - (3600 * hourTime)) - (60 * minTime))));

        String hour = String.format("%02d", hourTime);
        String min = String.format("%02d", minTime);
        String sec = String.format("%02d", secTime);

        return  hour + ":" +min + ":"+ sec ;

    }
}