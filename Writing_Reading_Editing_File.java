import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class Write extends Array {
    public static void main(String[] arg) throws IOException
    {
        //example array
        ArrayList<String> a = new ArrayList<String>();
        a.add("0");
        a.add("0");
        a.add("0");
        a.add("0");
        a.add("0");

        System.out.println(a);

        //reading the file

        String aa = "b";
        String cc = "c";

        WritingFile(a);
        //ReadingFile(aa,cc);

        String a11 = "11";
        String a12 = "12";
        String a13 = "SUCCESS";
        String a14 = "U R Great";
        String a15 = "15";

        //EditingFile(a11, a12, a13 , a14 , a15);
    }
}

class Array{

    public static void WritingFile (ArrayList<String> list) throws IOException
    {
        File file = new File("TestData.csv");
        FileWriter fw = new FileWriter(file , true);
        BufferedWriter bw = new BufferedWriter(fw);
        
        for(String X : list)
        {
            bw.write(X + ",");
        }
        bw.write(Date_time.date_time());
        bw.newLine();
        bw.close();
        fw.close();
    }

    public static void ReadingFile (String b , String c) throws IOException
    {
        File file = new File("TestData.csv");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        
        String line = "";
        while((line = br.readLine()) != null)
        {
            String [] values = line.split(",");
            // to check username and password are correct at that position at that location
            if (values[1].equals(b) && values[2].equals(c))
            {
                System.out.println("True");
            }
        }
        br.close();
        fr.close();
    }

    public static void EditingFile(String a11 , String a12 , String a13 , String a14 , String a15)
    {
        File OldFile = new File("TestData.csv");
        File NewFile = new File("TempData.csv");

        String b11 = "";
        String b12 = "";
        String b13 = "";
        String b14 = "";
        String b15 = "";
        
        try{

            FileWriter fw = new FileWriter(NewFile , true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            Scanner sr = new Scanner(OldFile);
            sr.useDelimiter("[,\n]");

            while(sr.hasNext())
            {
                b11 = sr.next();
                b12 = sr.next();
                b13 = sr.next();
                b14 = sr.next();
                b15 = sr.next();

                if (b11.equals(a11))
                {
                    pw.println(a11 + "," + a12 + "," + a13 + "," + a14 + "," + a15);
                }
                else
                {
                    pw.print( b11 + "," + b12 + "," + b13 + "," +b14 + "," + b15);
                }
            }

            sr.close();
            pw.flush();
            pw.close();
            OldFile.delete();
            File dump = new File("TestData.csv");
            NewFile.renameTo(dump);
        }
        catch(Exception e)
        {
            System.out.println("Code Does not work");
        }
    }
}
