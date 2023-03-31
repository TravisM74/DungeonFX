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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import old_code.Player;

public class CombatInterface2 {
	
	private ArrayList<WorldEntity> mobsGatheredFromTile;
	private ArrayList<WorldEntity> defeatedMobs;
	private WorldArray world;
	private Stage combatStage;
	private Scene scene;
	private BorderPane combatRoot;
	private boolean isFightResolved;
	private ArrayList<String> combatRoundText;
	private String combatText;

	private Tile tile;

	
	private Mob player;
	private WorldEntity entity;
	
	
	public CombatInterface2(Mob player, WorldEntity entity, WorldArray world, Stage primaryStage, Scene scene) {
		this.scene = scene;
		this.combatRoot = new BorderPane();
		this.player = player;
		this.entity = entity;
		this.world = world;
		this.isFightResolved = false;	
		this.mobsGatheredFromTile = new ArrayList<>();
		this.defeatedMobs = new ArrayList<>();
		this.combatRoundText = new ArrayList<>();;
		this.combatText = "";
		BorderPane interfaceFrame = new BorderPane();
					
		combatRoot.getChildren().addAll(interfaceFrame);
		
		this.tile = (this.world.getDungeonlevel(this.player.getDepth())
					.getTile(this.player.getEntity().getxLoc(), this.player.getEntity().getyLoc()));
		this.mobsGatheredFromTile = this.tile.getFightingMobEntitys();
		
		
		VBox pStats = new VBox();
		Pane playerStats = new Pane();
		
		for(WorldEntity entityToFight :this.mobsGatheredFromTile ) {
			Combat thisFight = new Combat(this.player, entityToFight,this.world);
			
			while(!this.isFightResolved) {
				this.combatText = "";
				this.isFightResolved = thisFight.fightRound();
				this.combatRoundText = thisFight.getCombatText();
				for (String s:combatRoundText) {
					System.out.println(s);
					combatText+=(s +"\n");
					interfaceFrame.setCenter(new Text(combatText));
					interfaceFrame.setLeft(this.player.statsPane());
					interfaceFrame.setRight(entityToFight.getMob().mobStatsPane());
					
				}
								
				//primaryStage.setScene(combatScene);
				
				//combatScene = new Scene(combatRoot,1200,800);
				//primaryStage.show();
				//Button nextRoundButton = new Button("Next ");
				//interfaceFrame.setBottom(nextRoundButton);

				thisFight.MessageDialog(player.getName() +" V " + entityToFight.getMob().getName(),combatText);	
			}
		}
		
	}
/*
	scene = new Scene(root,2200,1300);
	lootScene = new Scene(lootRoot,2200,1300);
	
	movementControl(primaryStage);
	
	primaryStage.setScene(scene);
*/

}
