package UserInterface;

import Items.Item;
import application.Mob;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class CharacterSheet {
	Scene characterSheet;
	Stage stage;
	Scene scene;
	BorderPane root;
	Mob player;
	Button switchButton;
	VBox leftPane;
	HBox topPane;
	VBox rightPane;
	HBox bottomPane;
	
	public CharacterSheet(Mob player,Stage stage,Scene scene) {
		this.stage = stage;
		this.scene = scene;
		
		this.player = player;
		
		root = new BorderPane();
		createLeftPanel();
		createTopPanel();
		createRightPanel();
		createBottomPanel();
		createCenter();	
		
		characterSheet = new Scene(root,800,600);
		
					
	}
	public void refreshPanels() {
		createLeftPanel();
		createTopPanel();
		createRightPanel();
		createCenter();
		createBottomPanel();
	}
	private void createBottomPanel(){
		bottomPane = new HBox();
		bottomPane.setAlignment(Pos.CENTER);
		switchButton = new Button(".Back.");
		bottomPane.getChildren().add(switchButton);
		
		 switchButton.setOnAction(e -> {
			 switchPressed();
			});
		root.setBottom(bottomPane);
	}
	private void createCenter() {
		root.setCenter(player.getInventory().characterGear());
	}
	private void createRightPanel(){
		rightPane = new VBox(10);
		Label levelLabel = new Label("Carrying ..");
		
		rightPane.getChildren().addAll(levelLabel,this.player.getInventory().DisplayCoin());
		for(Item item :this.player.getInventory().getBackPackContents()){	
			rightPane.getChildren().add(new Label(item.getDescription()));
		}
		root.setRight(rightPane);
	}
	private void createTopPanel(){
		topPane = new HBox(10);
		topPane.setAlignment(Pos.CENTER);
		Label nameLabel = new Label(this.player.getName());
		topPane.getChildren().add(nameLabel);
		root.setTop(topPane);
	}
	private void createLeftPanel() {
		leftPane = new VBox(10);
		leftPane.getChildren().add(this.player.statsPane());
		root.setLeft(leftPane);
	}
	public void CharaterSheetOpen() {
		createCenter();
		stage.setScene(characterSheet);
	}
	public void isclickedClose() {
		stage.setScene(scene);
		
	}
	public void switchPressed() {
		isclickedClose();
	}
}
