package com.example.a16sserver; //학년입력 화면

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class User_account_3 extends AppCompatActivity {
    private EditText text_grade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account3);

        text_grade = (EditText) findViewById(R.id.text_grade);
        Button btn_nextpage2 = (Button)findViewById(R.id.btn_nextpage2);
        btn_nextpage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmp_grade = text_grade.getText().toString();//닉네임을 tmp_nickname에 우선저장.(데베 저장안했으니까 아직!)
                Intent intent = new Intent(getApplicationContext(),User_account_4.class); // 다음화면으로 넘기기위한 intent 선언

                if (tmp_grade.equals("")){  //입력값이 공백이면!
                    showToast("입력값이 비었습니다."); //토스트메세지 함수 부르기
                }
                else{
                    System.out.println(tmp_grade);
                    startActivity(intent); //다음액티비티로 넘어감.
                }

            }
            void showToast(String msg){ // 토스트메세지 함수
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });


    }


}