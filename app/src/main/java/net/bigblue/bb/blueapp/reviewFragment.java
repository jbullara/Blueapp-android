package net.bigblue.bb.blueapp;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import net.bigblue.bb.blueapp.models.objects.Order;
import net.bigblue.bb.blueapp.adapters.OrderArrayAdapter;
import net.bigblue.bb.blueapp.models.server.RequestInterface;
import net.bigblue.bb.blueapp.models.server.ServerRequest;
import net.bigblue.bb.blueapp.models.server.ServerResponse;

import com.fenjuly.mylibrary.FloorListView;

import org.json.JSONArray;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class reviewFragment extends Fragment implements View.OnClickListener{
    Order cOrder;
    ArrayList<Order> orderList;

    Button sz1,sz2;
   JSONArray jsArray = new JSONArray();
    View view;

    public static reviewFragment newInstance(){
        reviewFragment fragment = new reviewFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.review, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view){

        orderList = ((main) getActivity()).getOrderList();

        sz1 = (Button)view.findViewById(R.id.continueBtn);
        sz2 = (Button)view.findViewById(R.id.addBtn);

        sz2.setTag("add");
        sz1.setTag("send");

        sz1.setOnClickListener(this);
        sz2.setOnClickListener(this);

        if(orderList.isEmpty()){

              ((main) getActivity()).switchFragment("cat");

        } else {
            OrderArrayAdapter adapter = new OrderArrayAdapter(getActivity(), orderList);

            FloorListView listView = (FloorListView) view.findViewById(R.id.listView);

            listView.setAdapter(adapter);
        }
    }

    @Override
    public void onClick(View view) {

        String tag = (String) view.getTag();
        switch(tag){
            case "add":
                TabLayout.Tab tb = ((main) getActivity()).tl.getTabAt(0);
                tb.select();


                ((main) getActivity()).switchFragment("cat");
                break;
            case "send":
                sendOrder();
                break;
        }
    }

private void leave(){

}
    private void sendOrder(){
       /* for(int i=0; i<orderList.size();i++){
            jsArray.put(orderList.get(i).getJSONObject());
            Log.d("T",orderList.get(i).getJSONObject().toString());
        }*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface.auth requestInterface = retrofit.create(RequestInterface.auth.class);

        ServerRequest request = new ServerRequest();
        //request.setOperation(Constants.REGISTER_OPERATION);

        request.setOrderList(orderList);
        request.setUser(((main) getActivity()).getUser());
        request.setFunc(Constants.ORDER_FUNC);
        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                Snackbar.make(view, resp.getMessage()+resp.getResult(), Snackbar.LENGTH_LONG).show();
               // progress.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

               // progress.setVisibility(View.INVISIBLE);
                Log.d(Constants.TAG,"failed");
                Snackbar.make(view, t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();


            }
        });
    }

}
