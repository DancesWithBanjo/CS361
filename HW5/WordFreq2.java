import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.lang.Integer;
import java.util.TreeMap;

/**
 * Write a description of class WordFreqs here.
 * 
 * Christian Wiemer
 * 
 */
public class WordFreq2
{
    /**
     * Constructor for objects of class WordFreqs
     */
    public WordFreq2()
    {
        
    }

    public static void main(String[] args){
        if(args.length == 0){
            System.out.print("Please enter a file to read");
            System.exit(1);
        }
        RedBlackTree<String, Integer> table = new RedBlackTree<String, Integer>();
        
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
                } else if(answer.charAt(0) == '>' && answer.length() > 1){
                    if(table.findSuccessor(answer.substring(1)) == null){
                        System.out.println("There is no word after " + "\"" + answer.substring(1) + "\"");
                    } else {
                        System.out.println("The word " + "\"" + table.findSuccessor(answer.substring(1)) + "\" comes after " + "\"" + answer.substring(1) + "\"");  
                    }
                } else if(answer.charAt(0) == '>'){ //last key
                    System.out.println("The alphabetically last word is " + "\"" + table.getMaxKey() + "\"");
                } else if(answer.charAt(0) == '<' && answer.length() > 1){
                    if(table.findPredecessor(answer.substring(1)) == null){
                        System.out.println("There is no word before " + "\"" + answer.substring(1) + "\"");
                    } else {
                        System.out.println("The word " + "\"" + table.findPredecessor(answer.substring(1)) + "\" comes before " + "\"" + answer.substring(1) + "\"");
                    }
                } else if(answer.charAt(0) == '<'){
                    System.out.println("The alphabetically first word is " + "\"" + table.getMinKey() + "\"");
                }
                else{
                    answer = answer.toLowerCase();
                    if(table.get(answer) == 0){
                        System.out.println("\"" + answer + "\" appears 0 times");
                    } else {
                        System.out.println("\"" + answer + "\" appears " + table.get(answer) + " times");
                    }
                }
            }
        }
    }
}
