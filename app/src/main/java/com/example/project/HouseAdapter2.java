package com.example.project;

import android.content.Context;
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

import com.example.project.ui.Menu.menuFragment;
import com.example.project.ui.home.HomeFragment;
import com.example.project.ui.home.House;
import com.example.project.ui.property.PropertyFragment;

import java.util.List;

public class HouseAdapter2
        extends RecyclerView.Adapter<HouseAdapter2.ViewHolder> {
    private Context context;
    private List<House> items;
    public CardView v;

    public HouseAdapter2(Context context, List<House> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        v = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.housecard3,
                parent,
                false);

        return new ViewHolder(v);
    }

    public TextView city;

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final House house = items.get(position);
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.imageSearch);
        city = cardView.findViewById(R.id.tx2);
        TextView price = cardView.findViewById(R.id.txBd);
        TextView bed = cardView.findViewById(R.id.txPr);
        TextView surface = cardView.findViewById(R.id.txSr);
        Button View = cardView.findViewById(R.id.viewEdit);

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
                Bitmap b=StringToBitMap(house.getBitmap());
                imageView2.setImageBitmap(b);
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


                city1.setText(house.getId() + "");
                area.setText(house.getSurface());
                price1.setText(house.getPrice());
                bed1.setText(house.getNumOfbedrooms());
                status.setText(house.getStatus());
                address.setText(house.getAddress());
                year.setText(house.getYear());
                date.setText(house.getDate());
                Owner.setText(own);

                Bitmap b1 = StringToBitMap(house.getBitmap());
                imageView2.setImageBitmap(b1);

                linearLayout.setVisibility(View.GONE);
                apply.setText("Apply");
                if (menuFragment.history == 2 || AfterConnect.Guest==1) {
                    apply.setVisibility(View.GONE);
                }
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


