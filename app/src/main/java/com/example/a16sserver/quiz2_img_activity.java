package com.example.a16sserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class quiz2_img_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2_img);

        ImageView img_quiz2_fullscreen = (ImageView) findViewById(R.id.img_quiz2_fullscreen); //이미지뷰

        Intent intent = getIntent(); //이미지링크 불러오기
        String test_img = (String) intent.getSerializableExtra("test_img");

        System.out.println("받아온 값 : "+test_img);

        Glide.with(this).load("http://goo.gl/gEgYUd").override(300,200).into(img_quiz2_fullscreen);

        //ArrayList<String> data= (ArrayList<String>) intent.getSerializableExtra("quiz2_content");
    }
}