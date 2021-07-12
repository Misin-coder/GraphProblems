package graphTheory;

import java.util.*;

public class TopoSortKahn {

	int counter; // for my add method i did this
	int vertices;
	LinkedList<Integer>[] graph; // for adjacency list
	static int[] result; // to store result
	Queue<Integer> q; // to maintain fifo kind of nature as kahn algo is bfs only
	int[] indegree; // to update and set indegree of each vertex

	public TopoSortKahn(int vertices) {
		this.vertices = vertices;
		this.counter = 0;
		this.graph = new LinkedList[vertices];
		result = new int[vertices];
		q = new LinkedList<>();
		indegree = new int[vertices];
		
		for(int i = 0; i < vertices; i++) {
			graph[i] = new LinkedList<>();
		}

	}

	public void topoLogicalSort() {

		// write indegree of each vertex
		for (int i = 0; i < vertices; i++) {
			for (int j : graph[i]) {
				indegree[j]++;
			}
		}

		// node having indegree add to queue;
		for (int i = 0; i < vertices; i++) {
			if (indegree[i] == 0) {
				q.add(i);
			}
		}

		// while queue is not empty pop head of queue and decrease indegree of its
		// adjacent

		
		while (!q.isEmpty()) {

			int i = q.poll();

			// once removed from queue add your element to result
			add(i);

			// decrease indegree of adjacent
			for (int x : graph[i]) {
				indegree[x]--;
				if (indegree[x] == 0) {
					q.add(x);
				}

			}

		}
		if(result.length != vertices) {
			System.out.println("topological sort cannot exist bcoz a cycle exist in your graph");
		}
	}
	
	public void addEdge(int i, int j) {
		graph[i].add(j);
		
	}
	
	public void add(int i) {
		result[counter++] = i;
	}
	
	
	
	public static void main(String[] args) {
		
		TopoSortKahn g = new TopoSortKahn(6);
		g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        
        g.topoLogicalSort();
        
        for(int x : result) {
        	System.out.println(x);
        }
		
	}

	

}
