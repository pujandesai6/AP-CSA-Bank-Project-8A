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
    public static int typeOfTransaction = 0; //1 = Deposit, 2 = Withdraw, 3 = Transfers 
    public static double amount = 0.0; //sets the amount that is being deposited, withdrawed, or transfered
    public static String memo = ""; //description of what the transaction was for
    private static String username = Data.generaldata.get(0); //gets username from array
    public static int balanceindex = 0; //where the balance is in the data file/array list
    public static double balance = 0.0; //converts the string balance from array to double
    public static String transactiontype = ""; //electronic transfer, deposit, withdraw, payment, foreign exchange
    private static String originalTypeOfAccount = ""; //for transfers: when otheraccount is set a typeOfTransaction
    private static String originalMethod = ""; //for transfers: when othemethod is set as typeOfMethod
    public static String investmenttype = ""; //bonds, cods, annuities - for moneymarket
    private static String accounts[] = {"Savings", "Checkings", "Investors", "Loans"};
    private static Scanner input = new Scanner(System.in);
    public static String transfertype = "";

    public static void ATM() throws IOException{
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
        choice = 0;
        do{
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. View Balance");
            System.out.println("4. Sign Out.");
            System.out.print("Enter Choice: ");
            choice = input.nextInt();
        }
        while(choice < 0 || choice > 4);


        if(choice == 1){
            typeOfTransaction = 1;
            System.out.println("--------------------");
            System.out.print("How much would you like to deposit? $");
            amount = input.nextDouble();
            memo = "ATM Deposit";
        }
        else if(choice == 2){
            Boolean overDraft = false;
            do{
                typeOfTransaction = 2;
                System.out.println("--------------------");
                System.out.print("How much would you like to withdraw? $");
                amount = input.nextDouble();
                overDraft = overdraft();
            }
            while(overDraft == true);
            memo = "ATM Withdrawl";
        }
        else if(choice == 3){
            System.out.println("--------------------");
        }
        else{
            UserMenu.main(null);
        }
        transactions();
    }

    public static void variables(){
        if(Data.typeOfAccount.equals("Investors")){
            balanceindex = 2;
        }
        else if(Data.typeOfAccount.equals("Savings")){
            balanceindex = 3;
        }
        else if(Data.typeOfAccount.equals("Checkings")){
            balanceindex = 4;
        }
        else if(Data.typeOfAccount.equals("Loans")){

        }
    }
    
    public static void getbalance() throws IOException{
        variables();
        String filename = username + "_" + Data.typeOfAccount + "Transactions.csv"; //names csv file based on username
        File file = new File(filename); //describing the file
        FileWriter fw = new FileWriter(file , true);
        FileReader fr = new FileReader(file);
        BufferedWriter bw = new BufferedWriter(fw);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        String bal = "";
        if(br.readLine() != null){
            while((line = br.readLine()) != null){ //gets last balance of transaction history
                String [] values = line.split(",");
                bal = values[4];
            }
        }
        else{
            if(Data.typeOfAccount.equals("Investors")){
                bal = Data.investordata.get(balanceindex);
            }
            if(Data.typeOfAccount.equals("Loans")){
                bal = "0";
            }
            else{
                bal = Data.customerdata.get(balanceindex);
            }
        }
        balance = Double.parseDouble(bal);
        if(Data.typeOfAccount.equals("Investors")){
            bal = Data.investordata.set(balanceindex, bal);
        }
        else{
            bal = Data.customerdata.set(balanceindex, bal);
        }
        br.close();
        bw.close();
        fw.close();
        fr.close();
    }

    public static void transactions() throws IOException{
        getbalance();
        if(typeOfTransaction == 1){ //gets balance after deposit
            transactiontype = "Deposit";
            balance = balance + amount; 
            balance = Math.round(balance*100.0)/100.0;
        }
        else if(typeOfTransaction == 2){//gets balance after withdrawl
            transactiontype = "Withdrawl";
            balance = balance - amount; 
            balance = Math.round(balance*100.0)/100.0;
        }
        else if(typeOfTransaction == 3){//gets balance after transfer
            transactiontype = "Transfer";
            balance = balance - amount; 
            balance = Math.round(balance*100.0)/100.0;
        }
        else if(typeOfTransaction == 4){//gets balance after transfer
            transactiontype = "Recieved";
            balance = balance + amount; 
            balance = Math.round(balance*100.0)/100.0;
        }

        if(Data.typeOfAccount.equals("Investors")){ 
            Data.investordata.set(balanceindex, balance + "");
            System.out.println("Investor Balance: " + Data.investordata.get(balanceindex));
        } 
        else{
            Data.customerdata.set(balanceindex, balance + "");
            System.out.println(Data.typeOfAccount + " Balance: " + Data.customerdata.get(balanceindex));
        }

        addTransaction();
    } 

    public static void transfer() throws IOException{
        originalMethod = Data.method; 
        originalTypeOfAccount = Data.typeOfAccount;
        //System.out.println(originalTypeOfAccount);
        Data.method = "Transfer";
        memo = "Electronic Transfer";
        if(transfertype.equals("Out")){
            typeOfTransaction = 3;
        }
        else{
            typeOfTransaction = 4;
        }

        int i = 1;
        String otheraccount[] = new String[3];
        for(String str: accounts){
            if(str.equals(Data.typeOfAccount)){
            }
            else{
                otheraccount[i-1] = str;
                i++;
            }
        }
        int account = 0;
        if(transfertype.equals("Out")){
            System.out.println("Where would you like to transfer money to?");
        }
        else{
            System.out.println("Where would you like to transfer money from?");
        }
        do{
            System.out.println("1. " + otheraccount[0]);
            System.out.println("2. " + otheraccount[1]);
            System.out.println("3. " + otheraccount[2]); //if user were to have another account
            System.out.print("Enter Choice: ");
            account = input.nextInt();
        }
        while(account < 0 || account > 3);
        String transferaccount = otheraccount[account - 1];
        System.out.println(transferaccount);
        Boolean overDraft = false;
        do{
            System.out.println("--------------------");
            System.out.print("How much would you like to transfer: $");
            amount = input.nextDouble();
            if(transfertype.equals("Out")){
                overDraft = overdraft();
            }
            else if(transfertype.equals("In")){
                Data.typeOfAccount = transferaccount;
                overDraft = overdraft();
                Data.typeOfAccount = originalTypeOfAccount;
            }
        }
        while(overDraft == true);
        getbalance();
        variables();
        transactions();
        Data.method = originalMethod;
        Data.typeOfAccount = transferaccount;
        if(transfertype.equals("Out")){
            typeOfTransaction = 4;
        }
        else{
            typeOfTransaction = 3;
        }
        variables();
        transactions();

    }

    public static Boolean overdraft() throws IOException{
        Boolean overdraft = false;
        getbalance();
        if(amount > balance){
            System.out.println("Your " + (Data.typeOfAccount).toLowerCase() + " account balance is lower than the transaction amount. Change the amount to avoid overdraft fees.");
            overdraft = true;
        }
        else{
            overdraft = false;
        }
        return overdraft;
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
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String formatteddate = dateFormat.format(today);
        bw.write(formatteddate + ",");
        bw.write(transactiontype + ",");
        bw.write(Math.round(amount * 100.00)/100.0 + ",");
        bw.write(memo + ",");
        bw.write(balance + "");
        bw.close();
        if(!originalTypeOfAccount.equals("")){
            Data.typeOfAccount = originalTypeOfAccount;
        }
        if(Data.method.equals("ATM")){
            System.out.println("--------------------");
            ATM();
        }
        else if(Data.method.equals("Transfer")){
            return;
        }
        else if(Data.method.equals("moneymarket")){
            MoneyMarket.menu();
        }
        else if(Data.method.equals("bonds")){
            Bonds.bondsmain();
        }
        else if(Data.method.equals("Investors")){
            InvestorsAccount.investormenu();
        }
        else if(Data.method.equals("loan payment")){
             Loans.payment();
        }
        else if(Data.method.equals("FE")){
            BankMain.menu();
        }
        else{
            Account.accountmain();
        }

    }
    
    public static void investmentclass() throws IOException{
        getbalance();
        if(typeOfTransaction == 6){//gets balance after transfer
            transactiontype = "Investment Bought";
            balance = balance - amount; 
            balance = Math.round(balance*100.0)/100.0;
        }
        else if(typeOfTransaction == 7){//gets balance after transfer
            transactiontype = "Investment Sold";
            balance = balance + amount; 
            balance = Math.round(balance*100.0)/100.0;
        }
        memo = investmenttype;
        Data.typeOfAccount = "Investors";
        addTransaction();
    }

    public static void loans() throws IOException{
        transactiontype = "Loan Payment";
        balance = balance - amount; 
        balance = Math.round(balance*100.0)/100.0;
        addTransaction();
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
        String strdate = "";
        String strtype = "";
        String stramount = "";
        String strmemo = "";
        String strbalance = "";
        while((line = br.readLine()) != null){
            String [] values = line.split(",");
            strdate = values[0];
            strtype = values[1];
            stramount = values[2];
            strmemo = values[3];
            strbalance = values[4];
            statement.addRow(new Object[] {strdate,strtype,stramount,strmemo,strbalance});
            statement.addRow(new Object[] {""});
        }
        br.close();
        fw.close();
        
        frame.add(table);
        frame.setSize(800,800);
        frame.setLocationRelativeTo(null);  
        frame.setVisible(true);
        System.out.println("Statement has been opened in a new tab.");


        if(Data.method.equals("Loans")){
            Loans.loansmenu();
        }
        else if(Data.typeOfAccount.equals("Investors")){
            InvestorsAccount.investormenu();
        }
        else if(Data.method.equals("FE")){
            BankMain.menu();
        }
        else{
            Account.accountmain();
        }
    }
}

