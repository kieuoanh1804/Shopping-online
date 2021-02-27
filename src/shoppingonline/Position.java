/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingonline;

import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class Position {
    private int id;
    private String name;
    private String  desc;
    private ArrayList<Employee> listEmp;

    public Position(int id, String name, String desc, ArrayList<Employee> listEmp) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.listEmp = listEmp;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public ArrayList<Employee> getListEmp() {
        return listEmp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setListEmp(ArrayList<Employee> listEmp) {
        this.listEmp = listEmp;
    }
    
            
}
