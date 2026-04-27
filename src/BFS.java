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

    public void initialize(Node start, Node end){
        endNode = end;
        queue.add(start);
    }

    public void step(){

    }

    public boolean isFinished(){
        return true;
    }

    public boolean hasPath(){
        return true;
    }    

    public List<Node> visitedNodes(){
        ArrayList<Node> visitedArray = new ArrayList<>(visited);
        return visitedArray;
    }

    public List<Node> foundPath(){

    }
}