package com.example.a16sserver.retrofit;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.a16sserver.retrofit.dto.Test;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    /**
     * 사실상 초기화 시키는 것과 거의 동일하게 작동한다고 보면 안될까 싶어서 이렇게 작명함
     * 가능하면 여기서 다 해결하려 했는데, 각 메서드가 다 달라서 일단은 부분적으로 오버라이딩 시키는 걸로 ㅇㅇ
     * @return Retrofit이 해당 URL으로 초기화된 상태에서 리턴됩니다.
     */
    public static Retrofit Init(){
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);

        return new Retrofit.Builder()
                .baseUrl(RetrofitTool.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();
    }
}
