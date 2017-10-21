import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.lang.Integer;

/**
 * Write a description of class WordFreqs here.
 * 
 * @author (your name) 
 */
public class WordFreqs
{
    //delimiter shit: "*'[^a-zA-Z0-9_']+*'"

    /**
     * Constructor for objects of class WordFreqs
     */
    public WordFreqs(){
    }
    
    public static void main(String[] args){
        Table table = new Table();
        Scanner scan;
        int newValue = 1;
        
        if(args[0] == null) {
            System.out.println("Sorry, but we could not find the file");
            return;
        } else {
            File file = new File(args[0]);
            try {
                scan = new Scanner(file);
                scan.useDelimiter("*'[^a-zA-Z0-9_']+*'");
                
                while(scan.hasNext()){
                    table.put(scan.next(), newValue);
                }
            }
            catch(Exception e){
                System.err.format("Exception occurred trying to read '%s'.", file);
                e.printStackTrace();
                return;
            }
            
            System.out.println("This text contains" + table.size() + "distinct words.");
            System.out.println("Please enter a word to get its frequency or hit enter to leave");
            Scanner input = new Scanner(System.in);
            String answer = input.nextLine();
            if(answer.length() == 0){
                return;
            } else {
                answer.toLowerCase();
                System.out.println("\"" + answer + "\"appears" + table.get(answer) + "times");
            }
        }
    }
}
