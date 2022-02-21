package com.example.project;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Renting extends AppCompatActivity {
    public int Signed1 = 0;
    public int Signed2 = 0;
    public int Signed3 = 0;
    public int Signed4 = 0;
    public int Signed5 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renting);

        LinearLayout firstLinearLayout=new LinearLayout(this);
        LinearLayout LinearLayout=new LinearLayout(this);
        ScrollView scrollView=new ScrollView(this);
        LinearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(LinearLayout);

        EditText Email = (EditText) findViewById(R.id.Email2);
        EditText agencyName = (EditText) findViewById(R.id.agencyName);
        EditText password = (EditText) findViewById(R.id.Password12);
        EditText confirmPassword = (EditText) findViewById(R.id.Password22);
        EditText phone = (EditText) findViewById(R.id.phoneNumber2);

        TextView emailError = (TextView) findViewById(R.id.emailError1);
        TextView passwordError = (TextView) findViewById(R.id.passwordError1);
        TextView nameError = (TextView) findViewById(R.id.nameError1);
        TextView confirmError = (TextView) findViewById(R.id.confirmError1);
        TextView phoneError = (TextView) findViewById(R.id.phoneError1);

        Button create = (Button) findViewById(R.id.Create2);
        Button back = (Button) findViewById(R.id.back1);



        String[] countryList = { "France", "Palestine", "Spain","Germany","Switzerland","Netherlands"};
        Spinner countrySpinner =(Spinner)
                findViewById(R.id.countrySpinner2);
        ArrayAdapter<String> Arr3 = new
                ArrayAdapter<>(this,android.R.layout.simple_spinner_item, countryList);
        countrySpinner.setAdapter(Arr3);

        String[] cityList = {""};
        String[] city1List = { "Paris", "Monaco"};
        String[] city2List = { "Ramallah", "Jerusalem"};
        String[] city3List = { "Seville", "Madrid"};
        String[] city4List = { "Berlin", "München"};
        String[] city5List = { "Zürich", "Basel"};
        String[] city6List = { "Amsterdam", "Rotterdam "};
        Spinner citySpinner =(Spinner) findViewById(R.id.citySpinner2);
        ArrayAdapter<String> Arr4 = new
                ArrayAdapter<>(this,android.R.layout.simple_spinner_item, city1List);
        citySpinner.setAdapter(Arr4);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(Renting.this,SignUp.class);
                Renting.this.startActivity(intent);
                finish();
            }
        });
        //country Spinner When selecting
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                if(countrySpinner.getSelectedItem().toString().equals("France")) {
                    ArrayAdapter<String> Arr4 = new ArrayAdapter<String>(Renting.this,
                            android.R.layout.simple_spinner_item, city1List);
                    citySpinner.setAdapter(Arr4);
                    phone.setText("+06");
                }
                else if(countrySpinner.getSelectedItem().toString().equals("Palestine")) {
                    ArrayAdapter<String> Arr4 = new ArrayAdapter<String>(Renting.this,
                            android.R.layout.simple_spinner_item, city2List);
                    citySpinner.setAdapter(Arr4);
                    phone.setText("+970");
                }
                else if(countrySpinner.getSelectedItem().toString().equals("Spain")) {
                    ArrayAdapter<String> Arr4 = new ArrayAdapter<String>(Renting.this,
                            android.R.layout.simple_spinner_item, city3List);
                    citySpinner.setAdapter(Arr4);
                    phone.setText("+34");
                }
                else if(countrySpinner.getSelectedItem().toString().equals("Germany")) {
                    ArrayAdapter<String> Arr4 = new ArrayAdapter<String>(Renting.this,
                            android.R.layout.simple_spinner_item, city4List);
                    citySpinner.setAdapter(Arr4);
                    phone.setText("+49");
                }
                else if(countrySpinner.getSelectedItem().toString().equals("Switzerland")) {
                    ArrayAdapter<String> Arr4 = new ArrayAdapter<String>(Renting.this,
                            android.R.layout.simple_spinner_item, city5List);
                    citySpinner.setAdapter(Arr4);
                    phone.setText("+41");
                }
                else if(countrySpinner.getSelectedItem().toString().equals("Netherlands")) {
                    ArrayAdapter<String> Arr4 = new ArrayAdapter<String>(Renting.this,
                            android.R.layout.simple_spinner_item, city6List);
                    citySpinner.setAdapter(Arr4);
                    phone.setText("+31");
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
                ArrayAdapter<String> Arr4 = new ArrayAdapter<String>(Renting.this,
                        android.R.layout.simple_spinner_item, cityList);
                citySpinner.setAdapter(Arr4);
                phone.setText("");
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // SignUp page requirements
                if (Email.getText().toString().equals("")) {
                    emailError.setText("Email mustn't be Empty");
                    Signed1 = 1;
                } else {
                    emailError.setText("");
                    Signed1 = 0;
                }

                if (phone.getText().toString().length()<10) {
                    phoneError.setText("phone must be Correct");
                    Signed4 = 1;
                } else {
                    phoneError.setText("");
                    Signed4 = 0;
                }

                if(20 >= agencyName.getText().toString().length() && 3 <= agencyName.getText().toString().length()){
                    nameError.setText("");
                    Signed5 = 0;
                } else{
                    nameError.setText("Name must be 3 to 20 character");
                    Signed5 = 1;
                }
                //Password pattern
                Pattern special= Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                Pattern number = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);
                Matcher matcher = special.matcher(password.getText().toString());
                Matcher matcherNumber = number.matcher(password.getText().toString());

                boolean constainsSymbols = matcher.find();
                boolean containsNumber = matcherNumber.find();

                if(15 >= password.getText().toString().length() && password.getText().toString().length() >=8){
                    if(constainsSymbols == true && containsNumber == true){
                        passwordError.setText("");
                        Signed2 = 0;
                    } else{
                        passwordError.setText("Password must contain at least a Number and Special characters");
                        Signed2 = 1;
                    }
                } else{
                    passwordError.setText(" Password must be 8 to 15 length");
                    Signed2 = 1;
                }

                if (password.getText().toString().equals(confirmPassword.getText().toString()) == false) {
                    confirmError.setText("Passwords must be the same");
                    Signed3 = 1;
                }else{
                    confirmError.setText("");
                    Signed3 = 0;
                }

                if (Signed1==0 && Signed2==0 && Signed3==0 && Signed4==0 && Signed5==0) {
                    DataBaseHelper dataBaseHelper = new
                            DataBaseHelper(Renting.this, "Data1", null, 1);
                    Rentals rentals = new Rentals(
                            Email.getText().toString().trim(),
                            agencyName.getText().toString().trim(),
                            password.getText().toString().trim(),
                            countrySpinner.getSelectedItem().toString().trim(),
                            citySpinner.getSelectedItem().toString().trim(),
                            phone.getText().toString().trim());
                    dataBaseHelper.insertRental(rentals);
                    Intent intent = new
                            Intent(Renting.this,SignIn.class);
                    Renting.this.startActivity(intent);
                    finish();
                }
            }
        });

    }

}