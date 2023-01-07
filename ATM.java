import java.util.Scanner;

public class ATM
{    
    static Scanner input = new Scanner(System.in);
    static private String cardNumber = "1234567891012131";
    static private int Pin = 1234;
    static double add10 = 0;
    static double add20 = 0;
    static double add50 = 0;
    static double add100 = 0;
    static double addCustom = 0;
    

    public static void main(String[] args)
    {
        //int login = 0;

        // use the do-while loop
        System.out.println();
        System.out.println("Enter Card Number: ");
        String userCardNumber = input.next();
        if (userCardNumber.equals(cardNumber))
        {
            System.out.println();
            System.out.println("Enter the Pin Number: ");
            int userPin = input.nextInt();
            if (userPin == Pin)
            {
                System.out.println("Welcome to the account.");
                System.out.println("Would you like to access the Checking Account or the Savings Account? ");
                String checkingOrSavings = input.next();

                
                if (checkingOrSavings.equalsIgnoreCase("checking") || checkingOrSavings.equalsIgnoreCase("checking account"))
                {

                    Checking checking1 = new Checking(add10, add20, add50, add100, addCustom, add10, add10, add10, add10);
                    System.out.println("Welcome to the Checking Account.");
                    System.out.println("Your current balance in the Checking Account is $" + checking1.getcheckingBalance()); // add checking balance
                    System.out.println("Would you like to make a Deposit or Withdraw money?\nPress 1 to Deposit, press 2 to Withdraw");
                    int depositOrWithdraw = input.nextInt();
                    
                    if (depositOrWithdraw == 1)
                    {
                        System.out.println("How much would you like to Deposit?\n$10, $20, $50, $100, or a custom amount \n(please write \"11\" if you want a custom deposit.)");
                        double checkingAccount = input.nextInt();

                        
                        if (checkingAccount == 10)
                        {
                            System.out.println("You have added $" + checking1.balance10() + "\nYour balance is now $" + checking1.balance10());
                        }
                        else if(checkingAccount == 20)
                        {
                            System.out.println("You have added $" + checking1.balance20() + "\nYour balance is now $" + checking1.balance20());
                        }
                        else if(checkingAccount == 50)
                        {
                            System.out.println("You have added $" + checking1.balance50() + "\nYour balance is now $" + checking1.balance50());
                        }
                        else if(checkingAccount == 100)
                        {
                            System.out.println("You have added $" + checking1.balance100() + "\nYour balance is now $" + checking1.balance100());
                        }
                        else if(checkingAccount == 11)
                        {
                            System.out.println("Please type in how much money you wish to deposit...");
                            addCustom = input.nextDouble();
                            System.out.println("You have added $" + addCustom + "\nYour balance is now $" + addCustom);
                        }
                        
                                                
                    }
                    
                }
                else if (checkingOrSavings.equalsIgnoreCase("saving") || checkingOrSavings.equals("savings")|| checkingOrSavings.equals("savings account"))
                {
                    System.out.println("Welcome to the Savings Account.");
                    System.out.println("Your current balance in the Savings Account is "); // add savings balance
                    
                }
                
                
            }
            else{
                System.out.println("Incorrect Pin");
            }
            
        }
        else{
            System.out.println("Incorrect Card Number");
        }

    }
    
}