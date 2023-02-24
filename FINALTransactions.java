import java.util.*;
import java.lang.Math;
import java.time.LocalDate;
import java.io.*;
import java.text.SimpleDateFormat; 
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Transactions {
    public static int typeOfTransaction = 0;
    public static String amount = "";
    public static String memo = "";
    private static String username = Data.generaldata.get(0); //gets username from array
    public static int balanceindex = 0;
    public static double balance = 0.0;
    public static String transactiontype = "";
    private static String originalTypeOfAccount = "";
    private static String originalMethod = "";
    public static String investmenttype = "";

    public static void ATM(){
        Scanner input = new Scanner(System.in);
        int choice = 0;
        System.out.println("Select an Account");
        do{
            System.out.println("1. Savings Account");
            System.out.println("2. Checkings Account");
            System.out.println("3. Sign Out");
            System.out.print("Enter Choice: ");
            choice = input.nextInt();
        }
        while(choice < 0 || choice > 3); 
        if(choice == 1){
            Data.typeOfAccount = "Savings";  //updates type of account if user picked savings
            balanceindex = 3;
        }
        else if(choice == 2){
            Data.typeOfAccount = "Checkings"; //updates type of account if user picked checkings
            balanceindex = 4;
        }
        else{
            UserMenu.main(null); //brings user to bank menu/home
        }

        System.out.println("--------------------");
        System.out.println("What would you like to do?");
        do{
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Sign Out.");
            System.out.print("Enter Choice: ");
            typeOfTransaction = input.nextInt();
        }
        while(typeOfTransaction < 0 || typeOfTransaction > 3);


        if(typeOfTransaction == 1){
            System.out.println("--------------------");
            System.out.println("How much would you like to deposit?");
            System.out.print("$");
            amount = input.next();
            memo = "ATM Deposit";
        }
        else if(typeOfTransaction == 2){
            Boolean overDraft = false;
            do{
                System.out.println("--------------------");
                System.out.println("How much would you like to withdraw?");
                System.out.print("$");
                amount = input.next();
                overDraft = overdraft();
            }
            while(overDraft == true);
            memo = "ATM Withdrawl";
        }
        else{
            UserMenu.main(null);
        }
        try {
            transactions();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void transfer(){
        originalMethod = Data.method; //sets pathway for the method it came from
        Data.method = "Transfer"; //sets pathway for this method 
        memo = "Electronic Transfer"; //sets memo
        typeOfTransaction = 3; //sets the transaction as Transfer
        String otheraccount1 = "";
        String otheraccount2 = "";
        originalTypeOfAccount = Data.typeOfAccount;
        Scanner input = new Scanner(System.in); //asks user where to transfer money to
        int account = 0;
        if(Data.typeOfAccount.equals("Savings")){ //checks for which account is recieving money
            otheraccount1 = "Checkings";
            otheraccount2 = "Investors";
        }
        else if(Data.typeOfAccount.equals("Investors")){ //checks for which account is recieving money
            otheraccount1 = "Savings";
            otheraccount2 = "Checkings";
        }
        else{
            otheraccount1 = "Savings";
            otheraccount2 = "Investors";
        }
        System.out.println("--------------------");
        System.out.println("Where would you like to transfer money to?");
        do{
            System.out.println("1. " + otheraccount1);
            System.out.println("2. " + otheraccount2); //if user were to have another account
            System.out.print("Enter Choice: ");
            account = input.nextInt();
        }
        while(account < 0 || account > 2);

        String transferaccount = "";
        if(account == 1){
            if(otheraccount1.equals("Savings")){
                transferaccount = "Savings";
            }
            else if(otheraccount1.equals("Checkings")){
                transferaccount = "Checkings";
            }
            else if(otheraccount1.equals("Investors")){
                transferaccount = "Investors";
            }
        }
        if(account == 2){
            if(otheraccount2.equals("Savings")){
                transferaccount = "Savings";
            }
            else if(otheraccount2.equals("Checkings")){
                transferaccount = "Checkings";
            }
            else if(otheraccount2.equals("Investors")){
                transferaccount = "Investors";
            }
        }

        Boolean overDraft = false;
        do{
            System.out.println("--------------------");
            System.out.print("How much would you like to transfer: $");
            amount = input.next();
            overDraft = overdraft();
        }
        while(overDraft == true);
        if(Data.typeOfAccount.equals("Savings")){ //checks for which account is recieving money
            balanceindex = 3;
        }
        else if(Data.typeOfAccount.equals("Investors")){ //checks for which account is recieving money
            balanceindex = 2;
        }
        else if(Data.typeOfAccount.equals("Checkings")){
            balanceindex = 4;
        }
        try {
            transactions(); //calls transactions to update the transaction statement and balance of the account which is TRANSFERRING money
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Data.method = originalMethod;
        Data.typeOfAccount = transferaccount;
        typeOfTransaction = 4;
        if(Data.typeOfAccount.equals("Savings")){ //checks for which account is recieving money
            balanceindex = 3;
        }
        else if(Data.typeOfAccount.equals("Investors")){ //checks for which account is recieving money
            balanceindex = 2;
        }
        else if(Data.typeOfAccount.equals("Checkings")){
            balanceindex = 4;
        }
        try {
            transactions(); //calls transactions to update the transaction statement and balance of the account which is Recieving money
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void getbalance() throws IOException{
        String filename = username + "_" + Data.typeOfAccount + "Transactions.csv"; //names csv file based on username
        File file = new File(filename); //describing the file
        FileWriter fw = new FileWriter(file , true);
        FileReader fr = new FileReader(file);
        BufferedWriter bw = new BufferedWriter(fw);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        String bal = "";
        if(br.readLine() == null) {
            if(Data.typeOfAccount.equals("Investors")){ //gets balance from array if they have an empty transactions history
                bal = Data.investordata.get(balanceindex);
            } 
            else{
                bal = Data.customerdata.get(balanceindex);
            }
        }
        else{
            while((line = br.readLine()) != null){ //gets last balance of transaction history
                String [] values = line.split(",");
                bal = values[4];
            }
        }
        if(Data.typeOfAccount.equals("Investors")){ //gets balance from array if they have an empty transactions history
            Data.investordata.set(balanceindex, bal);
        } 
        else{
            Data.customerdata.set(balanceindex, bal);
        }

        balance = Double.parseDouble(bal);
        br.close();
        bw.close();
    }

    public static void transactions() throws IOException{
        getbalance();
        double amt = Double.parseDouble(amount);
        transactiontype = "";
        if(typeOfTransaction == 1){ //gets balance after deposit
            transactiontype = "Deposit";
            balance = balance + amt; 
            balance = Math.round(balance*100.0)/100.0;
        }
        else if(typeOfTransaction == 2){//gets balance after withdrawl
            transactiontype = "Withdrawl";
            balance = balance - amt; 
            balance = Math.round(balance*100.0)/100.0;
        }
        else if(typeOfTransaction == 3){//gets balance after transfer
            transactiontype = "Transfer";
            balance = balance - amt; 
            balance = Math.round(balance*100.0)/100.0;
        }
        else if(typeOfTransaction == 4){//gets balance after transfer
            transactiontype = "Recieved";
            balance = balance + amt; 
            balance = Math.round(balance*100.0)/100.0;
        }
        else if(typeOfTransaction == 5){//gets balance after transfer
            transactiontype = "Loan";
            balance = balance + amt; 
            balance = Math.round(balance*100.0)/100.0;
        }


        String newbalance = String.valueOf(balance);    
        if(Data.typeOfAccount.equals("Investors")){ 
            Data.investordata.set(balanceindex, newbalance);
            System.out.println("Investor Balance: " + Data.investordata.get(2));
        } 
        else{
            Data.customerdata.set(balanceindex, newbalance);
        }

        addTransaction();
    }

    public static void addTransaction() throws IOException{
        String filename = username + "_" + Data.typeOfAccount + "Transactions.csv"; //names csv file based on username
        File file = new File(filename); //describing the file
        FileWriter fw = new FileWriter(file , true);
        FileReader fr = new FileReader(file);
        BufferedWriter bw = new BufferedWriter(fw);
        BufferedReader br = new BufferedReader(fr);
        if(br.readLine() == null){
            bw.write("Date,");
            bw.write("Type,");
            bw.write("Amount,");
            bw.write("Memo,");
            bw.write("Balance");
        }
        bw.newLine();
        String line = "";
        String newbalance = String.valueOf(balance);
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String formatteddate = dateFormat.format(today);
        bw.write(formatteddate + ",");
        bw.write(transactiontype + ",");
        bw.write(amount + ",");
        bw.write(memo + ",");
        bw.write(newbalance);
        bw.close();

        Data.typeOfAccount = originalTypeOfAccount;
        if(Data.method.equals("ATM")){
            ATM();
        }
        else if(Data.method.equals("Transfer")){
            return;
        }
        else if(Data.method.equals("Investors")){
            InvestorsAccount.investormenu();
        }
        else{
            Account.accountmain();
        }
    }

    public static void statement() throws IOException{
        String filename = Data.generaldata.get(0) + "_" + Data.typeOfAccount + "Transactions.csv"; //names csv file based on username
        JFrame frame = new JFrame();
        DefaultTableModel statement = new DefaultTableModel(); 
        JTable table = new JTable(statement); 
        statement.addColumn("");
        statement.addColumn("");
        statement.addColumn("");
        statement.addColumn("");
        statement.addColumn("");
        statement.addRow(new Object[] {"", "","", "", ""});
        File file = new File(filename); //describing the file
        FileWriter fw = new FileWriter(file , true);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        String date = "";
        String type = "";
        String amount = "";
        String memo = "";
        String balance = "";
        while((line = br.readLine()) != null){
            String [] values = line.split(",");
            date = values[0];
            type = values[1];
            amount = values[2];
            memo = values[3];
            balance = values[4];
            statement.addRow(new Object[] {date,type,amount,memo,balance});
            statement.addRow(new Object[] {""});
        }
        br.close();
        fw.close();
        
        frame.add(table);
        frame.setSize(800,800);
        frame.setLocationRelativeTo(null);  
        frame.setVisible(true);
        System.out.println("Statement has been opened in a new tab.");

        if(Data.typeOfAccount.equals("Investors")){
            InvestorsAccount.investormenu();
        }
        else if(Data.method.equals("FE")){
            BankMain.menu();
        }
        else{
            Account.accountmain();
        }
    }

    public static Boolean overdraft(){
        Boolean overdraft = false;
        try {
            getbalance();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        if(Double.parseDouble(amount) > balance){
            System.out.println("Your " + (Data.typeOfAccount).toLowerCase() + " account balance is lower than the " + Data.typeOfAccount.toLowerCase() + " amount. Change the amount to avoid overdraft fees.");
            overdraft = true;
        }
        else{
            overdraft = false;
        }
        return overdraft;
    }

    public static void investmentclass() throws IOException{
        balance = balance - Double.parseDouble(amount);
        transactiontype = "Investment";
        memo = investmenttype;
        Data.typeOfAccount = "Investors";
        addTransaction();
    }
}

