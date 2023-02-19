package com.example.a16sserver;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class fragment_test_grade_1 extends Fragment {
    private Context mContext;
    private ListView listview = null;
    private ListViewAdapter adapter = null;
    public boolean isCheck[] = new boolean[50]; //체크박스배열
    public int chk_favorite = 0;

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

        String[] quiz2_yy_data = new String[]{"22년","21년","20년","19년"};
        String[] quiz2_name_data = new String[]{"11월모의고사","10월모의고사","9월모의고사","6월모의고사","3월모의고사"};
        int[] quiz2_id_data = new int[]{2211,2210,2209,2106,2103,2003,1910};
        int[] quiz2_score_data = new int[]{3,3,2,2,3,3,2};
        String[] quiz2_timer_data = new String[]{"03:00","03:30","02:45","15:00","05:30","05:30","03:00"};


        //Adapter 안에 넣을 리스트 데이터  정보 담기
        //반복문으로 돌린후에 밑에꺼 나오게 바꾸기
        adapter.addItem(new quiz2_list(quiz2_yy_data[0],quiz2_id_data[0],quiz2_name_data[0],0,quiz2_score_data[0],quiz2_timer_data[0]));
        adapter.addItem(new quiz2_list(quiz2_yy_data[0],quiz2_id_data[1],quiz2_name_data[1],0,quiz2_score_data[1],quiz2_timer_data[1]));
        adapter.addItem(new quiz2_list(quiz2_yy_data[0],quiz2_id_data[2],quiz2_name_data[2],1,quiz2_score_data[2],quiz2_timer_data[2]));
        adapter.addItem(new quiz2_list(quiz2_yy_data[1],quiz2_id_data[3],quiz2_name_data[3],1,quiz2_score_data[3],quiz2_timer_data[3]));
        adapter.addItem(new quiz2_list(quiz2_yy_data[1],quiz2_id_data[4],quiz2_name_data[3],1,quiz2_score_data[4],quiz2_timer_data[4]));
        adapter.addItem(new quiz2_list(quiz2_yy_data[2],quiz2_id_data[5],quiz2_name_data[4],0,quiz2_score_data[5],quiz2_timer_data[5]));
        adapter.addItem(new quiz2_list(quiz2_yy_data[3],quiz2_id_data[6],quiz2_name_data[1],0,quiz2_score_data[6],quiz2_timer_data[6]));



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
            final quiz2_list quiz2_list = items.get(position);
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
                    Intent intent = new Intent(getActivity(),quiz2_solve.class); // 다음화면으로 넘기기위한 intent 선언

                    ArrayList<String> quiz2_content = new ArrayList<>(); //누른 모의고사 내용을 배열로 전달

                    quiz2_content.add(quiz2_list.getQuiz2_name());// 모의고사 이름
                    quiz2_content.add(String.valueOf(quiz2_list.getQuiz2_score()));// 모의고사 점수
                    quiz2_content.add(quiz2_list.getQuiz2_timer().substring(0,quiz2_list.getQuiz2_timer().indexOf(":"))); //모의고사 분
                    quiz2_content.add(quiz2_list.getQuiz2_timer().substring(quiz2_list.getQuiz2_timer().indexOf(":")+1));//모의고사 초

                    intent.putExtra("quiz2_content",quiz2_content);

/*
                    // 모의고사 이름을 풀기 누르는 화면으로 전달하기
                    intent.putExtra("quiz2_name",quiz2_list.getQuiz2_name());
                    // 모의고사 점수를 풀기 누르는 화면으로 전달하기
                    System.out.println("모의고사 점수 들어가는지 체크 : "+quiz2_list.getQuiz2_score());//잘들어옴
                    intent.putExtra("quiz2_score",quiz2_list.getQuiz2_score());

                    ////////////-----03:30 시간일 때 03과 30 따로 잘라서 전달하기 ------------------------------------
                    int timer1 ;
                    int timer2 ;

                    timer1 = Integer.parseInt(quiz2_list.getQuiz2_timer().substring(0,quiz2_list.getQuiz2_timer().indexOf(":")));
                    timer2 = Integer.parseInt(quiz2_list.getQuiz2_timer().substring(quiz2_list.getQuiz2_timer().indexOf(":")+1));

                    // 모의고사 분단위 시간을 풀기 누르는 화면으로 전달하기
                    intent.putExtra("quiz2_timer1",timer1);
                    // 모의고사 초단위 시간을 풀기 누르는 화면으로 전달하기
                    intent.putExtra("quiz2_timer2",timer2);
                    //////////------------------------------------------------------------------------------------

*/
                    Toast.makeText(context, quiz2_list.getQuiz2_name()+"모의고사 id :" +quiz2_list.getQuiz2_id() +"모의고사 점수 : "+quiz2_list.getQuiz2_score(),Toast.LENGTH_SHORT).show();
                    startActivity(intent); //intent 실행


                }
            });
            //-----------------------------------------------------------------------

            return convertView;
        }
        ///////////////---------------------------------------------------------------------------------
    }

//////--------------------------------------------------------------------------------------
}


