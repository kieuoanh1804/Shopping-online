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
public class ImportDetail {
    private int id;
    private Product product;
    private int quantity;
    private double unit_price;

    public ImportDetail(int id, Product product, int quantity, double unit_price) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        setUnit_price(unit_price);
    }

    public int getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnit_price(double unit_price) {
        if (unit_price > 0)
            this.unit_price = 0;
        else
            this.unit_price = unit_price;
    }

}
