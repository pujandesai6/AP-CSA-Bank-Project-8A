import java.util.Scanner;

public class ATM {
    static Scanner input = new Scanner(System.in);
    static private String cardNumber = "1";
    // actual cardNumber = 1234567891012131
    static private int Pin = 1234;
    public double addCustom = 0;
    public static double checkingBalance = 0;

    // withdrawal

    static double takeCustom = 0;

    public static void main(String[] args) {
        int login = 0;

        // use the do-while loop
        System.out.println();
        System.out.print("Enter Card Number: ");
        String userCardNumber = input.next();
        if (userCardNumber.equals(cardNumber)) {
            System.out.println();
            System.out.print("Enter the Pin Number: ");
            int userPin = input.nextInt();
            if (userPin == Pin) {
                System.out.println("Welcome to the account.");
                System.out.println("What would you like to access?\n1.Checking Account \n2.Savings Account");
                int checkingOrSavings = input.nextInt();

                do {
                    if (checkingOrSavings == 1) {
                        login++;
                        Checking checking1 = new Checking(addCustom, takeCustom, addCustom, addCustom, addCustom,
                                addCustom, addCustom, addCustom, addCustom, addCustom);
                        System.out.println("Welcome to the Checking Account.");
                        System.out.println(
                                "Your current balance in the Checking Account is $" + checkingBalance); // add
                        // checking
                        // balance
                        System.out
                                .println("Would you like to make a Deposit or Withdraw money?\n1.Deposit\n2.Withdraw");
                        double depositOrWithdraw = input.nextDouble();

                        if (depositOrWithdraw == 1) {
                            System.out.println(
                                    "How much would you like to Deposit?");
                            addCustom = input.nextDouble();
                            Checking.Checking(addCustom);
                            System.out.println(
                                    "You deposited $" + addCustom + "\nYour balance is now $"
                                            + checking1.getaddCustom());

                        }
                        System.out.println("Would you like to make any changes?\n1.Yes \n2.No");
                        int changeToWithdrawal = input.nextInt();
                        if (changeToWithdrawal == 1) {
                            addCustom = 2;
                        }

                    } else if (addCustom == 2) {
                        System.out.println(
                                "How much would you like to withdraw?\n$10, $20, $50, $100, or a custom amount?\n(please write \"11\" if you want a custom withdrawal.)");
                        // double withdrawCheckingAccount = input.nextDouble();

                        // if (withdrawCheckingAccount == 10) {
                        // System.out.println("You have taken $" + checking1.balancetake10()
                        // + "\nYour balance is now $" + checkingBalance);
                        // } else if (withdrawCheckingAccount == 20) {
                        // System.out.println("You have take $" + checking1.balancetake20()
                        // + "\nYour balance is now $" + checkingBalance);
                        // } else if (withdrawCheckingAccount == 50) {
                        // System.out.println("You have taken $" + checking1.balancetake50()
                        // + "\nYour balance is now $" + checkingBalance);
                        // } else if (withdrawCheckingAccount == 100) {
                        // System.out.println("You have taken $" + checking1.balancetake100()
                        // + "\nYour balance is now $" + checkingBalance);
                        // } else if (withdrawCheckingAccount == 11) {
                        // System.out.println("Please type in how much money you wish to withdraw...");
                        // addCustom = input.nextDouble();
                        // System.out.println(
                        // "You have taken $" + takeCustom + "\nYour balance is now $" +
                        // checkingBalance);
                        // }
                        // }

                        // }

                        // else if (checkingOrSavings == 2) {
                        // System.out.println("Welcome to the Savings Account.");
                        // System.out.println("Your current balance in the Savings Account is "); // add
                        // savings balance

                    }

                }

                while (login == 1);
            }

            else {
                System.out.println("Incorrect Pin");
            }

        } else

        {
            System.out.println("Incorrect Card Number");
        }

    }

}