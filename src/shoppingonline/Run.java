/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingonline;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**
 *
 * @author long.nguyen22
 */
public class Run {
    

    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {
        Company.readData();
        Store.readData();
        AccountManagementSystem.readData();
        Account account = null;
        boolean yn = true;
        outer:
        while (yn) {

            int login = Menu.signIn();
            switch1:
            switch (login) {

                case 1: {                   
                    account = AccountManagementSystem.login();
                    if (account == null) {
                        break;
                    }
                    outer2:
                    while (true) {
                        Menu.MenuCustomer(account);
                        System.out.println("Bạn có muốn quay lại không ");
                        
                        if (!Menu.exit()) {
                            break;
                        }
                    }
                    break;
                }
                case 2: {
                    yn = AccountManagementSystem.register();
                    if (yn) {
                        account = AccountManagementSystem.getListAccount().get(AccountManagementSystem.getListAccount().size() - 1);

                    } else {
                        System.out.println("Đăng ký không thành công ");

                    }

                    break;

                }
                case 3: {
                    yn = AccountManagementSystem.admin();
                    if (yn) {
                        Menu.admin();
                    } else { 
                        System.out.println("Đăng nhập không thành công");
                    }
                    break;
                }

            }

            yn = Menu.exit();
            if (!yn) {
                break ;
            }

        }

        AccountManagementSystem.writeData();
        Store.writeData();
                Company.writeData();;

    }

}
