import java.util.Set;
import java.util.HashSet;

public class Node{
    private Set<Node> neighbours = new HashSet<>();
    private int type; 
    private int numEdges;

    /**
     * displays all connecting nodes
     * @return connecting nodes
     */
    public Set<Node> getNeighbours() {
        return neighbours;
    }
    /**
     * indicates if the node represents a wall, path, start or end point
     * @return integer of state
     */
    public int getType(){
        return type;
    }

    public int numEdges(){
        return numEdges;
    }
    /**
     * add a new connection
     * @param node connecting node
     */
    public void addNeighbour(Node node){
        boolean newToSet = neighbours.add(node);
        if(newToSet){
            numEdges += 1;
        }
    }

    /**
     * Remove a connection
     * @param node connecting node
     */
    public void removeNeighbour(Node node){
        boolean inSet =neighbours.remove(node);
        if(inSet){
            numEdges -= 1;
        }
    }


}