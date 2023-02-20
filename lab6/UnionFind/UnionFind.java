package UnionFind;
public class UnionFind {

    public int[] vertices ;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        vertices  = new int[n];

        for (int i = 0; i < n; i++) {
            vertices[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex < 0 || vertex >= vertices.length) {
            throw new RuntimeException("Not valid vertex");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);

        int curr_vertices = v1;

        while ( parent(curr_vertices) >= 0) {
            curr_vertices = parent(curr_vertices);
        }
        return -1 * parent(curr_vertices);
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return vertices[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {

        int root1 = find(v1);
        int root2 = find(v2);

        return (root1 == root2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie-break by connecting v1's root to v2's root. Unioning a
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {

        int root1 = find(v1);
        int root2 = find(v2);


        //Check if connected
        if(root1 == root2) {
            return;
        }

        if(vertices[root2] < vertices[root1]) {
            vertices[root2] += vertices[root1];
            vertices[root1] = root2;
        } else {
            vertices[root1] += vertices[root2];
            vertices[root2] = root1;
        }

    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);

        int curr_vertex = vertex;
        int root;

        while (parent(curr_vertex) >= 0) {
            curr_vertex = parent(curr_vertex);
        }
        root = curr_vertex;

        //Set all others to root
        int next_vertex;
        curr_vertex = vertex;
        while(curr_vertex != root) {
            next_vertex = parent(curr_vertex);
            vertices[curr_vertex] = root;
            curr_vertex = next_vertex;
        }

        return root;
    }

}
