package graphTheory;

import java.util.*;

public class DjkstraAlgo {

	int vertices; // number of vertices/nodes in graph
	LinkedList<Node>[] graph;  // adjacency list am using
	static int[] dist; // made static to use inside main method ,, this array will contain updated distance
	PriorityQueue<Node> q; // priority queue used to get min distance Node
	Set<Integer> visited; // to maintain a list of all those node whose shortest path is calculated

	public DjkstraAlgo(int vertices) {

		this.vertices = vertices;
		this.graph = new LinkedList[vertices];
		dist = new int[vertices];
		q = new PriorityQueue(vertices, new Node()); // to order priority queue based on comparator we use 2 argument constructor
		visited = new HashSet<>();

		for (int i = 0; i < vertices; i++) {
			graph[i] = new LinkedList<>();   // initialised to make new list here instead of null
		}
	}

	public void addEdge(int source, int destination, int cost) {
		graph[source].add(new Node(destination, cost));
	}

	public void Djkstra(int source) {
		// set the distance for each vertex
		// source would be 0 rest infinity or Max-value
		for (int i = 0; i < vertices; i++) {
			dist[i] = Integer.MAX_VALUE; // added infinity to all ,
		}
		
		dist[source] = 0;

		// add source vertex to priority queue
		q.add(new Node(source, 0));


		// while my all vertices are not selected as a min among smallest one time
		// refracted in visited
		while (visited.size() != vertices) {

			// pick from priority queue the smallest/min distance
			int i = q.remove().node;

			// once node is removed from queue add to set
			visited.add(i);

			// relax adjacent vertex of this node;
			// And add the new node to queue bcoz distance has got updated so add new node as i dont have any method to update 
			// already present node and its distance
			

			for (Node v : graph[i]) {
				if (!visited.contains(v.node)) {
					if (dist[i] + v.cost < dist[v.node]) {
						dist[v.node] = dist[i] + v.cost;
						q.add(new Node(v.node, dist[v.node]));
					}
				}
			}

		}

	}

	
	// Comparator is implemented bcoz priority queue will need to know how to compare two nodes
	// priority internally will be using compare method so implement
	private class Node implements Comparator<Node> {
		int node;
		int cost;

		public Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		public Node() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public int compare(Node node1, Node node2) {
			if (node1.cost > node2.cost)
				return 1;
			if (node1.cost < node2.cost)
				return -1;
			return 0;
		}
	}

	public static void main(String[] args) {

		DjkstraAlgo d = new DjkstraAlgo(6);
		d.addEdge(0, 1, 5);
		d.addEdge(0, 2, 3);
		d.addEdge(0, 3, 2);
		d.addEdge(0, 4, 3);
		d.addEdge(0, 5, 3);
		d.addEdge(2, 1, 2);
		d.addEdge(2, 3, 3);

		d.Djkstra(0);
		for (int i = 0; i < 6; i++) {
			System.out.println(dist[i]);
		}

	}

}
