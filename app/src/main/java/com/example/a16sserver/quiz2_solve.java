package com.example.a16sserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class quiz2_solve extends AppCompatActivity {

    private TextView quiz2_id;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2_solve);
        mContext = this;

        Intent intent = getIntent(); // 모의고사 이름가져오기 위한 intent 선언
        String quiz2_name = intent.getStringExtra("quiz2_name");//값 가져옴
        System.out.println("풀기버튼 눌렀을 때 모의고사 이름"+quiz2_name);

        ///////--------모의고사 이름----------------------------------------------------------------
        //String text1 = SharedPreferences_class.getString(mContext,"key_nickname_s");
        //System.out.println("닉네임 : "+text1);

        TextView id_quiz_name = (TextView) findViewById(R.id.id_quiz_name);
        id_quiz_name.setText(quiz2_name);

        //0216해야할 거 sharedPreferences_class에 모의고사 시간 넣고 불러와야함. 점수도
        ///////----------------------------------------------------------------------------------

    }
}