import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;

public class SafetyDepositBox {
    public ArrayList<String> Valuables;
    private static Scanner input = new Scanner(System.in);
    private static int limit = 10;
    private static String exitCharacter = "XXXXX";
    private static String stopCharacter = "YYYYY";
   
    private void ExitPrint() {
        System.out.printf("INPUT '%s' (without quotes) AT ANY POINT TO RETYPE\n", exitCharacter);
    }
   
    private void StopPrint() {
        System.out.printf("INPUT '%s' (without quotes) AT ANY POINT TO STOP\n\n", stopCharacter);
    }
   
    public SafetyDepositBox() {
        this.Valuables = new ArrayList<String>();

        ExitPrint();
        StopPrint();
       
        for (int i = 0; i < 10; i++) {
            System.out.printf("Enter valuable to deposit #%d: \n", i + 1);
            String Valuable = "";
            Valuable += input.nextLine();
           
            if (Valuable.contains(exitCharacter))
            {
                i -= 1;
                System.out.println("Please retype.");
                continue;
            } else if (Valuable.contains(stopCharacter)) {
                i -= 1;
                System.out.printf("Are you sure you'd like to stop inserting items in the deposit box? Enter %s again to confirm, and anything else to cancel.\n", stopCharacter);
                if (input.nextLine().equals(stopCharacter)) {
                    return;                    
                } else {
                    System.out.println("Alright.");
                    continue;
                }
            }
           
            this.Valuables.add(Valuable);
            try {
                BankMain.menu();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
   
    private static boolean CheckCharacter(String chr) {
        return input.nextLine().equals(chr);
    }
   
    public void DepositValuables() {
        if (this.Valuables.size() >= limit) {
            System.out.println("Your box is full! Remove some items from the deposit box?");
            RemoveValuables();
        }
       
        System.out.printf("TYPE %s AT ANY POINT TO STOP PUTTING ITEMS IN THE BOX\n", exitCharacter);
        String response = "1";
       
        for (int i = this.Valuables.size(); i < limit; i++) {
            System.out.printf("What would you like to add to slot %d of the deposit box?\n", i + 1);
            String valuable = input.nextLine();
               
            if (valuable.equals(exitCharacter)) {
                System.out.println("Are you sure you want to stop putting items in the box? Enter XXXXX again to confirm");
                   
                if (SafetyDepositBox.CheckCharacter(exitCharacter)) {
                    break;
                }
               
                System.out.println("Alright.");
                i--;
                continue;
            }        
               
            System.out.printf("Are you sure you want to add %s to your deposit box at slot %d?\n", valuable, i + 1);
            System.out.println("Press 1 to confirm, and anything else to cancel.");
           
            response = input.nextLine();
           
            if (response.equals(exitCharacter)) {
                System.out.println("Are you sure you want to stop putting items in the box? Enter XXXXX again to confirm");
                   
                if (SafetyDepositBox.CheckCharacter(exitCharacter)) {
                    break;
                }
               
                System.out.println("Alright.");
                i--;
                continue;
            } else {
                Valuables.add(valuable);
            }
        }
    }
   
    public void DisplayContentsOfBox() {
        System.out.println("Here are your Valuables.");
       
        for (int i = 0; i < Valuables.size(); i++){
            System.out.printf("%d. %s\n", i + 1, Valuables.get(i));
        }
    }
   
    public void RemoveValuables() {
        StopPrint();
       
        System.out.println("Enter the index of the item you want to remove.");
        String index = input.nextLine();
       
        if (index.contains(stopCharacter)) {
            System.out.printf("Are you sure you want to stop removing valuables? Retype %s to confirm.\n", stopCharacter);
           
            if (input.nextLine().equals(stopCharacter)) {
                return;
            }
        } else {
            System.out.printf("Are you sure you want to remove %s from your deposit box? Type 1 to confirm.\n", Valuables.get(Integer.parseInt(index) - 1));
            if (input.nextLine().equals("1")) {
                Valuables.remove(Integer.parseInt(index) - 1);
                System.out.println("Removed.");
            }
        }
    }
}
