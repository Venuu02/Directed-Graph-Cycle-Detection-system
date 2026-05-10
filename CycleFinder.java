import java.util.*;

public class CycleFinder {

    public static List<Integer> findCycle(Graph graph) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> stack = new HashSet<>();
        Map<Integer, Integer> parent = new HashMap<>();

        for (int node : graph.getVertices()) {
            if (!visited.contains(node)) {
                List<Integer> cycle = dfs(node, graph, visited, stack, parent);
                if (cycle != null) {
                    return cycle;
                }
            }
        }
        return null;
    }

    private static List<Integer> dfs(int node, Graph graph,
                                    Set<Integer> visited,
                                    Set<Integer> stack,
                                    Map<Integer, Integer> parent) {

        visited.add(node);
        stack.add(node);

        for (int neighbor : graph.getAdjacencyList().getOrDefault(node, new ArrayList<>())) {

            if (!visited.contains(neighbor)) {
                parent.put(neighbor, node);
                List<Integer> result = dfs(neighbor, graph, visited, stack, parent);
                if (result != null) return result;
            }

            else if (stack.contains(neighbor)) {
                
                List<Integer> cycle = new ArrayList<>();
                int current = node;

                cycle.add(neighbor);

                while (current != neighbor) {
                    cycle.add(current);
                    current = parent.get(current);
                }

                cycle.add(neighbor);
                Collections.reverse(cycle);

                return cycle;
            }
        }

        stack.remove(node);
        return null;
    }
}