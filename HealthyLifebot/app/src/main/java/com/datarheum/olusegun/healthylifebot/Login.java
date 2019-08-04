package com.datarheum.olusegun.healthylifebot;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {




    EditText txtemail, txtpassword;
    Button btn_signin;
    android.widget.ProgressBar ProgressBar;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private Object GoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        firebaseAuth = FirebaseAuth.getInstance();
        final EditText txtemail = findViewById(R.id.email);
        final EditText txtpassword = findViewById(R.id.password);
        final Button btn_signin = findViewById(R.id.login);
        final android.widget.ProgressBar ProgressBar = findViewById(R.id.progressBar);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            if (firebaseUser != null){
                Toast.makeText(Login.this, "Authentication to healthylife Successful", Toast.LENGTH_SHORT).show();
                Intent log = new Intent(Login.this,WelcomeScreen.class);
                startActivity(log);
            }
            else {
                Toast.makeText(Login.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
            }

            }
        };

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = txtemail.getText().toString().trim();
                String password = txtpassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this, "Please Enter Your email", Toast.LENGTH_SHORT).show();
                    return;

                }


                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                    return;

                }

                ProgressBar.setVisibility(view.VISIBLE);
                if (!(TextUtils.isEmpty(email) && TextUtils.isEmpty(password))){

                    firebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {


                                    ProgressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {

                                        startActivity(new Intent(getApplicationContext(), WelcomeScreen.class));
                                        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();



                                    } else {

                                        Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });
                }


            }
        });

    }



    public void sign(View view) {
        Intent signupclick = new Intent(Login.this, NewSignup.class);
        startActivity(signupclick);
    }

    public void googlesign(View view) {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


    }


}
