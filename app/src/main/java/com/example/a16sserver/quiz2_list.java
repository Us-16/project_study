package com.example.a16sserver; //문제선택(모의고사) -> 모의고사 리스트

public class quiz2_list {

    String quiz2_yy; //모의고사 년도 yy
    int quiz2_id; //모의고사 문제id
    String quiz2_name; //모의고사 문제 제목
    int quiz2_star; //즐겨찾기 유무

    public quiz2_list(String quiz2_yy,int quiz2_id,String quiz2_name,int quiz2_star){
        this.quiz2_yy = quiz2_yy;
        this.quiz2_id = quiz2_id;
        this.quiz2_name = quiz2_name;
        this.quiz2_star = quiz2_star;

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
}
