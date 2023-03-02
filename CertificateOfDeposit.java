import java.util.*;
import java.io.*;

public class CertificateOfDeposit {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<String[]> cods = new ArrayList<String[]>();

    public static void codmain() throws IOException{
        System.out.println("What would you like to do?");
        int choice = -1;
        while(choice < 0 || choice > 3){
            System.out.println("1. View Certificates of Deposit");
            System.out.println("2. Buy a Certificate of Deposit");
            System.out.println("3. Return Home");
            System.out.print("Enter Choice: ");
            choice = input.nextInt();
        }
        if(choice == 1){
            viewcod();
        }
        else if(choice == 2){
            buycod();
        }
        else if(choice == 3){
            InvestorsAccount.accountmain();
        }
    }
    
    public static void viewcod() throws IOException{
        readfile();
        System.out.println("--------------------");
        if(cods.size() != 0){
            int i = 1;
            for(String cod[]: cods){
                System.out.println(i + ". " + cod[0]);
                i++;
            }   
            int choice = 0;
            do{
                System.out.print("Which Certificate of Deposit would you like to see: ");
                choice = input.nextInt();
            }
            while(choice < 0 || choice > i);
            System.out.println("--------------------");
            System.out.println("Name: " + cods.get(choice - 1)[0]);
            System.out.println("Investment Amount: $" + cods.get(choice - 1)[2]);
            System.out.println("Term: " + cods.get(choice - 1)[3] + " Years");
            System.out.println("Interest: " + cods.get(choice - 1)[4] + "%");
            System.out.println("Final Investment Amount: $" + cods.get(choice - 1)[5]);
            System.out.println("Tradable? " + cods.get(choice - 1)[6]);
            System.out.println("--------------------");
            codmain();
        }
        else{
            System.out.println("--------------------");
            System.out.println("You do not own any Certificates of Deposit.");
            System.out.println("--------------------");
            codmain();
        }
    }

    public static void readfile() throws IOException{
        cods.clear();
        String filename = Data.generaldata.get(0) + "Investments.csv"; //names csv file based on username
        File file = new File(filename); //describing the file
        FileWriter fw = new FileWriter(file , true);
        FileReader fr = new FileReader(file);
        BufferedWriter bw = new BufferedWriter(fw);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while((line = br.readLine()) != null){
            String values[] = line.split(",");
            if(values[1].equals("Certificate of Deposit")){
                cods.add(values);
            }
        }
        br.close();
        bw.close();
        fr.close();
        fw.close();
    }

    public static void buycod() throws IOException{
        System.out.println("--------------------");
        System.out.print("Purpose of Certificate of Deposit (no spaces): ");
        String memo = input.next();

        System.out.print("Enter the length(years) for your Certificate of Deposit: ");
        int term = input.nextInt();

        double interest = Math.round(((double)(Math.random() * (term + 1)) + term)*100.00)/100.00;

        Boolean overDraft = false;
        double investment = 0.0;
        do{
            System.out.print("How much do you want to invest: $");
            investment = input.nextDouble();
            Transactions.amount = investment;
            overDraft = Transactions.overdraft();
        }
        while(overDraft == true);

        Double finalinvestment = Math.round(investment*Math.pow((1 + (interest/100)), term)*100.00)/100.00;

        System.out.println("--------------------");
        System.out.println("Please confirm your Certificate of Deposit Information");
        System.out.println("Name: " + Data.generaldata.get(2) + " " + Data.generaldata.get(3));
        System.out.println("Social Security Number: " + Data.generaldata.get(6));
        System.out.println("FDIC Number: " + Data.investordata.get(4));
        System.out.println("Purpose: " + memo);
        System.out.println("Term: " + term);
        System.out.println("Investment: " + investment);
        System.out.println("Interest: " + interest);
        System.out.println("Investment after Interest: $" + finalinvestment);
        System.out.println("--------------------");

        String codnumber = "";
        for(int i = 0; i < 6; i++){
            int number = (int)(Math.random() * 9) + 0;
            codnumber += number;
        }
        System.out.print("Confirm (y/n)?");
        String confirm = input.next();
        if(confirm.contains("y")){
            System.out.println("Confirming Your COD...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            // TODO Auto-generated catch block
                e.printStackTrace();
            }
            MoneyMarket.name = memo;
            MoneyMarket.typeOfInvestment = "Certificate of Deposit";
            MoneyMarket.investmentamount = investment;
            MoneyMarket.term = term;
            MoneyMarket.interest = interest;
            MoneyMarket.totalinvestment = finalinvestment;
            MoneyMarket.tradeable = "no";
            try {
                MoneyMarket.investments();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Your Certificate of Deposit was successful! Your COD number is " + codnumber);
            Transactions.investmenttype = "Certificate of Deposit";
            try {
                Transactions.investmentclass();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("--------------------");
        }
        else{
            System.out.println("Canceling your COD purchase...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Your COD purchase was canceled.");
            System.out.println("--------------------");
        }
    }
}


