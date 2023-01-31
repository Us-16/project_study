package com.example.a16sserver;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class fragment_myquiz extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_myquiz,container,false);

        Button btn_myquiz_all_2 = (Button) rootView.findViewById(R.id.btn_myquiz_all_2);

        //-------버튼 클릭 시 동작---------------------------------------------
        btn_myquiz_all_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MainActivity_myquiz.class); // 다음화면으로 넘기기위한 intent 선언
                startActivity(intent); //intent 실행
            }
        });
        ////-------------------------------------------------------------------

        return rootView;
    }
}

//btn_myquiz_all_2