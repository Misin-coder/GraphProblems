package graphTheory;

import java.util.*;

public class Bellman {

	int vertices;
	LinkedList<Node>[] graph;
	static int[] distance;

	public Bellman(int vertices) {
		this.vertices = vertices;
		graph = new LinkedList[vertices];
		this.distance = new int[vertices];

		for (int i = 0; i < vertices; i++) {
			graph[i] = new LinkedList<>();
			distance[i] = Integer.MAX_VALUE;
		}
	}

	class Node {
		int vertex;
		int weight;

		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
	}

	public void shortestPath(int source) {

		distance[source] = 0;
		for (int k = 0; k < vertices; k++) {
			for (int i = 0; i < vertices; i++) {
				for (Node n : graph[i]) {
					// relax the edges
					if (distance[i] + n.weight < distance[n.vertex]) {
						distance[n.vertex] = distance[i] + n.weight;
					}
				}
			}
		}

	}
	
	public void addEdge(int source, int vertex, int weight) {
		graph[source].add(new Node(vertex, weight));
	}

	public static void main(String[] args) {
		
		Bellman g = new Bellman(7);
		g.addEdge(0, 1, 6);
		g.addEdge(0, 2, 5);
		g.addEdge(0, 3, 5);
		g.addEdge(1, 4, -1);
		g.addEdge(2, 1, -2);
		g.addEdge(3, 2, -2);
		g.addEdge(3, 5, -1);
		g.addEdge(5, 6, 3);
		g.addEdge(4, 6, 3);
		g.shortestPath(0);
		
		for(int x : distance) {
			if(x == Integer.MAX_VALUE) {
				System.out.println("  INF" );
			} else {
				System.out.print("   " + x);
			}
			
		}
		

	}

}
