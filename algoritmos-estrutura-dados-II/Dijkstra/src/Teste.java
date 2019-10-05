import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class Teste {

    public static void main(String[] args) {

        String arg = args[0];
        System.out.println(arg);

        In in = new In("/home/18203069/DriveH/faculdade/algoritmos-estrutura-dados-II/Dijkstra/tinyEWG.txt");
        EdgeWeightedDigraph dig = new EdgeWeightedDigraph(in);
        System.out.println(" Total vértices: "+dig.V());
        System.out.println("Total arestas: "+dig.E());

        String styleSheet =
                "node {" +
                        "	fill-color: black;" +
                        "}" +
                        "node.marked {" +
                        "	fill-color: red;" +
                        "}";

        Dijkstra dijkstra = new Dijkstra(dig, 0);

        Graph g = new SingleGraph("Dijkstra");
        String[] allLines = in.readAllLines();
        for (int i = 2; i < allLines.length; i++) {
            String node = allLines[i].split(" ")[0];
            g.addNode(node);
        }

        for (DirectedEdge edge :
                dig.edges()) {
            g.addEdge(String.valueOf(edge.weight()),String.valueOf(edge.to()),String.valueOf( edge.from()), true);
        }

        g.addAttribute("ui.quality");
        g.addAttribute("ui.antialias");
        g.addAttribute("ui.stylesheet", styleSheet);
        g.display();

    }
}
