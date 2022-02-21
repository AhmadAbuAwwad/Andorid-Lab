package com.example.project.ui.home;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.AfterConnect;
import com.example.project.DataBaseHelper;
import com.example.project.HouseAdapter2;
import com.example.project.SignIn;
import com.example.project.databinding.FragmentHomeBinding;
import com.example.project.ui.Menu.menuFragment;
import com.example.project.ui.property.PropertyFragment;

public class HomeFragment extends Fragment {
    public static int k = -1;
    private FragmentHomeBinding binding;
    public static Dialog myDialog;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        DataBaseHelper dataBaseHelper = new DataBaseHelper(root.getContext());

        if (SignIn.i == 1) {
            String email = SignIn.rental.getRentalId();
            Cursor cursor2 = dataBaseHelper.getNotification(email);
            if (cursor2.moveToFirst()) {
                Toast.makeText(root.getContext(), "You have a House Request", Toast.LENGTH_SHORT).show();
                dataBaseHelper.setNotification(email);
            }
        }
        else if(AfterConnect.Guest == 1){

        }
        else {
            String email = SignIn.tenant.getTenantId();
            Cursor cursor2 = dataBaseHelper.getNotificationTen(email);
            if (cursor2.moveToFirst()) {
                Toast.makeText(root.getContext(), "You have an accepted Request", Toast.LENGTH_SHORT).show();
                dataBaseHelper.setNotificationTen(email);
            }
        }


        k = -1;
        menuFragment.history = 0;

        RecyclerView recycler = binding.houseRecycler;
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        myDialog = new Dialog(getContext());


        HouseModel.homeHouses.clear();
        Cursor cursor = dataBaseHelper.getAllHouses();
        cursor.moveToFirst();


        Cursor c = dataBaseHelper.getAllHouses();
        Cursor cursor1 = dataBaseHelper.get5recentHouses();

        if (c != null) {
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {

                boolean garden = false;
                boolean balcony = false;


                if (c.getString(c.getColumnIndex("balcony")).equals("true")) {
                    balcony = true;
                }
                if (c.getString(c.getColumnIndex("garden")).equals("true")) {
                    garden = true;
                }

                if (cursor1 != null) {

                    //more to the first row
                    cursor1.moveToFirst();

                    //iterate over rows
                    for (int s = 0; s < cursor1.getCount(); s++) {

                        if (c.getInt(c.getColumnIndex("ID")) == cursor1.getInt(cursor1.getColumnIndex("ID"))) {
                            HouseModel.homeHouses.add(
                                    new House(c.getInt(c.getColumnIndex("ID")),
                                            c.getString(c.getColumnIndex("City")),
                                            c.getString(c.getColumnIndex("address")),
                                            c.getString(c.getColumnIndex("area")),
                                            c.getString(c.getColumnIndex("year")),
                                            c.getString(c.getColumnIndex("price")),
                                            c.getString(c.getColumnIndex("bedrooms")),
                                            c.getString(c.getColumnIndex("status")),
                                            garden, balcony,
                                            c.getString(c.getColumnIndex("date")),
                                            c.getString(c.getColumnIndex("description")),
                                            c.getString(c.getColumnIndex("Owner")),
                                            c.getString(c.getColumnIndex("Owner")),
                                            c.getString(c.getColumnIndex("bitmap"))));
                        }
                        cursor1.moveToNext();
                    }
                }
                c.moveToNext();
            }
        }
//        c.moveToFirst();
//        int l = 0;
//        while (c.moveToNext()) {
//            if (l == 0) {
//                c.moveToFirst();
//                l++;
//            }
//            boolean garden = false;
//            boolean balcony = false;
//
//
//            if (c.getString(c.getColumnIndex("balcony")).equals("true")) {
//                balcony = true;
//            }
//            if (c.getString(c.getColumnIndex("garden")).equals("true")) {
//                garden = true;
//            }
//
//            int l1 = 0;
//            cursor1.moveToFirst();
//            while (cursor1.moveToNext()) {
//                if (l1 == 0) {
//                    cursor1.moveToFirst();
//                    l1++;
//                }
//                if (c.getInt(c.getColumnIndex("ID")) == cursor1.getInt(cursor1.getColumnIndex("ID"))) {
//                    HouseModel.homeHouses.add(
//                            new House(c.getInt(c.getColumnIndex("ID")),
//                                    c.getString(c.getColumnIndex("City")),
//                                    c.getString(c.getColumnIndex("address")),
//                                    c.getString(c.getColumnIndex("area")),
//                                    c.getString(c.getColumnIndex("year")),
//                                    c.getString(c.getColumnIndex("price")),
//                                    c.getString(c.getColumnIndex("bedrooms")),
//                                    c.getString(c.getColumnIndex("status")),
//                                    garden, balcony,
//                                    c.getString(c.getColumnIndex("date")),
//                                    c.getString(c.getColumnIndex("description")),
//                                    c.getString(c.getColumnIndex("Owner")),
//                                    c.getString(c.getColumnIndex("Owner")),
//                                    c.getString(c.getColumnIndex("bitmap"))));
//                }
//            }
//        }

        if (PropertyFragment.searchActiv == 1) {
            PropertyFragment.searchActiv = 0;
            HouseAdapter2 adapter = new HouseAdapter2(root.getContext(), HouseModel.searchHouses);
            recycler.setAdapter(adapter);
        } else {
            HouseAdapter adapter = new HouseAdapter(root.getContext(), HouseModel.homeHouses);
            recycler.setAdapter(adapter);
        }
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}