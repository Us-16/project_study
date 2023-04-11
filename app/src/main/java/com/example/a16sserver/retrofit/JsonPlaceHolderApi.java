package com.example.a16sserver.retrofit;

import com.example.a16sserver.retrofit.dto.Response_check;
import com.example.a16sserver.retrofit.dto.StudentAccount;
import com.example.a16sserver.retrofit.dto.Upload;
import com.example.a16sserver.springdo.LoginResultDO;
import com.example.a16sserver.springdo.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

//    @GET("api/question")
//    Call<List<Question>> getQuestions();


    @GET("api/student/{username}/{password}")
    Call<LoginResultDO> StudentLogin(@Path("username") String username, @Path("password") String password);

    @Headers("Content-Type: application/json")
    @POST("/api/signup")
    Call<StudentAccount> signupData(@Body HashMap<String, Object> param);

    @GET("api/check_response/{username}")
    Call<Response_check> getCheck(@Path("username") String username);

    @GET("api/image_test/{id}")
    Call<Upload> getFiles(@Path("id") Long id);

    @GET("api/page/{page}")
    Call<ArrayList<Question>> getQuestionPage(@Path("page") int page);

    @GET("api/page/info")
    Call<Integer> getQuestionPageInfo();

    @GET("api/teacher/{username}/{password}")
    Call<LoginResultDO> TeacherLogin(@Path("username")String test_id, @Path("password") String test_pw);

    @GET("api/question/{t_id}")
    Call<List<Question>> getByT_id(@Path("t_id")Long t_id);
}
