import java.util.*;
import java.lang.Math;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

public class betaTest {
    public static void main (String args []) {
        SavingsAccount.promptCreate();
        SavingsAccount.promptTransaction();
        Loan.promptCreate();
        Loan.promptPay();
        Loan.getUserLoans();
        SavingsAccount.getUserSavings();
    }
}

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
    		SavingsAccount.getUserSavings();
    		System.out.print("\nPick an account # from the list to deposit into?: #");
    		accountIndex = test.nextInt();
    		
    		System.out.print("\nHow much would you like to deposit?: $");
    		deposit = test.nextDouble();
    		
    		SavingsAccount.depositSavings(accountIndex, deposit);
    	}
    	else if(ask.equals("w"))
    	{
    		int accountIndex;
    		double withdrawal;
    		SavingsAccount.getUserSavings();
    		System.out.print("\nPick an account # from the list to withdraw from?: #");
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
    
    public static double getSavingsBal(int x)
    {
    	SavingsAccount account = savingsList.get(x - 1);
    	return account.getBalance();
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

class Loan
{
    private static ArrayList<Loan> loanList = new ArrayList<Loan>();
    private String fullLegalName;
    private String loanName;
    private double principle;
    private double monthlyPay;
    private double totalPay;
    private double interestRate;
    private int loanLength;
    private static int creditScore;

    public Loan(String loanName, double principle, double totalPay, double monthlyPay, int loanLength, double interestRate)
    {
        this.fullLegalName = "GetFullName"; //Get from Customer
        this.loanName = loanName;
        this.principle = principle;
    	this.totalPay = totalPay;
        this.monthlyPay = monthlyPay;
        this.loanLength = loanLength;
        this.creditScore = 700; //Get from Customer
        this.interestRate = interestRate;
    }
    
    public static void promptCreate()
    {
    	Scanner adhi = new Scanner(System.in);
        String ask = "";
            
        while(!(ask.equals("n")))
    		{
    	        System.out.print("Would you like to take out a new loan? [y/n]: ");
    	        ask = adhi.nextLine();
    	        
    	        if (ask.equals("y"))
    	            Loan.createLoan();
    		}
    }
    
    public static void promptPay()
    {
    	Scanner adhi = new Scanner(System.in);
        String ask = "";
            
        while(!(ask.equals("n")))
    		{
    	        System.out.print("Would you like to pay a loan's monthly payment? [y/n]: ");
    	        ask = adhi.nextLine();
    	        
    	        if (ask.equals("y"))
    	            Loan.payLoan();
    		}
    }
    
    public static void createLoan()
    {
        Scanner adhi = new Scanner(System.in);
        String name = "";
        double loanPrinciple = 0.0;
        int loanTerm = 0;
        
        System.out.print("What is the loan for?: ");
        name = adhi.nextLine();
        System.out.print("How much are you borrowing: $");
        loanPrinciple = Math.floor(adhi.nextDouble()*100)/100;
        System.out.print("Pick an an Account # from the list to deposit the loan into: #");
        SavingsAccount.getUserSavings();
        SavingsAccount.depositSavings(adhi.nextInt(),loanPrinciple);
        System.out.print("How many years do you want your loan term to be?: ");
        loanTerm = adhi.nextInt();
   
        double interest = Math.floor(100 * (1.0 + ((1000.0 - (double)creditScore) * 0.0001) * (1.0 + (double)loanTerm * 0.1))) / 100;
	    double tPay = Math.floor(100 * (Math.pow(interest, (double) loanTerm) * loanPrinciple)) / 100;
        double monthlyPayments = Math.floor(100 * (tPay / ((double)loanTerm * 12.0))) / 100;
        loanList.add(new Loan(name,loanPrinciple,tPay,monthlyPayments,loanTerm,interest));
        System.out.println("New loan created!");
    }
    public static void getUserLoans()   //displays loans
    {
    	int i;
        for(Loan x : loanList)
        {
        	i = loanList.indexOf(x) + 1;
            System.out.println("\nLoan #" + i + " ========================");
            x.displayLoanDetails();
        }
        	System.out.println("================================");
    }
    public void totalMinusMonthly()    //resets monthly payment
    {
	   totalPay -= monthlyPay;
	   monthlyPay = 0;
    }
    public static void loanTotalPay(int choice)     //amount left in a loan
    {
	    Loan loanChoice = loanList.get(choice - 1);
	    loanChoice.totalMinusMonthly();
	    System.out.println("Remaining Balance: $" + loanChoice.getBalance());
    }
    public double getMonthlyPay()
    {
	    return monthlyPay;
    }
    public static double getChoiceMonthlyPay(int x)    //gets which loan to pay
    {
	    Loan choice = loanList.get(x - 1);
	    return choice.getMonthlyPay();
    }
    public static void payLoan()
    {
    	Scanner ashleySmells = new Scanner(System.in);
    	int loanChoice, acctChoice;
	
    	System.out.println("Choose a Loan # from the list to make a payment on: ");
    	Loan.getUserLoans();
    	loanChoice = ashleySmells.nextInt();
    	System.out.println("Choose an Account # from the list to pay from: ");
    	SavingsAccount.getUserSavings();
    	acctChoice = ashleySmells.nextInt();
	if(SavingsAccount.getSavingsBal(acctChoice) >= Loan.getChoiceMonthlyPay(loanChoice)) {
		Loan.loanTotalPay(loanChoice);
		SavingsAccount.withdrawSavings(acctChoice, Loan.getChoiceMonthlyPay(loanChoice));
	}
	else {
	   System.out.print("not enough");
	}
	   
	
    }
    public double getBalance()
    {
	    return totalPay;
    }
    public void displayLoanDetails() 
    {
    	double displayInterest = (interestRate - 1.0) * 100.0;
        System.out.println("Holder: " + fullLegalName);
        System.out.println("Purpose: " + loanName);
        System.out.println("Original Term: " + loanLength);
        System.out.println("Remaining balance: $" + totalPay);
        System.out.println("Remaining balance this month: $" + monthlyPay);
        System.out.println("Interest: " + displayInterest + "%");
        System.out.println(interestRate);
    }
    
}