import java.util.*;

public class Dijkstra {
    public static void main(String[] args) {
        System.out.println("Hello");
        dijkstraShortestPath();
    }

    public static void dijkstraShortestPath() {
        char[] nodes = {'A', 'B', 'C', 'D', 'E', 'F'};

        Map<String, Integer> edges = new HashMap<>();
        // graf
        edges.put("AB", 4);
        edges.put("AC", 3);
        edges.put("BC", 1);
        edges.put("BD", 3);
        edges.put("CD", 2);
        edges.put("CE", 4);
        edges.put("DE", 1);
        edges.put("DF", 3);
        edges.put("EF", 1);

        int[][] graph = graphMap(nodes, edges);

        // Bentuk arrays penampung jarak, cek visited, dan previous node
        int[] distances = new int[nodes.length];
        boolean[] visited = new boolean[nodes.length];
        int[] previous = new int[nodes.length];

        // inisialisasi arrays
        for (int i = 0; i < nodes.length; i++) {
            distances[i] = Integer.MAX_VALUE;
            visited[i] = false;
            previous[i] = -1;
        }

        // Mulai dari node 0 / A
        distances[0] = 0;

        for (int i = 0; i < nodes.length - 1; i++) {
            // Cari node dengan nilai terkecil
            int minVertex = findMinVertex(distances, visited);
            visited[minVertex] = true;

            // Update distances
            for (int j = 0; j < nodes.length; j++) {
                if (!visited[j] && graph[minVertex][j] != 0 && distances[minVertex] != Integer.MAX_VALUE) {
                    int newDistance = distances[minVertex] + graph[minVertex][j];
                    if (newDistance < distances[j]) {
                        distances[j] = newDistance;
                        previous[j] = minVertex;
                    }
                }
            }

            // Update iterasi
            System.out.println("Iteration " + (i + 1) + ":");
            System.out.println("Distances: " + Arrays.toString(distances));
            System.out.println("Visited: " + Arrays.toString(visited));
            System.out.println("Previous: " + Arrays.toString(previous));
            System.out.println();
        }

        // Jarak terpendek dari A ke F
        int shortestDistanceToF = distances['F' - 'A'];

        // Mengurutkan label dari A ke F
        StringBuilder shortestPathLabelToF = new StringBuilder();
        int current = 'F' - 'A';
        while (current != -1) {
            shortestPathLabelToF.insert(0, nodes[current]);
            current = previous[current];
        }

        // Menampilkan hasil
        System.out.println("\nShortest distance from A to F: " + shortestDistanceToF);
        System.out.println("Shortest path from A to F: " + shortestPathLabelToF);
    }


    public static int findMinVertex(int[] distances, boolean[] visited) {
        int minVertex = -1;
        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && (minVertex == -1 || distances[i] < distances[minVertex])) {
                minVertex = i;
            }
        }
        return minVertex;
    }

    public static int[][] graphMap(char[] nodes, Map<String, Integer> edges) {
        int n = nodes.length; //Jumlah nodes
        int[][] graph = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = 0;
            }
        }

        for (Map.Entry<String, Integer> entry : edges.entrySet()) {
            String edge = entry.getKey();
            int weight = entry.getValue();
            char fromNode = edge.charAt(0);
            char toNode = edge.charAt(1);

            int fromIndex = -1;
            int toIndex = -1;
            for (int i = 0; i < n; i++) {
                if (nodes[i] == fromNode) {
                    fromIndex = i;
                }
                if (nodes[i] == toNode) {
                    toIndex = i;
                }
            }

            graph[fromIndex][toIndex] = weight;
            graph[toIndex][fromIndex] = weight;
        }

        return graph;
    }
}
