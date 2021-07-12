package graphTheory;

import java.util.*;

public class FulkFurkersonEdmond {

	int vertices; // vertices in your graph
	int[][] residualGraph; // for fulkerson you will have to convert your graph to residualgraph
	int maxFlow;   // to return max flow possible in the graph
	HashMap<Integer, Integer> parent; // to trace  augumented path

	public FulkFurkersonEdmond(int vertices, int graph[][]) {

		this.vertices = vertices;
		residualGraph = new int[vertices][vertices];
		//initially my residual graph will be equal to graph bcoz flow is zero
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				residualGraph[i][j] = graph[i][j];
			}
		}
		maxFlow = 0;
		parent = new HashMap<>();
	}

	// through bfs we will find a augumented path from source to sink

	public boolean bfs(int source, int sink) {
    
		// bfs
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[vertices];
		q.add(source);
		visited[source] = true;
		parent.put(source, -1);

		while (!q.isEmpty()) {

			int u = q.remove();

			for (int v = 0; v < vertices; v++) {
				
				// very important line that if edge is greater than 0 vertex is not visited
				if (!visited[v] && residualGraph[u][v] > 0) {
					parent.put(v, u);
					// if sink is found return
					if (v == sink) {
						return true;
					}
					// otherwise keep adding and mark visited
					q.add(v);
					visited[v] = true;
				}
			}
		}
		return false;
	}

	public int maxFlow(int source, int sink) {
		
		// while augumented path exist then
		while (bfs(source, sink)) {

			// find flow/bottleneck
			int min = Integer.MAX_VALUE;
			
			// find min for augumented path
			int v = sink;
			while (v != source) {
				int u = parent.get(v);
				min = Math.min(residualGraph[u][v], min);
				v = u;
			}
			
			// add min to max flow
			maxFlow += min;
			
			// update/refresh the residual graph 
			
			// didnot write v = sink and this mother fucker wasted my hours 
			 v = sink;

			while (v != source) {
				int u = parent.get(v);
				// when reversing reduce capacity of edge and for its opposite edge increase as
				// bcoz that would mean that you can undo this capacity
				residualGraph[u][v] -= min;
				residualGraph[v][u] += min;
				v = u;
			}
			
		}
		return maxFlow;

	}

	public static void main(String[] args) {

		int graph[][] = new int[][] { { 0, 16, 13, 0, 0, 0 }, { 0, 0, 10, 12, 0, 0 }, { 0, 4, 0, 0, 14, 0 },
				{ 0, 0, 9, 0, 0, 20 }, { 0, 0, 0, 7, 0, 4 }, { 0, 0, 0, 0, 0, 0 } };

		FulkFurkersonEdmond maxflow = new FulkFurkersonEdmond(6, graph);
		System.out.println(maxflow.maxFlow(0, 5));

	}

}
