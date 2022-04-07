/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;
import java.util.TreeMap;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.*;
import java.io.FileWriter;
/**
 *
 * @author Ken
 */
public class Assignment2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
          
        // TODO code application logic here
        String INPUT_PATH = System.getProperty("user.dir") + "/Input/";
        String delimeter = "[^A-Za-z]+";
        Scanner fileReader = null;

        System.out.println("Unbalanced TreeMap");
        long startTime = System.nanoTime();
        long TotalTime = 0;
        UnbalancedTreeMap t1 = new UnbalancedTreeMap();
        for (int i = 1; i <= 77; i++) {
            try {
                fileReader = new Scanner(new File(INPUT_PATH + i + ".okpuncs"));
                fileReader.useDelimiter(delimeter);
            } catch (FileNotFoundException e) {
                System.out.println("File not found" + e);
            }
            
            while (fileReader.hasNext()) {
                String word = fileReader.next();
                t1.put(word, 1 + t1.get(word));
            }
            TotalTime += (System.nanoTime()-startTime);
        }
System.out.println("Unbalanced Treemap total running time: " + TotalTime);

       System.out.println("\n" + "Java Standard TreeMap");
     Long startTime2 = System.nanoTime();
     
         long TotalTime2 = 0;
        TreeMap<String, Integer> map = new TreeMap<>();
        for (int i = 1; i <= 77; i++) {
            try {
                fileReader = new Scanner(new File(INPUT_PATH + i + ".okpuncs"));
                fileReader.useDelimiter(delimeter);
            } catch (FileNotFoundException e) {
                System.out.println("File not found" + e);
            }
            
            while (fileReader.hasNext()) {
                String word = fileReader.next();
                if (map.containsKey(word))
                    map.put(word, map.get(word) + 1);
                else
                    map.put(word, 1);
            }

            TotalTime2 += (System.nanoTime()-startTime2);
        }
        System.out.println("Standard TreeMap Total Running time: " + TotalTime2);
        
        //to store the running times
        
        File file1 = new File("readme.txt");
        FileWriter myWriter = new FileWriter(file1);
        String Time = Long.toString(TotalTime);
        String Time2 = Long.toString(TotalTime2);
        myWriter.write("UnbalancedTreeMap Running time: ");
        myWriter.write(Time);
        myWriter.write("\nStandard Library Running time: ");
        myWriter.write(Time2);
        
        myWriter.close();
        System.out.println("file path=" + file1.getCanonicalPath());
    }
    
    
    
}
