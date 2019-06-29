import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class GUI extends Application {

    private GridPane gridPane = new GridPane();
    private GridPane gridPane2 = new GridPane();

    private int gridPane2Col = 3;
    private int gridPane2Row = 3;

    private Pane[][] panes = new Pane[gridPane2Col][gridPane2Row];

    private HBox footerHBox = new HBox();
    private HBox footer2HBox = new HBox();
    private VBox vbox = new VBox();

    private TextArea pathTextArea = new TextArea("C:\\Users\\djankooo\\Desktop\\PWJJ\\lab2\\test");

    private Button findClassesButton = new Button("Find class");
    private Button loadClassesButton = new Button("Load class");
    private Button unloadClassesButton = new Button("Unload class");
    private Button createObjectButton = new Button("Create object");
    private Button destroyObjectButton = new Button("Destroy object");

    private ListView classListView = new ListView();

    private ArrayList <Node> nodesToRemove = new ArrayList<>();


    private MyClassLoader mcl;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Class loader");

        primaryStage.setScene(new Scene(gridPane, 1000, 500));

        gridPane.setPadding(new Insets(20, 20, 20, 20));

        gridPane.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.5));
        gridPane.prefHeightProperty().bind(primaryStage.heightProperty().multiply(1.0));

        vbox.prefWidthProperty().bind(gridPane.widthProperty().multiply(0.50));
        vbox.prefHeightProperty().bind(gridPane.heightProperty().multiply(1.0));

        gridPane2.prefWidthProperty().bind(primaryStage.widthProperty().multiply(0.5));
        gridPane2.prefHeightProperty().bind(primaryStage.heightProperty().multiply(1.0));

        pathTextArea.prefWidthProperty().bind(vbox.widthProperty().multiply(1.0));
        pathTextArea.prefHeightProperty().bind(vbox.heightProperty().multiply(0.1));

        findClassesButton.prefWidthProperty().bind(vbox.widthProperty().multiply(0.2));
        findClassesButton.prefHeightProperty().bind(vbox.heightProperty().multiply(0.1));

        classListView.prefWidthProperty().bind(vbox.widthProperty().multiply(1.0));
        classListView.prefHeightProperty().bind(vbox.heightProperty().multiply(0.8));

        loadClassesButton.prefWidthProperty().bind(vbox.widthProperty().multiply(0.5));
        loadClassesButton.prefHeightProperty().bind(vbox.heightProperty().multiply(0.1));

        unloadClassesButton.prefWidthProperty().bind(vbox.widthProperty().multiply(0.5));
        unloadClassesButton.prefHeightProperty().bind(vbox.heightProperty().multiply(0.1));

        createObjectButton.prefWidthProperty().bind(vbox.widthProperty().multiply(0.5));
        createObjectButton.prefHeightProperty().bind(vbox.heightProperty().multiply(0.1));

        destroyObjectButton.prefWidthProperty().bind(vbox.widthProperty().multiply(0.5));
        destroyObjectButton.prefHeightProperty().bind(vbox.heightProperty().multiply(0.1));

        footerHBox.getChildren().addAll(loadClassesButton, unloadClassesButton);
        footer2HBox.getChildren().addAll(createObjectButton, destroyObjectButton);

        vbox.getChildren().addAll(pathTextArea, classListView, footerHBox, footer2HBox);

        gridPane.add(vbox, 0, 0);
        gridPane.add(gridPane2, 1, 0);

        gridPane.setGridLinesVisible(true);
        gridPane2.setGridLinesVisible(true);

        setNumberOfColRow(gridPane2Col, gridPane2Row);

        primaryStage.show();

        loadClassesButton.setOnAction(event -> {
            mcl = new MyClassLoader(pathTextArea.getText());
            mcl.searchInDirectory();
            classListView.getItems().addAll(mcl.getClassNamesInDirectory());
            gridPane2.getChildren().removeAll();
        });

        gridPane2.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)){
                Node clickedNode = event.getPickResult().getIntersectedNode();
                if (clickedNode != gridPane || clickedNode != vbox || clickedNode != gridPane2) {
                    nodesToRemove.add(clickedNode);
                    System.out.println(clickedNode.getClass());
                }
            }
        });

        unloadClassesButton.setOnAction( event -> {

            mcl = null;
            System.gc();
        });

        createObjectButton.setOnAction(event -> {
            String s = classListView.getSelectionModel().getSelectedItems().toString();
            Class newClass = null;

            try {
                newClass = mcl.loadClass(String.valueOf(s.substring(1, s.length() - 1)));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            for (int j = 0; j < gridPane2Row; j++) {
                for (int i = 0; i < gridPane2Col; i++) {
                    //if(getNodeByRowColumnIndex(i,j,gridPane2) != null) {

                    if (panes[i][j] == null) {
                        try {
                            Pane p = new Pane();
                            p = (Pane) newClass.newInstance();
                            gridPane2.add(p, i, j);
                            panes[i][j] = p;
                            return;
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        destroyObjectButton.setOnAction(event -> {
            destroyChoosenObjects();
        });


    }

    private void destroyChoosenObjects() {
        removeDuplicates(nodesToRemove);
        for (Node n : nodesToRemove) {

            for (int row = 0; row < gridPane2Row; row++) {
                for (int col = 0; col < gridPane2Row; col++) {
                    if (panes[col][row] == n) {
                        panes[col][row] = null;
                        gridPane2.getChildren().remove(n);
                        gridPane2.add(new Pane(), col, row);
                    }
                }
            }
        }
        nodesToRemove.clear();
    }

    public void setNumberOfColRow(int col, int row) {
        for (int i = 0; i < col; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / col);
            gridPane2.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < row; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / col);
            gridPane2.getRowConstraints().add(rowConst);
        }
    }
    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {
        Set<T> set = new LinkedHashSet<>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
    }


}
