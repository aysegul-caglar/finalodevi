package com.example.finalodevi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    Button LoginBtn, RegisterBtn;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;
    TextInputEditText LoginEmail, LoginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginBtn= findViewById(R.id.btn_login);
        RegisterBtn= findViewById(R.id.btn_signup);
        firebaseAuth= FirebaseAuth.getInstance();
        progressBar= findViewById(R.id.progressbar);
        LoginEmail= findViewById(R.id.email);
        LoginPassword= findViewById(R.id.password);

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),signup.class);
                startActivity(intent);
                finish();
            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);

                String email, password;
                email = String.valueOf(LoginEmail.getText());
                password = String.valueOf(LoginPassword.getText());

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Email ve şifre boş bırakılamaz", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(), "Giriş başarılı", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(), "Giriş başarısız", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        });

            }

        });

     }

    }
