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
public class OrderDetail {
    private int id;
    private Product product;
    private int quantity;
    private double unitPrice;

    public OrderDetail(int id, Product product, int quantity, double unitPrice) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        setUnitPrice(unitPrice);
    }

    public OrderDetail(int id, Product product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice=this.product.getPrice()*quantity;
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

    public double getUnitPrice() {
        return unitPrice;
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

    public void setUnitPrice(double unitPrice) {
        if (unitPrice < 0)
            unitPrice = 0;
        else
            this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "{" + "id=" + id + ", product=" + product + ", quantity=" + quantity + ", unitPrice=" + String.format("%,.2f", unitPrice) + "}\n";
    }

}
