import java.util.LinkedList;

public class DFSPaths {
    private boolean marked[];
    private int edgeTo[];
    private int s;

    public DFSPaths(Graph g, int source) {
        this.s = source;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        dfs(g, s);
    }

    public boolean hasPath(int w) {
        return marked[w];
    }

    public Iterable<Integer> pathTo(int w) {
        if (!hasPath(w)) return null;
        LinkedList<Integer> res = new LinkedList<>();
        for (int x = w; x != s; x = edgeTo[x])
            res.addFirst(x);
        res.addFirst(s);
        return res;
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
                edgeTo[w] = v;
            }
        }
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        StdOut.println(G);
        StdOut.println();
        DFSPaths dfs = new DFSPaths(G, 0);
        for (int v = 0; v < G.V(); v++) {
            StdOut.print(v + ": ");
            if (dfs.hasPath(v)) {
                for (int x : dfs.pathTo(v))
                    StdOut.print(x + " ");
                StdOut.println();
            } else
                StdOut.println("sem caminho");
        }
    }

}
