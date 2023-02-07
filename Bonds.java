import java.util.Scanner;
import java.lang.Math;
import java.util.random.*;
import java.io.*;

public class Bonds{
    public static void main(String[] args){
        user user1 = new user("admin", "user", 123456, "123098", "Harshit", "Patel", "Male", 99, "idontexist@gmail.com", 100000.00, "none", "none", "none", "Donald", "none", "none", "none", "none", 0);
        Bond bond1 = new Bond("", "", "", "", "", "", 0.0, "");
        System.out.println("Directing you to TreasuryDirect...");
        login(user1);
    }

    public static void login(user user1){
        Scanner input = new Scanner(System.in);
        int choice = 0;
        do{
            System.out.println("1. Create an account");
            System.out.println("2. Login");
            choice = input.nextInt();
        }
        while(choice == 1 || choice == 2);
        if(choice == 1){
            String tdnumber = user1.getTDnumber();
            if(tdnumber.equals("none")){
                System.out.println("Connecting with your bank account...");
                System.out.println("Registering your account...");
                System.out.println("Generating your account number...");
                System.out.println("Your Treasury Direct Account has been registered!");
                String accountnumber = "" + (int)(Math.random() * 9) + 0;
                for(int i = 0; i <  10; i++){
                    accountnumber += (int)(Math.random() * 9) + 0;
                }
                System.out.println("Your Treasury Direct Account Number is: " + accountnumber);
            }
            else{
                System.out.println("You already have a Treasury Direct Account");
                System.out.println("Please login into your account");
                login(user1);
            }
            
        }
        if(choice == 2){
            System.out.println("");
        }
        System.out.println("Enter the four digits of you Social Security Number: ");
        String last4digits = input.next();
        String ssnumber = user1.getSSNumber();
        int index = ssnumber.length();
        ssnumber = ssnumber.substring(index - 4, index);
        if(last4digits.equals(ssnumber)){
            System.out.println("works");
        }
        else{
            int i = 0;
            Boolean matches = false;
            do{
                System.out.println("The last 4 digits do not match with you Social Security Number. Please Try Again. You have " + (3 - i) + " remaining." );
                i++;
                System.out.println("Enter the four digits of you Social Security Number: ");
                ssnumber = input.next();
                if(last4digits.equals(ssnumber)){
                    matches = true;
                }
            }
            while(i < 3 || matches == true);
            if(matches == true){
                TDMenu(user1);
            }
        }
    }

    public static void TDMenu(user user1){
        Scanner input = new Scanner(System.in);
        int choice = 0;
        do{
            System.out.println("1. Manage Bonds");
            System.out.println("2. Buy Bonds");
            choice = input.nextInt();
        }
        while(choice == 1 || choice == 2);
        if(choice == 1){
            
            managebonds(user1);
        }
        else{
            buybonds(user1);
        }
    }

    public static void managebonds(user user1){

    }

    public static void buybonds(user user1){
        user1.setBondnumber(user1.getBondnumber()+1);
        Scanner input = new Scanner(System.in);
        int choice = 0;
        do{
            System.out.print("Type of Owner:");
            System.out.println("1. Sole Owner");
            System.out.print("2. Primary Owner:");
            System.out.print("3. Beneficiary");
            choice = input.nextInt();
        }
        while(choice == 1 || choice == 2 || choice == 3);
        if(choice == 1){
            
        }
        else if(choice == 2){
            
        }
        else{
            
        }
        
    }
}

class user{
    private String username;
    private String password;
    private int accountnumber;
    private String socialsecurity;
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
    private String tdnumber;
    private int numberofbonds;

    public user(){
        username = "";
        password = "";
        accountnumber = 0;
        socialsecurity = "";
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
        tdnumber = "";
        numberofbonds = 0;
    }

    public user(String a, String b, int c, String d, String e, String f, String g, int h, String i, double j, String k, String l, String m, String n, String o, String p, String q, String r, int s){
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
        tdnumber = r;
        numberofbonds = s;
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

    public String getBalance(){
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

    public String getTDnumber(){
        return tdnumber;
    }

    public void setTDnumber(int r){
        tdnumber = r;
    }
    
    public String getSSNumber(){
        return socialsecurity;
    }

    public int getBondnumber(){
        return numberofbonds;
    }

    public void setBondnumber(int s){
        numberofbonds = s;
    }
}

class Bond{
    private String type;
    private String firstname;
    private String middleinitial;
    private String lastname;
    private String suffix;
    private String TIN;
    private double amount;
    private String sourceofunds;

    public Bond(){
        type = "";
        firstname = "";
        middleinitial = "";
        lastname = "";
        suffix = "";
        TIN = "";
        amount = 0.0;
        sourceofunds = "";
    }

    public Bond(String a, String b, String c, String d, String e, String f, double g, String h){
        type = a;
        firstname = b;
        middleinitial = c;
        lastname = d;
        suffix = e;
        TIN = f;
        amount = g;
        sourceoffunds = h;
    }
}
