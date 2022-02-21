package com.example.project.ui.property;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.AfterConnect;
import com.example.project.DataBaseHelper;
import com.example.project.HomeActivity;
import com.example.project.R;
import com.example.project.SignIn;
import com.example.project.databinding.FragmentSearchBinding;
import com.example.project.databinding.FragmentPostBinding;
import com.example.project.ui.Menu.menuFragment;
import com.example.project.ui.home.House;
import com.example.project.ui.home.HouseModel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PropertyFragment extends Fragment {

    public static int searchActiv = 0;
    private FragmentSearchBinding binding;
    private FragmentPostBinding binding1;

    public static int minSurface1 = 0;
    public static int maxSurface1 = 0;
    public static int minBedrooms1 = 0;
    public static int maxBedrooms1 = 0;
    public static int minPrice1 = 0;

    int surface = 0;
    int bedrooms = 0;
    int price = 0;
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
    public EditText postDateStart;
    public ImageButton imageBtn1;


    RecyclerView recyclerView;
    DataBaseHelper databaseHelper;
    public static final int PICK_IMAGE = 1;
    public static Uri uri;
    public static Bitmap bitmap;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSearchBinding.inflate(inflater, container, false);
        binding1 = FragmentPostBinding.inflate(inflater, container, false);
        View root;
        menuFragment.history = 0;
        if (SignIn.i == 1 && AfterConnect.Guest != 1) {

            root = binding1.getRoot();
            getActivity().setTitle("Post a Property");

            DataBaseHelper dataBaseHelper = new DataBaseHelper(root.getContext());
            EditText address = (EditText) root.findViewById(R.id.address);
            EditText postArea = (EditText) root.findViewById(R.id.postArea);
            EditText postYear = (EditText) root.findViewById(R.id.postYear);
            EditText bedrooms = (EditText) root.findViewById(R.id.postBedroom);
            EditText postRentalPrice = (EditText) root.findViewById(R.id.postRentalPrice);
            EditText descriptionText = (EditText) root.findViewById(R.id.descriptionText);
            postDateStart = (EditText) root.findViewById(R.id.postDateStart);
            EditText postDateEnd = (EditText) root.findViewById(R.id.postDateEnd);

            imageBtn1 = (ImageButton) root.findViewById(R.id.imageBtn1);
            CheckBox hasBalcony = (CheckBox) root.findViewById(R.id.postBalcony);
            CheckBox hasGarden = (CheckBox) root.findViewById(R.id.postGarden);
            Spinner citySpinner = (Spinner) root.findViewById(R.id.spinnerCity);
            String[] cityList = {"Paris", "Monaco", "Ramallah", "Jerusalem", "Berlin", "München", "Seville", "Madrid", "Amsterdam", "Rotterdam"};
            ArrayAdapter<String> Arr4 = new
                    ArrayAdapter<>(root.getContext(), android.R.layout.simple_spinner_item, cityList);
            citySpinner.setAdapter(Arr4);

            String[] FurList = {"furnished", "unfurnished"};
            Spinner statusSpinner = (Spinner) root.findViewById(R.id.postStatusSpinner);
            ArrayAdapter<String> Arr5 = new
                    ArrayAdapter<>(root.getContext(), android.R.layout.simple_spinner_item, FurList);
            statusSpinner.setAdapter(Arr5);

            imageBtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                }
            });

            if (address.getText().toString() != "") {
                Signed1 = 0;
            } else {
                Signed1 = 1;
                address.setText("Error");
            }
            if (postArea.getText().toString() != "") {
                Signed2 = 0;
            } else {
                postArea.setText("Error");
                Signed2 = 1;
            }
            if (postYear.getText().toString() != "") {
                Signed3 = 0;
            } else {
                postYear.setText("Error");
                Signed3 = 1;
            }
            if (bedrooms.getText().toString() != "") {
                Signed4 = 0;
            } else {
                Signed4 = 1;
                bedrooms.setText("Error");
            }
            if (postRentalPrice.getText().toString() != "") {
                Signed6 = 0;
                ////////////////////////////////////
            } else {
                Signed6 = 1;
                postRentalPrice.setText("Error");
            }
            if (descriptionText.getText().toString() != "") {
                Signed7 = 0;
            } else {
                Signed7 = 1;
                descriptionText.setText("Error");
            }
            if (postDateStart.getText().toString() != "") {
                Signed8 = 0;
            } else {
                Signed8 = 1;
                postDateStart.setText("Error");
            }
            if (postDateEnd.getText().toString() != "") {
                Signed9 = 0;
            } else {
                Signed9 = 1;
                postDateEnd.setText("Error");
            }

            Button post = (Button) root.findViewById(R.id.addProp);
            post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String balcony = "0";
                    String garden = "0";
                    if (hasBalcony.isChecked()) {
                        balcony = "1";
                    }

                    if (hasGarden.isChecked()) {
                        garden = "1";
                    }
                    if (Signed1 == 0 && Signed2 == 0 && Signed3 == 0 && Signed4 == 0 && Signed5 == 0 && Signed6 == 0
                            && Signed7 == 0 && Signed8 == 0 && Signed9 == 0 && Signed10 == 0) {
                        HouseModel.i++;
                        House newHouse = new House(HouseModel.i, citySpinner.getSelectedItem().toString(), address.getText().toString(),
                                postArea.getText().toString(), postYear.getText().toString(), bedrooms.getText().toString()
                                , postRentalPrice.getText().toString(), statusSpinner.getSelectedItem().toString(),
                                hasBalcony.isChecked(), hasGarden.isChecked(), postDateEnd.getText().toString(), descriptionText.getText().toString(), SignIn.rental.getRentalId(), SignIn.rental.getRentalName(),
                                BitMapToString(bitmap));

                        boolean l = dataBaseHelper.insertHouse(newHouse);
                        if (l) {
                            Toast.makeText(root.getContext(), "failed", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(root.getContext(), "Added successfully", Toast.LENGTH_SHORT).show();
                        }
                        Cursor s = dataBaseHelper.getAllHouses();
                        s.moveToLast();
//                        newHouse.setId(s.getInt(s.getColumnIndex("ID")));
                        newHouse.setId(s.getCount());
                        boolean l1 = dataBaseHelper.insertHousePrp(newHouse);


                        Intent intent = new
                                Intent(root.getContext(), HomeActivity.class);
                        root.getContext().startActivity(intent);
                    }
                }
            });
        } else {

            root = binding.getRoot();
            getActivity().setTitle("Search for a Property");
            Spinner spinner = root.findViewById(R.id.spinnerCity);
            EditText minSurface = root.findViewById(R.id.minSurface);
            EditText maxSurface = root.findViewById(R.id.maxSurface);
            EditText minBedrooms = root.findViewById(R.id.minSurface);
            EditText maxBedrooms = root.findViewById(R.id.maxSurface);
            EditText minPrice = root.findViewById(R.id.minRentalPrice);
            Spinner spinnerStatus = root.findViewById(R.id.statusSpinner);
            CheckBox garden1 = root.findViewById(R.id.garden);
            CheckBox balcony1 = root.findViewById(R.id.balcony);
            Button search = root.findViewById(R.id.searchProp);

            String[] cityList = {"Paris", "Monaco", "Ramallah", "Jerusalem", "Berlin", "München", "Seville", "Madrid", "Amsterdam", "Rotterdam"};
            ArrayAdapter<String> Arr4 = new
                    ArrayAdapter<>(root.getContext(), android.R.layout.simple_spinner_item, cityList);
            spinner.setAdapter(Arr4);
            String[] FurList = {"furnished", "unfurnished"};
            ArrayAdapter<String> Arr5 = new
                    ArrayAdapter<>(root.getContext(), android.R.layout.simple_spinner_item, FurList);
            spinnerStatus.setAdapter(Arr5);

            RecyclerView recycler = root.findViewById(R.id.house_recycler2);

            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    HouseModel.searchHouses.clear();
                    String minS = minSurface.getText().toString().trim();
                    String maxS = maxSurface.getText().toString().trim();

                    String minB = minBedrooms.getText().toString().trim();
                    String maxB = maxBedrooms.getText().toString().trim();

                    String minP = minPrice.getText().toString().trim();


                    HouseModel.allHouses.clear();
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(root.getContext());
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
                                searchActiv = 1;
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
                            Intent(root.getContext(), HomeActivity.class);
                    root.getContext().startActivity(intent);
                }
            });
        }
        return root;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE) {
            //TODO: action
            uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String BitMapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}