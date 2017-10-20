import java.util.ArrayList;

/**
 * A HashTable meant for quick storing and accessing data using key-value pairs
 * 
 * Christian Wiemer
 */
public class Table<K, V>
{
    public ArrayList<ArrayList<Node<K,V>>> table;
    
    private static int[] primes = {11, 19, 41, 79, 163, 317, 641, 1279, 2557, 5119, 10243, 20479, 40961, 81919, 163841, 327673};
    private int size = 0; //track when putting in NEW item
    private int pIndex = 0;
    private int prime = primes[pIndex];
    private double lFactor = size/pIndex;
    private double maxLoad = 0.75;
    
    /**
     * Constructor for objects of class Table
     */
    public Table(int length)
    {
        for(int i = 0; i<length; i++){
            table.add(i, new ArrayList<Node<K,V>>());
        }
    }
    
    public void put(K key, V value){
        int hash = key.hashCode() % prime;
        ArrayList<Node<K,V>> hashArray = table.get(hash);
        
        boolean replaced = false;
        for(int i = 0; i<hashArray.size(); i++){
            if(hashArray.get(i).getKey().equals(key)){
                hashArray.get(i).value = value;
                replaced = true;
            }
        }
        
        if(replaced){
            hashArray.add(new Node(key,value));
            size++;
        }
        
        if(lFactor > maxLoad){
            
        }
    }
    
    public V get(K key){
        int hash = key.hashCode() % prime;
        ArrayList<Node<K,V>> hashArray = table.get(hash);
        V val = null;
        
        for(int i = table.get(hash).size()-1; i>0; i--){
            if(hashArray.get(i).getKey().equals(key)){
                val = hashArray.get(i).getValue();
            } else {
                return null;
            }
        }
        return val;
    }
    
    public boolean contains(K key){
        int hash = key.hashCode() % prime;
        ArrayList<Node<K,V>> hashArray = table.get(hash);
        boolean truth = false;
        
        for(int i = 0; i<hashArray.size(); i++){
            if(hashArray.get(i).getKey().equals(key)){
                truth = true;
            } else {
                truth = false;
            }
        }
        return truth;
    }
    
    public void delete(K key){
        int hash = key.hashCode() % prime;
        ArrayList<Node<K,V>> hashArray = table.get(hash);
        
        for(int i = 0; i<hashArray.size(); i++){
            if(hashArray.get(i).getKey().equals(key)){
                hashArray.remove(i);
            }
        }
    }
    
    public int size(){
        return size;
    }
    
    private void rehash(){
        pIndex++;
        prime = primes[pIndex];
        
        ArrayList<ArrayList<Node<K,V>>> tempTable = table;
        for(int i = 0; i<prime; i++){
            table.add(i, new ArrayList<Node<K,V>>());
        }
        
        for(int i = 0; i<tempTable.size(); i++){
            for(int j = 0; j<tempTable.get(i).size(); j++){
                if(tempTable.get(i).get(j) == null){
                } else{
                    table.get(i).add(j, new Node(tempTable.get(i).get(j).getKey(), tempTable.get(i).get(j).getValue()));
                    //This is definitely not right
                }
            }
        }
    }
    
    private class Node<K, V>{
        public K key;
        public V value;
        
        public Node(K key, V value){
            this.key = key;
            this.value = value;
        }
        
        public K getKey(){
            return key;
        }
        
        public V getValue(){
            return value;
        }
    }
}
