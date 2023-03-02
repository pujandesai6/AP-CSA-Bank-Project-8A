import java.util.*;
import java.io.*;
import java.lang.reflect.Array;
import javax.swing.text.DefaultStyledDocument.ElementSpec;
import java.time.LocalDate;
import java.text.SimpleDateFormat; 

public class Loans {
    private static String loantype = "";
    private static int term = 0;
    private static double finalloanamount = 0.0;
    private static double monthlypayment = 0.0;
    public static Scanner input = new Scanner(System.in);
    private static String name = "";
    private static String employername = "";
    //public static Hashtable<String, Hashtable<String, String[]>> loans = new Hashtable<String, Hashtable<String, String[]>>();
    private static ArrayList<String[]> loans = new ArrayList<String[]>();
    private static String loan[] = {};
    private static double newamount = 0.0;


    public static void loansmain() throws IOException{
        System.out.println("--------------------");
        System.out.println("Which loan would you like to apply for?");
        int choice = -1;
        while(choice < 0 || choice > 5){
            System.out.println("1. Personal Loan");
            System.out.println("2. Student Loan");
            System.out.println("3. Car Loan");
            System.out.println("4. Mortgage");
            System.out.println("5. Go Back");
            System.out.print("Enter Choice: ");
            choice = input.nextInt();
            System.out.println("--------------------");
        }
        if(choice == 1){
            loantype = "personal loan";
            loans();
        }
        else if(choice == 2){
            loantype = "student loan";
            loans();
        }
        else if(choice == 3){
            loantype = "car loan";
            loans();
        }
        else if(choice == 4){
            loantype = "mortgage";
            loans();
        }
        else if(choice == 5){
            Loans.loansmenu();
        }
    }

    public static void loans() throws IOException{
        Scanner input = new Scanner(System.in); 

        System.out.print("What would you like to name your " + loantype + "? "); //asks user for loan name
        name = input.nextLine();
        System.out.println("--------------------");

        System.out.print("What is your requested " + loantype + " amount: $"); //asks user for principle amount
        double principleamount = input.nextDouble();
        System.out.println("--------------------");
 
        double downpayment = 0.0; //asks user for downpayement only if they selected car loans or mortgage
        if(loantype.equals("mortgage")){
            do{
                System.out.print("What is down payment: $");
                downpayment = input.nextDouble();
                System.out.println("--------------------");
                if((principleamount * .2) > downpayment){
                    System.out.print("You have to put a minimum of 20% down payment.");
                }
            }
            while((principleamount * .2) > downpayment);
        }
        else if(loantype.equals("car loan")){
            System.out.print("What is down payment: $");
            downpayment = input.nextDouble();
            System.out.println("--------------------");
        }

        double loanamount = Math.round((principleamount - downpayment)*100.00)/100.00; //gets loan amount

        int time = 0;
        if(loantype.equals("mortgage")){
            time = 1;
            do{
                System.out.print("Please enter your desired loan term (in years): "); //asks user for loan term
                term = input.nextInt();
                System.out.println("--------------------");
            }
            while(term <= 0 || term > 30);
        }
        else{
            time = 12;
            System.out.print("Please enter your desired loan term (in months): "); //asks user for loan term
            term = input.nextInt();
            System.out.println("--------------------");
        }

        System.out.println("Enter your employment info"); //asks user for employment history
        input.nextLine();
        if(loantype.equals("student loan")){
            System.out.print("School Name:" + " "); //employer name
            employername = input.nextLine();
            System.out.println("--------------------");
        }
        else{
            System.out.print("Employer Name:" + " "); //employer name
            employername = input.nextLine();
            System.out.println("--------------------");
        }


        System.out.print("Your Net Income: "); //their salary
        Double salary = input.nextDouble();


        double interest = 0.0;
        if(loantype.equals("student loan")){ //calculates interest // if its student loan then its 0
            interest = Math.round(((double)(Math.random() * 1) + 5)*100.00)/100.00;
        }
        else{
            if(salary < 50000){
                interest = Math.round(((double)(Math.random() * 1) + 8)*100.00)/100.00;
            }
            else if(salary > 50000 && salary < 100000){
                interest = Math.round(((double)(Math.random() * 1) + 7)*100.00)/100.00;
            }
            else if(salary > 100000 ){
                interest = Math.round(((double)(Math.random() * 1) + 6)*100.00)/100.00;
            }
        }


        double interestAmount = Math.round(((double)(loanamount * (term/time) * (interest))/100.00)*100.00)/100.00;
        finalloanamount = Math.round(((double)(((interestAmount + loanamount) * 100.00)/100.00)));
        if(loantype.equals("mortgage")){
            term = term*12;
        }
        monthlypayment = Math.round(((double)(finalloanamount/(term)))*100.00)/100.00;

        System.out.println("--------------------");
        System.out.println("Please confirm your loan information.");
        System.out.println("Full Name: " + Data.generaldata.get(2) + " " +  Data.generaldata.get(3));
        System.out.println("Social Security Number: " + Data.generaldata.get(6));
        System.out.println("Loan Name: " + name);
        System.out.println("Type of Loan: " + loantype);
        if(loantype.equals("car loan") || loantype.equals("mortgage")){
            System.out.println("Principle Amount: $" + principleamount);
            System.out.println("Down Payment: $" + downpayment);
            System.out.println("Loan Amount: $" + loanamount);
        }
        else{
            System.out.println("Loan Amount: $" + principleamount);
        }
        if(loantype.equals("mortgage")){
            System.out.println("Term: " + term/12 + " Years");
        }
        else{
            System.out.println("Term: " + term + " Months");
        }
        System.out.println("Employer Name: " + employername);
        System.out.println("Net Income: $" + salary);
        System.out.println("Interest Rate: " + interest + "%");
        System.out.println("Interest Amount: $" + interestAmount);
        System.out.println("Total Loan Amount: $" + finalloanamount);
        System.out.println("Total Monthly Payment: $" + monthlypayment);
        System.out.println("--------------------");
        System.out.print("Confirm (y/n)? ");
        String confirm = input.next();
        if(confirm.contains("y")){
            System.out.println("Confirming Your Loan...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Your loan request has been approved! Please check your email -- " + Data.generaldata.get(7) + " -- for updates of this loan.");
            addloan();
        }
        else{
            System.out.println("Your loan request has been canceled.");
            BankMain.bankmenu();
        }
    }

