package com.example.a16sserver.image;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a16sserver.R;
import com.example.a16sserver.retrofit.JsonPlaceHolderApi;
import com.example.a16sserver.retrofit.RetrofitUtil;
import com.example.a16sserver.retrofit.dto.Upload;

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
        TextView titleView = findViewById(R.id.image_title);
        TextView contentView = findViewById(R.id.image_content);
        TextView filepathView = findViewById(R.id.image_filepath);
        ImageView imageView = findViewById(R.id.iamge_testImage);

        String page = editText.getText().toString();



        Retrofit retrofit = RetrofitUtil.Init();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<Upload> call = jsonPlaceHolderApi.getFiles(Long.parseLong(page));
        call.enqueue(new Callback<Upload>() {
            @Override
            public void onResponse(Call<Upload> call, Response<Upload> response) {
                if (response.isSuccessful()) {
                    Upload up = response.body();

                    String title = up.getTitle();
                    String content = up.getContent();
                    String filepath = up.getFilepath();

                    titleView.setText(up.getTitle());
                    contentView.setText(up.getContent());
                    filepathView.setText(up.getFilepath());

                    String imageURL = "http://10.0.2.2:8080" + filepath;
                    Glide.with(getApplicationContext()).load(imageURL).into(imageView);
                    //imageView.setImageResource(R.drawable.img_home);
                } else {
                    Log.e(TAG, "Check downs");
                    Log.e(TAG, "Status code : " + response.code());
                    Log.e(TAG, response.errorBody().toString());
                    Log.e(TAG, call.request().body().toString());
                }
            }

            @Override
            public void onFailure(Call<Upload> call, Throwable t) {
                Log.e("TEST", "Connection Fail");
            }
        });
    }
}