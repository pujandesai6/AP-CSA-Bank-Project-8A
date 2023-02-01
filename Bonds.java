import java.util.Scanner;
import java.lang.Math;
import java.util.random.*;

public class Bonds{
    public static void main(String[] args){
        user user1 = new user("admin", "user", 123456, 123098, "Harshit", "Patel", "Male", 99, "idontexist@gmail.com", 100000.00, "none", "none", "none", "Donald", "none", "none", "none", 0);
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
        while(choice < 1 || choice > 2);
        if(choice == 1){
            int tdnumber = user1.getTDnumber();
            if(tdnumber == 0){
                System.out.println("Connecting with your bank account...");
                System.out.println("Registering your account...");
                System.out.println("Generating your account number...");
                System.out.println("Your Treasury Direct Account has been registered!");
                String strnumber = "" + (int)(Math.random() * 9) + 0;
                for(int i = 0; i <  11; i++){
                    strnumber += (int)(Math.random() * 9) + 0;
                }
                int number = Integer.parseInt(strnumber);
                System.out.println("Your Treasury Direct Account Number is: " + number);
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
        int last4digits = input.nextInt();
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
    private int tdnumber;

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
        tdnumber = 0;
    }

    public user(String a, String b, int c, int d, String e, String f, String g, int h, String i, double j, String k, String l, String m, String n, String o, String p, String q, int r){
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

    public int getTDnumber(){
        return tdnumber;
    }

    public void setTDnumber(int r){
        tdnumber = r;
    }
}
