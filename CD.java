import java.util.Scanner;

public class CD{
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args){
        
        
        System.out.println();
        System.out.println("Hello! Welcome to the Bank of Cangerizzi Certificate of Deposit or \"CD\".\n1.Open a Certificate of Deposit\n2.Help Me (What is a CD?)\n3.Exit");
        String ent = input.next();
        int i = 0;
        do{
            
        if(ent.equals("1"))
        {
            System.out.println("Great! Our annual interest rate is 4%");
            System.out.println("How much money would you like to pledge to the Certificate of Deposit? (Balance must be atleast $1000)");
            double amount = input.nextDouble();
            double annualInterest = 0.04;
            if (amount > 1000)
            {
                System.out.println("Great, you will pledge $" + amount + " We can provide a contract that last for 3, 6, and 12 months.\nWhich option would be suitable for you?\n1. 3 Months\n2. 6 Months\n3. 12 Months");
                int months = input.nextInt();
                if(months == 1)
                {
                    System.out.println("The contract will be for 3 months. The balance will be $" + amount + ". The interest rate will be 1%.");
                    double thrmonthpercent = 0.01;
                    double thrmonthapp = thrmonthpercent*amount;
                    double thrtotinterest = thrmonthapp + amount;
                    System.out.println("Your predicted total balance value will amount to $" + thrtotinterest);
                    break;
                }
                if (months == 2)
                {
                    System.out.println("The contract will be for 6 months. The balance will be $" + amount + ". The interest rate will be 2%.");
                    double sixmonthpercent = 0.02;
                    double sixmonthapp = sixmonthpercent*amount;
                    double sixtotinterest = sixmonthapp + amount;
                    System.out.println("Your predicted total balance value will amount to $" + sixtotinterest);
                    break;
                }
                if (months == 3)
                {
                    System.out.println("The contract will be for 12 months. The balance will be $" + amount + ". The interest rate will be 4%.");
                    double twelmonthpercent = 0.04;
                    double twelmonthapp = twelmonthpercent*amount;
                    double tweltotinterest = twelmonthapp + amount;
                    System.out.println("Your predicted total balance value will amount to $" + tweltotinterest);
                    break;
                }
                            
                   
          
                
            }
            else{ System.out.println("Sorry, you didnt put enough money into the deposit.");
                System.out.println("How much money would you like to pledge to the Certificate of Deposit? (Balance must be atleast $1000)");
                }
        }
        else if (ent.equals("2"))
        {
            System.out.println("A certificate of deposit is a deposit where, you the customer, will not be able to access the assets or securities in this account until the date, that will be negotiated on, is reached.\nBy keeping your money in this account, your money will have our annual interest rate applied.");
            System.out.println("1.Let's Do It\n2.Not Today");
            ent = input.next();
        }
        else if(ent.equals("3"))
        {
            System.out.println("Goodbye!");
        }
        i++;
        }
        while (i<=1);
    }
}