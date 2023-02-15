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
