package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class postProperty extends AppCompatActivity {
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
    public EditText postDateStart;
    public ImageButton imageBtn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    public void openFolder(){
        Intent chooser = new Intent(Intent.ACTION_GET_CONTENT);
        Uri uri = Uri.parse(Environment.getDownloadCacheDirectory().getPath().toString());
        chooser.addCategory(Intent.CATEGORY_OPENABLE);
        chooser.setDataAndType(uri, "*/*");
        int request = 1;
// startActivity(chooser);
        try {
            startActivityForResult(chooser,  0);
//            imageBtn1.setBackgroundResource();
        }
        catch (android.content.ActivityNotFoundException ex)
        {
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}