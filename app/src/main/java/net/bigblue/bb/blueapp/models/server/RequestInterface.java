package net.bigblue.bb.blueapp.models.server;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class RequestInterface {
    public final static String emtest = "ajax/emtest.php";
    public final static String auth = "bigblue/mobile/mobile-auth.php";


    public interface emtest{

        @POST(emtest)
        Call<ServerResponse> operation(@Body ServerRequest request);
    }


    public interface auth{

        @POST(auth)
        Call<ServerResponse> operation(@Body ServerRequest request);
    }


}





