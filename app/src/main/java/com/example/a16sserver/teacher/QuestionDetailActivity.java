package com.example.a16sserver.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.a16sserver.R;

public class QuestionDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);

        Intent intent = getIntent();
        TextView text = findViewById(R.id.sendText);
        text.setText(intent.getStringExtra("selectedItem"));
    }
}