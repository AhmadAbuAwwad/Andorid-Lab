package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.project.ui.History.HistoryFragment;
import com.example.project.ui.home.House;
import com.example.project.ui.home.HouseModel;

public class profileHistory extends AppCompatActivity {


    public static Tenant tenant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_history);

        TextView firstName = findViewById(R.id.firstNameT);
        TextView lastName = findViewById(R.id.lastNameT);
        TextView familySize = findViewById(R.id.familySizeT);
        TextView gender = findViewById(R.id.genderT);
        TextView country = findViewById(R.id.countryT);
        TextView city = findViewById(R.id.cityT);
        TextView phone = findViewById(R.id.phoneT);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        Cursor cursor = dataBaseHelper.getAllTenants();
        if (cursor != null) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                if (HouseAdapter5.house2.getYear().equals(cursor.getString(cursor.getColumnIndex("ID")).trim())) {
                    firstName.setText(cursor.getString(cursor.getColumnIndex("firstName")));
                    lastName.setText(cursor.getString(cursor.getColumnIndex("lastName")));
                    gender.setText(cursor.getString(cursor.getColumnIndex("Gender")));
                    familySize.setText(cursor.getString(cursor.getColumnIndex("Size")));
                    country.setText(cursor.getString(cursor.getColumnIndex("Country")));
                    city.setText(cursor.getString(cursor.getColumnIndex("City")));
                    phone.setText(cursor.getString(cursor.getColumnIndex("Phone")));
                    HistoryFragment.history = 1;
                    RecyclerView recycler = findViewById(R.id.house_recycler9);
                    recycler.setLayoutManager(new LinearLayoutManager(this));
                    HouseModel.allHouses.clear();

                    Cursor cursor3 = dataBaseHelper.getAllHouses();
                    Cursor cursor1 = dataBaseHelper.getRequests();

                    if (cursor3 != null) {
                        cursor3.moveToFirst();
                        for (int i2 = 0; i2 < cursor3.getCount(); i2++) {
                            boolean garden = false;
                            boolean balcony = false;
                            if (cursor3.getString(cursor3.getColumnIndex("garden")).equals("true")) {
                                garden = true;
                            }
                            if (cursor3.getString(cursor3.getColumnIndex("balcony")).equals("true")) {
                                balcony = true;
                            }
                            if (cursor1 != null) {
                                cursor1.moveToFirst();
                                for (int i1 = 0; i1 < cursor1.getCount(); i1++) {
                                    if (cursor1.getInt(cursor1.getColumnIndex("houseID")) == cursor3.getInt(cursor3.getColumnIndex("ID"))) {
                                        if (cursor1.getString(cursor1.getColumnIndex("status")).equals("true")) {
                                            if (cursor1.getString(cursor1.getColumnIndex("email")).equals(HouseAdapter5.house2.getYear())) {
                                                HouseModel.allHouses.add(
                                                        new House(cursor3.getInt(cursor3.getColumnIndex("ID")),
                                                                cursor3.getString(cursor3.getColumnIndex("City")),
                                                                cursor3.getString(cursor3.getColumnIndex("address")),
                                                                cursor3.getString(cursor3.getColumnIndex("area")),
                                                                cursor1.getString(cursor1.getColumnIndex("email")),
                                                                cursor3.getString(cursor3.getColumnIndex("price")),
                                                                cursor3.getString(cursor3.getColumnIndex("bedrooms")),
                                                                cursor3.getString(cursor3.getColumnIndex("status")),
                                                                garden, balcony,
                                                                cursor3.getString(cursor3.getColumnIndex("date")),
                                                                cursor3.getString(cursor3.getColumnIndex("description")),
                                                                cursor3.getString(cursor3.getColumnIndex("Owner")),
                                                                cursor3.getString(cursor3.getColumnIndex("OwnName")),
                                                                cursor3.getString(cursor3.getColumnIndex("bitmap"))));

                                            }
                                        }
                                    }
                                    cursor1.moveToNext();
                                }
                            }
                            cursor3.moveToNext();
                        }
                    }
                    HouseAdapter4 adapter = new HouseAdapter4(this, HouseModel.allHouses);
                    recycler.setAdapter(adapter);
                }
                cursor.moveToNext();
            }
        }


    }
}