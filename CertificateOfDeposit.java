import java.util.*;
import java.io.*;

//yall dumb af

public class CertificateOfDeposit {
    private static Scanner input = new Scanner(System.in);

    public static void codmain() throws IOException{
        System.out.println("Linking your account with FDIC...");
        System.out.println("--------------------");
        System.out.println("What would you like to do?");
        int choice = -1;
        while(choice < 0 || choice > 3){
            System.out.println("1. View Certificates of Deposit");
            System.out.println("2. Buy a Certificate of Deposit");
            System.out.println("3. Return Home");
            System.out.print("Enter Choice: ");
            choice = input.nextInt();
        }
        if(choice == 1){
            viewcod();
        }
        else if(choice == 2){
            buycod();
        }
    }
    
    public static void viewcod(){
    
    }

    public static void buycod() throws IOException{
        System.out.println("--------------------");
        System.out.print("Purpose of Certificate of Deposit (no spaces): ");
        String memo = input.next();

        System.out.print("Enter the length(years) for your Certificate of Deposit: ");
        int term = input.nextInt();

        double interest = Math.round(((double)(Math.random() * (term + 1)) + term)*100.00)/100.00;

        Boolean overDraft = false;
        double investment = 0.0;
        do{
            System.out.print("How much do you want to invest: $");
            investment = input.nextDouble();
            Transactions.amount = investment;
            overDraft = Transactions.overdraft();
        }
        while(overDraft == true);

        Double finalinvestment = Math.round(investment*Math.pow((1 + (interest/100)), term)*100.00)/100.00;

        System.out.println("--------------------");
        System.out.println("Please confirm your Certificate of Deposit Information");
        System.out.println("Name: " + Data.generaldata.get(2) + " " + Data.generaldata.get(3));
        System.out.println("Social Security Number: " + Data.generaldata.get(6));
        System.out.println("FDIC Number: " + Data.investordata.get(4));
        System.out.println("Purpose: " + memo);
        System.out.println("Term: " + term);
        System.out.println("Investment: " + investment);
        System.out.println("Interest: " + interest);
        System.out.println("Investment after Interest: $" + finalinvestment);
        System.out.println("--------------------");

        String codnumber = "";
        for(int i = 0; i < 6; i++){
            int number = (int)(Math.random() * 9) + 0;
            codnumber += number;
        }
        System.out.print("Confirm (y/n)?");
        String confirm = input.next();
        if(confirm.contains("y")){
            System.out.println("Confirming Your COD...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            // TODO Auto-generated catch block
                e.printStackTrace();
            }
            MoneyMarket.name = memo;
            MoneyMarket.typeOfInvestment = "Certificate of Deposit";
            MoneyMarket.investmentamount = investment;
            MoneyMarket.term = term;
            MoneyMarket.interest = interest;
            MoneyMarket.totalinvestment = finalinvestment;
            MoneyMarket.tradeable = "no";
            try {
                MoneyMarket.investments();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Your Certificate of Deposit was successful! Your COD number is " + codnumber);
            Transactions.investmenttype = "Certificate of Deposit";
            try {
                Transactions.investmentclass();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("--------------------");
        }
        else{
            System.out.println("Canceling your COD purchase...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Your COD purchase was canceled.");
            System.out.println("--------------------");
        }
    }
}

