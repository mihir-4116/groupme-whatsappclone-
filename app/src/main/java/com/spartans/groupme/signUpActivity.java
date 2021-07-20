package com.spartans.groupme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.spartans.groupme.databinding.ActivitySignUpBinding;
import com.spartans.groupme.models.Users;

public class signUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(signUpActivity.this);
        progressDialog.setTitle("creating account");
        progressDialog.setMessage("we are creating your account");
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.etemail.getText().toString().isEmpty()){
                    binding.etemail.setError("Enter your email");
                    return;
                }
                if(binding.etpassword.getText().toString().isEmpty()){
                    binding.etpassword.setError("Enter your password");
                    return;
                }
                if(binding.etUserName.getText().toString().isEmpty()){
                    binding.etUserName.setError("Enter the username");
                    return;
                }
                progressDialog.show();
                auth.createUserWithEmailAndPassword(binding.etemail.getText().toString(),binding.etpassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                       if(task.isSuccessful()){
                           Users user = new Users(binding.etUserName.getText().toString(),
                                   binding.etemail.getText().toString(),
                                   binding.etpassword.getText().toString());
                           String id = task.getResult().getUser().getUid();
                           database.getReference().child("Users").child(id).setValue(user);

                           Toast.makeText(signUpActivity.this, "User created successfully", Toast.LENGTH_SHORT).show();
                       }else{
                           Toast.makeText(signUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                       }
                    }
                });
            }
        });

        binding.clickSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signUpActivity.this,signInActivity.class);
                startActivity(intent);
            }
        });
    }
}