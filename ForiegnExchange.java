import java.io.IOException;
import java.util.Scanner;

public class ForiegnExchange {
    public static void femain() throws IOException{
        Scanner scanner = new Scanner(System.in);

        System.out.println("--------------------");

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
        
        System.out.println("--------------------");
        ConvertCurrency();
    }


    public static void ConvertCurrency() throws IOException{
        // ask the user to enter the currency they want to convert to
    	Scanner scanner = new Scanner(System.in);
    	int currencyChoice = 0;
        do{
            System.out.print("Enter the number corresponding to the currency you want to convert to: ");
            currencyChoice = scanner.nextInt();
            System.out.println("--------------------");
        }
        while(currencyChoice < 0 || currencyChoice > 15);

        // ask the user to enter the amount in dollars

        System.out.print("Enter the amount in dollars you would like to transfer: ");
        double amountInDollars = scanner.nextDouble();
        System.out.println("--------------------");

        // Convert the amount in dollars to the selected currency

        double convertedAmount = 0.0;
        
        double[] conversions = {0, 0.92, 0.81, 129.27, 0.92, 0.75, 1.41, 1.55, 1.31, 7.81, 6.74, 81.88, 1500.60, 4.27, 54.47, 32.96 };
        if (currencyChoice > 0 && currencyChoice <= 15) { //you guys need to include 15 
        	convertedAmount = conversions[currencyChoice] * amountInDollars;
        	System.out.println("Your converted amount is: " + (double) Math.round(convertedAmount * 1000) / 1000 + currencyChoice);
        	
        	//Confirm the conversion
        	System.out.println("Would you like to confirm your exchange? You will be charged an extra 1% of the amount you wish to exchange.\n1. Yes\n2. No");
            System.out.print("Enter Choice: ");
        	int response = scanner.nextInt();
            System.out.println("--------------------");
        	
        	if (response == 1)
        	{
        		
        		double totalAmount = (amountInDollars * 1.01 );
        		System.out.println("The total amount you will be charges is: " + totalAmount);
        		System.out.println("--------------------");
        		System.out.println("From which account would you like to take funds from? \n1. Savings Account\n2. Checkings Account");
                System.out.print("Enter Choice: ");
        		int resp = scanner.nextInt();
        		System.out.println("--------------------");
                System.out.println("Converting money ...");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("Successful!\nDon't forget to collect your foriegn currency.");
        		if (resp == 1)
        		{
                    Data.method = "FE";
                    Data.typeOfAccount = "Savings";
        			Transactions.typeOfTransaction = 3;
                    Transactions.amount = totalAmount;
                    Transactions.memo = "Foriegn Exchange";
                    Transactions.transactions();
        		}
        		
        		else if (resp == 2)
        		{
                    Data.method = "FE";
        			Data.typeOfAccount = "Checkings";
        			Transactions.typeOfTransaction = 3;
                    Transactions.amount = totalAmount;
                    Transactions.memo = "Foriegn Exchange";
                    Transactions.transactions();
        		}
        		        	
        	}
        	else if (response == 2)
        	{
        		System.out.println("Enter 1 to restart or 2 to exit");
        		int answer = scanner.nextInt();
        		
        		if (answer == 1) {
        			ConvertCurrency();
        		}
        		else if (answer == 2) {
        			System.out.println("Thank you for using the Currency Converter");
                    System.out.println("--------------------");
                    BankMain.menu();
        		}
        	}
        	else
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