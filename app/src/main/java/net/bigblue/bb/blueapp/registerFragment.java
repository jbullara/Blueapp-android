package net.bigblue.bb.blueapp;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.bigblue.bb.blueapp.adapters.SaveSharedPreferences;
import net.bigblue.bb.blueapp.models.objects.User;
import net.bigblue.bb.blueapp.models.server.RequestInterface;
import net.bigblue.bb.blueapp.models.server.ServerRequest;
import net.bigblue.bb.blueapp.models.server.ServerResponse;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class registerFragment extends Fragment implements View.OnClickListener{

    private AppCompatButton btn_register;
    private EditText et_name, et_company, et_email,et_phone;

    private ProgressBar progress;
    private SharedPreferences pref;
    View view;

    public static registerFragment newInstance(){
       registerFragment fragment = new registerFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_register,container,false);
        initViews(view);

        return view;
    }

    private void initViews(View view){

        pref = getActivity().getPreferences(0);

        btn_register = (AppCompatButton)view.findViewById(R.id.btn_request);

        et_email = (EditText)view.findViewById(R.id.et_email);
        et_name = (EditText)view.findViewById(R.id.et_name);

        et_company = (EditText)view.findViewById(R.id.et_company);
        et_phone = (EditText)view.findViewById(R.id.et_phone);

        progress = (ProgressBar)view.findViewById(R.id.progress);

        btn_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        //((main) getActivity()).switchFragment("cat");

        switch (v.getId()){



            case R.id.btn_request:
                String email = et_email.getText().toString();
                String name = et_name.getText().toString();
                String company = et_company.getText().toString();
                String phone = et_phone.getText().toString();


                User user = new User();
                user.setEmail(email);
                user.setCompany(company);
                user.setName(name);
                user.setPhone(phone);


                if(!company.isEmpty() && ( !email.isEmpty() || !phone.isEmpty() )) {

                    //progress.setVisibility(View.VISIBLE);
                    requestAccess(user);
                   // Snackbar.make(view, "Request has been sent", Snackbar.LENGTH_LONG).show();
                } else {

                   // progress.setVisibility(View.VISIBLE);


                   Snackbar.make(view, "Fields are empty !", Snackbar.LENGTH_LONG).show();
                }
                break;

        }
    }



    private void requestAccess(User user){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface.auth requestInterface = retrofit.create(RequestInterface.auth.class);




        ServerRequest request = new ServerRequest();
        request.setFunc(Constants.REQUEST_FUNC);
        request.setUser(user);


        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                Log.d("response","");

                Snackbar.make(view, resp.getMessage(), Snackbar.LENGTH_LONG).show();
                ((main) getActivity()).switchFragment("login");
                progress.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                progress.setVisibility(View.INVISIBLE);
                Log.d(Constants.TAG,"failed");
                Snackbar.make(view, t.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();

            }
        });
    }


}
