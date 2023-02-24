import java.util.Scanner;
import java.io.IOException;

public class Account {
    public static void variableupdate(){
        if(Data.typeOfAccount.equals("Investors")){
            InvestorsAccount.accountmain();
        }
        else{
            accountmain();
        }
    }

    public static void accountmain(){
        Data.method = "Account"; //sets the current method as pathway
        if(Data.typeOfAccount.equals("Savings")){ 
            Transactions.balanceindex = 3;
        }
        else{
            Transactions.balanceindex = 4;
        }
        Scanner input = new Scanner(System.in);
        int choice = 0;
        System.out.println("--------------------");
        System.out.println("What would you like to do?");
        do{
            System.out.println("1. View Balance");
            System.out.println("2. View Transaction Statement");
            System.out.println("3. Transfer");
            System.out.println("4. Return home.");
            System.out.print("Enter Choice: ");
            choice = input.nextInt();
        }
        while(choice < 0 || choice > 4);
        if(choice == 1){
            System.out.println("--------------------");
            try {
                Transactions.getbalance();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(Data.typeOfAccount + " Balance: $" + Data.customerdata.get(Transactions.balanceindex));
            accountmain();
        }
        if(choice == 2){
            try {
                Transactions.statement();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(choice == 3){
            Transactions.transfer();
        }
        if(choice == 4){
            BankMain.bankmenu();
        }
    }
}
