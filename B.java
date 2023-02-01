import java.util.Scanner;
import java.util.ArrayList;

public class Mariam{
    
	public static void main(String[] args) {
        CheckingAccount a = new CheckingAccount();
        a.menu();
    } 
}

class CheckingAccount extends Savings
{
    private double CA_TotalBalance = 200;
    private double CA_OverDraftLimit;
    private static double MinimumBalance = 100;
        
    public void deposit(double amount)
    {
        CA_TotalBalance += amount;
        CA_OverDraftLimit = CA_TotalBalance * 1.5;
    }
    
    public void withdraw(double amount){
        if (amount <= CA_TotalBalance)
        {
            CA_TotalBalance -= amount;
            if(CA_TotalBalance < 100)
            {
                sa_balance = sa_balance - 25;
                System.out.println("Your balance is less than minimum balance requirement.\nTherefore $25 dollars has been deducted from your savings account.");

            }
        }
        else if (amount <= CA_OverDraftLimit)
        {
            CA_TotalBalance -= amount + 35;
            System.out.println("Your Total Balance is now $" + CA_TotalBalance + "\nYou have to pay $25 fee for overdrafting");
            CA_OverDraftLimit = 0;
        }
        else
        {
            System.out.println("Your balance is $"+ CA_TotalBalance +"\nYour balance is not high enough to make this withdraw");
        }
    }
    
    
    public String getAccountNumber()
    {
    	ArrayList<Integer> numbers = new ArrayList<Integer>();
    	for(int i = 1 ; i < 10 ; i++)
    	{
    		numbers.add((int)(Math.random() * 10));
    	}
    	return numbers.toString().replace("[" , "").replace(" " , "").replace("," , "").replace("]" , "");
    }

    public double getBalance()
    {
        return CA_TotalBalance;
    }

    public void menu()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("\nAccount Number:  " + getAccountNumber() + "\n");

        int i = 0;
        while (i < 1)
        {
            System.out.println("1. Deposit Money \n2. Withdraw Money \n3. Log Out");
            System.out.print("Enter Option: ");
            int option = input.nextInt();
            System.out.println();
            
            
        
            if (option == 1)
            {
                System.out.print("Enter Amount to Deposit: ");
                double amount = input.nextDouble();
                System.out.println("$"+ amount + " Deposited to the Account\n");
                deposit(amount);
                System.out.println("Total Amount in Account: " + getBalance() + "\n");                
            }
            
            else if (option == 2)
            {
                System.out.print("Enter Amount to Withdraw: ");
                double amount = input.nextDouble();
                System.out.println("$"+ amount + " Withdrawn from the Account\n");
                withdraw(amount);
                System.out.println("Total Amount in Account: " + getBalance() + "\n");
                
            }
            
            
            else if (option == 3)
            {
                System.out.print("Successfully logged out");
                i++;
            }
            
            else
            {
                System.out.println("Sorry, Try Again");
            }
        }

    }
}
