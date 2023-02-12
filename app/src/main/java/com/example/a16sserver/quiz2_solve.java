package com.example.a16sserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class quiz2_solve extends AppCompatActivity {

    private TextView quiz2_id;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2_solve);

        ///////--------이전 문제 맞은 갯수, 총갯수----------------------------------------------------------------
        //풀기 눌렀을 때 값 불러와야됨
        //SharedPreferences_class.setString(mContext,"key_quiz_name","14"); //이전문제 맞은갯수

        //String text1 = SharedPreferences_class.getString(mContext,"key_quiz_name");
        //System.out.println("모의고사 이름 : "+text1);

        //id_quiz_name = (EditText)findViewById(R.id.id_quiz_name); //닉네임 가져옴.

        //id_quiz_name.setText(text1);

        ///////----------------------------------------------------------------------------------

    }
}