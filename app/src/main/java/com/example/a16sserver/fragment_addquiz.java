package com.example.a16sserver;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class fragment_addquiz extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_addquiz,container,false);

        Button btn_quiz2 = (Button) rootView.findViewById(R.id.btn_quiz2);

        btn_quiz2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MainActivity_addquiz.class); // 다음화면으로 넘기기위한 intent 선언
                startActivity(intent);
            }
        });

        return rootView;
    }

}