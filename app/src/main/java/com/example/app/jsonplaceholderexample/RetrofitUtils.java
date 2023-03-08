package com.example.app.jsonplaceholderexample;

import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * API를 실제 요청해주는 클래스
 * 사용이 필수는 아님
 * 사용하지 않으면 중복이 발생할 수 있음(알아서 처신 잘 하라고 아 ㅋㅋ)
 */
@Component
public class RetrofitUtils {
    public <T> T execute(Call<T> call){
        try{
            Response<T> response = call.execute();

            if(response.isSuccessful()) {
                return response.body();
            }else{
                throw new RuntimeException(response.raw().toString());
            }
        }catch(IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
