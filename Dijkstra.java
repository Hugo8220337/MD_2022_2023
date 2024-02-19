import java.io.*;
import java.util.*;

public class Dijkstra {
   private static final Graph.Edge[] GRAPH = {
      new Graph.Edge("Aveiro", "Coimbra", 80),
      new Graph.Edge("Aveiro", "Porto", 70),
      new Graph.Edge("Aveiro", "Viseu", 100),
      new Graph.Edge("Beja", "Evora", 80),
      new Graph.Edge("Beja", "Faro", 170),
      new Graph.Edge("Beja", "Setubal", 135),
      new Graph.Edge("Braga", "Porto", 50),
      new Graph.Edge("Braga", "Viana do Castelo", 50),
      new Graph.Edge("Braga", "Vila Real", 100),
      new Graph.Edge("Braganca", "Guarda", 200),
      new Graph.Edge("Braganca", "Vila Real", 140),
      new Graph.Edge("Castelo Branco", "Coimbra", 160),
      new Graph.Edge("Castelo Branco", "Guarda", 100),
      new Graph.Edge("Castelo Branco", "Portalegre", 80),
      new Graph.Edge("Castelo Branco", "Santarem", 200),
      new Graph.Edge("Coimbra", "Aveiro", 80),
      new Graph.Edge("Coimbra", "Castelo Branco", 160),
      new Graph.Edge("Coimbra", "Guarda", 160),
      new Graph.Edge("Coimbra", "Leiria", 70),
      new Graph.Edge("Coimbra", "Viseu", 80),
      new Graph.Edge("Evora", "Beja", 80),
      new Graph.Edge("Evora", "Lisboa", 150),
      new Graph.Edge("Evora", "Portalegre", 100),
      new Graph.Edge("Evora", "Santarem", 120),
      new Graph.Edge("Faro", "Beja", 170),
      new Graph.Edge("Faro", "Setubal", 260),
      new Graph.Edge("Guarda", "Braganca", 200),
      new Graph.Edge("Guarda", "Castelo Branco", 100),
      new Graph.Edge("Guarda", "Coimbra", 160),
      new Graph.Edge("Guarda", "Vila Real", 150),
      new Graph.Edge("Guarda", "Viseu", 80),
      new Graph.Edge("Leiria", "Coimbra", 70),
      new Graph.Edge("Leiria", "Lisboa", 130),
      new Graph.Edge("Lisboa", "Evora", 150),
      new Graph.Edge("Lisboa", "Leiria", 130),
      new Graph.Edge("Lisboa", "Santarem", 70),
      new Graph.Edge("Lisboa", "Setubal", 50),
      new Graph.Edge("Portalegre", "Castelo Branco", 80),
      new Graph.Edge("Portalegre", "Evora", 100),
      new Graph.Edge("Portalegre", "Santarem", 150),
      new Graph.Edge("Porto", "Aveiro", 70),
      new Graph.Edge("Porto", "Braga", 50),
      new Graph.Edge("Porto", "Viana do Castelo", 80),
      new Graph.Edge("Porto", "Vila Real", 120),
      new Graph.Edge("Santarem", "Castelo Branco", 200),
      new Graph.Edge("Santarem", "Evora", 120),
      new Graph.Edge("Santarem", "Lisboa", 70),
      new Graph.Edge("Santarem", "Portalegre", 150),
      new Graph.Edge("Setubal", "Beja", 135),
      new Graph.Edge("Setubal", "Faro", 260),
      new Graph.Edge("Setubal", "Lisboa", 50),
      new Graph.Edge("Viana do Castelo", "Braga", 50),
      new Graph.Edge("Viana do Castelo", "Porto", 80),
      new Graph.Edge("Vila Real", "Braga", 100),
      new Graph.Edge("Vila Real", "Braganca", 140),
      new Graph.Edge("Vila Real", "Guarda", 150),
      new Graph.Edge("Vila Real", "Porto", 120),
      new Graph.Edge("Vila Real", "Viseu", 110),
      new Graph.Edge("Viseu", "Aveiro", 100),
      new Graph.Edge("Viseu", "Coimbra", 80),
      new Graph.Edge("Viseu", "Guarda", 80),
      new Graph.Edge("Viseu", "Vila Real", 110),
   };
   private static final int TAMANHO_GRAFO = 18;
   public static final String START = "Evora";
   private static final String[] ARRAY_CAPITAIS = { "Aveiro", "Beja", "Braga", "Braganca",  "Bragança", "Castelo Branco", "Coimbra", "Evora", "faro", "Guarda", "Leiria", "Lisboa", "Portalegre", "Porto", "Santarem", "Setubal", "Viana do Castelo", "Vila Real", "Viseu" };

