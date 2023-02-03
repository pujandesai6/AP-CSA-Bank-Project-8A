import java.util.Scanner;
import java.lang.Math;
import java.util.random.*;

public class Bonds{
    public static void main(String[] args){
        user user1 = new user("admin", "user", 123456, "123098", "Harshit", "Patel", "Male", 99, "idontexist@gmail.com", 100000.00, "none", "none", "none", "Donald", "none", "none", "none", "none");
        System.out.println("Directing you to TreasuryDirect...");
        usermenu(user1);
    }

    public static void usermenu(user user1){
        Scanner input = new Scanner(System.in);
        int choice = 0;
        do{
            System.out.println("1. Create an account");
            System.out.println("2. Login");
            choice = input.nextInt();
        }
        while(choice == 1 || choice == 2);
        if(choice == 1){
            createaccount(user1);
        }
        else{
            login(user1);
        }
    }

    public static void createaccount(user user1){
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
            user1.setTDnumber(accountnumber);
            System.out.println("Your Treasury Direct Account Number is: " + accountnumber);
            System.out.println("Directing you back to TreasuryDirect Login...");
            login(user1);
        }
        else{
            System.out.println("You already have a Treasury Direct Account");
            System.out.println("Please login into your account");
            login(user1);
        }
    }

    public static void securitycheck(user user1){

    }

    public static void login(user user1){
        Scanner input = new Scanner(System.in);
        String accountnumber = input.next();
        boolean accnumbercheck = false;
        do{
            System.out.println("Please Enter Your Account Number: ");
        
        }   
        while(accnumbercheck = false);
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
    }

    public user(String a, String b, int c, String d, String e, String f, String g, int h, String i, double j, String k, String l, String m, String n, String o, String p, String q, String r){
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
}
