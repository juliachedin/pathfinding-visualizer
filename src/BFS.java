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
                neighbour.setParent(nodeChecked);
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

    /**
     * Checks if the end node has been visited indicating a path
     * Disclaimer: Only accurate when algorithm is done searching
     */
    public boolean hasPath(){
        return visited.contains(endNode);
    }    

    /**
     * @return a list of visited nodes so far
     */
    public List<Node> visitedNodes(){
        ArrayList<Node> visitedArray = new ArrayList<>(visited);
        return visitedArray;
    }
    /**
     * Traces back the path from end to start using parent pointers
     * @return list of nodes from start to end
     */
    public List<Node> foundPath(){
        List<Node> path = new ArrayList<>();
        Node current = endNode;

         while(current != null){
             path.add(0, current);
             current = current.getParent();
        }

        return path;
    }
}