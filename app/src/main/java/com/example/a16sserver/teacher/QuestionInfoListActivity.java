package com.example.a16sserver.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a16sserver.R;
import com.example.a16sserver.retrofit.dto.Test;
import com.example.a16sserver.tool.ConvertSpannable;

import java.util.ArrayList;
import java.util.List;

public class QuestionInfoListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_info_list);

        setHead("JorimJoram"); //받아온 데이터로 넘기면 됨
        setListView();
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
    private ArrayList<TestDO> onlyTestDatas(){
        ArrayList<TestDO> dataList = new ArrayList<>();

        for(int i=0; i<10; i++){
            dataList.add(new TestDO(0L+i, "question " + i, "Math " + i, 1000+i, 85/(i+1)));
        }
        //데이터 문제 없음. 데이터한테 하소연하지 마셈
        return dataList;
    }

    private void setListView(){
        //참고한 거 따라해봤는데 되더라
        ListView listView = findViewById(R.id.question_list_data);

    }
    private float dpToPx(float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}