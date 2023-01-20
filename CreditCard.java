import java.util.Scanner;
import java.lang.Math;

public class CreditCard{
    public static void main(String[] args){
        user user1 = new user("admin", "user", 123456, 123098, "John", "Doe", "Male", 99, "idontexist@gmail.com", 100000.00, "none", "none", "none");
        choices(user1);
        }

    public static void choices(user user1){
        Scanner input = new Scanner(System.in);
        System.out.println("1: Credit Card");
        System.out.println("2: Debit Card");
        System.out.println("3: Credit Card Statement");
        System.out.print("Enter option: ");

        int choice = input.nextInt();
        if(choice == 1){
            stringconverter(user1);
        }
        if(choice == 2){
            stringconverter(user1);
        }
        if(choice == 3){
            statement(user1);
        }
    }
    public static void statement(user user1){
        int transactions = 4;
        int balance = user1.getBalance();
        System.out.println("Recent Transactions");
        System.out.println("");
        System.out.println("|     Type       |      Paid In      |      Paid Out      |      Balance      |");
        System.out.println("|----------------|-------------------|--------------------|-------------------|");
        for(int i = 0; i < transactions; i++){
            System.out.println("|" + type + "|" + paidin + "|" + paidout + "|" + balance +"|");
            System.out.println("|----------------|-------------------|--------------------|-------------------|");
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Press 1 to return home");
        System.out.println("Press 2 to sign out");
        int choice = input.nextInt();
        if(choice == 1){
            choices(user1);
        }
        else if(choice == 2){
            return;
        }
    }

    public static void stringconverter(user user1){
        String numberstring = user1.getNumber();
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
            user1.setNumber(numberstring);
            expconverter(user1, numberstring);
        }
        else{
            expconverter(user1, numberstring);
        }
    }
        //Generates card number and exp date if not present already)
    public static void expconverter(user user1, String numberstring){
        String expstring = user1.getExpDate();
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
            user1.setExpDate(expstring);
            //creditcard(user1, numberstring, expstring);
            cvvgenerator(user1, numberstring, expstring);
        }
        else{
            cvvgenerator(user1, numberstring, expstring);
        }
    }
        //Generates CVV if not present
    public static void cvvgenerator(user user1, String numberstring, String expstring){
        String cvv = user1.getCvv();
        if(cvv.equals("none")){
            cvv = " ";
            for(int i = 0; i < 3; i++){
                int number = (int)(Math.random() * 9);
                cvv += number + " ";
            }
            user1.setCvv(cvv);
            creditcard(user1, numberstring, expstring, cvv);
        }
        else{
            creditcard(user1, numberstring, expstring, cvv);
        }
    }

        //Prints Credit Card with users numbers
    public static void creditcard(user user1, String numberstring, String expstring, String cvv){
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
                System.out.println("|:::[" + numberstring + "]:::::::::::::|");
            }
            if(i == 11){
                System.out.println("|:::::::::::::::::::::::::::::::::::[EXPIRES]:[" + expstring + "]::|");
            }
            if(i == 12){
                System.out.println("|:::[J o h n    D    S m i t h]:::::[END]::::::::::::::::::|");
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
                System.out.println("|                                          |::::|" + cvv + "|::|");
            }
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Press 1 to return home");
        System.out.println("Press 2 to sign out");
        int choice = input.nextInt();
        if(choice == 1){
            choices(user1);
        }
        else if(choice == 2){
            return;
        }
    }

    public static void debitcard(user user1, String numberstring, String cvv){
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
                System.out.println("|:::[" + numberstring + "]:::::::::::::|");
            }
            if(i == 11){
                System.out.println("|:::::::::::::::::::::::::::::::::::[EXPIRES]:[0 3 / 1 2]::|");
            }
            if(i == 12){
                System.out.println("|:::[J o h n    D    S m i t h]:::::[END]::::::::::::::::::|");
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
                System.out.println("|                                          |::::| 9 8 8 |::|");
            }
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Press 1 to return home");
        System.out.println("Press 2 to sign out");
        int choice = input.nextInt();
        if(choice == 1){
            choices(user1);
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
    private String expdate;
    private String cvvnumber;

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
        expdate = "";
        cvvnumber = "";
    }

    public user(String a, String b, int c, int d, String e, String f, String g, int h, String i, double j, String k, String l, String m){
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
        expdate = l;
        cvvnumber = m;
    }

    public String getNumber(){
        return creditcardnumber;
    }

    public void setNumber(String k){
        creditcardnumber = k;
    }

    public String getExpDate(){
        return expdate;
    }

    public void setExpDate(String l){
        expdate = l;
    }

    public String getCvv(){
        return cvvnumber;
    }

    public void setCvv(String m){
        cvvnumber = m;
    }

    public String getBalance(){
        return balance;
    }

    public void setBalance(double j){
        balance = j;
    }
}
