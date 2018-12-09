import java.util.*;

public class Vertex implements Comparable<Vertex> { //class(Vertex < Graph)
	// declare variable
	public final String name;
	public ArrayList<Edge> neighbours; 
//	public LinkedList<Vertex> path;
	public List<String> path;
	public int minDistance = Integer.MAX_VALUE; 

    public Vertex(String name) { // constructor (Vertex)
		//object 
    	this.name = name;
		this.neighbours = new ArrayList<Edge>();
		path = new LinkedList<String>();
    }
    
    @Override //to use toString ???? 
    public String toString() {
        return name;
    }

    public int compareTo(Vertex other) { // method (Vertex)
		return Integer.compare(minDistance, other.minDistance);
	}
}