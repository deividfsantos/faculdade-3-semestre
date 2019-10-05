import java.util.Scanner;

/******************************************************************************
 *  Compilation:  javac CriticalPath.java
 *  Execution:    java CriticalPath
 *  Dependencies: EdgeWeightedDigraph.java DirectedEdge.java Topological.java AcyclicLP.java
 *  Data files:   https://algs4.cs.princeton.edu/44sp/jobsPC.txt
 *
 *  Computes critical path from a scheduling specification
 *
 *  % java CriticalPath
 *
 ******************************************************************************/

public class CriticalPath {

    public CriticalPath(String filename)
    {
       In arq = new In(filename);

       int totalJobs = arq.readInt();
       int totalVerts = totalJobs * 2 + 2;

       EdgeWeightedDigraph g = new EdgeWeightedDigraph(totalVerts);

       int start = totalJobs * 2;
       int finish = totalJobs * 2 + 1;

       for(int i=0; i<totalJobs; i++)
       {
           double duration = arq.readDouble();
           int totalDeps = arq.readInt();
           for(int d=0; d<totalDeps; d++)
           {
               int dep = arq.readInt();
               DirectedEdge e = new DirectedEdge(i*2+1, dep*2, 0);
               g.addEdge(e);
           }
           g.addEdge(new DirectedEdge(start, i*2, 0));
           g.addEdge(new DirectedEdge(i*2+1, finish, 0));
           g.addEdge(new DirectedEdge(i*2, i*2+1, duration));
       }
       arq.close();
       AcyclicLP lp = new AcyclicLP(g, start);
       System.out.println("Caminho Crítico:");
       for(DirectedEdge e: lp.pathTo(finish)) {
           e.setAttr("color=\"red\", penwidth=5");
           if(e.from() < start && e.from() % 2 == 0) // aresta é início de uma tarefa?
               System.out.println("Tarefa: "+e.from()/2);
//               System.out.println(e.from()+" -> "+e.to()+" ("+e.getAttr()+")");
       }
       System.out.println();
       System.out.println(g.toDot());
    }

    public static void main(String args[])
    {
        new CriticalPath("jobsPC.txt");
    }

}
