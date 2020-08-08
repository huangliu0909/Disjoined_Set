class disjointed_set{
	int[] parent;
	disjointed_set(int n){
		this.parent = new int[n];
		for(int i = 0; i < n; i ++) {
			this.parent[i] = -1;
		}
	}
	int find_set(int x) {
		if(this.parent[x] < 0) return x;
		return find_set(this.parent[x]);
	}
	void union(int x1, int x2) {
		int s1 = find_set(x1);
		int s2 = find_set(x2);
		if(s1 == s2) return;
		//union by rank, using size as rank
		if(this.parent[s1] <= this.parent[s2]) {
			//the set of x1 have more elements
			this.parent[s1] += this.parent[s2];
			this.parent[s2] = s1;
		}
		else {
			this.parent[s2] += this.parent[s1];
			this.parent[s1] = s2;
		}
	}
}

class graph{
	disjointed_set ds;
	graph(int v){
		this.ds = new disjointed_set(v);
	}
	void addEdge(int x1, int x2) {
		System.out.println("add edge: " + String.valueOf(x1) + " " + String.valueOf(x2));
		int s1 = this.ds.find_set(x1);
		int s2 = this.ds.find_set(x2);
		if(s1 == s2) System.out.println("there is a circle");
		this.ds.union(x1, x2);
	}
}

//main function
public class Main {
  public static void main(String[] args) {
	  //test for disjointed_set
	  disjointed_set ds = new disjointed_set(5);
      System.out.println(ds.find_set(3)); //output:3
      ds.union(1, 2);
      ds.union(1, 3);
      System.out.println(ds.find_set(1)); //output:1
      System.out.println(ds.find_set(2)); //output:1
      System.out.println(ds.find_set(3)); //output:1
      
      // test for finding a circle in a graph
	  graph g = new graph(3);
	  g.addEdge(0, 1); //add edge: 0 1
	  g.addEdge(1, 2); //add edge: 1 2
	  g.addEdge(0, 2); //add edge: 0 2 there is a circle
  }
}

