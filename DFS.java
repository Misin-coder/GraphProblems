package graphTheory;

import java.util.LinkedList;
import java.util.Stack;

public class DFS {

	int vertices;
	LinkedList<Integer>[] graph;

	public DFS(int vertices) {
		this.vertices = vertices;
		this.graph = new LinkedList[vertices];
		for (int i = 0; i < vertices; i++) {
			graph[i] = new LinkedList<>();
		}
	}
	
	public void addEdge(int origin, int destination) {
		graph[origin].add(destination);
	}

	// explicit stack based
	public void dfs(int origin) {

		// create a array for marking and a stack to attain lifo
		boolean[] visited = new boolean[vertices];
		Stack<Integer> s = new Stack<>();

		// add and mark first node
		s.add(origin);
		visited[origin] = true;

		// while stack isn't empty pop out the last and add and mark neighbour of it
		while (!s.isEmpty()) {

			origin = s.pop();
			System.out.print(origin + " ");

			for (int neighbour : graph[origin]) {
				if (!visited[neighbour]) {
					s.add(neighbour);
					visited[neighbour] = true;
				}
			}
		}
	}
	
	// implicit stack based
	
	public void dfs2(int origin) {
		boolean[] visited = new boolean[vertices];
		visited[origin] = true;
		dfshelper(origin, visited);
		
	}
	

	private void dfshelper(int origin, boolean[] visited) {
		System.out.print(origin + " ");
		
		for(int neighbour : graph[origin]) {
			if(!visited[neighbour]) {
				visited[neighbour] = true;
				dfshelper(neighbour, visited);
			}		
		}		
	}

	public static void main(String[] args) {

		DFS g = new DFS(4);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);
		
		g.dfs(0);
		System.out.println();
		g.dfs(2);
	}

}