    public static void addloan() throws IOException{
        String filename = Data.generaldata.get(0) + "_Loans.csv"; //names csv file based on username
        File file = new File(filename); //describing the file
        FileWriter fw = new FileWriter(file , true);
        FileReader fr = new FileReader(file);
        BufferedWriter bw = new BufferedWriter(fw);
        BufferedReader br = new BufferedReader(fr);
        if(br.readLine() == null){
            bw.write("Date,");
            bw.write("Memo,");
            bw.write("Loan Type,");
            bw.write("Term,");
            bw.write("Amount,");
            bw.write("Monthly Payment");
        }
        bw.newLine();
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String formatteddate = dateFormat.format(today);
        bw.write(formatteddate + ",");
        bw.write(name + ",");
        bw.write(loantype + ",");
        bw.write(term + ",");
        bw.write(finalloanamount + ",");
        bw.write(monthlypayment + "");
        bw.close();
        loansmenu();
    }

    public static void loansmenu() throws IOException{
        System.out.println("--------------------");
        System.out.println("What would you like do to do?");
        //getLoans();
        int choice = 0;
        do{
            System.out.println("1. View Personal Loan Account.");
            System.out.println("2. View Student Loan Account.");
            System.out.println("3. View Car Loan Account.");
            System.out.println("4. View Mortgage Account.");
            System.out.println("5. Apply for a loan.");
            System.out.println("6. View Transaction Statement");
            System.out.println("7. Return Home.");
            System.out.print("Enter Choice: ");
            choice = input.nextInt();
        }
        while(choice < 0 || choice > 7);
        
        if(choice == 1){
            loantype = "personal loan";
            showLoanNames();
        }
        else if(choice == 2){
            loantype = "student loan";
            showLoanNames();
        }
        else if(choice == 3){
            loantype = "car loan";
            showLoanNames();
        }
        else if(choice == 4){
            loantype = "mortgage";
            showLoanNames();
        }
        else if(choice == 5){
            loansmain();
        }
        else if(choice == 6){
            Data.typeOfAccount = "Loans";
            Data.method = "Loans";
            Transactions.statement();
        }
        else{
            BankMain.bankmenu();
        }
    }

    public static void showLoanNames() throws IOException{
        readfile();
        if(loans.size() != 0){
            System.out.println("--------------------");
            int i = 1;
            for(String loan[]: loans){
                System.out.println(i + ". " + loan[1]);
                System.out.println("--------------------");
                i++;
            }
            int choice = 0;
            do{
                System.out.print("Which loan would you like to see? ");
                choice = input.nextInt();
            }
            while(choice < 0 || choice > i);
            System.out.println("--------------------");
            loan = loans.get(choice - 1);
            showloans();
        }
        else{
            System.out.println("--------------------");
            System.out.println("You do not have any " + loantype + " account(s).");
            loansmenu();
        }
    }

