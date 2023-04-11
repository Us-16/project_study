package com.example.a16sserver.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a16sserver.R;
import com.example.a16sserver.test.TestActivity;
import com.example.a16sserver.tool.ConvertSpannable;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;


public class TeacherMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);

        headText();
        barChartTest();
        PrintMostSubject();
        goToNext();
        setPercentText();
    }

    private void goToNext() {
        Button nextBtn = findViewById(R.id.teacher_main_btnGoToNext);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(TeacherMainActivity.this, QuestionInfoListActivity.class);

                //데이터 전달 테스트
                ArrayList<StudentParcelable> studentList = new ArrayList<>();
                studentList.add(new StudentParcelable("Jorim", 3));
                studentList.add(new StudentParcelable("HyeHwa", 4));
                StudentList list = new StudentList(studentList);

                Intent intent = new Intent(TeacherMainActivity.this, TestActivity.class);
                intent.putExtra("studentList", list);
                startActivity(intent);
            }
        });


    }

    private void headText() {
        ConvertSpannable spannable = new ConvertSpannable();
        TextView head = findViewById(R.id.teacher_main_head);
        String teacher = "JorimJoram"; //나중에 알아서 잘 딱 바꾸세여(알잘딱바세)
        int num = 17268425;
        //자릿수 표시해보자
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        String formattedNum = numberFormat.format(num); // int -> String

        String full = String.format(Locale.KOREA,"%s 강사님!\n현재 %s 명의 학생들이\n강사님의 문제에 도전했습니다!", teacher, formattedNum);
        String target = String.format("%s", teacher);
        String targetNum = String.format(Locale.KOREA, "%s 명", formattedNum);

        SpannableString first = spannable.spannableString(full, target, Typeface.BOLD, 1.3f, Color.parseColor("#000000"));

        head.setText(spannable.moreSpannable(first, full.indexOf(targetNum), full.indexOf(targetNum) + targetNum.length(), Typeface.BOLD, 1.1f, Color.BLACK));
        head.setTextSize(dpToPx(6));
    }

    private void barChartTest() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(3, 15f));
        entries.add(new BarEntry(2, 21f));
        entries.add(new BarEntry(1, 32f));
        entries.add(new BarEntry(0, 25f));

        Collections.reverse(entries);

        ValueFormatter valueFormatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                // value를 이용하여 Y값을 반환
                // 예: value를 문자열로 변환하여 반환
                return String.valueOf(value);
            }
        };

        BarDataSet dataSet = new BarDataSet(entries, "Label");
        dataSet.setValueFormatter(valueFormatter);

        HorizontalBarChart barChart = findViewById(R.id.barChart);

        //그라데이션 넣기
        dataSet.setColors(setGradient());


        XAxis xAxis = barChart.getXAxis();
        YAxis yAxis = barChart.getAxisRight();
        YAxis yAxis2 = barChart.getAxisLeft();

        yAxis2.setEnabled(false);

        xAxis.setDrawGridLines(false);
        yAxis.setGridLineWidth(1f);

        barChart.getLegend().setEnabled(false);

        barChart.setData(new BarData(dataSet)); //데이터 셋 설정
        barChart.setDescription(null); //데이터 설명 제거
        barChart.setDrawValueAboveBar(false); // 막대 위에 값 표기
        barChart.setPinchZoom(false); //확대/축소 기능 제거


        // x축의 레이블 위치 설정 (위쪽 또는 아래쪽)
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); //이렇게 되면 데이터가 정반대로 나타납니다. 그래서 저장되는 순서를 바꿨습니다.
        // x축의 레이블 개수 설정 (표시할 레이블 개수)
        xAxis.setLabelCount(4);
        // x축의 레이블 텍스트 크기 설정
        xAxis.setTextSize(15f);
        // x축의 레이블 색상 설정
        xAxis.setTextColor(Color.BLACK);
        // x축의 레이블 간격 설정 (1이면 모든 레이블을 표시, 2면 레이블 하나를 건너뛰고 표시, 3이면 두 개를 건너뛰고 표시, ... 등)
        xAxis.setGranularity(1f);

        // x축의 값 포맷 설정 (예시: 소수점 한 자리까지 표시)
        // X축 값을 포맷팅하는 ValueFormatter 설정
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                // 여기에서 학생 이름을 반환하도록 로직을 작성합니다.
                // 예시: 학생 이름 배열이 있다고 가정하고, value를 인덱스로 사용하여 학생 이름을 반환합니다.
                String[] students = {"그 외", "3학년", "2학년", "1학년"};
                return students[(int)value];
            }
        });
        barChart.setDrawValueAboveBar(true); // 막대 레이블을 막대 위에 표시하도록 설정
        PercentCustomFormatter percentCustomFormatter = new PercentCustomFormatter();
        yAxis.setValueFormatter(percentCustomFormatter);

        barChart.invalidate(); //차트 그리기
    }

    private int[] setGradient() {
        return new int[] {Color.parseColor("#002D9F"),
                Color.parseColor("#074CFC"),
                Color.parseColor("#4E7FFF"),
                Color.parseColor("#9EB9FF")};
    }

    private class PercentCustomFormatter extends ValueFormatter {
        @Override
        public String getFormattedValue(float value) {
            String[] score = String.valueOf(value).split("\\.");
            return score[0] + "%";
        }
    }

    private void PrintMostSubject(){
        ConvertSpannable spannable = new ConvertSpannable();
        TextView subjectTextView = findViewById(R.id.teacher_main_mostSubject);

        String full = String.format("%s 강사님은\n%s 과목을\n가장 많이 출제하셨습니다!", "Jorim", "코딩");
        String target = String.format("%s 과목", "코딩");

        subjectTextView.setText(spannable.spannableString(full, target, Typeface.BOLD, 1.3f, Color.parseColor("#0085ff")));

        //dp로 바꾸기 by GPT

        subjectTextView.setTextColor(Color.parseColor("#000000"));

        subjectTextView.setTextSize(dpToPx(8));
    }

    private void setPercentText(){
        TextView percentTv = findViewById(R.id.teacher_main_percent);
        double percent = 35.1;

        percentTv.setText(String.format(Locale.KOREA,"%.1f%%", percent));
        percentTv.setTextSize(dpToPx(10));
        percentTv.setTextColor(Color.BLACK);
        percentTv.setTypeface(percentTv.getTypeface(), Typeface.BOLD);
    }

    private float dpToPx(float dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

}