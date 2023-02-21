package com.example.a16sserver; //문제선택(모의고사) -> 모의고사 리스트

public class quiz2_list {

    String quiz2_yy; //모의고사 년도 yy
    int quiz2_id; //모의고사 문제id
    String quiz2_name; //모의고사 문제 제목
    int quiz2_star; //즐겨찾기 유무
    int quiz2_score; //점수
    String quiz2_timer; //시간
    int quiz2_ans_num; // 답갯수
    int quiz2_que; //문제갯수
    public quiz2_list(String quiz2_yy,int quiz2_id,String quiz2_name,int quiz2_star
            ,int quiz2_score, String quiz2_timer, int quiz2_ans_num,int quiz2_que
            ){
        this.quiz2_yy = quiz2_yy;
        this.quiz2_id = quiz2_id;
        this.quiz2_name = quiz2_name;
        this.quiz2_star = quiz2_star;
        this.quiz2_score = quiz2_score;
        this.quiz2_timer = quiz2_timer;
        this.quiz2_ans_num = quiz2_ans_num;
        this.quiz2_que = quiz2_que;

    }
    //-------모의고사 년도yy-----------------------
    public String getQuiz2_yy(){
        return quiz2_yy;
    }
    public void setQuiz2_yy(String quiz2_yy){
        this.quiz2_yy = quiz2_yy;
    }
    //-----------------------------------------

    //-------모의고사 id-----------------------
    public int getQuiz2_id(){
        return quiz2_id;
    }
    public void setQuiz2_id(int quiz2_id){
        this.quiz2_id = quiz2_id;
    }
    //-----------------------------------------

    //-------모의고사 이름------------------------
    public String getQuiz2_name(){
        return quiz2_name;
    }
    public void setQuiz2_name(String quiz2_name){
        this.quiz2_name= quiz2_name;
    }
    //--------------------------------------------

    //--------모의고사 즐겨찾기 유무 즐찾x : 0 , 즐찾o : 1------------------
    public int getQuiz2_star(){
        return quiz2_star;
    }
    public void setQuiz2_star(int quiz2_star){
        this.quiz2_star = quiz2_star;
    }
    //--------------------------------------------

    //--------모의고사 점수------------------
    public int getQuiz2_score(){return quiz2_score;}
    public void setQuiz2_score(int quiz2_score){
        this.quiz2_score = quiz2_score;
    }
    //--------------------------------------------

    //--------모의고사 시간------------------
    public String getQuiz2_timer(){return quiz2_timer;}
    public void setQuiz2_timer(String quiz2_timer){
        this.quiz2_timer = quiz2_timer;
    }
    //--------------------------------------------

    //--------모의고사 답갯수------------------
    public int getQuiz2_ans_num(){return quiz2_ans_num;}
    public void setQuiz2_ans_num(int quiz2_ans_num){
        this.quiz2_ans_num = quiz2_ans_num;
    }
    //--------------------------------------------

    //--------모의고사 문제갯수------------------
    public int getQuiz2_que(){return quiz2_que;}
    public void setQuiz2_que(int quiz2_que){
        this.quiz2_que = quiz2_que;
    }
    //--------------------------------------------


}
