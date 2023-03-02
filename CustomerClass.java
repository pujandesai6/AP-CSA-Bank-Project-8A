import java.util.*;
import java.io.*;
import java.util.regex.*;

public class CustomerClass {
    private static String username;
    private static String password;
    private static Scanner input = new Scanner(System.in);

    public static void menu() throws IOException{ //asks user if they want to login or register
        int choice = 0;
        do{
            System.out.println("1. Login\n2. Register\n3. TestMode");
            System.out.print("Enter Your Choice: ");
            choice = input.nextInt();
            System.out.println("--------------------");
        }
        while(choice < 1 || choice > 3);
        if(choice == 1){ 
            login();
        }
        else if(choice == 2){
            register();
        }
        else{
            Data.testingmode = true;
            register();
        }
    }

    public static void login() throws IOException{ //for login
        boolean validation = false;
        int threshold = 0;
        do{
            System.out.print("Username: ");
            username = input.next();
            System.out.print("Password: ");
            password = input.next();
            validation = validate(threshold);
            threshold++;
        }
        while(validation == false && threshold < 4);
        if(threshold == 4){
            System.out.println("You have too many failed attempts, please wait a few seconds and try again.");
            System.out.println("--------------------");
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            menu();
        }
        if(validation == true){
            BankMain.menu();
        }
    }

