package com.example.a16sserver; //학교입력화면

import static com.example.a16sserver.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class User_account_4 extends AppCompatActivity {
    private EditText text_school;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_user_account4);

        text_school = (EditText)findViewById(id.text_school);
        Button btn_nextpage3 = (Button) findViewById(id.btn_nextpage3); // 다음페이지 버튼
        btn_nextpage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmp_school = text_school.getText().toString();//닉네임을 tmp_school에 우선저장.(데베 저장안했으니까 아직!)
                Intent intent = new Intent(getApplicationContext(),home_portfolio.class); // 다음화면으로 넘기기위한 intent 선언
                System.out.println(text_school);
                startActivity(intent);




            }

        });
    }
}