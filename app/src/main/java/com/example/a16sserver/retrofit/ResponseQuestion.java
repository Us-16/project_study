package com.example.a16sserver.retrofit;


import com.example.a16sserver.retrofit.dto.Question;
import com.example.a16sserver.retrofit.dto.Response_check;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * 메서드 명을 Questions로 마무리 짓도록 작명할 것
 * 반드시 복수형으로 작성할 것 -> 메서드 사용하는 입장에서는 마지막에 Questions로 통일하면 됨
 */
public class ResponseQuestion {

    /**
     * 모든 문제를 다 불러옴
     */
    public Call<List<Question>> getCallQuestions(){
        Retrofit retrofit = RetrofitUtil.Init();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Question>> call = jsonPlaceHolderApi.getQuestions(); //이거때문에라도 모든 문제 불러오게 될것
        return call;
    }
    public Call<Response_check> getCallCheck(String username){
        Retrofit retrofit = RetrofitUtil.Init();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<Response_check> call = jsonPlaceHolderApi.getCheck(username); //이거때문에라도 모든 문제 불러오게 될것
        return call;
    }
}
