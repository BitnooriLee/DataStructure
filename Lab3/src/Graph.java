import java.util.*;

public class Graph { //class 

	// The graph contains vertices that have neighbours
	private HashMap<String, Vertex> vertices;
	// Hashmaps are used to keep track of the shortest path from a source 
	// to a destination.
	private Map<String, List<String>> minPaths = new HashMap<>();
	private HashMap<String, Integer> minDists;
	
	public Graph() {
		vertices = new HashMap<String, Vertex>();
		minDists = new HashMap<String, Integer>();
	}
	/**
	 * Create a new vertex by adding it values to the hashmaps.
	 * This is assumed to have O(1) complexity
	 * @param vert the name of the vertex to be added.
	 */
	public void addVertex(String vert) {
		vertices.put(vert, new Vertex(vert));
		minPaths.put(vert, new ArrayList<String>());
		minDists.put(vert, Integer.MAX_VALUE);
	}

	/**
	 * Add a weighted edge between two vertices. Every vertex has a list 
	 * of all of its neighbours, this functions add an edge object to 
	 * both of the given vertices
	 * @param src 1st vertex
	 * @param dest 2nd vertex
	 * @param weight the weight of the edge
	 */
	public void addEdge(String src, String dest, int weight) { // method(Graph)
		Vertex s = vertices.get(src); 
		s.neighbours.add(new Edge(vertices.get(dest), weight));

		Vertex d = vertices.get(dest);
		d.neighbours.add(new Edge(vertices.get(src), weight));
    }
	
	public static class Path { //class (Path < Graph)
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

	/**
	 * Uses Dijkstras' algorithm to find the shortest path between the src and
	 * destination node.
	 * @param src is the name of the starting vertex
	 * @param dest the name of the destination vertex
	 * @return a Path object containing the shortest path between the vertices
	 */
	public Path shortestPath(String src, String dest) { // method(Graph)
		// If src = dest return a trivial path
		if(src.equals(dest)) {
			List<String> temp = new ArrayList<>();
			temp.add(dest);
			return new Path(0, temp);
		}
		// Initialize the vertices giving the source a 0 distance and every other
		// vertex an infinet distance
        for (String key: vertices.keySet()) {
            vertices.get(key).minDistance = Integer.MAX_VALUE;
            vertices.get(key).path = new ArrayList<String>();

        }

        Vertex source = vertices.get(src);
		source.minDistance = 0; 
		// Keep track of vertices to visit
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		queue.add(source);
		
		// Look through all of the vertices in the graph
		while (!queue.isEmpty()) {
			Vertex u = queue.poll();
			// Look through all of the neighbours and determine that 
			// they have a minimal path. If not copy the current path  
			// to the destination vertex of the edge and add the source 
			// vertex to the path also update the minimal distance. Do
			// this until there are no more neighbours.
			for (Edge neighbour: u.neighbours) {
				int newDist = u.minDistance + neighbour.weight; 

				if (neighbour.dest.minDistance > newDist) { 
					queue.remove(neighbour.dest); 
	
					neighbour.dest.minDistance = newDist;
					
					neighbour.dest.path = new ArrayList<String>(u.path);
					neighbour.dest.path.add(u.toString());
					minPaths.replace(neighbour.dest.toString(), neighbour.dest.path);
					
					queue.add(neighbour.dest);
				}
			}
        }
		
		List<String> temp = minPaths.get(dest);
		// Destination vertex was ignored above so it is added here 
		// to complete the path
		temp.add(dest);
		Path path = new Path(vertices.get(dest).minDistance,temp);
		
		// If minDistance is still inifity then the vertex didn't have any edges. In this case
		// return null.
		return vertices.get(dest).minDistance==Integer.MAX_VALUE? null:path;
    } // TODO: change return type ?

	public static void main(String[] arg) {
		Graph g = new Graph();
		g.addVertex("V0");
		g.addVertex("V1");
		g.addVertex("V2");
		g.addEdge("V1", "V2", 0);
		g.addEdge("V1", "V0", 2);
		g.addEdge("V0", "V2", 0);
		g.shortestPath("V2", "V1");  // result: totalDist: 0, vertices: [V2, V1]
		g.shortestPath("V2", "V0");  // result: totalDist: 0, vertices: [V2, V0]
		System.out.println(g.shortestPath("V0", "V1"));  // result: totalDist: 2, vertices: [V0, V1]
	}

}