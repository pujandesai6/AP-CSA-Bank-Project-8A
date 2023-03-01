import java.util.*;
import java.lang.Math;
import java.time.LocalDate;
import java.io.*;

public class Data {
    public static ArrayList<String> generaldata = new ArrayList<String>();
    public static ArrayList<String> customerdata = new ArrayList<String>();
    public static ArrayList<String> investordata = new ArrayList<String>();  
    public static ArrayList<String> ATMarray = new ArrayList<String>(); //atm info
    public static String typeOfAccount = ""; //updates the type of account the user is current in 
    public static String method = ""; //gets updated when ever a method calls a common class
    public static Boolean testingmode = false;

    public static void generalarray(String info, int infotype) throws IOException{
        generaldata.clear();
        File file = new File("GeneralData.csv"); //describing the file
        FileWriter fw = new FileWriter(file , true);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while((line = br.readLine()) != null){
            String [] values = line.split(",");
            if(values[infotype].equals(info)){
                for(String value: values){
                    generaldata.add(value);
                }
            }
        }
        br.close();
        fw.close();
    }

    public static void customerarray(String info, int infotype) throws IOException{
        customerdata.clear();
        File file = new File("CustomerData.csv"); //describing the file
        FileWriter fw = new FileWriter(file , true);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while((line = br.readLine()) != null){
            String [] values = line.split(",");
            

            if(values[infotype].equals(info)){
                for(String value: values){
                    customerdata.add(value);
                }
            }
        }
        br.close();
        fw.close();
    }

    public static void investorarray(String info, int infotype) throws IOException{
        investordata.clear();
        File file = new File("InvestorsData.csv"); //describing the file
        FileWriter fw = new FileWriter(file , true);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while((line = br.readLine()) != null){
            String [] values = line.split(",");
            if(values[infotype].equals(info)){
                for(String value: values){
                    investordata.add(value);
                }
            }
        }
        br.close();
        fw.close();
    }

    public static void card(String info, int infotype) throws IOException{
        ATMarray.clear();
        File file = new File("Card.csv"); //describing the file
        FileWriter fw = new FileWriter(file , true);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while((line = br.readLine()) != null){
            String [] values = line.split(",");
            if(values[infotype].equals(info)){
                for(String value: values){
                    ATMarray.add(value);
                }
            }
        }
        fr.close();
        br.close();
        fw.close();
    }
}

