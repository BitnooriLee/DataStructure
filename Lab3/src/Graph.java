import java.util.*;

public class Graph { //class 
	public class Vertex implements Comparable<Vertex> { //class(Vertex < Graph)
		// declare variable
		public final String name;
		public ArrayList<Edge> neighbours; // 현재의 Vortex에 이웃으로 연결되는 Edge를 저장
		public LinkedList<Vertex> path; // 현재 node까지 오는데 최소 거리의 경로를 저장
		public int minDistance = Integer.MAX_VALUE; // 현재 node까지 오는데 최소 거리를 저장

        public Vertex(String name) { // constructor (Vertex)
			//object 
        	this.name = name;
			neighbours = new ArrayList<Edge>();
			path = new LinkedList<Vertex>();
        }
        
        @Override //to use toString ???? 
        public String toString() {
            return name;
        }

        public int compareTo(Vertex other) { // method (Vertex)
			return Integer.compare(minDistance, other.minDistance);
		}
	}

	public class Edge { // class (Edge < Graph)
		// declare variable
		public final Vertex dest; // 현재 node를 기준으로 Edge를 통해 연결될 목적지 node
        public final int weight;

		public Edge(Vertex dest, int weight) {
			this.dest = dest;
			this.weight = weight;
        }
			
	}

	private HashMap<String, Vertex> vertices; // type variable....?

	public Graph() { // Constructor (Graph)
		//object
		vertices = new HashMap<String, Vertex>();
	}

	public void addVertex(String vert) { // method(Graph)
		vertices.put(vert, new Vertex(vert));
	}

	public void addEdge(String src, String dest, int weight) { // method(Graph)
		Vertex s = vertices.get(src); // Edge의 방향: A -> B
		s.neighbours.add(new Edge(vertices.get(dest), weight));

		Vertex d = vertices.get(dest); // Edge의 방향: B -> A, Undirected이므로 이렇게 양방향에 같은 점수를 입력해 줌
		d.neighbours.add(new Edge(vertices.get(src), weight));
    }
	
	public static class Path { //class (Path < Graph)
	        public int totalDist;
	        public List<String> vertices;
	        public Path(int totalDist, List<String> vertices) {
	            this.totalDist = totalDist;
	            this.vertices = vertices;
	        }

	        @Override //to use toString ???? 
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
		
        for (String key: vertices.keySet()) { // 그래프 초기화, 밑에서 Dijkstra 알고리즘이 한바퀴 돌 때마다 minDistance와 path에 값들이 추가되기 때문에 새로 계산을 시작하기 전에 초기화
            vertices.get(key).minDistance = Integer.MAX_VALUE;
            vertices.get(key).path = new LinkedList<Vertex>();
        }

        Vertex source = vertices.get(src); // 시작 node
		source.minDistance = 0; // 시작 node이므로 최소거리는 0
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		queue.add(source); // 현재 node부터 탐색 시작

		while (!queue.isEmpty()) {
			Vertex u = queue.poll(); // 현재 node를 queue에서 꺼냄
			for (Edge neighbour: u.neighbours) { // 현재 node의 이웃 node들을 살펴봄
				int newDist = u.minDistance + neighbour.weight; // 현재 node에서 이웃 node로 연결되는 distance

				if (neighbour.dest.minDistance > newDist) { // 현재 node에서 이웃 node로 연결되는 distance가, 지금까지 계산된 이웃 node까지 가는 거리보다 작은 경우
					queue.remove(neighbour.dest); // 이웃 node의 업데이트를 위해 일단 queue에서 지움
					neighbour.dest.minDistance = newDist; // 새로 계산된 최단 거리를 입력

					neighbour.dest.path = new LinkedList<Vertex>(u.path); // 이웃 node로 갈때, 현재 node까지의 path
					neighbour.dest.path.add(u); // 거기다가 현재의 위치를 path에 추가 (현재 node를 거쳐가므로)

					queue.add(neighbour.dest); // 업데이트된 이웃 node를 입력
				}
			}
        }
		
		List<String> temp = new ArrayList<>();
		
		for(int i=0;i<vertices.get(dest).path.size(); i++) {
			temp.add(vertices.get(dest).path.get(i).toString());
		}
		//List<String> temp = (LinkedList<String>)vertices.get(dest).path.clone();
			temp.add(dest);
		
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