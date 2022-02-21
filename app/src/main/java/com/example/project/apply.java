package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project.ui.home.HomeFragment;
import com.example.project.ui.home.House;
import com.example.project.ui.home.HouseAdapter;

public class apply extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);


        TextView city1 = (TextView) findViewById(R.id.city22);
        TextView area = (TextView) findViewById(R.id.area22);
        TextView price1 = (TextView) findViewById(R.id.bedroom22);
        TextView bed1 = (TextView) findViewById(R.id.price22);
        TextView status = (TextView) findViewById(R.id.status22);
        TextView address = (TextView) findViewById(R.id.address22);
        TextView year = (TextView) findViewById(R.id.year22);
        TextView balcony = (TextView) findViewById(R.id.balcony22);
        TextView garden = (TextView) findViewById(R.id.garden22);
        TextView date = (TextView) findViewById(R.id.date222);
        TextView Owner = (TextView) findViewById(R.id.ownerTxt22);

        EditText startDate = findViewById(R.id.startDate22);
        EditText endDate =  findViewById(R.id.endDate22);

        ImageView imageView = findViewById(R.id.image22);
        if (HouseAdapter.ts2lesh.isHasgarden()) {
            garden.setText("true");
        } else {
            garden.setText("false");
        }
        if (HouseAdapter.ts2lesh.isHasbalcone()) {
            balcony.setText("true");
        } else {
            balcony.setText("false");
        }


        city1.setText(HouseAdapter.ts2lesh.getCity());
        area.setText(HouseAdapter.ts2lesh.getSurface());
        price1.setText(HouseAdapter.ts2lesh.getPrice());
        bed1.setText(HouseAdapter.ts2lesh.getNumOfbedrooms());
        status.setText(HouseAdapter.ts2lesh.getStatus());
        address.setText(HouseAdapter.ts2lesh.getAddress());
        year.setText(HouseAdapter.ts2lesh.getYear());
        date.setText(HouseAdapter.ts2lesh.getDate());
        Owner.setText(HouseAdapter.ts2lesh.getOwnerName());
        imageView.setImageBitmap(StringToBitMap(HouseAdapter.ts2lesh.getBitmap()));

        Button apply = findViewById(R.id.apply22);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(v.getContext());
                dataBaseHelper.request(HouseAdapter.ts2lesh.getId(),startDate.getText().toString(),endDate.getText().toString(), SignIn.tenant.getTenantId());
                Intent intent = new Intent(v.getContext(), HomeActivity.class);
                v.getContext().startActivity(intent);
            }
        });

    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}