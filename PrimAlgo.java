package graphTheory;
import java.util.*;


public class PrimAlgo {

	int vertices;
	LinkedList<Node>[] graph;
	static Set<Pair> result;
	PriorityQueue<Pair> q;
	boolean[] visited;
	
	class Node {
		int vertex;
		int weight;
		
		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
	}
	
	class Pair implements Comparable<Pair>{
		int vertex;
		int parent;
		int weight;
		public Pair(int vertex, int parent, int weight) {
			this.vertex = vertex;
			this.parent = parent;
			this.weight = weight;
		}
		
		@Override
		public String toString() {
			return "Pair [vertex=" + vertex + ", parent=" + parent + ", weight=" + weight + "]";
		}
		@Override
		public int compareTo(Pair o) {
			if(this.weight > o.weight) return 1;
			if(this.weight < o.weight) return -1;
			return 0;
		}
		
	}
	
	public PrimAlgo(int vertices) {
		this.vertices = vertices;
		graph = new LinkedList[vertices];
		result = new HashSet<>();
		q = new PriorityQueue();
		visited = new boolean[vertices];
		
		for (int i = 0; i < vertices; i++) {
			graph[i] = new LinkedList<>();   // initialised to make new list here instead of null
		}
		
	}
	
	public void prims() {
		
		// intialising the things
		q.add(new Pair(0, -1, 0));
		
		while(!q.isEmpty()) {
			
			Pair p = q.remove();
			if(visited[p.vertex]) {
				continue;
			}
			
			visited[p.vertex] = true;
			
			if(!(p.parent == -1)) {
				result.add(p);
			}
			
			for(Node n : graph[p.vertex]) {
				if(!visited[n.vertex]) {
					q.add(new Pair(n.vertex, p.vertex, n.weight));
				}
			}
			
		}
		
		
	}
	
	public void addEdge(int source, int destination, int weight) {
		
		graph[source].add(new Node(destination, weight));
		graph[destination].add(new Node(source, weight));
		
	}
	
	
	  public static void main(String[] args)
	    {
	        int v = 9;
	  
	        PrimAlgo e = new PrimAlgo(v);
	  
	        e.addEdge( 0, 1, 4);
	        e.addEdge(0, 7, 8);
	        e.addEdge( 1, 2, 8);
	        e.addEdge( 1, 7, 11);
	        e.addEdge( 2, 3, 7);
	        e.addEdge( 2, 8, 2);
	        e.addEdge( 2, 5, 4);
	        e.addEdge( 3, 4, 9);
	        e.addEdge( 3, 5, 14);
	        e.addEdge( 4, 5, 10);
	        e.addEdge( 5, 6, 2);
	        e.addEdge( 6, 7, 1);
	        e.addEdge( 6, 8, 6);
	        e.addEdge( 7, 8, 7);
	  
	        // Method invoked
	        e.prims();
	        
	        for(Pair p : result) {
	        	System.out.println(p);
	        }
	    }
}
