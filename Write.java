import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.ArrayList;

public class Write extends Array {
    public static void main(String[] arg) throws IOException
    {
        //example array
        ArrayList<String> a = new ArrayList<String>();
        a.add("a");
        a.add("b");
        a.add("c");
        a.add("d");
        a.add("e");

        System.out.println(a);

        //reading the file

        String aa = "b";
        String cc = "c";

        WritingFile(a);
        ReadingFile(aa,cc);
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
}