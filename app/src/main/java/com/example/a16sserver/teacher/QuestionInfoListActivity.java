package com.example.a16sserver.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a16sserver.R;
import com.example.a16sserver.retrofit.dto.Test;
import com.example.a16sserver.tool.ConvertSpannable;

import java.util.ArrayList;
import java.util.List;

public class QuestionInfoListActivity extends AppCompatActivity {

    ArrayList<TestDO> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_info_list);

        setHead("JorimJoram"); //받아온 데이터로 넘기면 됨
        this.onlyTestDatas();
        this.setListView();
    }


    private void setHead(String username){
        ConvertSpannable spannable = new ConvertSpannable();
        TextView headText = findViewById(R.id.teacher_question_list_head);
        String full = String.format("%s 님께서\n제출하신 문제입니다", username);
        headText.setText(spannable.spannableString(full, username, Typeface.BOLD, 1.3f, Color.BLACK));
        headText.setTextSize(dpToPx(8));
    }

    /**
     * 테스트 위해서 만드는 DO List입니다. 후에는 반드시 제거하시고 가져온 데이터로 나타내세여
     * @return
     */
    private void onlyTestDatas(){
        this.dataList = new ArrayList<TestDO>();

        for(int i=0; i<10000; i++){
            dataList.add(new TestDO((long) i, "question " + i, "Math " + i, 1000+i, i));
        }
        //데이터 문제 없음. 데이터한테 하소연하지 마셈
    }

    private void setListView(){
        //참고한 거 따라해봤는데 되더라

        ListView listView = findViewById(R.id.question_list_data);
        final ListAdapter listAdapter = new ListAdapter(this, this.dataList);

        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                //Toast.makeText(getApplicationContext(), listAdapter.getItem(position).getQuestionTitle(), Toast.LENGTH_LONG).show();
                TestDO selectedItem = (TestDO)parent.getItemAtPosition(position);
                String testName = Long.toString(selectedItem.getId());

                Intent intent = new Intent(QuestionInfoListActivity.this, QuestionDetailActivity.class);
                intent.putExtra("selectedItem", testName);
                startActivity(intent);
            }
        });

    }
    private float dpToPx(float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}