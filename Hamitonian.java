package graphTheory;

import java.util.*;

public class Hamitonian {

	int vertices;
	LinkedList<Integer>[] g;
	LinkedList<Integer> path;

	public Hamitonian(int vertices, int startVertex) {
		this.vertices = vertices;
		g = new LinkedList[vertices];
		path = new LinkedList<>();
		path.add(startVertex); // i am adding starting vertex here only

		for (int i = 0; i < vertices; i++) {
			g[i] = new LinkedList<>();
		}
	}

	public void addEdge(int source, int destination) {
		g[source].add(destination);
		g[destination].add(source);

	}

	public void hPath(int vertex) {

		// base case
		// either vertex == vertices - 1 bcoz 1st position of your path you have set so
		// you now you want
		// to go for a length rest 3 now refer to notebook
		// path.size() == vertices also valid and recommended one
		if (vertex == vertices - 1) {
			if (g[path.getFirst()].contains(path.getLast())) {
				for (int x : path) {
					System.out.print("  " + x);
				}
				System.out.println();
			}
			return;
		}

		// generating state space tree and bounding condition
		for (int i = 0; i < vertices; i++) {

			if (boundingCondition(i)) {
				// add the vertex to path
				path.add(i);
				// recur the vertex
				hPath(vertex + 1);
				// backtracking
				path.removeLast();
			}

		}

	}

	private boolean boundingCondition(int vertex) {
		// duplicates not allowed if it is the duplicate one then this one should be
		// explored further
		// if duplicate return false

		if (path.contains(vertex)) {
			return false;

		}

		// there should be an edge with the previous one if not return false
		if (!(g[path.getLast()].contains(vertex))) {

			return false;
		}

		return true;

	}

	public static void main(String[] args) {

		Hamitonian g = new Hamitonian(4, 0);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 3);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 3);
		g.hPath(0);
	}

}
