package net.bigblue.bb.blueapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import net.bigblue.bb.blueapp.models.objects.Order;


public class speciesFragment extends Fragment implements View.OnClickListener{
    Order cOrder;
    EditText et ;
    Button sz1, sz2, sz3;

    public static sizeFragment newInstance(){
        sizeFragment fragment = new sizeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.species, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view){
        cOrder = ((main) getActivity()).getOrder();
        et= (EditText)view.findViewById(R.id.qtyeditText) ;
        sz1 = (Button)view.findViewById(R.id.size1btn);
        sz2 = (Button)view.findViewById(R.id.size2btn);
        sz3 = (Button)view.findViewById(R.id.size3btn);

        sz1. setTag("YF");
        sz2. setTag("BE");
        sz3. setTag("Either");

        sz1.setOnClickListener(this);
        sz2.setOnClickListener(this);
        sz3.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        String tag = (String) view.getTag();
        cOrder.species = tag;
        Log.d("Order",cOrder.getJSONObject().toString());
        ((main) getActivity()).switchFragment("size");

    }



}
