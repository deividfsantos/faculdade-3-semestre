//import edu.princeton.cs.algs4.EdgeWeightedDigraph;
//import edu.princeton.cs.algs4.In;
//import org.graphstream.graph.Edge;
//import org.graphstream.graph.Graph;
//import org.graphstream.graph.Node;
//import org.graphstream.graph.implementations.SingleGraph;
//
//public class AppDijkstra {
//
//	public static void main(String[] args) {
//
//		// Exemplo: lendo os dados do arquivo com as classes do Sedgewick
//		EdgeWeightedDigraph dig = new EdgeWeightedDigraph(new In("tinyEWG.txt"));
//		System.out.println("Total vértices: "+dig.V());
//		System.out.println("Total arestas: "+dig.E());
//
//		String styleSheet =
//	            "node {" +
//	            "	fill-color: black;" +
//	            "}" +
//	            "node.marked {" +
//	            "	fill-color: red;" +
//	            "}";
//
//		// API do Graphstream:
//
//		// Cria um grafo
//		Graph g = new SingleGraph("Dijkstra");
//
//		// Exemplo: criando vértices (nodos), adicionando atributos (opcionalmente)
//		Node a = g.addNode("A" );
//		// ui.label = rótulo do vértice
//		a.addAttribute("ui.label", "A");
//		// ui.classe = classe declarada na style sheet acima (pinta de vermelho)
//		a.setAttribute("ui.class", "marked");
//		// ui.style = outra forma de especificar o estilo do vértice. Neste caso, o tamanho é 30
//		a.setAttribute("ui.style", "size: 30;");
//		g.addNode("B" );
//		g.addNode("C" );
//
//		// Exemplo: criando arestas
//		// 4 parâmetros: nome da aresta, vértice inicial, vértice final, true se for dirigida
//		Edge ab = g.addEdge("AB", "A", "B", true);
//		// O rótulo da aresta equivale ao peso num grafo valorado
//		ab.addAttribute("ui.label", "12.5");
//		// Acrescenta um atributo weight para armazenar o peso numérico
//		ab.addAttribute("weight", 12.5);
//		// Usando ui.style para especificar o estilo: largura (size) e cor (fill-color)
//		ab.addAttribute("ui.style", "size: 5px; fill-color: red;");
//		g.addEdge("BC", "B", "C", true);
//		g.addEdge("CA", "C", "A", true);
//		/**/
//
//		// Exemplo: como acessar todos os vértices do grafo
//		for(Node n: g) {
//			System.out.print(n.getId()+": ");
//			// Acessando cada aresta do vértice
//			for(Edge e: n.getEachEdge())
//				System.out.print(e.getNode0().getId()+"->"+e.getNode1().getId()+"("+e.getAttribute("weight")+") ");
//			System.out.println();
//		}
//
//		// Exemplo: como acessar todas as arestas do grafo
//		for(Edge e:g.getEachEdge()) {
//			System.out.println(e.getId()); // etc.
//		}
//
//		// Solicita alta qualidade no desenho
//		g.addAttribute("ui.quality");
//		g.addAttribute("ui.antialias");
//
//		// Caso se queira utilizar todos os recursos de CSS do visualizador avançado:
//		//System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
//
//		// Informa a style sheet a ser utilizada (se for o caso)
//	    g.addAttribute("ui.stylesheet", styleSheet);
//
//	    // Exibe o grafo
//		g.display();
//
//	}
//}
