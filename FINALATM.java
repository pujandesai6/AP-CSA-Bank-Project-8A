import java.util.*;
import java.io.IOException;


public class ATM {
    public static void access(){
        Data.method = "ATM";
        Scanner input = new Scanner(System.in);
        int choice = 0;
        System.out.println("What type of card will you be using today?");
        do{
            System.out.println("1. Debit");
            System.out.println("2. Credit");
            System.out.print("Enter choice: ");
            choice = input.nextInt();
            System.out.println("--------------------");
        }
        while(choice < 0 || choice > 2);

        if(choice == 1){
            choice = 5;
        }
        else{
            choice = 6;
        }   

        Boolean cardValid = false;
        String cardnumber = "";
        do{
            System.out.print("Enter your card number: ");
            cardnumber = input.next();
            System.out.println("--------------------");
            cardValid = numberValidate(cardnumber,choice);
        }
        while(cardValid == false);
        
        int threshold = 0;
        boolean Valid = false;
        String pin = "";
        do{
            System.out.print("Enter your pin: ");
            pin = input.next();
            System.out.println("--------------------");
            Valid = Validate(cardnumber, choice, pin, threshold);
            threshold++;
        }
        while(Valid == false && threshold < 4);

        if(threshold == 4){
            System.out.print("Please wait a few seconds and then try again!");
            System.out.println("--------------------");
            ATM.access();
        }
        if(Valid == true){
            Transactions.ATM();
        }
    }

    public static boolean numberValidate(String cardnumber, int choice){
        boolean valid = true;
        try {
            Data.customerarray(cardnumber, choice);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(Data.customerdata.size() == 0){
            System.out.println("Invalid Card Number! Please try again.");
            valid = false;
        }
        else{
            try {
                Data.card(Data.customerdata.get(0), 0);
                Data.generalarray(Data.customerdata.get(0), 0);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return valid;
    }

    public static boolean Validate(String cardnumber, int choice, String pin, int threshold){
        if(cardnumber.equals(Data.customerdata.get(choice)) && pin.equals(Data.ATMarray.get(6))){
            return true;
        }
        else{
            int triesremaining = 3 - threshold; //caluclates tries remaining
            System.out.println("Pin number is wrong! You have " + triesremaining + " try(s) remaining before your card gets suspended!");
            return false;
        }
    }
}
