import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Astar implements PathfindingAlgorithm {

    private Set<Node> visited;
    private PriorityQueue<Node> queue;
    private Map<Node, Integer> gCosts;
    private Node endNode;

    public Astar() {
        visited = new HashSet<>();
        gCosts = new HashMap<>();
        queue = new PriorityQueue<>(this::compareNodes);
    }

    /**
     * Compares two nodes based on their fCost (gCost + hCost).
     */
    private int compareNodes(Node a, Node b) {
        int fCostA = gCosts.getOrDefault(a, Integer.MAX_VALUE) + heuristic(a);
        int fCostB = gCosts.getOrDefault(b, Integer.MAX_VALUE) + heuristic(b);
        return fCostA - fCostB;
    }

    /**
     * Estimates distance from node to end using Manhattan distance.
     */
    private int heuristic(Node node) {
        return Math.abs(node.getX() - endNode.getX()) + Math.abs(node.getY() - endNode.getY());
    }

    /**
     * Initializes the algorithm with start and end node.
     * Sets gCost of start node to 0 and adds it to the queue.
     */
    public void initialize(Node start, Node end) {
        endNode = end;
        gCosts.put(start, 0);
        queue.add(start);
    }

    /**
     * Represents one step of the A* algorithm.
     * Checks neighbours of current node and updates their gCosts.
     */
    public void step() {
        Node current = queue.remove();
        visited.add(current);

        for (Node neighbour : current.getNeighbours()) {
            if (!visited.contains(neighbour)) {
                int newGCost = gCosts.getOrDefault(current, Integer.MAX_VALUE) + 1;
                if (newGCost < gCosts.getOrDefault(neighbour, Integer.MAX_VALUE)) {
                    gCosts.put(neighbour, newGCost);
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
        return queue.isEmpty();
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