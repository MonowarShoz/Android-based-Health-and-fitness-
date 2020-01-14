package com.shoz.monowar.fitnessandhealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

   private EditText LoginEmail,LoginPassword;
   private   TextView textLogin;
   private Button LoginBtn,ForgetPass;
   private ProgressBar logProgress;
   private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();



        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, ReccommendedCalorie.class));
            finish();
        }





        LoginEmail = (EditText) findViewById(R.id.LogTextEmail);
        LoginPassword = (EditText) findViewById(R.id.LogTextPassword);
        logProgress = (ProgressBar) findViewById(R.id.Loginprogress);
        LoginBtn = (Button) findViewById(R.id.LoginButton);
        textLogin = (TextView) findViewById(R.id.textLogin);
        ForgetPass = (Button) findViewById(R.id.btn_forget_password);

        //mAuth = FirebaseAuth.getInstance();




        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, signup.class));
            }
        });
        ForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPassword.class));
            }
        });


        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = LoginEmail.getText().toString();
                final String password = LoginPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                logProgress.setVisibility(View.VISIBLE);

                //authenticate user
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                logProgress.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        LoginPassword.setError(getString(R.string.minimum_password));
                                    } else {
                                        Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(LoginActivity.this, Profile.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });

            }
        });
    }
}






