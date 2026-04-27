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
}