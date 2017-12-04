import java.util.Scanner;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
/**
 * A Graph is a data structure that can represent something like a state map
 * 
 * @author Christian Wiemer 
 */
public class Graph
{
    public ArrayList<ArrayList<Edge>> graph = new ArrayList<ArrayList<Edge>>();
    public final int RADIUS = 6371;
    
    /**
     * Constructs a graph with a given number of vertices
     */
    public Graph(int numVertices){
        
    }
    
    /**
     * Constructs a graph with a given file
     */
    public Graph(Scanner scanner){
        
    }// from file
    
    /**
     * Constructs and adds an edge to the graph
     * 
     * @params v1: the edges starting vertex
     * @params v2: the edges ending vertex
     * @params weight: the 'weight' of the edge
     */
    public void addEdge(int v1, int v2, double weight){
        Edge e = new Edge(v1,v2,weight);
        graph.get(v1).add(e);
    }
    
    /**
     * Adds an edge to the graph
     * 
     * @params edge: the edge to add
     */
    public void addEdge(Edge edge){
        graph.get(edge.getV1()).add(edge);
    }// add edge
    
    /**
     * Searches for a path from a given start to a given finish
     * 
     * @params start: the starting vertex
     * @params goal: the vertex to search for a path to
     * @return: true if a path exists from start to goal
     */
    public boolean search(City start, City goal) {
        // init
        Set<City> closedList = new HashSet<City>();
        PriorityQueue<City> openList = new PriorityQueue<City>();
        openList.add(start);
        // loop while more to explore
        while (!openList.isEmpty()) {
            // get active, do basic checks
            City active = openList.peek();
            if (closedList.contains(active)) continue;
            if (active == goal) return true;
            // add to open list
            for (City v : active.getAdjacencyList()) {
                if (!closedList.contains(v)) openList.add(v);
            }
            // finish exploring
            closedList.add(active);
        }
        // if we get here, we ran out of vertices to explore
        return false;
    }
    
    // The calculation for the weight of an edge
    private double heuristic(double lat1, double lon1, double lat2, double lon2){
        return Math.toRadians(Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) 
        * Math.cos(lat2) * Math.cos(lon1-lon2)))*RADIUS;
    }
    
    // Edge helper class
    private class Edge implements Comparable<Edge> {
        public int v1, v2;
        public double weight;
        public Edge(int v1, int v2, double weight){
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }// init

        public double getWeight(){
            return weight;
        }

        public int getOther(int v){  // other vertex
            if(v == v1){
                return v2;
            } else if (v == v2){
                return v1;
            } else{
                return -1;
            }
        }
        
        public int getV1(){
            return v1;
        }
        
        public int getV2(){
            return v2;
        }

        public int getEither(){
            return -1;
        }// either vertex

        public int compareTo(Edge other){
            return -1;
        }// comparison
    }
}
