import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

public class UserMenu 
{
    static Scanner UserInput = new Scanner(System.in);
    static boolean welcomed = false;
    static int failThreshold = 20;
    static int timesFailed = 0;
    
    static Customer localCustomer = Customer.placeholderCustomer;

    public static void PrintBankIntro() {
        System.out.println("Hello! Welcome to Vito Cangerizzi Bank!");
    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    	System.out.println("Voted most trusted bank of New Jesery since 2023!");
    
        for (int i = 0; i < 12; i++ )
        {
            for (int v = 0; v < 28; v++)
            {
                if (i > 0 && (v == 23 - i || v == i - 1))
                {
                    System.out.print("$$");
                    continue;
                }
                System.out.print(" ");
            }

            for (int c = 1; c < 22; c++)
            {
                if (i > 0 && (i == 1 || i == 11) && c <= 16)
                {
                    System.out.print("$");
                }
                else
                {
                    System.out.print("");
                }
                if (c == 1 && i != 0)
                {
                System.out.print("$$");
                }
            }

            System.out.println();
        }

        System.out.println("\n");
    }
    public static void main(String args[]) 
    {
    	PrintBankIntro();
        LoginLoop();
    }

    public static void LoginLoop() {
       //Will be greeted like this the first time the user attempts to login
       if (welcomed == false) {
           welcomed = true;
            System.out.println("Would you like to login or register an account?");
            System.out.println("(Input 1 to login or 2 to register)");
        } else if (timesFailed < failThreshold) {
            //Will be greeted like this all times afterward
            System.out.println("Input 1 to log in to an existing account or 2 to create a new one.");
        } else {
            System.out.println("Too many attempts made, deactivating your ability to login");
            System.out.println("Have you forgotten your password? Please send the email address associated with your bank account, and we will send you a password reset request.");
            //TODO: MAKE IT CHECK ALL CUSTOMER'S EMAILS AND FIND THE RIGHT ONE
            return;
        }
        
        String response = UserInput.next();
   
        if (response.equals("1")) {
            localCustomer = Customer.LoginPrompt();
            
            if (localCustomer.GetUsername().equals("INVALID")) {
                System.out.println("Incorrect username or password.");
                timesFailed++;
                LoginLoop();
            } else {
                System.out.printf("Login successful.");
            }
        } else if (response.equals("2")) {
            localCustomer = Customer.RegisterAccount();
        } else {
            //Call function again so they can ask to LOGIN or REGISTER (all other answers point here)
            System.out.println("Invalid response.");
            LoginLoop();
        }

        while (localCustomer.equals(Customer.placeholderCustomer) == false) {
            localCustomer.GoToHomePage();
        }

        welcomed = false;
        PrintBankIntro();
        LoginLoop();
    } 

    public static void Logout() {
        localCustomer = Customer.placeholderCustomer;
        System.out.println("Logout successful. See you soon!\n\n");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}

class StringToDate {
    public static int tryParseInt(String str) {
        try {
            return Integer.parseInt(str);
        }
        catch(Exception e) {
            return 0;
        }
    }

    //Returns error code if the string doesn't pass the conditions for a date (not in mm/dd/yyyy format, entered a day bigger than 31, 30, or 28 depending on the month, etc)
    public static int CanBeConvertedToDate(String date) {
        //NOT IN mm/dd/yyyy format (errorcode: 101)
        if (date.length() != 10 || date.charAt(2) != '/' || date.charAt(5) != '/') {
            return 101;
        } 
 
        //day, month, or year is not a positive integer above 0 (errorcode: 102)

        int monthInt = tryParseInt(date.substring(0, 2));
        if (monthInt <= 0) {
            return 102;
        }

        int dayInt = tryParseInt(date.substring(3, 5));
        if (dayInt <= 0) {
            return 102;
        }
        
        int yearInt = tryParseInt(date.substring(6));
        if (yearInt <= 0) {
            return 102;
        }

        //given date isnt real (like february 54, or the 1st day of the 16th month)  (errorcode: 103)
        //person is born on february 29 - error message of -103 (still passes, but notifies the user their birthday will be inputted as March 1 for convenience)
        if (monthInt > 12) {
            return 103;
        }

        //date given is greater than the current year (errorcode: 104)
        LocalDate today = LocalDate.now();
        int todayDay = today.getDayOfMonth();
        int todayMonth = today.getMonth().getValue();
        int todayYear = today.getYear();

   
        if (yearInt > todayYear) {
            return 104;
        } else if (yearInt == todayYear) {
            if (monthInt > todayMonth) {
                return 104;
            } else if (monthInt == todayMonth) {
                if (dayInt > todayDay) {
                    return 104;
                }
            }
        }

        //date given is more than 125 years ago (errorcode: 105)

        if (todayYear - yearInt > 125) {
            return 105;
        }

        //this catches the months with 31 days
        //odd numbers from 1 to 7 (1, 3, 5, 7) and even numbers from 8 to 12 (8, 10, 12)
        //then every other day has 30 except february (which helps because that has the lowest number of days anyways and needs a new condition)
        if (monthInt < 8 && monthInt % 2 == 1 || monthInt >= 8 && monthInt % 2 == 0) {
            if (dayInt > 31) {
                return 103;
            }
        } else if (monthInt != 2) {
            if (dayInt > 30) {
                return 103;
            }
        } else  {
            if (dayInt >= 29) {
                if (dayInt == 29) {
                    return -103;
                }

                return 103;
            }
        }

        return 0;
    }

