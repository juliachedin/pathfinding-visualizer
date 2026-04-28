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

    @Test
    public void numEdgesIsZeroForEmptyNode(){
        Node node = new Node(0, 0, 0);
        assertEquals(0, node.numEdges());
    }

    @Test
    public void numEdgesIncreasesByOneForAddedNeighbour(){
        Node node = new Node(0, 0, 0);
        int numNeighbours = 5;
        for (int i = 0; i < numNeighbours; i++){
            Node neighbour = new Node(0, 0, 0);
            node.addNeighbour(neighbour);
        }
        assertEquals(numNeighbours, node.numEdges());
    }

    @Test
    public void numEdgesDecreasesByOneForRemovedNeigbours(){
        Node node = new Node(0, 0, 0);
        int numNeighbours = 5;
        for (int i = 0; i < numNeighbours; i++){
            Node neighbour = new Node(0, 0, 0);
            node.addNeighbour(neighbour);
            node.removeNeighbour(neighbour);
        }
        assertEquals(0, node.numEdges());
    }

    @Test
    public void removeExistingNeighbourIsCorrect(){
        Node node = new Node(0, 0, 0);
        Node neighbour = new Node(1, 0, 0);
        node.addNeighbour(neighbour);
        node.removeNeighbour(neighbour);
        assertEquals(0, node.getNeighbours().size());
        assertTrue(!node.getNeighbours().contains(neighbour));
    }

    @Test
    public void removeUnexistingNeighbourHasNoEffect(){
        Node node = new Node(0, 0, 0);
        Node neighbour = new Node(1, 0, 0);
        Node otherNode = new Node(2, 0, 0);

        node.addNeighbour(neighbour);
        node.removeNeighbour(otherNode);

        assertEquals(1, node.getNeighbours().size());
    }

    @Test
    public void getXIsCorrect(){
        for (int i = 0; i < 5; i++){
            Node node = new Node(0, i, 0);
            assertEquals(i, node.getX());
        }
    }

    @Test
    public void getYIsCorrect(){
        for (int i = 0; i < 5; i++){
            Node node = new Node(0, 0, i);
            assertEquals(i, node.getY());
        }
    }
}