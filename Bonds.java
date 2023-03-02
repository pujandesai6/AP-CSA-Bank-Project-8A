import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;
import java.util.random.*;
import java.io.*;

public class Bonds{
    public static String tdnumber = Data.investordata.get(3);
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<String[]> bonds = new ArrayList<String[]>();

    public static void bondsmain() throws IOException{
        System.out.println("--------------------");
        System.out.println("What would like to do?");
        int choice = 0;
        do{
            System.out.println("1. Buy Bonds (Non Tradable)");
            System.out.println("2. View Owned Bonds (Tradable and Non Tradable)");
            System.out.println("3. Go Back.");
            System.out.print("Enter Choice: ");
            choice = input.nextInt();
        }
        while(choice < 0 || choice > 3);
        if(choice == 1){
            login();
        }
        else if(choice == 2){
            viewbonds();
        }
        else if(choice == 3){
            InvestorsAccount.investormenu();
        }
    }

    public static void login() throws IOException{
        System.out.println("--------------------");
        String last4digits = "";
        Boolean validSSN = false;
        int index = Data.generaldata.get(6).length();
        do{
            System.out.print("Please enter the last 4 digits of Social Security Number: ");
            last4digits = input.next();
            if(last4digits.equals(Data.generaldata.get(6).substring(index - 4, index))){
                validSSN = true;
            }
            else{
                System.out.println("The last 4 digits do not match with you Social Security Number. Please Try Again.");
                System.out.println("--------------------");
            }
        }
        while(validSSN == false);
        
        if(validSSN == true){
            buybonds();
        }
    }

    public static void buybonds() throws IOException{
        System.out.println("--------------------");
        Scanner input = new Scanner(System.in);
        System.out.print("What would you like to name this bond? ");
        String name = input.nextLine();
        int choice = 0;
        do{
            System.out.println("Type of Owner:");
            System.out.println("1. Sole Owner");
            System.out.println("2. Primary Owner:");
            System.out.println("3. Beneficiary");
            System.out.print("Enter Choice: ");
            choice = input.nextInt();
        }
        while(choice < 0 || choice > 3);

        String ownertype = "";
        String secondownerfirstname = "";
        String secondownerlastname = "";
        if(choice == 1){
            ownertype = "Sole Owner";
        }
        if(choice == 2){
            System.out.println("--------------------");
            ownertype = "Primary";
            System.out.print("Secondary Owner's First Name: ");
            secondownerfirstname = input.next();
            System.out.print("Secondary Owner's Last Name: ");
            secondownerlastname = input.next();
        }
        if(choice == 3){
            System.out.println("--------------------");
            ownertype = "Beneficiary";
            System.out.print("Beneficiary's First Name: ");
            secondownerfirstname = input.next();
            System.out.print("Beneficiary's Last Name: ");
            secondownerlastname = input.next();
        }

        System.out.println("--------------------");
        Boolean overDraft = false;
        double investment = 0.0;
        do{
            System.out.print("How much do you want to invest: $");
            investment = input.nextDouble();
            Transactions.amount = investment;
            overDraft = Transactions.overdraft();
        }
        while(overDraft == true);

        double interest = Math.round(((double)(Math.random() * 6) + 1)*100.00)/100.00;

        double finalinvestment = (Double)(Math.round(investment*Math.pow((1+(interest/200.00)),60))*100.00)/100.00;
        System.out.println(finalinvestment);

        System.out.println("--------------------");
        System.out.println("Please read the terms and conditions and confirm your information.");
        System.out.println("1. Your bond will earn " + interest + "% interest every 6 months.");
        System.out.println("2. Your bond will mature after 30 years.");
        System.out.println("Bond information: ");
        System.out.println("Bond Ownership: " + ownertype);
        if(ownertype.equals("Primary")){
            System.out.println("Secondary Owner: " + secondownerfirstname + " " + secondownerlastname);
        }
        if(ownertype.equals("Beneficiary")){
            System.out.println("Beneficiary: " + secondownerfirstname + " " + secondownerlastname);
        }
        if(ownertype.equals("Primary") || ownertype.equals("Beneficiary")){
            System.out.println("***Note*** In case of death, your bond will transferred under " + secondownerfirstname + " " + secondownerlastname + "'s name ***Note***");
        }
        System.out.println("Investment: $" + investment);
        System.out.println("Social Security Number: " + Data.generaldata.get(6));
        System.out.print("Confirm (y/n)? ");
        String confirm = input.next();
        if(confirm.contains("y")){
            System.out.println("Confirming Your Bond...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("--------------------");
            System.out.println("Your bond purchase was successful. Please check your email -- " + Data.generaldata.get(7) + " --for reciept.");
            System.out.println("--------------------");
            MoneyMarket.name = name;
            MoneyMarket.typeOfInvestment = "Bond";
            MoneyMarket.investmentamount = investment;
            MoneyMarket.term = 30;
            MoneyMarket.interest = interest;
            MoneyMarket.totalinvestment = finalinvestment;
            MoneyMarket.tradeable = "no";
            try {
                MoneyMarket.investments();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Transactions.investmenttype = "Bond";
            Data.method = "bonds";
            try {
                Transactions.investmentclass();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("--------------------");
        }
        else{
            System.out.println("Canceling your bond purchase...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Your bond purchase was canceled.");
            System.out.println("--------------------");
            Bonds.bondsmain();
        }
    }

    public static void viewbonds() throws IOException{
        readfile();
        System.out.println("--------------------");
        if(bonds.size() != 0){
            int i = 1;
            for(String bond[]: bonds){
                System.out.println(i + ". " + bond[0]);
                i++;
            }   
            int choice = 0;
            do{
                System.out.print("Which bond would you like to see: ");
                choice = input.nextInt();
            }
            while(choice < 0 || choice > i);
            System.out.println("--------------------");
            System.out.println("Name: " + bonds.get(choice - 1)[0]);
            System.out.println("Investment Amount: $" + bonds.get(choice - 1)[2]);
            System.out.println("Term: " + bonds.get(choice - 1)[3] + " Years");
            System.out.println("Interest: " + bonds.get(choice - 1)[4] + "%");
            System.out.println("Final Investment Amount: $" + bonds.get(choice - 1)[5]);
            System.out.println("Tradable? " + bonds.get(choice - 1)[6]);
            System.out.println("--------------------");
            bondsmain();
        }
        else{
            System.out.println("You do not own any bonds.");
            bondsmain();
        }
    }

    public static void readfile() throws IOException{
        bonds.clear();
        String filename = Data.generaldata.get(0) + "Investments.csv"; //names csv file based on username
        File file = new File(filename); //describing the file
        FileWriter fw = new FileWriter(file , true);
        FileReader fr = new FileReader(file);
        BufferedWriter bw = new BufferedWriter(fw);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while((line = br.readLine()) != null){
            String values[] = line.split(",");
            if(values[1].equals("Bond")){
                bonds.add(values);
            }
        }
        br.close();
        bw.close();
        fr.close();
        fw.close();
    }
}
