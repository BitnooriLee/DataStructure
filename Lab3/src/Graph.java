import java.util.*;

public class Graph { //class 
	public class Vertex implements Comparable<Vertex> { //class(Vertex < Graph)
		// declare variable
		public final String name;
		public ArrayList<Edge> neighbours; // Edge current Vortex to neighbours 
		public LinkedList<Vertex> path; // min distance path to current node 
		public int minDistance = Integer.MAX_VALUE; // min distance to current node 

        public Vertex(String name) { // constructor (Vertex)
			//object 
        	this.name = name;
			neighbours = new ArrayList<Edge>();
			path = new LinkedList<Vertex>();
        }
        
        @Override //to use toString 
        public String toString() {
            return name;
        }

        public int compareTo(Vertex other) { // method (Vertex)
			return Integer.compare(minDistance, other.minDistance);
		}
	}

	public class Edge { // class (Edge < Graph)
		// declare variable
		public final Vertex dest; // destination node from current node through the Edge
        public final int weight; 

		public Edge(Vertex dest, int weight) {
			this.dest = dest;
			this.weight = weight;
        }
			
	}

	private HashMap<String, Vertex> vertices; // type variable

	public Graph() { // Constructor (Graph)
		//object
		vertices = new HashMap<String, Vertex>();
	}

	public void addVertex(String vert) { // method(Graph)
		vertices.put(vert, new Vertex(vert));
	}

	public void addEdge(String src, String dest, int weight) { // method(Graph)
		Vertex s = vertices.get(src); // Edge: A -> B
		s.neighbours.add(new Edge(vertices.get(dest), weight));

		Vertex d = vertices.get(dest); // Edge: B -> A, Undirected
		d.neighbours.add(new Edge(vertices.get(src), weight));
    }
	
	public static class Path { //class (Path < Graph)
	        public int totalDist;
	        public List<String> vertices;
	        public Path(int totalDist, List<String> vertices) {
	            this.totalDist = totalDist;
	            this.vertices = vertices;
	        }

	        @Override //to use toString 
	        public String toString() {
	            return "totalDist: " + totalDist + ", vertices: " + vertices.toString();
	        }
	}

	public Path shortestPath(String src, String dest) { // method(Graph)
		if(src.equals(dest)) {
			List<String> temp = new ArrayList<>();
			temp.add(dest);
			return new Path(0, temp);
		}
		
        for (String key: vertices.keySet()) { // reset Graph, Everytime Dijkstra Algorithm executes, minDistance and path are added, 
        									  // so reset before calculation
            vertices.get(key).minDistance = Integer.MAX_VALUE;
            vertices.get(key).path = new LinkedList<Vertex>();
        }

        Vertex source = vertices.get(src); // Start node
		source.minDistance = 0; // Start node, minDistance = 0
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		queue.add(source); // current node

		while (!queue.isEmpty()) {
			Vertex u = queue.poll(); // pop current node from the queue
			for (Edge neighbour: u.neighbours) { // check neighbour of current node
				int newDist = u.minDistance + neighbour.weight; // distance from current to neighbour node

				if (neighbour.dest.minDistance > newDist) { // current distance vs minDistance
					queue.remove(neighbour.dest); // remove for update 
					neighbour.dest.minDistance = newDist; // update distance

					neighbour.dest.path = new LinkedList<Vertex>(u.path); //  path from current node to neighbour 
					neighbour.dest.path.add(u); // add current node to path 

					queue.add(neighbour.dest); // update neighbour node
				}
			}
        }
		
		List<String> temp = new ArrayList<>();
		
		for(int i=0;i<vertices.get(dest).path.size(); i++) { // convert data type 
			temp.add(vertices.get(dest).path.get(i).toString());
		}
			temp.add(dest); // add current node to path 
		
		Path path = new Path(vertices.get(dest).minDistance,temp); //new LinkedList<String>
		
		return vertices.get(dest).minDistance==Integer.MAX_VALUE? null:path;
    } // TODO: change return type ?

	public static void main(String[] arg) {
		Graph g = new Graph();
		g.addVertex("V0");
		g.addVertex("V1");
		g.addEdge("V0", "V1", 0);
		g.shortestPath("V0", "V0");  // result: totalDist: 0, vertices: [V0]
		g.shortestPath("V0", "V1");  // result: totalDist: 0, vertices: [V0, V1]
		
		System.out.println(g.shortestPath("V0", "V0"));
		System.out.println(g.shortestPath("V0", "V1"));
	}

}