    public static LocalDate ConvertToDateMMDDYYYY(String date) {
        int month = tryParseInt(date.substring(0, 2));
        int day = tryParseInt(date.substring(3, 5));
        int year = tryParseInt(date.substring(6));

        return LocalDate.of(year, month, day);
    }
}

class Customer {
    private static ArrayList<Customer> Customers = new ArrayList<Customer>();
    private static Scanner UserInput = new Scanner(System.in);
    
    public static Customer placeholderCustomer = new Customer();

    //NOTE: Alert if user is depositing from a bank they don't usually go to
    //PERSONAL INFO
    private String FullLegalName;
    private String firstName;
    private LocalDate dateOfBirth;
    private int age;
    private String stateOfResidence;
    private String zipCode;
    private String lastFourDigitsOfSS;

    //CONTACT INFO
    private String phoneNumber;
    private String email;

    //BANK INFO
    private ArrayList<GeneralAccount> accounts;
    private int creditScore;

    //ACCOUNT INFO
    private String username;
    private String password;
    private String pin;
    //private SafetyDepositBox SafetyBox;
    
    //Creates a test customer at index 0. Only for testing purposes.
    //COMMENT THIS OUT WHEN NOT TESTING
    
    // public static void EnableTestingMode() {
    //     Customers.add(0, new Customer("TestUsername", "TESTING123"));
    //     System.out.println("Test Customer successfully created. Your username is TestUsername, and your password is TESTING123.");
    // }

    //METHODS
    private Customer() {
        this.username = "INVALID";
     }
     
    private Customer(String FLN, String fN, LocalDate doB, String sOR, String zC, String lFDoSS, String pN, String ema, String user, String pass) {
        this.FullLegalName = FLN;
        this.firstName = fN;
        this.dateOfBirth = doB;
        this.stateOfResidence = sOR;
        this.zipCode = zC;
        this.lastFourDigitsOfSS = lFDoSS;
        this.phoneNumber = pN;
        this.email = ema;
        this.username = user;
        this.password = pass;
        this.creditScore = (int) (Math.random() * 850) + 1;
        this.pin = Integer.toString((int) (Math.random() * 9999) + 1);
     }

    public void GoToHomePage() {
        System.out.printf("Welcome to the Bank of Cangerizzi, %s\n!", firstName);
        System.out.println("What would you like to do?");

        System.out.println("Press 1 to see user details");
        System.out.println("Press 2 to edit and create new bank accounts");
        System.out.println("Press 3 to log out");

        String option = UserInput.nextLine();

        if (option.equals("1")) {
            DisplayUserDetails();
        } else if (option.equals("2")) {
            DisplayBankAccounts();
        } else if (option.equals("3")) {
            System.out.println("Are you sure you want to log out? Press 3 to confirm.");
            if (UserInput.nextLine().equals("3")) {
                UserMenu.Logout();
            }
        } else {
            System.out.println("Invalid input.");
        }
    }
    
    private void DisplayUserDetails() {
        System.out.printf("Name: %s\n", FullLegalName);
        System.out.printf("Date of Birth: %s\n", dateOfBirth);
        System.out.printf("State: %s\n", stateOfResidence);
        System.out.printf("Phone Number: %s\n", phoneNumber);
        System.out.printf("Email: %s\n", email);

        System.out.println("\n");

        System.out.println("Returning to home page.");
    }

    private void DisplayBankAccounts() {
        int inc = 0;
        for (GeneralAccount acc : accounts) {
            inc++;
            System.out.printf("%d: Name: %s | Balance: %.2f\n", inc, acc.getAccountName(), acc.getBalance());
        }
    }

