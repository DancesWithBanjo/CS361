import java.util.ArrayList;
import java.util.Scanner;

/**
 * A list set to be arranged where the parent is always greater than it's children
 * Will sort itself on each removal or addition of a new item so the above statement is true
 * 
 * Christian Wiemer
 */
public class PriorityQueue
{

  public ArrayList<Player> pQueue;
    
  /**
  * Initializes the priority queue
  */
  public PriorityQueue()
  {
    pQueue = new ArrayList();
  }

  /**
   * Inserts a player into the priority queue and then triggers swim to put them in the proper place.
   */
    public void insert(Player p) {
    pQueue.add(p);
    swim(pQueue.size());
  }

  /**
   * Removes the top player and triggers sink to reorder the priority queue
   */
  public String remove() {
    Player top = pQueue.get(0);
    pQueue.set(0, pQueue.get(pQueue.size()-1));
    sink(0);
    return top.name;
  }

  /**
   * returns the current size of the queue.
   */
  public int getSize() {
    return pQueue.size();
  }
  
  /**
   * Clears out the priority queue by setting it to a new list
   */
  public void clear() {
    pQueue = new ArrayList();
  }

  /**
   * Takes an object at the bottom of the queue and moves it up to its proper position
   * Based on score
   */
  public void swim(int i){
    int parent = (i-1)/2;
    i = i-1;
    Player swimmer = pQueue.get(i);
    Player substitute;

    while (i>0 && swimmer.score > pQueue.get(parent).score) {
      substitute = pQueue.get(parent);
      pQueue.set(parent, swimmer);
      pQueue.set(i, substitute);   // Hopefully that swaps them correctly.
      i = parent;
      parent = (parent-1)/2;  // Get ready for the next swap
    }
  }

  /**
   * Places an object at the top of the queue and sinks it down to its proper position
   * Based on score
   */
  public void sink(int i){
    int size = pQueue.size();
    int leftPlayer;
    int rightPlayer;
    int bestPlayer;
    Player sinker = pQueue.get(i);
    Player substitute;
    

    while (i < size/2) {
      leftPlayer = (2*i)+1;
      rightPlayer = leftPlayer+1;

      if (rightPlayer < size && pQueue.get(leftPlayer).score < pQueue.get(rightPlayer).score) {
        bestPlayer = rightPlayer;
      } else {
        bestPlayer = leftPlayer;
      }
      if (sinker.score >= pQueue.get(bestPlayer).score) {
        break;  //Stops here if the sinker is already in the right position
      }

      substitute = pQueue.get(i);
      pQueue.set(i, pQueue.get(bestPlayer)); //Swap down
      pQueue.set(bestPlayer, substitute);
      i = bestPlayer; //Make ready for the next loop
    }
    pQueue.set(i, sinker);
  }
}
