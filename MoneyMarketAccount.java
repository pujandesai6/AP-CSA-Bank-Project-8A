import java.util.Scanner;

public class MoneyMarketAccount
{
public static void main(String[] args)
{
 Scanner userInput = new Scanner(System.in);
    float amount = 0;
    int interest = 7; // 7%
    float decimalInterest = interest * 0.01f; // 1.07
    float totalInterest;
   boolean depositAgain = true;
    
    //deposit money
    while (depositAgain == true)
    {
      
      System.out.println("Enter the amount you want to be deposited into your money market account: ");
      String deposit = userInput.nextLine();
      
      amount += Float.parseFloat(deposit);
      System.out.println("You have deposited - $" + deposit);
      
      Scanner follow = new Scanner(System.in);
      System.out.println("Would you like to deposit any more into your account?(yes or no)");
      String response = userInput.nextLine();
    
      if (response.equalsIgnoreCase("yes"))
      {
      depositAgain = true;
      System.out.println("Enter the amount you want to be deposited into your money market account: ");
      deposit = userInput.nextLine();
    amount += Float.parseFloat(deposit);
      }
      else if (response.equalsIgnoreCase("no"))
      {
      depositAgain = false;
    break;
      }
    else
    {
        System.out.println("invalid response. Returning to main menu");
        System.out.println();
    }
        
    }
    //how much you have in account
    boolean showAmount = false;
    System.out.println("Would you like to see how much you have deposited into your account?(yes or no)");
    String response = userInput.nextLine();
    if (response.equalsIgnoreCase("yes"))
      showAmount = true;
    else if (response.equalsIgnoreCase("no"))
      showAmount = false;
    else
    {
        System.out.println("invalid response. Returning to main menu");
        System.out.println();
    }
    if (showAmount == true) {
      System.out.println("Here's how much money you have:");
      System.out.println("$" + amount);
    }
    System.out.println("Would you like to see how much interest you have built up from your deposited amount after one month?(yes or no)");
    response = userInput.nextLine();

    totalInterest = amount * decimalInterest;
    if (response.equalsIgnoreCase("yes"))
      System.out.println("Your total interest made is " + totalInterest);
    else if (response.equalsIgnoreCase("no"))
      System.out.println("Okay Thank you!");
    else
    {
        System.out.println("Invalid response. Returning to main menu");
        System.out.println();
    }

    amount = amount + totalInterest;

    // after intrest
    System.out.println("Here's how much money you have (after interest):");
    System.out.println("$" + amount); 

}

}