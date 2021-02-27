/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingonline;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public abstract class Person {
    protected int id;
    protected  String name;
    protected String email;
    protected String address;
    protected String phone;
    protected Date dateOfBirth ;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Person(int id, String name, String email, String address, String phone, Date dateOfBirth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return   "name=" + name + ", email=" + email + ", address=" + address + ", phone=" + phone + ", dateOfBirth=" + Format.DATE_FORMAT.format(dateOfBirth)+ '}';
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    
    
           
}
