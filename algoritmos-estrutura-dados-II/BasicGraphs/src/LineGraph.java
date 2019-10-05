import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LineGraph {

    public static void main(String[] args) {
        In in = new In(args[0]);

        List<Point> points = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("book.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                points.add(new Point(Integer.valueOf(values[0]), Integer.valueOf(values[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        EdgeWeightedGraph edgeWeightedGraph = new EdgeWeightedGraph(points.size());
        Double lessDistance = Double.MAX_VALUE;
        Double secondLessDistance = Double.MAX_VALUE;
        Double thirdLessDistance = Double.MAX_VALUE;
        Point p1 = points.get(0);
        Point p2 = points.get(0);
        Point p3 = points.get(0);
        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            for (int j = i; j < points.size(); j++) {
                Double distance = calculateDistance(p, points.get(j));
                if (distance < lessDistance) {
                    p3 = p2;
                    p2 = p1;
                    p1 = points.get(j);
                    thirdLessDistance = secondLessDistance;
                    secondLessDistance = lessDistance;
                    lessDistance = distance;
                } else if (calculateDistance(p, points.get(j)) < secondLessDistance) {
                    p3 = p2;
                    p2 = points.get(j);
                    thirdLessDistance = secondLessDistance;
                    secondLessDistance = distance;
                } else if (calculateDistance(p, points.get(j)) < thirdLessDistance) {
                    p3 = points.get(j);
                    thirdLessDistance = distance;
                }
            }
            Edge edge1 = new Edge(i, i+1, lessDistance);
            edgeWeightedGraph.addEdge(edge1);
            Edge edge2 = new Edge(i, i+1, secondLessDistance);
            edgeWeightedGraph.addEdge(edge2);
            Edge edge3 = new Edge(i, i+1, thirdLessDistance);
            edgeWeightedGraph.addEdge(edge3);
        }
    }

    private static Double calculateDistance(Point p1, Point p2) {
        Double pow1 = Math.pow(p1.getX() - p1.getY(), 2);
        Double pow2 = Math.pow(p2.getX() - p2.getY(), 2);
        return Math.sqrt(pow1 + pow2);
    }

}
