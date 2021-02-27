/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingonline;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class Customer extends Person {

    private int loyalPoint;

    public Customer(int id, String name, String email, String address, String phone, Date dateOfBirth) {
        super(id, name, email, address, phone, dateOfBirth);
    }

    public Customer( int id, String name, String email, String address, String phone, Date dateOfBirth,int loyalPoint) {
        super(id, name, email, address, phone, dateOfBirth);
        this.loyalPoint = loyalPoint;
    }

    public static Customer createCustomer() throws ParseException {
        Scanner sc = new Scanner(System.in);
        int id;
        String name = Format.enterName();
        String email = Format.enterEmail();
        System.out.println("Enter your address ");
        String address = sc.nextLine();
        String phone = Format.enterPhone();
        System.out.println("Enter date of birth ");
        Date date = Format.enterDate();
        if(Company.getCustomers()==null){
            Company.setCustomers( new ArrayList<Customer>() );
        }
        if (Company.getCustomers().isEmpty()) {
            id = 1;
        } else {
            id = Company.getCustomers().get(Company.getCustomers().size() - 1).getId() + 1;
        }
        Customer customer = new Customer(id, name, email, address, phone, date);
        Company.addCustomer(customer);
        return customer;
    }

    @Override
    public String toString() {
        return "Customer{" +super.toString()+ '}';
    }

    public void setLoyalPoint(int loyalPoint) {
        this.loyalPoint = loyalPoint;
    }

    public int getLoyalPoint() {
        return loyalPoint;
    }
   
   

}
