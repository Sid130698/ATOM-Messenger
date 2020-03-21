package com.example.atommessenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private EditText UserEmail, UserPassword;
    private Button CreateAccountButton;
    private TextView AlreadyHavEAccountLink;
    private FirebaseAuth mAuth ;
    private ProgressDialog loadingBar;
    private DatabaseReference rootReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        InitializeFields();
        AlreadyHavEAccountLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }

        });
        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });
        mAuth=FirebaseAuth.getInstance();
        rootReference= FirebaseDatabase.getInstance().getReference();
    }

    private void createNewAccount() {
        String email = UserEmail.getText().toString();
        String password = UserPassword.getText().toString();
        if (TextUtils.isEmpty(email))
            Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show();

if(TextUtils.isEmpty(password))


        Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show();
else{
    loadingBar.setTitle("Creating new account");
    loadingBar.setMessage("please wait,while we are creating new account for you");
    loadingBar.setCanceledOnTouchOutside(true);
    loadingBar.show();
    mAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                          String currentUserId=mAuth.getCurrentUser().getUid();
                          rootReference.child("Users").child(currentUserId).setValue("");
                        Toast.makeText(RegisterActivity.this,"Account Created Succesfully",Toast.LENGTH_LONG).show();
                        loadingBar.dismiss();
                        Intent MainActivityIntent=new Intent(RegisterActivity.this,MainActivity.class);
                        MainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(MainActivityIntent);
                        finish();

                }
                    else{
                        String message=task.getException().toString();
                        Toast.makeText(RegisterActivity.this, "Error : "+message, Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
            }
    });
}
    }


    private void InitializeFields() {
        UserEmail=findViewById(R.id.Register_email);
        UserPassword=findViewById(R.id.Register_password);
        CreateAccountButton=findViewById(R.id.Register_button);
        AlreadyHavEAccountLink=findViewById(R.id.Already_have_account_link);
        loadingBar=new ProgressDialog(this);
    }
}
