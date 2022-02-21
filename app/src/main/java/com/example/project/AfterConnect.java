package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AfterConnect extends AppCompatActivity {

    public static int Guest = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_connect);
        Guest = 0;
        Button SignUp =(Button) findViewById(R.id.SignUpButton);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Guest = 0;
                Intent intent = new
                        Intent(AfterConnect.this,SignUp.class);
                AfterConnect.this.startActivity(intent);
                finish();
            }
        });

        Button SignIn =(Button) findViewById(R.id.SignInButton);

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Guest = 0;
                Intent intent = new
                        Intent(AfterConnect.this,SignIn.class);
                AfterConnect.this.startActivity(intent);
                finish();
            }
        });

        Button Guest1 =(Button) findViewById(R.id.GuestButton);

        Guest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Guest = 1;
                Intent intent = new
                        Intent(AfterConnect.this,HomeActivity.class);
                AfterConnect.this.startActivity(intent);
                finish();
            }
        });
    }
}