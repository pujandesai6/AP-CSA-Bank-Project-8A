import java.util.Scanner;
import java.lang.Math;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class CreditCard{
    public static void main(String[] args){
        user user1 = new user("admin", "user", 123456, 123098, "John", "Doe", "Male", 99, "idontexist@gmail.com", 100000.00, "none", "none", "none", "Edward", "none", "none", "none");
        variables(user1);
    }

    public static void variables(user user1){
        String name = name(user1);
        String creditcardnumber = numbergenerator(user1, 0);
        String debitcardnumber = numbergenerator(user1, 1);
        String creditcardexp = expgenerator(user1, 0);
        String debitcardexp = expgenerator(user1, 1);
        String creditcardcvv = cvvgenerator(user1, 0);
        String debitcardcvv = cvvgenerator(user1, 1);
        choices(user1, name, creditcardnumber, debitcardnumber, creditcardexp, debitcardexp, creditcardcvv, debitcardcvv);
    }

    public static void choices(user user1, String name, String creditcardnumber, String debitcardnumber, String creditcardexp, String debitcardexp, String creditcardcvv, String debitcardcvv){
        Scanner input = new Scanner(System.in);
        System.out.println("1. Credit Card");
        System.out.println("2. Debit Card");
        System.out.println("3. Credit Card Statement");
        System.out.print("Enter option: ");
        int choice = input.nextInt();
        if(choice == 1){
            creditcard(user1, name, creditcardnumber, creditcardexp, creditcardcvv);
        }
        if(choice == 2){
            debitcard(user1, name, debitcardnumber, debitcardexp, debitcardcvv);
        }
        if(choice == 3){
            statement(user1);
        }
    }

    public static String name(user user1){
        String firstname = user1.getFirstName();
        String lastname = user1.getLastName();
        String middlename = user1.getMiddileInitial();
        String name = "[" + firstname.charAt(0);
        for(int i = 1; i < firstname.length(); i++){
            name += " " + firstname.charAt(i);
        }
        name += "    ";
        if(middlename.equals("none")){
            name += "";
        }
        else{
            name += "" + middlename.charAt(0);
            name += "   ";
        }
        for(int i = 0; i < lastname.length(); i++){
            name += " " + lastname.charAt(i);
        }
        name += "]";
        if(name.length() == 30){
            return name;
        }
        if(name.length() < 30){
            int extrachar = 30 - name.length();
            for(int i = 0; i < extrachar; i++){
                name += ":";
            }
            return name;
        }
        if(name.length() > 27){
            int excess =  name.length() - 27;
            System.out.println(excess);
            if(excess < 4)
            {
                excess = 3 - excess;
                for(int i = 0; i < excess; i++){
                    name += ":";
                }
            }
            else{
                name = "[" + firstname.charAt(0);
                for(int i = 1; i < firstname.length(); i++){
                name += " " + firstname.charAt(i);
                }
                name += "   ";
                for(int i = 0; i < lastname.length(); i++){
                    name += " " + lastname.charAt(i);
                }
                name += "]";
                excess =  name.length() - 27;
                if(excess < 4)
                {   
                    excess = 3 - excess;
                    for(int i = 0; i < excess; i++){
                        name += ":";
                    }
                }
            }
            return name;
        }
        return name;
    }

    public static String numbergenerator(user user1, int a){
        String numberstring = "";
        if(a == 0){
            numberstring = user1.getCreditCardNumber();
        }
        else{
            numberstring = user1.getDebitCardNumber();
        }
        if(numberstring.equals("none")){
            numberstring = "";
            for(int i = 0; i < 4; i++){
                int number = (int)(Math.random() * 9) + 0;
                numberstring += number + " ";
            }
            numberstring+= "   ";
            for(int i = 0; i < 4; i++){
                int number = (int)(Math.random() * 9) + 0;
                numberstring += number + " ";
            }
            numberstring+= "   ";
            for(int i = 0; i < 4; i++){
                int number = (int)(Math.random() * 9) + 0;
                numberstring += number + " ";
            }
            numberstring+= "   ";
            for(int i = 0; i < 3; i++){
                int number = (int)(Math.random() * 9) + 0;
                numberstring += number + " ";
            }
            int number = (int)(Math.random() * 9) + 0;
            numberstring += number;
            if(a == 0){
                user1.setCreditCardNumber(numberstring);
            }
            else{
                user1.setDebitCardNumber(numberstring);
            }
            return numberstring;
        }
        else{
            return numberstring;
        }
    }
    public static String expgenerator(user user1, int a){
        String expstring = "";
        if(a == 0){
            expstring = user1.getCreditCardExp();
        }
        else{
            expstring = user1.getDebitCardExp();
        }
        if(expstring.equals("none")){
            expstring = "";
            int number = (int)(Math.random() * 1) + 0;
            if(number == 0){
                expstring += 0 + " " + ((int)(Math.random() * 8) + 1) + " ";
            }
            else{
                expstring += 1 + ((int)(Math.random() * 2) + 0) + " ";
            }
            number = (int)(Math.random() * 4) + 5;
            expstring += "/ 2 " + number;
            if(a == 0){
                user1.setCreditCardExp(expstring);
            }
            else{
                user1.setDebitCardExp(expstring);
            }
            return expstring;
        }
        else{
            return expstring;
        }
    }
        //Generates CVV if not present
    public static String cvvgenerator(user user1,int a){
        String cvv = "";
        if(a == 0){
            cvv = user1.getCreditCardCvv();
        }
        else{
            cvv = user1.getDebitCardCvv();
        }
        if(cvv.equals("none")){
            cvv = " ";
            for(int i = 0; i < 3; i++){
                int number = (int)(Math.random() * 9);
                cvv += number + " ";
            }
            if(a == 0){
                user1.setCreditCardCvv(cvv);
            }
            else{
                user1.setDebitCardCvv(cvv);
            }
            return cvv;
        }
        else{
            return cvv;
        }
    }

    public static void statement(user user1){
        /*int transactions = 4;
        String[] types = {"Withdrawl", "Deposit", "Transfer"};
        double balance = user1.getBalance();
        double paidin = 101.25;
        double paidout = 12333.24;
        System.out.println("Recent Transactions");
        System.out.println("");
        System.out.println("|     Type       |      Paid In      |      Paid Out      |      Balance      |");
        System.out.println("|----------------|-------------------|--------------------|-------------------|");
        for(int i = 0; i < transactions; i++){
            String type = types[(int)(Math.random() * 2)];
            System.out.println("|" + type + "|" + paidin + "|" + paidout + "|" + balance +"|");
            System.out.println("|----------------|-------------------|--------------------|-------------------|");
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Press 1 to return home");
        System.out.println("Press 2 to sign out");
        int choice = input.nextInt();
        if(choice == 1){
            variables(user1);
        }
        else if(choice == 2){
            return;
        }*/
        JFrame frame = new JFrame();
 
        String[] columnNames = {"Date", "Type", "Paidin "};
 
        Object[][] data = {
            {"Ken", new Integer(5), new Boolean(false)},
            {"Tom", new Integer(3), new Boolean(true)},
            {"Susam", new Integer(2), new Boolean(false)},
            {"Mark",new Integer(20), new Boolean(true)},
            {"Joe", new Integer(10), new Boolean(false)}
        };
        JTable table = new JTable(data, columnNames);
        frame.add(table);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);  
        frame.setVisible(true);
        System.out.println("Statement has been opened in a new tab.");
        variables(user1);
    }   
        //Prints Credit Card with users numbers
    public static void creditcard(user user1, String name, String creditcardnumber, String creditcardexp, String creditcardcvv){
        for(int i = 0; i < 14; i++){
            if(i == 0 || i == 3 || i == 4 || i == 8 || i == 10 || i == 13){
                System.out.println("|::::::::::::::::::::::::::::::::::::::::::::::::::::::::::|");
            }
            if(i == 1){
                System.out.println("|:::[ B A N K   O F ]:::::::::::::::::::::[ C R E D I T ]::|");
            }
            if(i == 2){
                System.out.println("|:::[ V I T O   C A N G E R I Z Z I ]:::::::::[ C A R D ]::|");
            }
            if(i == 5 || i == 6){
                System.out.println("|:::|########|:::::::::::::::::::::::::::::::::::::|####|::|");
            }
            if(i == 7){
                System.out.println("|:::|########|:::::::::::::::::::::::::::::::::::::::::::::|");
            }
            if(i == 9){
                System.out.println("|:::[" + creditcardnumber + "]:::::::::::::|");
            }
            if(i == 11){
                System.out.println("|:::::::::::::::::::::::::::::::::::[EXPIRES]:[" + creditcardexp + "]::|");
            }
            if(i == 12){
                System.out.println("|:::" + name + "::[END]::::::::::::::::::|");
            }
        }

        for(int j = 0; j < 4; j++){
            System.out.println("");
        }

        for(int k = 0; k < 14; k++){
            if(k == 0 || k == 4 || k == 5 || k == 8 || k == 9 || k == 10 || k == 11 || k == 12){
                System.out.println("|::::::::::::::::::::::::::::::::::::::::::::::::::::::::::|");
            }
            if(k == 1 || k == 2 || k == 3){
                System.out.println("|NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN|");
            }
            if(k == 6){
                System.out.println("|                                          |::::| C V V |::|");
            }
            if(k == 7){
                System.out.println("|                                          |::::|" + creditcardcvv + "|::|");
            }
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Press 1 to return home");
        System.out.println("Press 2 to sign out");
        int choice = input.nextInt();
        if(choice == 1){
            variables(user1);
        }
        else if(choice == 2){
            return;
        }
    }

    public static void debitcard(user user1, String name, String debitcardnumber, String debitcardexp, String debitcardcvv){
        for(int i = 0; i < 14; i++){
            if(i == 0 || i == 3 || i == 4 || i == 8 || i == 10 || i == 13){
                System.out.println("|::::::::::::::::::::::::::::::::::::::::::::::::::::::::::|");
            }
            if(i == 1){
                System.out.println("|:::[ B A N K   O F ]:::::::::::::::::::::::[ D E B I T ]::|");
            }
            if(i == 2){
                System.out.println("|:::[ V I T O   C A N G E R I Z Z I ]:::::::::[ C A R D ]::|");
            }
            if(i == 5 || i == 6){
                System.out.println("|:::|########|:::::::::::::::::::::::::::::::::::::|####|::|");
            }
            if(i == 7){
                System.out.println("|:::|########|:::::::::::::::::::::::::::::::::::::::::::::|");
            }
            if(i == 9){
                System.out.println("|:::[" + debitcardnumber + "]:::::::::::::|");
            }
            if(i == 11){
                System.out.println("|:::::::::::::::::::::::::::::::::::[EXPIRES]:[" + debitcardexp + "]::|");
            }
            if(i == 12){
                System.out.println("|:::" + name + "::[END]::::::::::::::::::|");
            }
        }

        for(int j = 0; j < 4; j++){
            System.out.println("");
        }

        for(int k = 0; k < 14; k++){
            if(k == 0 || k == 4 || k == 5 || k == 8 || k == 9 || k == 10 || k == 11 || k == 12){
                System.out.println("|::::::::::::::::::::::::::::::::::::::::::::::::::::::::::|");
            }
            if(k == 1 || k == 2 || k == 3){
                System.out.println("|NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN|");
            }
            if(k == 6){
                System.out.println("|                                          |::::| C V V |::|");
            }
            if(k == 7){
                System.out.println("|                                          |::::|" + debitcardcvv + "|::|");
            }
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Press 1 to return home");
        System.out.println("Press 2 to sign out");
        int choice = input.nextInt();
        if(choice == 1){
            variables(user1);
        }
        else if(choice == 2){
            return;
        }
    }
}

class user{
    private String username;
    private String password;
    private int accountnumber;
    private int socialsecurity;
    private String firstname;
    private String lastname;
    private String gender;
    private int age;
    private String email;
    private double balance;
    private String creditcardnumber;
    private String creditcardexp;
    private String creditcardcvv;
    private String middleinitial;
    private String debitcardnumber;
    private String debitcardexp;
    private String debitcardcvv;

    public user(){
        username = "";
        password = "";
        accountnumber = 0;
        socialsecurity = 0;
        firstname = "";
        lastname = "";
        gender = "";
        age = 0;
        email = "";
        balance = 0;
        creditcardnumber = "";
        creditcardexp = "";
        creditcardcvv = "";
        middleinitial = "";
        debitcardnumber = "";
        debitcardexp = "";
        debitcardcvv = "";
    }

    public user(String a, String b, int c, int d, String e, String f, String g, int h, String i, double j, String k, String l, String m, String n, String o, String p, String q){
        username = a;
        password = b;
        accountnumber = c;
        socialsecurity = d;
        firstname = e;
        lastname = f;
        gender = g;
        age = h;
        email = i;
        balance = j;
        creditcardnumber = k;
        creditcardexp = l;
        creditcardcvv = m;
        middleinitial = n;
        debitcardnumber = o;
        debitcardexp = p;
        debitcardcvv = q;
    }

    public String getCreditCardNumber(){
        return creditcardnumber;
    }

    public void setCreditCardNumber(String k){
        creditcardnumber = k;
    }

    public String getDebitCardNumber(){
        return debitcardnumber;
    }

    public void setDebitCardNumber(String o){
        debitcardnumber = o;
    }

    public String getCreditCardExp(){
        return creditcardexp;
    }

    public void setCreditCardExp(String l){
        creditcardexp = l;
    }

    public String getDebitCardExp(){
        return debitcardexp;
    }

    public void setDebitCardExp(String p){
        debitcardexp = p;
    }
    
    public String getCreditCardCvv(){
        return creditcardcvv;
    }

    public void setCreditCardCvv(String m){
        creditcardcvv = m;
    }

    public String getDebitCardCvv(){
        return debitcardcvv;
    }

    public void setDebitCardCvv(String q){
        debitcardcvv = q;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double j){
        balance = j;
    }

    public String getFirstName(){
        return firstname;
    }

    public String getLastName(){
        return lastname;
    }

    public String getMiddileInitial(){
        return middleinitial;
    }
}
