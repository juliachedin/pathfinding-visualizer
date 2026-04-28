import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class AStar implements PathfindingAlgorithm {

    private Set<Node> visited;
    private PriorityQueue<Node> queue;
    private Map<Node, Integer> gCosts;
    private Node endNode;

    public AStar() {
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
}