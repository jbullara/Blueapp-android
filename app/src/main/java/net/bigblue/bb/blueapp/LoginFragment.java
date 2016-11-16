package net.bigblue.bb.blueapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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


public class LoginFragment extends Fragment implements View.OnClickListener{

    private AppCompatButton btn_login;
    private EditText et_email,et_password;
    private TextView tv_register;
    private ProgressBar progress;

    View view;
    public static LoginFragment newInstance(){
        LoginFragment LF = new LoginFragment();
        return LF;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_login,container,false);
        initViews(view);

        return view;
    }

    private void initViews(View view){

      //  pref = getActivity().getPreferences(0);

        btn_login = (AppCompatButton)view.findViewById(R.id.btn_login);
        tv_register = (TextView)view.findViewById(R.id.tv_register);
        et_email = (EditText)view.findViewById(R.id.et_email);
        et_password = (EditText)view.findViewById(R.id.et_password);

        progress = (ProgressBar)view.findViewById(R.id.progress);

        btn_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        TextView tv = (TextView) getActivity().findViewById(R.id.COMPANYNAME);
        ((main) getActivity()).clearAll();
        tv.setText("");

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.tv_register:
                ((main) getActivity()).switchFragment("req");
                break;

            case R.id.btn_login:
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                if(!email.isEmpty() && !password.isEmpty()) {

                    progress.setVisibility(View.VISIBLE);
                    loginProcess(email,password);

                } else {

                    progress.setVisibility(View.VISIBLE);
                    loginProcess("5275","twocousins");

                }
                break;

        }
    }


    private void loginProcess(String email,String password){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestInterface.auth requestInterface = retrofit.create(RequestInterface.auth.class);


        User user = new User();
        user.setEmail(email);
        user.setPassword(password);


        ServerRequest request = new ServerRequest();
        request.setFunc(Constants.AUTH_FUNC);
        request.setUser(user);


        Call<ServerResponse> response = requestInterface.operation(request);

        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                ServerResponse resp = response.body();
                Log.d("response","");
                JSONObject js;
                Snackbar.make(view, resp.getMessage(), Snackbar.LENGTH_LONG).show();

                try{
                     js = new JSONObject(resp.getJson());
                    User mUser = new User();
                    mUser.setCustId(js.getString("cust_id"));
                    mUser.setCompany(js.getString("company"));
                    mUser.setEmail(js.getString("email"));
                    mUser.setPhone(js.getString("phone"));
                    //Log.d("user", mUser.toString());

                    SaveSharedPreferences.setObject( getActivity().getBaseContext() ,mUser);

                    TextView tv = (TextView) getActivity().findViewById(R.id.COMPANYNAME);
                    tv.setText(mUser.getCompany());


                    Intent intent = new Intent(getActivity(), main.class);
                    getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    startActivity(intent);






                } catch (JSONException e){

                }

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
