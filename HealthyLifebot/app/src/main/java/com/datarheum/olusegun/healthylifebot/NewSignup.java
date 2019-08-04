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

public class NewSignup extends AppCompatActivity {

    EditText txtname, txtemail, txtpassword, txtcpassword;
    Button btn_signup,logreg;
    android.widget.ProgressBar ProgressBar;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_signup);


        final EditText txtname = findViewById(R.id.namereg);
        final EditText txtemail = findViewById(R.id.emailreg);
        final EditText txtpassword = findViewById(R.id.passwordreg);
        final EditText txtcpassword = findViewById(R.id.confirmPasswordreg);
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
                                                  Toast.makeText(NewSignup.this, "Please Enter Your name", Toast.LENGTH_SHORT).show();
                                                  return;

                                              }

                                              if (TextUtils.isEmpty(email)){
                                                  Toast.makeText(NewSignup.this, "Please Enter Your email", Toast.LENGTH_SHORT).show();
                                                  return;

                                              }


                                              if (TextUtils.isEmpty(password)){
                                                  Toast.makeText(NewSignup.this, "Input the password you wish to use", Toast.LENGTH_SHORT).show();
                                                  return;

                                              }

                                              if (TextUtils.isEmpty(cpassword)){
                                                  Toast.makeText(NewSignup.this, "Confirm Password field cannot be empty", Toast.LENGTH_SHORT).show();
                                                  return;

                                              }

                                              if (password.length()<6){

                                                  Toast.makeText(NewSignup.this, "Enter at least 6 characters for your password", Toast.LENGTH_SHORT).show();
                                              }



                                              ProgressBar.setVisibility(view.VISIBLE);


                                              if (password.equals(cpassword)){

                                                  firebaseAuth.createUserWithEmailAndPassword(email, password)
                                                          .addOnCompleteListener(NewSignup.this, new OnCompleteListener<AuthResult>() {
                                                              @Override
                                                              public void onComplete(@NonNull Task<AuthResult> task) {


                                                                  ProgressBar.setVisibility(View.GONE);
                                                                  if (task.isSuccessful()) {

                                                                      startActivity(new Intent(getApplicationContext(), WelcomeScreen.class));
                                                                      Toast.makeText(NewSignup.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                                                                  } else {

                                                                      Toast.makeText(NewSignup.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                                                  }

                                                                  // ...
                                                              }
                                                          });




                                              }





                                          }


                                      }





        );


    }

    public void logreg(View view) {

    }
}
