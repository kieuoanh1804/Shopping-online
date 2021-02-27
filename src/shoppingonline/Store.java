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
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.midi.Patch;

/**
 *
 * @author Administrator
 */
public class Store {

    private static ArrayList<Categories> listCategories;
    private static ArrayList<Order> listOrder;
    private static ArrayList<Product> listProducts;
    private static ArrayList<ProductImport> listProductImports;
    private static ArrayList<Provider> listProviders;
    private static ArrayList<ImportDetail> listImportDetails;
    private static ArrayList<OrderDetail> listOrderDetails;

    public static ArrayList<Product> getListProducts() {
        return listProducts;
    }

    public static ArrayList<ProductImport> getListProductImports() {
        return listProductImports;
    }

    public static ArrayList<Provider> getListProviders() {
        return listProviders;
    }

    public static ArrayList<ImportDetail> getListImportDetails() {
        return listImportDetails;
    }

    public static ArrayList<OrderDetail> getListOrderDetails() {
        return listOrderDetails;
    }

    public static void writeData() throws IOException, ParseException {
        writeDataFile(new File("Product.txt"));
        writeDataFile(new File("Categories.txt"));
        writeDataFile(new File("OrderDetail.txt"));
        writeDataFile(new File("Order.txt"));
        writeDataFile(new File("Provider.txt"));
        writeDataFile(new File("ProductImport.txt"));
        writeDataFile(new File("ImportDetail.txt"));

    }

    public static void readData() throws IOException {
        readDataFile(new File("Product.txt"));
        readDataFile(new File("Categories.txt"));
        readDataFile(new File("OrderDetail.txt"));

        readDataFile(new File("Order.txt"));
        readDataFile(new File("Provider.txt"));
        readDataFile(new File("ProductImport.txt"));
        readDataFile(new File("ImportDetail.txt"));
    }

