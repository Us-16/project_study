package com.example.a16sserver.account; // 닉네임 화면

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a16sserver.R;
import com.example.a16sserver.SharedPreferences_class;


public class User_account_2 extends AppCompatActivity {
    private EditText text_nickname;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account2);
        mContext = this;

        text_nickname = (EditText)findViewById(R.id.text_nickname); //닉네임 가져옴.
        Button btn_nextpage1 = (Button) findViewById(R.id.btn_nextpage1); // 다음페이지 버튼

        btn_nextpage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tmp_nickname = text_nickname.getText().toString();//닉네임을 tmp_nickname에 우선저장.(데베 저장안했으니까 아직!)
                Intent intent = new Intent(getApplicationContext(),User_account_3.class); // 다음화면으로 넘기기위한 intent 선언


                if (tmp_nickname.equals("")){  //입력값이 공백이면!
                    showToast("입력값이 비었습니다."); //토스트메세지 함수 부르기
                }
                else{
                    // SharedPreferences_class에  닉네임 저장
                    SharedPreferences_class.setString(mContext,"key_nickname_s",tmp_nickname);
                    String text = SharedPreferences_class.getString(mContext,"key_nickname_s");
                    //System.out.println("닉네임 받은것은 : "+text);
                    startActivity(intent); //다음액티비티로 넘어감.
                }
            }
            void showToast(String msg){ // 토스트메세지 함수
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });

    }


}