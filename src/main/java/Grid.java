public class Grid{

    Node [][] nodes;
    int xSize;
    int ySize;

    public Grid(int width, int height){
        xSize = width;
        ySize = height;
        // creates a grid with the specified width and height
        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                //default value 0 for type, indicating "free"
                Node node = new Node(0, i, j);
                nodes[i][j] = node;
            }
        }
    }
    /**
     * This method cleans all of the nodes by removing all connections and walls
     */
    public void cleanGrid(){
        for (int i = 0; i < xSize; i++){
            for (int j = 0; j < ySize; j++){
                //removes the parent 
                nodes[i][j].setParent(null);
                //returns to default type of being "free"
                nodes[i][j].setType(0);
                //removes all connections
                for(Node neighbour: nodes[i][j].getNeighbours()){
                    nodes[i][j].removeNeighbour(neighbour);
                }
            }
        }
    }
    /**
     * Method for getting a specific node in the grid
     * @param xIndex x-index
     * @param yIndex y-index
     * @return node asked for
     */
    public Node getNode(int xIndex, int yIndex){
        return nodes[xIndex][yIndex];
    }




}