
public class AppShortestPathComparison {

	public static void main(String[] args) {

		//Digraph dg = DigraphGenerator.complete(1000);
		//Digraph dg = DigraphGenerator.binaryTree(1000);
		//Digraph dg = DigraphGenerator.dag(1000, 1000);
		
		/*
		EdgeWeightedDigraph DG = new EdgeWeightedDigraph(dg.V());
		for(int v=0; v<dg.V(); v++)
		{
			for(int w: dg.adj(v))
			{
				double weight = StdRandom.uniform(1, 10);
				DG.addEdge(new DirectedEdge(v, w, weight));
			}
		}
		*/
		
		// Carrega EWG de um arquivo texto e cria um AdjMatrixEdgeWeightedDigraph 
		
		EdgeWeightedDigraph DG = new EdgeWeightedDigraph(new In("1000EWD.txt"));

		AdjMatrixEdgeWeightedDigraph G = new AdjMatrixEdgeWeightedDigraph(DG.V());
		for (DirectedEdge e : DG.edges())
			G.addEdge(e);

		// StdOut.println(G);

		// Executa Floyd-Warshall
		System.out.println("Inicio do Floyd-Warshall");
		FloydWarshall spt = new FloydWarshall(G);

		System.out.println("Tempo do Floyd-Warshall: " + spt.tempoTotal());

		// Mesmo para Dijkstra
		System.out.println("\nInicio do Dijkstra");
		long totalTime = 0;
		// Executa o algoritmo para todos os vertices
		for (int v = 0; v < DG.V(); v++) {
			DijkstraSP dij = new DijkstraSP(DG, v);
			totalTime += dij.elapsedTime();
		}

		System.out.println("Tempo do Dijskstra:      " + totalTime);

	}

}
