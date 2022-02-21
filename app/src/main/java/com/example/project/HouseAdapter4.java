package com.example.project;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.ui.History.HistoryFragment;
import com.example.project.ui.home.HomeFragment;
import com.example.project.ui.home.House;
import com.example.project.ui.home.HouseModel;
import com.example.project.ui.property.PropertyFragment;

import java.util.List;

public class HouseAdapter4
        extends RecyclerView.Adapter<HouseAdapter4.ViewHolder> {
    private Context context;
    private List<House> items;
    public CardView v;
    private Dialog myDialog;
    public static House house1;

    public HouseAdapter4(Context context, List<House> items) {
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
        HistoryFragment.history = 0;
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
        Bitmap b = StringToBitMap(house.getBitmap());
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

                myDialog = new Dialog(v.getContext());
                myDialog.setContentView(R.layout.history_popup);
                TextView close = (TextView) myDialog.findViewById(R.id.clos);
                TextView date = (TextView) myDialog.findViewById(R.id.StartDate0);
                TextView date1 = (TextView) myDialog.findViewById(R.id.endDate0);

                close.setText("X");
                Button accept = (Button) myDialog.findViewById(R.id.accep);

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });


                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                myDialog.show();
                TextView city1 = (TextView) myDialog.findViewById(R.id.cit);
                TextView area = (TextView) myDialog.findViewById(R.id.are);
                TextView price1 = (TextView) myDialog.findViewById(R.id.bedroo);
                TextView bed1 = (TextView) myDialog.findViewById(R.id.pric);
                TextView status = (TextView) myDialog.findViewById(R.id.statu);
                TextView address = (TextView) myDialog.findViewById(R.id.addres);
                TextView year = (TextView) myDialog.findViewById(R.id.yea);
                TextView balcony = (TextView) myDialog.findViewById(R.id.balcon);
                TextView garden = (TextView) myDialog.findViewById(R.id.garde);

                LinearLayout linearLayout2 = myDialog.findViewById(R.id.A);
                TextView Owner = (TextView) myDialog.findViewById(R.id.ownerTx);
                ImageView imageView2 = myDialog.findViewById(R.id.imag);

                Button view = myDialog.findViewById(R.id.accep);
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
                date1.setText(house.getDescription());
                Bitmap b1 = StringToBitMap(house.getBitmap());
                imageView2.setImageBitmap(b1);

                house1 = new House(house.getId(), house.getCity(), house.getAddress(), house.getSurface(), house.getYear().trim()
                        , house.getNumOfbedrooms(), house.getPrice(), house.getStatus(), house.isHasbalcone(),
                        house.isHasgarden(), house.getDate(), house.getDescription(), house.getOwner(), house.getOwnerName(), house.getBitmap());

                Owner.setText(own);
                linearLayout2.setVisibility(View.GONE);

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


