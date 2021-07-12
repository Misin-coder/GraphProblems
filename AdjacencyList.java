package graphTheory;

import java.util.HashMap;
import java.util.LinkedList;

// consider directed graph, weighted

public class AdjacencyList {

	private class Node {

		// bcoz by default java will compare key based on memory address so to prevent
		// override hashcode and equals
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + character;
			result = prime * result + weight;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (character != other.character)
				return false;
			if (weight != other.weight)
				return false;
			return true;
		}

		char character;
		int weight;

		public Node(char character, int weight) {
			this.character = character;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "(" + character + ", " + weight + ")";
		}

		private AdjacencyList getEnclosingInstance() {
			return AdjacencyList.this;
		}

	}

	HashMap<Node, LinkedList<Node>> graph;

	public AdjacencyList() {
		graph = new HashMap<>();
	}

	public void addEdge(char origin, char destination, int weight) {
		addEdge(new Node(origin, 0), new Node(destination, weight));
	}

	private void addEdge(Node origin, Node destination) {

		if (!graph.containsKey(origin)) {
			graph.put(origin, new LinkedList<Node>());
		}

		graph.get(origin).add(destination);
	}

	public void printGraph() {

		for (Node key : graph.keySet()) {
			System.out.print(key.character + " --> ");
			if (graph.get(key) != null) {
				for (Node n : graph.get(key)) {
					System.out.print(n + " ");
				}
			}
			System.out.println();
		}

	}
}
