package net.bigblue.bb.blueapp;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by jason on 11/8/16.
 */

public class firebaseMessage extends FirebaseInstanceIdService {

   @Override
    public void onTokenRefresh(){
       String refreshedToken = FirebaseInstanceId.getInstance().getToken();



   }


}
