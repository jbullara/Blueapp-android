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


public class quantityFragment extends Fragment implements View.OnClickListener{
    Order cOrder;
    EditText et ;
    Button sz1;

    public static quantityFragment newInstance(){
        quantityFragment fragment = new quantityFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quantity, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view){
        cOrder = ((main) getActivity()).getOrder();
        et= (EditText)view.findViewById(R.id.qtyeditText) ;
        sz1 = (Button)view.findViewById(R.id.continueBtn);

        sz1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        cOrder.price = et.getText().toString();
        Log.d("Order",cOrder.getJSONObject().toString());
        ((main) getActivity()).switchFragment("price");

    }



}
