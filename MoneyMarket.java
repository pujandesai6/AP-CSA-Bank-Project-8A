import java.util.*;
import java.lang.Math;
import java.time.LocalDate;
import java.io.*;
import java.text.SimpleDateFormat; 
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MoneyMarket {
    public static String name = "";
    public static String typeOfInvestment = "";
    public static double investmentamount = 0.0;
    public static int term = 0;
    public static double interest = 0.0;
    public static double totalinvestment = 0.0;
    public static String tradeable = "";
    private static Scanner input = new Scanner(System.in);
    private static String security1[] = {"Delta Airlines Bond", "Bond", "12000.00", "12", "3.12", "17399.06"};
    private static String security2[] = {"Bank of America Bond", "Bond", "48000.00", "10", "5.22", "80359.08"};
    private static String security3[] = {"Colgate Certificate of Deposit", "COD", "5000.00", "4", "2.05", "5422.78"};
    private static String security4[] = {"PNC Bank Certificate of Deposit", "COD", "7000.00", "6", "1.96", "7864.60"};
    private static String security[] = {};
    private static String sellsecurity[];
    //private static String filename = "";

    public static void menu() throws IOException{
        System.out.println("--------------------");
        System.out.println("What would like to do?");
        System.out.println("1. Buy Brokered Securities");
        System.out.println("2. Go Back.");
        int choice = 0;
        do{
            System.out.print("Enter Choice: ");
            choice = input.nextInt();
        }
        while(choice < 0 || choice > 2);
        if(choice == 1){
            market();
        }
        else if(choice == 2){
            InvestorsAccount.accountmain();
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
            bw.write("Name,");
            bw.write("Type of investment,");
            bw.write("Investment Amount,");
            bw.write("Term,");
            bw.write("Interest,");
            bw.write("Final Amount");
            bw.write("Tradable");
        }
        else{
            bw.newLine();
            bw.write(name + ",");
            bw.write(typeOfInvestment + ",");
            bw.write(investmentamount + ",");
            bw.write(term + ",");
            bw.write(interest + ",");
            bw.write(totalinvestment + ",");
            bw.write(tradeable);
        }
        bw.close();
        br.close();
    }

    public static void market() throws IOException{
        System.out.println("--------------------");
        System.out.println("Current Market: ");
        System.out.println("1. " + security1[0] + ": $" + security1[2] + " - Term: " + security1[3] + " Years -  Interest: " + security1[4] + "% - Final Amount: $" + security1[5]);
        System.out.println("2. " + security2[0] + ": $" + security2[2] + " - Term: " + security2[3] + " Years -  Interest: " + security2[4] + "% - Final Amount: $" + security2[5]);
        System.out.println("3. " + security3[0] + ": $" + security3[2] + " - Term: " + security3[3] + " Years -  Interest: " + security3[4] + "% - Final Amount: $" + security3[5]);
        System.out.println("4. " + security4[0] + ": $" + security4[2] + " - Term: " + security4[3] + " Years -  Interest: " + security4[4] + "% - Final Amount: $" + security4[5]);
        System.out.println("--------------------");
        System.out.println("Would like to buy any of these brokered securities?(y/n) ");
        String yesorno = input.next();
        if(yesorno.contains("y")){
            int choice = 0;
            do{
                System.out.print("Enter Choice: ");
                choice = input.nextInt();
            }
            while(choice < 0 || choice > 4);
            if(choice == 1){
                security = security1;
                trade();
            }
            else if(choice == 2){
                security = security2;
                trade();
            }
            else if(choice == 3){
                security = security3;
                trade();
            }
            else if(choice == 4){
                security = security4;
                trade();
            }
        }
        else{
            InvestorsAccount.accountmain();
        }
    }

    public static void trade() throws IOException{
        name = security[0];
        typeOfInvestment = security[1];
        investmentamount = Double.parseDouble(security[2]);
        term = Integer.parseInt(security[3]);
        interest = Double.parseDouble(security[4]);
        totalinvestment = Double.parseDouble(security[5]);
        tradeable = "yes";
        investments();
        Data.typeOfAccount = "Investors";
        Transactions.amount = Double.parseDouble(security[2]);
        Transactions.investmenttype = security[0];
        Transactions.investmentclass();
    }

    public static void update(){
        File OldFile = new File(Data.generaldata.get(0) + "Investments.csv");
        File NewFile = new File("Old.csv");

        String oldname = "";
        String oldinvestmenttype = "";
        String oldinvestmentamount = "";
        String oldterm = "";
        String oldinterest = "";
        String oldfinalamount = "";
        String oldtradable = "";

        try{
            FileWriter fw = new FileWriter(NewFile , true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            Scanner sr = new Scanner(OldFile);
            sr.useDelimiter("[,\n]");

            while(sr.hasNext())
            {
                oldname = sr.next();
                oldinvestmenttype= sr.next();
                oldinvestmentamount = sr.next();
                oldterm = sr.next();
                oldinterest = sr.next();
                oldfinalamount = sr.next();
                oldtradable= sr.next();

                if(oldname.equals(name))
                {
                    pw.println(name+ "," + typeOfInvestment + "," + investmentamount + "," + term + "," + interest + "," + totalinvestment + "," + tradeable);
                }
                else
                {
                    pw.print(oldname+ "," + oldinvestmenttype + "," + oldinvestmentamount + "," + oldterm + "," + oldinterest + "," + oldfinalamount + "," + oldtradable);
                }
            }
            sr.close();
            pw.flush();
            pw.close();
            OldFile.delete();
            File dump = new File(Data.generaldata.get(0) + "Investments.csv");
            NewFile.renameTo(dump);
        }
        catch(Exception e)
        {
            System.out.println("404 error...");
        }
    }
}