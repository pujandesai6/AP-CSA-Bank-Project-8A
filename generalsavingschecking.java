//Ashley, Sebastian, Ravi's Savings Account + General Account
//and
//Anthony, Dany, Sid's Checking Account


import java.lang.Math;
import java.util.Scanner;
import java.util.ArrayList;

public class TestClass {
    public static void main(String args[]) {
        SavingsAccount.createSavingsAccount();
        System.out.print(SavingsAccount.printList());
        CheckingAccount.createCheckingAccount();
    System.out.print(CheckingAccount.printList());
    }
}


class GeneralAccount
{
    private String accountName;
    private double balance;
    private int accountNumber;
    private static double overdraftLimit;
    
    
    //Constructors
    public GeneralAccount(String name, double balance)
    {
        accountName = name;
        this.balance = balance;
        overdraftLimit = balance * 1.1;
        accountNumber = (int)(Math.random() * 999999999);
    }
    
    //Deposit & Withdrawal
    public void depositMoney(double deposit)
    {
        balance += deposit;
        System.out.println("Your balance is now $" + balance);
    }
    
    public void withdrawMoney(double withdrawal)
    {
        if(withdrawal <= balance)
        {
            balance -= withdrawal;
            System.out.println("Your balance is now $" + balance);
        }
        
        //Overdraft fee
        else if(withdrawal < overdraftLimit)
        {
            balance -= withdrawal + 35.0;
            System.out.println("Your balance is now $" + balance + "\n Due to overdrafting, you had to pay a fee of $35");
        }
        
        //Over Overdraft Limit
        else
        {
            System.out.println("Your balance is $" + balance + "\n Your balance is not high enough to make this withdrawal");
        }
    }
    
    //Get method
    public double getBalance()
    {
        return balance;
    }
    
    public int getAccountNumber()
    {
        return accountNumber;
    }
    
    public String getAccountName()
    {
        return accountName;
    }
}

class SavingsAccount extends GeneralAccount
{
    private int savingsAccountNumber;
    private static double interestRate = 0.0325;
    private static double minimumBalance = 300.0;
    private static double minimumStartingBalance = 50.0;
    private static int period= 1;
    private static double monthlyFee = 5.0;
    private static ArrayList<SavingsAccount> savingsList = new ArrayList<SavingsAccount>();
    
    //Constructors
    
    public SavingsAccount(String accountName, double balance)
    {
        super(accountName, balance);
        savingsAccountNumber = (int)(Math.random() * 999999999);
    }
    
    
    //Create Savings Account
    public static void createSavingsAccount()
    {
        Scanner test = new Scanner(System.in);
        
        String aName;
        double initialDeposit;
        
        System.out.print("What would you like to call your savings account?: ");
        aName = test.nextLine();
        System.out.print("What is the initial deposit?: "); 
        initialDeposit = test.nextDouble();
        
        if (initialDeposit >= SavingsAccount.getMinimumBal())
            {
                System.out.println("Your savings account balance is now " + initialDeposit);
            }
        else
            {
                while (initialDeposit < SavingsAccount.getMinimumBal())
                {
                    System.out.println("Too low! The minimum amount to start a savings account is " + SavingsAccount.getMinimumBal());
                    System.out.print("What is the initial deposit?: "); 
                    initialDeposit = test.nextDouble();
                }
                
            }
            
        
        savingsList.add(new SavingsAccount(aName, initialDeposit));
    }
    
    //Month advance

    /*
    public static void monthAdvance()
    {
        if(balance >= minimumBalance)
            monthlyFee = 0.0;
        
        balance = balance * (1.0 + Math.pow(interestRate / (double) period, period) - monthlyFee);
    }
    */
    
    public static String printList() {
        String results = "";
        for(SavingsAccount i : savingsList) 
        {
            results += i.getAccountName() + "(" + i.getAccountNumber() + "): " + i.getBalance() + "\n";
        }
        return results;
    }
    
    public int getSavingsAccountNumber()
    {
        return savingsAccountNumber;
    }
    
    public static double getMinimumBal()
    {
        return minimumStartingBalance;
    }
    
    public static ArrayList<SavingsAccount> getList()
    {
        return savingsList;
    }
}

class CheckingAccount extends GeneralAccount
{
    private int checkingAccountNumber;
    private static double overdraftFee = 35.0;
    private static ArrayList<CheckingAccount> checkingList = new ArrayList<CheckingAccount>();
    
    //Constructors
    
    public CheckingAccount(String accountName, double balance)
    {
        super(accountName, balance);
        checkingAccountNumber = (int)(Math.random() * 999999999);
    }
    
    //Create Checking Account
    public static void createCheckingAccount()
    {
        Scanner test = new Scanner(System.in);
        
        String aName;
        double initialDeposit;
        
        System.out.print("What would you like to call your checking account?: ");
        aName = test.nextLine();
        System.out.print("What is the initial deposit?: "); 
        initialDeposit = test.nextDouble();
        
        checkingList.add(new CheckingAccount(aName, initialDeposit));
    }
    
    public void withdrawMoney(double withdrawal)
    {
        if(withdrawal <= getBalance())
        {
            super.withdrawMoney(withdrawal);
        }
        else
        {
            super.withdrawMoney(withdrawal + overdraftFee);
            System.out.println("Due to overdrafting, you had to pay a fee of $" + overdraftFee);
        }
    }
    
    public static String printList() {
        String results = "";
        for(CheckingAccount i : checkingList) 
        {
            results += i.getAccountName() + "(" + i.getAccountNumber() + "): " + i.getBalance() + "\n";
        }
        return results;
    }
    
    public int getCheckingAccountNumber()
    {
        return checkingAccountNumber;
    }
}