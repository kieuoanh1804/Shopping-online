/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingonline;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class Account {

    private int id;
    private Customer customer;
    private double balance;
    private String userName;
    private String password;
    private ArrayList<Order> paid;
    private ArrayList<Order> cart;

    public Account() {

    }

    public Account(String userName, String password, Customer customer, double balance, int id) {
        this.customer = customer;
        this.balance = balance;
        this.userName = userName;
        this.password = password;
        this.id = id;

    }

    public Account(int id, Customer customer, double balance, String userName, String password, ArrayList<Order> paid, ArrayList<Order> cart) {
        this.id = id;
        this.customer = customer;
        this.balance = balance;
        this.userName = userName;
        this.password = password;
        this.paid = paid;
        this.cart = cart;
    }

    public boolean payment(Order order) {
        double totalprice = order.getTotalPrice();
        if (this.cart == null || this.cart.isEmpty()) {
            System.out.println("Giỏ đồ của bạn trống! Vui lòng thêm hàng vào giỏ");
            return false;
        }
        if (withdraw(totalprice)) {
            order.setStatus(true);
            for (int i = 0; i < cart.size(); i++) {
                if (order.getId() == cart.get(i).getId()) {
                    cart.remove(i);
                    if (paid == null) {
                        setPaid(new ArrayList<Order>());
                    }
                    paid.add(order);
                }

            }

        } else {
            System.out.print("Số tiền của bạn không đủ để mua đồ! Vui lòng nạp thêm tiền  ");
            System.out.println("Tổng số tiền : " + String.format("%,.2f", getBalance()) + '<' + String.format("%,.2f", totalprice) + "(Tổng đơn giá)");
            return false;
        }

        return true;
    }

    public void changePassword() {
        String oldpass;
        String newPass;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Nhập mật khẩu cũ  ");

            oldpass = sc.nextLine();
            if (Format.PASS__PATTERN.matcher(password).matches() == false) {
                System.err.println("Nhâp không đúng định dạng(Chỉ bao gồm a-z và 0-9)");
                continue;
            }
            if (!oldpass.equals(password)) {
                System.out.println("Mật khẩu nhập không đúng ");
                continue;
            } else {

                System.out.println("Nhập mật khẩu mới ");
                newPass = sc.nextLine();
                if (Format.PASS__PATTERN.matcher(newPass).matches() == false) {
                    System.err.println("Nhâp không đúng định dạng(Chỉ bao gồm a-z và 0-9)");
                    continue;
                }

            }

            System.out.println("Xác nhận mật khẩu ");
            if (!sc.nextLine().equals(newPass)) {
                System.err.println("Mật khẩu không trùng khớp");
                break;

            }
            setPassword(newPass);
            System.out.println("Đổi mật khẩu thành công");
            break;
        }
    }

    public void ViewCart() {
        if (this.cart == null) {
            System.out.println("Trống rỗng ");
            return;
        }

        if (this.cart.isEmpty()) {
            System.out.println("Trong rỗng ");

        } else {
            for (int i = 0; i < cart.size(); i++) {
                System.out.printf("HÓA ĐƠN :%d \n", i + 1);

                System.out.println("----------------------------------------------------------------------------------");
                System.out.println("Chi tiết hóa đơn gồm: ");
                for (int j = 0; j < cart.get(i).getOrderDetail().size(); j++) {
                    System.out.printf("%-10s || %-30s || %-20s || %-20s \n\n", "Id", "Tên sản phẩm", "Giá", "Kích thước");
                    cart.get(i).getOrderDetail().get(j).getProduct().display();
                    System.out.printf("Tổng giá sản phẩm: %,.2f , số lượng: %d\n", cart.get(i).getOrderDetail().get(j).getUnitPrice(), cart.get(i).getOrderDetail().get(j).getQuantity());
                }
                System.out.printf("\nTỔNG GIÁ HÓA ĐƠN  %d là: %,.2f \n\n", i + 1, cart.get(i).getTotalPrice());

            }
        }
    }

    public void ViewPaid() {
        if (this.paid == null) {
            System.out.println("Trống rỗng ");
            return;
        }
        if (this.paid.isEmpty()) {
            System.out.println("Trống rỗng ");

        } else {
            for (int i = 0; i < paid.size(); i++) {
                System.out.printf("HÓA ĐƠN :%d \n", i + 1);

                System.out.println("----------------------------------------------------------------------------------");
                System.out.println("Chi tiết hóa đơn gồm: ");
                for (int j = 0; j < paid.get(i).getOrderDetail().size(); j++) {
                    System.out.printf(" %-10s || %-30s || %-20s || %-20s \n\n", "Id", "Tên sản phẩm", "Giá", "Kích thước");
                    paid.get(i).getOrderDetail().get(j).getProduct().display();
                    System.out.printf("Tổng giá sản phẩm : %,.2f , số lượng: %d\n", paid.get(i).getOrderDetail().get(j).getUnitPrice(), paid.get(i).getOrderDetail().get(j).getQuantity());
                }
                System.out.printf("\nTỔNG GIÁ HÓA ĐƠN  %d là: %,.2f \n\n", i + 1, paid.get(i).getTotalPrice());
            }
        }

    }

    public void setPaid(ArrayList<Order> paid) {
        this.paid = paid;
    }

    public void setCart(ArrayList<Order> cart) {
        this.cart = cart;
    }

    public void addCart(Order order) {
        if (cart == null) {
            cart = new ArrayList<Order>();
        } else {
            cart.add(order);
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getBalance() {
        return balance;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public void setcustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
            System.err.println("Thành công ");
            return true;
        } else {
            System.err.println("Không hợp lệ! giao dịch không thành công");
            return false;
        }
    }

    public boolean deposit(double amount) {
        return setBalance(balance + amount);
    }

    public boolean withdraw(double amount) {
        return setBalance(balance - amount);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Order> getPaid() {
        return paid;
    }

    public ArrayList< Order> getCart() {
        return cart;
    }

    public void display() {
        System.out.printf("%s: %-20s||%s: %-20s||%s:  %,.2f  ||%s: %-20s\n", "Tên tài khoản ", getUserName(), "Tên khách hàng ", getCustomer().getName(), "Số dư ", getBalance(), "Địa chỉ email", getCustomer().getEmail());

    }

    @Override
    public String toString() {
        return "Account{" + "id " + id + " Tên tài khoản = " + userName + ",số dư =" + String.format(" %,.2f ", balance) + "Tên khách hàng=" + customer.getName() + ", email: " + customer.getEmail() + '}';
    }

}
