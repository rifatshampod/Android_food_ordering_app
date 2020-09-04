package com.example.foodapp_ssoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LogIn_ extends AppCompatActivity {

    EditText emailId, password;
    Button btn_login, btn_reset_password, btnregister;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_);



        emailId=findViewById(R.id.emailId);
        password=findViewById(R.id.password);
        btn_login=findViewById(R.id.btn_login);
        btn_reset_password=findViewById(R.id.btn_reset_password);
        btnregister=findViewById(R.id.btnregister);

        firebaseAuth=FirebaseAuth.getInstance();

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnregisterIntent= new Intent(LogIn_.this,SignUp_.class);
                startActivity(btnregisterIntent);

            }
        });

        if (firebaseAuth.getCurrentUser() != null) {                                   //to check if the user is already logged in, no need to log in again
            Intent intent = new Intent(LogIn_.this, Home_.class);
            startActivity(intent);
            finish();
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailId.getText().toString().trim();
                String user_password= password.getText().toString().trim();

                if (email.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(user_password)){
                    password.setError("Passwors is required.");
                    return;
                }
                if (user_password.length() < 6){
                    password.setError("Password must be >= 6 character ");
                }

                else {
                    firebaseAuth.signInWithEmailAndPassword(email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(LogIn_.this,"Log in successful",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LogIn_.this,Home_.class);

                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(LogIn_.this,"Error singing in. Check user Email/password "+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });



    }
}