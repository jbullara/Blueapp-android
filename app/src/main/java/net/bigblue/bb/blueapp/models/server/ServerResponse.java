package net.bigblue.bb.blueapp.models.server;


import net.bigblue.bb.blueapp.models.objects.User;

public class ServerResponse {

    private String result;
    private String message;
    private String json;
    private User user;


    public String getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public String getJson() {
        return json;
    }


    public User getUser() {
        return user;
    }
}
