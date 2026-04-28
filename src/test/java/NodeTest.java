import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;

public class NodeTest{


    @BeforeAll
    public static void setUp(){ }

    @Test
    public void getNeighboursWhenEmpty(){
        Node node = new Node(0, 0, 0);
        Set<Node> neighbours = node.getNeighbours();
        assertTrue(neighbours.isEmpty());
    }   

    @Test 
    public void addNeighbourToEmptyNode(){
        Node node = new Node(0, 0, 0);
        Node neighbour = new Node(0, 0, 0);
        node.addNeighbour(neighbour);
        assertEquals(1, node.getNeighbours().size());
        assertTrue(node.getNeighbours().contains(neighbour));
    }

    @Test
    public void addSeveralNeighbours(){
        Node node = new Node(0, 0, 0);
        int numNeighbours = 10;
        for (int i = 0; i < numNeighbours; i++){
            Node neighbour = new Node(i, i, i);
            node.addNeighbour(neighbour);
        }
        assertEquals(numNeighbours, node.getNeighbours().size());
    }

    @Test
    public void addDuplicatesOnlyCountsOnce(){
        Node node = new Node(0, 0, 0);
        Node neighbour = new Node(0, 0, 0);
        node.addNeighbour(neighbour);
        node.addNeighbour(neighbour);
        assertEquals(1, node.getNeighbours().size());
    }

    @Test
    public void getTypeIsCorrect(){
        for (int i = 0; i < 5; i++){
            Node node = new Node(i, 0, 0);
            assertEquals(i, node.getType());
        }
    }
}