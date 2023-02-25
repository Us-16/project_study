package com.example.a16sserver.login;//로그인화면

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a16sserver.account.User_account;
import com.example.a16sserver.R;
import com.example.a16sserver.image.ImageTestActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageButton btn_kakaologin = (ImageButton) findViewById(R.id.btn_login);
        Button btnTest = (Button)findViewById(R.id.btn_loginTest);

        btn_kakaologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), User_account.class);
                startActivity(intent);
            }
        });
        btn(btnTest);
        imageBtn();
    }


    private void imageBtn() {
        Button testImageBtn = findViewById(R.id.login_btn_imageTest);
        testImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ImageTestActivity.class);
                startActivity(intent);
            }
        });
    }

    private void btn(Button btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginTestActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}