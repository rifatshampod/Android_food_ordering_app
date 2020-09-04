package com.example.foodapp_ssoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp_ extends AppCompatActivity {

    EditText emailcust, usernamecust, password;
    Button register;
    ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);

        emailcust=findViewById(R.id.emailcust);
        usernamecust=findViewById(R.id.username);
        password=findViewById(R.id.password);
        register=findViewById(R.id.register);
        progressBar=findViewById(R.id.progressBar);

        databaseReference=FirebaseDatabase.getInstance().getReference("customer");
        firebaseAuth=FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = emailcust.getText().toString().trim();
                final String username = usernamecust.getText().toString().trim();
                String user_password= password.getText().toString().trim();


                if (email.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(username.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please enter username", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(user_password)){
                    password.setError("Password is required.");
                    return;
                }
                if (user_password.length() < 6){
                    password.setError("Password must be >= 6 character ");
                }

                else{
                    firebaseAuth.createUserWithEmailAndPassword(email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){

                                Customer information = new Customer(

                                        username,
                                        email
                                );

                                FirebaseDatabase.getInstance().getReference("Customer")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(SignUp_.this, "User Added successfully", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(getApplicationContext(), LogIn_.class));
                                    }
                                });

                            }
                            else {
                                Toast.makeText(SignUp_.this,"Error Registering new user",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });


    }
}