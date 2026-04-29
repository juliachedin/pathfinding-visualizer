import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
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
    

    public static void main(String[] args) {
        launch(args); //set up in the application 
    }

    //This start function is where UI code is written
    @Override 
    public void start(Stage primaryStage) throws Exception{
        //Terminology: the window is the stage and the content inside is the scene
        primaryStage.setTitle("Pathfinding Visualizer");

        gridPane = new Pane();
        grid = new Grid(numCellsX, numCellsY);

        createVisualGrid();

        Scene scene = new Scene(gridPane, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void createVisualGrid(){
        gridUI = new Rectangle[numCellsX][numCellsY];

        for (int i = 0; i < numCellsX; i++){
            for (int j = 0; j < numCellsY; j++){
                Rectangle rect = new Rectangle(squareSize, squareSize);
                rect.setX(i * squareSize);
                rect.setY(j * squareSize);
                
                rect.setFill(Color.WHITE);
                rect.setStroke(Color.BLACK);

                gridUI[i][j] = rect;

                gridPane.getChildren().add(rect);
            }
        }
    }

}