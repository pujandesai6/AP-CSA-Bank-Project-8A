import java.util.Scanner;
import java.io.IOException;
import java.lang.Math;


public class BankMain {
    public static void menu() throws IOException{
        String username = Data.generaldata.get(0);
        try {
            Data.customerarray(username ,0);
            Data.investorarray(username ,0);
            Data.card(username ,0);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        bankmenu();
    }

    public static void bankmenu() throws IOException{
        System.out.println("--------------------");
        System.out.println("Welcome, " + Data.generaldata.get(0) + "!");
        System.out.println("--------------------");
        System.out.println("What would you like to do?");
        int choice = 0;
        Scanner input = new Scanner(System.in); //asks user where to go
        do{
            System.out.println("1. View Savings Account");
            System.out.println("2. View Checkings Account");
            System.out.println("3. View Investors Account");
            System.out.println("4. View Cards");
            System.out.println("5. View Loans");
            System.out.println("6. View Foriegn Exchange");
            System.out.println("7. View Safety Deposit Box");
            System.out.println("8. Sign Out");
            System.out.print("Enter Your Choice: ");
            choice = input.nextInt();
        }
        while(choice < 0 && choice > 6);
        if(choice == 1){
            Data.typeOfAccount = "Savings";
            Account.variableupdate();
        }
        else if(choice == 2){
            Data.typeOfAccount = "Checkings";
            Account.variableupdate();
        }
        else if(choice == 3){
            Data.typeOfAccount = "Investors";
            Account.variableupdate();
        }
        else if(choice == 4){
            Card.cardmain();
        }
        else if(choice == 5){ 
            Loans.loansmenu();
        }
        else if(choice == 6){
            ForiegnExchange.femain();
        }
        else if(choice == 7){
            System.out.println("--------------------");
            new SafetyDepositBox();
            System.out.println("--------------------");
            System.out.println("Your safety deposit box number is " + Data.generaldata.get(8));
            System.out.println("Please visit " + BankLocations.bankname + " to access your safety deposit box.");
            bankmenu();
        }
        else if(choice == 8){
            Data.customerdata.clear(); //clears all data 
            Data.generaldata.clear(); //clears all data 
            Data.investordata.clear(); //clears all data 
            Data.ATMarray.clear(); //clears all data 
            Data.typeOfAccount = ""; //clears all data 
            Data.method = ""; //clears all data 
            UserMenu.main(null);
        }
    }
}
