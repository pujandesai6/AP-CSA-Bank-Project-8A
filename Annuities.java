import java.util.*;
import java.lang.*;
import java.io.*;


public class Annuities {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<String[]> annuities = new ArrayList<String[]>();

    public static void menu() throws IOException{
        System.out.println("--------------------");
        System.out.println("What would like to do?");
        int choice = 0;
        do{
            System.out.println("1. Buy An Annuity (Non Tradable)");
            System.out.println("2. View Owned Annuities");
            System.out.println("3. Go Back.");
            System.out.print("Enter Choice: ");
            choice = input.nextInt();
        }
        while(choice < 0 || choice > 3);
        if(choice == 1){
            annuitiesmain();
        }
        else if(choice == 2){
            viewannuities();
        }
        else if(choice == 3){
            InvestorsAccount.investormenu();
        }
    }

    public static void annuitiesmain() throws IOException{
        System.out.println("--------------------");
        System.out.println("What would like to name this annuity? ");
        String name = input.next();
        System.out.println("--------------------");
        System.out.println("Here are our insurance investment options:");
        System.out.println("1. Insurance 1:");
        System.out.println(" - Rate (Interest rate) - 4%");
        System.out.println(" - Years (Time for payout) - 1 year");
       
        System.out.println();
       
        System.out.println("2. Insurance 2:");
        System.out.println(" - Rate (Interest rate) - 5.15%");
        System.out.println(" - Years (Time for payout) - 1 year");

        System.out.println();
       
        System.out.println("3. Insurance 3:");
        System.out.println(" - Rate (Interest rate) - 3.8%");
        System.out.println(" - Years (Time for payout) - 2 years");

        System.out.println("");
        int choice = -1;
        input.nextLine();
        while(choice < 0 || choice > 3){
            System.out.print("Please enter the number of the insurance option you would like to pick: ");
            choice = input.nextInt();
            System.out.println("--------------------");
        }

        Boolean overDraft = false;
        double investment = 0.0;
        do{
            System.out.print("How much do you want to invest: $");
            investment = input.nextDouble();
            Transactions.amount = investment;
            overDraft = Transactions.overdraft();
        }
        while(overDraft == true);

        Double finalinvestment = 0.0;
        int term = 0;
        double interest = 0.0;
        if(choice == 1){
            term = 1;
            interest = 4;
            finalinvestment = Math.round((investment * Math.pow((1 + .04), 1))*100.00)/100.00;
        }
        else if(choice == 2){
            term = 1;
            interest = 5.15;
            finalinvestment = Math.round((investment * Math.pow((1 + .0515), 1))*100.00)/100.00;
        }
        else{
            term = 2;
            interest = 3.8;
            finalinvestment = Math.round((investment * Math.pow((1 + .038), 2))*100.00)/100.00;
        }


        System.out.print("Confirm (y/n)? ");
        String confirm = input.next();
        if(confirm.contains("y")){
            System.out.println("Confirming Your Investment...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("--------------------");
            System.out.println("Your investment was successful! Your investment will be worth $" + finalinvestment + " after the term is over.");
            System.out.println("--------------------");
            MoneyMarket.name = name;
            MoneyMarket.typeOfInvestment = "Annuities";
            MoneyMarket.investmentamount = Math.round(investment * 100.00)/100.00;
            MoneyMarket.term = term;
            MoneyMarket.interest = interest;
            MoneyMarket.totalinvestment = Math.round(finalinvestment*100.00)/100.00;
            MoneyMarket.tradeable = "no";
            try {
                MoneyMarket.investments();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Transactions.investmenttype = "Annuity";
            try {
                Transactions.investmentclass();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("--------------------");
        }
        else{
            System.out.println("Canceling your investment...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("Your investment was canceled.");
            System.out.println("--------------------");
            InvestorsAccount.investormenu();
        }
    }

    public static void viewannuities() throws IOException{
        readfile();
        System.out.println("--------------------");
        if(annuities.size() != 0){
            int i = 1;
            for(String annuity[]: annuities){
                System.out.println(i + ". " + annuity[0]);
                i++;
            }   
            int choice = 0;
            do{
                System.out.print("Which annuity would you like to see: ");
                choice = input.nextInt();
            }
            while(choice < 0 || choice > i);
            System.out.println("--------------------");
            System.out.println("Name: " + annuities.get(choice - 1)[0]);
            System.out.println("Investment Amount: $" + annuities.get(choice - 1)[2]);
            System.out.println("Term: " + annuities.get(choice - 1)[3] + " Years");
            System.out.println("Interest: " + annuities.get(choice - 1)[4] + "%");
            System.out.println("Final Investment Amount: $" + annuities.get(choice - 1)[5]);
            System.out.println("Tradable? " + annuities.get(choice - 1)[6]);
            System.out.println("--------------------");
            menu();
        }
        else{
            System.out.println("--------------------");
            System.out.println("You do not own any annuities.");
            System.out.println("--------------------");
            menu();
        }
    }

    public static void readfile() throws IOException{
        annuities.clear();
        String filename = Data.generaldata.get(0) + "Investments.csv"; //names csv file based on username
        File file = new File(filename); //describing the file
        FileWriter fw = new FileWriter(file , true);
        FileReader fr = new FileReader(file);
        BufferedWriter bw = new BufferedWriter(fw);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while((line = br.readLine()) != null){
            String values[] = line.split(",");
            if(values[1].equals("Annuities")){
                annuities.add(values);
            }
        }
        br.close();
        bw.close();
        fr.close();
        fw.close();
    }
}
