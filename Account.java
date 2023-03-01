import java.util.Scanner;
import java.io.IOException;

public class Account {
    public static void variableupdate() throws IOException{
        if(Data.typeOfAccount.equals("Investors")){
            InvestorsAccount.accountmain();
        }
        else{
            accountmain();
        }
    }

    public static void accountmain() throws IOException{
        Data.method = "Account"; //sets the current method as pathway
        Scanner input = new Scanner(System.in);
        int choice = 0;
        System.out.println("--------------------");
        System.out.println("What would you like to do?");
        do{
            System.out.println("1. View Balance");
            System.out.println("2. View Transaction Statement");
            System.out.println("3. Transfer Money to " + Data.typeOfAccount);
            System.out.println("4. Transfer Money out of " + Data.typeOfAccount);
            System.out.println("5. Deposit money into " + Data.typeOfAccount);
            System.out.println("6. Return home.");
            System.out.print("Enter Choice: ");
            choice = input.nextInt();
        }
        while(choice < 0 || choice > 6);
        if(choice == 1){
            System.out.println("--------------------");
            //Transactions.variables();
            Transactions.getbalance();
            System.out.println(Data.typeOfAccount + " Balance: $" + Data.customerdata.get(Transactions.balanceindex));
            accountmain();
        }
        else if(choice == 2){
            Transactions.statement();
        }
        else if(choice == 3){
            System.out.println("--------------------");
            Transactions.transfertype = "In";
            Transactions.transfer();
        }
        else if(choice == 4){
            System.out.println("--------------------");
            Transactions.transfertype = "Out";
            Transactions.transfer();
        }
        else if(choice == 5){
            System.out.print("How much would you like to deposit: $");
            Transactions.amount = input.nextDouble();
            Transactions.typeOfTransaction = 1;
            Transactions.memo = "Check/Online";
            Transactions.transactions();
        }
        else if(choice == 6){
            BankMain.bankmenu();
        }
    }
}
