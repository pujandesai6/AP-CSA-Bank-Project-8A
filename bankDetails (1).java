public class bankDetails {
    public static void main(String args[]) {
    System.out.println("Hello! Welcome to Vito Cangerizzi Bank!");
    
   
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
    System.out.println("Username:");
    System.out.println("Password:");
    
    //code for user and password (other group code)
    
    System.out.println("Not registered yet? Sign up here!");
    
    //code for registering (other group code)
    //Information needed(user input): name, SSN, DOB, age, home address, billing address, contact information, which bank location to use, etc.
    //finish creating account button (?)
    //bring back to intial sign up screen to sign in
    
    System.out.println("Which bank location will you be using today?");
    System.out.println("1) The Bank of Vito Cangerizzi 2) Vito Southern Bank of New Jersey 3) Vito Western Bank of New Jersey  4) Vito Eastern Bank of New Jersey");
    
    //user selection of bank (other group code)
    
    System.out.print("Welcome to Vito Cangerizzi LOCATION! What would you like to do today?");
    
    //make a withdrawal -> to withdrawal code (other group code)
    //make a deposit -> to deposit code (other group code)
    
    //Vedant C Patel currency conversion code
    
    System.out.print("Thank you for using Vito Cangerizzi Bank!");
    
    }
}