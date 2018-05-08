import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class Gui extends Application {
	private Driver _driver;
	private ObservableList<Profile> _names;
	private Profile _selectedProfile;

	public Gui() {
		_driver = new Driver();
		_names = FXCollections.<Profile>observableArrayList();
	}

	@Override // Override the start method from the superclass
	public void start(Stage primaryStage) throws Exception {
		GridPane root = new GridPane();
		Button btnDisplay = new Button("Display");
		Button btnDelete = new Button("Delete");
		Button btnAdd = new Button("Add");

		root.add(btnDisplay, 1, 1, 2, 2);
		root.add(btnDelete, 1, 5, 2, 2);
		root.add(btnAdd, 1, 10, 2, 2);
		root.setAlignment(Pos.TOP_LEFT);

		Label searchLabel = new Label("search the profile");
		TextField searchText = new TextField();
		Button btnSearch = new Button("Search");
		root.add(searchLabel, 7, 1, 2, 2);
		root.add(searchText, 9, 1, 4, 2);
		root.add(btnSearch, 13, 1, 2, 2);

		ListView<Profile> lstNames = new ListView<>(_names);
		lstNames.setOrientation(Orientation.VERTICAL);
		lstNames.setPrefSize(300, 200);
		lstNames.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Profile>() {
			public void changed(ObservableValue<? extends Profile> ov, final Profile oldvalue, final Profile newvalue) {
				_selectedProfile = newvalue;
			}
		});

		root.add(lstNames, 7, 3, 2, 2);

		root.setHgap(10);
		root.setVgap(10);

		setAddButtonAction(primaryStage, btnAdd);
		setSearchButtonAction(btnSearch, searchText);
		setDeleteButtonAction(btnDelete);
		setDiplayButtonAction(btnDisplay, primaryStage);

		root.setPadding(new Insets(30));
		Scene scene = new Scene(root, 800, 600);
		primaryStage.setTitle("Menu");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void setDeleteButtonAction(Button btnDelete) {
		btnDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					if (_selectedProfile != null) {
						_driver.DeleteProfile(_selectedProfile.getname());
						_names.remove(_selectedProfile);
						_selectedProfile = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void setAddButtonAction(Stage primaryStage, Button add) {
		add.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				final Stage dialogStage = new Stage();
				dialogStage.initModality(Modality.APPLICATION_MODAL);
				dialogStage.initOwner(primaryStage);
				dialogStage.setTitle("Create a new profile");

				GridPane addPopupGrid = new GridPane();
				Label nameLabel = new Label("Name");
				TextField nameText = new TextField();
				Label ageLabel = new Label("Age");
				TextField ageText = new TextField();
				Label statusLabel = new Label("Status");
				TextField statusText = new TextField();

				addPopupGrid.add(nameLabel, 1, 1, 2, 2);
				addPopupGrid.add(nameText, 3, 1, 2, 2);
				addPopupGrid.add(ageLabel, 1, 3, 2, 2);
				addPopupGrid.add(ageText, 3, 3, 2, 2);
				addPopupGrid.add(statusLabel, 1, 5, 2, 2);
				addPopupGrid.add(statusText, 3, 5, 2, 2);

				Label momNameLabel = new Label("Mother's name");
				TextField momNameText = new TextField();

				addPopupGrid.add(momNameLabel, 1, 7, 2, 2);
				addPopupGrid.add(momNameText, 3, 7, 2, 2);

				Label dadNameLabel = new Label("Father's name");
				TextField dadNameText = new TextField();

				addPopupGrid.add(dadNameLabel, 1, 9, 2, 2);
				addPopupGrid.add(dadNameText, 3, 9, 2, 2);

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
							success = _driver.createProfile(nameText.getText(), statusText.getText(), age);
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

	private void setSearchButtonAction(Button search, TextField searchText) {
		search.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Profile profile = _driver.searchProfile(searchText.getText());
				if (profile != null) {
					_names.add(profile);
				}
			}
		});
	}

	private void setDiplayButtonAction(Button btnDisplay, Stage primaryStage) {
		btnDisplay.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				final Stage DisplayStage = new Stage();
				DisplayStage.initModality(Modality.APPLICATION_MODAL);
				DisplayStage.initOwner(primaryStage);
				DisplayStage.setTitle("Profile Display");

				GridPane DisplayPopupGrid = new GridPane();

				Label nameLabel = new Label("Name:");
				Label nameText = new Label(_selectedProfile.getname());
				Label ageLabel = new Label("Age:");
				Label ageText = new Label(_selectedProfile.getage() + "");
				Label statusLabel = new Label("Status:");
				Label statusText = new Label(_selectedProfile.getstatus());

				ObservableList<Profile> listoffriends = FXCollections
						.<Profile>observableArrayList(_selectedProfile.getfriendlist());

				ListView<Profile> _friendlist = new ListView<>(listoffriends);
				_friendlist.setOrientation(Orientation.VERTICAL);
				_friendlist.setPrefSize(300, 200);
				_friendlist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Profile>() {
					public void changed(ObservableValue<? extends Profile> ov, final Profile oldvalue,
							final Profile newvalue) {
						_selectedProfile = newvalue;
					}
				});

				DisplayPopupGrid.add(nameLabel, 1, 1, 2, 2);
				DisplayPopupGrid.add(nameText, 3, 1, 2, 2);
				DisplayPopupGrid.add(ageLabel, 1, 3, 2, 2);
				DisplayPopupGrid.add(ageText, 3, 3, 2, 2);
				DisplayPopupGrid.add(statusLabel, 1, 5, 2, 2);
				DisplayPopupGrid.add(statusText, 3, 5, 2, 2);
				DisplayPopupGrid.add(_friendlist, 1, 8, 2, 2);

				Scene DisplayScene = new Scene(DisplayPopupGrid, 600, 400);
				DisplayStage.setScene(DisplayScene);
				DisplayStage.show();
			}
		});
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}