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

public class LoginActivity extends AppCompatActivity {
    EditText email,pass;
    Button login,register;

    FirebaseAuth Auth;
    ProgressDialog mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        email=findViewById(R.id.editText);
        pass=findViewById(R.id.editText2);
        login=findViewById(R.id.button);
        register=findViewById(R.id.button2);
        register.findViewById(R.id.button2);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail =email.getText().toString().trim();
                String password =pass.getText().toString().trim();
                if(!TextUtils.isEmpty(mail)||!TextUtils.isEmpty(password)){

                    mProgress.setTitle("logging to your  account....");
                    mProgress.setMessage("Please wait ....");
                    mProgress.setCanceledOnTouchOutside(false);
                    mProgress.show();

                    SendUserToMainActivity(mail,password);
                }
            }
        });

    }


    private void SendUserToMainActivity( final String email, String password) {
        Auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mProgress.dismiss();
                    Intent main = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(main);
                    finish();
                }
                else {

                    mProgress.hide();
                    Toast.makeText(getApplicationContext(),"Can'tloggin try again...",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
