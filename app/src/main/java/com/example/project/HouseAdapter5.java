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

public class HouseAdapter5
        extends RecyclerView.Adapter<HouseAdapter5.ViewHolder> {
    private Context context;
    private List<House> items;
    public static House house2;
    public CardView v;
    private Dialog myDialog;
    private Bitmap b;

    public HouseAdapter5(Context context, List<House> items) {
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
//      TODO    PropertyFragment غيرها ل bitmap B = etc
        b = StringToBitMap(house.getBitmap());
        imageView.setImageBitmap(b);
        price.setText(house.getPrice());
        city.setText(house.getCity());
        bed.setText(house.getNumOfbedrooms());
        surface.setText(house.getSurface());

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
                myDialog.setContentView(R.layout.popup);
                TextView close = (TextView) myDialog.findViewById(R.id.close);

                close.setText("X");
                Button apply = (Button) myDialog.findViewById(R.id.apply);
                Button accept = (Button) myDialog.findViewById(R.id.accept);

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myDialog.dismiss();
                    }
                });


                myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                myDialog.show();
                TextView city1 = (TextView) myDialog.findViewById(R.id.city);
                TextView area = (TextView) myDialog.findViewById(R.id.area);
                TextView price1 = (TextView) myDialog.findViewById(R.id.bedroom);
                TextView bed1 = (TextView) myDialog.findViewById(R.id.price);
                TextView status = (TextView) myDialog.findViewById(R.id.status1);
                TextView address = (TextView) myDialog.findViewById(R.id.address1);
                TextView year = (TextView) myDialog.findViewById(R.id.year);
                TextView balcony = (TextView) myDialog.findViewById(R.id.balcony1);
                TextView garden = (TextView) myDialog.findViewById(R.id.garden1);
                TextView date = (TextView) myDialog.findViewById(R.id.date1);
                LinearLayout linearLayout = myDialog.findViewById(R.id.app);
                LinearLayout linearLayout2 = myDialog.findViewById(R.id.As);
                TextView Owner = (TextView) myDialog.findViewById(R.id.ownerTxt);
                TextView TEN = (TextView) myDialog.findViewById(R.id.textView13);
                TEN.setText("Tenant");
                ImageView imageView2 = myDialog.findViewById(R.id.image23);

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
                imageView2.setImageBitmap(b);

                house2 = new House(house.getId(), house.getCity(), house.getAddress(), house.getSurface(), house.getYear()
                        , house.getNumOfbedrooms(), house.getPrice(), house.getStatus(), house.isHasbalcone(),
                        house.isHasgarden(), house.getDate(), house.getDescription(), house.getOwner(), house.getOwnerName(), house.getBitmap());

                apply.setText("Reject");
                apply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        DataBaseHelper dataBaseHelper = new DataBaseHelper(v.getContext());
                        Cursor cursor1 = dataBaseHelper.getRequests();
                        if (cursor1 != null) {
                            cursor1.moveToFirst();
                            for (int i = 0; i < cursor1.getCount(); i++) {
                                if (cursor1.getInt(cursor1.getColumnIndex("houseID")) == house.getId()) {
                                    dataBaseHelper.updater(house.getId(), cursor1.getString(cursor1.getColumnIndex("email")), "false");
                                    Intent intent = new Intent(v.getContext(), com.example.project.HomeActivity.class);
                                    v.getContext().startActivity(intent);
                                }
                                cursor1.moveToNext();
                            }
                        }
//            }


                    }

                });
                accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DataBaseHelper dataBaseHelper = new DataBaseHelper(v.getContext());
                        Cursor cursor1 = dataBaseHelper.getRequests();
                        if (cursor1 != null) {
                            cursor1.moveToFirst();
                            for (int i = 0; i < cursor1.getCount(); i++) {
                                if (cursor1.getInt(cursor1.getColumnIndex("houseID")) == house.getId()) {
                                    dataBaseHelper.updater(house.getId(), cursor1.getString(cursor1.getColumnIndex("email")), "true");

                                    Intent intent = new Intent(v.getContext(), com.example.project.HomeActivity.class);
                                    v.getContext().startActivity(intent);
                                }
                                cursor1.moveToNext();
                            }
                        }

                    }
                });

                Button tenant = myDialog.findViewById(R.id.tenantView);
                tenant.setVisibility(android.view.View.VISIBLE);
                tenant.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), profileHistory.class);
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


