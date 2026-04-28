import java.util.List;

public interface PathfindingAlgorithm {
    /**
     * Initializes the algorithm with a start point and end goal
     * @param start the start node
     * @param end the end node to be found
     */
    void initialize(Node start, Node end);

    /** 
     * Moves on search to next node
     */
    void step();

    /**
     * Checks if algorithm has reached it's goal yet
     * @return true if end is reached
     */
    boolean isFinished();

    /**
     * Checks if it is even possible to find a path
     * @return false if impossible
     */
    boolean hasPath();

    /**
     * For UI design purposes, all visited nodes are recorded
     * @return a list of the visited nodes
     */
    List<Node> visitedNodes();

    /**
     * The final path from start to end
     * @return all nodes of the path
     */
    List<Node> foundPath();
}
