package com.example.finalodevi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class splash extends AppCompatActivity {
  Button LoginBtn,RegisterBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        LoginBtn= findViewById(R.id.btnlogin);
        RegisterBtn= findViewById(R.id.btnregister);

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),login.class);
                startActivity(intent);
                finish();
            }
        });
        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),signup.class);
                startActivity(intent);
                finish();
            }
        });


    }
}