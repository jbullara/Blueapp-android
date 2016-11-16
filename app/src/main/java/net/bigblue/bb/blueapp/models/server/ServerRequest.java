package net.bigblue.bb.blueapp.models.server;


import net.bigblue.bb.blueapp.models.objects.Order;
import net.bigblue.bb.blueapp.models.objects.User;

import java.util.ArrayList;

public class ServerRequest {

    private String func;
    private User user;
    private ArrayList<Order> orderList;

    public void setFunc(String func) {
        this.func = func;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOrderList(ArrayList<Order> Order) {
        this.orderList = Order;
    }
}
