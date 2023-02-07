import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;


class GeneralAccount
{
    private ArrayList<String> transactionHistory;
    
    private String accountName;
    private String FullLegalName;
    private double balance;
    private int accountNumber;
    private static double overdraftLimit;
    
    //Credit Card Variables
    private String creditcardnumber;
    private String creditcardexp;
    private String creditcardcvv;
    private String middleinitial;
    private String debitcardnumber;
    private String debitcardexp;
    private String debitcardcvv;
    
//Constructor
    public GeneralAccount(String name, String FullLegalName, double balance)
    {
        transactionHistory = new ArrayList<String>();
        accountName = name;
        this.FullLegalName = FullLegalName;
        this.balance = balance;
        overdraftLimit = balance * 1.1;
        accountNumber = (int)(Math.random() * 999999999);
    }
    
//Deposit & Withdrawal
    public void deposit(double deposit)
    { 
        balance += Math.floor(100 * deposit) / 100;
        LocalDate date = LocalDate.now();
        System.out.println(accountName + "'s balance is now $" + balance);
        transactionHistory.add(date + ": Deposited $" + deposit);
    }
    
    public void withdraw(double withdrawal)
    {
        if(withdrawal <= balance)
        {
            balance -= Math.floor(100 * withdrawal) / 100;;
            LocalDate date = LocalDate.now();
            System.out.println(accountName + "'s balance is now $\n" + balance);
            transactionHistory.add(date + ": Withdrew $" + withdrawal);
        }
        
        //Overdraft fee
        else if(withdrawal < overdraftLimit)
        {
        	double totalWithdrawal = Math.floor(100 * withdrawal) / 100 + 35.0;
            balance -= totalWithdrawal;
            LocalDate date = LocalDate.now();
            System.out.println(accountName + "'s balance is now $" + balance + "\nDue to overdrafting, you had to pay a fee of $35\n");
            transactionHistory.add(date + ": Withdrew $" + totalWithdrawal);
        }
        
        //Over Overdraft Limit
        else
        {
            System.out.println("Your balance is $" + balance + "\n Your balance is not high enough to make this withdrawal\n");
        }
    }
//Account Details
    public void displayAccountDetails() {
        System.out.println("Account Holder: " + FullLegalName);
        System.out.println("Account Name: " + accountName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: $" + balance);
        System.out.println("Transaction History:");
        
        for(String x : transactionHistory)
        {
        	System.out.println(x);
        }
        
    }
    
//Get Methods
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
	private static ArrayList<SavingsAccount> savingsList = new ArrayList<SavingsAccount>();
    
	private int savingsAccountNumber;
    private static double minimumStartingBalance = 50.0;
    
    /* unused interest variables
    private static double interestRate = 0.0325;
    private static double minimumBalance = 300.0;
    private static int period= 1;
    private static double monthlyFee = 5.0;
    */
    
//Constructors
    
    public SavingsAccount(String accountName, String FullLegalName, double balance)
    {
        super(accountName, FullLegalName, balance);
        savingsAccountNumber = (int)(Math.random() * 999999999);
    }
    
//Prompts
    public static void promptCreate()
    {
    	Scanner test = new Scanner(System.in);
        String ask = "";
            
        while(!(ask.equals("n")))
    		{
    	        System.out.print("Would you like to make a new saving's account? [y/n]: ");
    	        ask = test.nextLine();
    	        
    	        if (ask.equals("y"))
    	            SavingsAccount.createSavingsAccount();
    		}
    }
    
    public static void promptTransaction()
    {
    	Scanner test = new Scanner(System.in);
    	String ask;
    	
    	System.out.print("Would you like to make a Deposit or Withdrawal? [d/w]: ");
    	ask = test.nextLine();
    	if(ask.equals("d"))
    	{
    		int accountIndex;
    		double deposit;
    		System.out.print("\nWhat account would you like to deposit into?: #");
    		accountIndex = test.nextInt();
    		
    		System.out.print("\nHow much would you like to deposit?: $");
    		deposit = test.nextDouble();
    		
    		SavingsAccount.depositSavings(accountIndex, deposit);
    	}
    	else if(ask.equals("w"))
    	{
    		int accountIndex;
    		double withdrawal;
    		System.out.print("\nWhat account would you like to withdraw from?: #");
    		accountIndex = test.nextInt();
    		
    		System.out.print("\nHow much would you like to withdraw?: $");
    		withdrawal = test.nextDouble();
    		
    		SavingsAccount.withdrawSavings(accountIndex, withdrawal);
    	}
    }
    
//Create Savings Account
    public static void createSavingsAccount()
    {
        Scanner test = new Scanner(System.in);
        
        String aName;
        String FullLegalName = "getFullName";
        double initialDeposit;
        
        System.out.print("What would you like to call the account?: ");
        aName = test.nextLine();
        System.out.print("What is the initial deposit?: $"); 
        initialDeposit = Math.floor(test.nextDouble() * 100) / 100;
        
        if (initialDeposit >= SavingsAccount.getMinimumBal())
            {
                System.out.println(aName + "'s balance is now $" + initialDeposit + "\n");
            }
        else
            {
                while (initialDeposit < SavingsAccount.getMinimumBal())
                {
                    System.out.println("Too low! The minimum amount to start an account is $" + SavingsAccount.getMinimumBal());
                    System.out.print("What is the initial deposit?: $"); 
                    initialDeposit = test.nextDouble();
                }
                
            }
        savingsList.add(new SavingsAccount(aName, FullLegalName, initialDeposit));
    }
    
//Deposit and Withdrawal in Account #X
    public static void depositSavings(int x, double deposit)
    {
    	SavingsAccount account = savingsList.get(x - 1);
    	account.deposit(deposit);
    }
    
    public static void withdrawSavings(int x, double withdrawal)
    {
    	SavingsAccount account = savingsList.get(x - 1);
    	account.withdraw(withdrawal);
    }
    
    public static void getUserSavings()
    {
    	int i;
        for(SavingsAccount x : savingsList)
        {
        	i = savingsList.indexOf(x) + 1;
            System.out.println("\nAccount #" + i + " =====================");
            x.displayAccountDetails();
        }
        	System.out.println("================================\n");
    }
    
    public int getSavingsAccountNumber()
    {
        return savingsAccountNumber;
    }
    
    public static double getMinimumBal()
    {
        return minimumStartingBalance;
    }
    
    /*
    public static void monthAdvance()
    {
        if(balance >= minimumBalance)
            monthlyFee = 0.0;
        
        balance = balance * (1.0 + Math.pow(interestRate / (double) period, period) - monthlyFee);
    }
    */
}


class CheckingAccount extends GeneralAccount
{
    private static ArrayList<CheckingAccount> checkingList = new ArrayList<CheckingAccount>();
    
