import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.lang.Integer;

/**
 * The primary setup of the priority queue execution
 * 
 * Christian Wiemer
 */
public class Sportsball
{
    public Sportsball() {
    }

    /**
     * Runs the sportsball game
     */
    public static void main(String[] args) {
        PriorityQueue pQueue = new PriorityQueue();
        Scanner scan;
        Player p;
        String text;
        String leroy;
        String nameScore[] = new String[2];

        if(args[0] == null) {
            System.out.println("Sorry, but we could not find the file");
            return;
        }
        else{
            File file = new File(args[0]);
            try{
                scan = new Scanner(file);

                while(scan.hasNext()){
                    text = scan.nextLine();

                    if(text.equals("GO!")){
                        leroy = pQueue.remove();
                        System.out.println(leroy + " enters the game!");
                    }
                    else{
                        nameScore = text.split("/");
                        System.out.println(nameScore[0] + nameScore[1]);
                        p = new Player(nameScore[0], Integer.parseInt(nameScore[1]));
                        pQueue.insert(p);
                    }
                }
            }
            catch(Exception e){
                System.err.format("Exception occurred trying to read '%s'.", file);
                e.printStackTrace();
                return;
            }
        }
    }
}
