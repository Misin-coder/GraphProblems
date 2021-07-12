package graphTheory;

import java.util.*;

public class KrushalAlgo {

	int vertices;
	LinkedList<Node>[] graph;

	class Node implements Comparable<Node> {
		int source;
		int destination;
		int weight;

		public Node(int source, int destination, int weight) {
			this.source = source;
			this.destination = destination;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			if (this.weight > o.weight)
				return 1;
			if (this.weight < o.weight)
				return -1;
			return 0;
		}

	}

	public KrushalAlgo(int vertices) {
		this.vertices = vertices;
		this.graph = new LinkedList[vertices];

		for (int i = 0; i < vertices; i++) {
			graph[i] = new LinkedList<>();
		}
	}

	public void kruskal() {
        
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		DisjointSet d = new DisjointSet(vertices);
		
		for(int i = 0; i < vertices; i++) {
			for(Node n: graph[i])  {
				q.add(n);
			}	
			d.make_set(i);
		}
		
		while(!q.isEmpty()) {
			Node n = q.poll();
			int source = n.source;
			int destination = n.destination;
			int weight = n.weight;
			
			
			if(d.find1(source) != d.find1(destination)) {
           	 System.out.println(source + "  --> " + destination + "  =  " + weight);
           	 d.union1(source, destination);
            }
			
		}
        	 
             
        	 
        
         
		
		
	}

	public static void main(String[] args) {

		KrushalAlgo e = new KrushalAlgo(9);
		
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
        
        e.kruskal();
		
	}

	private void addEdge(int i, int j, int k) {
		graph[i].add(new Node(i, j, k));
		graph[j].add(new Node(j, i, k));
		
	}

}
