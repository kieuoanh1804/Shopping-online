/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingonline;

/**
 *
 * @author Administrator
 */
public class Product {

    private int id;
    private String name;
    private double price;
    private String size;

    public Product(int id, String name, double price, String size) {
        this.id = id;
        this.name = name;
        this.size= size;
        setPrice(price);   
    
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getSize() {
        return size;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        if (price < 0)
            this.price = 0;
        else
            this.price = price;
    }

    public void setSize(String size) {
        this.size = size;
    }
        public void display(){
            System.out.printf("|%-10d || %-30s || %-20s || %-20s \n",id, name ,String.format("%,.2f", price),size);
        }
    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price= " + String.format("%,.2f", price) + ", size= "
                + this.size + "}\n";
    }

}
