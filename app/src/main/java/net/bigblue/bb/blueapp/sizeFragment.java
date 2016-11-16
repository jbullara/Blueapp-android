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


public class sizeFragment extends Fragment implements View.OnClickListener{
    Order cOrder;
    EditText et ;
    Button sz1, sz2, sz3,sz4;

    public static sizeFragment newInstance(){
        sizeFragment fragment = new sizeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cat_size, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view){
        cOrder = ((main) getActivity()).getOrder();
        et= (EditText)view.findViewById(R.id.qtyeditText) ;
        sz1 = (Button)view.findViewById(R.id.size1btn);
        sz2 = (Button)view.findViewById(R.id.size2btn);
      //  sz3 = (Button)view.findViewById(R.id.size3btn);
      //  sz4 = (Button)view.findViewById(R.id.size4btn);


        sz1.setOnClickListener(this);
        sz2.setOnClickListener(this);
      //  sz3.setOnClickListener(this);
      //  sz4.setOnClickListener(this);

        String[] ar;

        switch(cOrder.category){
            case "tuna":
                ar= new String[]{"60u", "60 up", "4060", "40-60"};
                break;
            case "sword":
                ar= new String[]{"pup", "Pup", "marker", "Marker"};
                break;
            case "salmon":
                ar= new String[]{"whole", "Whole", "fillet", "Fillet"};
                break;
            default:
                ar= new String[]{"", "", "", ""};
                break;
        }

        sz1. setTag(ar[0]);
        sz1.setText(ar[1]);
        sz2. setTag(ar[2]);
        sz2.setText(ar[3]);


    }

    @Override
    public void onClick(View view) {
        String tag = (String) view.getTag();
        cOrder.size = tag;
        Log.d("Order",cOrder.getJSONObject().toString());
        ((main) getActivity()).switchFragment("grade");

    }



}
