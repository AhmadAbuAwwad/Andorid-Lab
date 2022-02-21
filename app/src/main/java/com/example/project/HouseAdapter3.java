package com.example.project;

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

import com.example.project.DataBaseHelper;
import com.example.project.HomeActivity;
import com.example.project.R;
import com.example.project.SignIn;
import com.example.project.editActivity;
import com.example.project.ui.Menu.menuFragment;
import com.example.project.ui.home.HomeFragment;
import com.example.project.ui.home.House;
import com.example.project.ui.property.PropertyFragment;

import java.util.List;

public class HouseAdapter3
        extends RecyclerView.Adapter<HouseAdapter3.ViewHolder> {
    private Context context;
    private List<House> items;
    public CardView v;

    public HouseAdapter3(Context context, List<House> items) {
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
    private Bitmap b;
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final House house = items.get(position);
        CardView cardView = holder.cardView;
        ImageView imageView = (ImageView) cardView.findViewById(R.id.imageSearch);
        city = cardView.findViewById(R.id.tx2);
        TextView price = cardView.findViewById(R.id.txPr);
        TextView bed = cardView.findViewById(R.id.txBd);
        TextView surface = cardView.findViewById(R.id.txSr);
        Button View = cardView.findViewById(R.id.viewEdit);

        // TODO __picture of all the project__

        price.setText(house.getPrice());
        b = StringToBitMap(house.getBitmap());
        imageView.setImageBitmap(b);
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
                editActivity.historyDialog.setContentView(R.layout.tent);
                TextView close2 = (TextView) editActivity.historyDialog.findViewById(R.id.close2);

                Button edit = (Button) editActivity.historyDialog.findViewById(R.id.apply11);
                Button delete = (Button) editActivity.historyDialog.findViewById(R.id.accept11);


                close2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editActivity.historyDialog.dismiss();
                    }
                });
                editActivity.historyDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                editActivity.historyDialog.show();
                EditText city11 = editActivity.historyDialog.findViewById(R.id.city11);
                EditText area11 = editActivity.historyDialog.findViewById(R.id.area11);
                EditText price11 = editActivity.historyDialog.findViewById(R.id.bedroom11);
                EditText bed11 = editActivity.historyDialog.findViewById(R.id.price11);
                EditText status11 = editActivity.historyDialog.findViewById(R.id.status11);
                EditText address11 = editActivity.historyDialog.findViewById(R.id.address11);
                EditText year11 = editActivity.historyDialog.findViewById(R.id.year11);
                EditText balcony11 = editActivity.historyDialog.findViewById(R.id.balcony11);
                EditText garden11 = editActivity.historyDialog.findViewById(R.id.garden11);
                EditText date11 = editActivity.historyDialog.findViewById(R.id.date11);
                TextView Owner = (TextView) editActivity.historyDialog.findViewById(R.id.ownerName1);
                LinearLayout linearLayout11 = editActivity.historyDialog.findViewById(R.id.app11);
                if (house.isHasgarden()) {
                    garden11.setText("true");
                } else {
                    garden11.setText("false");
                }


                if (house.isHasbalcone()) {
                    balcony11.setText("true");
                } else {
                    balcony11.setText("false");
                }

                city11.setText(house.getCity());
                area11.setText(house.getSurface());
                price11.setText(house.getPrice());
                bed11.setText(house.getNumOfbedrooms());
                status11.setText(house.getStatus());
                address11.setText(house.getAddress());
                year11.setText(house.getYear());
                date11.setText(house.getDate());
                ImageView imageView2 = editActivity.historyDialog.findViewById(R.id.image11);
                Bitmap b1 = StringToBitMap(house.getBitmap());
                imageView2.setImageBitmap(b1);
                Owner.setText(own);
                edit.setText("Edit");
                delete.setText("Delete");
                if (balcony11.getText().equals("true")) {
                    house.setHasbalcone(true);
                } else
                    house.setHasbalcone(false);

                if (garden11.getText().equals("true")) {
                    house.setHasgarden(true);
                } else
                    house.setHasgarden(false);
//TODO                      e3mal house ID s7 jowa al aDapter
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DataBaseHelper dataBaseHelper = new DataBaseHelper(v.getContext());
                        dataBaseHelper.editHouse(new House(house.getId(), city11.getText().toString(), address11.getText().toString(),
                                area11.getText().toString(), year11.getText().toString(), bed11.getText().toString(),
                                price11.getText().toString(), status11.getText().toString(), house.isHasbalcone(), house.isHasgarden(), date11.getText().toString(),
                                house.getDescription(), house.getOwner(), house.getOwnerName(), house.getBitmap()));

                        bed.setText(house.getSurface());
                        surface.setText(house.getNumOfbedrooms());
                        Intent intent = new Intent(v.getContext(), HomeActivity.class);
                        v.getContext().startActivity(intent);

                    }
                });
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DataBaseHelper dataBaseHelper = new DataBaseHelper(v.getContext());
                        dataBaseHelper.deleteHouse(house.getId());
                        Intent intent = new Intent(v.getContext(), HomeActivity.class);
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


