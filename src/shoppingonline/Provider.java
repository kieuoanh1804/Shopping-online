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
public class Provider {
    private int id;
    private String providerName;
    private String phone;
    private String email;
    private String address;

    public Provider(int id, String providerName, String phone, String email, String address) {
        this.id = id;
        this.providerName = providerName;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getProviderName() {
        return providerName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
