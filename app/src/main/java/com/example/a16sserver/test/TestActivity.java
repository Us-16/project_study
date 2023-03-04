package com.example.a16sserver.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.a16sserver.R;
import com.example.a16sserver.springdo.ContainerDO;
import com.example.a16sserver.springdo.Question;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

//        Bundle bundle = getIntent().getExtras();
//        ArrayList<ParcelableTest> test = bundle.getParcelable("testContent");
        TextView textView = findViewById(R.id.test_text);
        String msg = "";

        ContainerDO test = (ContainerDO) getIntent().getSerializableExtra("dataContent");
        msg += test.toString()+"\n";
        for(Question item : test.getQuestions()){
            msg += item.getId()+"\n";
            msg += item.getSubject()+"\n";
            msg += item.getChoice1()+"\n";
            msg += item.getChoice2()+"\n";
            msg += item.getChoice3()+"\n";
            msg += item.getChoice4()+"\n";
            msg += item.getChoice5()+"\n";
            msg += "\n\n";
        }


        textView.setText(msg);
    }
}