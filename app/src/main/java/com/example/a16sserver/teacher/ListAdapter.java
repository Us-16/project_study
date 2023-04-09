package com.example.a16sserver.teacher;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a16sserver.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ListAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater;
    ArrayList<TestDO> testList;

    public ListAdapter(Context context, ArrayList<TestDO> data){
        this.mContext = context;
        this.testList = data;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return testList.size();
    }

    @Override
    public TestDO getItem(int i) {
        return testList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.question_custom, null);

        setContent(view, position);

        return view;
    }

    private void setContent(View view, int position) {
        TextView title = view.findViewById(R.id.custom_question_title);
        TextView subject = view.findViewById(R.id.custom_question_subject);
        TextView viewTv = view.findViewById(R.id.custom_question_view);
        TextView correct = view.findViewById(R.id.custom_question_correct);

        title.setText(testList.get(position).getQuestionTitle());
        title.setTextSize(24);
        title.setTypeface(title.getTypeface(), Typeface.BOLD);
        title.setTextColor(Color.BLACK);

        subject.setText(testList.get(position).getSubject());
        subject.setTextSize(16);

        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        String formattedNum = numberFormat.format(testList.get(position).getView()); // int -> String

        viewTv.setText(formattedNum);
        viewTv.setTextSize(16);

        correct.setText(String.format(Locale.KOREA,"%.1f%%", testList.get(position).getCorrect()));
        correct.setTextSize(16);

        ImageView viewImg = view.findViewById(R.id.teacher_question_list_viewImg);
        ImageView correctImg = view.findViewById(R.id.teacher_question_list_correctImg);

        //Glide.with(this).load(URL + question.getFilepath()).into(question_image);
        Glide.with(view).load(R.drawable.view).into(viewImg);
        Glide.with(view).load(R.drawable.correct).into(correctImg);

        ImageView QMainImg = view.findViewById(R.id.custom_question_image);
        Glide.with(view).load(R.drawable.paper).into(QMainImg);
    }
}
