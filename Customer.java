import java.util.Scanner;
import java.util.ArrayList;

class AccessPoint {
    static Scanner UserInput = new Scanner(System.in);
    static boolean welcomed = false;
    static int failThreshold = 20;
    static int timesFailed = 0;
    
    
    public static void main(String[] args) {
        //COMMENT OUT THE LINE BELOW WHEN NOT TESTING
        //Customer.EnableTestingMode();
        LoginLoop();
    }
    
    public static void LoginLoop() {
        Customer localCustomer;
        
        //Will be greeted like this the first time the user attempts to login
        if (welcomed == false) {
            welcomed = true;
            System.out.println("Hello, welcome to the Bank of Cangerizzi.");
            System.out.println("Would you like to login or register an account?");
            System.out.println("(Input 1 to login or 2 to register)");
        } else if (timesFailed < failThreshold) {
            //Will be greeted like this all times afterward
            System.out.println("Input 1 to log in to an existing account or 2 to create a new one.");
        } else {
            System.out.println("Too many attempts made, deactivating your ability to login");
            System.out.println("Have you forgotten your password? Please send the email associated with your bank account, and we will send you a password reset request.");
            //TODO: MAKE THIS CODE FUNCTIONAL
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
                System.out.printf("Login successful. Welcome to the Bank of Cangerizzi, %s. \n", localCustomer.GetUsername());
            }
        } else if (response.equals("2")) {
            localCustomer = Customer.RegisterAccount();
        } else {
            //Call function again so they can ask to LOGIN or REGISTER (all other answers point here)
            System.out.println("Invalid response.");
            LoginLoop();
        }
        
        
    } 
}

class Customer {
    private static ArrayList<Customer> Customers = new ArrayList<Customer>();
    private static Scanner UserInput = new Scanner(System.in);
    private static ArrayList<String> securityQuestions;
    
    private static Customer placeholderCustomer = new Customer();
    
    //Creates a test customer at index 0. Only for testing purposes.
    //COMMENT THIS OUT WHEN NOT TESTING
    
    // public static void EnableTestingMode() {
    //     Customers.add(0, new Customer("TestUsername", "TESTING123"));
    //     System.out.println("Test Customer successfully created. Your username is TestUsername, and your password is TESTING123.");
    // }
    
    
    public static Customer RegisterAccount() {
        //Registration questions
        System.out.println("Insert XXXXX at any point to retype an answer for a question, as backspace doesn't work.");
        
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
        
        while (dateOfBirth.contains("XXXXX")) {
            System.out.println("Line exited. Please retype:");
            dateOfBirth = UserInput.nextLine();
        }
        
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
                System.out.println("Enter your birthdate in mm/dd/yyyy form:");
                dateOfBirth = UserInput.nextLine();
        
                while (dateOfBirth.contains("XXXXX")) {
                    System.out.println("Line exited. Please retype:");
                    dateOfBirth = UserInput.nextLine();
                }
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

        Customer newCustomer = new Customer(fullName, firstName, dateOfBirth, stateOfResidence, zipCode, lastFourDigitsofSS, phoneNumber, email, setUser, setPass);
        Customers.add(newCustomer);
        
        System.out.println("Account successfully created. Welcome, " + newCustomer.GetFirstName());
        
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
   
    //NOTE: Alert if user is depositing from a bank they don't usually go to
    //PERSONAL INFO
    private String FullLegalName;
    private String firstName;
    private String dateOfBirth;
    private int age;
    private String stateOfResidence;
    private String zipCode;
    private String lastFourDigitsOfSS;

    //CONTACT INFO
    private String phoneNumber;
    private String email;

    //BANK INFO
    private ArrayList<BankAccount> accounts;
    private int creditScore;
    private String dateOfRegistration;

    //ACCOUNT INFO
    private String username;
    private String password;
    private String pin;
    //private SafetyDepositBox SafetyBox;

    //METHODS
    private Customer() {
       this.username = "INVALID";
    }
    
    private Customer(String FLN, String fN, String doB, String sOR, String zC, String lFDoSS, String pN, String ema, String user, String pass) {
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
        this.pin = pin;
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
}

//DONT DELETE THIS
//ITS A PLACEHOLDER TO MAKE THE CODE RUn
class BankAccount {
    
}
