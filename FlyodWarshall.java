package graphTheory;

public class FlyodWarshall {
	
	int vertices;
	int[][] graph;
	
	public FlyodWarshall() {
		// empty
	}
	
	public int[][] shortestPath(int[][] grap, int vertices) {
		this.graph = grap;
		this.vertices = vertices;
		
		// detailed explanation abdul bari video on youtube 
		// video how this formula is deduce
		// important thing a short can be through some vertex so that what we checking through first loop
		for(int k = 0; k < vertices; k++) {
			for(int i = 0; i < vertices; i++) {
				for(int j = 0; j < vertices; j++) {
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
		
		return graph;
		
	}
	
	
	public static void main(String[] args) {
		
		int INF = 999999;
		FlyodWarshall sp = new FlyodWarshall();
		
		int graph[][] = { {0,   5,  INF, 10},
                {INF, 0,   3, INF},
                {INF, INF, 0,   1},
                {INF, INF, INF, 0}
              };
		
		sp.shortestPath(graph, 4);
		
//		for(int[] x : graph) {
//			for(int y : x) {
//				System.out.println("  " + y);
//			}
//			System.out.println();
//		}
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(graph[i][j] == INF) {
					System.out.print("  " + "INF" );
				} else {
					System.out.print("    " + graph[i][j] );
				}
				
			}
			System.out.println();
		}
		
	}

}
