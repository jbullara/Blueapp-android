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

public class gradeFragment extends Fragment implements View.OnClickListener{
    Order cOrder;
    EditText et ;
    Button sz1, sz2, sz3,sz4;

    public static gradeFragment newInstance(){
        gradeFragment fragment = new gradeFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.grade, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view){
        cOrder = ((main) getActivity()).getOrder();
        et= (EditText)view.findViewById(R.id.qtyeditText) ;
        sz1 = (Button)view.findViewById(R.id.size1btn);
        sz2 = (Button)view.findViewById(R.id.size2btn);
        sz3 = (Button)view.findViewById(R.id.size3btn);
        sz4 = (Button)view.findViewById(R.id.size4btn);


        sz1.setOnClickListener(this);
        sz2.setOnClickListener(this);
        sz3.setOnClickListener(this);
        sz4.setOnClickListener(this);

        String[] ar;

        switch(cOrder.category){
            case "tuna":
                ar= new String[]{"1+", "1", "2+", "2"};
                break;
            case "sword":
                ar= new String[]{"R+", "R", "R-", "Br"};
                break;
            case "salmon":

                if(cOrder.size.equals("fillet")){
                    ar= new String[]{"2-3", "3-4", "4-5", "5+"};
                } else {
                    ar= new String[]{"10-12", "12-14", "14-16", "16+"};
                }

                break;
            default:
                ar= new String[]{cOrder.category, "", "", ""};
                break;
        }


        sz1. setTag(ar[0]);
        sz1.setText(ar[0]);

        sz2. setTag(ar[1]);
        sz2.setText(ar[1]);

        sz3. setTag(ar[2]);
        sz3.setText(ar[2]);

        sz4. setTag(ar[3]);
        sz4.setText(ar[3]);


    }

    @Override
    public void onClick(View view) {
        String tag = (String) view.getTag();
        cOrder.grade= tag;
        Log.d("Order",cOrder.getJSONObject().toString());
        ((main) getActivity()).switchFragment("qty");

    }



}
