public class SavingsTest {
    public static void main (String args []) {
        SavingsAccount.userMenu();
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
    }
    
//Prompts
    public static void userMenu()
    {
        int choice = 0;
        Scanner sebSmells = new Scanner(System.in);
        
        while(choice != 2 && savingsList.size() == 0)
        {
        	System.out.println("Pick an action from the list: \n1. Create new savings account\n2. Exit");
            choice = sebSmells.nextInt();
            
            if(choice > 0 && choice < 3)
            {
	            switch (choice) 
	            {
	                case 1 : createSavingsAccount();
	                    continue;
	                case 2 :
	            }
            }
            else
	        	System.out.println("Invalid choice");
        }
        while(choice != 5 && savingsList.size() > 0) 
        {
	        System.out.println("Pick an action from the list: \n" + menuOptions());
	        choice = sebSmells.nextInt();
	        
	        if(choice > 0 && choice < 6)
	        {
	        switch (choice) {
	            case 1 : createSavingsAccount();
	                continue;
	            case 2 : promptDeposit();
	            	continue;
	            case 3 : promptWithdraw();
	            	continue;
	            case 4 : promptDisplay();
	            	continue;
	            case 5 : 
	            }
	        }
	        else
	        	System.out.println("Invalid choice");
        }
    }
    public static String menuOptions()
    {
        return "1. Create new savings account\n2. Deposit\n3. Withdraw\n4. View account details\n5. Exit";
    }
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
    public static void promptDeposit()
    {
        Scanner test = new Scanner(System.in);
    	int accountIndex = 0;
		double deposit = 0;
		
		SavingsAccount.getUserSavings();
		while(accountIndex < 1 || accountIndex >savingsList.size())
		{
			System.out.print("\nPick an account # from the list to deposit into: #");
			accountIndex = test.nextInt();
			if(accountIndex < 1 || accountIndex >savingsList.size())
				System.out.println("Invalid choice");
		}
		
		while(deposit <= 0)
		{
		System.out.println("\nHow much would you like to deposit?: $");
		deposit = test.nextDouble();
		if(deposit <= 0)
			System.out.println("Invalid amount");
		}
		
		SavingsAccount.depositSavings(accountIndex, deposit);
    }
    public static void promptWithdraw()
    {
        Scanner test = new Scanner(System.in);
        int accountIndex = 0;
		double withdrawal = 0.0;
		
		SavingsAccount.getUserSavings();
		while(accountIndex < 1 || accountIndex >savingsList.size())
		{
			System.out.print("\nPick an account # from the list to withdraw from: #");
			accountIndex = test.nextInt();
			if(accountIndex < 1 || accountIndex >savingsList.size())
				System.out.println("Invalid choice");
		}
		while(withdrawal <= 0)
		{
		System.out.println("\nHow much would you like to withdraw?: $");
		withdrawal = test.nextDouble();
		if(withdrawal <= 0)
			System.out.println("Invalid amount");
		}
		
		SavingsAccount.withdrawSavings(accountIndex, withdrawal);
    }
    public static void promptDisplay()
    {
    	Scanner test = new Scanner(System.in);
        int accountIndex = 0;
		
		SavingsAccount.getUserSavings();
		while(accountIndex < 1 || accountIndex >savingsList.size())
		{
			System.out.print("\nPick an account # from the list to view: #");
			accountIndex = test.nextInt();
			if(accountIndex < 1 || accountIndex >savingsList.size())
				System.out.println("Invalid choice");
		}
		SavingsAccount x = savingsList.get(accountIndex - 1);
		x.displayAccountDetails();
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
    	System.out.println("=================");
    	int i;
        for(SavingsAccount x : savingsList)
        {
        	i = savingsList.indexOf(x) + 1;
            System.out.println("Account #" + i + ": " + x.getAccountName());
            System.out.println("Balance: $" + x.getBalance());
            System.out.println("=================");
        }
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
}
