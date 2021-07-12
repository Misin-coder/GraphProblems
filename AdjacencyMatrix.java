package graphTheory;

// representing graph using adjacency matrix
//  considering undirected, unweighted graph

public class AdjacencyMatrix {

	int vertices;
	int[][] graph;

	public AdjacencyMatrix(int vertices) {
		this.vertices = vertices;
		graph = new int[vertices][vertices];
	}

	public void addEdge(int source, int destination) {
		graph[source][destination] = 1;
		graph[destination][source] = 1;
	}

	public void printGraph() {
		for (int i = 0; i < vertices; i++) {
			for (int j = 0; j < vertices; j++) {
				System.out.print(graph[i][j] + " ");
			}
			System.out.println();
		}
	}
}
