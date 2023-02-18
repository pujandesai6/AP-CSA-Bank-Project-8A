import java.util.*;
import java.lang.Math;
import java.time.LocalDateTime;

class GeneralAccount
{
    //Private Instance Variables
    private ArrayList<String> transactionHistory;
    private String accountName;
    private double balance;
    private int accountNumber;

    //General bank details
    private static int overdraftfee = 40;
    private static int maxoverdraftcharge = 200;
    
    //Credit Card Variables
    private String creditcardnumber;
    private String creditcardexp;
    private String creditcardcvv;
    private String middleinitial;
    private String debitcardnumber;
    private String debitcardexp;
    private String debitcardcvv;
    
//Constructor
    public GeneralAccount(String accName, double balance)
    {
        this.transactionHistory = new ArrayList<String>();
        this.accountName = accName;
        this.balance = balance;
        this.accountNumber = (int) (Math.random() * 1000000000) + 1;
    }
    
//Deposit & Withdrawal
    public void deposit(double deposit)
    { 
        this.balance += Math.floor(100 * deposit) / 100;
        System.out.println(accountName + "'s balance is now $" + this.balance);
        transactionHistory.add(LocalDateTime.now() + ": Deposited $" + deposit);
    }
    
    public void withdraw(double withdrawal)
    {
        if (withdrawal <= this.balance)
        {
            this.balance -= Math.floor(100 * withdrawal) / 100;;
            System.out.println(accountName + "'s balance is now $" + this.balance);
            transactionHistory.add(LocalDateTime.now() + ": Withdrew $" + withdrawal);
        }
        
        //Overdraft fee
        else if(withdrawal - this.balance <= maxoverdraftcharge)
        {
        	double totalWithdrawal = Math.floor(100 * withdrawal) / 100 + overdraftfee;
            balance -= totalWithdrawal;
            System.out.println(accountName + "'s balance is now -$" + (-this.balance));
            System.out.println("Overdraft fee of $" + overdraftfee + " charged\n");
            transactionHistory.add(LocalDateTime.now() + ": Withdrew $" + totalWithdrawal);
        }
        
        //Over Overdraft Limit
        else
        {
            System.out.println("Your balance is not high enough to make this withdrawal.\n");
        }
    }
//Account Details
    public void ShowTransactionHistory() {
        for (int i = 0; i < this.transactionHistory.size(); i++)
        {
        	System.out.println("Transaction at " + LocalDateTime.now() + ": " + transactionHistory.get(i));
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
