import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ExercicioMST {
    private static final String NEWLINE = System.getProperty("line.separator");

    public static void main(String[] args) throws IOException {
        In in = new In(args[0]);
        String[] strings = in.readAllStrings();
        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(strings.length);
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings.length; j++) {
                String[] point = strings[i].split(";");
                Double x1 = Double.valueOf(point[0].replace("\"", ""));
                Double y1 = Double.valueOf(point[1].replace("\"", ""));

                String[] point2 = strings[j].replace("\"", "").split(";");
                Double x2 = Double.valueOf(point2[0]);
                Double y2 = Double.valueOf(point2[1]);

                double weight = Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
                Edge edge = new Edge(i, j, weight);
                edgeWeightedGraph.addEdge(edge);
            }
        }

        List<String> vertices = new ArrayList<>();
//
//        KruskalMST kruskalMST = new KruskalMST(edgeWeightedGraph);
//        for (Edge k : kruskalMST.edges()) {
//            int either = k.either();
//            int other = k.other(either);
//            if (either > other) {
//                vertices.add(either + " " + other + " 1");
//            } else {
//                vertices.add(other + " " + either + " 1");
//            }
//        }

        KruskalMST kruskalMST = new KruskalMST(edgeWeightedGraph);
        for (Edge e : edgeWeightedGraph.edges()) {
            int eithere = e.either();
            int othere = e.other(eithere);
            for (Edge k : kruskalMST.edges()) {
                int either = k.either();
                int other = k.other(either);
                if (either > other) {
                    String e1 = either + " " + other;
                    String e2 = eithere + " " + othere;
                    if (e1.equals(e2)) {
                        vertices.add(e1 + " 1");
                    } else {
                        vertices.add(e1 + " 0");
                    }
                } else {
                    String e1 = other + " " + either;
                    String e2 = othere + " " + eithere;
                    if (e1.equals(e2)) {
                        vertices.add(e1 + " 1");
                    } else {
                        vertices.add(e1 + " 0");
                    }
                }
            }
        }

        String pathname = System.getProperty("user.dir") + "/ehnois.txt";
        System.out.println(pathname);
        File file = new File(pathname);
        Files.createFile(file.toPath());
        Files.write(file.toPath(), vertices);
    }

    public static String toString(EdgeWeightedGraph edgeWeightedGraph) {
        StringBuilder s = new StringBuilder();
        s.append(edgeWeightedGraph.V() + " " + edgeWeightedGraph.E() + NEWLINE);
        for (int v = 0; v < edgeWeightedGraph.V(); v++) {
            for (Edge e : edgeWeightedGraph.adj(v)) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static String toDot(KruskalMST kruskalMST) {
        StringBuilder s = new StringBuilder();
        s.append("graph {" + NEWLINE);
        s.append("rankdir = LR;" + NEWLINE);
        s.append("node [shape = circle];" + NEWLINE);
        for (Edge e : kruskalMST.edges()) {
            String attrib = "label=" + e.weight();
            if (e.getColor() != null)
                attrib += ", color=" + e.getColor();
            s.append(e.either() + " -- " + e.other(e.either()) + " [" + attrib + "]" + NEWLINE);
        }
        s.append("}");
        return s.toString();
    }

}
