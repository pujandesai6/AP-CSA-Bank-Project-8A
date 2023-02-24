import java.util.Scanner;
import java.io.*;
import javax.swing.text.DefaultStyledDocument.ElementSpec;

public class Loans {
    private static String loantype = "";

    public static void loansmain(){
        System.out.println("--------------------");
        Scanner input = new Scanner(System.in);
        System.out.println("Which loan would you like to apply for?");
        int choice = -1;
        while(choice < 0 || choice > 5){
            System.out.println("1. Personal Loan");
            System.out.println("2. Student Loan");
            System.out.println("3. Car Loan");
            System.out.println("4. Mortgage");
            System.out.println("5. Return Home");
            System.out.print("Enter Choice: ");
            choice = input.nextInt();
            System.out.println("--------------------");
        }
        if(choice == 1){
            loantype = "personal loan";
            loans();
        }
        if(choice == 2){
            loantype = "student loan";
            loans();
        }
        if(choice == 3){
            loantype = "car loan";
            loans();
        }
        if(choice == 4){
            loantype = "mortgage";
            loans();
        }
        else if(choice == 5){
            BankMain.menu();
        }
    }

    public static void loans(){
        Scanner input = new Scanner(System.in); //asks user for principle amount
        System.out.print("What is your requested " + loantype + " amount: ");
        double principleamount = input.nextDouble();
        System.out.println("--------------------");
 
        double downpayment = 0.0; //asks user for downpayement only if they selected car loans or mortgage
        if(loantype.equals("car loan") || loantype.equals("mortgage")){
            System.out.print("What is down payment: ");
            downpayment = input.nextDouble();
            System.out.println("--------------------");
        }

        double loanamount = Math.round((principleamount - downpayment)*100.00)/100.00; //gets loan amount

        System.out.print("Please enter your desired loan term (in years): "); //asks user for loan term
        int term = input.nextInt();
        System.out.println("--------------------");

        System.out.println("Enter your employment info"); //asks user for employment history
        System.out.print("Employer Name (do not put spaces): "); //employer name
        String employername = input.next();
        System.out.println("--------------------");

        System.out.print("Your Salary: "); //their salary
        Double salary = input.nextDouble();

        System.out.println("--------------------");

        double interest = 0.0;
        if(loantype.equals("student loan")){ //calculates interest // if its student loan then its 0
            interest = 0;
        }
        else{
            interest = Math.round(((double)(Math.random() * 6) + 0)*100.00)/100.00;
        }


        double interestAmount = Math.round(((double)(loanamount * term * interest)/100.00)*100.00)/100.00;
        double finalloanamount = Math.round(((double)((interestAmount + loanamount * 100.00)/100.00)));
        double monthlypayment = Math.round((double)(finalloanamount/(term*12))*100.00)/100.00;

        System.out.println("--------------------");
        System.out.println("Please confirm your loan information.");
        System.out.println("Full Name: " + Data.generaldata.get(2) + " " +  Data.generaldata.get(3));
        System.out.println("Social Security Number: " + Data.generaldata.get(6));
        System.out.println("Type of Loan: " + loantype);
        if(loantype.equals("car loan") || loantype.equals("mortgage")){
            System.out.println("Principle Amount: $" + principleamount);
            System.out.println("Down Payment: $" + downpayment);
            System.out.println("Loan Amount: $" + loanamount);
        }
        else{
            System.out.println("Loan Amount: $" + principleamount);
        }
        System.out.println("Term: " + term + " Years");
        System.out.println("Employer Name: " + employername);
        System.out.println("Salary: $" + salary);
        System.out.println("Interest Rate: " + interest + "%");
        System.out.println("Interest Amount: $" + interestAmount);
        System.out.println("Total Loan Amount: $" + finalloanamount);
        System.out.println("Total Monthly Payment: $" + monthlypayment);
        System.out.println("--------------------");
        System.out.print("Confirm (y/n)? ");
        String confirm = input.next();
        if(confirm.contains("y")){
            System.out.println("Confirming Your Loan...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Your loan request has been placed. Please check your email -- " + Data.generaldata.get(7) + " --for updates of this loan.");
            Data.typeOfAccount = "Savings";
            Transactions.amount = String.valueOf(principleamount);
            Transactions.typeOfTransaction = 5;
            Transactions.memo = "Foriegn Exchange";
            try {
                Transactions.transactions();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Your loan request has been canceled.");
            BankMain.bankmenu();
        }
    }
}
