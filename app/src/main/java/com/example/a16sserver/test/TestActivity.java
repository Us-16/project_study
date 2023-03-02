package com.example.a16sserver.test;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.a16sserver.R;
import com.example.a16sserver.springdo.ParcelableTest;
import com.example.a16sserver.springdo.ParcelableTest2;
import com.example.a16sserver.springdo.Question;
import com.example.a16sserver.springdo.QuestionListTest;

import java.io.Serializable;
import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

//        Bundle bundle = getIntent().getExtras();
//        ArrayList<ParcelableTest> test = bundle.getParcelable("testContent");
        TextView textView = findViewById(R.id.test_text);
        String msg = "";

        ArrayList<ParcelableTest2> test = (ArrayList<ParcelableTest2>)getIntent().getSerializableExtra("testContent");
        for(ParcelableTest2 item : test) {
            msg += item.getId()+"\n";
            msg += item.getTitle()+"\n";
            msg += item.getChoice1()+"\n";
            msg += item.getChoice2()+"\n";
            msg += item.getChoice3()+"\n";
            msg += item.getChoice4()+"\n";
            msg += item.getChoice5()+"\n";
            msg += "----------------------\n";
        }
        textView.setText(msg);
    }
}