import java.util.ArrayList;
/**
 * Write a description of class City here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class City
{
    public String name;
    public double lat, lon;
    
    public City(String name, double lat, double lon){
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }
    
    public ArrayList<City> getAdjacencyList(){
        return null;
    }

}
