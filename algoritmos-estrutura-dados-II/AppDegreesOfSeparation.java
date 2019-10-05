import java.util.HashMap;
import java.util.Map;

public class AppDegreesOfSeparation {

    public static void main(String[] args) {
        In in = new In("Movies.txt");
        String[] filmes = in.readAllLines();
        Map<String, Integer> all = new HashMap<>();
        for (int i = 0; i < filmes.length; i++) {
            String[] actor = filmes[i].split("/");
            for (int j = 0; j < actor.length; j++) {
                all.put(actor[j], i);
            }
        }
        System.out.println(all);
    }
}
