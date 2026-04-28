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

}