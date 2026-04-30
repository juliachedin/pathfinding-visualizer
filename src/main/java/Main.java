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
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;



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
    private VBox buttonPane;

    private int editType = 0;
    
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
                        switch(editType){
                            case 1:
                                rect.setFill(Color.RED);
                                break;
                            case 2:
                                rect.setFill(Color.BLUE);
                                break;
                            case 3:
                                rect.setFill(Color.GREEN);
                                break;
                        }
                        currentNode.setType(editType);
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
    
    public void createButtons(){
        Button wallButton = new Button("Wall");
        Button startButton = new Button("Start");
        Button endButton = new Button("End");

        wallButton.setOnAction(event -> {
            editType = 1;
        });
        startButton.setOnAction(event -> {
            editType = 2;
        });
        endButton.setOnAction(event -> {
            editType = 3;
        });

        buttonPane.getChildren().addAll(wallButton, startButton, endButton); 
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setPadding(new Insets(10));

        
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
        buttonPane = new VBox(10);

        createVisualGrid();
        createButtons();

        layout.setCenter(gridPane);
        layout.setLeft(buttonPane);
        Scene scene = new Scene(layout, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}