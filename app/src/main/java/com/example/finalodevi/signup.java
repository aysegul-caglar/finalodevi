package com.example.finalodevi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {

    Button RegisterBtn,LoginBtn;
    TextInputEditText Email,Password;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        RegisterBtn= findViewById(R.id.btn_signup);
        LoginBtn= findViewById(R.id.btn_login);
        Email= findViewById(R.id.sign_up_email);
        Password= findViewById(R.id.sign_up_password);
        progressBar= findViewById(R.id.progressbar);
         firebaseAuth= FirebaseAuth.getInstance();

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),login.class);
                startActivity(intent);
                finish();
            }
        });

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);

                String email,password;
                email=String.valueOf(Email.getText());
                password=String.valueOf(Password.getText());

                if(TextUtils.isEmpty(email)|| TextUtils.isEmpty(password)){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),"Email ve şifre boş bırakılamaz",Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(),"Kayıt başarılı",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(),"Kayıt başarısız",Toast.LENGTH_SHORT).show();
                                    Log.d("AAAAAAAAAAAAAAAAAA",task.toString());
                                }
                            }
                        });



            }
        });





    }
}