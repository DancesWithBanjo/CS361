import java.util.ArrayList;

/**
 * A HashTable meant for quick storing and accessing data using key-value pairs
 * 
 * Christian Wiemer
 */
public class Table<K, V>
{
    public ArrayList<ArrayList<Node<K,V>>> table;
    
    private static  final int[] primes = {11, 19, 41, 79, 163, 317, 641, 1279, 2557, 5119, 10243, 20479, 40961, 81919, 163841, 327673};
    private int size = 0; //track when putting in NEW item
    private int pIndex = 0;
    private int prime = primes[pIndex];
    private double lFactor;
    private final double MAXLOAD = 0.75;
    
    /**
     * Constructor for objects of class Table.
     */
    public Table()
    {
        for(int i = 0; i<prime; i++){
            System.out.println(prime);
            System.out.println(i);
            table.add(new ArrayList<Node<K,V>>());
        }
    }
    
    /**
     * Hashes the key and places the key-value pair in a position based on the hashcode.
     * If the key is already there, it will replace the value stored in there with the new one.
     * Triggers rehash() if the load factor is greater than 0.75.
     * 
     * @params key: the key linked with the value
     * @params value: the value assigned to the key
     */
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
        
        if(!replaced){
            hashArray.add(new Node(key,value));
            size++;
        }
        
        lFactor = size/prime;
        
        if(lFactor > MAXLOAD){
            rehash();
        }
    }
    
    /**
     * Finds and returns the value of the input key.
     * 
     * @params key: the key who's value is being sought after
     */
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
    
    /**
     * Checks to see if a key is in the hash table.
     * 
     * @params key: the key to check for
     */
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
    
    /**
     * Removes the input key from the hash table.
     * 
     * @params key: the key to delete
     */
    public void delete(K key){
        int hash = key.hashCode() % prime;
        ArrayList<Node<K,V>> hashArray = table.get(hash);
        
        for(int i = 0; i<hashArray.size(); i++){
            if(hashArray.get(i).getKey().equals(key)){
                hashArray.remove(i);
            }
        }
    }
    
    /**
     * Returns the total number of key-value pairs in the hash table
     */
    public int size(){
        return size;
    }
    
    //Rehashes the hash table with a higher prime number
    private void rehash(){
        pIndex++;
        prime = primes[pIndex];
        
        ArrayList<ArrayList<Node<K,V>>> tempTable = table;
        table = new ArrayList<ArrayList<Node<K,V>>>();
        for(int i = 0; i<prime; i++){
            table.add(i, new ArrayList<Node<K,V>>());
        }
        
        size = 0;
        
        for(int i = 0; i<tempTable.size(); i++){
            for(int j = 0; j<tempTable.get(i).size(); j++){
                if(tempTable.get(i).get(j) == null){
                } else{
                    put(tempTable.get(i).get(j).getKey(), tempTable.get(i).get(j).getValue());
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