    public static void showloans() throws IOException{
        System.out.println("Date : " + loan[0]);
        System.out.println("Loan Type : " + loan[2]);
        if(loan[2].equals("mortgage")){
            System.out.println("Term : " + (Integer.parseInt(loan[3]))/12 + " Years");
        }
        else{
            System.out.println("Term : " + loan[3] + " Months");
        }
        System.out.println("Total Loan Amount After Interest: $" + loan[4]);
        System.out.println("Monthly Payment : $" + loan[5]);
        int choice = 0;
        do{
            System.out.println("--------------------");
            System.out.println("1. Go to Back Loans Menu");
            System.out.println("2. View Payment on " + loan[1]);
            System.out.print("Enter Choice: ");
            choice = input.nextInt();
        }   
        while(choice < 0 || choice > 2);
        if(choice == 1){
            loansmenu();
        }
        else if(choice == 2){
            payment();
        }
    }

    public static void readfile() throws IOException{
        loans.clear();
        String filename = Data.generaldata.get(0) + "_Loans.csv"; //names csv file based on username
        File file = new File(filename); //describing the file
        FileWriter fw = new FileWriter(file , true);
        FileReader fr = new FileReader(file);
        BufferedWriter bw = new BufferedWriter(fw);
        BufferedReader br = new BufferedReader(fr);
        String line = ""; 
        while((line = br.readLine()) != null){
            String values[] = line.split(",");
            if(values[2].equals(loantype)){
                loans.add(values);
            }
        }
        br.close();
        bw.close();
        fw.close();
        fr.close();
    }

    public static void payment() throws IOException{
        Data.typeOfAccount = "Loans";
        System.out.println("--------------------");
        int choice = 0;
        do{
            System.out.println("What would you like to");
            System.out.println("1. Make monthly payment.");
            System.out.println("2. Configure monthly payment.");
            System.out.println("3. Go Back.");
            System.out.print("Enter Choice: ");
            choice = input.nextInt();
        }
        while(choice < 0 || choice > 3);
        if(choice == 1){
            String filename = Data.generaldata.get(0) + "_LoansTransactions.csv"; //names csv file based on username
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
                bal = Data.investordata.get(4);
            }
            System.out.println("Your payment of $" + loan[5] + " is processing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            br.close();
            bw.close();
            fw.close();
            fr.close();
            System.out.println("Succesful!");
            newamount = Double.parseDouble(loan[4]) -  Double.parseDouble(loan[5]);
            update();
            Data.method = "loan payment";
            Data.typeOfAccount = "Loans";
            Transactions.balance = Double.parseDouble(bal);
            Transactions.amount = Double.parseDouble(loan[5]);
            Transactions.memo = loan[1];
            Transactions.loans();
        }
        else if(choice == 2){
            Double payment = 0.0;
            do{
                System.out.println("How much would like to the payment to be? $");
                payment = input.nextDouble();
                if(Double.parseDouble(loan[5]) < payment){
                    System.out.println("Your payment has to be higher than minimum monthly payment of $" + loan[5]);
                }
            }
            while(Double.parseDouble(loan[5]) < payment);
            String filename = Data.generaldata.get(0) + "_LoansTransactions.csv"; //names csv file based on username
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
                bal = Data.investordata.get(4);
            }
            System.out.println("Your payment of $" + payment + " is processing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            br.close();
            bw.close();
            fw.close();
            fr.close();
            System.out.println("Succesful!");
            newamount = Double.parseDouble(loan[4]) - payment;
            update();
            Data.method = "loan payment";
            Data.typeOfAccount = "Loans";
            Transactions.balance = Double.parseDouble(bal);
            Transactions.amount = payment;
            Transactions.memo = loan[1];
            Transactions.loans();
        }
        else{
            showloans();
        }
    }

    public static void update() throws IOException{
        File OldFile = new File(Data.generaldata.get(0) + "_Loans.csv");
        File NewFile = new File("Old.csv");

        String olddate = "";
        String oldmemo = "";
        String oldloantype = "";
        String oldterm = "";
        String oldamount = "";
        String oldmonthlypayment = "";

        try{
            FileWriter fw = new FileWriter(NewFile , true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            Scanner sr = new Scanner(OldFile);
            sr.useDelimiter("[,\n]");

            while(sr.hasNext())
            {
                olddate = sr.next();
                oldmemo= sr.next();
                oldloantype = sr.next();
                oldterm = sr.next();
                oldamount = sr.next();
                oldmonthlypayment = sr.next();

                if(oldloantype.equals(loan[2]))
                {
                    pw.print(olddate + "," + oldmemo + "," + oldloantype + "," + oldterm + "," + newamount + "," + oldmonthlypayment);
                }
                else{
                    pw.print(olddate + "," + oldmemo + "," + oldloantype + "," + oldterm + "," + oldamount + "," + oldmonthlypayment);
                }
            }
            pw.flush();
            sr.close();
            pw.close();
            OldFile.delete();
            File dump = new File(Data.generaldata.get(0) + "_Loans.csv");
            NewFile.renameTo(dump);
        }
        catch(Exception e)
        {
            System.out.println("404 error...");
        }
    }
}