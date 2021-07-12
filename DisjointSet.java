package graphTheory;

// naive implementation of disjoint set 

public class DisjointSet {

	int[] representative; // elements themselves are their own set/representative
	int[] rank;

	public DisjointSet(int number) {
		this.representative = new int[number];
		this.rank = new int[number];

	}

	public void make_set(int x) {
		representative[x] = x;
	}

	// this method give a representative/parent
	public int find(int x) {

		if (representative[x] == x) {
			return x;
		}
		return find(representative[x]);

	}

	// using caching mechanism that path compression
	public int find1(int x) {

		if (representative[x] == x) {
			return x;
		}
		int y = find(representative[x]);
		representative[x] = y;
		return y;

	}

	public void union(int x, int y) {
		int repre1 = find(x);
		int repre2 = find(y);

		if (repre1 == repre2)
			return;

		representative[x] = y;

	}

	// using union by rank
	public void union1(int x, int y) {
		int repre1 = find(x);
		int repre2 = find(y);

		if (repre1 == repre2)
			return;

		// get rank

		int r1 = rank[repre1];
		int r2 = rank[repre2];

		if (r1 > r2) {
			representative[repre2] = repre1;
		} else if (r2 > r1) {
			representative[repre1] = repre2;
		} else {
			representative[repre1] = repre2;
			rank[r2] += 1;
		}
		// rank represent height of tree / lowest level for a set/tree

	}

	public static void main(String[] args) {

		DisjointSet ds = new DisjointSet(5);
		ds.make_set(0);
		ds.make_set(1);
		ds.make_set(2);
		ds.make_set(3);
		ds.make_set(4);

		ds.union1(0, 1); //1
		ds.union1(2, 3); //3
		ds.union1(3, 4); //4

		System.out.println(ds.find1(1)); // 1
		System.out.println(ds.find1(2)); // 4 
		System.out.println(ds.find1(3)); // 4

	}

	// the problem is the time complexity is n bcoz the naive implementation leads
	// for long chains
	// we can use path compression and union by rank for efficiency

}
