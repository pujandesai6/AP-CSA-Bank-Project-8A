
//Mihir, Matt, Rob's ATM Class
import java.util.Scanner;

public class ATM {

    // private static ArrayList<SavingsAccount> savingsList = new
    // ArrayList<SavingsAccount>();
    static Scanner input = new Scanner(System.in);
    // placeholder
    static private String cardNum = "000000";

    public static void main(String[] args) {

        Boolean login = false;
        int Pin = 0;
        // int ATMbalance
        double transferAmount = 0;
        String name = " ";
        SavingsAccount sa = new SavingsAccount("SA", name, 1000);
        CheckingAccount ca = new CheckingAccount("CA", name, 500);
        String userCardNum;

    // use the do-while loop
        while (!login) {

            System.out.println("Welcome to an ATM of The Bank of Cangerizzi");
            System.out.println("What is your card number: ");
            userCardNum = input.next();

            if (!userCardNum.equals(cardNum)) {
                System.out.println("Invalid Card Number");
                continue;
            }
            System.out.print("Please set up your Pin: ");
            Pin = input.nextInt();
            String newPin = String.valueOf(Pin);
            if (newPin.length() > 5 || newPin.length() < 4) {
                System.out.println("Your pin number must be 4 integers.");

            } else {
                login = true;
            }
        }
        while (login) {
            System.out.println("---Welcome---");
            System.out.println();
            System.out.print("Enter your Pin Number again: ");
            int userPin = input.nextInt();
            int checkingOrSaving = 0;
            if (userPin == Pin) {
                System.out.println("Welcome to your account.");
                System.out.println(
                        "What would you like to access?\n1.Checking Account\n2.Savings Account\n3.Wire Transfer");
                checkingOrSaving = input.nextInt();

                if (checkingOrSaving == 1) {
                    // checking account
                    CheckingAccount.createCheckingAccount();
                    System.out.print(CheckingAccount.printList());
                } else if (checkingOrSaving == 2) {
                    SavingsAccount.createSavingsAccount();
                    System.out.print(SavingsAccount.printList());
                } else if (checkingOrSaving == 3) {
                    System.out.println("Would you like to make a wire transfer between accounts?\n1.Yes\n2.No");
                    int wireYesNo = input.nextInt();
                    if (wireYesNo == 1) {

                        System.out.println("To which account?\n1.From Checking To Savings\n2.From Savings To Checking");
                        int wireChoice = input.nextInt();
                        if (wireChoice == 1) {
                            System.out.println(
                                    "How much would to like to transfer from the Checking account to the Savings account?\nYou have $"
                                            + ca.getBalance() + " in your checking account.\n" + "You also have $"
                                            + sa.getBalance() + " in your savings account.");
                            System.out.print("$");
                            transferAmount = input.nextDouble();
                            System.out.print("Checking Account: ");
                            ca.withdraw(transferAmount);
                            System.out.print("Savings Account: ");
                            sa.deposit(transferAmount);
                            System.out.println("Wire transfer successful.");
                        } else {
                            System.out.println(
                                    "How much would to like to transfer from the Savings account to the Checking account?\nYou have $"
                                            + sa.getBalance() + " in your savings account.\n" + "You also have $"
                                            + ca.getBalance() + " in your checking account.");
                            System.out.print("$");
                            transferAmount = input.nextDouble();
                            System.out.print("Savings Account: ");
                            sa.withdraw(transferAmount);
                            System.out.print("Checking Account: ");
                            ca.deposit(transferAmount);
                            System.out.println("Wire transfer successful.");
                        }
                    } else {
                        continue;
                    }
                }
            } else {
                System.out.println("Incorrect Pin");
                continue;
            }
        }
    }
}
