package com.example.app.jsonplaceholderexample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
public class RetrofitRegistry {
    private static final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private final Gson gson = new GsonBuilder().setLenient().create();

    @Bean
    PostAPI getJsonPlaceHolderAPI(){
        OkHttpClient client = new OkHttpClient.Builder()
                //서버로 요청하는데 걸리는 시간 제한(15초) -> handshake
                .connectTimeout(15, TimeUnit.SECONDS)
                //서버로 요청이 성공했다면, 응답데이터를 받는데 시간을 제한(마찬가지로 15초)
                .addInterceptor(loggingInterceptor)
                .build();

        String baseUrl = "https://jsonplaceholder.typicode.com";
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
                .create(PostAPI.class);
    }
}
