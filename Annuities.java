import java.util.*;
import java.lang.*;
import java.io.*;


public class Annuities {
    private static Scanner input = new Scanner(System.in);
    public static void annuitiesmain() throws IOException{
        System.out.println("--------------------");
        System.out.println("What would like to name this annuity? ");
        String name = input.next();
        System.out.println("--------------------");
        System.out.println("Here are our insurance investment options:");
        System.out.println("1. Insurance 1:");
        System.out.println(" - Rate (Interest rate) - 4%");
        System.out.println(" - Years (Time for payout) - 1 year");
       
        System.out.println();
       
        System.out.println("2. Insurance 2:");
        System.out.println(" - Rate (Interest rate) - 5.15%");
        System.out.println(" - Years (Time for payout) - 1 year");

        System.out.println();
       
        System.out.println("3. Insurance 3:");
        System.out.println(" - Rate (Interest rate) - 3.8%");
        System.out.println(" - Years (Time for payout) - 2 years");

        System.out.println("");
        int choice = -1;
        while(choice < 0 || choice > 3){
            System.out.print("Please enter the number of the insurance option you would like to pick: ");
            choice = input.nextInt();
            System.out.println("--------------------");
        }

        Boolean overDraft = false;
        double investment = 0.0;
        do{
            System.out.print("How much do you want to invest: $");
            investment = input.nextDouble();
            Transactions.amount = investment;
            overDraft = Transactions.overdraft();
        }
        while(overDraft == true);

        Double finalinvestment = 0.0;
        int term = 0;
        double interest = 0.0;
        if(choice == 1){
            term = 1;
            interest = 4;
            finalinvestment = Math.round((investment * Math.pow((1 + .04), 1))*100.00)/100.00;
        }
        else if(choice == 2){
            term = 1;
            interest = 5.15;
            finalinvestment = Math.round((investment * Math.pow((1 + .0515), 1))*100.00)/100.00;
        }
        else{
            term = 2;
            interest = 3.8;
            finalinvestment = Math.round((investment * Math.pow((1 + .038), 2))*100.00)/100.00;
        }


        System.out.print("Confirm (y/n)?");
        String confirm = input.next();
        if(confirm.contains("y")){
            System.out.println("Confirming Your Investment...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Your investment was successful! Your investment will be worth $" + finalinvestment + " after the term is over.");
            MoneyMarket.name = name;
            MoneyMarket.typeOfInvestment = "Annuities";
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

            Transactions.investmenttype = "Annuity";
            try {
                Transactions.investmentclass();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("--------------------");
        }
        else{
            System.out.println("Canceling your investment...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Your investment was canceled.");
            System.out.println("--------------------");
            InvestorsAccount.investormenu();
        }
    }
}