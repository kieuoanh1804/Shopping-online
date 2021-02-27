/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingonline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Order {

    private int id;
    private Employee employee;
    private Customer customer;
    private Date date;
    private String shipment_address;
    private boolean status;
    private ArrayList<OrderDetail> orderDetail;

    public Order(int id, Employee employee, Customer customer, Date date, String shipment_address, boolean status, ArrayList<OrderDetail> orderDetail) {
        this.id = id;
        this.employee = employee;
        this.customer = customer;
        this.date = date;
        this.shipment_address = shipment_address;
        this.status = status;
        this.orderDetail = orderDetail;
    }

    @Override
    public String toString() {
            String a="Detail: ";
            for (int i = 0; i <orderDetail.size(); i++) {
            a+=orderDetail.toString();
                    }
            
        return "Order{"+ a+",  ngày =" + Format.DATE_FORMAT.format(date) + ",tổng hóa đơn =" + String.format("%,.2f",getTotalPrice())+ "}\n";
    }

    public double getTotalPrice(){
        double total=0;
        for (int i = 0; i < orderDetail.size(); i++) {
            total+= orderDetail.get(i).getUnitPrice();
            
        }
    return total;
    }



    public int getId() {
        return id;
    }



    public Date getDate() {
        return date;
    }

    public String getShipment_address() {
        return shipment_address;
    }

    public boolean getStatus() {
        return status;
    }

    public ArrayList<OrderDetail> getOrderDetail() {
        return orderDetail;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public void setShipment_address(String shipment_address) {
        this.shipment_address = shipment_address;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public boolean isStatus() {
        return status;
    }


    public void setOrderDetail(ArrayList<OrderDetail> orderDetail) {
        this.orderDetail = orderDetail;
    }

  



    public void showData() {
        
    }
}
