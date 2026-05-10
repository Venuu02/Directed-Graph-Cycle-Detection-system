import java.util.*;

public class Main {
    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Please provide an input file name.");
            return;
        }

        String filename = args[0];
        Graph graph = GraphParser.parseFromFile(filename);

        if (graph.isEmpty()) {
            System.out.println("Graph is empty.");
            return;
        }

        System.out.println("Graph loaded successfully.");
        System.out.println("Vertices: " + graph.getVertices());
        System.out.println("Adjacency List: " + graph.getAdjacencyList());

        System.out.println("\nStarting sink elimination...");

        long start = System.nanoTime();

        while (!graph.isEmpty()) {
            Integer sink = graph.findSink();

            if (sink == null) {
                System.out.println("No sink found.");
                System.out.println("Graph is acyclic: NO");

                
                List<Integer> cycle = CycleFinder.findCycle(graph);
                if (cycle != null) {
                    System.out.println("Cycle found: " + cycle);
                }

                long end = System.nanoTime();
                long duration = end - start;
                System.out.println("Execution time: " + duration + " ns");

                return;
            }

            System.out.println("Sink found: " + sink);
            System.out.println("Removing sink: " + sink);
            graph.removeVertex(sink);
        }

        System.out.println("Graph is acyclic: YES");

        long end = System.nanoTime();
        long duration = end - start;
        System.out.println("Execution time: " + duration + " ns");
    }
}