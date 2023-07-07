package controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Order {
    private String id;
    private String customer_username;
    private String salesman_username;
    private List<OrderDetail> orderDetails;
    private Date orderedTime;
    private int state;

    public Order (String id,  String salesman_username, String customer_username, List<OrderDetail> orderDetails, Date orderedTime, int state){
        this.id = id;
        this.salesman_username = salesman_username;
        this.customer_username = customer_username;
        this.orderDetails = orderDetails;
        this.orderedTime = orderedTime;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public String getCustomer_username() {
        return customer_username;
    }

    public String getSalesman_username() {
        return salesman_username;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public Date getOrderedTime() {
        return orderedTime;
    }

    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }

    public long getTotalPrice(){
        long sum = 0;
        for (OrderDetail i: orderDetails){
            sum+= i.getTotalPrice();
        }
        return sum;
    }

}
