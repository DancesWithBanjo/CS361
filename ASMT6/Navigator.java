import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.lang.Integer;

/**
 * Write a description of class Navigator here.
 * 
 * @author Christian Wiemer
 */
public class Navigator
{
    /**
     * Constructor for objects of class Navigator
     */
    public Navigator(){
    }

    public static void main(String[] args){
        if(args.length == 0){
            System.out.print("Please enter a file to read");
            System.exit(1);
        }
        Scanner scan;
        
        if(args[0] == null) {
            System.out.println("Sorry, but we could not find the file");
            return;
        } else {
            File file = new File(args[0]);
            
            try {
                scan = new Scanner(file);
                
                while(scan.hasNext()){
                    String name = scan.next();
                    double lat = scan.nextDouble();
                    double lon = scan.nextDouble();
                    City city = new City(name, lat, lon);
                }
            } catch(Exception e){
                System.err.format("Exception occurred trying to read '%s'.", file);
                e.printStackTrace();
                return;
            }
        }
    }
}
