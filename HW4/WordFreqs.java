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
    /**
     * Constructor for objects of class WordFreqs
     */
    public WordFreqs(){
    }
    
    public static void main(String[] args){
        Table<String, Integer> table = new Table<String, Integer>();
        Scanner scan;
        int keyVal = 1;
        
        if(args[0] == null) {
            System.out.println("Sorry, but we could not find the file");
            return;
        } else {
            File file = new File(args[0]);
            try {
                scan = new Scanner(file);
                scan.useDelimiter("'*[^a-zA-Z0-9_']+'*");
                
                while(scan.hasNext()){
                    String key = scan.next().toLowerCase();
                    if(key.length() == 0){
                        continue;
                    } else if(table.contains(key)){
                        keyVal = table.get(key);
                        table.put(key, keyVal+1);
                    } else{
                        table.put(key, keyVal);
                    }
                }
            }
            catch(Exception e){
                System.err.format("Exception occurred trying to read '%s'.", file);
                e.printStackTrace();
                return;
            }
            
            System.out.println("This text contains " + table.size() + " distinct words.");
            System.out.println("Please enter a word to get its frequency or hit enter to leave");
            Scanner input = new Scanner(System.in);
            boolean going = true;
            
            while(going){
                String answer = input.nextLine();
                if(answer.length() == 0){
                    System.out.println("See ya later!");
                    break;
                } else if(answer.charAt(0) == '-'){
                    table.delete(answer.substring(1));
                    System.out.println(answer.substring(1) + " has been removed");
                } else{
                    answer = answer.toLowerCase();
                    System.out.println("\"" + answer + "\" appears " + table.get(answer) + " times");
                }
            }
        }
    }
}
