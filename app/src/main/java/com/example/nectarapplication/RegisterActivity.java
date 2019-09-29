package com.example.nectarapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class RegisterActivity extends AppCompatActivity {

    EditText mName,mEmail,mPassword;
    Button createAccountBtn,alreadyHaveAccountBtn;

    private FirebaseAuth mAuth;

    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mName = findViewById(R.id.text_name);
        mEmail = findViewById(R.id.text_email);
        mPassword = findViewById(R.id.text_password);
        createAccountBtn = findViewById(R.id.create_account_btn);
        alreadyHaveAccountBtn = findViewById(R.id.Already_account_btn);

        mProgress = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        alreadyHaveAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(logIntent);
            }
        });

        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mName.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (!TextUtils.isEmpty(name) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(password))
                {
                    mProgress.setTitle("Creating new account....");
                    mProgress.setMessage("Please wait while we create the new account....");
                    mProgress.setCanceledOnTouchOutside(false);
                    mProgress.show();

                    SendUserToMainActivity(name,email,password);
                }
            }
        });
    }

    private void SendUserToMainActivity(final String name, final String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {
                    mProgress.dismiss();
                    Intent main = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(main);
                    finish();
                }
                else
                {
                    mProgress.hide();
                    Toast.makeText(getApplicationContext(),"Can't create new, Please check the form and try again...",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}

