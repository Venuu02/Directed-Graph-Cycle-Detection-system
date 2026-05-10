import java.util.*;

public class Graph {

    private Map<Integer, List<Integer>> adj;

    private Map<Integer, List<Integer>> incoming;

    private Map<Integer, Integer> outDegree;

    private Set<Integer> vertices;

    public Graph() {
        adj = new HashMap<>();
        incoming = new HashMap<>();
        outDegree = new HashMap<>();
        vertices = new HashSet<>();
    }

    public void addEdge(int from, int to) {

        vertices.add(from);
        vertices.add(to);

        adj.putIfAbsent(from, new ArrayList<>());
        adj.putIfAbsent(to, new ArrayList<>());

        incoming.putIfAbsent(from, new ArrayList<>());
        incoming.putIfAbsent(to, new ArrayList<>());

        adj.get(from).add(to);
        incoming.get(to).add(from);

        outDegree.put(from, outDegree.getOrDefault(from, 0) + 1);

        outDegree.putIfAbsent(to, outDegree.getOrDefault(to, 0));
    }

    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    public Integer findSink() {
        for (int v : vertices) {
            if (outDegree.getOrDefault(v, 0) == 0) {
                return v;
            }
        }
        return null; 
    }

    public void removeVertex(int v) {

        if (!vertices.contains(v)) return;

        for (int parent : incoming.getOrDefault(v, new ArrayList<>())) {
            adj.get(parent).remove(Integer.valueOf(v));
            outDegree.put(parent, outDegree.get(parent) - 1);
        }

        vertices.remove(v);
        adj.remove(v);
        incoming.remove(v);
        outDegree.remove(v);
    }

    public Set<Integer> getVertices() {
        return new HashSet<>(vertices);
    }

    public Map<Integer, List<Integer>> getAdjacencyList() {
        return adj;
    }
}