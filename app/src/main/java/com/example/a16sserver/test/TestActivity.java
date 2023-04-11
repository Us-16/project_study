package com.example.a16sserver.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.a16sserver.R;
import com.example.a16sserver.springdo.ContainerDO;
import com.example.a16sserver.springdo.Question;
import com.example.a16sserver.teacher.StudentList;
import com.example.a16sserver.teacher.StudentParcelable;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Intent intent = getIntent();
        if(intent != null){
            StudentList spList = intent.getParcelableExtra("studentList");
            for(StudentParcelable item : spList.getStudents())
                System.out.println(item.getName() + " : " + item.getNum());
        }
    }
}