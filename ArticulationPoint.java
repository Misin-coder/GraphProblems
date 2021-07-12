package graphTheory;

import java.util.*;

public class ArticulationPoint {

	int vertices; // number of vertices in your graph
	LinkedList<Integer>[] graph; // for adjacency list graph representation
	int[] discoveryTime; // discovery time of a vertex while doing dfs
	int[] low; // lowest discovery time we can get
	boolean[] visited;
	static Set<Integer> Ap;
	int[] parent; // parent of each vertex while dfs transversal
	int time; // will help in setting discovery time

	public ArticulationPoint(int vertices) {
		this.vertices = vertices;
		this.graph = new LinkedList[vertices];
		this.discoveryTime = new int[vertices];
		this.low = new int[vertices];
		this.visited = new boolean[vertices];
		this.parent = new int[vertices];
		parent[0] = -1;
		time = 0;
		Ap = new HashSet<>();

		for (int i = 0; i < vertices; i++) {
			graph[i] = new LinkedList<>();
		}
	}

	public void ArticulationPoint(int vertex) {

		visited[vertex] = true;
		discoveryTime[vertex] = low[vertex] = time++;
		int children = 0;

		for (int adj : graph[vertex]) {

			if (!visited[adj]) {

				parent[adj] = vertex;
				children++;
				ArticulationPoint(adj);
				low[vertex] = Math.min(low[vertex], low[adj]);

				// check whether it is articulation point or not
				// if root node of dfs then check children else
				// if not root then we will check whether there is some backEdge that give low time
				if (parent[vertex] == -1 && children > 1 || low[adj] >= discoveryTime[vertex]) {

					Ap.add(vertex);

				}

			}
			
			// the if condition makes sure that tree edge is not counted but backEdge 
			// adjacent and parent of vertex are not same make sure else it will work even for tree Edge
			if (parent[vertex] != adj) {
				
				// we update low time of vertex to discovery time of adjacent
				low[vertex] = Math.min(low[vertex], discoveryTime[adj]);
			}

		}

	}

	public void addEdge(int source, int destination) {
		graph[source].add(destination);
		graph[destination].add(source);
	}

	public static void main(String[] args) {

		ArticulationPoint g1 = new ArticulationPoint(5);

		g1.addEdge(1, 0);
		g1.addEdge(0, 2);
		g1.addEdge(2, 1);
		g1.addEdge(0, 3);
		g1.addEdge(3, 4);
		
		g1.ArticulationPoint(0);
		
		for(int i : Ap) {
			System.out.println(i) ;
		}
	}

}
