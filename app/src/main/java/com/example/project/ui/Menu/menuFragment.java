package com.example.project.ui.Menu;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.AfterConnect;
import com.example.project.DataBaseHelper;
import com.example.project.HomeActivity;
import com.example.project.R;
import com.example.project.SignIn;
import com.example.project.SignUp;
import com.example.project.databinding.FragmentRentalhistoryBinding;
import com.example.project.databinding.FragmentTenanthistoryBinding;
import com.example.project.editActivity;
import com.example.project.requestsActivity;
import com.example.project.searchActivity;
import com.example.project.ui.home.HomeFragment;
import com.example.project.ui.home.House;
import com.example.project.ui.home.HouseAdapter;
import com.example.project.ui.home.HouseModel;
import com.example.project.ui.property.PropertyFragment;

public class menuFragment extends Fragment {

    private FragmentTenanthistoryBinding binding;
    private FragmentRentalhistoryBinding binding1;
    public static int history = 1;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeFragment.k = 0;

        HouseModel.allHouses.clear();

        if (SignIn.i == 0 || AfterConnect.Guest == 1) { // Rental
            history = 2;
            binding = FragmentTenanthistoryBinding.inflate(inflater, container, false);
            root = binding.getRoot();
            RecyclerView recycler = root.findViewById(R.id.house_recyclerHistory11);
            recycler.setLayoutManager(new LinearLayoutManager(getContext()));

            DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext());
            Cursor cursor = dataBaseHelper.getAllHouses();
            Cursor cursor1 = dataBaseHelper.getAvHouses();
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

                    if (cursor1 != null) {
                        cursor1.moveToFirst();
                        for (int s = 0; s < cursor1.getCount(); s++) {
                            if (cursor1.getInt(cursor1.getColumnIndex("houseID")) == cursor.getInt(cursor.getColumnIndex("ID"))) {
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
                            }
                            cursor1.moveToNext();
                        }
                    }
                        cursor.moveToNext();
                    }
                }
                HouseAdapter adapter = new HouseAdapter(getContext(), HouseModel.allHouses);
                recycler.setAdapter(adapter);
                Button search = root.findViewById(R.id.menuSearch);
                search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new
                                Intent(root.getContext(), searchActivity.class);
                        root.getContext().startActivity(intent);
                    }
                });

            } else {    // Rentals
                binding1 = FragmentRentalhistoryBinding.inflate(inflater, container, false);
                root = binding1.getRoot();
                Button request = root.findViewById(R.id.Requests);
                Button edit = root.findViewById(R.id.editProperty);
                request.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new
                                Intent(root.getContext(), requestsActivity.class);
                        root.getContext().startActivity(intent);
                    }
                });
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new
                                Intent(root.getContext(), editActivity.class);
                        root.getContext().startActivity(intent);
                    }
                });

            }
            return root;
        }

        @Override
        public void onDestroyView () {
            super.onDestroyView();
            binding = null;
        }
    }