package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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

import com.example.project.ui.home.House;
import com.example.project.ui.home.HouseModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tenant extends AppCompatActivity {

    public int Signed1 = 0;
    public int Signed2 = 0;
    public int Signed3 = 0;
    public int Signed4 = 0;
    public int Signed5 = 0;
    public int Signed6 = 0;
    public int Signed7 = 0;
    public int Signed8 = 0;
    public int Signed9 = 0;
    public int Signed10 = 0;
    public int Signed11 = 0;
    public int Signed12 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenant);
        LinearLayout firstLinearLayout = new LinearLayout(this);
        LinearLayout LinearLayout = new LinearLayout(this);
        ScrollView scrollView = new ScrollView(this);
        LinearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(LinearLayout);

        EditText Email = (EditText) findViewById(R.id.Email1);
        EditText firstName = (EditText) findViewById(R.id.firstName);
        EditText lastName = (EditText) findViewById(R.id.lastName);
        EditText password = (EditText) findViewById(R.id.Password1);
        EditText confirmPassword = (EditText) findViewById(R.id.Password2);
        EditText salary = (EditText) findViewById(R.id.grossMonthlySalary);
        EditText occupation = (EditText) findViewById(R.id.occupation);
        EditText familySize = (EditText) findViewById(R.id.familySize);
        EditText phone = (EditText) findViewById(R.id.phoneNumber);

        TextView emailError = (TextView) findViewById(R.id.emailError);
        TextView nameError = (TextView) findViewById(R.id.nameError);
        TextView passwordError = (TextView) findViewById(R.id.passwordError);
        TextView confirmError = (TextView) findViewById(R.id.confirmError);
        TextView salaryError = (TextView) findViewById(R.id.salaryError);
        TextView occError = (TextView) findViewById(R.id.occError);
        TextView sizeError = (TextView) findViewById(R.id.sizeError);
        TextView phoneError = (TextView) findViewById(R.id.phoneError);

        Button back = (Button) findViewById(R.id.back2);
        Button create = (Button) findViewById(R.id.Create);

        String[] genderList = {"Male", "Female"};
        Spinner genderSpinner = (Spinner)
                findViewById(R.id.genderSpinner);
        ArrayAdapter<String> Arr1 = new
                ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genderList);
        genderSpinner.setAdapter(Arr1);

        String[] nationList = {"French", "Palestinian", "Spanish", "German", "Swiss", "Dutch"};

        Spinner nationSpinner = (Spinner)
                findViewById(R.id.nationSpinner);
        ArrayAdapter<String> Arr2 = new
                ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nationList);
        nationSpinner.setAdapter(Arr2);

        String[] countryList = {"France", "Palestine", "Spain", "Germany", "Switzerland", "Netherlands"};
        Spinner countrySpinner = (Spinner)
                findViewById(R.id.countrySpinner);
        ArrayAdapter<String> Arr3 = new
                ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryList);
        countrySpinner.setAdapter(Arr3);

        String[] cityList = {""};
        String[] city1List = {"Paris", "Monaco"};
        String[] city2List = {"Ramallah", "Jerusalem"};
        String[] city3List = {"Seville", "Madrid"};
        String[] city4List = {"Berlin", "München"};
        String[] city5List = {"Zürich", "Basel"};
        String[] city6List = {"Amsterdam", "Rotterdam "};
        Spinner citySpinner = (Spinner) findViewById(R.id.citySpinner);
        ArrayAdapter<String> Arr4 = new
                ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cityList);
        citySpinner.setAdapter(Arr4);

        //BACK BUTTON
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(Tenant.this, SignUp.class);
                Tenant.this.startActivity(intent);
                finish();
            }
        });

        //country Spinner When selecting
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Object item = parent.getItemAtPosition(pos);
                if (countrySpinner.getSelectedItem().toString().equals("France")) {
                    ArrayAdapter<String> Arr4 = new ArrayAdapter<String>(Tenant.this,
                            android.R.layout.simple_spinner_item, city1List);
                    citySpinner.setAdapter(Arr4);
                    phone.setText("+06");
                } else if (countrySpinner.getSelectedItem().toString().equals("Palestine")) {
                    ArrayAdapter<String> Arr4 = new ArrayAdapter<String>(Tenant.this,
                            android.R.layout.simple_spinner_item, city2List);
                    citySpinner.setAdapter(Arr4);
                    phone.setText("+970");
                } else if (countrySpinner.getSelectedItem().toString().equals("Spain")) {
                    ArrayAdapter<String> Arr4 = new ArrayAdapter<String>(Tenant.this,
                            android.R.layout.simple_spinner_item, city3List);
                    citySpinner.setAdapter(Arr4);
                    phone.setText("+34");
                } else if (countrySpinner.getSelectedItem().toString().equals("Germany")) {
                    ArrayAdapter<String> Arr4 = new ArrayAdapter<String>(Tenant.this,
                            android.R.layout.simple_spinner_item, city4List);
                    citySpinner.setAdapter(Arr4);
                    phone.setText("+49");
                } else if (countrySpinner.getSelectedItem().toString().equals("Switzerland")) {
                    ArrayAdapter<String> Arr4 = new ArrayAdapter<String>(Tenant.this,
                            android.R.layout.simple_spinner_item, city5List);
                    citySpinner.setAdapter(Arr4);
                    phone.setText("+41");
                } else if (countrySpinner.getSelectedItem().toString().equals("Netherlands")) {
                    ArrayAdapter<String> Arr4 = new ArrayAdapter<String>(Tenant.this,
                            android.R.layout.simple_spinner_item, city6List);
                    citySpinner.setAdapter(Arr4);
                    phone.setText("+31");
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
                ArrayAdapter<String> Arr4 = new ArrayAdapter<String>(Tenant.this,
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
                //Password pattern
                Pattern special = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                Pattern number = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);
                Matcher matcher = special.matcher(password.getText().toString());
                Matcher matcherNumber = number.matcher(password.getText().toString());

                boolean constainsSymbols = matcher.find();
                boolean containsNumber = matcherNumber.find();
                if (15 >= password.getText().toString().length() && password.getText().toString().length() >= 8) {
                    if (constainsSymbols == true && containsNumber == true) {
                        passwordError.setText("");
                        Signed2 = 0;
                    } else {
                        passwordError.setText("Password must contain at least a Number and Special characters");
                        Signed2 = 1;
                    }
                } else {
                    passwordError.setText(" Password must be 8 to 15 length");
                    Signed2 = 1;
                }
                if (password.getText().toString().equals(confirmPassword.getText().toString()) == false) {
                    confirmError.setText("Passwords must be the same");
                    Signed3 = 1;
                } else {
                    confirmError.setText("");
                    Signed3 = 0;
                }

                if (phone.getText().toString().length() < 10) {
                    phoneError.setText("phone must be Correct");
                    Signed4 = 1;
                } else {
                    phoneError.setText("");
                    Signed4 = 0;
                }
                if (20 >= firstName.getText().toString().length() && 3 <= firstName.getText().toString().length()) {
                    nameError.setText("");
                    Signed5 = 0;
                } else {
                    nameError.setText("Name must be 3 to 20 character");
                    Signed5 = 1;
                }
                if (20 >= lastName.getText().toString().length() && 3 <= lastName.getText().toString().length()) {
                    nameError.setText("");
                    Signed6 = 0;
                } else {
                    nameError.setText("Name must be 3 to 20 character");
                    Signed6 = 1;
                }
                if (salary.getText().toString().equals("")) {
                    salaryError.setText("salary mustn't be Empty");
                    Signed7 = 1;
                } else {
                    salaryError.setText("");
                    Signed7 = 0;
                }

                if (occupation.getText().toString().equals("")) {
                    occError.setText("occupation mustn't be Empty");
                    Signed8 = 1;
                } else {
                    occError.setText("");
                    Signed8 = 0;
                }

                if (familySize.getText().toString().equals("")) {
                    sizeError.setText("size mustn't be Empty");
                    Signed9 = 1;
                } else {
                    sizeError.setText("");
                    Signed9 = 0;
                }

                if (20 >= occupation.getText().toString().length()) {
                    occError.setText("");
                    Signed10 = 0;
                } else {
                    occError.setText("Occupation must be up 20 to character");
                    Signed10 = 1;
                }


                if (Signed1 == 0 && Signed2 == 0 && Signed3 == 0 && Signed4 == 0 && Signed5 == 0 && Signed6 == 0
                        && Signed7 == 0 && Signed8 == 0 && Signed9 == 0 && Signed10 == 0) {
                    DataBaseHelper dataBaseHelper = new
                            DataBaseHelper(Tenant.this);

                    Cursor cursor = dataBaseHelper.getAllTenants();
                    if (cursor != null) {
                        cursor.moveToFirst();
                        for (int i = 0; i < cursor.getCount(); i++) {

                            if (Email.getText().toString().trim().equals(cursor.getString(cursor.getColumnIndex("ID")).trim())) {
                                Signed11 = 1;
                                break;
                            } else
                                Signed11 = 0;
                        }
                    }
                    Cursor c = dataBaseHelper.getAllRentals();
                    if (c != null) {
                        c.moveToFirst();
                        for (int i = 0; i < c.getCount(); i++) {
                            if (Email.getText().toString().trim().equals(c.getString(c.getColumnIndex("ID")).trim())) {
                                Signed12 = 1;
                                break;
                            } else
                                Signed12 = 0;
                            c.moveToNext();
                        }
                    }
                    if (Signed11 == 0 && Signed12 == 0) {

                        // TODO     Password
                        Tenants tenant = new Tenants(
                                Email.getText().toString().trim(),
                                firstName.getText().toString().trim(),
                                lastName.getText().toString().trim(),
                                genderSpinner.getSelectedItem().toString().trim(),
                                Encrypter.EncrypterData512(password.getText().toString()).trim(),
                                nationSpinner.getSelectedItem().toString().trim(),
                                occupation.getText().toString().trim(),
                                familySize.getText().toString().trim(),
                                countrySpinner.getSelectedItem().toString().trim(),
                                citySpinner.getSelectedItem().toString().trim(),
                                phone.getText().toString().trim());


                        dataBaseHelper.insertTenant(tenant);
                        Intent intent = new
                                Intent(Tenant.this, SignIn.class);
                        Tenant.this.startActivity(intent);
                        finish();
                    } else {
                        emailError.setText("Email is already taken");
                        Signed11 = 0;
                        Signed12 = 0;
                    }
                }
            }
        });


    }
}