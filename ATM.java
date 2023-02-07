
import java.util.ArrayList;
import java.util.Scanner;

public class ATM extends GeneralAccount {
    public ATM(String name, double balance) {
        super(name, balance);
        // TODO Auto-generated constructor stub
    }

    private static ArrayList<SavingsAccount> savingsList = new ArrayList<SavingsAccount>();
    static Scanner input = new Scanner(System.in);
    static private String checkingCardNumber;
    static private String savingsCardNumber;

    static private double checkingBalance;
    // actual cardNumber = 4 2 2 8 4 5 3 1 6 8 3 4 7 0 5 1

    public static void main(String[] args) {

        int beg = 0;
        int Pin = 0;
        double checkingSavingAmount = 0;

        // use the do-while loop
        while (beg == 0) {

            System.out.println("Welcome to an ATM of The Bank of Cangerizzi");
            System.out.println("Please set up your Pin.");
            Pin = input.nextInt();
            String newPin = String.valueOf(Pin);
            if (newPin.length() > 5 || newPin.length() < 4) {
                System.out.println("Your pin number must be 4 integers.");

            } else if (newPin.length() == 4) {
                beg++;
            }

        }
        while (beg == 1) {

            System.out.println("Welcome to the ATM");
            System.out.println();
            System.out.print("Enter the Pin Number: ");
            int userPin = input.nextInt();
            int checkingOrSaving = 0;
            if (userPin == Pin) {

                System.out.println("Welcome to the account.");
                System.out.println("What would you like to access?\n1.Checking Account \n2.Savings Account");
                checkingOrSaving = input.nextInt();

                if (checkingOrSaving == 1) {
                    // checking account

                    System.out.println("Welcome to your Checking Account!");
                    System.out.println("What is the card number?");
                    String userCheckingCardNumber = input.next();
                    if (checkingCardNumber == userCheckingCardNumber) {
                        System.out.println("Account Number: " + (int) (Math.random() * 999999999));
                        System.out.println("Current Balance: " + checkingBalance);

                        while (true) {
                            System.out.println("Please select an option:");
                            System.out.println("1. Deposit");
                            System.out.println("2. Withdraw");
                            System.out.println("3. Exit");

                            int choice = input.nextInt();

                            if (choice == 1) {
                                System.out.println("Enter the amount you wish to deposit:");
                                double depositAmount = input.nextDouble();
                                checkingBalance += (depositAmount);
                            } else if (choice == 2) {
                                System.out.println("Enter the amount you wish to withdraw:");
                                double withdrawAmount = input.nextDouble();
                                checkingBalance -= (withdrawAmount);
                            } else if (choice == 3) {
                                break;
                            } else {
                                System.out.println("Invalid Choice!");
                            }
                        }

                        System.out.println("Thank you for using our services!");
                    }
                } else if (userPin != Pin) {
                    System.out.println("Incorrect Pin");

                } else if (checkingOrSaving == 2) {

                }

            } else {

                System.out.println("Incorrect Card Number");
            }

            System.out.println("Would you like to make a wire transfer between checkingBalances?\n1.Yes\n2.No");
            int wireYesNo = input.nextInt();
            if (wireYesNo == 1) {
                System.out.println("To which checkingBalance?\n1.From Checking To Savings\n2.From Savings To Checking");
                int wireChoice = input.nextInt();
                double balance = 0;
                if (wireChoice == 1) {
                    System.out.println(
                            "How much would to like to transfer from the Checking checkingBalance to the Savings checkingBalance?");
                    checkingSavingAmount = input.nextDouble();
                    // checking
                    checkingBalance.withdrawSavings(checkingSavingAmount);

                } else if (wireChoice == 2) {
                    System.out.println(
                            "How much would to like to transfer from the Savings checkingBalance to the Checking checkingBalance?");
                    checkingSavingAmount = input.nextDouble();
                    // checking
                    checkingBalance += checkingSavingAmount;
                    // savings
                    balance -= checkingSavingAmount;
                    // nbalance.depositMoney(checkingSavingAmount);
                    System.out.println("Your Checking checkingBalance now has $" + checkingBalance);
                    System.out.println("Your Savings checkingBalance now has $" + balance);
                }

            }

        }

    }

}
