package controller;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private String id;
    private Customer customer;
    private List<OrderDetail> orderDetails;
    private LocalDateTime orderedTime;
    private int state;

    Order (String id, Customer customer, List<OrderDetail> orderDetails, LocalDateTime orderedTime, int state){
        this.id = id;
        this.customer = customer;
        this.orderDetails = orderDetails;
        this.orderedTime = orderedTime;
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
