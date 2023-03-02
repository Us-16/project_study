package com.example.a16sserver;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.a16sserver.retrofit.JsonPlaceHolderApi;
import com.example.a16sserver.retrofit.RetrofitUtil;
import com.example.a16sserver.springdo.Question;
import com.example.a16sserver.springdo.QuizContent;
import com.example.a16sserver.test.TestActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class fragment_test_grade_1 extends Fragment {
    private Context mContext;
    private ListView listview = null;
    private ListViewAdapter adapter = null;
    public boolean isCheck[] = new boolean[50]; //체크박스배열
    public int chk_favorite = 0;

    //////----arraylist sharedpreference에 저장
    ArrayList<List> lists;
    Gson gson = new GsonBuilder().create();
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    final String PREF = "list";
    final String LIST = "arrayList";
    //////--------------------------------------

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_test_grade_1, container, false);
        //리스트뷰 객체 생성
        ListView listView = (ListView) rootView.findViewById(R.id.quiz2_list);


        adapter = new ListViewAdapter(); //리스트뷰 어댑터 생성
        listView.setAdapter(adapter); //리스트뷰에 어댑터를 붙여준다.

        // 모의고사 정보는 배열로 받게 되겠지만, sharedpreference에는 배열로 저장하지 않고
        // 간단하게 저장했다. 연동할때는 배열로 가져오기만 하면 돼서 다른페이지에 sharedpreference로 가져온거 필요없는건 빼기
        // 배열 시 사용 x 라고 표시해둠.

        String[] quiz2_2211 = {"22년","2211","11월 모의고사","0","3","03:30","5","3"}; //22년 11월 모의고사 데이터
        String[] quiz2_2209 = {"22년","2209","09월 모의고사","0","3","02:30","5","3"}; //22년 09월 모의고사 데이터
        String[] quiz2_2110 = {"21년","2110","10월 모의고사","0","2","04:30","5","3"}; //22년 10월 모의고사 데이터
        String[] quiz2_2103 = {"21년","2103","03월 모의고사","0","2","05:30","4","3"}; //22년 03월 모의고사 데이터
        String[] quiz2_1906 = {"19년","1906","06월 모의고사","0","2","03:30","4","3"}; //22년 06월 모의고사 데이터
        String[] quiz2_1809 = {"18년","1809","09월 모의고사","0","5","02:30","5","3"}; //22년 09월 모의고사 데이터

        //Adapter 안에 넣을 리스트 데이터  정보 담기
        //반복문으로 돌린후에 밑에꺼 나오게 바꾸기
        adapter.addItem(new quiz2_list(quiz2_2211[0],Integer.parseInt(quiz2_2211[1]),
                quiz2_2211[2],Integer.parseInt(quiz2_2211[3]),Integer.parseInt(quiz2_2211[4]),
                quiz2_2211[5],Integer.parseInt(quiz2_2211[6]),Integer.parseInt(quiz2_2211[7])));
        adapter.addItem(new quiz2_list(quiz2_2209[0],Integer.parseInt(quiz2_2209[1]),
                quiz2_2209[2],Integer.parseInt(quiz2_2209[3]),Integer.parseInt(quiz2_2209[4]),
                quiz2_2209[5],Integer.parseInt(quiz2_2209[6]),Integer.parseInt(quiz2_2211[7])));
        adapter.addItem(new quiz2_list(quiz2_2110[0],Integer.parseInt(quiz2_2110[1]),
                quiz2_2110[2],Integer.parseInt(quiz2_2110[3]),Integer.parseInt(quiz2_2110[4]),
                quiz2_2110[5],Integer.parseInt(quiz2_2110[6]),Integer.parseInt(quiz2_2211[7])));
        adapter.addItem(new quiz2_list(quiz2_2103[0],Integer.parseInt(quiz2_2103[1]),
                quiz2_2103[2],Integer.parseInt(quiz2_2103[3]),Integer.parseInt(quiz2_2103[4]),
                quiz2_2103[5],Integer.parseInt(quiz2_2103[6]),Integer.parseInt(quiz2_2211[7])));
        adapter.addItem(new quiz2_list(quiz2_1906[0],Integer.parseInt(quiz2_1906[1]),
                quiz2_1906[2],Integer.parseInt(quiz2_1906[3]),Integer.parseInt(quiz2_1906[4]),
                quiz2_1906[5],Integer.parseInt(quiz2_1906[6]),Integer.parseInt(quiz2_2211[7])));
        adapter.addItem(new quiz2_list(quiz2_1809[0],Integer.parseInt(quiz2_1809[1]),
                quiz2_1809[2],Integer.parseInt(quiz2_1809[3]),Integer.parseInt(quiz2_1809[4]),
                quiz2_1809[5],Integer.parseInt(quiz2_1809[6]),Integer.parseInt(quiz2_2211[7])));

        return rootView;
    }

