package UserInterface;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Items.Item;
import World.Tile;
import World.WorldArray;
import application.Mob;
import application.WorldEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import old_code.Player;

public class LootInterface {
	
	private BorderPane lootRoot;
	private Scene scene;
	private Stage stage;
	private ArrayList<WorldEntity> lootFound;
	private Button backToMain;
	private Mob player;
	private WorldArray world;
	private Tile thisTile;
	private VBox centerArea;
	private VBox rightPaneLoot;
	private VBox leftPanePlayer;
	
	public LootInterface(Mob player,WorldArray world,Stage stage,Scene scene) {
		this.stage = stage;
		this.scene = scene;
		this.player = player;
		this.world = world;
		lootFound = new ArrayList<>();
		
		BorderPane lootRoot = new BorderPane();
		createLeftPanel();
		getItemsFromTile();		
		
		createRightPanel();
		createCenterPanel();		
		
		
		HBox bottomBox = new HBox();
		backToMain = new Button(">- Back -<");
		backToMain.setOnAction(e -> returnToMainClicked());
		bottomBox.getChildren().add(backToMain);
		
		
		lootRoot.setRight(rightPaneLoot);
		lootRoot.setCenter(centerArea);
		lootRoot.setLeft(leftPanePlayer);
		lootRoot.setBottom(bottomBox);

	
		Scene lootScene = new Scene(lootRoot,1000,800);
		stage.setScene(lootScene);
		stage.show();
		
		for(WorldEntity entity: lootFound) {
			int confirmation =JOptionPane.showConfirmDialog(null, "Loot the "+ entity.getItem().toString(), "Confirmation",JOptionPane.YES_NO_OPTION);
			if (confirmation == JOptionPane.YES_OPTION){
				this.player.getInventory().AddItemToBackPack(entity.getItem());
				this.thisTile.remove(entity);
			} else {
				
			}
		}
	}
	public void refreshPanels() {
		createLeftPanel();
		getItemsFromTile();		
		createRightPanel();
		createCenterPanel();		
		
	}
	private void createCenterPanel(){
		centerArea = new VBox();
		centerArea.setBackground(new Background(new BackgroundFill(Color.SANDYBROWN, null, null)));
		Label backPackContentsLabel = new Label ("Currently in backpack.");
		backPackContentsLabel.setAlignment(Pos.CENTER);
		centerArea.getChildren().add(backPackContentsLabel);
		VBox playersBackPackInventory = new VBox();
		playersBackPackInventory.setAlignment(Pos.TOP_CENTER);
		for (Item item:this.player.getInventory().getBackPackContents()) {
			playersBackPackInventory.getChildren().add(new Label(item.toString()));
		}
		centerArea.getChildren().add(playersBackPackInventory);
	}
	private void createRightPanel() {
		rightPaneLoot = new VBox();
		rightPaneLoot.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
		rightPaneLoot.setAlignment(Pos.TOP_CENTER);
		rightPaneLoot.setSpacing(10);
		rightPaneLoot.setPadding(new Insets(2,2,2,2));
		Label rightPanelLootLabel = new Label("Loot on the floor..");
		rightPaneLoot.getChildren().add(rightPanelLootLabel);
		for(WorldEntity entity: lootFound) {
			rightPaneLoot.getChildren().add( new Label(entity.getItem().toString()));
		}
	}
	private void getItemsFromTile() {
		this.thisTile = this.world.getDungeonlevel(this.player.getDepth()).getTile(this.player.getEntity().getxLoc(), this.player.getEntity().getyLoc());
		lootFound = this.thisTile.getLootItems();
	}
	private void createLeftPanel() {
		leftPanePlayer = new VBox();
		leftPanePlayer.setAlignment(Pos.TOP_CENTER);
		leftPanePlayer.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
		Label leftPaneLabel = new Label("Players Inventory..");
		leftPanePlayer.getChildren().addAll(leftPaneLabel,this.player.getInventory().DisplayCoin());
	}
	private void returnToMainClicked() {
		this.stage.setScene(scene);
	}
	
}
