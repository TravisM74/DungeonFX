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
		primaryStage.setScene(combatScene);
		primaryStage.show();
		
		
		if (this.fightNumber <= this.mobsGatheredFromTile.size()-1) {
			this.entity = this.mobsGatheredFromTile.get(fightNumber);
			thisFight = new Combat(this.player,this.world);
			
		} else {
			this.fightResolved = true;
		}
		if (thisFight.getPlayerDefeated()) {
			this.fightResolved = true;
		}
		
		/*
		if (this.fightResolved) {
			primaryStage.setScene(scene);
		}
		*/
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
		leftPane.getChildren().add(statsPane);
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
		Label warning = new Label("Melee will get free attack");
		HBox altOptions = new HBox();
		tryRanged = new Button("Try ranged attack");
		tryRanged.setOnAction(e -> tryRangedButtonAction());
		tryFlee = new Button("Try Flee");
		tryFlee.setOnAction(e -> tryFleeButtonAction());
		tryHeal = new Button ("Try Heal");
		tryHeal.setOnAction(e -> tryHealButtonAction());
		VBox addWarning = new VBox();
		altOptions.getChildren().addAll(tryRanged,tryFlee,tryHeal);
		addWarning.getChildren().addAll(altOptions,warning);
		backButton = new Button("Back");
		backButton.setOnAction( e -> backButtonClicked());
		bottomPane.getChildren().addAll(fight,altOptions,backButton);
		return bottomPane;
	}
	private void fightButtonAction() {
		this.combatText = "";
		thisFight.playerAttacks(this.entity);
		thisFight.endTurn();
		
		if (thisFight.getMobDefeated()) {
			this.fightNumber++;
		} else {
			thisFight.MobAttacks(this.entity);
			thisFight.endTurn();
		}
		if (this.mobsGatheredFromTile.size()-1 > this.fightNumber) {
			for (int i = this.fightNumber; i < this.mobsGatheredFromTile.size()-1; i++) {
				thisFight.MobAttacks(this.entity);
				thisFight.endTurn();
			} 
		} else {
			this.combatText = "the fight is over.. \n (press back) \n  ";
			resetInterface();
		}
		combatTextReport();
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
	
		primaryStage.setScene(scene);
	}
	private void tryRangedButtonAction() {
		
	}
	private void tryFleeButtonAction() {
		
	}
	private void tryHealButtonAction() {
		
	}


}
