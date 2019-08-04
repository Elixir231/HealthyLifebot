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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    /*EditText txtname, txtemail, txtpassword, txtcpassword;
    Button btn_signup;
    ProgressBar ProgressBar;*/
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final EditText txtname = findViewById(R.id.name);
        final EditText txtemail = findViewById(R.id.email);
        final EditText txtpassword = findViewById(R.id.password);
        final EditText txtcpassword = findViewById(R.id.confirmPassword);
        final Button btn_signup = findViewById(R.id.signupreg);
        final android.widget.ProgressBar ProgressBar = findViewById(R.id.progressBar);


        firebaseAuth = FirebaseAuth.getInstance();

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                String name = txtname.getText().toString().trim();
                String email = txtemail.getText().toString().trim();
                String password = txtpassword.getText().toString().trim();
                String cpassword = txtcpassword.getText().toString().trim();


                if (TextUtils.isEmpty(name)){
                    Toast.makeText(Signup.this, "Please Enter Your name", Toast.LENGTH_SHORT).show();
                    return;

                }

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Signup.this, "Please Enter Your email", Toast.LENGTH_SHORT).show();
                    return;

                }


                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Signup.this, "Input the password you wish to use", Toast.LENGTH_SHORT).show();
                    return;

                }

                if (TextUtils.isEmpty(cpassword)){
                    Toast.makeText(Signup.this, "Confirm Password field cannot be empty", Toast.LENGTH_SHORT).show();
                    return;

                }

                if (password.length()<6){

                    Toast.makeText(Signup.this, "Enter at least 6 characters for your password", Toast.LENGTH_SHORT).show();
                }



                ProgressBar.setVisibility(view.VISIBLE);


                if (password.equals(cpassword)){

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {


                                    ProgressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {

                                        startActivity(new Intent(getApplicationContext(), WelcomeScreen.class));
                                        Toast.makeText(Signup.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                                    } else {

                                        Toast.makeText(Signup.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });




                }





            }


        }





        );



    }
}