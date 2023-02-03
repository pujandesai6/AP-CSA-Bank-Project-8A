import java.util.Scanner;
import java.lang.Math;


public class CurrencyConverter
{


    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);



        // Print the list of available currencies

        System.out.println("Choose from the following currencies:");

        System.out.println("1. Euro");

        System.out.println("2. British Pound");

        System.out.println("3. Japanese Yen");

        System.out.println("4. Swiss Franc");

        System.out.println("5. Canadian dollar");

        System.out.println("6. Australian dollar");

        System.out.println("7. New Zealand dollar");

        System.out.println("8. Singapore dollar");

        System.out.println("9. Hong Kong dollar");

        System.out.println("10. Chinese yuan");

        System.out.println("11. Indian rupee");
    
        System.out.println("12. Indonesian rupiah");

        System.out.println("13. Malaysian ringgit");

        System.out.println("14. Philippine peso");

        System.out.println("15. Thai baht");



        // ask the user to enter the currency they want to convert to

        System.out.print("Enter the number corresponding to the currency you want to convert to: ");

        int currencyChoice = scanner.nextInt();



        // ask the user to enter the amount in dollars

        System.out.print("Enter the amount in dollars: ");

        double amountInDollars = scanner.nextDouble();



        // Convert the amount in dollars to the selected currency

        double convertedAmount = 0.0;
        
        double[] conversions = {0, 0.92, 0.81, 129.27, 0.92, 0.75, 1.41, 1.55, 1.31, 7.81, 6.74, 81.88, 1500.60, 4.27, 54.47, 32.96 };
        if (currencyChoice > 0 && currencyChoice <= 15) {
        	convertedAmount = conversions[currencyChoice] * amountInDollars;
        	System.out.println("Your converted amount is: " + (double) Math.round(convertedAmount * 1000) / 1000);
        }
        else {
        	System.out.println("Error. Please enter a valid number from the list above.");
        }
      
    }
}
