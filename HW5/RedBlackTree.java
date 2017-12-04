import java.util.ArrayList;

/**
 * Write a description of class RedBlackTree here.
 * 
 * Christian Wiemer
 * @version (a version number or a date)
 */
public class RedBlackTree<K extends Comparable<K>, V>
{
    public int elements;
    private final boolean RED = true;
    private final boolean BLACK = false;
    public Node<K,V> root; 
    /**
     * Constructor for objects of class RedBlackTree
     */
    public RedBlackTree()
    {
        root = null;
        elements = 0;
    }
    
    /**
     * Adds a new key-value pair to the tree
     */
    public void put(K key, V value){
        root = findAndAdd(root, key, value);
        root.setColor(BLACK);
    }
    
    /**
     * Finds the value of the inputed key
     * 
     * @params key: the key of whose value we're looking for
     */
    public V get(K key){
        return get(root, key);
    }
    
    private V get(Node<K,V> node, K key){
        if(node == null) return null;
        
        int cmp = key.compareTo(node.key);
        
        if(cmp > 0) return get(node.right, key);
        else if(cmp < 0) return get(node.left, key);
        else return node.value;
    }

    // remove key-value pair
    public void delete(K key){
        
    }

    /**
     * Returns the number of key-value pairs in the tree
     */
    public int size(){
        return elements;
    }

    /**
     * Returns the first ordered key in the tree
     */
    public K getMinKey(){
        Node<K,V> node = fallLeft(root);
        return node.key;
    }
    
    /**
     * Returns the last ordered key in the tree
     */
    public K getMaxKey(){
        Node<K,V> node = fallRight(root);
        return node.key;
    }

    /**
     * Returns the preceding key from the inputed key
     * 
     * @params key: the key whose predecessor we're looking for
     */
    public K findPredecessor(K key){
        Node<K,V> node = findNode(root, key);
        if(node == null){
            return null;
        }
        if(node.left != null){
            node = fallRight(node.left);
        }
        
        return node.key;
        //If there's a left child, go to that and keep going right till you can't
    }

    /**
     * Returns the succeding key from the inputed key
     * 
     * @params key: the key whose successor we're looking for
     */
    public K findSuccessor(K key){
        Node<K,V> node = findNode(root, key);
        if(node == null){
            return null;
        }
        if(node.right != null){
            node = fallLeft(node.right);
        }
        
        return node.key;
        //If there's a right child, go to that and keep going left till you can't
    }


    /**
     * Checks if a key is within the tree
     * 
     * @params key: the key whose presence we're checking for
     */
    public boolean contains(K key) { return (get(key) != null); }

    /**
     * Checks if the tree is empty or not
     */
    public boolean isEmpty() { return (size()==0); }
    
    private boolean isRed(Node<K,V> node){
        if(node == null){
            return false;
        }
        if(node.isRed){
            return true;
        } else{
            return false;
        }
    }
    
    //Finds a node based on a key
    private Node<K,V> findNode(Node<K,V> node, K key){
        if(node == null)return null;
        
        int cmp = key.compareTo(node.key);
        
        if(cmp > 0){
            return findNode(node.left, key);
        } else if(cmp < 0){
            return findNode(node.right, key);
        } else{
            return node;
        }
    }
    
    //Follows the right child of a node continuosly till it's at the end.
    private Node<K,V> fallRight(Node<K,V> node){
        if(node.right == null){
            return node;
        } else{
            return fallRight(node.right);
        }
    }
    
    //Follows the left child of a node continuosly till it's at the end
    private Node<K,V> fallLeft(Node<K,V> node){
        if(node.left == null){
            return node;
        } else{
            return fallRight(node.left);
        }
    }
    
    private Node<K,V> rotateLeft(Node<K,V> node){
        Node <K,V> tempNode = null;
        Node <K,V> tempNode2 = null;
        Node <K,V> weirdNode = null;
        Node <K,V> finalNode;
        boolean rootCol = isRed(node);
        boolean rightCol = isRed(node.right);
        //Isolate the node that needs to be directly repositioned
        if(node.right.left != null){
            weirdNode = node.getRight().getLeft();
            node.right.left = null;
        }
        //Isolate the whole right branch
        if(node.right != null){
            tempNode = node.getRight();
            node.right = null;
        }
        tempNode2 = node;
        
        finalNode = tempNode;
        finalNode.setColor(rootCol);
        finalNode.left = tempNode2;
        finalNode.left.setColor(rightCol);
        finalNode.left.right = weirdNode;
        
        //Fix size
        finalNode.left.recalcSize();
        if(finalNode.right != null)finalNode.right.recalcSize();
        finalNode.recalcSize();
        return finalNode;
    }
    
    private Node<K,V> rotateRight(Node<K,V> node){
        Node <K,V> tempNode = null;
        Node <K,V> tempNode2 = null;
        Node <K,V> weirdNode = null;
        Node <K,V> finalNode;
        boolean rootCol = isRed(node);
        boolean leftCol = isRed(node.left);
        //Isolate the node that needs to be directly repositioned
        if(node.left.right != null){
            weirdNode = node.left.right;
            node.left.right = null;
        }
        //Isolate the whole left branch
        if(node.left != null){
            tempNode = node.left;
            node.left = null;
        }
        tempNode2 = node;
        
        finalNode = tempNode;
        finalNode.setColor(rootCol);
        finalNode.right = tempNode2;
        finalNode.right.setColor(leftCol);
        finalNode.right.left = weirdNode; 
        
        //fix size
        if(finalNode.left != null) finalNode.left.recalcSize();
        finalNode.right.recalcSize();
        finalNode.recalcSize();
        return finalNode;
    }
    
    private Node<K,V> findAndAdd(Node<K,V> node, K key, V value){
        if (node == null) {
            elements++;
            return new Node<K,V>(key,value, null, null, RED);
        }
        
        //follow the BST rules
        int cmp = key.compareTo(node.key);
        if (cmp<0) node.left = findAndAdd(node.left, key, value);
        else if (cmp>0) node.right = findAndAdd(node.right, key, value);
        else node.value = value;
        
        //going up & fixing things
        if (isRed(node.right) && !isRed(node.left)){
            node = rotateLeft(node);
        }
        
        if (isRed(node.left) && isRed(node.left.left)){
            node = rotateRight(node);
        }
        
        if (isRed(node.right) && isRed(node.left)){
            node.colorFlip();
        }
        
        node.recalcSize();
        return node;
    }
    
    private class Node<K, V>{
        public K key;
        public V value;
        public Node<K,V> left;
        public Node<K,V> right;
        public boolean isRed;
        public int size;
        
        public Node(K key, V value, Node<K,V> left, Node<K,V> right, boolean isRed){
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.isRed = isRed;
            size = 1;
        }
        
        public Node<K,V> getLeft(){
            return left;
        }
        
        public Node<K,V> getRight(){
            return right;
        }
        
        public void setColor(boolean color){
            if(color){
                isRed = true;
            }
            else{
                isRed = false;
            }
        }
        
        public void colorFlip(){
            this.isRed = !this.isRed;
            this.left.isRed = !this.left.isRed;
            this.right.isRed = !this.right.isRed;
        }
        
        public void recalcSize(){
            int lsize = 0;
            int rsize = 0;
            if(left != null) lsize = left.size;
            if(right != null) rsize = right.size;
            size = lsize + rsize +1;
        }
    }
}
