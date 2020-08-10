package com.example.reservationportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {
    EditText userEmail;
    Button userPass;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        userEmail=(EditText)findViewById(R.id.e1);
        userPass=(Button)findViewById(R.id.b1);
        mAuth = FirebaseAuth.getInstance();

        userPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.sendPasswordResetEmail(userEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(forgotpassword.this,"Password sent to your email",Toast.LENGTH_LONG).show();
                            Intent in = new Intent(forgotpassword.this,Login.class);
                            startActivity(in);

                        }else{
                            Toast.makeText(forgotpassword.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();

                        }

                    }
                });
            }
        });

    }
}