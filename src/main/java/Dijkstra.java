import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * This class implements the Dijkstra search algorithm by using
 * connecting nodes in a graph datastructure
 * 
 * The algorithm finds its way from a starting node to an ending node
 * by searching through the neighbours of different nodes
 */

public class Dijkstra implements PathfindingAlgorithm {

    private Set<Node> visited;
    private PriorityQueue<Node> queue;
    private Map<Node, Integer> distances;
    private Node endNode;

    public Dijkstra() {
        visited = new HashSet<>();
        distances = new HashMap<>();
        queue = new PriorityQueue<>(this::compareNodes);
    }

    /**
     * Compares two nodes based on their distance.
     */
    private int compareNodes(Node a, Node b) {
        return distances.getOrDefault(a, Integer.MAX_VALUE) - distances.getOrDefault(b, Integer.MAX_VALUE);
    }

    /**
     * Initializes the algorithm with start and end node.
     * Sets distance of start node to 0 and adds it to the queue.
     */
    public void initialize(Node start, Node end) {
        endNode = end;
        distances.put(start, 0);
        queue.add(start);
    }

    /**
     * Represents one step of Dijkstra's algorithm.
     * Checks neighbours of current node and updates their distances.
     */
    public void step() {
        Node current = queue.remove();
        visited.add(current);

        for (Node neighbour : current.getNeighbours()) {
            if (!visited.contains(neighbour)) {
                int newDistance = distances.getOrDefault(current, Integer.MAX_VALUE) + 1;
                if (newDistance < distances.getOrDefault(neighbour, Integer.MAX_VALUE)) {
                    distances.put(neighbour, newDistance);
                    neighbour.setParent(current);
                    queue.add(neighbour);
                }
            }
        }
    }

    /**
     * Checks if the algorithm is still searching or not.
     */
    public boolean isFinished() {
        return queue.isEmpty() || hasPath();
    }

    /**
     * Checks if the end node has been visited indicating a path exists.
     */
    public boolean hasPath() {
        return visited.contains(endNode);
    }

    /**
     * Returns a list of all visited nodes so far.
     */
    public List<Node> visitedNodes() {
        return new ArrayList<>(visited);
    }

    /**
     * Traces back the path from end to start using parent pointers.
     * @return list of nodes from start to end
     */
    public List<Node> foundPath() {
        List<Node> path = new ArrayList<>();
        Node current = endNode;

        while (current != null) {
            path.add(0, current);
            current = current.getParent();
        }

        return path;
    }
}