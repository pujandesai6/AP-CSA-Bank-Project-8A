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
        
        switch (currencyChoice) {
            case 1 :
                convertedAmount = amountInDollars * 0.92;
                break;
            case 2 :
                convertedAmount = amountInDollars * 0.81;
                break;
            case 3 :
                convertedAmount = amountInDollars * 129.27;
                break;
            case 4 :
                convertedAmount = amountInDollars * 0.92;
                break;
            case 5 :
                convertedAmount = amountInDollars * 0.75;
                break;
            case 6 :
                convertedAmount = amountInDollars * 1.41;
                break;
            case 7 :
                convertedAmount = amountInDollars * 1.55;
                break;
            case 8 :
                convertedAmount = amountInDollars * 1.31;
                break;
            case 9 :
                convertedAmount = amountInDollars * 7.84;
                break;
            case 10 :
                convertedAmount = amountInDollars * 6.74;
                break;
            case 11 :
                convertedAmount = amountInDollars * 81.88;
                break;
            case 12 :
                convertedAmount = amountInDollars * 1500.60;
                break;
            case 13 :
                convertedAmount = amountInDollars * 4.27;
                break;
            case 14 :
                convertedAmount = amountInDollars * 54.47;
                break;
            case 15 :
                convertedAmount = amountInDollars * 32.96;
                break;
        }
        System.out.println("Your converted amount is: " + (double) Math.round(convertedAmount * 1000) / 1000);
      
    }
}