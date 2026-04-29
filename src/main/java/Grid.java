public class Grid{

    Node [][] nodes;
    int xSize;
    int ySize;

    public Grid(int width, int height){
        xSize = width;
        ySize = height;

        for (int i = 0; i < width; i++){
            for (int j = 0; j < height; j++){
                //default value 0 for type, indicating "free"
                Node node = new Node(0, i, j);
                nodes[i][j] = node;
            }
        }
    }


}