import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;



//Must extend application to use JavaFX functionality
public class Main extends Application{

    private int width = 800;
    private int height = 700;
    private int squareSize = 20;
    private int numCellsX = 20;
    private int numCellsY = 20;
   
    private Rectangle [][] gridUI;
    private Grid grid;
    private Pane gridPane;
    
    public void createVisualGrid(){
        gridUI = new Rectangle[numCellsX][numCellsY];

        for (int i = 0; i < numCellsY; i++){
            for (int j = 0; j < numCellsX; j++){
                //create a new rectangle object for each sqare/cell
                Rectangle rect = new Rectangle(squareSize, squareSize);
                int xVal = j;
                int yVal = i;
                //determines positioning for each square
                rect.setX(j * squareSize);
                rect.setY(i * squareSize);
                
                //sets default color to white
                rect.setFill(Color.WHITE);
                rect.setStroke(Color.BLACK);
                //for each rectangle, checks if mouse is clicked and removes/adds a wall
                rect.setOnMouseClicked(mouseEvent ->{
                    Node currentNode = grid.getNode(yVal, xVal);
                    if (currentNode.getType() == 0){
                        rect.setFill(Color.RED);
                        currentNode.setType(1);
                    } else {
                        rect.setFill(Color.WHITE);
                        currentNode.setType(0);
                    }
                    
                });
                //remember each rect using a 2D array
                gridUI[i][j] = rect;
                //add rectangle to the Pane
                gridPane.getChildren().add(rect);
            }
        }
    }
    public static void main(String[] args) {
        launch(args); //set up in the application 
    }

    //This start function is where UI code is written
    @Override 
    public void start(Stage primaryStage) throws Exception{
        //Terminology: the window is the stage and the content inside is the scene
        primaryStage.setTitle("Pathfinding Visualizer");

        BorderPane layout = new BorderPane();

        gridPane = new Pane();
        grid = new Grid(numCellsX, numCellsY);

        createVisualGrid();

        layout.setCenter(gridPane);
        Scene scene = new Scene(layout, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}