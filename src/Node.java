import java.util.ArrayList;
import java.util.List;

public class Node{
    private List<Node> neighbours = new ArrayList<>();
    private int type; 


    /**
     * displays all connecting nodes
     * @return connecting nodes
     */
    public List<Node> getNeighbours() {
        return neighbours;
    }
    /**
     * indicates if the node represents a wall, path, start or end point
     * @return integer of state
     */
    public int getType(){
        return type;
    }

}