    public static void readDataFile(File file) {

        try {
            if (Files.isSameFile(file.toPath(), new File("Product.txt").toPath())) {
                listProducts = new ArrayList<Product>();
                Scanner read = new Scanner(file);
                while (read.hasNextLine()) {
                    int getId = Integer.parseInt(read.nextLine());
                    String name = read.nextLine();
                    double price = Double.parseDouble(read.nextLine());
                    String size = read.nextLine();
                    listProducts.add(new Product(getId, name, price, size));
                }
                read.close();
            }
        } catch (NumberFormatException | IOException e1) {

            e1.printStackTrace();
        }
        try {
            if (Files.isSameFile(file.toPath(), new File("ProductImport.txt").toPath())) {
                listProductImports = new ArrayList<ProductImport>();
                Scanner read = new Scanner(file);
                while (read.hasNextLine()) {
                    int getId = Integer.parseInt(read.nextLine());
                    Provider p = Store.findProvider(Integer.parseInt(read.nextLine()));
                    ImportDetail importDetail = Store.findImportDetail(Integer.parseInt(read.nextLine()));
                    Date date = Format.DATE_FORMAT.parse(read.nextLine());
                    boolean status = "true".equals(read.nextLine());
                    listProductImports.add(new ProductImport(getId, p, importDetail, date, status));
                }
                read.close();

            }
        } catch (NumberFormatException | IOException | ParseException e1) {
            // e1.printStackTrace();
        }
        try {
            if (Files.isSameFile(file.toPath(), new File("Provider.txt").toPath())) {
                listProviders = new ArrayList<Provider>();

                Scanner read = new Scanner(file);
                while (read.hasNextLine()) {
                    int getId = Integer.parseInt(read.nextLine());
                    String ProviderName = read.nextLine();
                    String phone = read.nextLine();
                    String email = read.nextLine();
                    String address = read.nextLine();

                    listProviders.add(new Provider(getId, ProviderName, phone, email, address));
                }
                read.close();

            }
        } catch (NumberFormatException | IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            if (Files.isSameFile(file.toPath(), new File("ImportDetail.txt").toPath())) {
                Scanner read = new Scanner(file);
                listImportDetails = new ArrayList<ImportDetail>();
                while (read.hasNextLine()) {
                    int getId = Integer.parseInt(read.nextLine());
                    Product product = findProduct(Integer.parseInt(read.nextLine()));
                    int quantity = Integer.parseInt(read.nextLine());
                    double unit_price = Double.parseDouble(read.nextLine());
                    listImportDetails.add(new ImportDetail(getId, product, quantity, unit_price));
                }
                read.close();
            }
        } catch (NumberFormatException | IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            if (Files.isSameFile(file.toPath(), new File("OrderDetail.txt").toPath())) {
                try {
                    Scanner read = new Scanner(file);
                    listOrderDetails = new ArrayList<OrderDetail>();
                    while (read.hasNextLine()) {
                        int getId = Integer.parseInt(read.nextLine());
                        int idPro = Integer.parseInt(read.nextLine());
                        Product p = Store.findProduct(idPro);
                        int quantity = Integer.parseInt(read.nextLine());
                        double price = Double.parseDouble(read.nextLine());
                        listOrderDetails.add(new OrderDetail(getId, p, quantity, price));
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Store.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (NumberFormatException | IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            if (Files.isSameFile(file.toPath(), new File("Order.txt").toPath())) {

                try {
                    listOrder = new ArrayList<Order>();
                    Scanner read = new Scanner(file);
                    while (read.hasNextLine()) {
                        int id = Integer.parseInt(read.nextLine());
                        int empId = Integer.parseInt(read.nextLine());
                        int CusID = Integer.parseInt(read.nextLine());
                        Date date = Format.DATE_FORMAT.parse(read.nextLine());
                        String shipment = read.nextLine();

                        boolean status = read.nextLine().equals("true");
                        ArrayList<OrderDetail> details = new ArrayList<OrderDetail>();
                        String listOrder1[] = read.nextLine().split(" ");
                        int[] ints = new int[listOrder1.length];
                        for (int i = 0; i < ints.length; i++) {
                            ints[i] = Integer.parseInt(listOrder1[i]);
                            details.add(findOrderDetail(ints[i]));
                        }
                        Employee e = Company.findEmployee(empId);
                        Customer customer = Company.findCustomer(CusID);

                        listOrder.add(new Order(id, e, customer, date, shipment, status, details));

                    }
                    read.close();

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Store.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (NumberFormatException | IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            if (Files.isSameFile(file.toPath(), new File("Categories.txt").toPath())) {
                listCategories = new ArrayList<Categories>();
                Scanner read = new Scanner(file);
                while (read.hasNextLine()) {
                    int getId = Integer.parseInt(read.nextLine());
                    String cate_name = read.nextLine();
                    String cate_desc = read.nextLine();
                    String token[] = read.nextLine().split(" ");
                    int[] ints = new int[token.length];
                    ArrayList<Product> listProduct = new ArrayList<Product>();

                    for (int i = 0; i < token.length; i++) {
                        ints[i] = Integer.parseInt(token[i]);
                        listProduct.add(findProduct(ints[i]));
                    }
                    String listQuantity[] = read.nextLine().split(" ");
                    ArrayList<Integer> quan = new ArrayList<Integer>();
                    for (int i = 0; i < listQuantity.length; i++) {
                        quan.add(Integer.parseInt(listQuantity[i]));
                    }
                    listCategories.add(new Categories(getId, cate_name, cate_desc, listProduct, quan));
                }
                read.close();

            }
        } catch (NumberFormatException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void write(byte x[][], FileOutputStream fos) throws IOException {
        for (int i = 0; i < x.length; i++) {
            fos.write(x[i]);
            fos.write(13);
        }
    }

    public static void writeDataFile(File file) throws IOException {
        if (Files.isSameFile(file.toPath(), new File("Product.txt").toPath())) {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                for (int i = 0; i < listProducts.size(); i++) {
                    byte x[][] = new byte[4][];
                    Product product = listProducts.get(i);
                    x[0] = Integer.toString(product.getId()).getBytes();
                    x[1] = product.getName().getBytes();
                    x[2] = Double.toString(product.getPrice()).getBytes();
                    x[3] = product.getSize().getBytes();
                    write(x, fos);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Store.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (Files.isSameFile(file.toPath(), new File("ProductImport.txt").toPath())) {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                for (int i = 0; i < listProductImports.size(); i++) {
                    ProductImport productImport = listProductImports.get(i);
                    byte x[][] = new byte[5][];
                    x[0] = Integer.toString(productImport.getId()).getBytes();
                    x[1] = Integer.toString(productImport.getProvider().getId()).getBytes();
                    x[2] = Integer.toString(productImport.getImportDetail().getId()).getBytes();
                    x[3] = Format.DATE_FORMAT.format(productImport.getImportDate()).getBytes();
                    if (productImport.isStatus()) {
                        x[4] = "true".getBytes();
                    } else {
                        x[4] = "false".getBytes();

                    }
                    write(x, fos);

                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Store.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (Files.isSameFile(file.toPath(), new File("Provider.txt").toPath())) {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                for (int i = 0; i < listProviders.size(); i++) {
                    Provider provider = listProviders.get(i);
                    byte x[][] = new byte[5][];
                    x[0] = Integer.toString(provider.getId()).getBytes();
                    x[1] = provider.getProviderName().getBytes();
                    x[2] = provider.getPhone().getBytes();

                    x[3] = provider.getEmail().getBytes();
                    x[4] = provider.getAddress().getBytes();

                    write(x, fos);

                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Store.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (Files.isSameFile(file.toPath(), new File("ImportDetail.txt").toPath())) {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                for (int i = 0; i < listProductImports.size(); i++) {
                    ImportDetail importDetail = listImportDetails.get(i);
                    byte x[][] = new byte[4][];
                    x[0] = Integer.toString(importDetail.getId()).getBytes();
                    x[1] = Integer.toString(importDetail.getProduct().getId()).getBytes();
                    x[2] = Integer.toString(importDetail.getQuantity()).getBytes();
                    x[3] = Double.toString(importDetail.getUnit_price()).getBytes();

                    write(x, fos);

                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Store.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (Files.isSameFile(file.toPath(), new File("OrderDetail.txt").toPath())) {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                for (int i = 0; i < listOrderDetails.size(); i++) {
                    OrderDetail orderDetail = listOrderDetails.get(i);
                    byte x[][] = new byte[4][];
                    x[0] = Integer.toString(orderDetail.getId()).getBytes();
                    x[1] = Integer.toString(orderDetail.getProduct().getId()).getBytes();
                    x[2] = Integer.toString(orderDetail.getQuantity()).getBytes();
                    x[3] = Double.toString(orderDetail.getUnitPrice()).getBytes();
                    write(x, fos);
                }
            } catch (Exception e) {
            }

        }
        if (Files.isSameFile(file.toPath(), new File("Order.txt").toPath())) {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                for (int i = 0; i < listOrder.size(); i++) {
                    Order order = listOrder.get(i);
                    byte x[][] = new byte[6][];
                    x[0] = Integer.toString(order.getId()).getBytes();
                    x[1] = "1".getBytes();
                    x[2] = Integer.toString(order.getCustomer().getId()).getBytes();
                    x[3] = Format.DATE_FORMAT.format(order.getDate()).getBytes();
                    x[4] = order.getShipment_address().getBytes();
                    if (order.getStatus()) {
                        x[5] = "true".getBytes();
                    } else {
                        x[5] = "false".getBytes();
                    }
                    write(x, fos);
                    for (int j = 0; j < order.getOrderDetail().size(); j++) {
                        byte detail[] = Integer.toString(order.getOrderDetail().get(j).getId()).getBytes();
                        fos.write(detail);
                        fos.write(' ');

                    }
                    fos.write(13);

                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Store.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (Files.isSameFile(file.toPath(), new File("Categories.txt").toPath())) {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                for (int i = 0; i < listCategories.size(); i++) {
                    Categories categories = listCategories.get(i);
                    byte x[][] = new byte[3][];
                    x[0] = Integer.toString(categories.getId()).getBytes();
                    x[1] = categories.getCateName().getBytes();
                    x[2] = categories.getCateDesc().getBytes();
                    write(x, fos);
                    if (!categories.getListProd().isEmpty()) {
                        for (int j = 0; j < categories.getListProd().size(); j++) {
                            byte idProp[] = Integer.toString(categories.getListProd().get(j).getId()).getBytes();
                            fos.write(idProp);
                            fos.write(' ');
                        }
                        fos.write(13);
                    }
                    if (!categories.getQuantity().isEmpty()) {
                        for (int j = 0; j < categories.getQuantity().size(); j++) {
                            byte id[] = Integer.toString(categories.getQuantity().get(j)).getBytes();
                            fos.write(id);
                            fos.write(' ');
                        }
                        fos.write(13);
                    }
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Store.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }


    public static ArrayList<Categories> getListCategories() {
        return listCategories;
    }

    public static ArrayList<Order> getListOrder() {
        return listOrder;
    }

    public static Product findProduct(int id) {
        for (int i = 0; i < listProducts.size(); i++) {
            if (id == listProducts.get(i).getId()) {
                return listProducts.get(i);
            }
        }

        return null;
    }

    public static Categories findCategories(int id) {
        for (int i = 0; i < listCategories.size(); i++) {
            if (id == listCategories.get(i).getId()) {
                return listCategories.get(i);
            }
        }

        return null;
    }

    public static OrderDetail findOrderDetail(int id) {
        for (int i = 0; i < listOrderDetails.size(); i++) {
            if (id == listOrderDetails.get(i).getId()) {
                return listOrderDetails.get(i);
            }
        }

        return null;
    }

    public static ImportDetail findImportDetail(int id) {
        for (int i = 0; i < listImportDetails.size(); i++) {
            if (id == listImportDetails.get(i).getId()) {
                return listImportDetails.get(i);
            }
        }

        return null;
    }

    public static Order findOrder(int id) {
        for (int i = 0; i < listOrder.size(); i++) {
            if (id == listOrder.get(i).getId()) {
                return listOrder.get(i);
            }
        }

        return null;
    }

    public static Provider findProvider(int id) {
        for (int i = 0; i < listProviders.size(); i++) {
            if (id == listProviders.get(i).getId()) {
                return listProviders.get(i);
            }
        }

        return null;
    }

    public static ProductImport findProductImport(int id) {
        for (int i = 0; i < listProductImports.size(); i++) {
            if (id == listProductImports.get(i).getId()) {
                return listProductImports.get(i);
            }
        }

        return null;
    }
 

    
    public static OrderDetail findOrderDetail(int id, ArrayList<OrderDetail> orderDetails) throws IOException {

        for (int i = 0; i < orderDetails.size(); i++) {
            if (id == orderDetails.get(i).getId()) {
                return orderDetails.get(i);
            }
        }

        return null;
    }

    
}
