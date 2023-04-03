package UserInterface;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JDialog;

import Interaction.Combat;
import Interaction.ToHitAC0;
import Items.Item;
import World.Tile;
import World.WorldArray;
import application.Mob;
import application.WorldEntity;
import attributes.Status;
import attributes.StatusState;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import old_code.Player;

public class CombatInterface {
	
	private ArrayList<WorldEntity> mobsGatheredFromTile;
	private WorldArray world;
	private Scene scene;
	private Scene combatScene;
	private Stage primaryStage;
	
	private int fightNumber;
	
	private BorderPane interfaceFrame;
	private Combat thisFight;

	
	private ArrayList<String> combatRoundText;
	private String combatText;

	private Tile tile;
	private Button fight;
	
	private Button backButton;
	
	private Button tryRanged;

	private Button tryFlee;

	private Button tryHeal;


	private boolean fightResolved;
	
	private Mob player;
	private WorldEntity entity;
	
	
	public CombatInterface(Mob player, WorldEntity entity, WorldArray world, Stage primaryStage, Scene scene) {
	
		this.scene = scene;
		this.player = player;
		this.entity = entity;
		this.world = world;
		this.primaryStage =primaryStage;
		this.fightResolved = false;
		
		
		this.mobsGatheredFromTile = new ArrayList<>();
		this.combatRoundText = new ArrayList<>();;
		this.combatText = "";
		this.fightNumber= 0;

			
		this.tile = (this.world.getDungeonlevel(this.player.getDepth())
					.getTile(this.player.getEntity().getxLoc(), this.player.getEntity().getyLoc()));
		this.mobsGatheredFromTile = this.tile.getFightingMobEntitys();
				
		interfaceFrame = new BorderPane();
		interfaceFrame.setTop(createTopPane());
		interfaceFrame.setRight(createRightPane(this.mobsGatheredFromTile));
		interfaceFrame.setLeft(createLeftPane());
		interfaceFrame.setBottom(createBottomPane());
		combatScene= new Scene(interfaceFrame,1000,900);
		fightCycle();
		primaryStage.setScene(combatScene);
		primaryStage.show();
		
		
		
	}
	
	
	private void fightCycle() {
		interfaceFrame.setRight(createRightPane(this.mobsGatheredFromTile));
		interfaceFrame.setLeft(createLeftPane());
		
		if ((this.fightNumber <= this.mobsGatheredFromTile.size()-1)){
			this.entity = this.mobsGatheredFromTile.get(fightNumber);
			thisFight = new Combat(this.player,this.world);
		} else {
			this.fightResolved = true;
			//thisFight.setPlayerDefeatedFalse();
		}
		
		
		if (this.player.getStatusState().equals(StatusState.UNCONCEOUS)) {
			fightResolved = true;
			fight.setDisable(true);
			backButton.setDisable(false);
		}
		if (fightResolved) {
			fight.setDisable(true);
			backButton.setDisable(false);
		}
	}

	private Pane createTopPane() {
		VBox topPane = new VBox();
		topPane.setAlignment(Pos.CENTER);
		Label topLabel = new Label("Combat ");
		topPane.getChildren().add(topLabel);
		return topPane;
	}
	private Pane createLeftPane() {
		VBox leftPane = new VBox(30);
		Label nameLabel = new Label(this.player.getName());
		Pane statsPane = new Pane(this.player.getStatsPane());
		leftPane.getChildren().addAll(nameLabel,statsPane);
		return leftPane;
	}
	private Pane createRightPane(ArrayList<WorldEntity> mobsGatheredFromTile2) {
		VBox rightPane = new VBox(30);
		for (WorldEntity mobEntity :mobsGatheredFromTile2) {
			rightPane.getChildren().add( new Pane(mobEntity.getMob().getStatsPane()));
		}
		return rightPane;
	}
	private Pane createBottomPane() {
		HBox bottomPane = new HBox();
		bottomPane = new HBox(10);
		bottomPane.setAlignment(Pos.CENTER);
		
		fight = new Button("Fight");
		fight.setOnAction(e -> fightButtonAction());
		fight.setDisable(false);
		
		Label warning = new Label("Melee will get free attack");
		HBox altOptions = new HBox();
		
		tryRanged = new Button("Try ranged attack");
		tryRanged.setDisable(true);
		tryRanged.setOnAction(e -> tryRangedButtonAction());
		
		tryFlee = new Button("Try Flee");
		tryFlee.setOnAction(e -> tryFleeButtonAction());
		tryFlee.setDisable(true);
		
		tryHeal = new Button ("Try Heal");
		tryHeal.setDisable(true);
		tryHeal.setOnAction(e -> tryHealButtonAction());
		
		VBox addWarning = new VBox();
		altOptions.getChildren().addAll(tryRanged,tryFlee,tryHeal);
		addWarning.getChildren().addAll(altOptions,warning);
		
		backButton = new Button("Back");
		backButton.setDisable(true);
		backButton.setOnAction( e -> backButtonClicked());
		bottomPane.getChildren().addAll(fight,altOptions,backButton);
		return bottomPane;
	}
	private void fightButtonAction() {
		this.combatText = "";
		
		//Combat thisround= new Combat(this.player.getEntity() , this.entity);
	
		thisFight.playerAttacks(this.entity);
		thisFight.endTurn(player.getName());
		
		if (thisFight.getMobDefeated()) {
			this.fightNumber++;
		} else {
			thisFight.mobAttackTurn(this.entity);
			thisFight.endTurn(this.entity.getMob().getName());
		}
		
		if (this.mobsGatheredFromTile.size()-1 > this.fightNumber) {
			for (int i = this.fightNumber; i < this.mobsGatheredFromTile.size()-1; i++) {
				thisFight.mobAttackTurn(this.entity);
				thisFight.endTurn(this.entity.getMob().getName());
			} 
		}
		
		
		combatTextReport();
		fightCycle();
	}
	private void resetInterface() {
		this.fightNumber = 0;
		this.combatText = "";
		this.mobsGatheredFromTile.clear();
		this.fightResolved = false;
	}
	private void combatTextReport() {
		this.combatRoundText = thisFight.getCombatText();
		for (String s:combatRoundText) {
			System.out.println(s);
			combatText+=(s +"\n");
			interfaceFrame.setCenter(new Text(combatText));
			interfaceFrame.setLeft(createLeftPane());
			interfaceFrame.setRight(createRightPane(mobsGatheredFromTile));
		}
	}
	private void backButtonClicked() {
		// setting player defeated flag to false in case then next fight is against the same mob entity after healing up.
		/*
		if (thisFight.getPlayerDefeated()) {
			thisFight.setPlayerDefeatedFalse();
		}
		*/
		resetInterface();
		primaryStage.setScene(scene);
	}
	private void tryRangedButtonAction() {
		
	}
	private void tryFleeButtonAction() {
		
	}
	private void tryHealButtonAction() {
		
	}


}
