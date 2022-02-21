package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.project.ui.home.House;
import com.example.project.ui.home.HouseModel;
import com.example.project.ui.property.PropertyFragment;

public class searchActivity extends AppCompatActivity {

    public static int minSurface1 = 0;
    public static int maxSurface1 = 0;
    public static int minBedrooms1 = 0;
    public static int maxBedrooms1 = 0;
    public static int minPrice1 = 0;


    int surface = 0;
    int bedrooms = 0;
    int price = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Spinner spinner = findViewById(R.id.spinnerCity1);
        EditText minSurface = findViewById(R.id.minSurface1);
        EditText maxSurface = findViewById(R.id.maxSurface1);
        EditText minBedrooms = findViewById(R.id.minSurface1);
        EditText maxBedrooms = findViewById(R.id.maxSurface1);
        EditText minPrice = findViewById(R.id.minRentalPrice1);
        Spinner spinnerStatus = findViewById(R.id.statusSpinner1);
        CheckBox garden1 = findViewById(R.id.garden123);
        CheckBox balcony1 = findViewById(R.id.balcony123);
        Button search = findViewById(R.id.searchProp123);

        String[] cityList = {"Paris", "Monaco", "Ramallah", "Jerusalem", "Berlin", "München", "Seville", "Madrid", "Amsterdam", "Rotterdam"};
        ArrayAdapter<String> Arr4 = new
                ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cityList);
        spinner.setAdapter(Arr4);
        String[] FurList = {"furnished", "unfurnished"};
        ArrayAdapter<String> Arr5 = new
                ArrayAdapter<>(this, android.R.layout.simple_spinner_item, FurList);
        spinnerStatus.setAdapter(Arr5);

        RecyclerView recycler = findViewById(R.id.house_recycler2);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PropertyFragment.searchActiv = 1;

                HouseModel.searchHouses.clear();
                String minS = minSurface.getText().toString().trim();
                String maxS = maxSurface.getText().toString().trim();

                String minB = minBedrooms.getText().toString().trim();
                String maxB = maxBedrooms.getText().toString().trim();

                String minP = minPrice.getText().toString().trim();


                HouseModel.allHouses.clear();
                DataBaseHelper dataBaseHelper = new DataBaseHelper(view.getContext());
                Cursor cursor = dataBaseHelper.getAllHousesAva();
                if (cursor != null) {
                    cursor.moveToFirst();
                    for (int i = 0; i < cursor.getCount(); i++) {
                        boolean garden = false;
                        boolean balcony = false;
                        if (cursor.getString(cursor.getColumnIndex("garden")).equals("true")) {
                            garden = true;
                        }

                        if (cursor.getString(cursor.getColumnIndex("balcony")).equals("true")) {
                            balcony = true;
                        }
//                        if (garden1.isChecked()) {
//                            garden = true;
//                        }else
//                            garden = true;


                        if (cursor.getString(cursor.getColumnIndex("balcony")).equals("true")) {
                            balcony = true;
                        }

                        try {
                            minSurface1 = Integer.parseInt(minSurface.getText().toString().trim());
                            maxSurface1 = Integer.parseInt(maxSurface.getText().toString().trim());
                            surface = Integer.parseInt(cursor.getString(cursor.getColumnIndex("area")).trim());

                            minBedrooms1 = Integer.parseInt(minBedrooms.getText().toString().trim());
                            maxBedrooms1 = Integer.parseInt(maxBedrooms.getText().toString().trim());
                            bedrooms = Integer.parseInt(cursor.getString(cursor.getColumnIndex("bedrooms")).trim());

                            minPrice1 = Integer.parseInt(minPrice.getText().toString().trim());
                            price = Integer.parseInt(cursor.getString(cursor.getColumnIndex("price")).trim());
                        } catch (NumberFormatException nfe) {
                            System.out.println("Could not parse " + nfe);
                        }
                        // TODO make a case for every
                        if (spinner.getSelectedItem().toString().equals(cursor.getString(cursor.getColumnIndex("City"))) &&
                                minSurface1 <= surface && maxSurface1 >= surface &&
                                minBedrooms1 <= bedrooms && maxBedrooms1 >= bedrooms && minPrice1 <= price &&
                                cursor.getString(cursor.getColumnIndex("status")).equals(spinnerStatus.getSelectedItem().toString()) &&
                                garden == garden1.isChecked() && balcony == balcony1.isChecked()) {
                            HouseModel.searchHouses.add(new House(
                                    cursor.getInt(cursor.getColumnIndex("ID")),
                                    cursor.getString(cursor.getColumnIndex("City")),
                                    cursor.getString(cursor.getColumnIndex("address")),
                                    cursor.getString(cursor.getColumnIndex("area")),
                                    cursor.getString(cursor.getColumnIndex("year")),
                                    cursor.getString(cursor.getColumnIndex("bedrooms")),
                                    cursor.getString(cursor.getColumnIndex("price")),
                                    cursor.getString(cursor.getColumnIndex("status")),
                                    balcony, garden,
                                    cursor.getString(cursor.getColumnIndex("date")),
                                    cursor.getString(cursor.getColumnIndex("description")),
                                    cursor.getString(cursor.getColumnIndex("Owner")),
                                    cursor.getString(cursor.getColumnIndex("OwnName")),
                                    cursor.getString(cursor.getColumnIndex("bitmap"))
                            ));
                        }
                        cursor.moveToNext();
                    }
                }
                Intent intent = new
                        Intent(view.getContext(), HomeActivity.class);
                view.getContext().startActivity(intent);
            }
        });

    }
}