package ultima;
//I needed a lot of imports to get what I needed done.
import java.io.BufferedWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static javafx.geometry.Pos.BASELINE_RIGHT;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 *
 * Made By Connor Berry
 */
public class Ultima extends Application{
    //This stuff is put here so I can use it with more of my code
    //Usually I'd put it at the end.
    public static Scanner value;
    private final TableView<resource> table=new TableView();
    private ObservableList<resource> data;
     
    
    public static void main(String[] args) {
        launch(args);
    }

    public Ultima() {
    //Code for the first resource to start with.
       int[] intel = new int[4];
       intel[0] = 20;
       intel[1] = 100;
       intel[2] = 30;
       intel[3] = 50;
       //Calculates everything inside resource instead of this class.
        this.data = FXCollections.observableArrayList(
                new resource("Starter",intel));
    }
     @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(900);
        stage.setHeight(550);
 //This is the main stage, and it's buttons.
        final FileChooser fileC = new FileChooser();
 fileC.getExtensionFilters().addAll(
         new ExtensionFilter("Text Files", "*.txt"));
 
         final Button new_order = new Button("New Order");
         final Button loader = new Button("Load Order");
        final Button button = new Button ("Save Order");
        final Button restart = new Button("Clear Order");
        final Button clear = new Button("Delete");
        
        new_order.setOnAction(new EventHandler<ActionEvent>() {
//First pop up for placing a new order.
            @Override
            public void handle(final ActionEvent e) {
                
                Stage popupwindow=new Stage();
                
                VBox layout= new VBox(10);
                Scene scene1= new Scene(layout, 300, 500);
                popupwindow.setScene(scene1);
            
                        VBox lain = new VBox(5);
                        Scene scene2 = new Scene(lain, 200, 100);
                        Label label = new Label("Remember that you need a name, \n and 4 whole numbers for the items!");
                        
                        Button confirm2 = new Button("I Got it!");
                
                GridPane grid = new GridPane();
                grid.setPadding(new Insets(10, 10, 10, 10));
                grid.setVgap(5);
                grid.setHgap(5);
                
                popupwindow.initModality(Modality.APPLICATION_MODAL);
                popupwindow.setTitle("New Order");
                
                TextField naming = new TextField("Order " + (data.size()+1));
                naming.setPromptText("Order Title");
                naming.setPrefColumnCount(10);
                TextField textField1 = new TextField();
                textField1.setPromptText("Food");
                textField1.setPrefColumnCount(10);
                TextField textField2 = new TextField();
                textField2.setPromptText("Candy");
                textField2.setPrefColumnCount(10);
                TextField textField3 = new TextField();
                textField3.setPromptText("Cleaning Supplies");
                textField3.setPrefColumnCount(10);
                TextField textField4 = new TextField();
                textField4.setPromptText("Medicine");
                textField4.setPrefColumnCount(10);
                
                Button button1= new Button("Confirm?");
                button1.setOnAction(new EventHandler<ActionEvent>() {
                    
                    @Override
                    public void handle(ActionEvent event) {
                        //The catch for if you try to use letters/punctuation in place of an order amount.
                        try{
                            int[]emmy = new int[4];
                            emmy[0] = Integer.valueOf(textField1.getText());
                            emmy[1] = Integer.valueOf(textField2.getText());
                            emmy[2] = Integer.valueOf(textField3.getText());
                            emmy[3] = Integer.valueOf(textField4.getText());
                            
                        data.add(new resource(naming.getText(),emmy));
                        }catch(Exception e){
                        lain.getChildren().addAll(label, confirm2);
                        
                        popupwindow.setScene(scene2);
                        confirm2.setOnAction(new EventHandler<ActionEvent>(){
                        @Override
                            public void handle(ActionEvent event) {
                                popupwindow.setScene(scene1);
                            }
                        });
                        }
                    }
                });
                layout.getChildren().addAll(naming, textField1, textField2, textField3, textField4, button1);
                layout.setAlignment(Pos.CENTER);
                
                popupwindow.showAndWait();
            }
        });
        
