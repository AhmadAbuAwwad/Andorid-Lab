package com.example.project.ui.History;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.AfterConnect;
import com.example.project.DataBaseHelper;
import com.example.project.HouseAdapter4;
import com.example.project.HouseAdapter5;
import com.example.project.SignIn;
import com.example.project.ui.Menu.menuFragment;

import com.example.project.ui.home.HomeFragment;
import com.example.project.ui.home.House;
import com.example.project.ui.home.HouseModel;

import com.example.project.databinding.FragmentMenuBinding;

public class HistoryFragment extends Fragment {


    public static Dialog myDialog;
    public static int history = 0;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeFragment.k = 0;
        if (AfterConnect.Guest == 1) { // Guest
            Intent intent = new
                    Intent(getContext(), AfterConnect.class);
            getContext().startActivity(intent);
            root =null;
        }
        else if (SignIn.i == 1) {
            history = 0;
            menuFragment.history = 1;
            FragmentMenuBinding binding = FragmentMenuBinding.inflate(inflater, container, false);
            root = binding.getRoot();
            RecyclerView recycler = binding.houseRecycler1;
            recycler.setLayoutManager(new LinearLayoutManager(getContext()));
            HouseModel.allHouses.clear();

            myDialog = new Dialog(getContext());

            DataBaseHelper dataBaseHelper = new DataBaseHelper(root.getContext());
            Cursor cursor = dataBaseHelper.getAllHouses();
            Cursor cursor1 = dataBaseHelper.getRequests();

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
                        for (int ss = 0; ss < cursor1.getCount(); ss++) {


                            if (cursor1.getInt(cursor1.getColumnIndex("houseID")) == cursor.getInt(cursor.getColumnIndex("ID"))) {
                                if (cursor1.getString(cursor1.getColumnIndex("status")).equals("true")) {
                                    if (cursor.getString(cursor.getColumnIndex("Owner")).equals(SignIn.rental.getRentalId())) {
                                        HouseModel.allHouses.add(
                                                new House(cursor.getInt(cursor.getColumnIndex("ID")),
                                                        cursor.getString(cursor.getColumnIndex("City")),
                                                        cursor.getString(cursor.getColumnIndex("address")),
                                                        cursor.getString(cursor.getColumnIndex("area")),
                                                        cursor1.getString(cursor1.getColumnIndex("email")),
                                                        cursor.getString(cursor.getColumnIndex("price")),
                                                        cursor.getString(cursor.getColumnIndex("bedrooms")),
                                                        cursor.getString(cursor.getColumnIndex("status")),
                                                        garden, balcony,
                                                        cursor1.getString(cursor1.getColumnIndex("Sdate")),
                                                        cursor1.getString(cursor1.getColumnIndex("Edate")),
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
            HouseAdapter4 adapter = new HouseAdapter4(root.getContext(), HouseModel.allHouses);
            recycler.setAdapter(adapter);

        } else {


            FragmentMenuBinding binding = FragmentMenuBinding.inflate(inflater, container, false);
            root = binding.getRoot();
            history = 1;
            RecyclerView recycler = binding.houseRecycler1;
            recycler.setLayoutManager(new LinearLayoutManager(getContext()));
            HouseModel.allHouses.clear();

            myDialog = new Dialog(getContext());

            DataBaseHelper dataBaseHelper = new DataBaseHelper(root.getContext());
            Cursor cursor = dataBaseHelper.getAllHouses();
            Cursor cursor1 = dataBaseHelper.getRequests();

            cursor.moveToFirst();
            int s = 0;
            while (cursor.moveToNext()) {
                if (s == 0) {
                    cursor.moveToFirst();
                    s++;
                }
                boolean garden = false;
                boolean balcony = false;
                if (cursor.getString(cursor.getColumnIndex("garden")).equals("true")) {
                    garden = true;
                }
                if (cursor.getString(cursor.getColumnIndex("balcony")).equals("true")) {
                    balcony = true;
                }
                int s1 = 0;
                cursor1.moveToFirst();
                while (cursor1.moveToNext()) {
                    if (s1 == 0) {
                        cursor1.moveToFirst();
                        s1++;
                    }

                    if (cursor1.getInt(cursor1.getColumnIndex("houseID")) == cursor.getInt(cursor.getColumnIndex("ID"))) {
                        if (cursor1.getString(cursor1.getColumnIndex("status")).equals("true")) {
                            if (cursor1.getString(cursor1.getColumnIndex("email")).equals(SignIn.tenant.getTenantId())) {
                                HouseModel.allHouses.add(
                                        new House(cursor.getInt(cursor.getColumnIndex("ID")),
                                                cursor.getString(cursor.getColumnIndex("City")),
                                                cursor.getString(cursor.getColumnIndex("address")),
                                                cursor.getString(cursor.getColumnIndex("area")),
                                                cursor1.getString(cursor1.getColumnIndex("email")),
                                                cursor.getString(cursor.getColumnIndex("price")),
                                                cursor.getString(cursor.getColumnIndex("bedrooms")),
                                                cursor.getString(cursor.getColumnIndex("status")),
                                                garden, balcony,
                                                cursor1.getString(cursor1.getColumnIndex("Sdate")),
                                                cursor1.getString(cursor1.getColumnIndex("Edate")),
                                                cursor.getString(cursor.getColumnIndex("Owner")),
                                                cursor.getString(cursor.getColumnIndex("OwnName")),
                                                cursor.getString(cursor.getColumnIndex("bitmap"))));
                            }
                        }
                    }


                }
            }
            HouseAdapter4 adapter = new HouseAdapter4(root.getContext(), HouseModel.allHouses);
            recycler.setAdapter(adapter);


        }

        return root;
    }
}