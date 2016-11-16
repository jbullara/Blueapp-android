package net.bigblue.bb.blueapp.adapters;


import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.bigblue.bb.blueapp.R;
import net.bigblue.bb.blueapp.categoryFragment;
import net.bigblue.bb.blueapp.main;
import net.bigblue.bb.blueapp.models.objects.Order;

import java.util.ArrayList;

/**
 * Created by jasonbullara on 10/27/16.
 */

public class OrderArrayAdapter extends android.widget.ArrayAdapter<Order> {

    private Context context;
    private ArrayList<Order> obj;

    public OrderArrayAdapter(Context context, ArrayList<Order> objects) {
        super(context, 0, objects);
        this.context = context;
        this.obj = objects;


    }

    public View getView(final int position, View customView, ViewGroup parent) {
        Order object = getItem(position);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (customView == null) {
            customView = layoutInflater.inflate(R.layout.listview, parent, false);
        }

            TextView category = (TextView) customView.findViewById(R.id.tvCat);
            TextView species = (TextView) customView.findViewById(R.id.tvSpec);
            TextView size = (TextView) customView.findViewById(R.id.tvSize);
            TextView grade = (TextView) customView.findViewById(R.id.tvGrade);
            TextView qty = (TextView) customView.findViewById(R.id.tvQty);
            TextView price = (TextView) customView.findViewById(R.id.tvPrice);
            ImageView iv = (ImageView) customView.findViewById(R.id.removeBtn);
            LinearLayout ll = (LinearLayout) customView.findViewById(R.id.listView);
            category.setText(object.category);

            species.setText(object.species);
            size.setText(object.size);
            grade.setText(object.grade);
            qty.setText(object.quantity + " LBs");
            price.setText("$" + object.price);


            View.OnClickListener myClickListener = new View.OnClickListener() {
                public void onClick(View v) {

                    Log.d("position", Integer.toString(position));
                    Log.d("Arraylist Size", Integer.toString(obj.size()));


                    if (obj.size() != 1) {
                        Order order = getItem(position);
                        remove(order);
                    } else {

                        ((main) context).newOrderList();
                        android.support.v4.app.Fragment fragment;

                        fragment = new categoryFragment();
                        android.support.v4.app.
                        FragmentTransaction ft =  ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.mainFrag,fragment);
                        ft.commit();
                    }

                }
            };


            iv.setOnClickListener(myClickListener);

        return customView;
    }

}