        loader.setOnAction((final ActionEvent e)->{
        try {
            //Loads another file if you ahve one.
            table.getItems().clear();
            
            File file = fileC.showOpenDialog(stage);

            FileReader fr = new FileReader(file);
            LineNumberReader lnr = new LineNumberReader(fr);
            int linenumber = 0;
                value = new Scanner(file);
                while (lnr.readLine() != null){
                linenumber++;
                System.out.println(linenumber);
                }
                lnr.close();
                
                for (int i = 0; i < (linenumber/5); i++) {
                    System.out.println(i);
                        String name = value.nextLine();
                        System.out.println(name);
                        int[] dos = new int[4];
                        for(int j =0; j<4; j++) {
                            dos[j] = Integer.valueOf(value.nextLine());
                        System.out.println(dos[j]);
                        }
                        data.add(new resource(name,dos));
                        
                }
                table.setItems(data);
            
        }
    catch(Exception ex) {
        System.out.println("WARNING! FILE IN CORRECT FORMAT COULD NOT BE FOUND!");
                }
        });
        
        button.setOnAction((final ActionEvent e)->{
            File file = fileC.showSaveDialog(stage);
            //Saves a file, you can edit it in notepad if you return in the same format.
                try {
                    BufferedWriter out = new BufferedWriter(new FileWriter(file));
                    for(int i=0; i<data.size();i++){
                        resource emmy = data.get(i);
                        out.write(emmy.getOrdering());
                        int[] spec = emmy.getCount();
                        for(int j=0;j<4;j++){
                    out.write("\n");
                    out.write(String.valueOf(spec[j]));
                    }
                    out.write("\n");
                    
                    }
               out.close();
                } catch (IOException ex) {
                    Logger.getLogger(Ultima.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch(NullPointerException et){
                }
        
        });
        restart.setOnAction((final ActionEvent e)->{
            //Restarts the whole table, so clear I don't think a warning is necessary.
            table.getItems().clear();
        });
        clear.setOnAction((final ActionEvent e)->{
            //Clears one individual row from the table.
            table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
        });
        
        table.setEditable(true);
        
        TableColumn order = new TableColumn("Orders");
        order.setCellValueFactory(
        new PropertyValueFactory<>("ordering")) ;
        TableColumn invoice = new TableColumn("Recieved");
        invoice.setCellValueFactory(
        new PropertyValueFactory<>("price"));
        TableColumn supply = new TableColumn("Cost");
        supply.setCellValueFactory(
        new PropertyValueFactory<>("actual"));
        
        table.getColumns().addAll(order, invoice, supply);
        table.setItems(data);
        
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
    @Override
    //This code is a popup that allows you to see the individual item stocks inside of an order.
    public void handle(MouseEvent mouseEvent) {
        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
            if(mouseEvent.getClickCount() == 2){
                resource bulk=table.getSelectionModel().getSelectedItem();
                Stage popupwindow=new Stage();
                
                VBox layout= new VBox(10);
                Scene scene1= new Scene(layout, 300, 500);
                popupwindow.setScene(scene1);
            
                        VBox lain = new VBox(5);
                        Scene scene2 = new Scene(lain, 200, 100);
                        
                GridPane grid = new GridPane();
                grid.setPadding(new Insets(10, 10, 10, 10));
                grid.setVgap(5);
                grid.setHgap(5);
                
                popupwindow.initModality(Modality.APPLICATION_MODAL);
                popupwindow.setTitle("");
                
                Label textField1 = new Label("Food count is: " + bulk.getCount()[0]+" and it's cost is "+(bulk.getCount()[0]*1.5));
                Label textField2 = new Label("Candy count is: " + bulk.getCount()[1]+" and it's cost is "+(bulk.getCount()[1]*.40));
                Label textField3 = new Label("Cleaning supply count is: " + bulk.getCount()[2]+" and it's cost is "+(bulk.getCount()[2]*1.00));
                Label textField4 = new Label("Medicine count is: " + bulk.getCount()[3]+" and it's cost is "+(bulk.getCount()[3]*.5));
                Label textField5 = new Label("Your profit is: " + bulk.getActual());
                Button button1= new Button("Confirm?");
                button1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        popupwindow.close();
                    }
                    });
                layout.getChildren().addAll( textField1, textField2, textField3, textField4, textField5, button1);
                layout.setAlignment(Pos.CENTER);
                
                popupwindow.showAndWait();
            }
        }
    }
});
        
        //This is the settings for the main stage that I have at the top.
        order.setMinWidth(250);
        invoice.setMinWidth(250);
        supply.setMinWidth(250);
        HBox hbox = new HBox();
        hbox.setAlignment(BASELINE_RIGHT);
        hbox.setSpacing(20);
        hbox.getChildren().addAll(new_order, loader,button, restart, clear);
        hbox.setPadding(new Insets(20,10,10,20));
        
        final VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(15, 0, 0, 15));
        vbox.getChildren().addAll(hbox, table);
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        
        stage.setTitle("Ultima");
        
        stage.setScene(scene);
        stage.show();
        }
    
}

