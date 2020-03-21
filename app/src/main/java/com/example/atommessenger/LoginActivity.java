package com.example.atommessenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

private EditText UserEmail,UserPassword;
private Button LoginButton,PhoneLoginButton;
private TextView NeedNewAccountLink,ForgetPasswordLink;
private FirebaseAuth mAuth;
private ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance();


        InitializeFields();


        NeedNewAccountLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  registerIntent=new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(registerIntent);
            }
        });

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllowUserToLogin();
            }

            private void AllowUserToLogin() {
                String email = UserEmail.getText().toString();
                String password = UserPassword.getText().toString();
                if(TextUtils.isEmpty(email))
                    Toast.makeText(LoginActivity.this, "Please Enter Valid id", Toast.LENGTH_SHORT).show();
                if(TextUtils.isEmpty(password))
                Toast.makeText(LoginActivity.this, "Please Enter correct Password", Toast.LENGTH_SHORT).show();
                else{
                    loadingBar.setTitle("Signing in");
                    loadingBar.setMessage("Please wait while we sign you in");
                    loadingBar.setCanceledOnTouchOutside(true);
                    loadingBar.show();
                    mAuth.signInWithEmailAndPassword(email,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                        SendUserToMainActivity();
                                        loadingBar.dismiss();
                                    }
                                    else{
                                        String message =task.getException().toString();
                                        Toast.makeText(LoginActivity.this, "Error is :"+message, Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();
                                    }
                                }
                            });

                    }
                }
            });
    }




    private void InitializeFields() {
        LoginButton=(Button) findViewById(R.id.login_button);
        PhoneLoginButton=findViewById(R.id.Phone_login_button);
        UserEmail=findViewById(R.id.login_email);
        UserPassword=findViewById(R.id.login_password);
        NeedNewAccountLink=findViewById(R.id.need_new_account_link);
        ForgetPasswordLink=findViewById(R.id.forget_password_link);
        loadingBar=new ProgressDialog(this);
    }



    private void SendUserToMainActivity() {
        Intent loginIntent =new Intent(LoginActivity.this,MainActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }

}
