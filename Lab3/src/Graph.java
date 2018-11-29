import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class Graph {
	
	private HashMap<Vertex, TreeSet<Vertex>> myAdjList;
	private HashMap<String, Vertex> myVertices;
	private static final TreeSet<Vertex> EMPTY_SET = new TreeSet<Vertex>();
	private int myNumVertices;
	private int myNumEdges;

	/**
	 * Construct empty Graph
	 */
	public Graph() {
		myAdjList = new HashMap<Vertex, TreeSet<Vertex>>();
		myVertices = new HashMap<String, Vertex>();
		myNumVertices = myNumEdges = 0;
	}
    public void addVertex(String label) { 
    	
    	Vertex v;
		v = myVertices.get(name);
		if (v == null) {
			v = new Vertex(name);
			myVertices.put(name, v);
			myAdjList.put(v, new TreeSet<Vertex>());
			myNumVertices += 1;
		}
		return v;
    	
    	//exist? error 
    	
    	//adds a node with the given label. You may assume that the node does not already exist in the graph.
    }
    
    public void addEdge(String node1, String node2, int dist) { 
    	
    	Vertex v, w;
		if (hasEdge(from, to))
			return;
		myNumEdges += 1;
		if ((v = getVertex(from)) == null)
			v = addVertex(from);
		if ((w = getVertex(to)) == null)
			w = addVertex(to);
		myAdjList.get(v).add(w);
		myAdjList.get(w).add(v);
		
    	//adds an edge with weight dist between the nodes node1 and node2. 
    	//You may assume that the two nodes exist in the graph and that dist is non-negative.
    }
    
    public static class Path {
        public int totalDist;
        public List<String> vertices;
        public Path(int totalDist, List<String> vertices) {
            this.totalDist = totalDist;
            this.vertices = vertices;
        }

        @Override
        public String toString() {
            return "totalDist: " + totalDist + ", vertices: " + vertices.toString();
        }
    }
    
    public Path shortestPath(String start, String dest) { ... }
}