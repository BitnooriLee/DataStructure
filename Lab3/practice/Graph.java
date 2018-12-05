import java.util.*;

public class Graph {
	public class Vertex implements Comparable<Vertex> {
		public final String name;
		public ArrayList<Edge> neighbours; // 현재의 Vortex에 이웃으로 연결되는 Edge를 저장
		public LinkedList<Vertex> path; // 현재 node까지 오는데 최소 거리의 경로를 저장
		public int minDistance = Integer.MAX_VALUE; // 현재 node까지 오는데 최소 거리를 저장

        public Vertex(String name) {
			this.name = name;
			neighbours = new ArrayList<Edge>();
			path = new LinkedList<Vertex>();
        }

        public int compareTo(Vertex other) {
			return Integer.compare(minDistance, other.minDistance);
		}
	}

	public class Edge {
		public final Vertex dest; // 현재 node를 기준으로 Edge를 통해 연결될 목적지 node
        public final int weight;

		public Edge(Vertex dest, int weight) {
			this.dest = dest;
			this.weight = weight;
        }
	}

	private HashMap<String, Vertex> vertices;

	public Graph() {
		vertices = new HashMap<String, Vertex>();
	}

	public void addVertex(String vert) {
		vertices.put(vert, new Vertex(vert));
	}

	public void addEdge(String src, String dest, int weight) {
		Vertex s = vertices.get(src); // Edge의 방향: A -> B
		s.neighbours.add(new Edge(vertices.get(dest), weight));

		Vertex d = vertices.get(dest); // Edge의 방향: B -> A, Undirected이므로 이렇게 양방향에 같은 점수를 입력해 줌
		d.neighbours.add(new Edge(vertices.get(src), weight));
    }

	public String shortestPath(String src, String dest) {
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

				if (neighbour.target.minDistance > newDist) { // 현재 node에서 이웃 node로 연결되는 distance가, 지금까지 계산된 이웃 node까지 가는 거리보다 작은 경우
					queue.remove(neighbour.target); // 이웃 node의 업데이트를 위해 일단 queue에서 지움
					neighbour.target.minDistance = newDist; // 새로 계산된 최단 거리를 입력

					neighbour.target.path = new LinkedList<Vertex>(u.path); // 이웃 node로 갈때, 현재 node까지의 path
					neighbour.target.path.add(u); // 거기다가 현재의 위치를 path에 추가 (현재 node를 거쳐가므로)

					queue.add(neighbour.target); // 업데이트된 이웃 node를 입력
				}
			}
        }
        
		return vertices.get(dest).minDistance==Integer.MAX_VALUE?"null":Integer.toString(vertices.get(dest).minDistance);
    }

	public static void main(String[] arg) {
		Graph g = new Graph();
		g.addVertex("A");
		g.addVertex("B");
		g.addVertex("C");
		g.addVertex("D");
		g.addVertex("E");
		g.addVertex("F");
		g.addEdge("A", "B", 2);
		g.addEdge("B", "E", 3);
		g.addEdge("A", "D", 7);
		g.addEdge("D", "E", 1);
		g.addEdge("B", "C", 1);

        System.out.println(g.shortestPath("A", "C"));  // totalDist: 3, vertices: [A, B, C]
		System.out.println(g.shortestPath("D", "A"));  // totalDist: 6, vertices: [D, E, B, A]
		System.out.println(g.shortestPath("E", "F"));  // null
	}

}

