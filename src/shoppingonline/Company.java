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
import java.nio.file.NoSuchFileException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Company {

    public final static String NAME = "PNV22A";
    private static ArrayList<Position> positions;
    private static ArrayList<Employee> employees;
    private static ArrayList<Customer> customers;

    public static void setEmployees(ArrayList<Employee> employees) {
        Company.employees = employees;
    }

    public static void setCustomers(ArrayList<Customer> customers) {
        Company.customers = customers;
    }

    public ArrayList<Position> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<Position> positions) {
        Company.positions = positions;
    }

    public static void readData() throws FileNotFoundException, IOException {
        readDataFile(new File("Employee.txt"));
        readDataFile(new File("Customer.txt"));
        readDataFile(new File("Position.txt"));
    }

    public static void addCustomer(Customer customer) {
        customers.add(customer);
    }

    private static void readDataFile(File file)  {
        
       
            try {
                if (Files.isSameFile(file.toPath(), new File("Customer.txt").toPath())) {
                Scanner read;
                customers = new ArrayList<Customer>();
                read = new Scanner(file);
                while (read.hasNextLine()) {
                    int id = Integer.parseInt(read.nextLine());
                    String name = read.nextLine();
                    String email = read.nextLine();
                    String address = read.nextLine();
                    String phone = read.nextLine();
                    Date dateOfBirth = Format.DATE_FORMAT.parse(read.nextLine());
                    int loypoint = Integer.parseInt(read.nextLine());
                    customers.add(new Customer(id, name, email, address, phone, dateOfBirth, loypoint));
                }
                read.close();
            }

            } catch (Exception e) {
        }
     
            try {
                if (Files.isSameFile(file.toPath(), new File("Employee.txt").toPath())) {
                employees= new ArrayList<Employee>();
                Scanner read = new Scanner(file);
                while (read.hasNextLine()) {
                    int id = Integer.parseInt(read.nextLine());
                    String name = read.nextLine();
                    String email = read.nextLine();
                    String address = read.nextLine();
                    String phone = read.nextLine();
                    Date dateOfBirth = Format.DATE_FORMAT.parse(read.nextLine());
                    double salary= Double.parseDouble(read.nextLine());
                    employees.add(new Employee(id, name, email, address, phone, dateOfBirth, salary));
                }
                read.close();
            }
            } catch (Exception e) {
        }

        
            try {
                if (Files.isSameFile(file.toPath(), new File("Position.txt").toPath())) {
                Scanner read;
                positions = new ArrayList<Position>();
                read = new Scanner(file);
                while (read.hasNextLine()) {
                    ArrayList<Employee> listEmp = new ArrayList<Employee>();
                    int id = Integer.parseInt(read.nextLine());
                    String name = read.nextLine();
                    String desc = read.nextLine();
                    String list[] = read.nextLine().split(" ");
                    int ints[] = new int[list.length];
                    for (int i = 0; i < ints.length; i++) {
                        ints[i] = Integer.parseInt(list[i]);
                        Employee e = findEmployee(ints[i]);
                        listEmp.add(e);
                    }
                    positions.add(new Position(id, name, desc, listEmp));

                }
            read.close();
            }
            } catch (Exception e) {
        }
      
    }

    public static void writeDataFile(File file) throws IOException {
        if (Files.isSameFile(file.toPath(), new File("Customer.txt").toPath())) {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                for (int i = 0; i < customers.size(); i++) {
                    Customer customer = customers.get(i);
                    byte x[][] = new byte[7][];
                    x[0] = Integer.toString(customer.getId()).getBytes();
                    x[1] = customer.getName().getBytes();
                    x[2] = customer.getEmail().getBytes();
                    x[3] = customer.getAddress().getBytes();
                    x[4] = customer.getPhone().getBytes();
                    x[5] = Format.DATE_FORMAT.format(customer.getDateOfBirth()).getBytes();
                    x[6] = Integer.toString(customer.getLoyalPoint()).getBytes();
                    Store.write(x, fos);

                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (Files.isSameFile(file.toPath(), new File("Employee.txt").toPath())) {

            try {
                FileOutputStream fos = new FileOutputStream(file);
                for (int i = 0; i < employees.size(); i++) {
                    Employee employee = employees.get(i);
                    byte x[][] = new byte[7][];

                    x[0] = Integer.toString(employee.getId()).getBytes();
                    x[1] = employee.getName().getBytes();
                    x[2] = employee.getEmail().getBytes();
                    x[3] = employee.getAddress().getBytes();
                    x[4] = employee.getPhone().getBytes();
                    x[5] = Format.DATE_FORMAT.format(employee.getDateOfBirth()).getBytes();
                    x[6] = Double.toString(employee.getSalary()).getBytes();
                    Store.write(x, fos);

                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (Files.isSameFile(file.toPath(), new File("Position.txt").toPath())) {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                for (int i = 0; i < positions.size(); i++) {
                    Position position = positions.get(i);
                    byte x[][] = new byte[3][];
                    x[0] = Integer.toString(position.getId()).getBytes();
                    x[1] = position.getName().getBytes();
                    x[2] = position.getDesc().getBytes();
                    Store.write(x, fos);
                    for (int j = 0; j < position.getListEmp().size(); j++) {
                        byte id[] = Integer.toString(position.getListEmp().get(j).getId()).getBytes();
                        fos.write(id);
                        fos.write(' ');
                    }

                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(Company.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public static void writeData() throws IOException {
        writeDataFile(new File("Customer.txt"));
                writeDataFile(new File("Employee.txt"));
                        writeDataFile(new File("Position.txt"));

    }

    public static Employee findEmployee(int id) {
        for (int i = 0; i < employees.size(); i++) {
            if (id == employees.get(i).getId()) {
                return employees.get(i);
            }
        }
        return null;
    }

    public static String getNAME() {
        return NAME;
    }

    public static ArrayList<Employee> getEmployees() {
        return employees;
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static Customer findCustomer(int id) {
        for (int i = 0; i < customers.size(); i++) {
            if (id == customers.get(i).getId()) {
                return customers.get(i);
            }
        }
        return null;
    }
}
