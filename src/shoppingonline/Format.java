/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingonline;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Administrator
 */
public class Format {

    public final static Pattern USER__PATTERN = Pattern.compile("[a-z0-9_-]{3,12}$");
    public final static Pattern PASS__PATTERN = Pattern.compile("[a-z0-9_-]{3,12}$");
    public final static Pattern DATE_PATTERN = Pattern.compile("\\d{2}[-|/]\\d{2}[-|/]\\d{4}");
    public final static Pattern NAME = Pattern.compile("[a-zA-Z ]{1,19}$");
    public final static Pattern EMAIL = Pattern.compile("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
    public final static Pattern PHONE = Pattern.compile("^0\\d{9}$");
        public final static Pattern NUMBER = Pattern.compile("[0-9]{1}$");

    public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    
    private Format(){
        
    }
    public static Date enterDate() throws ParseException {
        java.util.Date date;
        Scanner sc = new Scanner(System.in);
        String date1;
        java.util.Date date2 = new java.util.Date();
        java.util.Date date3=    DATE_FORMAT.parse("01/01/1900");
        while (true) {
            System.out.println("Enter a date: ");
            date1 = sc.nextLine();
            if (!Format.DATE_PATTERN.matcher(date1).matches()) {
                System.err.println("Format must be dd/mm/yyyy)");
                continue;
            }
            date = Format.DATE_FORMAT.parse(date1);
            if (date.after(date2)) {
                System.err.println("The date must be less than the current date");
                continue;
            }
             if (date.before(date3)) {
                System.err.println("The date must be greater than 01/01/1900)");
                continue;
            }
            break;
        }
        return date;
    }

    public static String enterName() {
        String name;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your name: ");
            name = scanner.nextLine();
            if (!NAME.matcher(name).matches()) {
                System.err.println("Name must be a-z or A-Z");
                continue;
            }
            break;
        }
        return name;
    }

    public static String enterEmail() {
        String email;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your email: ");
            email = scanner.nextLine();
            if (!EMAIL.matcher(email).matches()) {
                System.err.println(email + ":  Incorrect email format (ex:long.nguyen22@gmail.com) ");
                continue;
            }
            break;
        }
        return email;
    }

    public static String enterPhone() {
        String phone;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your phone");
            phone = scanner.nextLine();
            if (!PHONE.matcher(phone).matches()) {
                System.err.println(phone + ":  Incorrect phone format (ex:0988888888) ");
                continue;
            }
            break;
        }
        return phone;
    }
}


