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
import java.nio.file.Files;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class AccountManagementSystem {

    private static String passAdminStore = "admin";

    private static  ArrayList<Account> account= new ArrayList<Account>();


    public static Account login() {
        Scanner sc = new Scanner(System.in);
        String pass;
        String user;
        Account account;
        while (true) {
            System.out.println("Nhập tài tên tài khoản ");
            user = sc.nextLine();
            System.out.println("Nhập mật khẩu");
            pass = sc.nextLine();

            account = findAccount(user, pass);
            if (account == null) {
                 boolean b= Menu.exit();
                if(b){
                  continue;
                }
                else 
                   break;
            }
                
             else {
                System.out.println("Đăng nhập thành công ");
                break;
            }

        }
        return account;
    }

    public static boolean admin() {
        Scanner sc = new Scanner(System.in);
        outer:  do {
            System.out.println("Nhập mật khẩu admin "+"( mật khẩu là admin nha cô)");

            if (sc.nextLine().equals("admin")) {
                System.out.println("");
                return true;
            }
            else{
                boolean b= Menu.exit();
                if(b){
                   continue outer;
                }
                else 
                    break;
            }
        } while (true);
        return false;
    }
    

    public static boolean register() throws FileNotFoundException, ParseException {
        Scanner sc = new Scanner(System.in);
        String userName;
        String password;

        outer:
        while (true) {
            System.out.println("Nhập tên tài khoản  ");

            userName = sc.nextLine();
            if (Format.USER__PATTERN.matcher(userName).matches() == false) {
                System.err.println("Nhâp không đúng định dạng(Chỉ bao gồm a-z và 0-9)");

                continue;
            }
            for (int i = 0; i < account.size(); i++) {
                if (account.get(i).getUserName().equalsIgnoreCase(userName)) {
                    System.err.println("User:" + userName + " đã tồn tại! Thử tên tài khoản khác");

                    continue outer;
                }

            }
            break;
        }

        while (true) {
            System.out.println("Nhập mật khẩu ");

            password = sc.nextLine();
            if (Format.PASS__PATTERN.matcher(password).matches() == false) {
                System.err.println("Nhâp không đúng định dạng(Chỉ bao gồm a-z và 0-9)");

                continue;
            }
            System.out.println("Xác nhận mật khẩu ");
            if (!sc.nextLine().equals(password)) {
                System.err.println("Mật khẩu không trùng khớp");
                continue;
            }

            break;
        }

        Customer customer = Customer.createCustomer();

        int createID;

        if (account.isEmpty()) {
            createID = 1;
        } else {
            createID = account.get(account.size() - 1).getId() + 1;
        }

        account.add(new Account(userName, password, customer, 0.0, createID));
        System.out.println("Đăng ký thành công ");
        return true;

    }

    public  static ArrayList<Account> getListAccount() {
        return account;
    }

    public  static void readData() throws FileNotFoundException {
        try (Scanner readin = new Scanner( new File("Account.txt")) ){
            while (readin.hasNextLine()) {
                int getid = Integer.parseInt(readin.nextLine());
                int getCusID = Integer.parseInt(readin.nextLine());
                Customer customer = Company.findCustomer(getCusID);
                double getbalance = Double.parseDouble(readin.nextLine());
                String user = readin.nextLine();
                String pass = readin.nextLine();
                ArrayList<Order> paid = new ArrayList<Order>();
                ArrayList<Order> cart = new ArrayList<Order>();
                String paidString = readin.nextLine();
                String cartString = readin.nextLine();

                if (cartString.equals("null")) {
                    cart = null;
                } else {
                    String token[] = cartString.split(" ");
                    for (int i = 0; i < token.length; i++) {
                        cart.add(Store.findOrder(Integer.parseInt(token[i])));

                    }
                }
                if (paidString.equals("null")) {
                    paid = null;
                } else {
                    String token[] = paidString.split(" ");
                    for (int i = 0; i < token.length; i++) {
                        paid.add(Store.findOrder(Integer.parseInt(token[i])));
                    }
                }
               
                account.add(new Account(getid, customer, getbalance, user, pass, paid, cart));
            }
                           

        }
    }

    public  static void writeData() throws IOException {
        try {
            File file = new File("Account.txt");
            FileOutputStream fos = new FileOutputStream(file);
            for (int i = 0; i < account.size(); i++) {
                Account acc = account.get(i);
                byte x[][] = new byte[5][];
                x[0] = Integer.toString(acc.getId()).getBytes();
                x[1] = Integer.toString(acc.getCustomer().getId()).getBytes();
                x[2] = Double.toString(acc.getBalance()).getBytes();
                x[3] = acc.getUserName().getBytes();
                x[4] = acc.getPassword().getBytes();
                Store.write(x, fos);

                if (acc.getPaid() == null || acc.getPaid().isEmpty()) {
                    fos.write("null".getBytes());
                    fos.write(13);
                } else {
                    for (int j = 0; j < acc.getPaid().size(); j++) {
                        fos.write(Integer.toString(acc.getPaid().get(j).getId()).getBytes());
                        fos.write(' ');

                    }
                    fos.write(13);

                }

                if (acc.getCart() == null || acc.getCart().isEmpty()) {
                    fos.write("null".getBytes());
                    fos.write(13);
                } else {
                    for (int j = 0; j < acc.getCart().size(); j++) {
                        fos.write(Integer.toString(acc.getCart().get(j).getId()).getBytes());
                        fos.write(' ');

                    }
                    fos.write(13);

                }
            }
            fos.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AccountManagementSystem.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }


    public  static boolean removeAccount(int id) {
        for (int i = 0; i < account.size(); i++) {
            if (id == account.get(i).getId()) {
                account.remove(i);
                System.gc();
                return true;
            }
        }

        return false;
    }

    public static Account findAccount(int id) {
        for (int i = 0; i < account.size(); i++) {
            if (id == account.get(i).getId()) {
                return account.get(i);
            }
        }
        return null;
    }

    public  static Account findAccount(String user, String pass) {
        int j=0;
        for (int i = 0; i < account.size(); i++) {
            if (user.equals(account.get(i).getUserName())) {
                if (pass.equals(account.get(i).getPassword())) {
                    return account.get(i);
                } else {
                    j=1;
                    System.out.println("Mật khẩu không chính xác ");
                }
            }
        }
        if(j==0)
        System.out.println("Tài khoản này không tồn tại");
        return null;
    }



}
