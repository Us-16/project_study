package com.example.a16sserver.test;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.a16sserver.R;
import com.example.a16sserver.springdo.Question;

import java.io.Serializable;
import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Intent intent = getIntent();
        ArrayList<Question> data = (ArrayList<Question>) intent.getSerializableExtra("quiz2_content");
        Log.d(TAG, data.toString());
    }
}