package com.example.project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class SignIn extends Activity implements OnClickListener {

    public static int i = 0;// 0 for Rentals---------- 1 for Tenants
    // MUST BE 0 when starting

    private String username, password;
    private Button ok;
    private EditText editTextUsername, editTextPassword;
    private CheckBox saveLoginCheckBox;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    public int Signed = 0;
    public int Signed1 = 0;
    private CheckBox rememberCheck;
    private Button SignIn;
    private Button SignUp;
    private TextView emailError;
    private TextView passwordError;

    public static Rentals rental;
    public static Tenants tenant;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        LinearLayout firstLinearLayout = new LinearLayout(this);
        LinearLayout LinearLayout = new LinearLayout(this);
        ScrollView scrollView = new ScrollView(this);
        LinearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(LinearLayout);



        SignUp = (Button) findViewById(R.id.SignUp);
        emailError = (TextView) findViewById(R.id.EmailError);
        passwordError = (TextView) findViewById(R.id.PasswordError);

        ok = (Button) findViewById(R.id.buttonLogin);
        ok.setOnClickListener(this);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        saveLoginCheckBox = (CheckBox) findViewById(R.id.saveLoginCheckBox);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            editTextUsername.setText(loginPreferences.getString("username", ""));
            editTextPassword.setText(loginPreferences.getString("password", ""));
            saveLoginCheckBox.setChecked(true);
        }

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(SignIn.this, SignUp.class);
                SignIn.this.startActivity(intent);
                finish();
            }
        });

        Button back = findViewById(R.id.back4);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new
                        Intent(SignIn.this, AfterConnect.class);
                SignIn.this.startActivity(intent);
                finish();
            }
        });
    }

    public void onClick(View view) {
        if (view == ok) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editTextUsername.getWindowToken(), 0);

            username = editTextUsername.getText().toString();
            password = editTextPassword.getText().toString();

            if (saveLoginCheckBox.isChecked()) {
                loginPrefsEditor.putBoolean("saveLogin", true);
                loginPrefsEditor.putString("username", username);
                loginPrefsEditor.putString("password", password);
                loginPrefsEditor.commit();
            } else {
                loginPrefsEditor.clear();
                loginPrefsEditor.commit();
            }


            doSomethingElse();
        }
    }

    public void doSomethingElse() {

        if (editTextUsername.getText().toString().equals("")) {
            emailError.setText("Email mustn't be Empty");
            Signed = 1;
        } else {
            emailError.setText("");
            Signed = 0;
        }
        if (editTextPassword.getText().toString().equals("")) {
            passwordError.setText("password mustn't be Empty");
            Signed1 = 1;
        } else {
            passwordError.setText("");
            Signed1 = 0;
        }
        if (Signed == 0 && Signed1 == 0) {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
            if (dataBaseHelper.checkA(editTextUsername.getText().toString(), editTextPassword.getText().toString())) {
                i = 1;
                Intent intent = new Intent(SignIn.this, HomeActivity.class);
                SignIn.this.startActivity(intent);

            } else if (dataBaseHelper.checkT(editTextUsername.getText().toString(), editTextPassword.getText().toString())) {
                i = 0;
                Intent intent = new Intent(SignIn.this, HomeActivity.class);
                SignIn.this.startActivity(intent);

            }else {
                Toast.makeText(this, "Worng Email or Password", Toast.LENGTH_SHORT).show();
            }
        }
    }
}