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
    public static Hashtable<String, Hashtable<String, String[]>> loans = new Hashtable<String, Hashtable<String, String[]>>();


    public static void loansmain() throws IOException{
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


        System.out.print("What is your requested " + loantype + " amount: "); //asks user for principle amount
        double principleamount = input.nextDouble();
        System.out.println("--------------------");
 
        double downpayment = 0.0; //asks user for downpayement only if they selected car loans or mortgage
        if(loantype.equals("car loan") || loantype.equals("mortgage")){
            System.out.print("What is down payment: ");
            downpayment = input.nextDouble();
            System.out.println("--------------------");
        }

        double loanamount = Math.round((principleamount - downpayment)*100.00)/100.00; //gets loan amount

        int time = 0;
        if(loantype.equals("Mortgage")){
            time = 1;
            System.out.print("Please enter your desired loan term (in years): "); //asks user for loan term
            term = input.nextInt();
            System.out.println("--------------------");
        }
        else{
            time = 12;
            System.out.print("Please enter your desired loan term (in months): "); //asks user for loan term
            term = input.nextInt();
            System.out.println("--------------------");
        }

        System.out.println("Enter your employment info"); //asks user for employment history
        System.out.println("Employer Name: "); //employer name
        String employername = input.next();
        System.out.println("--------------------");

        System.out.print("Your Salary: "); //their salary
        Double salary = input.nextDouble();

        System.out.println("--------------------");

        double interest = 0.0;
        if(loantype.equals("student loan")){ //calculates interest // if its student loan then its 0
            interest = 0;
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


        double interestAmount = Math.round(((double)(loanamount * (term/time) * interest)/100.00)*100.00)/100.00;
        finalloanamount = Math.round(((double)(((interestAmount + loanamount) * 100.00)/100.00)));
        monthlypayment = Math.round((double)(finalloanamount/(term))*100.00)/100.00;

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
        System.out.println("Term: " + term);
        System.out.println("Employer Name: " + employername);
        System.out.println("Salary: $" + salary);
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
        String filename = Data.generaldata.get(0) + "_" + Data.typeOfAccount + "Loans.csv"; //names csv file based on username
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

    public static void showloan() throws IOException{
        String filename = Data.generaldata.get(0) + "_Loans.csv"; //names csv file based on username
        File file = new File(filename); //describing the file
        FileWriter fw = new FileWriter(file , true);
        FileReader fr = new FileReader(file);
        BufferedWriter bw = new BufferedWriter(fw);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        String loaninfo[] = new String[6];
        List<String[]> allloans = new ArrayList<>();
        
        while((line = br.readLine()) != null){
            String values[] = line.split(",");
            int j = 0;
            if(values[2].equals(loantype)){
                if(!values[1].equals(null)){
                    System.out.println(j +": Loan Name: " +values[1]);
                    allloans.add(loaninfo);
                }
            }
        }

        loansmenu();
    }

    public static void loansmenu() throws IOException{
        System.out.println("--------------------");
        System.out.println("What would you like do to do?");
        getLoans();
        int choice = 0;
        do{
            System.out.println("1. View Personal Loan Account.");
            System.out.println("2. View Student Loan Account.");
            System.out.println("3. View Car Loan Account.");
            System.out.println("4. View Mortgage Account.");
            System.out.println("5. Apply for a loan.");
            System.out.println("6. Return Home.");
            System.out.print("Enter Choice: ");
            choice = input.nextInt();
        }
        while(choice < 0 || choice > 6);
        
        if(choice == 1){
            loantype = "personal loan";
            showLoanType();
        }
        else if(choice == 2){
            loantype = "student loan";
            showLoanType();
        }
        else if(choice == 3){
            loantype = "car loan";
            showLoanType();
        }
        else if(choice == 4){
            loantype = "mortgage";
            showLoanType();
        }
        else if(choice == 5){
            loansmain();
        }
        else{
            BankMain.bankmenu();
        }
    }

    public static void getLoans() throws IOException{
        String filename = Data.generaldata.get(0) + "_Loans.csv"; //names csv file based on username
        File file = new File(filename); //describing the file
        FileWriter fw = new FileWriter(file , true);
        FileReader fr = new FileReader(file);
        BufferedWriter bw = new BufferedWriter(fw);
        BufferedReader br = new BufferedReader(fr);
        String line = "";        
        loans = new Hashtable<String, Hashtable<String, String[]>>();
        while((line = br.readLine()) != null){
            String values[] = line.split(",");
            var loantypeObj =  loans.get(values[2]);
            if(loantypeObj == null)
                loantypeObj = new Hashtable<String, String[]>();
            loantypeObj.put(values[1],  values);
            loans.put(values[2],  loantypeObj);
        }
    }

    public static void showLoanType() throws IOException{
        if(loans != null && loans.get(loantype) != null)
        {
            showLoanNames(loans.get(loantype));
        }
        else{
            System.out.println("You do not have any " + loantype + " account(s).");
            loansmenu();
        }
    }

    public static void showLoanNames(Hashtable<String, String[]> loansByType) throws IOException{
        if(loansByType != null)
        {
            System.out.println("--------------------");
            int choice = 0;
            Enumeration loanNames = loansByType.keys();
            String loanNameStr = "";
            int i = 1;
            do{
                // Display message
                loanNameStr = "";
                System.out.println("Available loans:");
                // Condition holds true till there is single element
                // remaining using hasMoreElements() method
                
                while (loanNames.hasMoreElements()) {    
                    // Displaying the Enumeration
                    String name = loanNames.nextElement().toString();
                    System.out.println(i + ". " +name);
                    loanNameStr += name +",";
                    i++;
                }
                loanNameStr += "Return Home";
                System.out.println(i + ". Return Back.");
                System.out.print("Enter Choice: ");
                choice = input.nextInt();
            } 
            while(choice < 0 || choice > i);

            String[] loanNamesArr = loanNameStr.split(",");
            if(loanNamesArr != null && loanNamesArr.length > 0){
                if(choice == loanNamesArr.length)
                {
                    loansmenu();
                }
                else{
                    String[] values = loansByType.get(loanNamesArr[choice-1]);
                    System.out.println("--------------------");
                    printLoanInfo(values);
                }
                
            }
           
        }
    }

    public static void printLoanInfo(String[] values) throws IOException{
        System.out.println("Date : " + values[0]);
        System.out.println("Memo : " + values[1]);
        System.out.println("Loan Type : " + values[2]);
        System.out.println("Term : " + values[3]);
        System.out.println("Total Loan Amount : $" + values[4]);
        System.out.println("Monthly Payment : $" + values[5]);
        loansmenu();
    }
}
