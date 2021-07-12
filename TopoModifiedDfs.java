package graphTheory;

import java.util.*;

public class TopoModifiedDfs {

	int vertices;
	LinkedList<Integer>[] graph; // for adjacency list
	static Stack<Integer> s; // to store result
	boolean[] visited; // to avoid cycles

	public TopoModifiedDfs(int vertices) {
		this.vertices = vertices;
		this.graph = new LinkedList[vertices];
		this.s = new Stack<>();
		visited = new boolean[vertices];
		for (int i = 0; i < vertices; i++) {
			graph[i] = new LinkedList<>();
		}
	}

	public void topologicalSort(int vertex) {
		visited[vertex] = true;

		for (int i : graph[vertex]) {
			if (!visited[i]) {
				topologicalSort(i);
			}
		}

		s.add(vertex);
	}
	
	public void addEdge(int i, int j) {
		graph[i].add(j);
		
	}
	
	

	public static void main(String[] args) {
		
		TopoModifiedDfs g = new TopoModifiedDfs(5);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 3);
		g.addEdge(2, 4);
		
		g.topologicalSort(0);
		
		
		
		System.out.println("____________________");
		
		for(int i = 0; i < 5; i++) {
			System.out.println(s.pop());
		}

	}

}