    public static Customer RegisterAccount() {
        //Registration questions
        System.out.println("Insert XXXXX at any point to retype an answer for a question if your backspace key doesn't work in the console.");
        
        System.out.println("--------------------------------");
        System.out.println("Enter your full name:");
        String fullName = UserInput.nextLine();
        
        while (fullName.contains("XXXXX")) {
            System.out.println("Line exited. Please retype:");
            fullName = UserInput.nextLine();
        }
        
        int locationOfSpace = fullName.indexOf(" ");

        String firstName = "placeholder";

        if (locationOfSpace == -1) {
            firstName = fullName;
        } else {
            firstName = fullName.substring(0, locationOfSpace);
        }
        
        System.out.println("--------------------------------");
        System.out.println("Enter your birthdate in mm/dd/yyyy form:");
        String dateOfBirth = UserInput.nextLine();

        do {
            boolean valid = true;

            int errorCode = StringToDate.CanBeConvertedToDate(dateOfBirth);

            if (errorCode == 101) {
                System.out.println("Not in MM/DD/YYYY form. Please retype:");
            } else if (errorCode == 102) {
                System.out.println("Invalid integer given for month, date, or year. Please retype:");
            } else if (errorCode == 103) {
                System.out.println("Date provided is not real. Please retype: ");
            } else if (errorCode == 104) {
                System.out.println("Date given is ahead of the current date. Please retype: ");
            } else if (errorCode == 105) {
                System.out.println("Date provided is far too long ago (must be less than 125 years). Please enter your real birthdate: ");
            } else if (errorCode == -103) {
                System.out.println("Your birthday is on February 29.");
                System.out.println("However, we will be storing your birthday as March 1 for convenience.");
                dateOfBirth = "03/01" + dateOfBirth.substring(5);
                break;
            } else {
                break;
            }

            dateOfBirth = UserInput.nextLine();
        } while (true);
        
        System.out.println("--------------------------------");
        System.out.println("Enter your state of residence (as abbreviation or full name)");
        String stateOfResidence = UserInput.nextLine();
        while (stateOfResidence.contains("XXXXX")) {
            System.out.println("Line exited. Please retype:");
            stateOfResidence = UserInput.nextLine();
        }
        
        System.out.println("--------------------------------");
        System.out.println("Enter your zip code: ");
        String zipCode = UserInput.nextLine();
        while (zipCode.contains("XXXXX")) {
            System.out.println("Line exited. Please retype:");
            zipCode = UserInput.nextLine();
        }
 
        System.out.println("--------------------------------");
        System.out.println("Enter the last four digits of your social security number: ");
        String lastFourDigitsofSS = UserInput.nextLine();
        while (lastFourDigitsofSS.contains("XXXXX")) {
            System.out.println("Line exited. Please retype:");
            lastFourDigitsofSS = UserInput.nextLine();
        }
 
        String username;
        boolean validUsername = false;
        
        do 
        {
            System.out.print("Create a username: ");
            username = UserInput.nextLine();
            
            if (username.contains("XXXXX")) {
                continue;
            }
            
            validUsername = true;
            System.out.println();
        
            //Username must contain at least 8 characters
            if (username.length() < 8 || username.length() > 24) {
                System.out.println("Username must be betweeen 8 and 24 characters.");
                validUsername = false;
                continue;
            }
        
            //Username must contain only letters and characters
            for (int i = 0; i < username.length(); i++) {
                if (!Character.isDigit(username.charAt(i)) && !Character.isLetter(username.charAt(i)))
                {
                    System.out.println("Username can only contain numbers and letters.");
                    validUsername = false;
                    continue;
                }
            }
        
            //Username must be original
            for (Customer c : Customers) 
            {
                if (c.GetUsername().equals(username)) 
                {
                    System.out.println("Username is taken.");
                    validUsername = false;
                    continue;
                }
            }

            if (validUsername == true) {
                System.out.println("Username created successfully.");
            }

        } while (!validUsername);
        
        String setUser = username;
        
        System.out.println("--------------------------------");
        
        String password;
        boolean validPassword = false; 

        do 
        {
            System.out.print("Create a strong password: ");
            password = UserInput.nextLine();
            
            if (password.contains("XXXXX")) {
                continue;
            }
            
            validPassword = true;
            
            System.out.println();
            
            //checks length of password
            if (password.length() < 8) 
            {
                System.out.println("Password must be at least 8 characters.");
                validPassword = false;
            }
            
            //checks if there is a space
            for (int i = 0; i < password.length(); i++) 
            {
                if (password.charAt(i) == ' ') 
                {
                    System.out.println("Password cannot contain a space.");
                    validPassword = false;
                    break;
                }
            }
            
            //checks if there is a special character, uppercase letter, and number
            boolean containsUppercase = false;
            boolean containsSpecialChar = false;
            boolean containsNumber = false;
            
            //loops through each character in password to check for special character, uppercase letter, and number
            for (int i = 0; i < password.length(); i++)
            {
                if (!Character.isDigit(password.charAt(i)) && !Character.isLetter(password.charAt(i)))
                {
                    containsSpecialChar = true;
                }
                if (Character.isUpperCase(password.charAt(i)))
                {
                    containsUppercase = true;
                }
                if (Character.isDigit(password.charAt(i)))
                {
                    containsNumber = true;
                }
            }
            
            //if (boolean == false) then validPassword == false
            if (!containsSpecialChar)
            {
                System.out.println("Password must contain at least 1 special character.");
                validPassword = false;
            }
            
            if (!containsUppercase)
            {
                System.out.println("Password must contain at least 1 uppercase letter.");
                validPassword = false;
            }
            
            if (!containsNumber)
            {
                System.out.println("Password must contain at least 1 number.");
            }

            //if validPassword == true, then user will be asked to confirm 
            if (validPassword) 
            {
                System.out.print("Please confirm your password: ");
                String confirmPassword = UserInput.nextLine();
                
                while (confirmPassword.contains("XXXXX")) {
                    System.out.print("Please confirm your password: ");
                    confirmPassword = UserInput.nextLine();
                }
                
                if (!confirmPassword.equals(password))
                {
                    System.out.println("Passwords do not match.");
                    validPassword = false;
                }
                else
                {
                    System.out.println("Password created successfully.");
                }
            }
        } while (!validPassword);
        
        String setPass = password;
        
        System.out.println("--------------------------------");
        System.out.println("Please enter your phone number: ");
        String phoneNumber = UserInput.nextLine();
        while (phoneNumber.contains("XXXXX")) {
            System.out.println("Line exited. Please retype:");
            phoneNumber = UserInput.nextLine();
        }
        
        System.out.println("--------------------------------");
        System.out.println("Please enter your email: ");
        String email = UserInput.nextLine();
        while (email.contains("XXXXX")) {
            System.out.println("Line exited. Please retype:");
            email = UserInput.nextLine();
        }
        
        System.out.println("--------------------------------");
        
        boolean correct = false;
        System.out.println("Press 1 to confirm the info below is correct, and anything else to edit.");
        
        System.out.printf("Full name: %s\n", fullName);
        System.out.printf("Date of Birth: %s\n", dateOfBirth);
        System.out.printf("State of Residence: %s\n", stateOfResidence);
        System.out.printf("Zip Code: %s\n", zipCode);
        System.out.printf("Last four digits of Social Security number: %s\n", lastFourDigitsofSS);
        System.out.printf("Phone Number: %s\n", phoneNumber);
        System.out.printf("Email: %s\n", email);

        String response = UserInput.nextLine();

        while (response.equals("1") == false) {
            System.out.println("--------------------------------");

            System.out.println("What piece of info is wrong?");
            System.out.println("Press 1 for Full name, 2 for Date of Birth, 3 for State of Residence, 4 for Zip Code, 5 for Social Security, 6 for Phone Number, and 7 for Email");
            System.out.println("Entering anything else will cancel this editing sequence.");
            String IHateJasonLayne = UserInput.nextLine();

            if (IHateJasonLayne.equals("1")) {
                System.out.println("Enter your full name:");
                fullName = UserInput.nextLine();
        
                while (fullName.contains("XXXXX")) {
                    System.out.println("Line exited. Please retype:");
                    fullName = UserInput.nextLine();
                }
                
                firstName = fullName.substring(0, fullName.indexOf(" "));
            } else if (IHateJasonLayne.equals("2")) {
                System.out.println("--------------------------------");
                System.out.println("Enter your birthdate in mm/dd/yyyy form:");
                dateOfBirth = UserInput.nextLine();
        
                do {
                    boolean valid = true;
        
                    int errorCode = StringToDate.CanBeConvertedToDate(dateOfBirth);
        
                    if (errorCode == 101) {
                        System.out.println("Not in MM/DD/YYYY form. Please retype:");
                    } else if (errorCode == 102) {
                        System.out.println("Invalid integer given for month, date, or year. Please retype:");
                    } else if (errorCode == 103) {
                        System.out.println("Date provided is not real. Please retype: ");
                    } else if (errorCode == 104) {
                        System.out.println("Date given is ahead of the current date. Please retype: ");
                    } else if (errorCode == 105) {
                        System.out.println("Date provided is far too long ago (must be less than 125 years). Please enter your real birthdate: ");
                    } else if (errorCode == -103) {
                        System.out.println("Your birthday is on February 29.");
                        System.out.println("However, we will be storing your birthday as March 1 for convenience.");
                        dateOfBirth = "03/01" + dateOfBirth.substring(5);
                        break;
                    } else {
                        break;
                    }
        
                    dateOfBirth = UserInput.nextLine();
                } while (true);
            } else if (IHateJasonLayne.equals("3")) {
                System.out.println("Enter your state of residence (as abbreviation or full name)");
                stateOfResidence = UserInput.nextLine();
                while (stateOfResidence.contains("XXXXX")) {
                    System.out.println("Line exited. Please retype:");
                    stateOfResidence = UserInput.nextLine();
                }
            } else if (IHateJasonLayne.equals("4")) {
                System.out.println("Enter your zip code: ");
                zipCode = UserInput.nextLine();
                while (zipCode.contains("XXXXX")) {
                    System.out.println("Line exited. Please retype:");
                    zipCode = UserInput.nextLine();
                }
            } else if (IHateJasonLayne.equals("5")) {
                System.out.println("Enter the last four digits of your social security number: ");
                lastFourDigitsofSS = UserInput.nextLine();
                while (lastFourDigitsofSS.contains("XXXXX")) {
                    System.out.println("Line exited. Please retype:");
                    lastFourDigitsofSS = UserInput.nextLine();
                }
            } else if (IHateJasonLayne.equals("6")) {
                phoneNumber = UserInput.nextLine();
                while (phoneNumber.contains("XXXXX")) {
                    System.out.println("Line exited. Please retype:");
                    phoneNumber = UserInput.nextLine();
                }
            } else if (IHateJasonLayne.equals("7")) {
                email = UserInput.nextLine();
                while (email.contains("XXXXX")) {
                    System.out.println("Line exited. Please retype:");
                    email = UserInput.nextLine();
                }
            } else {
                System.out.println("Are you sure all the info you've inputted is correct? Type 1 now to confirm, and anything else to re-edit.");

                System.out.printf("Full name: %s\n", fullName);
                System.out.printf("Date of Birth: %s\n", dateOfBirth);
                System.out.printf("State of Residence: %s\n", stateOfResidence);
                System.out.printf("Zip Code: %s\n", zipCode);
                System.out.printf("Last four digits of Social Security number: %s\n", lastFourDigitsofSS);
                System.out.printf("Phone Number: %s\n", phoneNumber);
                System.out.printf("Email: %s\n", email);

                response = UserInput.nextLine();
            }
        }

        Customer newCustomer = new Customer(fullName, firstName, StringToDate.ConvertToDateMMDDYYYY(dateOfBirth), stateOfResidence, zipCode, lastFourDigitsofSS, phoneNumber, email, setUser, setPass);
        Customers.add(newCustomer);
        
        System.out.println("Account successfully created.\n");
        
        return newCustomer;
    }
    
    //Prompts the user to insert a username and password, and checks all Customers in the Customers ArrayList to find one that matches the login info provided.
    //If a match is found, this returns the Customer with the matching login info. If it isn't found, it returns placeholderCustomer (a customer with a username of "INVALID").
    public static Customer LoginPrompt() {
        System.out.print("Please enter your username: ");
        String username = UserInput.next();
        System.out.print("Please enter your password: ");
        String password = UserInput.next();
        
        boolean success = false;
        Customer loginCustomer = placeholderCustomer;
        
        for (Customer c : Customers) {
            if (c.GetUsername().equals(username)) {
                if (c.GetPassword().equals(password)) {
                    success = true;
                    loginCustomer = c;
                }
            }
        }
        
        return loginCustomer;
    }
    
    public String GetUsername() {
        return this.username;
    }
    
    public String GetPassword() {
        return this.password;
    }
    
    public String GetFirstName() {
        return this.firstName;
    }
    
    public String GetFullName() {
        return this.FullLegalName;
    }

    public int GetCreditScore() {
        return this.creditScore;
    }

    public String GetPin() {
        return this.pin;
    }
}

//DONT DELETE THIS
//ITS A PLACEHOLDER TO MAKE THE CODE RUn
class GeneralAccount {
    
}