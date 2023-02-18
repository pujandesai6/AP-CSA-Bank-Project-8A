import java.util.Scanner;

class SavingsAccount extends GeneralAccount
{    
    private static double minimumStartingBalance = 50.0;
    
    /* unused interest variables
    private static double interestRate = 0.0325;
    private static double minimumBalance = 300.0;
    private static int period= 1;
    private static double monthlyFee = 5.0;
    */
    
//Constructors
    
    public SavingsAccount(String accountName, double balance)
    {
        super(accountName, balance);
    }
    
//Prompts
    public void promptTransaction()
    {
    	Scanner test = new Scanner(System.in);
    	String ask;
    	
    	System.out.print("Would you like to make a Deposit or Withdrawal? [d/w]: ");
    	ask = test.nextLine();
    	if(ask.equals("d")) {
    		double deposit;
    		
    		System.out.print("\nHow much would you like to deposit?: $");
    		deposit = test.nextDouble();
    		
    		this.deposit(deposit);
    	}
    	else if(ask.equals("w")) {
    		double withdrawal;
    		
    		System.out.print("\nHow much would you like to withdraw?: $");
    		withdrawal = test.nextDouble();
    		
    		this.withdraw(withdrawal);
    	}
        test.close();
    }
    
//Create Savings Account
    public static SavingsAccount createSavingsAccount()
    {
        Scanner test = new Scanner(System.in);
        
        String aName;
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
                    System.out.println();
                }
                
            }
        test.close();
        return new SavingsAccount(aName, initialDeposit);
    }
    
//Deposit and Withdrawal in Account #X
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