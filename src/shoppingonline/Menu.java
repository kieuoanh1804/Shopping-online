/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingonline;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author admin
 */
public class Menu {

    private static void managmentAccount() throws FileNotFoundException, ParseException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1.Xem thông tin tất cả các tài khoản");
            System.out.println("2.Thêm tài khoản");
            System.out.println("3.Xóa tài khoản");
            int choose = -1;
            boolean yn;
            String x;
            x = sc.nextLine();
            yn = checkCaseNumber(x, 1);
            if (yn) {
                choose = Integer.parseInt(x);
            } else {
                continue;
            }
            switch (choose) {
                case 1: {
                    System.out.println("Thông tin tất cả các tài khoản \n\n");
                    for (int i = 0; i < AccountManagementSystem.getListAccount().size(); i++) {
                        AccountManagementSystem.getListAccount().get(i).display();

                    }
                    break;
                }
                case 2: {
                    System.out.println("Thêm tài khoản \n\n ");
                    AccountManagementSystem.register();
                    break;
                }
                case 3: {
                    System.out.println("Xóa tài khoản ");
                    System.out.println("Có các tài khoản sau");
                    for (int i = 0; i < AccountManagementSystem.getListAccount().size(); i++) {
                        System.out.println(AccountManagementSystem.getListAccount().get(i));

                    }
                    System.out.println("Nhập id tài khoản bạn muốn xóa");
                    int id;
                    String x1;
                    x1 = sc.nextLine();
                    boolean y;
                    y = checkCaseNumber(x, 99);
                    if (y) {
                        id = Integer.parseInt(x);
                        if (id > 0 && id < AccountManagementSystem.getListAccount().size()) {
                            System.out.println("Xóa tài khoản thành công ");
                            AccountManagementSystem.removeAccount(id);
                        } else {
                            System.out.println("ID tài khoản bạn nhập không tồn tại");
                        }
                    } else {
                        System.out.println("Nhập không đúng định dạng");
                    }
                    break;
                }

            }
            break;

        }
    }

    public static void revenued() {
        System.out.println("Tổng doanh thu những mặt hàng đã bán được ");
        double total = 0;
        int j = 0;
        for (int i = 0; i < Store.getListOrder().size(); i++) {
            if (Store.getListOrder().get(i).getStatus() == true) {
                j++;
                total += Store.getListOrder().get(i).getTotalPrice();
            }

        }
        System.out.println("Tổng số doanh thu của " + j + " Order " + " là: " + String.format(" %,.2f ", total));
    }

    public static void admin() throws FileNotFoundException, ParseException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1.Quản lý tài khoản");
            System.out.println("2.Quản lý bán hàng ");
            int choose = -1;
            boolean yn;
            String x;
            x = sc.nextLine();
            yn = checkCaseNumber(x, 1);
            if (yn) {
                choose = Integer.parseInt(x);
            }
            switch (choose) {
                case 1: {
                    managmentAccount();
                    break;
                }
                case 2: {
                    managemenStore();
                    break;
                }

            }
            System.out.println("Bạn có muốn đăng xuất không");

            if (!exit()) {
                break;
            }
        }
    }

    public static void managemenStore() {
        System.out.println("1.Xem các sản phẩm có trong kho ");
        System.out.println("2.Xem các mặt hàng có trong kho");
        System.out.println("3.Thông kê doanh thu ");
        System.out.println("4.Hiện thị danh sách bán chạy( giảm dần)");
        int choose = 0;
        String x;
        Scanner sc = new Scanner(System.in);
        while (true) {
            x = sc.nextLine();
            if (!checkCaseNumber(x, 1)) {
                System.out.println("Nhập không hợp lệ");
                System.out.println("Nhập lại");
                continue;
            } else {
                choose = Integer.parseInt(x);
            }
            switch (choose) {
                case 1: {
                    viewProduct(Store.getListProducts());
                    break;
                }
                case 2: {
                    viewCategories(Store.getListCategories());
                    break;
                }

                case 3: {
                    revenued();
                    break;
                }
                case 4: {
                    ArrayList<Product> list = new ArrayList<Product>();
                    int max[] = new int[Store.getListProducts().size()];
                    for (int i = 0; i < Store.getListOrder().size(); i++) {
                        if (Store.getListOrder().get(i).getStatus() == true) {
                            for (int j = 0; j < Store.getListOrder().get(i).getOrderDetail().size(); j++) {
                                max[Store.getListOrder().get(i).getOrderDetail().get(j).getProduct().getId() - 1] += Store.getListOrder().get(i).getOrderDetail().get(j).getQuantity();
                            }
                        }
                    }
                    int arr[] = new int[max.length];
                    int index = 0;
                    for (int j = 0; j < Store.getListProducts().size(); j++) {
                        int maX = max[0];
                        index = 0;
                        for (int i = 1; i < Store.getListProducts().size(); i++) {
                            if (maX < max[i]) {
                                maX = max[i];
                                index = i;
                            }
                        }
                        arr[j] = max[index];

                        max[index] = -1;
                        list.add(Store.findProduct(index + 1));
                    }

                    System.out.println("Danh sách các sản phẩm bán chạy giảm gần là ");
                    System.out.printf("%-62s\n", "____________________________________________________________________________________________");
                    System.out.printf("|%-10s || %-30s || %-20s || %-20s %-20s \n", "Id", "Tên sản phẩm", "Giá", "Kích thước", "So luong");

                    System.out.printf("%-62s\n", "____________________________________________________________________________________________");

                    for (int i = 0; i < list.size(); i++) {
                        System.out.printf("|%-10d || %-30s || %-20s || %-20s %-20d\n", list.get(i).getId(), list.get(i).getName(), String.format("%,.2f", list.get(i).getPrice()), list.get(i).getSize(),arr[i]);
                        System.out.printf("%-62s\n", "|===========================================================================================");

                    }

                    break;
                }
            }

            break;
        }

    }

    public static int signIn() {
        int choose = 0;
        outer:
        do {
            System.out.println("Đăng nhập tài khoản để vào Shop( Cô có thể nhập tk: long123 mk:long123 hoặc tạo tài khoản để đăng nhập) ");
            System.out.println("1. Nếu có tài khoản(Đăng nhập) ");
            System.out.println("2. Nếu chưa có tài khoản(Tạo một tài khoản)");
            System.out.println("3. Đăng nhập băng admin ");
            Scanner sc = new Scanner(System.in);
            String x = sc.nextLine();
            boolean a = checkCaseNumber(x, 1);
            if (a) {
                choose = Integer.parseInt(x);
            } else {
                continue;
            }

        } while (choose != 1 && choose != 2 && choose != 3);

        return choose;

    }

    public static boolean exit() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Nhấn 0 để tiếp tục ");
            System.out.println("Nhấn phím bất kỳ để thoát");
            String go = sc.nextLine();
            boolean go1 = Format.NUMBER.matcher(go).matches();
            if (!go1) {
                return false;
            }
            int go2 = Integer.parseInt(go);
            if (go2 == 0) {
                return true;
            } else {
                return false;
            }
        }

    }

    public static void MenuCustomer(Account account) {
        Scanner sc = new Scanner(System.in);
        outer:
        while (true) {
            String x;
            int choose = 0;
            System.out.println("1.Xem các sản phẩm ");
            System.out.println("2.Xem thông tin tài khoản ");
            System.out.println("3.Xem giỏ đồ");
            System.out.println("4.Xem các mặt hàng đã thanh toán ");
            System.out.println("5.Thêm sản phẩm vào giỏ hàng ");
            System.out.println("6.Thanh toán mặt hàng");
            System.out.println("7.Đổi mật khẩu");
            x = sc.nextLine();
            boolean a = checkCaseNumber(x, 4);
            if (a) {
                choose = Integer.parseInt(x);
            } else {
                continue outer;
            }
            switch (choose) {

                case 1: {
                    MenuProduct();
                    break;
                }
                case 2: {
                    System.out.println("Thông tin tài khoản của bạn là");
                    account.display();
                    break;
                }
                case 3: {
                    account.ViewCart();
                    break;
                }
                case 4: {
                    account.ViewPaid();
                    break;
                }
                case 5: {

                    ArrayList<  OrderDetail> orderDetail = new ArrayList<OrderDetail>();
                    while (true) {
                        viewProduct(Store.getListProducts());
                        System.out.println("\n\nCó các sản phẩm trên bạn muốn thêm sản phẩm nào vào giỏ ");
                        String z;
                        z = sc.nextLine();
                        int cho;
                        boolean as = checkCaseNumber(x, Store.getListProducts().size() / 10 - 1);
                        if (!as) {
                            System.out.println("Nhập sai ");
                            continue;
                        }
                        cho = Integer.parseInt(z);

                        System.out.println("Nhập địa chỉ bạn muốn giao hàng ");
                        String address = sc.nextLine();

                        int cho1;

                        String q;
                        while (true) {

                            System.out.println("Nhập số lượng ");
                            q = sc.nextLine();
                            if (checkCaseNumber(x, 3)) {
                                break;
                            }
                        }
                        cho1 = Integer.parseInt(q);

                        Product product = null;
                        int found = 0;
                        for (int i = 0; i < Store.getListProducts().size(); i++) {
                            if (Store.getListProducts().get(i).getId() == cho) {
                                product = Store.findProduct(cho);
                                found++;
                            }
                        }
                        if (found == 0) {
                            System.out.println("Sản phẩm không tồn tài \nNhap lại");
                            continue;
                        }
                        OrderDetail orderDetail1 = new OrderDetail(Store.getListOrderDetails().get(Store.getListOrderDetails().size() - 1).getId() + 1, product, cho1);
                        Store.getListOrderDetails().add(orderDetail1);
                        orderDetail.add(orderDetail1);
                        System.out.println("Bạn có muốn mua thêm sản phẩm nào nữa không ");
                        System.out.println("1.Có");
                        System.out.println("2.Không ");
                        x = sc.nextLine();
                        boolean ad = checkCaseNumber(x, 1);
                        if (ad) {
                            int go = 0;
                            Order order = new Order(Store.getListOrder().get(Store.getListOrder().size() - 1).getId(), Company.findEmployee(1), account.getCustomer(), new Date(), address, false, orderDetail);
                            account.addCart(order);
                            go = Integer.parseInt(x);
                            if (go != 1) {
                                break;
                            }
                        }

                    }

                    break;
                }
                case 6: {
                    account.ViewCart();
                    System.out.println("Nhập id order muốn thanh toán ");
                    int id;
                    String check;
                    check = sc.nextLine();
                    if (checkCaseNumber(x, 3)) {
                        id = Integer.parseInt(check);
                        id--;

                    } else {
                        System.out.println("Định dạng không đúng ");
                        System.out.println("Thanh toán không thành công");
                        break;
                    }
                    if (id >= 0 && id < account.getCart().size()) {

                        if (account.payment(account.getCart().get(id))) {
                            System.out.println("Thanh toán thành công");
                        }
                    } else {
                        System.out.println("Thanh toán thất bại");
                    }
                    break;

                }
                case 7: {
                    account.changePassword();
                    break;
                }

            }
                                    System.out.println("Bạn có muốn đăng xuất không(Bấm phím bất kỳ để đăng xuất)");

            a = exit();
            if (!a) {
                break;
            }
        }
    }

    public static boolean checkCaseNumber(String x, int n) {
        if (x == "") {
            return false;
        }
        String a = "[0-9]" + "{1," + n + "}" + "$";
        Pattern pattern = Pattern.compile(a);
        if (pattern.matcher(x).matches()) {
            return true;
        }
        return false;
    }

    public static void viewProduct(ArrayList<Product> list) {
        System.out.printf("%-62s\n", "___________________________________________________________________________________");
        System.out.printf("|%-10s || %-30s || %-20s || %-20s \n", "Id", "Tên sản phẩm", "Giá", "Kích thước|");

        System.out.printf("%-62s\n", "|_________________________________________________________________________________|");

        for (int i = 0; i < list.size(); i++) {
            list.get(i).display();
            System.out.printf("%-62s\n", "|=================================================================================");

        }
    }

    public static void viewCategories(ArrayList<Categories> list) {
        System.out.printf("%-62s\n", " _________________________________________________________________________________");
        System.out.printf("|%-10s || %-30s || %-30s \n", "Id", "Tên loại sản phẩm ", "Mô tả thêm|");

        System.out.printf("%-62s\n", "|_________________________________________________________________________________|");

        for (int i = 0; i < list.size(); i++) {

            list.get(i).display();
            System.out.printf("%-62s\n", "|=================================================================================");

        }
    }

    public static int chooseCategories() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String x;
            int choose = -1;
            viewCategories(Store.getListCategories());
            System.out.println("Chọn ID loại sẳn phẩm ");
            x = sc.nextLine();
            boolean a = checkCaseNumber(x, Store.getListCategories().size() / 10 + 1);

            if (a) {
                choose = Integer.parseInt(x);
                if (choose > 0 && choose <= Store.getListCategories().size()) {
                    return choose;
                } else {
                    continue;
                }
            } else {
                System.out.println("Nhập lại ");
            }

        }
    }

    public static void quickSortTang(ArrayList<Product> list, int low, int high) {

        if (low < high) {

            int pi = patitionTang(list, low, high);

            quickSortTang(list, low, pi - 1);
            quickSortTang(list, pi + 1, high);
        }
    }

    public static void quickSortGiam(ArrayList<Product> list, int low, int high) {

        if (low < high) {

            int pi = patitionGiam(list, low, high);

            quickSortGiam(list, low, pi - 1);
            quickSortGiam(list, pi + 1, high);
        }
    }

    public static void swapProduct(ArrayList<Product> list, int pro1, int pro2) {
        Product temp = list.get(pro1);
        list.set(pro1, list.get(pro2));
        list.set(pro2, temp);

    }

    public static int patitionTang(ArrayList<Product> list, int low, int high) {
        double pivot = list.get(high).getPrice();
        int left = low;
        int right = high - 1;
        while (true) {
            while (left <= right && list.get(left).getPrice() < pivot) {
                left++;
            }
            while (right >= left && list.get(right).getPrice() > pivot) {
                right--;
            }
            if (left >= right) {
                break;
            }
            swapProduct(list, left, right);
            left++;
            right--;
        }
        swapProduct(list, left, high);
        return left;
    }

    public static int patitionGiam(ArrayList<Product> list, int low, int high) {
        double pivot = list.get(high).getPrice();
        int left = low;
        int right = high - 1;
        while (true) {
            while (left <= right && list.get(left).getPrice() > pivot) {
                left++;
            }
            while (right >= left && list.get(right).getPrice() < pivot) {
                right--;
            }
            if (left >= right) {
                break;
            }
            swapProduct(list, left, right);
            left++;
            right--;
        }
        swapProduct(list, left, high);
        return left;
    }

    public static void MenuProduct() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String x;
            int choose = 0;
            System.out.println("1.Xem sản phẩm bán chạy nhất ");
            System.out.println("2.Xem sản phẩm theo thể loại ");
            System.out.println("3.Xem sản phẩm theo giá từ thấp đến cao ");
            System.out.println("4.Xem sản phẩm theo giá từ cao xuống thấp ");
            System.out.println("5.Xem tất cả sẳn phẩm ");
            x = sc.nextLine();
            boolean a = checkCaseNumber(x, 1);
            if (a) {
                choose = Integer.parseInt(x);
            } else {
                continue;
            }

            switch (choose) {

                case 1: {
                    System.out.println("");
                    int max[] = new int[Store.getListProducts().size()];
                    for (int i = 0; i < Store.getListOrder().size(); i++) {
                        if (Store.getListOrder().get(i).getStatus()) {
                            for (int j = 0; j < Store.getListOrder().get(i).getOrderDetail().size(); j++) {
                                max[Store.getListOrder().get(i).getOrderDetail().get(j).getProduct().getId() - 1]++;
                            }
                        }
                    }
                    int maxx = max[0];
                    int index = 0;
                    for (int j = 0; j < max.length; j++) {
                        if (max[j] > maxx) {
                            maxx = max[j];
                        }
                        index = j;
                    }
                    System.out.println("Sản phẩm bán chạy nhất là ");

                    System.out.println(Store.getListProducts().get(index));

                    break;

                }
                case 2: {
                    int cateID = chooseCategories();
                    ArrayList<Product> list = new ArrayList<Product>();

                    for (int i = 0; i < Store.getListCategories().get(cateID - 1).getListProd().size(); i++) {
                        list.add(Store.getListCategories().get(cateID - 1).getListProd().get(i));
                    }
                    viewProduct(list);

                    break;
                }
                case 3: {
                    ArrayList<Product> list = new ArrayList<Product>();
                    for (int i = 0; i < Store.getListProducts().size(); i++) {
                        list.add(Store.getListProducts().get(i));
                    }
                    quickSortTang(list, 0, Store.getListProducts().size() - 1);
                    viewProduct(list);

                    break;
                }
                case 4: {
                    ArrayList<Product> list = new ArrayList<Product>();
                    for (int i = 0; i < Store.getListProducts().size(); i++) {
                        list.add(Store.getListProducts().get(i));
                    }
                    quickSortGiam(list, 0, Store.getListProducts().size() - 1);
                    viewProduct(list);
                    break;
                }
                case 5: {
                    viewProduct(Store.getListProducts());
                    break;
                }

            }
            break;

        }
    }
}
