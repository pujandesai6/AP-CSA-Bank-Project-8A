import java.util.Scanner;

public class UserMenu 
{
    public static void main(String args[]) 
    {
    	System.out.println("Hello! Welcome to Vito Cangerizzi Bank!");
    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    	System.out.println("Voted most trusted bank of New Jesery since 2023!");
    
   
        for (int i = 0; i < 12; i++ )
        {
            for (int v = 0; v < 28; v++)
            {
                if (i > 0 && (v == 23 - i || v == i - 1))
                {
                    System.out.print("$$");
                    continue;
                }
                System.out.print(" ");
            }
            for (int c = 1; c < 22; c++)
            {
                if (i > 0 && (i == 1 || i == 11) && c <= 16)
                {
                    System.out.print("$");
                }
                else
                {
                    System.out.print("");
                }
                if (c == 1 && i != 0)
                {
                System.out.print("$$");
                }
            }
            System.out.println();
        }
        
        login();
    }
    
    public static void login()
    {
        Scanner scanner = new Scanner(System.in);
    	int answer = 0;
    	System.out.print("Would you like to login? Type 1 for yes or type 2 for no: ");
    	answer = scanner.nextInt();
    
    	if ( answer == 1)
    	{
    		//lead to login code
    	}
    	else if ( answer == 2)
    	{
    		System.out.println("Thank you, have a good day!");
    		//terminate program
    	}
    	else 
    	{
    		login();
    	}
    }
}