    public static void register() throws IOException{ //register
        String newusername ="";
        boolean usernameIsValid = true;
        do{ //checks if username meets the requirements
            System.out.println("Username has to be between 8 and 24 characters.\nUsername cannot contain special charcters.");
            System.out.println("--------------------");
            System.out.print("Enter a username: "); 
            newusername = input.next();
            if(Data.testingmode == true){
                usernameIsValid = true;
            } 
            else{
                usernameIsValid = usernameValidate(newusername);
            }
        }
        while(usernameIsValid == false);
        System.out.println("Username is valid!");
        System.out.println("--------------------");

        String newpassword ="";
        boolean passwordIsValid = true;
        do{ //checks if password meets the requirements
            System.out.println("Password has to be at least 8 characters long.\nPassword has to contain at least one uppercase and lowercase letter.\nPassword has to contain at least one number.\nPassword has to contain at least one special character.");
            System.out.println("--------------------");
            System.out.print("Enter a password: "); 
            newpassword = input.next();
            if(Data.testingmode == true){
                passwordIsValid = true;
            } 
            else{
                passwordIsValid = passwordValidate(newpassword);
            }
        }
        while(passwordIsValid == false);
        System.out.println("Password is valid!");
        System.out.println("--------------------");

        System.out.print("Enter your first name: "); //asks for first name
        String newfirstname = input.next();
        System.out.println("--------------------");

        System.out.print("Enter your last name: "); //asks for last name
        String newlastname = input.next();
        System.out.println("--------------------");

        String newbirthdate = "";
        int newage = 0;
        do{
            System.out.print("Enter your birthday(mm/dd/yyyy): "); //asks for birthdate
            newbirthdate = input.next();
            String month = newbirthdate.substring(0, 2);
            String day = newbirthdate.substring(3, 5); 
            String year = newbirthdate.substring(6);

            if (!(month.matches("[0-9][0-9]") && day.matches("[0-9][0-9]") && year.matches("[0-9][0-9][0-9][0-9]"))) {
                System.out.println("Invalid format.\n");
                continue;
            }

            int monthInt = Integer.parseInt(month);
            int dayInt = Integer.parseInt(day);

            if (monthInt > 12 || monthInt < 1) {
                System.out.println("Month should be a number between 01 and 12\n");
                continue;
            } else if (dayInt > 31 || dayInt < 1) {
                System.out.println("Day should be a number between 01 and 28, 29, 30, and 31 depending on the month.\n");
                continue;
            }

            int birthdateyear = Integer.parseInt(newbirthdate.substring(6));
            newage = 2023-birthdateyear;
            if(newage < 18){
                System.out.println("You are to young to make a bank account.");
                UserMenu.main(null);
                return;
            }
            else if(newage > 105){
                System.out.println("You are too old to make a bank account");
                UserMenu.main(null);
                return;
            }
        }
        while(newage < 18 || newage > 105);
        System.out.println("--------------------");

        String newssn = "";
        do{
            System.out.print("Enter the last 4 digits of your social security number: "); //asks for ssn
            newssn = input.next();
        }
        while(newssn.length() != 4 && newssn.contains(newssn));
        System.out.println("--------------------");

        System.out.print("Enter your email: "); //asks for email
        String newemail = input.next();
        System.out.println("--------------------");

        String pin = "";
        do{
            System.out.print("Enter a 4 digit pin number: "); //asks for a pin
            pin = input.next();
        }
        while(pin.length() != 4);
        System.out.println("--------------------");

        System.out.println("Please make sure your information is correct."); //prints out registration info
        System.out.println("Username: " + newusername);
        System.out.println("Password: " + newpassword);
        System.out.println("First Name: " + newfirstname);
        System.out.println("Last Name: " + newlastname);
        System.out.println("Birthday: " + newbirthdate);
        System.out.println("Age: " + newage);
        System.out.println("Social Security Number: " + newssn);
        System.out.println("Email: " + newemail);
        System.out.println("Pin: " + pin);
        System.out.println("--------------------");
        String savingsaccountnumber = "";
        for(int i = 0; i < 12; i++){
            int number = (int)(Math.random() * 9) + 0;
            savingsaccountnumber += number;
        }
        String checkingsaccountnumber = "";
        for(int i = 0; i < 12; i++){
            int number = (int)(Math.random() * 9) + 0;
            checkingsaccountnumber += number;
        }

        String creditcardnumber = Card.numbergenerator();
        String debitcardnumber = Card.numbergenerator();
        String creditcardnum = creditcardnumber.replaceAll(" ", "");
        String debitcardnum = debitcardnumber.replaceAll(" ", "");

        int creditscore = (int)(Math.random() * 850) + 0;

        int newsafetydeposit = (int)(Math.random() * 300) + 1;

        System.out.println("Registering your details...");
        File file = new File("GeneralData.csv"); //describing the general file
        FileWriter fw = new FileWriter(file , true);
        FileReader fr = new FileReader(file);
        BufferedWriter bw = new BufferedWriter(fw);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        if((line = br.readLine()) == null){
            bw.write("username[0],");
            bw.write("password[1],");
            bw.write("firstname[2],");
            bw.write("lastname[3],");
            bw.write("birthdate[4],");
            bw.write("age[5],");
            bw.write("ssn[6],");
            bw.write("email[7],");
            bw.write("safetydepositboxnumber[8]");
        }
        bw.newLine(); 
        bw.write(newusername + ","); //adds general details to database
        bw.write(newpassword + ",");
        bw.write(newfirstname + ",");
        bw.write(newlastname + ",");
        bw.write(newbirthdate + ",");
        bw.write(newage + ",");
        bw.write(newssn + ",");
        bw.write(newemail + ",");
        bw.write(newsafetydeposit + "");
        bw.close();
        br.close();

        file = new File("CustomerData.csv"); //describing the customer file
        fw = new FileWriter(file , true);
        fr = new FileReader(file);
        bw = new BufferedWriter(fw);
        br = new BufferedReader(fr);
        line = "";
        if((line = br.readLine()) == null){
            bw.write("username[0],");
            bw.write("savingsaccountnumber[1],");
            bw.write("checkingsaccountnumber[2],");
            bw.write("savingsbalance[3],");
            bw.write("checkingsbalance[4],");
            bw.write("debitcardnum[5],");
            bw.write("creditcardnum[6],");
            bw.write("creditscore[7]");
        }
        bw.newLine();
        bw.write(newusername + ",");
        bw.write(savingsaccountnumber + ","); //adds customer details to database
        bw.write(checkingsaccountnumber + ",");
        bw.write("100.00,");
        bw.write("0.00,");
        bw.write(creditcardnum + ",");
        bw.write(debitcardnum + ",");
        bw.write(creditscore + "");
        bw.close();
        br.close();

        file = new File("Card.csv"); //describing the file
        fw = new FileWriter(file , true);
        fr = new FileReader(file);
        bw = new BufferedWriter(fw);
        br = new BufferedReader(fr);

        //card info
        String creditcardexp = Card.expdategenerator();
        String debitcardexp = Card.cvvgenerator();
        String debitcardcvv = Card.expdategenerator();

        line = "";
        if((line = br.readLine()) == null){
            bw.write("username[0],");
            bw.write("debitcardnumber[1],");
            bw.write("debitcardexp[2],");
            bw.write("debitcardcvv[3],");
            bw.write("creditcardnumber[4],");
            bw.write("creditcardexp[5],");
            bw.write("pin[6]");
        }
        bw.newLine(); 
        bw.write(newusername + ",");
        bw.write(debitcardnumber + ",");
        bw.write(debitcardexp + ",");
        bw.write(debitcardcvv + ",");
        bw.write(creditcardnumber + ",");
        bw.write(creditcardexp + ",");
        bw.write(pin + "");
        bw.close();
        br.close();
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Account successfully created. Welcome, " + newfirstname + "!");
        System.out.println("As a reward of registering with the " + BankLocations.bankname + ", we have deposited a $100.00 to you savings account!");
        System.out.println("Redirecting you to login...");
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("--------------------");
        menu();
    }

