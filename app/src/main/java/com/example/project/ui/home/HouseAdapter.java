package com.example.project.ui.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.AfterConnect;
import com.example.project.DataBaseHelper;
import com.example.project.HomeActivity;
import com.example.project.R;
import com.example.project.SignIn;
import com.example.project.apply;
import com.example.project.editActivity;
import com.example.project.ui.Menu.menuFragment;
import com.example.project.ui.property.PropertyFragment;

import java.util.List;

public class HouseAdapter
        extends RecyclerView.Adapter<HouseAdapter.ViewHolder> {
    private Context context;
    private List<House> items;
    public static House ts2lesh;
    public CardView v;

    public HouseAdapter(Context context, List<House> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.housecard,
                parent,
                false);

        return new ViewHolder(v);
    }

    public TextView city;

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final House house = items.get(position);
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.image);
        city = cardView.findViewById(R.id.txtName);
        TextView price = cardView.findViewById(R.id.txtBedroom);
        TextView bed = cardView.findViewById(R.id.txtPrice);
        TextView surface = cardView.findViewById(R.id.txtsurface);
        Button View = cardView.findViewById(R.id.viewHouse);

        // TODO __picture of all the project__

        price.setText(house.getPrice());
        city.setText(house.getCity());
        bed.setText(house.getNumOfbedrooms());
        surface.setText(house.getSurface());
        Bitmap b=StringToBitMap(house.getBitmap());
        imageView.setImageBitmap(b);

        String own = house.getOwnerName();
        Animation scale, scale1;
        scale = AnimationUtils.loadAnimation(v.getContext(), R.anim.scale);
        scale1 = AnimationUtils.loadAnimation(v.getContext(), R.anim.scale1);
        cardView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP)
                    cardView.startAnimation(scale1);
                else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    cardView.startAnimation(scale);
                }
                return true;
            }
        });
        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.myDialog.setContentView(R.layout.popup);
                TextView close = (TextView) HomeFragment.myDialog.findViewById(R.id.close);

                close.setText("X");
                Button apply = (Button) HomeFragment.myDialog.findViewById(R.id.apply);
                Button accept = (Button) HomeFragment.myDialog.findViewById(R.id.accept);

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HomeFragment.myDialog.dismiss();
                    }
                });


                HomeFragment.myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                HomeFragment.myDialog.show();
                TextView city1 = (TextView) HomeFragment.myDialog.findViewById(R.id.city);
                TextView area = (TextView) HomeFragment.myDialog.findViewById(R.id.area);
                TextView price1 = (TextView) HomeFragment.myDialog.findViewById(R.id.bedroom);
                TextView bed1 = (TextView) HomeFragment.myDialog.findViewById(R.id.price);
                TextView status = (TextView) HomeFragment.myDialog.findViewById(R.id.status1);
                TextView address = (TextView) HomeFragment.myDialog.findViewById(R.id.address1);
                TextView year = (TextView) HomeFragment.myDialog.findViewById(R.id.year);
                TextView balcony = (TextView) HomeFragment.myDialog.findViewById(R.id.balcony1);
                TextView garden = (TextView) HomeFragment.myDialog.findViewById(R.id.garden1);
                TextView date = (TextView) HomeFragment.myDialog.findViewById(R.id.date1);
                LinearLayout linearLayout = HomeFragment.myDialog.findViewById(R.id.app);
                LinearLayout linearLayout2 = HomeFragment.myDialog.findViewById(R.id.As);
                TextView Owner = (TextView) HomeFragment.myDialog.findViewById(R.id.ownerTxt);
                ImageView imageView2 = HomeFragment.myDialog.findViewById(R.id.image23);
                if (house.isHasgarden()) {
                    garden.setText("true");
                } else {
                    garden.setText("false");
                }
                if (house.isHasbalcone()) {
                    balcony.setText("true");
                } else {
                    balcony.setText("false");
                }


                city1.setText(house.getCity());
                area.setText(house.getSurface());
                price1.setText(house.getPrice());
                bed1.setText(house.getNumOfbedrooms());
                status.setText(house.getStatus());
                address.setText(house.getAddress());
                year.setText(house.getYear());
                date.setText(house.getDate());
                Owner.setText(own);
                Bitmap b1=StringToBitMap(house.getBitmap());
                imageView2.setImageBitmap(b1);

                linearLayout.setVisibility(View.GONE);
                apply.setText("Apply");
                if (SignIn.i == 1 || AfterConnect.Guest == 1) {
                    apply.setVisibility(View.GONE);
                }

                apply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ts2lesh = new House(house.getId(), house.getCity(), house.getAddress(),
                                house.getSurface(), house.getYear(), house.getNumOfbedrooms(),
                                house.getPrice(), house.getStatus(), house.isHasbalcone(), house.isHasgarden(),
                                house.getDate(), house.getDescription(), house.getOwner(), house.getOwnerName(), house.getBitmap());
                        if (AfterConnect.Guest == 1) {
                            Intent intent = new Intent(v.getContext(), AfterConnect.class);
                            v.getContext().startActivity(intent);
                        }
                        Intent intent = new Intent(v.getContext(), com.example.project.apply.class);
                        v.getContext().startActivity(intent);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }

    }

    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }
        catch(Exception e){
            e.getMessage();
            return null;
        }
    }



}


