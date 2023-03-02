import java.util.*;
import java.lang.Math;
import java.lang.reflect.Array;
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
    private static ArrayList<String[]> securities = new ArrayList<String[]>();
    private static String security[] = {};
    private static ArrayList<String[]> investments = new ArrayList<String[]>();
    private static String investment[] = {};
    private static String transactiontype = "";

    public static void market() throws IOException{
        String companies[] = {"Delta Airlines", "Bank of America", "Colgate", "PNC Bank", "Walmart", "Amazon", "Apple", "AT&T", "Honda Motors", "Wells Fargo"};
        String typeOfSecurity[] = {"Bond", "Certificate of Deposit","Bond", "Certificate of Deposit"};
        if(securities.size() == 0){
            for(int i = 0; i < 10; i++){
                String name = companies[(int)(Math.random() * 9)] + "";
                double amount = Math.round((Double)(1 + Math.random() * 10000)*100.00)/100.00;
                int term = (int)(1 + Math.random() * 29);
                double interest = Math.round((Double)(3 + Math.random() * 10)*100.00)/100.00;
                double finalamount = Math.round((double)(amount*Math.pow((1 + (interest/200)), (term*2)))*100.00)/100.00;
                String security[] = {name, typeOfSecurity[(int)(Math.random() * 3)], amount + "", term + "", interest + "", finalamount + ""};
                securities.add(security);
            }
        }
        int i = 1;
        System.out.println("--------------------");
        for(String securities[]: securities){
            System.out.println(i + ". Name: " + securities[0] + " - Type: " + securities[1] + " - Amount: $" + securities[2] + " - Term(Years): " + securities[3] + " - Interest: " + securities[4] + "% - Final Amount: $" + securities[5]);
            System.out.println("--------------------");
            i++;
        }
        System.out.print("Would like to buy any of these brokered securities?(y/n): ");
        String yesorno = input.next();
        if(yesorno.contains("y")){
            int choice = 0;
            do{
                System.out.print("Enter the number corresponding to the security you would like to purchase: ");
                choice = input.nextInt();
                Transactions.getbalance();
                if(Transactions.balance < Double.parseDouble(securities.get(choice-1)[2])){
                    System.out.println("Your investors account doesn't have enough funds to buy this security.");
                    System.out.println("--------------------");
                    System.out.println("Would like to transfer money to Investors Account?(y/n) ");
                    String y = input.next();
                    if(y.contains("y")){
                        Transactions.transfertype = "In";
                        Transactions.transfer();
                    }
                }
            }
            while(choice < 0 || choice > i  || Transactions.balance < Double.parseDouble(securities.get(choice-1)[2]));
            security = securities.get(choice-1);
            System.out.println("Purchasing your " + security[0] + " " + security[1] + "...");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Your purchase was successful!");
            System.out.println("--------------------");
            trade();
        }
        else{
            InvestorsAccount.accountmain();
        }
    }

    public static void menu() throws IOException{
        Data.method = "moneymarket";
        System.out.println("What would like to do?");
        System.out.println("1. Buy Brokered Securities");
        System.out.println("2. Sell Brokered Securities");
        System.out.println("3. Go Back.");
        int choice = 0;
        do{
            System.out.print("Enter Choice: ");
            choice = input.nextInt();
        }
        while(choice < 0 || choice > 3);
        if(choice == 1){
            market();
        }
        else if(choice == 2){
            tradeable = "yes";
            readfile();
            int i = 1;
            if(investments.size() != 0){
                System.out.println("--------------------");
                for(String investment[]: investments){
                    System.out.println(i + ". Name: " + investment[0] + " - Type: " + investment[1] + " - Amount: $" + investment[2] + " - Term(Years): " + investment[3] + " - Interest: " + investment[4] + "% - Final Amount: $" + investment[5]);
                    System.out.println("--------------------");
                    i++;
                }
                int sell = 0;
                do{
                    System.out.print("Enter the number corresponding to the security you would like to sell? ");
                    sell = input.nextInt();
                }
                while(sell < 0 || sell > i);
                investment = investments.get(sell-1);
                update();
                menu();
            }
            else{
                System.out.println("--------------------");
                System.out.println("You do not own any brokered securities.");
                System.out.println("--------------------");
                menu();
            }
            
        }
        else if(choice == 3){
            InvestorsAccount.accountmain();
        }
    }

    public static void readfile() throws IOException{
        investments.clear();
        String filename = Data.generaldata.get(0) + "Investments.csv"; //names csv file based on username
        File file = new File(filename); //describing the file
        FileWriter fw = new FileWriter(file , true);
        FileReader fr = new FileReader(file);
        BufferedWriter bw = new BufferedWriter(fw);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while((line = br.readLine()) != null){
            String values[] = line.split(",");
            if(values[6].equals(tradeable)){
                investments.add(values);
            }
        }
        br.close();
        bw.close();
        fr.close();
        fw.close();
    }

    public static void investments() throws IOException{
        String filename = Data.generaldata.get(0) + "Investments.csv"; //names csv file based on username
        File file = new File(filename); //describing the file
        FileWriter fw = new FileWriter(file , true);
        FileReader fr = new FileReader(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.flush();
        BufferedReader br = new BufferedReader(fr);
        if(br.readLine() == null){
            bw.write("Name,");
            bw.write("Type of investment,");
            bw.write("Investment Amount,");
            bw.write("Term,");
            bw.write("Interest,");
            bw.write("Final Amount,");
            bw.write("Tradable");
            bw.newLine();
        }
        bw.write(name + ",");
        bw.write(typeOfInvestment + ",");
        bw.write(investmentamount + ",");
        bw.write(term + ",");
        bw.write(interest + ",");
        bw.write(totalinvestment + ",");
        bw.write(tradeable);
        bw.newLine();
        br.close();
        bw.close();
        fr.close();
        fw.close();
    }

    public static void trade() throws IOException{
        name = security[0];
        typeOfInvestment = security[1];
        investmentamount = Double.parseDouble(security[2]);
        term = Integer.parseInt(security[3]);
        interest = Double.parseDouble(security[4]);
        totalinvestment = Double.parseDouble(security[5]);
        tradeable = "yes";
        if(!transactiontype.equals("sell")){
            investments();
            Transactions.typeOfTransaction = 6;
        }
        Data.typeOfAccount = "Investors";
        Data.method = "moneymarket";
        Transactions.amount = Double.parseDouble(security[2]);
        Transactions.investmenttype = security[0];
        Transactions.investmentclass();
    }

    public static void update() throws IOException{
        String filename = Data.generaldata.get(0) + "Investments.csv"; //names csv file based on username
        File oldfile = new File(filename);
        File newfile = new File("New.csv"); //describing the file
        FileWriter fw = new FileWriter(newfile , true);
        FileReader fr = new FileReader(oldfile);
        BufferedWriter bw = new BufferedWriter(fw);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while((line = br.readLine()) != null){
            String values[] = line.split(",");
            if(!values[0].equals(investment[0])){
                bw.write(values[0] + ",");
                bw.write(values[1] + ",");
                bw.write(values[2] + ",");
                bw.write(values[3] + ",");
                bw.write(values[4] + ",");
                bw.write(values[5] + ",");
                bw.write(values[6]);
                bw.newLine();
            }
        }
        bw.flush();
        br.close();
        bw.close();
        fr.close();
        fw.close();
        oldfile.delete();
        newfile.renameTo(oldfile);
        transactiontype = "sell";
        Transactions.typeOfTransaction = 7;
        security = investment;
        trade();
    }
}
