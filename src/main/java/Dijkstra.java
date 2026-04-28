import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstra implements PathfindingAlgorithm {

    private Set<Node> visited;
    private PriorityQueue<Node> queue;
    private Map<Node, Integer> distances;
    private Node endNode;

    public Dijkstra() {
        visited = new HashSet<>();
        distances = new HashMap<>();
        queue = new PriorityQueue<>((a, b) -> distances.getOrDefault(a, Integer.MAX_VALUE) - distances.getOrDefault(b, Integer.MAX_VALUE));
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

}