    private int checkingAccountNumber;
    private static double minimumStartingBalance = 0.0;
    
    //Constructors
    public CheckingAccount(String accountName, String FullLegalName, double balance)
    {
        super(accountName, FullLegalName, balance);
        checkingAccountNumber = (int)(Math.random() * 999999999);
    }
    
    //Prompts
    public static void promptCreate()
    {
        Scanner test = new Scanner(System.in);
        String ask = "";
            
        while(!(ask.equals("n")))
        {
            System.out.print("Would you like to make a new checking account? [y/n]: ");
            ask = test.nextLine();
            
            if (ask.equals("y"))
                CheckingAccount.createCheckingAccount();
        }
    }
    
    public static void promptTransaction()
    {
        Scanner test = new Scanner(System.in);
        String ask;
        
        System.out.println("Which checking account would you like to perform a transaction on?");
        for(int i = 0; i < checkingList.size(); i++)
        {
            System.out.println((i+1) + ". " + checkingList.get(i).getAccountName());
        }
        
        System.out.print("Enter the number of the account you would like to perform a transaction on: ");
        int choice = Integer.parseInt(test.nextLine());
        
        System.out.println("Would you like to:\n1. Deposit\n2. Withdraw");
        System.out.print("Enter the number of your desired transaction: ");
        int action = Integer.parseInt(test.nextLine());
        
        System.out.print("Enter the amount: ");
        double amount = Double.parseDouble(test.nextLine());
        
        if(action == 1)
            checkingList.get(choice-1).deposit(amount);
        else if(action == 2)
            checkingList.get(choice-1).withdraw(amount);
    }
    
    //Create Account
    public static void createCheckingAccount()
    {
        Scanner test = new Scanner(System.in);
        System.out.println("What is the name on the checking account?");
        String accountName = test.nextLine();
        
        System.out.println("What is the full legal name on the account?");
        String FullLegalName = test.nextLine();
        
        System.out.println("What is the starting balance of the account?");
        double balance = Double.parseDouble(test.nextLine());
        
        CheckingAccount check = new CheckingAccount(accountName, FullLegalName, balance);
        checkingList.add(check);
        System.out.println("Your checking account was created successfully!\n");
    }
}

