import javafx.scene.paint.Color;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ToggleGroup;

/**
 * Main class for running the visualiser program
 * This main class includes javafx operations for UI design and events
 * 
 */

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

    private Node currentStartPoint = null;
    private Node currentEndPoint = null;

    private String selectedAlgorithm = null;

    //to check state of visualizer and if it is running a search
    private boolean isRunning = false;

    private ToggleButton runButton;
    
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
                    int typeOfRect = currentNode.getType();
                    if (typeOfRect == 0){
                        switch(editType){
                            case 1:
                                rect.setFill(Color.RED);
                                break;
                            case 2:
                                rect.setFill(Color.BLUE);
                                if (currentStartPoint != null){
                                    currentStartPoint.setType(0);
                                    gridUI[currentStartPoint.getY()][currentStartPoint.getX()].setFill(Color.WHITE);
                                }
                                currentStartPoint = grid.getNode(yVal, xVal);

                                break;
                            case 3:
                                rect.setFill(Color.GREEN);
                                if (currentEndPoint != null){
                                    currentEndPoint.setType(0);
                                    gridUI[currentEndPoint.getY()][currentEndPoint.getX()].setFill(Color.WHITE);
                                }

                                currentEndPoint = grid.getNode(yVal, xVal);
                                break;
                        }
                        currentNode.setType(editType);
                    } else {
                        if (typeOfRect == 2){
                            currentStartPoint = null;
                        } else if (typeOfRect == 3){
                            currentEndPoint = null;
                        }
                        rect.setFill(Color.WHITE);
                        currentNode.setType(0);
                    }
                    updateRunButton();
                });
                //remember each rect using a 2D array
                gridUI[i][j] = rect;
                //add rectangle to the Pane
                gridPane.getChildren().add(rect);
            }
        }
    }
    
    public void createButtons(){

        ToggleButton wallButton = new ToggleButton("Wall");
        ToggleButton startButton = new ToggleButton("Start");
        ToggleButton endButton = new ToggleButton("End");
        runButton = new ToggleButton("Run");
        ChoiceBox<String> algorithmButton = new ChoiceBox<String>();

        //use observable list to add items to the choice box
        ObservableList<String> algorithms = algorithmButton.getItems();
        algorithms.addAll("BFS", "Dijkstra", "A*");

        //create toggle group and add all buttons for this. Creates visual effect of one being chosen at a time
        ToggleGroup buttonGroup = new ToggleGroup();
        wallButton.setToggleGroup(buttonGroup);
        startButton.setToggleGroup(buttonGroup);
        endButton.setToggleGroup(buttonGroup);
        runButton.setToggleGroup(buttonGroup);

        //add buttons to CSS style class
        wallButton.getStyleClass().add("button");
        startButton.getStyleClass().add("button");
        endButton.getStyleClass().add("button");
        runButton.getStyleClass().add("button");

        wallButton.setOnAction(event -> {
            editType = 1;
        });
        startButton.setOnAction(event -> {
            editType = 2;
        });
        endButton.setOnAction(event -> {
            editType = 3;
        });
        runButton.setOnAction(event -> {
            runAlgorithm();
            //change state to active
            isRunning = true;
            updateRunButton();
        });

        updateRunButton();
        
        algorithmButton.setOnAction(event -> {
            selectedAlgorithm = algorithmButton.getValue();
            updateRunButton();
        });


        buttonPane.getChildren().addAll(wallButton, startButton, endButton, runButton, algorithmButton);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setPadding(new Insets(10));

        
    }   

    /**
     * Updates if the runbutton is still disabled or not based on conditions
     * @param runButton button to be disabled
     */
    public void updateRunButton(){
        //to be allowed to run start/end points must exist, an algorithm has to be chosen and state of visualizer not running
        if (currentStartPoint != null && currentEndPoint != null && selectedAlgorithm != null && !isRunning) {
            runButton.setDisable(false);
        } else {
            runButton.setDisable(true);
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
        buttonPane = new VBox(10);

        createVisualGrid();
        createButtons();

        // allows dragging to add walls
        gridPane.setOnMouseDragged(mouseEvent -> {
            if (editType == 1) {
                int col = (int)(mouseEvent.getX() / squareSize);
                int row = (int)(mouseEvent.getY() / squareSize);
                if (row >= 0 && row < numCellsY && col >= 0 && col < numCellsX) {
                    Node currentNode = grid.getNode(row, col);
                    if (currentNode.getType() == 0) {
                        currentNode.setType(1);
                        gridUI[row][col].setFill(Color.RED);
                    }
                }
            }
        });

        layout.setCenter(gridPane);
        layout.setLeft(buttonPane);
        Scene scene = new Scene(layout, width, height);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void runAlgorithm() {
    // Clean grid before running to avoid stale neighbours/parents
    grid.cleanGrid();

    // Re-color walls that were visually reset
    for (int i = 0; i < numCellsY; i++) {
        for (int j = 0; j < numCellsX; j++) {
            Node node = grid.getNode(i, j);
            if (node.getType() == 1) {
                gridUI[i][j].setFill(Color.RED);
            } else {
                gridUI[i][j].setFill(Color.WHITE);
            }
        }
    }

    // Re-apply start and end types since cleanGrid resets them
    currentStartPoint.setType(2);
    currentEndPoint.setType(3);

    // Build the graph - connect all non-wall nodes to their neighbours
    for (int i = 0; i < numCellsY; i++) {
        for (int j = 0; j < numCellsX; j++) {
            Node current = grid.getNode(i, j);
            if (current.getType() != 1) {
                if (i > 0 && grid.getNode(i-1, j).getType() != 1) current.addNeighbour(grid.getNode(i-1, j));
                if (i < numCellsY-1 && grid.getNode(i+1, j).getType() != 1) current.addNeighbour(grid.getNode(i+1, j));
                if (j > 0 && grid.getNode(i, j-1).getType() != 1) current.addNeighbour(grid.getNode(i, j-1));
                if (j < numCellsX-1 && grid.getNode(i, j+1).getType() != 1) current.addNeighbour(grid.getNode(i, j+1));
            }
        }
    }

    // Run algorithm, check which algorithm has been selected
    PathfindingAlgorithm algorithm;
    if (selectedAlgorithm.equals("BFS")){
        algorithm = new BFS();
    } else if (selectedAlgorithm.equals("Dijkstra")){
        algorithm = new Dijkstra();
    } else if (selectedAlgorithm.equals("A*")){
        algorithm = new Astar();
    } else {
        throw new IllegalArgumentException(
            "unknown algorithm: " + selectedAlgorithm
        );
    }

    algorithm.initialize(currentStartPoint, currentEndPoint);

    // Replace while-loop with Timeline animation - runs one step at a time with 30ms delay
    Timeline[] timelineHolder = new Timeline[1];
    timelineHolder[0] = new Timeline(new KeyFrame(Duration.millis(30), event -> {
        if (!algorithm.isFinished()) {
            algorithm.step();
            for (Node node : algorithm.visitedNodes()) {
                if (node != currentStartPoint && node != currentEndPoint) {
                    gridUI[node.getY()][node.getX()].setFill(Color.YELLOW);
                }
            }
        } else {
            timelineHolder[0].stop();
            if (algorithm.hasPath()) {
                for (Node node : algorithm.foundPath()) {
                    if (node != currentStartPoint && node != currentEndPoint) {
                        gridUI[node.getY()][node.getX()].setFill(Color.ORANGE);
                    }
                }
            }
            //change state to not active
            isRunning = false;
            gridUI[currentStartPoint.getY()][currentStartPoint.getX()].setFill(Color.BLUE);
            gridUI[currentEndPoint.getY()][currentEndPoint.getX()].setFill(Color.GREEN);
        }
    }));
    timelineHolder[0].setCycleCount(Timeline.INDEFINITE);
    timelineHolder[0].play();
    }
}