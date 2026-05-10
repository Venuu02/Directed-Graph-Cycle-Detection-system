import java.io.*;

public class GraphParser {

    public static Graph parseFromFile(String filename) {
        Graph graph = new Graph();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean firstLineChecked = false;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s+");

                if (!firstLineChecked) {
                    firstLineChecked = true;

                    if (parts.length == 1) {
                        continue;
                    }
                }

                
                if (parts.length == 2) {
                    int from = Integer.parseInt(parts[0]);
                    int to = Integer.parseInt(parts[1]);
                    graph.addEdge(from, to);
                } else {
                    System.out.println("Invalid line: " + line);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in file.");
        }

        return graph;
    }
}