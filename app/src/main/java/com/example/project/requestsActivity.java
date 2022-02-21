package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import com.example.project.ui.History.HistoryFragment;
import com.example.project.ui.home.HomeFragment;
import com.example.project.ui.home.House;
import com.example.project.ui.home.HouseAdapter;
import com.example.project.ui.home.HouseModel;

public class requestsActivity extends AppCompatActivity {
    public static int L = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        RecyclerView recycler = findViewById(R.id.houseRecyclerHistory87);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        L = 1;
        HouseModel.allHouses.clear();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        Cursor cursor = dataBaseHelper.getAllHouses();
        Cursor cursor1 = dataBaseHelper.getRequests();

        if (cursor != null) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {

                String email = SignIn.rental.getRentalId();
                boolean garden = false;
                boolean balcony = false;
                if (cursor.getString(cursor.getColumnIndex("garden")).equals("true")) {
                    garden = true;
                }
                if (cursor.getString(cursor.getColumnIndex("balcony")).equals("true")) {
                    balcony = true;
                }
                if (cursor1 != null) {
                    cursor1.moveToFirst();
                    for (int i1 = 0; i1 < cursor1.getCount(); i1++) {
                        if (cursor1.getInt(cursor1.getColumnIndex("houseID")) == cursor.getInt(cursor.getColumnIndex("ID"))) {
                            if (cursor1.getString(cursor1.getColumnIndex("status")).equals("wait")) {
                                if (cursor.getString(cursor.getColumnIndex("Owner")).equals(email)) {
                                    HouseModel.allHouses.add(
                                            new House(cursor.getInt(cursor.getColumnIndex("ID")),
                                                    cursor.getString(cursor.getColumnIndex("City")),
                                                    cursor.getString(cursor.getColumnIndex("address")),
                                                    cursor.getString(cursor.getColumnIndex("area")),
                                                    cursor1.getString(cursor1.getColumnIndex("email")), cursor.getString(cursor.getColumnIndex("price")),
                                                    cursor.getString(cursor.getColumnIndex("bedrooms")),
                                                    cursor.getString(cursor.getColumnIndex("status")),
                                                    garden, balcony,
                                                    cursor.getString(cursor.getColumnIndex("date")),
                                                    cursor.getString(cursor.getColumnIndex("description")),
                                                    cursor.getString(cursor.getColumnIndex("Owner")),
                                                    cursor.getString(cursor.getColumnIndex("OwnName")),
                                                    cursor.getString(cursor.getColumnIndex("bitmap"))));
                                }
                            }
                        }
                        cursor1.moveToNext();
                    }
                }

                cursor.moveToNext();
            }
        }
        HistoryFragment.history = 1;
        HouseAdapter5 adapter = new HouseAdapter5(this, HouseModel.allHouses);
        recycler.setAdapter(adapter);

    }
}