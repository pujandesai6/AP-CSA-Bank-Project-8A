import java.util.*;

class Loan
{
	private static ArrayList<Loan> loanList = new ArrayList<Loan>();
    private String fullLegalName;
    private String loanName;
    private double principle;
    private double monthlyPay;
    private double interestRate;
    private int loanLength;
    private static int creditScore;

    public Loan(String loanName, double principle, double monthlyPay, int loanLength, double interestRate)
    {
        this.fullLegalName = "GetFullName"; //Get from Customer
        this.loanName = loanName;
        this.principle = principle;
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
   
        double interest = ((1000.0 - (double)creditScore) * 0.001) * (1.0 + (double)loanTerm / 20); //Not Working
        double monthlyPayments = (interest * loanPrinciple)/ 12;
        loanList.add(new Loan(name,loanPrinciple,monthlyPayments,loanTerm,interest));
        System.out.println("New loan created!");
    }
    public static void getUserLoans()
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
    public void payLoan()
    {
    	Scanner ashleySmells = new Scanner(System.in);
    	int loanChoice, acctChoice;
    	System.out.println("Choose a Loan # from the list to make a payment on: ");
    	Loan.getUserLoans();
    	loanChoice = ashleySmells.nextInt();
    	System.out.println("Choose an Account # from the list to pay from: ");
    	SavingsAccount.getUserSavings();
    	acctChoice = ashleySmells.nextInt();
    	
    	
    }
    public void displayLoanDetails() 
    {
    	double displayInterest = (interestRate - 1) * 100;
        System.out.println("Holder: " + fullLegalName);
        System.out.println("Purpose: " + loanName);
        System.out.println("Original Term: " + loanLength);
        System.out.println("Remaining balance: $" + principle);
        System.out.println("Remaining balance this month: $" + monthlyPay);
        System.out.println("Interest: " + displayInterest + "%");
        System.out.println(interestRate);
    }
    
}
