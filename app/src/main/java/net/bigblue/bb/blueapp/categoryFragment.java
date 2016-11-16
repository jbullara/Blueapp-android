package net.bigblue.bb.blueapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import net.bigblue.bb.blueapp.models.objects.Order;


public class categoryFragment extends Fragment implements View.OnClickListener{
    Order cOrder;

    Button tunaBtn, swordBtn, mahiBtn, wahooBtn, salmonBtn, otherBtn;

    public static categoryFragment newInstance(){
        categoryFragment CF = new categoryFragment();
        return CF;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view){
        cOrder = ((main) getActivity()).getOrder();

        tunaBtn = (Button)view.findViewById(R.id.tunabtn);
        swordBtn = (Button)view.findViewById(R.id.swordbtn);
        mahiBtn = (Button)view.findViewById(R.id.mahibtn);
        wahooBtn = (Button)view.findViewById(R.id.wahoobtn);
        salmonBtn = (Button)view.findViewById(R.id.salmonbtn);
        otherBtn = (Button)view.findViewById(R.id.otherbtn);

        tunaBtn.setTag("tuna");
        swordBtn.setTag("sword");
        mahiBtn.setTag("mahi");
        wahooBtn.setTag("wahoo");
        salmonBtn.setTag("salmon");
        otherBtn.setTag("other");

        tunaBtn.setOnClickListener(this);
        swordBtn.setOnClickListener(this);
        mahiBtn.setOnClickListener(this);
        wahooBtn.setOnClickListener(this);
        salmonBtn.setOnClickListener(this);
        otherBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        String tag = (String) view.getTag();

        Log.d("before",cOrder.category);
        cOrder.category=tag;
        Log.d("after",cOrder.category);
        Log.d("after2",((main) getActivity()).getOrder().category);

        switch(tag){
            case "mahi":
            case "wahoo":
            case "other":
                ((main) getActivity()).switchFragment("qty");
                break;
            case "tuna":
                ((main) getActivity()).switchFragment("spc");
                break;
            default:
                ((main) getActivity()).switchFragment("size");
                break;
        }

    }



}
