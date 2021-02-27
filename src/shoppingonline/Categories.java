    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingonline;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author long.nguyen22
 */
public class Categories {

    private int id;
    private String cateName;
    private String cateDesc;
    private ArrayList<Product> listProd;
    private ArrayList<Integer> quantity;

    private Categories(String cateName, String cateDesc, int id) {
        this.cateName = cateName;
        this.cateDesc = cateDesc;
        this.id = id;
        this.listProd = new ArrayList<Product>();
        this.quantity = new ArrayList<Integer>();

    }

    public Categories(int id,String cateName, String cateDesc, ArrayList<Product> list, ArrayList<Integer> quantity) {
        this.cateName = cateName;
        this.cateDesc = cateDesc;
        this.listProd = list;
        this.quantity = quantity;
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public String getCateName() {
        return cateName;
    }

    public String getCateDesc() {
        return cateDesc;
    }

    public ArrayList<Product> getListProd() {
        return listProd;
    }

    public ArrayList<Integer> getQuantity() {
        return quantity;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public void setCateDesc(String cateDesc) {
        this.cateDesc = cateDesc;
    }

    public void setListProd(ArrayList<Product> listProd) {
        this.listProd = listProd;
    }

    public void setQuantity(ArrayList<Integer> quantity) {
        this.quantity = quantity;
    }

  

    public static boolean createCategories(String cateName, String cateDesc) {

        for (int i = 0; i < Store.getListCategories().size(); i++) {
            if (Store.getListCategories().get(i).getCateName().equals(cateName)) {
                return false;
            }
        }

        int createID=1;
        if (Store.getListCategories().isEmpty()) {
            createID = 1;
        } else {
            createID = Store.getListCategories().get(Store.getListCategories().size() - 1).getId() + 1;
        }
        Store.getListCategories().add(new Categories(cateDesc, cateDesc, createID));
        return true;

    }
    public  void display(){
        System.out.printf("|%-10s || %-30s || %-30s \n",String.format("%d",id),cateName,cateDesc);
    }

    @Override
    public String toString() {
        return "Categories{" + "id=" + id + ", cateName=" + cateName + ", cateDesc=" + cateDesc +  '}';
    }
    
  
}
