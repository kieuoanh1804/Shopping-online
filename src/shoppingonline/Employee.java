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
public class Employee extends Person {

    private double salary;

    public Employee( int id, String name, String email, String address, String phone, Date dateOfBirth,double salary) {
        super(id, name, email, address, phone, dateOfBirth);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            this.salary = 0;
        } else {
            this.salary = salary;
        }
    }

    @Override
    public String toString() {
        return "Employee{" + super.toString() + '}';
    }

}
