package com.example.a16sserver.image;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a16sserver.R;
import com.example.a16sserver.retrofit.JsonPlaceHolderApi;
import com.example.a16sserver.retrofit.RetrofitUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ImageTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_test);

        Button loadButton = findViewById(R.id.image_loadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putter();
            }
        });

    }

    private void putter() {
        EditText editText = findViewById(R.id.image_page);
        LinearLayout ly = findViewById(R.id.image_container);

        String page = editText.getText().toString();

        Retrofit retrofit = RetrofitUtil.Init();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<ArrayList<Question>> call = jsonPlaceHolderApi.getQuestionPage(Integer.parseInt(page));
        call.enqueue(new Callback<ArrayList<Question>>() {
            @Override
            public void onResponse(Call<ArrayList<Question>> call, Response<ArrayList<Question>> response) {
                if(response.isSuccessful()){
                    ly.removeAllViews();
                    ArrayList<Question> questionList = response.body();
                    String tmp, url;


                    for(Question item : questionList) {
                        tmp = "";
                        url = "http://10.0.2.2:8080";
                        url += item.getFilepath();

                        tmp += item.getId() + "\n";
                        tmp += item.getSubject() + "\n";
                        tmp += item.getContent() + "\n";
                        tmp += item.getFilepath() + "\n";
                        tmp += url + "\n";
                        tmp += item.getCreateDate() + "\n";

                        TextView tx = new TextView(getApplicationContext());
                        tx.setText(tmp);
                        ly.addView(tx);

                        if(url.equals("http://10.0.2.2:8080null"))
                            continue;//test

                        ImageView iv = new ImageView(getApplicationContext());
                        iv.setAdjustViewBounds(true);
                        Glide.with(getApplicationContext()).load(url)
                                .into(iv);
                        ly.addView(iv);
                    }
                }else{
                    Log.d(TAG, "Status code : " + response.code() );
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Question>> call, Throwable t) {
                Log.d("TEST", "Connection Fail");
                t.printStackTrace(); //서비스할 쯤 되면 이거 t.getMessage()로 변경 부탁여
            }
        });
    }
}