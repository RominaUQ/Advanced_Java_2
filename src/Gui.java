import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.FileInputStream;

public class Gui extends Application {
		  
	@Override // Override the start method from the superclass
	  public void start(Stage primaryStage) throws exception {
	  GridPane root= new GridPane();
	  
	  Button display= new Button ("Display");
	  Button Delete= new Button ("Add");
	  Button Add= new Button ("Delete");
	  

	  root.add(display, 1, 1,1, 1);
	  root.add(Delete, 	1, 5,1, 1);
	  root.add(Add, 	1, 10,1, 5);
	 // root.addRow(3, display, Add, Delete );
	  root.setAlignment(Pos.TOP_LEFT);
	 
	  Label textFieldLabel = new Label("search the profile");
      TextField textField = new TextField();
     // root.add(10,textFieldLabel, textField);
	  
	  //root.setVgap(20);
	 // root.addRow(1, display);
	 // root.addRow(2, Add);
	  //root.addRow(3, Delete);
	  
	  /*root.getChildren().add(new Button("Display"));
	  root.getChildren().add(new Button("Add a Profile"));
	  root.getChildren().add(new Button("Delete a Profile"));
	  root.getChildren().add(new Button("Check Connection"));
	  root.getChildren().add(new Button("Create a New Profile"));
	  root.getChildren().add(new Button("Exit"));*/
		
	 root.setPadding(new Insets(30));
	 Scene scene= new Scene(root, 500, 400);
	 primaryStage.setTitle("Menu");
	 primaryStage.setScene(scene);
	 primaryStage.show();
	}
		public static void main(String[] args) {
		       Application.launch(args);
		  }	
}