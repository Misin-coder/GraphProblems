package graphTheory;

import java.util.*;

// undirected graph

public class EularGraphProblem {

	int vertices;
	LinkedList<Integer>[] g;
	static LinkedList<Integer> path;
	int degree[];

	public EularGraphProblem(int vertices) {
		this.vertices = vertices;
		g = new LinkedList[vertices];
		path = new LinkedList<>();

		for (int i = 0; i < vertices; i++) {
			g[i] = new LinkedList<>();
		}
		degree = new int[vertices];
	}

	public void addEdge(int source, int destination) {
		g[source].add(destination);
		g[destination].add(source);
		

	}

	// check non zero vertices are connected or not
	public boolean isConnected() {

		// start from a non zero vertex
		int v = 0;
		for (int i = 0; i < vertices; i++) {
			if (g[i].size() > 0) {
				v = i;
				break;
			}
		}

		// if there are no edges in the graph return true;
		if (v == vertices) {
			return true;
		}

		// do a dfs transversal of non zero vertex
		boolean[] visited = new boolean[vertices];
		dfs(v, visited);

		// after transversal if some vertex are unvisited and have degree > 0 thats
		// means
		// there is other component having edge
		for (int i = 0; i < vertices; i++) {
			if (visited[i] == false && g[i].size() > 0) {
				return false;
			}
		}
		return true;
	}

	// if all vertex degree is even or only two vertex have odd degree return true
	public boolean isEulerian() {

		if (!isConnected()) {
			return false;
		}
		int count = 0;
		for (int i = 0; i < vertices; i++) {
			// check odd and even
			if (g[i].size() % 2 == 1) {
				count++;
			}
		}

		if (count > 2) {
			return false;
		}
		// count cannot be 1 in undirected graph
		return true;

	}

	public void eularPath() {
		if (isEulerian()) {
			// lets find startVertex;
			int start = 0;
			for (int i = 0; i < vertices; i++) {
				if (g[i].size() % 2 == 1) {
					start = i;
				}
				degree[i] = g[i].size();
			}

			algo(start);
		} else {
			System.out.println("not possible");
		}

	}

	private void algo(int start) {

		Stack<Integer> s = new Stack<>();
		int current = start;
		
		while(!s.isEmpty() || g[current].size() != 0) {
			
			// if vertex doesnot have neibour then do this
			if(g[current].size() == 0) {
				
				path.add(current);
//				System.out.println(current);
				current = s.pop();
			} else {
				s.add(current);
				int neighbour = g[current].getLast();
//				System.out.println(current);
//				System.out.println(neighbour);
				g[current].remove(new Integer(neighbour));
				g[neighbour].remove(new Integer(current));
				current = neighbour;
			}
			
		}
       // add the remaining stack element in the path as well;
		path.add(current);
		
	}

	private void dfs(int v, boolean[] visited) {
		visited[v] = true;
		for (int i : g[v]) {
			if (!visited[i]) {
				dfs(i, visited);
			}
		}

	}

	public static void main(String[] args) {

		EularGraphProblem g4 = new EularGraphProblem(5);
		g4.addEdge(0, 1);
		g4.addEdge(0, 4);
		g4.addEdge(1, 2);
		g4.addEdge(1, 3);
		g4.addEdge(2, 3);
		g4.eularPath();
		for(int x : path) {
			System.out.println("  " + x);
		}

	}

}
