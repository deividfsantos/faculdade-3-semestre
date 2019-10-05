import java.util.HashSet;
import java.util.Set;

public class Cycle {

    private boolean[] marked;
    private Set<String> edgeSet;
    private Graph g;

    public Cycle(Graph g) {
        this.marked = new boolean[g.V()];
        this.g = g;
        this.edgeSet = new HashSet<>();
    }

    private boolean containsCycle(int v) {
        boolean hasCycle = false;
        marked[v] = true;
        for (int u : g.adj(v)) {
            if (!marked[u]) {
                edgeSet.add(u + "+" + v);
                containsCycle(u);
            } else {
                if (!edgeSet.contains(u + "+" + v)) {
                    hasCycle = true;
                }
            }
        }
        return hasCycle;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        Cycle cycle = new Cycle(G);
        System.out.println(cycle.containsCycle(0));
    }
}