    public static boolean validate(int threshold) throws IOException{ //validates username and password
        boolean isValid = false;  
        Data.generalarray(username, 0);
        if(Data.generaldata.size() != 0){
            if(username.equals(Data.generaldata.get(0)) && password.equals(Data.generaldata.get(1))){ //checks for match
                isValid = true;
            }
        }
        else{
            int triesremaining = 3 - threshold; //caluclates tries remaining
            System.out.println("Username and Password do not match! You have " + triesremaining + " try(s) remaining!");
        }
        return isValid;
    }

    public static boolean usernameValidate(String newusername) throws IOException{ //checks if username is valid
        Data.generalarray(newusername, 0);
        if(Data.generaldata.size() != 0){ //checks if username already exists
            System.out.println("Username is already taken! Please try another username.");
            return false;
        }
        if(newusername.length() < 8 || newusername.length() > 24){ //checks username length is between 8 and 24 characters
            System.out.println("Username has to be between 8 and 24 characters. Please try another username");
            return false;
        }
        for(int i = 0; i < newusername.length(); i++){ //checks if username contains special characters 
            if(!Character.isDigit(newusername.charAt(i)) && !Character.isLetter(newusername.charAt(i))){
                System.out.println("Username cannot contain special charcters. Please try another username");
                return false;
            }
        }
        return true;
    }

    public static boolean passwordValidate(String newpassword) throws IOException{ //checks is password is valid
        boolean passwordIsValid = true;
        if(newpassword.length()>=8){ //first checks if password is 8 characters long
            Pattern lowercaseletter = Pattern.compile("[a-z]"); //defines lowercase letters
            Pattern uppercaseletter = Pattern.compile("[A-Z]"); //defines upper letters
            Pattern digit = Pattern.compile("[0-9]"); //defines all numbers for 0-9
            Pattern special = Pattern.compile("[!@#$%&*()_=+|<>?{}\\[\\]~-]"); //defines all special characters

            Matcher hasLowerCaseLetter = lowercaseletter.matcher(newpassword); //looks for lowercase 
            Matcher hasUpperCaseLetter = uppercaseletter.matcher(newpassword); //looks for uppercase
            Matcher hasDigit = digit.matcher(newpassword); //looks for number 
            Matcher hasSpecial = special.matcher(newpassword);//looks for special chracter 

            boolean LowerCaseletterFind = hasLowerCaseLetter.find(); //converts to boolean
            boolean UpperCaseletterFind = hasUpperCaseLetter.find(); //converts to boolean
            boolean digitFind = hasDigit.find(); //converts to boolean
            boolean specialFind = hasSpecial.find(); //converts to boolean

            if(LowerCaseletterFind == false){ //if password is missing lowerletter
                System.out.println("Password has to contain at least one uppercase and lowercase letter. Please try again.");
                passwordIsValid = false;
            }
            if(UpperCaseletterFind == false){ //if password is uppercase letter
                System.out.println("Password has to contain at least one uppercase and lowercase letter. Please try again.");
                passwordIsValid = false;
            }
            if(digitFind == false){ //if password is missing a number
                System.out.println("Password has to contain at least one number. Please try again.");
                passwordIsValid = false;
            }
            if(specialFind == false){ //if password is special character
                System.out.println("Password has to contain at least one special character. Please try again.");
                passwordIsValid = false;
            }
        }
        else{
            System.out.println("Password has to be at least 8 characters long. Please try again."); //if password is not 8 characters long, it returns false
            passwordIsValid = false;
        }
        return passwordIsValid;
    }
}
