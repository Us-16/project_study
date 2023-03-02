package com.example.a16sserver.test;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.a16sserver.R;
import com.example.a16sserver.springdo.ParcelableTest;
import com.example.a16sserver.springdo.Question;

import java.io.Serializable;
import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Bundle bundle = getIntent().getExtras();
        ParcelableTest test = bundle.getParcelable("testContent");
        Log.d(TAG, "Im TestAct");
        Log.d(TAG, test.getId() + " : " + test.getTitle()+ " " + test.getChoice1() +  " " + test.getChoice2() + " " + test.getChoice3() + " " + test.getChoice4() + " " + test.getChoice5());
    }
}