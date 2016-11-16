package net.bigblue.bb.blueapp.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;

import net.bigblue.bb.blueapp.models.objects.User;

/**
 * Created by jason on 11/9/16.
 */

public class SaveSharedPreferences {
    private static final String USER_ID = "USERID";
    private static final String USEROBJ = "USEROBJ";
    private static final String PASSWORD = "PASSWORD";

    static SharedPreferences getSharedPreference(Context ctx){
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserId(Context ctx, String userid){
        SharedPreferences.Editor editor = getSharedPreference(ctx).edit();
        editor.putString(USER_ID,userid);
        editor.commit();
    }

    public static String getUserId(Context ctx){
        return getSharedPreference(ctx).getString(USER_ID,"");
    }

    public static void deleteUserId(Context ctx){
        getSharedPreference(ctx).edit().remove(USER_ID).commit();
    }

    public static void setObject(Context ctx,User user){

        Gson gson = new Gson();
        String json = gson.toJson(user);
        getSharedPreference(ctx).edit().putString(USEROBJ, json).commit();

    }

    public static User getUser(Context ctx){
        Gson gson = new Gson();
        String json = getSharedPreference(ctx).getString(USEROBJ, "");


        return gson.fromJson(json, User.class);
    }

    public static void deleteUser(Context ctx){
        getSharedPreference(ctx).edit().remove(USEROBJ).commit();
    }


}
