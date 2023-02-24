import java.util.*;
import java.lang.Math;
import java.time.LocalDate;
import java.io.*;
import java.text.SimpleDateFormat; 
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class InvestorsAccount {
    private static Scanner input = new Scanner(System.in);
    public static String typeOfInvestment = "";
    public static double investmentamount = 0.0;
    public static int term = 0;
    public static double interest = 0.0;
    public static double totalinvestment = 0.0;

    public static void accountmain(){
        Data.method = "Investors";
        System.out.println("--------------------");
        try {
            Data.investorarray(Data.generaldata.get(0), 0);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(Data.investordata.size() == 0){
            try {
                investorregister();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else{
            investormenu();
        }
    }

    public static void investorregister() throws IOException{
        System.out.print("You don't have an investors account yet. Would like to open one? (y/n)");
        String choice = input.next();
        if(choice.contains("y")){
            System.out.println("Generating Investors Account Number...");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String investorsaccountnumber = "";
            for(int i = 0; i < 12; i++){
                int number = (int)(Math.random() * 9) + 0;
                investorsaccountnumber += number;
            }

            String tdnumber = "";
            for(int i = 0; i < 12; i++){
                int number = (int)(Math.random() * 9) + 0;
                tdnumber += number;
            }

            String fdicnumber = "";
            for(int i = 0; i < 12; i++){
                int number = (int)(Math.random() * 9) + 0;
                fdicnumber += number;
            }
            double balance = 0.00;

            File file = new File("InvestorsData.csv"); //describing the general file
            FileWriter fw = new FileWriter(file , true);
            FileReader fr = new FileReader(file);
            BufferedWriter bw = new BufferedWriter(fw);
            BufferedReader br = new BufferedReader(fr);
            bw.newLine(); 
            bw.write(Data.generaldata.get(0) + ","); //adds general details to database
            bw.write(investorsaccountnumber + ",");
            bw.write(balance + ",");
            bw.write(tdnumber + ",");
            bw.write(fdicnumber);
            bw.close();
            br.close();
            Data.investorarray(Data.generaldata.get(0), 0);
            investormenu();
        }
        else{
            System.out.println("Redirecting you back to home...");
            BankMain.bankmenu();
        }
    }

    public static void investormenu(){
        Data.method = "Investors";
        System.out.println("Welcome to Vito Cangerizzi Investment Banking!");
        System.out.println("--------------------");
        System.out.println("Where would like to go?");
        System.out.println("1. View Balance\n2. Transfer\n3. View Transaction History\n4. View Bonds\n5. Check Money Market\n6. View Certificate of Deposit\n7. Annuities\n8. Return home.");
        System.out.print("Enter Choice: ");
        int choice = input.nextInt();
        if(choice == 1){
            System.out.println("--------------------");
            try {
                Transactions.getbalance();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println(Data.typeOfAccount + " Balance: $" + Transactions.balance);
            System.out.println("--------------------");
            investormenu();
        }
        else if(choice == 2){
            Transactions.transfer();
        }
        else if(choice == 3){
            try {
                Transactions.statement();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else if(choice == 4){
            Bonds.bondsmain();
        }
        else if(choice == 5){
            try {
                moneymarket();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else if(choice == 6){
            CertificateOfDeposit.codmain();
        }
        else if(choice == 7){
            Annuities.annuitiesmain();
        }
        else{
            BankMain.bankmenu();
        }
    }

    public static void investments() throws IOException{
        String filename = Data.generaldata.get(0) + "Investments.csv"; //names csv file based on username
        File file = new File(filename); //describing the file
        FileWriter fw = new FileWriter(file , true);
        FileReader fr = new FileReader(file);
        BufferedWriter bw = new BufferedWriter(fw);
        BufferedReader br = new BufferedReader(fr);
        if(br.readLine() == null){
            bw.write("Username,");
            bw.write("Type of investment,");
            bw.write("Investment Amount,");
            bw.write("Term,");
            bw.write("Interest,");
            bw.write("Final Amount");
        }
        else{
            bw.newLine();
            bw.write(typeOfInvestment + ",");
            bw.write(investmentamount + ",");
            bw.write(term + ",");
            bw.write(interest + ",");
            bw.write(totalinvestment + ",");
        }
        bw.close();
        br.close();
    }

    public static void moneymarket() throws IOException{
        String filename = Data.generaldata.get(0) + "Investments.csv"; //names csv file based on username
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
        String typeofinvestment = "";
        String investmentamount = "";
        String term = "";
        String interest = "";
        String totalinvestment = "";
        while((line = br.readLine()) != null){
            String [] values = line.split(",");
            typeofinvestment = values[0];
            investmentamount = values[1];
            term = values[2];
            interest = values[3];
            totalinvestment = values[4];
            statement.addRow(new Object[] {typeofinvestment,investmentamount,term,interest,totalinvestment});
            statement.addRow(new Object[] {""});
        }
        br.close();
        fw.close();
        
        frame.add(table);
        frame.setSize(800,800);
        frame.setLocationRelativeTo(null);  
        frame.setVisible(true);
        System.out.println("Your investments have been opened in a new tab.");
        InvestorsAccount.investormenu();
    }
}
