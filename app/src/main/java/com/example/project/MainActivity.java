package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    private TextView courseNameTV, courseTracksTV, courseBatchTV;
    private String courseName1 ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_main);
        Button Connect = (Button) findViewById(R.id.Connect);


        String url = "https://jsonkeeper.com/b/63OH";


        Connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String courseName = response.getString("courseName");
                            Toast.makeText(MainActivity.this, courseName, Toast.LENGTH_SHORT).show();
                            courseName1 = courseName;


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Fail to get data..", Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(jsonObjectRequest);
                if(courseName1.equals("")){
                    Toast.makeText(MainActivity.this, "Fail to get data..", Toast.LENGTH_SHORT).show();
                }else{
                Intent intent = new
                        Intent(MainActivity.this, AfterConnect.class);
                MainActivity.this.startActivity(intent);
                finish();
                }
            }
        });
    }
}