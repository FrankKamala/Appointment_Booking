package com.example.dental;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity implements View.OnClickListener {
   // Button button,sign;
    EditText ed1,ed2; //Edit text input
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Get Firebase auth instance
        mAuth = FirebaseAuth.getInstance();


        findViewById(R.id.create).setOnClickListener(this);
        findViewById(R.id.button).setOnClickListener(this);


        //Get Firebase auth instance

      //  sign.setOnClickListener(new View.OnClickListener() {
      //      @Override
      //      public void onClick(View v) {
       //         startActivity(new Intent(login.this,SignUp.class));
         //   }
      //  });*/



       /* button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed1.getText().toString().equals("admin") &&
                        ed2.getText().toString().equals("admin")) {
                    startActivity(new Intent(login.this, admin.class)); //starts main Activity

                }else{
                    startActivity(new Intent(login.this, MainActivity.class)); //starts main Activity


                }
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 {
                    startActivity(new Intent(login.this, SignUp.class)); //starts main Activity

                }
            }
        });*/

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.create:
                finish();
                startActivity(new Intent(this, SignUp.class));
                break;

            case R.id.button:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String email = ed1.getText().toString().trim();
        String password = ed2.getText().toString().trim();
        if (email.isEmpty()) {
            ed1.setError("Email is required");
            ed1.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            ed1.setError("Please enter a valid email");
            ed1.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            ed2.setError("Password is required");
            ed2.requestFocus();
            return;
        }


        if (password.length() < 6) {
            ed2.setError("Minimum length of password should be 6");
            ed2.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);

                if(task.isSuccessful()){
                    finish();
                    Intent intent=new Intent(login.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }






  /*  public void login(View view) {

            if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                startActivity(new Intent(login.this, admin.class)); //starts main Activity
                //correcct password
            }else{
                startActivity(new Intent(login.this, MainActivity.class)); //starts main Activity
                //wrong password
            }
        }*/


}
