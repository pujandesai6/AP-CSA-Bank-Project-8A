import java.util.Scanner;

public class main {
    public static void main(String args[]) {
        
	Scanner scan = new Scanner(System.in);
	
	//Assuming that this goes after login
	System.out.println("Welcome to The Bank of Vito Cangerizzi.");
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	System.out.println("1) The Bank of Vito Cangerizzi, 241 Central Ave, Jersey City, NJ 07307");
	System.out.println("2) Vito Southern Bank of New Jersey, 1970 NJ-70 East, Cherry Hill, NJ 08003");
	System.out.println("3) Vito Western Bank of New Jersey, 2465 S Broad St, Hamilton Township, NJ 08610");
	System.out.println("4) Vito Eastern Bank of New Jersey, 8 S Main St, Marlboro, NJ 07746");
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	System.out.println("!~ Enter the number of the bank you are closest to/would like to see the details of.");
	System.out.print("!~ If you do not know which bank you are closest to, type 5: ");
	int locationAnswer = scan.nextInt();
	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

	boolean validLocation = false;
        while (!validLocation) {
	if (locationAnswer == 1) {
	    validLocation = true;
	    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	    System.out.println("Name: The Bank of Vito Cangerizzi");
        System.out.println("Area: Northern New Jersey");
        System.out.println("Address: 241 Central Ave, Jersey City, NJ 07307");
        System.out.println("Routing Number: 021206074");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	} else if (locationAnswer == 2) {
	    validLocation = true;
	    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	    System.out.println("Name: Vito Southern Bank of New Jersey");
        System.out.println("Area: Southern New Jersey");
        System.out.println("Address: 1970 NJ-70 East, Cherry Hill, NJ 08003");
        System.out.println("Routing Number: 031302117");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	} else if (locationAnswer == 3) {
	    validLocation = true;
	    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	    System.out.println("Name: Vito Western Bank of New Jersey");
        System.out.println("Area: Western New Jersey");
        System.out.println("Address:  2465 S Broad St, Hamilton Township, NJ 08610");
        System.out.println("Routing Number: 231271161");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	} else if (locationAnswer == 4) {
	    validLocation = true;
	    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	    System.out.println("Name: Vito Eastern Bank of New Jersey");
        System.out.println("Area: Eastern New Jersey");
        System.out.println("Address: 8 S Main St, Marlboro, NJ 07746");
        System.out.println("Routing Number: 231170181");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	} else if (locationAnswer == 5) {
	    validLocation = true;
	    scan.nextLine();
        boolean validZip = false;
        while (!validZip) {
	    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	    System.out.print("Please enter the first three(3) digits of your zip code: ");
	    
	    String startZip = scan.nextLine();
	    
	    
	    if (startZip.equals("070") || startZip.equals("071") || startZip.equals("072") || startZip.equals("073") || startZip.equals("074") || startZip.equals("075") || startZip.equals("076") || startZip.equals("078") || startZip.equals("079") || startZip.equals("088") || startZip.equals("089")) {
	        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	        System.out.println("Name: The Bank of Vito Cangerizzi");
	        System.out.println("Area: Northern New Jersey");
	        System.out.println("Address: 241 Central Ave, Jersey City, NJ 07307");
            System.out.println("Routing Number: 021206074");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            validZip = true;
	    } else if (startZip.equals("077") || startZip.equals("087")) {
	        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	        System.out.println("Name: Vito Southern Bank of New Jersey");
            System.out.println("Area: Southern New Jersey");
            System.out.println("Address: 1970 NJ-70 East, Cherry Hill, NJ 08003");
            System.out.println("Routing Number: 031302117");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            validZip = true;
	    } else if (startZip.equals("085") || startZip.equals("086")) {
	        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	        System.out.println("Name: Vito Western Bank of New Jersey");
            System.out.println("Area: Western New Jersey");
            System.out.println("Address:  2465 S Broad St, Hamilton Township, NJ 08610");
            System.out.println("Routing Number: 231271161");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            validZip = true;
	    } else if (startZip.equals("080") || startZip.equals("081") || startZip.equals("083") || startZip.equals("082") || startZip.equals("084")) {
	        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	        System.out.println("Name: Vito Eastern Bank of New Jersey");
            System.out.println("Area: Eastern New Jersey");
            System.out.println("Address: 8 S Main St, Marlboro, NJ 07746");
            System.out.println("Routing Number: 231170181");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            validZip = true;
	    } else {
	        System.out.println("Invalid response. Please try again:");
	    }
	}
	} else {
	    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	    System.out.println("Invalid response. Please try again:");
	    System.out.println("Please enter the number of whichever bank you are closest to, or would like to see the details of (including addresses and routing numbers).");
	    System.out.print("If you do not know which bank you are closest to, type 5: ");
        locationAnswer = scan.nextInt();
	}
}
}
}
class bankLocations {
    public static void main(String args[]) {
        Banks mainBank = new Banks("The Bank of Vito Cangerizzi", "Northern New Jersey", "241 Central Ave, Jersey City, NJ 07307", 021206074);
        Banks southBank = new Banks("Vito Southern Bank of New Jersey", "Southern New Jersey", "1970 NJ-70 East, Cherry Hill, NJ 08003", 031302117);
        Banks westBank = new Banks("Vito Western Bank of New Jersey",  "Western New Jersey", "2465 S Broad St, Hamilton Township, NJ 08610", 231271161);
        Banks eastBank = new Banks("Vito Eastern Bank of New Jersey", "Eastern New Jersey", "8 S Main St, Marlboro, NJ 07746", 231170181);
    }
}

class Banks {
	private static String name;
	private static String area;
	private static String address;
	private static int routingNumber;
	
	public Banks(String n, String a, String add, int route) {
	    name = n;
	    area = a;
	    address = add;
	    routingNumber = route;
	}
}