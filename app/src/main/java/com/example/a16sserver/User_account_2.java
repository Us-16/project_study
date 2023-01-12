package com.example.a16sserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class User_account_2 extends AppCompatActivity {
    private EditText text_nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account2);

        text_nickname = (EditText)findViewById(R.id.text_nickname);

        Button btn_nextpage1 = (Button) findViewById(R.id.btn_nextpage1); // 다음페이지 버튼
        btn_nextpage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmp_nickname = text_nickname.getText().toString();//닉네임을 tmp_nickname에 우선저장.(데베 저장안했으니까 아직!)
                Intent intent = new Intent(getApplicationContext(),User_account_3.class); // 다음화면으로 넘기기위한 intent 선언
                System.out.println(tmp_nickname);
                startActivity(intent);




            }

        });

    }


}
