package controller;

public class Login {
    public Login(){

    }
    public static Customer loginCustomer(String userName, String password){
        // get i4 on database
        System.out.println(userName +"  "+ password);
        Customer customer = new Customer();
         return customer;
    }

    public static Salesman loginSalesman(String userName, String password){
        // get i4 on database
        Salesman salesman = new Salesman(userName, password);
        return salesman;
    }

}