//////----------리스트뷰 어댑터 구현 ----------------------------------------------------------
    public class ListViewAdapter extends BaseAdapter {



        // Adapter에 추가된 데이터를 저장하기 위한 ArrayList(리스트뷰에 문자열을 뿌려주는것)
        ArrayList<quiz2_list> items = new ArrayList<quiz2_list>();


        // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
        @Override
        public int getCount() {
            return items.size();
        }

        // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
        @Override
        public long getItemId(int position) {
            return position;
        }

        // 아이템 데이터 추가를 위한 함수.
        public void addItem(quiz2_list item) {
            items.add(item);
        }

        /////////////position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현---------------
        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            final Context context = viewGroup.getContext();
            final quiz2_list quiz2_list = items.get(position); //앞으로는 형님이라고 부르도록
            //CheckBox favoriteBtn = (CheckBox) viewGroup.findViewById(R.id.chk_quiz2_favorite);

            //------------ "row_quiz2" Layout을 inflate하여 convertView 참조 획득.--------------
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.row_quiz2, viewGroup, false);

            }
            else {
                View view = new View(context);
                view = (View) convertView;
            }
            ///----------------------------------------------------------------------------

            TextView quiz2_yy = (TextView) convertView.findViewById(R.id.quiz2_yy); //모의고사 년도
            TextView quiz2_name = (TextView) convertView.findViewById(R.id.quiz2_name); //모의고사 이름
            Button btn_quiz2_solve = (Button) convertView.findViewById(R.id.btn_quiz2_solve); //풀기 버튼
            CheckBox favoriteBtn= (CheckBox) convertView.findViewById(R.id.chk_quiz2_favorite);//즐겨찾기 버튼

            quiz2_name.setText(quiz2_list.getQuiz2_name()); //모의고사 이름에 값 넣음
            quiz2_yy.setText(quiz2_list.getQuiz2_yy()); //모의고사 년도에 값 넣음

            isCheck[0]=true;
            isCheck[1]=false;
            favoriteBtn.setChecked(isCheck[0]);


            ///-----------체크박스 체크 이벤트  -----------------------------------------
            favoriteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(favoriteBtn.isChecked()){
                        chk_favorite = 1;
                        quiz2_list.setQuiz2_star(chk_favorite);
                        Toast.makeText(context, quiz2_list.getQuiz2_name()+" : 즐겨찾기함\n"+"체크박스 :"+quiz2_list.getQuiz2_star()+"모의고사 id :" +quiz2_list.getQuiz2_id(), Toast.LENGTH_SHORT).show();
                        //체크박스 선택 해제 후 값 수정하는 것 까지는 완료했는데, 다른 프래그먼트 넘어갔다가 다시 돌아오는걸 체크해야됨. 0130에
                    }
                    else{
                        chk_favorite = 0;
                        quiz2_list.setQuiz2_star(chk_favorite);
                        Toast.makeText(context, quiz2_list.getQuiz2_name()+" : 즐겨찾기 취소함\n"+"체크박스 :"+quiz2_list.getQuiz2_star()+"모의고사 id :"+quiz2_list.getQuiz2_id(), Toast.LENGTH_SHORT).show();

                    }
                }
            });
            ///------------------------------------------------------------------------------------------


            //-------풀기 버튼 클릭 시 동작---------------------------------------------
            btn_quiz2_solve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RetrofitOn();
//                    Intent intent = new Intent(getActivity(),quiz2_solve.class); // 다음화면으로 넘기기위한 intent 선언
//                    ArrayList<String> quiz2_content = new ArrayList<>(); //누른 모의고사 내용을 배열로 전달
//                    quiz2_content.add(quiz2_list.getQuiz2_name());// 모의고사 이름
//                    quiz2_content.add(String.valueOf(quiz2_list.getQuiz2_score()));// 모의고사 점수
//                    quiz2_content.add(quiz2_list.
//                            getQuiz2_timer().substring(0,quiz2_list.getQuiz2_timer().indexOf(":"))); //모의고사 분
//                    quiz2_content.add(quiz2_list.
//                            getQuiz2_timer().substring(quiz2_list.getQuiz2_timer().indexOf(":")+1));//모의고사 초
//                    quiz2_content.add(String.valueOf(quiz2_list.getQuiz2_que())); //모의고사 문제갯수 4
//                    quiz2_content.add(String.valueOf(quiz2_list.getQuiz2_id()));// 모의고사 id

//                    QuizContent qz = new QuizContent();
//                    qz.setName(quiz2_list.getQuiz2_name());
//                    qz.setScore(quiz2_list.getQuiz2_score());
//                    qz.setTimerMin(quiz2_list.getQuiz2_timer().substring(0,quiz2_list.getQuiz2_timer().indexOf(":")));
//                    qz.setTimerSec(quiz2_list.getQuiz2_timer().substring(quiz2_list.getQuiz2_timer().indexOf(":")+1));
//                    qz.setAmount(quiz2_list.getQuiz2_que());
//                    qz.setId(quiz2_list.getQuiz2_id());

//                    System.out.println(qz.toString());
//                    intent.putExtra("quiz2_content", qz);
//                    startActivity(intent); //intent 실행

                }
            });

            return convertView;
        }
    }

    public void RetrofitOn(){
        Retrofit retrofit = RetrofitUtil.Init();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<ArrayList<Question>> call  = jsonPlaceHolderApi.getQuestionPage(1);
        call.enqueue(new Callback<ArrayList<Question>>() {
            @Override
            public void onResponse(Call<ArrayList<Question>> call, Response<ArrayList<Question>> response) {
                if(response.isSuccessful()){
                    Intent intent = new Intent(getActivity(), TestActivity.class);// 다음화면으로 넘기기위한 intent 선언

                    ArrayList<Question> questions = response.body();

                    intent.putExtra("quiz2_content", questions);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Question>> call, Throwable t) {

                Log.e(TAG, t.getMessage());
            }
        });
    }
}


