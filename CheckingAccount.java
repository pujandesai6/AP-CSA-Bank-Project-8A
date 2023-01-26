import java.util.Scanner;

public class CheckingAccount {
    private int accountNumber;
    private double balance;

    public CheckingAccount() {
        this.accountNumber = (int)(Math.random() * 1000000);
        this.balance = 0;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount >= 100) {
            balance += amount;
            System.out.println("Deposit Successful! Your new balance is: " + balance);
        } else {
            System.out.println("Deposit unsuccessful - minimum deposit is $100.00");
        }
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal Successful! Your new balance is: " + balance);
        } else {
            System.out.println("Insufficient Funds!");
        }
    }

    public static void main(String[] args) {
        CheckingAccount account = new CheckingAccount();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to your Checking Account!");
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Current Balance: " + account.getBalance());

        while (true) {
            System.out.println("Please select an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("Enter the amount you wish to deposit:");
                double depositAmount = scanner.nextDouble();
                account.deposit(depositAmount);
            } else if (choice == 2) {
                System.out.println("Enter the amount you wish to withdraw:");
                double withdrawAmount = scanner.nextDouble();
                account.withdraw(withdrawAmount);
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid Choice!");
            }
        }


        System.out.println("Thank you for using our services!");
    }
}
