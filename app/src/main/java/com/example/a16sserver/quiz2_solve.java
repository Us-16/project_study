package com.example.a16sserver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a16sserver.account.User_account_3;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class quiz2_solve extends AppCompatActivity {

    private Context mContext;
    private ImageView imageView1; //문제 이미지뷰 변수
    CountDownTimer countDownTimer;//카운트다운 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2_solve);
        mContext = this;
        Button btn_quiz_next = (Button) findViewById(R.id.btn_quiz_next); //다음문제 클릭부분
        Button btn_quiz_back = (Button) findViewById(R.id.btn_quiz_back); //이전문제 클릭부분


        Intent intent = getIntent(); // 모의고사 quiz2_list 가져오기 위한 intent 선언

        ArrayList<String> data = (ArrayList<String>) intent.getSerializableExtra("quiz2_content");

        ///////--------모의고사 이름----------------------------------------------------------------

        String quiz2_name = data.get(0);//모의고사 이름 가져옴
        System.out.println("풀기버튼 눌렀을 때 모의고사 이름" + quiz2_name);

        TextView id_quiz_name = (TextView) findViewById(R.id.id_quiz_name);
        id_quiz_name.setText(quiz2_name);

        ///////----------모의고사 점수---------------------------------------------------------------
        String quiz2_score = data.get(1);//모의고사 점수 가져옴
        System.out.println("풀기버튼 눌렀을 때 모의고사 점수" + quiz2_score);

        TextView id_quiz_score = (TextView) findViewById(R.id.id_quiz_score); //모의고사 점수부분
        id_quiz_score.setText(quiz2_score);

        ///////----------------------------------------------------------------------------------


        //임시 문제 데이터 저장해두기 (간이 서버데이터역할(을 하려했는데 못함 ㅋ))
        ArrayList<String> quiz_content_saveList = new ArrayList<>();

        for(int i=0;i<Integer.parseInt(data.get(4));i++){
            System.out.println("문제 수 만큼 돌린다 : "+i+1+"번째");
            quiz_content_saveList.add(data.get(5));
            quiz_content_saveList.add(data.get(5));
            quiz_content_saveList.add(data.get(5));
            quiz_content_saveList.add(data.get(5));
        } //이거 아직 완료안했음. 딴거 해놓고 하기


        String[][] quiz2_que = {{"2211", "2", "문제1번~~한값은2211?", "문제2번~~한값은?"}, {"2209", "2", "문제1번~~한값은?2209", "문제2번~~한값은?"}
                , {"2110", "2", "문제1번~~한값은2110?", "문제2번~~한값은?"}, {"2103", "3", "문제1번~~한값은?2103", "문제2번~~한값은?", "문제3번~~한값은?"}
                , {"1906", "3", "문제1번~~한값은1906?", "문제2번~~한값은?", "문제3번~~한값은?"}, {"1809", "2", "문제1번~~한값은?1809", "문제2번~~한값은?", "문제3번~~한값은?"}
        }; //모의고사 문제데이터

        ImageView img_quiz_content = (ImageView) findViewById(R.id.img_quiz_content); //이미지뷰

        String[][] quiz2_que_img = {{"2211", "C:\\Users\\wkdgy\\Desktop\\0213~\\img_quiz_path\\test1.png", "C:\\Users\\wkdgy\\Desktop\\0213~\\img_quiz_path\\test2.png"}, {"2209",  "C:\\Users\\wkdgy\\Desktop\\0213~\\img_quiz_path\\test1.png", "C:\\Users\\wkdgy\\Desktop\\0213~\\img_quiz_path\\test2.png"}
                , {"2110",  "C:\\Users\\wkdgy\\Desktop\\0213~\\img_quiz_path\\test1.png", "C:\\Users\\wkdgy\\Desktop\\0213~\\img_quiz_path\\test2.png"}, {"2103",  "C:\\Users\\wkdgy\\Desktop\\0213~\\img_quiz_path\\test1.png", "C:\\Users\\wkdgy\\Desktop\\0213~\\img_quiz_path\\test2.png",""}
                , {"1906",  "","C:\\Users\\wkdgy\\Desktop\\0213~\\img_quiz_path\\test1.png", "C:\\Users\\wkdgy\\Desktop\\0213~\\img_quiz_path\\test2.png"}, {"1809",  "C:\\Users\\wkdgy\\Desktop\\0213~\\img_quiz_path\\test1.png","", "C:\\Users\\wkdgy\\Desktop\\0213~\\img_quiz_path\\test2.png"}
        }; //모의고사 이미지데이터


/*
        uiz2_content.add(quiz2_list.getQuiz2_name());// 모의고사 이름
        quiz2_content.add(String.valueOf(quiz2_list.getQuiz2_score()));// 모의고사 점수
        quiz2_content.add(quiz2_list.
                getQuiz2_timer().substring(0,quiz2_list.getQuiz2_timer().indexOf(":"))); //모의고사 분
        quiz2_content.add(quiz2_list.
                getQuiz2_timer().substring(quiz2_list.getQuiz2_timer().indexOf(":")+1));//모의고사 초
        quiz2_content.add(String.valueOf(quiz2_list.getQuiz2_que())); //모의고사 문제갯수 4
        quiz2_content.add(String.valueOf(quiz2_list.getQuiz2_id()));// 모의고사 id
*/


        /////////////----------------------------------------------------------------------------------


        ///////----------모의고사 가져온 id 를 토대로, 문제내용 뽑아오기---------------------------------------------------------------
        String quiz2_id = data.get(5);//모의고사 id 가져옴
        System.out.println("풀기버튼 눌렀을 때 모의고사 id : " + quiz2_id);

        TextView id_quiz_content = (TextView) findViewById(R.id.id_quiz_content); //모의고사 문제 1번부터 쭉 적히는 곳.
        TextView txt_quiz2_num = (TextView) findViewById(R.id.txt_quiz2_num); // 모의고사 문제 1번,2번 텍스트

        System.out.println("quiz2_que.length : " + quiz2_que.length);
        System.out.println("quiz2_que[i][0]h : " + quiz2_que[0][0]);



        for (int i = 0; i < quiz2_que.length; i++) {
            if (quiz2_id.equals(quiz2_que[i][0])) { //id 값 찾아서
                //찾았으면,
                System.out.println("quiz2_id : " + quiz2_id + " quiz2_que[i][0] : " + quiz2_que[i][0]);
                final int que_num = Integer.parseInt(quiz2_que[i][1]); //문제수 숫자화

                //
                id_quiz_content.setText(quiz2_que[i][2]); //모의고사 문제에 첫번째 문제값 넣음.
                txt_quiz2_num.setText("1"); //모의고사 문제에 1번 번호 넣기.
                //img_quiz_content

                //Glide.with(this).load(quiz2_que_img[i][1]).override(300,200).into(img_quiz_content);
                Glide.with(this).load("http://goo.gl/gEgYUd").override(300,200).into(img_quiz_content);
                //ImageView.setVisibility(View.GONE); 이미지 없으면 숨기기 하기.

                System.out.println("문제가 뭐냐면 : " + quiz2_que[i][2]);

                //////-----1번 문제일 때 이전문제 비활하는거 체크-------------------------------------------------
                String check_quiz2_num = txt_quiz2_num.getText().toString(); //문제번호 가져옴
                //이전문제를 눌렀다면, 현재 문제번호를 데이터에서 찾아서 이전문제 뽑기.
                System.out.print("지금 문제번호 : "+ check_quiz2_num);

                if (check_quiz2_num == "1"){
                    System.out.println("첫화면 입니다.");
                    //btn_quiz_back.setClickable(false); //버튼 비활성화(이전문제 못 누르게)
                    btn_quiz_back.setEnabled(false);
                    System.out.println("비활완료");
                }

                ////////----------------------------------------------------------------------------

                ////-------------- 다음문제 버튼 클릭 시 문제 바뀜---------------------------------
                final int a = i;
                btn_quiz_next.setOnClickListener(new View.OnClickListener() {

                    int cnt = 1;//문제 인덱스 (초기 0)

                    @Override
                    public void onClick(View view) {

                        System.out.println("다음문제 버튼 클릭함!!!");

                        btn_quiz_back.setEnabled(true); //첫 화면 아닐테니까 바로 이전문제 비활 풀기

                        String check_quiz2_num = txt_quiz2_num.getText().toString(); //문제번호 가져옴

                        cnt = Integer.parseInt(check_quiz2_num);
                        if (cnt < que_num) { //문제수보다 작다면,
                            System.out.println("지금 문제번호 : "+cnt);
                            id_quiz_content.setText(quiz2_que[a][cnt + 2]);//문제내용 텍스트
                            txt_quiz2_num.setText(Integer.toString(cnt + 1));//문제번호 텍스트
                            cnt++; // 다음문제로 넘어가면 +!

                            if (cnt == que_num) { //마지막 문제에 도달했다면,
                                System.out.println("지금 문제번호 : "+cnt);
                                System.out.println("채점하기!! 다음으로 넘어가야돼!!");
                                btn_quiz_next.setSelected(true);
                                btn_quiz_next.setText("채점하기");
                            }


                        }




                    }
                });
                //////--------------------------------------------------------------------



                ///////----이전문제 클릭 시 문제바뀜------------------------------------------
                btn_quiz_back.setOnClickListener(new View.OnClickListener() {

                    int cnt = 1;
                    @Override
                    public void onClick(View view) {

                        System.out.println("이전문제 버튼 클릭함!!!");
                        String check_quiz2_num = txt_quiz2_num.getText().toString(); //문제번호 가져옴
                        System.out.println("가져온 문제번호 : "+check_quiz2_num);

                        int cnt = Integer.parseInt(check_quiz2_num);

                        if (cnt==2){ //첫번째 문제일때 비활시키려고
                            System.out.println("첫화면 입니다.");
                            //btn_quiz_back.setClickable(false); //버튼 비활성화(이전문제 못 누르게)
                            btn_quiz_back.setEnabled(false);
                            System.out.println("비활완료"); // 비활시키고 문제 1번으로 바꾸기

                            id_quiz_content.setText(quiz2_que[a][cnt]);//문제내용 텍스트
                            txt_quiz2_num.setText(Integer.toString(cnt-1));//문제번호 텍스트
                        }
                        else{

                            id_quiz_content.setText(quiz2_que[a][cnt]);//문제내용 텍스트
                            txt_quiz2_num.setText(Integer.toString(cnt-1));//문제번호 텍스트
                        }







                        ////--이전문제 클릭했으니까 다음문제 활성화------------------------
                        btn_quiz_next.setSelected(false);
                        btn_quiz_next.setText("다음문제");
                        //////------------------------------------------------------


                    }
                });

                ////////--------------------------------------------------------------------
                String test_img = "http://goo.gl/gEgYUd";
                img_quiz_content.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), quiz2_img_activity.class);
                        intent.putExtra("test_img",test_img); //값 넘김
                        //ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getApplicationContext(),"test_img");
                        startActivity(intent);


                    }
                });





            }

        }


        ///////----------------------------------------------------------------------------------




        ///////-------타이머 줄어들게 하기--------------------------------------------------------------

        ///////----------모의고사 시간 (00 : 00)---------------------------------------------------------------
        TextView id_quiz_timer1 = (TextView) findViewById(R.id.id_quiz_timer1); //카운트다운 할 곳.

        ///////-------------------------------------------------------------------------------------------------
        String min = String.format("%02d", Integer.parseInt(data.get(2)));//모의고사 시간(분(04이렇게)) 가져옴
        String sec = String.format("%02d", Integer.parseInt(data.get(3)));//모의고사 시간(초) 가져옴 30
        System.out.println("풀기 눌렀을 때 모의고사 분 : " + min);
        System.out.println("풀기 눌렀을 때 모의고사 초 : " + sec);


        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {


                id_quiz_timer1.setText(getTime());

            }

            @Override
            public void onFinish() {
                countDownTimer.start();
            }
        };
        countDownTimer.start();
        ///////----------------------------------------------------------------------------------------

    }

    ////////타이머 시간 얻는 함수 ------------------------------------------
    private String getTime(){
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        Intent intent = getIntent();

        ArrayList<String> data= (ArrayList<String>) intent.getSerializableExtra("quiz2_content");

        ///////--------모의고사 이름----------------------------------------------------------------

        String count_min = String.format("%02d", Integer.parseInt(data.get(2)));//모의고사 시간(분(04이렇게)) 가져옴
        String count_sec = String.format("%02d", Integer.parseInt(data.get(3)));//모의고사 시간(초) 가져옴 30

        int year = calendar.get(Calendar.YEAR); // 현재날짜 년도 등등 얻기위한 변수설정
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int c_hour = calendar.get(Calendar.HOUR_OF_DAY);
        int c_min = calendar.get(Calendar.MINUTE);
        int c_sec = calendar.get(Calendar.SECOND);



        Calendar baseCal = new GregorianCalendar(year,month,day,c_hour,0,0); //기준날짜 00:00:00
        Calendar targetCal = new GregorianCalendar(year,month,day,0,Integer.parseInt(data.get(2)),Integer.parseInt(data.get(3)));
        //비교대상날짜(현재날짜에 특정시간까지 타이머)


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