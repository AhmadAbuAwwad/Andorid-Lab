package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;

import com.example.project.ui.Menu.menuFragment;
import com.example.project.ui.home.HomeFragment;
import com.example.project.ui.home.House;
import com.example.project.ui.home.HouseModel;

public class editActivity extends AppCompatActivity {
    public static Dialog historyDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        menuFragment.history = 1;
        HomeFragment.k = 0;
        historyDialog = new Dialog(this);

        RecyclerView recycler = findViewById(R.id.houseRecyclerHistory5);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        HouseModel.allHouses.clear();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        Cursor cursor = dataBaseHelper.getMyHouses(SignIn.rental.getRentalId());  // TODO  5leha tjeeeb mn table many to many
        if (cursor != null) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                String email = cursor.getString(cursor.getColumnIndex("Owner"));
                boolean garden = false;
                boolean balcony = false;
                if (cursor.getString(cursor.getColumnIndex("garden")).equals("true")) {
                    garden = true;
                }

                if (cursor.getString(cursor.getColumnIndex("balcony")).equals("true")) {
                    balcony = true;
                }

                HouseModel.allHouses.add(
                        new House(cursor.getInt(cursor.getColumnIndex("ID")),
                                cursor.getString(cursor.getColumnIndex("City")),
                                cursor.getString(cursor.getColumnIndex("address")),
                                cursor.getString(cursor.getColumnIndex("area")),
                                cursor.getString(cursor.getColumnIndex("year")),
                                cursor.getString(cursor.getColumnIndex("price")),
                                cursor.getString(cursor.getColumnIndex("bedrooms")),
                                cursor.getString(cursor.getColumnIndex("status")),
                                garden, balcony,
                                cursor.getString(cursor.getColumnIndex("date")),
                                cursor.getString(cursor.getColumnIndex("description")),
                                cursor.getString(cursor.getColumnIndex("Owner")),
                                cursor.getString(cursor.getColumnIndex("OwnName")),
                                cursor.getString(cursor.getColumnIndex("bitmap"))));

                cursor.moveToNext();
            }
        }
        HouseAdapter3 adapter = new HouseAdapter3(this, HouseModel.allHouses);
        recycler.setAdapter(adapter);
    }
}