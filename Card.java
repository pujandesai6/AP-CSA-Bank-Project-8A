import java.io.IOException;
import java.util.Scanner;

public class Card {
    private static String firstname = "";
    private static String lastname = "";
    private static String cardtype = "";
    private static String cardnumber = "";
    private static String cardexp = "";
    private static String cardName;
    private static String cardcvv = "";
    private static String cvvtext = "";
    
    public static String numbergenerator(){
        String numberstring = "";
        numberstring = "";
        for(int i = 0; i < 4; i++){
            int number = (int)(Math.random() * 9) + 0;
            numberstring += number + " ";
        }
        numberstring+= "   ";
        for(int i = 0; i < 4; i++){
            int number = (int)(Math.random() * 9) + 0;
            numberstring += number + " ";
        }
        numberstring+= "   ";
        for(int i = 0; i < 4; i++){
            int number = (int)(Math.random() * 9) + 0;
            numberstring += number + " ";
        }
        numberstring+= "   ";
        for(int i = 0; i < 3; i++){
            int number = (int)(Math.random() * 9) + 0;
            numberstring += number + " ";
        }
        int number = (int)(Math.random() * 9) + 0;
        numberstring += number + "";
        return numberstring;
    }

    public static String expdategenerator(){
        String expstring = "";
        int number = (int)(Math.random() * 1) + 0;
        if(number == 0){
            expstring += 0 + " " + ((int)(Math.random() * 8) + 1) + " ";
        }
        else{
            expstring += 1 + ((int)(Math.random() * 2) + 0) + " ";
        }
        number = (int)(Math.random() * 4) + 5;
        expstring += "/ 2 " + number;
        return expstring;
    }

    public static String cvvgenerator(){
        String cvv = " ";
        for(int i = 0; i < 3; i++){
            int number = (int)(Math.random() * 9);
            cvv += number + " ";
        }
        return cvv;  
    }

    public static String name(){
        String name = "[" + firstname.charAt(0);
        for(int i = 1; i < firstname.length(); i++){
            name += " " + firstname.charAt(i);
        }
        name += "    ";
        for(int i = 0; i < lastname.length(); i++){
            name += " " + lastname.charAt(i);
        }
        name += "]";
        if(name.length() == 30){
            cardName = name;
        }
        if(name.length() < 30){
            int extrachar = 30 - name.length();
            for(int i = 0; i < extrachar; i++){
                name += ":";
            }
        }
        if(name.length() > 27){
            int excess =  name.length() - 27;
            System.out.println(excess);
            if(excess < 4)
            {
                excess = 3 - excess;
                for(int i = 0; i < excess; i++){
                    name += ":";
                }
            }
            else{
                name = "[" + firstname.charAt(0);
                for(int i = 1; i < firstname.length(); i++){
                name += " " + firstname.charAt(i);
                }
                name += "   ";
                for(int i = 0; i < lastname.length(); i++){
                    name += " " + lastname.charAt(i);
                }
                name += "]";
                excess =  name.length() - 27;
                if(excess < 4)
                {   
                    excess = 3 - excess;
                    for(int i = 0; i < excess; i++){
                        name += ":";
                    }
                }
            }
        }
        return name;
    }

    public static void cardmain() throws IOException{
        try {
            Data.card(Data.customerdata.get(0), 0);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        firstname = Data.generaldata.get(2);
        lastname = Data.generaldata.get(3);
        cardName = name();
        System.out.println("--------------------");
        Scanner input = new Scanner(System.in);
        int choice = 0;
        System.out.println("What type of card would like to see?");
        do{
            System.out.println("1. Debit");
            System.out.println("2. Credit");
            System.out.print("Enter Choice: ");
            choice = input.nextInt();
        }
        while(choice < 0 || choice > 2);
        System.out.println("--------------------");
        if(choice == 1){
            cardtype = "::[ D E B I T ]";
            cardnumber = Data.ATMarray.get(1);
            cardexp = Data.ATMarray.get(3);
            cardcvv = "|" + Data.ATMarray.get(2) + "|";
            cvvtext = "| C V V |";
            printcard();
        }
        else{
            cardtype = "[ C R E D I T ]";
            cardnumber = Data.ATMarray.get(4);
            cardexp = Data.ATMarray.get(5);
            cardcvv = ":::::::::";
            cvvtext = ":::::::::";
            printcard();
        }
    }

    public static void printcard() throws IOException{
        for(int i = 0; i < 14; i++){
            if(i == 0 || i == 3 || i == 4 || i == 8 || i == 10 || i == 13){
                System.out.println("|::::::::::::::::::::::::::::::::::::::::::::::::::::::::::|");
            }
            if(i == 1){
                System.out.println("|:::[ B A N K   O F ]:::::::::::::::::::::" + cardtype + "::|");
            }
            if(i == 2){
                System.out.println("|:::[ V I T O   C A N G E R I Z Z I ]:::::::::[ C A R D ]::|");
            }
            if(i == 5 || i == 6){
                System.out.println("|:::|########|:::::::::::::::::::::::::::::::::::::|####|::|");
            }
            if(i == 7){
                System.out.println("|:::|########|:::::::::::::::::::::::::::::::::::::::::::::|");
            }
            if(i == 9){
                System.out.println("|:::[" + cardnumber + "]:::::::::::::|");
            }
            if(i == 11){
                System.out.println("|:::::::::::::::::::::::::::::::::::[EXPIRES]:[" + cardexp + "]::|");
            }
            if(i == 12){
                System.out.println("|:::" + cardName + "::[END]::::::::::::::::::|");
            }
        }

        for(int j = 0; j < 4; j++){
            System.out.println("");
        }

        for(int k = 0; k < 14; k++){
            if(k == 0 || k == 4 || k == 5 || k == 8 || k == 9 || k == 10 || k == 11 || k == 12){
                System.out.println("|::::::::::::::::::::::::::::::::::::::::::::::::::::::::::|");
            }
            if(k == 1 || k == 2 || k == 3){
                System.out.println("|NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN|");
            }
            if(k == 6){
                System.out.println("|                                          |::::" + cvvtext + "::|");
            }
            if(k == 7){
                System.out.println("|                                          |::::" + cardcvv + "::|");
            }
        }
        BankMain.bankmenu();
    }
}
