package graphTheory;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    // adjacency list to represent graph i am using
	
	int vertices;
	LinkedList<Integer>[] graph;

	public BFS(int vertices) {
		this.vertices = vertices;
		this.graph = new LinkedList[vertices];
		for (int i = 0; i < vertices; i++) {
			graph[i] = new LinkedList<Integer>();
		}
	}

	public void addEdge(int origin, int destination) {
		graph[origin].add(destination);
	}

	public void bfs(int origin) {
		// create boolean array to mark visited nodes
		// create queue to attain fifo
		boolean[] visited = new boolean[vertices];
		Queue<Integer> q = new LinkedList<>();

		// add starting node to queue and mark it visited to your array
		q.add(origin);
		visited[origin] = true;

		// while q is not empty remove the front of the queue and add its neighbour to
		// queue and mark
		while (!q.isEmpty()) {

		    origin = q.poll();
			System.out.print(origin + " ");

			for (int neighbour : graph[origin]) {
				if (!visited[neighbour]) {
					q.add(neighbour);
					visited[neighbour] = true;
				}
			}
		}

	}

	public static void main(String[] args) {

		BFS g = new BFS(4);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);
		
		g.bfs(2);
		System.out.println();
		g.bfs(0);

	}

}
