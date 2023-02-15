import java.util.Scanner;
import java.util.ArrayList;

public class checkings_account{
    
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

    Scanner input = new Scanner(System.in);
    Scanner options = new Scanner(System.in);

    ArrayList<String> accountinfo = new ArrayList<String>();
        
    public void deposit(double amount)
    {
        CA_TotalBalance += amount;
        CA_OverDraftLimit = CA_TotalBalance;
        if (MinimumBalance > CA_TotalBalance)
        {
            sa_balance = sa_balance - 25;
            System.out.println("Your balance is less than minimum balance requirement.\nTherefore $25 dollars has been deducted from your savings account.");

        }
    }
    
    public void withdraw(double amount)
    {
        if (amount <= CA_TotalBalance)
        {
            CA_TotalBalance -= amount;
        }
        else if (amount >= CA_TotalBalance && (amount - CA_TotalBalance) < 0)
        {
            double new_amount = amount - CA_TotalBalance;
            sa_balance -= new_amount + 35;
            System.out.println("$"+ new_amount + "Has been duducted from your Savings Account.\nYour Checkings Account has balance of $" + CA_TotalBalance);

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
                System.out.print("Successfully logged out\n");
                i++;
            }
            
            else
            {
                System.out.println("Sorry, Try Again");
            }
        }

    }

}
