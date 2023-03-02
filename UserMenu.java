import java.util.Scanner;
import java.io.*;
public class UserMenu{ 
    public static void main(String[] args) throws IOException{
        PrintBankIntro();
        BankLocations.blmain();
        Scanner input = new Scanner(System.in);
        int choice = 0;
        System.out.println("Welcome to the " + BankLocations.bankname + "!");
        System.out.println("What would you like to do?");
        do{
            System.out.println("1. Visit Bank Website\n2. Use ATM");
            System.out.print("Enter Choice: ");
            choice = input.nextInt();
            System.out.println("--------------------");
        }
        while(choice < 0 || choice > 2);
        if(choice == 1){
            CustomerClass.menu();
        }
        else{
            ATM.access();
        }
    }

    public static void PrintBankIntro() {
        System.out.println("Hello! Welcome to Vito Cangerizzi Bank!");
    	System.out.println("--------------------");
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

        System.out.println("\n");
    }
}

