import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.ArrayList;


public class Reading_Writing_File {
    public static void WritingFile(ArrayList<String> list) throws IOException
    {
        File file = new File("Saving.csv");
        FileWriter fw = new FileWriter(file , true);
        BufferedWriter bw = new BufferedWriter(fw);
        
        for(String X : list)
        {
            bw.write(X + "|//");
        }
        bw.newLine();
        bw.close();
        fw.close();
    }

    public static ArrayList<String[]> GetContentsOfDataFile() throws IOException
    {
        File file = new File("Saving.csv");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        
        ArrayList<String[]> dataFile = new ArrayList<>();
        while(true)
        {
            String line = br.readLine(); 
            if (line == null) {
                break;
            }

            dataFile.add(line.split("[|]"));
        }
        br.close();
        fr.close();

        return dataFile;
    }

    public static void ClearDataFile() throws IOException {
        File file = new File("Saving.csv");
        new FileWriter(file , false).close();
    }
}