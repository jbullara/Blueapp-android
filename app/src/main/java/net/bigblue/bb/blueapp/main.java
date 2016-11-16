package net.bigblue.bb.blueapp;

import android.app.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import net.bigblue.bb.blueapp.adapters.SaveSharedPreferences;
import net.bigblue.bb.blueapp.models.objects.Order;
import net.bigblue.bb.blueapp.models.objects.User;

import java.util.ArrayList;


public class main extends AppCompatActivity {
    public Order cOrder;
    public ArrayList<Order> orderList= new ArrayList<>();
    public User mUser;
    TabLayout tl;


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();

            if(getFragmentManager().getBackStackEntryCount()==1){
                newcOrder();
                initFragment();
            }
        } else {
            super.onBackPressed();
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.main);


       // SaveSharedPreferences.deleteUser(getBaseContext());

        mUser = SaveSharedPreferences.getUser(getBaseContext());

        if( mUser == null){

            switchFragment("login");

        } else {

            TextView tv = (TextView) findViewById(R.id.COMPANYNAME);
            tv.setText(mUser.getCompany());
            VP();
            initFragment();
        }


    }

    private void initFragment(){
        Fragment fragment;

        fragment = new categoryFragment();
        android.support.v4.app.
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrag,fragment);
        ft.commit();


    }

    public Order getOrder(){
        if(this.cOrder==null){
            this.cOrder = new Order("","","","","","");
        }
        return this.cOrder;
    }

    public User getUser(){
        return this.mUser;
    }

    public ArrayList<Order> getOrderList(){
        return this.orderList;
    }

    public void newOrderList(){
       this.orderList = new ArrayList<>();
    }

    public void newcOrder(){
        this.cOrder = new Order("","","","","","");
    }

    public void switchFragment(String s){
       Fragment fragment;
        Integer nobacksies = 0;
        TabLayout.Tab tb;
        switch(s){
            case "cat":
                nobacksies=1;


                fragment = new categoryFragment();
                break;
            case "qty":
                fragment = new quantityFragment();
                break;
            case "spc":
                fragment = new speciesFragment();
                break;
            case "size":
                fragment = new sizeFragment();
                break;
            case "grade":
                fragment = new gradeFragment();
                break;
            case "price":
                fragment = new priceFragment();
                break;
            case "review":
                nobacksies=1;
                fragment = new reviewFragment();
                break;
            case "login":

                fragment = new LoginFragment();
                break;

            case "req":

                fragment = new registerFragment();
                break;
            default:
                newcOrder();
                fragment = new categoryFragment();
                break;

        }



        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrag,fragment);
        ft.addToBackStack("my_fragment");
        if( nobacksies==1){ getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE); }
        ft.commit();
    }

    public void clearAll(){
        SaveSharedPreferences.deleteUser(getBaseContext());
        newOrderList();
        newcOrder();
        mUser= new User();
    }
    public void VP(){
        ViewPager vp = (ViewPager)findViewById(R.id.VPager);
        tl = (TabLayout) findViewById(R.id.TLayout) ;
        tl.setupWithViewPager(vp);


        FragmentStatePagerAdapter fspa = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new categoryFragment();
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
        vp.setAdapter(fspa);

        tl.getTabAt(0).setIcon(R.drawable.ic_person);
        tl.getTabAt(0).setText("Order");
        tl.getTabAt(1).setIcon(R.drawable.ic_key);
        tl.getTabAt(1).setText("Invoices");
        tl.getTabAt(2).setIcon(R.drawable.ic_key);
        tl.getTabAt(2).setText("Orders");
        tl.getTabAt(3).setIcon(R.drawable.ic_email);
        tl.getTabAt(3).setText("Logout");

        tl.setVisibility(TabLayout.VISIBLE);

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

                switch(position){


                    case 0:
                        switchFragment("cat");
                        break;
                    case 1:
                        switchFragment("req");
                        break;
                    case 2:
                        if(orderList.isEmpty()){
                            switchFragment("req");
                        }else{
                            switchFragment("review");
                        }





                        break;

                    case 3:
                        switchFragment("login");
                        tl.setVisibility(TabLayout.INVISIBLE);
                        break;
                    default:
                       switchFragment("cat");

                        break;
                }




            }

            @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
            @Override public void onPageScrollStateChanged(int state) {}
        });

    }
}
