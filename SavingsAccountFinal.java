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
    public static void userMenu()
    {
        int choice = 0;
        Scanner sebSmells = new Scanner(System.in);
        
        while(choice != 5) {
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
            case 4 : getUserSavings();
            	continue;
            case 5 : 
            }
        }
        else
        	System.out.println("Please pick a valid choice");
        }
        
    }
    public static String menuOptions()
    {
        return "1. Create new savings account\n2. Deposit\n3. Withdraw\n4. View accounts\n5. Exit";
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
    	int accountIndex;
		double deposit;
		
		SavingsAccount.getUserSavings();
		System.out.print("\nPick an account # from the list to deposit into: #");
		accountIndex = test.nextInt();
		
		System.out.print("\nHow much would you like to deposit?: $");
		deposit = test.nextDouble();
		
		SavingsAccount.depositSavings(accountIndex, deposit);
    }
    public static void promptWithdraw()
    {
        Scanner test = new Scanner(System.in);
        int accountIndex;
		double withdrawal;
		
		SavingsAccount.getUserSavings();
		System.out.print("\nPick an account # from the list to withdraw from: #");
		accountIndex = test.nextInt();
		
		System.out.print("\nHow much would you like to withdraw?: $");
		withdrawal = test.nextDouble();
		
		SavingsAccount.withdrawSavings(accountIndex, withdrawal);
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
}
