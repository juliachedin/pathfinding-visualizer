import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class BFS implements PathfindingAlgorithm{
    
    private Set<Node> visited;
    private Queue<Node> queue;
    private Node endNode;

    public BFS(){
        visited = new HashSet<>();
        queue = new LinkedList<Node>();
    }

    /**
     * Adds start position and remembers end goal
     */
    public void initialize(Node start, Node end){
        endNode = end;
        queue.add(start);
    }

    /**
     * Represents one step of the algorithm
     * In this case: checks neighbours for a current node
     * Modifies visited and adds/removes nodes from the queue
     */
    public void step(){
        Node nodeChecked = queue.remove();
        for (Node neighbour : nodeChecked.getNeighbours()){
            if(!visited.contains(neighbour)){
                visited.add(neighbour);
                queue.add(neighbour);
            }
        }
    }

    /**
     * Checks if the algorithm is still searching or not
     */
    public boolean isFinished(){
        if (queue.isEmpty()){
            return true;
        }
        return false;
    }

    public boolean hasPath(){
        return true;
    }    

    /**
     * @return a list of visited nodes so far
     */
    public List<Node> visitedNodes(){
        ArrayList<Node> visitedArray = new ArrayList<>(visited);
        return visitedArray;
    }

    public List<Node> foundPath(){

    }
}