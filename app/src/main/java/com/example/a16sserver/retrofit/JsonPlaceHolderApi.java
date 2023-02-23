package com.example.a16sserver.retrofit;

import com.example.a16sserver.retrofit.dto.CheckUsername;
import com.example.a16sserver.retrofit.dto.LoginTestDTO;
import com.example.a16sserver.retrofit.dto.Question;
import com.example.a16sserver.retrofit.dto.Response_check;
import com.example.a16sserver.retrofit.dto.StudentAccount;
import com.example.a16sserver.retrofit.dto.Test;
import com.example.a16sserver.retrofit.dto.Upload;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonPlaceHolderApi {

    @GET("api/question")
    Call<List<Question>> getQuestions();


    @GET("api/login/result/{username}/{password}")
    Call<HashMap<String, String>> Login(@Path("username") String username, @Path("password") String password);

    @Headers("Content-Type: application/json")
    @POST("/api/signup")
    Call<StudentAccount> signupData(@Body HashMap<String, Object> param);

    @GET("api/check_response/{username}")
    Call<Response_check> getCheck(@Path("username") String username);

    @GET("api/image_test/{id}")
    Call<Upload> getFiles(@Path("id") Long id);
}
