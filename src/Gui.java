import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Gui extends Application {
	private Driver _driver;

	public Gui() {
		_driver = new Driver();
	}

	@Override // Override the start method from the superclass
	public void start(Stage primaryStage) throws exception {
		GridPane root = new GridPane();
		// root.setGridLinesVisible(true);
		Button btnDisplay = new Button("Display");
		Button btnDelete = new Button("Delete");
		Button btnAdd = new Button("Add");

		root.add(btnDisplay, 1, 1, 2, 2);
		root.add(btnDelete, 1, 5, 2, 2);
		root.add(btnAdd, 1, 10, 2, 2);
		root.setAlignment(Pos.TOP_LEFT);

		Label textFieldLabel = new Label("search the profile");
		TextField textField = new TextField();
		Button btnSearch = new Button("Search");
		root.add(textFieldLabel, 7, 1, 2, 2);
		root.add(textField, 9, 1, 4, 2);
		root.add(btnSearch, 13, 1, 2, 2);

		root.setHgap(10);
		root.setVgap(10);

		setAddButtonAction(btnAdd, primaryStage);
		setSearchButtonAction(btnSearch, primaryStage);
		
		root.setPadding(new Insets(30));
		Scene scene = new Scene(root, 800, 600);
		primaryStage.setTitle("Menu");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void setAddButtonAction(Button add, Stage primaryStage) {
		add.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				final Stage dialogStage = new Stage();
				dialogStage.initModality(Modality.APPLICATION_MODAL);
				dialogStage.initOwner(primaryStage);
				dialogStage.setTitle("Create a new profile");

				GridPane addPopupGrid = new GridPane();
				Label firstNameLabel = new Label("First name");
				TextField firstNameText = new TextField();
				Label surNameLabel = new Label("Surname");
				TextField surNameText = new TextField();
				Label ageLabel = new Label("Age");
				TextField ageText = new TextField();
				Label statusLabel = new Label("Age");
				TextField statusText = new TextField();

				addPopupGrid.add(firstNameLabel, 1, 1, 2, 2);
				addPopupGrid.add(firstNameText, 3, 1, 2, 2);
				addPopupGrid.add(surNameLabel, 1, 3, 2, 2);
				addPopupGrid.add(surNameText, 3, 3, 2, 2);
				addPopupGrid.add(ageLabel, 1, 5, 2, 2);
				addPopupGrid.add(ageText, 3, 5, 2, 2);
				addPopupGrid.add(statusLabel, 1, 7, 2, 2);
				addPopupGrid.add(statusText, 3, 7, 2, 2);

				Label momFirstNameLabel = new Label("Mother's First name");
				TextField momFirstNameText = new TextField();
				Label momSurNameLabel = new Label("Mother's Surname");
				TextField momSurNameText = new TextField();

				addPopupGrid.add(momFirstNameLabel, 1, 9, 2, 2);
				addPopupGrid.add(momFirstNameText, 3, 9, 2, 2);
				addPopupGrid.add(momSurNameLabel, 1, 11, 2, 2);
				addPopupGrid.add(momSurNameText, 3, 11, 2, 2);

				Label dadFirstNameLabel = new Label("Father's First name");
				TextField dadFirstNameText = new TextField();
				Label dadSurNameLabel = new Label("Father's Surname");
				TextField dadSurNameText = new TextField();

				addPopupGrid.add(dadFirstNameLabel, 1, 13, 2, 2);
				addPopupGrid.add(dadFirstNameText, 3, 13, 2, 2);
				addPopupGrid.add(dadSurNameLabel, 1, 15, 2, 2);
				addPopupGrid.add(dadSurNameText, 3, 15, 2, 2);

				Button btnCreate = new Button("Create");
				Button btnCancel = new Button("Cancel");

				addPopupGrid.add(btnCreate, 1, 17, 2, 2);
				addPopupGrid.add(btnCancel, 3, 17, 2, 2);

				btnCancel.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						dialogStage.close();
					}
				});

				btnCreate.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						Boolean success = false;
						try {
							int age = Integer.parseInt(ageText.getText());
							success = _driver.createProfile(firstNameText.getText(), surNameText.getText(), statusText.getText(),
									age);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (success) {
							dialogStage.close();
						}
					}
				});

				addPopupGrid.setAlignment(Pos.TOP_LEFT);

				addPopupGrid.setHgap(10);
				addPopupGrid.setVgap(10);

				Scene dialogScene = new Scene(addPopupGrid, 600, 400);
				dialogStage.setScene(dialogScene);
				dialogStage.show();
			}
		});
	}
	
	private void setSearchButtonAction(Button search, Stage primaryStage) {
		search.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {


			}
		});
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}