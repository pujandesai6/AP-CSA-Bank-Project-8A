import java.util.Scanner;
import java.lang.Math;


public class debug
{


    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);



        // Print the list of available currencies

        System.out.println("Choose from the following currencies:");

        System.out.println("1. Euro: 0.92");
 
        System.out.println("2. British Pound: 0.81");

        System.out.println("3. Japanese Yen: 129.27");

        System.out.println("4. Swiss Franc: 0.92");

        System.out.println("5. Canadian Dollar: 0.75");

        System.out.println("6. Australian Dollar: 1.41");

        System.out.println("7. New Zealand Dollar: 1.55");

        System.out.println("8. Singapore Dollar: 1.31");

        System.out.println("9. Hong Kong Dollar: 7.81");

        System.out.println("10. Chinese Yuan: 6.74");

        System.out.println("11. Indian Rupee: 81.88");
    
        System.out.println("12. Indonesian Rupiah: 1500.60");

        System.out.println("13. Malaysian Ringgit: 4.27");

        System.out.println("14. Philippine Peso: 54.47");

        System.out.println("15. Thai Baht: 32.96");
        
        System.out.println();
        
        ConvertCurrency();
    }


    public static void ConvertCurrency() {
        // ask the user to enter the currency they want to convert to
    	Scanner scanner = new Scanner(System.in);
    	
        System.out.print("Enter the number corresponding to the currency you want to convert to: ");
        System.out.println();

        int currencyChoice = scanner.nextInt();

        // ask the user to enter the amount in dollars

        System.out.print("Enter the amount in dollars: ");
        System.out.println();

        double amountInDollars = scanner.nextDouble();


        // Convert the amount in dollars to the selected currency

        double convertedAmount = 0.0;
        
        double[] conversions = {0, 0.92, 0.81, 129.27, 0.92, 0.75, 1.41, 1.55, 1.31, 7.81, 6.74, 81.88, 1500.60, 4.27, 54.47, 32.96 };
        if (currencyChoice > 0 && currencyChoice < 15) {
        	convertedAmount = conversions[currencyChoice] * amountInDollars;
        	System.out.println("Your converted amount is: " + (double) Math.round(convertedAmount * 1000) / 1000);
        	
        	//Confirm conversion
        	System.out.println("Would you like to confirm your exchange? \nYou will be charged an extra 1% of the amount you wish to exchange \nEnter 1 for yes and 2 for no");
        	int response = scanner.nextInt();
        	
        	if (response == 1)
        	{
        		double totalAmount = amountInDollars + (amountInDollars * (0.01));
        		System.out.println("The total amount you will be charges is: " + totalAmount);
        		//code to remove totalAmount from bank account and where would converted amount go
        	}
        	else if (response == 2)
        	{
        		ConvertCurrency();
        	}
        }
        else {
        	System.out.println("Error. Please enter a valid number from the list above.");
        	ConvertCurrency();
        }
      
    } 
}
