package graphTheory;

import java.util.*;

public class GraphColoring {

	int vertices;
	LinkedList<Integer>[] graph;
	int[] color;
	int m;
	static String[] array;

	public GraphColoring(int vertices, int m) {
		this.vertices = vertices;
		this.m = m;
		this.graph = new LinkedList[vertices];
		this.color = new int[vertices];

		for(int i = 0; i < vertices; i++) {
			graph[i] = new LinkedList<>();
		}
	}

	public void addEdge(int source, int destination) {
		graph[source].add(destination);
		graph[destination].add(source);

	}

	// generating state space tree
	public void mColoring(int vertex) {

		// when base condition is met print the colors
		if (vertex == vertices) {
			for (int i = 0; i < color.length; i++) {
				System.out.print(array[color[i]] + "  ");
			}
			System.out.println();
			return;
		}

		// try all possible combination of available colors
		for (int i = 1; i <= m; i++) {
			// checking the bounding condition
			if (safe(vertex, i)) {
				// color the node 
				color[vertex] = i;
				// recur to next node
				mColoring(vertex + 1);
				// when backtracking removing the color
				color[vertex] = 0;
			}
		}
	}

	public boolean safe(int vertex, int i) {

		for (int n : graph[vertex]) {
			if (color[n] == i) {
				return false;
			}
		}
		return true;

	}

	public static void main(String[] args) {
		
		GraphColoring.array = new String[] {"", "blue", "green", "red" };
		

		GraphColoring g = new GraphColoring(6, 3);
		g.addEdge(0, 1);
		g.addEdge(0, 5);
		g.addEdge(1, 4);
		g.addEdge(2, 3);
		g.addEdge(0, 4);
		g.addEdge(4, 5);
		g.addEdge(1, 3);
		g.addEdge(2, 4);
		
		g.mColoring(0);
		
	}

}
