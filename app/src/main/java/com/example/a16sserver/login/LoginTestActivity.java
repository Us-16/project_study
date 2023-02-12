package com.example.a16sserver.login;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.a16sserver.Main_activity;
import com.example.a16sserver.R;
import com.example.a16sserver.retrofit.JsonPlaceHolderApi;
import com.example.a16sserver.retrofit.RetrofitUtil;
import com.example.a16sserver.retrofit.util.AES256;
import com.example.a16sserver.retrofit.util.Hex;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class LoginTestActivity extends AppCompatActivity {

    private final AES256 aes256 = new AES256();
    private final Hex hex = new Hex();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_test2);

        loginBtn();
    }

    private void loginBtn(){
        final Button signin_button = findViewById(R.id.Login_signIn);
        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText Text_id = findViewById(R.id.Login_id);
                EditText Text_pw = findViewById(R.id.Login_pw);
                String test_id;
                String test_pw;

                //암호화
                try {
                    test_id = aes256.encrypt(Text_id.getText().toString());
                    test_id = hex.stringToHex(test_id);
                    test_pw = aes256.encrypt(Text_pw.getText().toString());
                    test_pw = hex.stringToHex(test_pw);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                Log.d("Emergency", test_id + " : " + test_pw);

                //집어놓고
                Retrofit retrofit = RetrofitUtil.Init();
                JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
                Call<HashMap<String, String>> call = jsonPlaceHolderApi.Login(test_id, test_pw);
                //수신
                call.enqueue(new Callback<HashMap<String, String>>() {
                    @Override
                    public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                        if(response.isSuccessful()){
                            HashMap<String, String> lt = response.body();
                            assert lt != null;
                            try{
                                ReceiveSuccess(lt);
                            }catch (Exception e){
                                Log.e("Receive Error" , e.getMessage());
                            }
                        }else{
                            Log.d(TAG, "Check downs");
                            Log.d(TAG, "Status code : " + response.code() );
//                            Log.d(TAG, response.errorBody().toString());
//                            Log.d(TAG, call.request().body().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<HashMap<String, String>> call, Throwable t) {
                        Log.d("TEST", "Connection Fail"); //해결함ㅋㅋㅋㅋㅋ
                    }
                });
            }
        });
    }

    private void ReceiveSuccess(@NonNull HashMap<String, String> lt) throws Exception {
        Log.d("LOGIN", ""+aes256.decrypt(lt.get(aes256.encrypt("check"))).charAt(0));
        if(aes256.decrypt(lt.get(aes256.encrypt("check"))).charAt(0) == 'S'){
            Intent intent = new Intent(LoginTestActivity.this, Main_activity.class);
            startActivity(intent);
            finish();
        }else{
            TextView fail = findViewById(R.id.Login_failMessage);
            fail.setText("아이디와 비밀번호를 다시 확인해주세요");
        }
    }
}