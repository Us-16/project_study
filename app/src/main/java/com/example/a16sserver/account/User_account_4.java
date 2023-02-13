package com.example.a16sserver.account; //학교입력화면

import static com.example.a16sserver.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a16sserver.Main_activity;
import com.example.a16sserver.SharedPreferences_class;

public class User_account_4 extends AppCompatActivity {
    private EditText text_school;
    private Context mContext;
    //private long backKeyPressedTime = 0; //뒤로가기 2번 == 종료
    //private Toast toast;//뒤로가기 2번 == 종료알림

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_user_account4);
        mContext = this;

        text_school = (EditText)findViewById(id.text_school);
        Button btn_nextpage3 = (Button) findViewById(id.btn_nextpage3); // 다음페이지 버튼

        btn_nextpage3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String tmp_school = text_school.getText().toString();//학교명을 tmp_school에 우선저장.(데베 저장안했으니까 아직!)
                Intent intent = new Intent(getApplicationContext(), Main_activity.class); // 다음화면으로 넘기기위한 intent 선언

                if (tmp_school.equals("")){  //입력값이 공백이면!
                    showToast("입력값이 비었습니다."); //토스트메세지 함수 부르기
                }
                else{
                    SharedPreferences_class.setString(mContext,"key_school_s",tmp_school);
                    String text = SharedPreferences_class.getString(mContext,"key_school_s");
                    System.out.println("학교:"+text);


                    startActivity(intent); //다음액티비티로 넘어감.
                }


            }
            void showToast(String msg){ // 토스트메세지 함수
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
            }

        });
    }
/*
    @Override
    public void onBackPressed() {

        // 기존 뒤로가기 버튼의 기능을 막기위해 주석처리 또는 삭제
        // super.onBackPressed();

        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지났으면 Toast Show
        // 2000 milliseconds = 2 seconds
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지나지 않았으면 종료
        // 현재 표시된 Toast 취소
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();
            toast.cancel();
        }
    }*/
}