   public static void main(String[] args) {
      Graph g = new Graph(GRAPH);

      System.out.println("\nCaminho de " + Dijkstra.START + " a " + "Aveiro" + ":");
      g.dijkstra(START);
      g.printPath("Aveiro"); // primeira iteração
      g.printAllPaths(); //restantes iterações

   }
}

class Graph {
   private final Map<String, Vertex> graph; // mapping of vertex names to Vertex objects, built from a set of Edges

   /** One edge of the graph (only used by Graph constructor) */
   public static class Edge {
      public final String v1, v2;
      public final int dist;

      public Edge(String v1, String v2, int dist) {
         this.v1 = v1;
         this.v2 = v2;
         this.dist = dist;
      }
   }

   /** One vertex of the graph, complete with mappings to neighbouring vertices */
   public static class Vertex implements Comparable<Vertex> {
      public final String name;
      public int dist = Integer.MAX_VALUE; // MAX_VALUE assumed to be infinity
      public Vertex previous = null;
      public final Map<Vertex, Integer> neighbours = new HashMap<>();

      public Vertex(String name) {
         this.name = name;
      }

      private void printPath() {
         if (this == this.previous) {
            System.out.printf("%s", this.name);
         } else if (this.previous == null) {
            System.out.printf("%s(unreached)", this.name);
         } else {
            this.previous.printPath();
            System.out.printf(" -> %s(%d)", this.name, this.dist);
         }
      }

      public int compareTo(Vertex other) {
         if (dist == other.dist)
            return name.compareTo(other.name);

         return Integer.compare(dist, other.dist);
      }

      @Override
      public String toString() {
         return "(" + name + ", " + dist + ")";
      }
   }

   /** Builds a graph from a set of edges */
   public Graph(Edge[] edges) {
      graph = new HashMap<>(edges.length);

      // one pass to find all vertices
      for (Edge e : edges) {
         if (!graph.containsKey(e.v1))
            graph.put(e.v1, new Vertex(e.v1));
         if (!graph.containsKey(e.v2))
            graph.put(e.v2, new Vertex(e.v2));
      }

      // another pass to set neighbouring vertices
      for (Edge e : edges) {
         graph.get(e.v1).neighbours.put(graph.get(e.v2), e.dist);
         // graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // also do this for
         // an undirected graph
      }
   }

   /** Runs dijkstra using a specified source vertex */
   public void dijkstra(String startName) {
      if (!graph.containsKey(startName)) {
         System.err.printf("Graph doesn't contain start vertex \"%s\"\n", startName);
         return;
      }
      final Vertex source = graph.get(startName);
      NavigableSet<Vertex> q = new TreeSet<>();

      // set-up vertices
      for (Vertex v : graph.values()) {
         v.previous = v == source ? source : null;
         v.dist = v == source ? 0 : Integer.MAX_VALUE;
         q.add(v);
      }

      dijkstra(q);
   }

   /** Implementation of dijkstra's algorithm using a binary heap. */
   private void dijkstra(final NavigableSet<Vertex> q) {
      Vertex u, v;
      while (!q.isEmpty()) {

         u = q.pollFirst(); // vertex with shortest distance (first iteration will return source)
         if (u.dist == Integer.MAX_VALUE)
            break; // we can ignore u (and any other remaining vertices) since they are unreachable

         // look at distances to each neighbour
         for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
            v = a.getKey(); // the neighbour in this iteration

            final int alternateDist = u.dist + a.getValue();
            if (alternateDist < v.dist) { // shorter path to neighbour found
               q.remove(v);
               v.dist = alternateDist;
               v.previous = u;
               q.add(v);
            }
         }
      }
   }

   /** Prints a path from the source to the specified vertex */
   public void printPath(String endName) {
      if (!graph.containsKey(endName)) {
         System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endName);
         return;
      }

      graph.get(endName).printPath();
      System.out.println();
   }

   /**
    * Prints the path from the source to every vertex (output order is not
    * guaranteed)
    */
   public void printAllPaths() {
      for (Vertex v : graph.values()) {
         if(v.name != Dijkstra.START) {
            System.out.println("\nCaminho de " + Dijkstra.START + " a " + v.name + ":");
            v.printPath();
            System.out.println();
         }
      }
   }
}