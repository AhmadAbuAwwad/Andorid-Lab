package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SignUp extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button renting = findViewById(R.id.Renting);
        Button tenant = findViewById(R.id.Tenant);
        Button back = (Button) findViewById(R.id.back);

        renting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(SignUp.this, Renting.class);
                SignUp.this.startActivity(intent);
                finish();
            }
        });
        tenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(SignUp.this, Tenant.class);
                SignUp.this.startActivity(intent);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(SignUp.this,SignIn.class);
                SignUp.this.startActivity(intent);
                finish();
            }
        });
        /*
        if(countrySpinner.toString().equals("Palestine")){
            adp4 = new ArrayAdapter<String>(SignUp.this,android.R.layout.simple_spinner_dropdown_item,city1List);
        }
        if(countrySpinner.toString().equals("Jordan")){
            adp4 = new ArrayAdapter<String>(SignUp.this,android.R.layout.simple_spinner_dropdown_item,city2List);
        }
        if(countrySpinner.toString().equals("Syria")){
            adp4 = new ArrayAdapter<String>(SignUp.this,android.R.layout.simple_spinner_dropdown_item,city3List);
        }
        if(countrySpinner.toString().equals("Iraq")){
            adp4 = new ArrayAdapter<String>(SignUp.this,android.R.layout.simple_spinner_dropdown_item,city4List);
        }
        if(countrySpinner.toString().equals("Qatar")){
            adp4 = new ArrayAdapter<String>(SignUp.this,android.R.layout.simple_spinner_dropdown_item,city5List);
        }
        if(countrySpinner.toString().equals("Egypt")){
            adp4 = new ArrayAdapter<String>(SignUp.this,android.R.layout.simple_spinner_dropdown_item,city6List);
        }
        */


    }
}