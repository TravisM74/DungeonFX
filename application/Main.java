package application;


import javafx.scene.layout.BorderPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import old_code.Player;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import Control.MovementControlButtons;
import Control.Visual;

import Gfx.HeldItem;
import Gfx.Humanoid;
import Items.Item;
import Items.ItemTypeEnum;
import Items.LootTables;
import Items.QualityEnum;
import Items.UsedEnum;
import Items.WeaponTypeEnum;
import Race.Race;
import Race.RaceEnum;
import UserInterface.CharacterSheet;
import UserInterface.CombatInterface;
import UserInterface.ItemInterface;
import UserInterface.LootInterface;

import World.WorldArray;

import attributes.StatusState;

import javafx.application.Application;

import javafx.geometry.Pos;


public class Main extends Application {
	private Pane root;
	
	private BorderPane gameWindows;
	private Scene scene;
	private Stage stage;
	CharacterSheet cSheet;
	
	private Group board;
	private Group playerBody;
	private Pane centerWindow;
	public final int WIDTH = 39;
	public final int HEIGHT = 19;
	
	
	//private Player player;
	private Mob player;

	private WorldArray world;
	private WorldEntity entity;
	private CombatInterface combatInt;
	private LootInterface lootThings;
	private Humanoid playerForm;
	private MovementControlButtons moveButtons;
	private HeldItem mainhandHeld;
	private HeldItem offhandHeld;
	
	private ArrayList<WorldEntity> mobQue;
	
	
	@Override
	public void start(Stage primaryStage) {
		this.stage=primaryStage;
		mobQue = new ArrayList<>();
							
		makeBoard();
		this.world = new WorldArray();
		createPlayer();
		//moveButtons = new MovementControlButtons(this.player);
		root = new BorderPane();
		gameWindows = new BorderPane();
		centerWindow = new Pane();
		
		refreshLeftPanel();
		for (int i = 0 ; i < 10; i++) {
			newGeneratedOrcMob();
		}
		//main PlayWindow name
		centerWindow.getChildren().addAll(board,playerForm.getBody(),mainhandHeld.getItemForm());
		gameWindows.setCenter(centerWindow);
		scene = new Scene(root,2200,1300);
		
		cSheet = new CharacterSheet(this.player ,this.stage, this.scene);
		
		movementControl(primaryStage);
		
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	public Pane getRoot() {
		return this.root;
	}
	
	public void createPlayer() {
		CharacterClass cClass  = new CharacterClass(CharClass.FIGHTER);
		this.entity = new WorldEntity(Entities.PLAYER);
		Race race = new Race(RaceEnum.HUMAN);
		this.player = new Mob("Test_Victim",1,race,cClass,entity,this.world);
		
		playerForm = this.player.getCClass().getEntityForm();
		this.player.getInventory().getMainHandItem().getWeaponType().getMainhandHeldItemForm().getItemForm().setTranslateX(player.getCClass().getEntityForm().getTranslateX()+player.getCClass().getEntityForm().getRightHandX());
		this.player.getInventory().getMainHandItem().getWeaponType().getMainhandHeldItemForm().getItemForm().setTranslateY(player.getCClass().getEntityForm().getTranslateY()+player.getCClass().getEntityForm().getRightHandY());
		
		mainhandHeld = this.player.getInventory().getMainHandItem().getWeaponType().getMainhandHeldItemForm();

		
		
	}
	
	public void processTurnEvents(Stage primaryStage) {
		
		int regen =this.player.getStatus().gethealthRegen();
		System.out.println("PTE:"+ this.player.getStatus() +" HP: "+ this.player.getHitPoints()+" regen ="+ regen +" move:"+this.player.getmoveRestriction());
		refreshLeftPanel();
		testForInteractions(primaryStage);
		updateWeaponVisuals();	
		if((this.player.getStatusState() == (StatusState.RESTING))|| (this.player.getStatusState() == (StatusState.UNCONCEOUS))){
			this.player.isAlive();
			System.out.println("in resting");
			this.player.addHealth(player.getStatus().gethealthRegen());
		}

		
	
	}
	public void updateWeaponVisuals() {
		// removes item weapons in backpack set to visibility false 
		for (Item item :player.getInventory().getBackPackContents()) {
			if ((item.getItemTypeEnum().equals(ItemTypeEnum.WEAPON)) && (!item.getVisability() )){
					centerWindow.getChildren().remove(item.getWeaponType().getMainhandHeldItemForm().getItemForm());
			}
		}
		if (!centerWindow.getChildren().contains(player.getInventory().getMainHandItem().getWeaponType().getMainhandHeldItemForm().getItemForm())) {
			centerWindow.getChildren().add(player.getInventory().getMainHandItem().getWeaponType().getMainhandHeldItemForm().getItemForm());
		} 
		
	}
	
	public void testForInteractions(Stage primaryStage) {
		//this.world.getDungeonlevel(this.player.getDepth()).getTile(this.player.getEntity().getxLoc(),this.player.getEntity().getyLoc()).fightTest();
		if (this.world.getDungeonlevel(this.player.getDepth())
				.getTile(this.player.getEntity().getxLoc(),this.player.getEntity().getyLoc())
				.fightTest()) {
			this.combatInt = new CombatInterface(this.player,this.entity, this.world, primaryStage, this.scene);
			//primaryStage.setScene(scene);
			//primaryStage.show();
		}
		
	}

	public void ProcessMobQueTurn (ArrayList<WorldEntity> mobQue){
		if (mobQue.size() > 0) {
			for(WorldEntity entity: mobQue) {
				}
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void movementControl(Stage PrimaryStage) {
		scene.setOnKeyPressed(event -> { 
			KeyCode key = event.getCode();
			//System.out.println(key);
			switch(key) {
			
				case LEFT: 
					player.moveLeft();
					this.player.getCClass().getEntityForm().setTranslateX(world.getXPixel(this.player.getEntity().getxLoc()));
					this.player.getCClass().getEntityForm().setTranslateY(world.getYPixel(this.player.getEntity().getyLoc()));					
					processTurnEvents(PrimaryStage);
					break;
				case RIGHT: 
					player.moveRight();
					this.player.getCClass().getEntityForm().setTranslateX(world.getXPixel(this.player.getEntity().getxLoc()));
					this.player.getCClass().getEntityForm().setTranslateY(world.getYPixel(this.player.getEntity().getyLoc()));					
					
					processTurnEvents(PrimaryStage);
					break;
				case UP:
					player.moveUp();
					this.player.getCClass().getEntityForm().setTranslateX(world.getXPixel(this.player.getEntity().getxLoc()));
					this.player.getCClass().getEntityForm().setTranslateY(world.getYPixel(this.player.getEntity().getyLoc()));					
					
					processTurnEvents(PrimaryStage);
					break;
				case DOWN: 
					player.moveDown();
					this.player.getCClass().getEntityForm().setTranslateX(world.getXPixel(this.player.getEntity().getxLoc()));
					this.player.getCClass().getEntityForm().setTranslateY(world.getYPixel(this.player.getEntity().getyLoc()));					
					
					processTurnEvents(PrimaryStage);
					break;
				case F1:
					JOptionPane.showMessageDialog(null, 	
							"Help \n"
							+"A\t\t- Adventuring\n"
							+"R\t\t- Resting and healing\n" 
							+"SPACE\t- passTurn\n"
							+"L \t\t- Loot space"
							);
				case R:
					this.player.setStatusResting();
					
					this.player.restrictPlayerMovementOn();
					processTurnEvents(PrimaryStage);
					this.player.getCClass().getEntityForm().getBody().setRotate(90);
					break;
					
				case A:
					this.player.setStatusAdventuring();
					this.player.restrictPlayerMovementOff();
					processTurnEvents(PrimaryStage);
					this.player.getCClass().getEntityForm().getBody().setRotate(0);
					break;
				case L:
					lootThings = new LootInterface(this.player, this.world, PrimaryStage, scene);
					lootThings.refreshPanels();
					
					
					break;
				case C:
					cSheet.CharaterSheetOpen();
					cSheet.refreshPanels();
					break;
			
				//Test button to get Wep in hand
				case V:
					ItemInterface itemInt = new ItemInterface(playerForm,scene,stage);
					itemInt.openItemPane();
					break;
				
			default: 
				if ((!this.player.isAlive() ) && (this.player.getHitPoints() > -9)) {
					System.out.println(this.player.getStatus() +" "+ this.player.getHitPoints()+ ":");
					
				}
				System.out.println("Turn Passed");
				processTurnEvents(PrimaryStage);
				break;
			}
		});
		root.getChildren().addAll(gameWindows);
	}
	
	public void refreshLeftPanel() {
		try {
			VBox leftSidePanels = new VBox();
			
			leftSidePanels.getChildren().addAll(this.player.statsPane());
			gameWindows.setLeft(leftSidePanels);
						
		} catch (IllegalArgumentException e) {
			System.out.println("Error :"+e);
		}
	}
	
	public void makeBoard() {
		board = new Group();
		boolean isLight = true;
		int size = 50;
		for (int rank = 0; rank < 19; rank++)
		{
		 for (int file = 0; file < 39; file++)
		 {
			 Rectangle r = new Rectangle(size, size);
			 r.setStyle("-fx-fill: grey; -fx-stroke: black; -fx-stroke-width: 5;");
			 
			 r.setX(file * size);
			 r.setY(rank * size);
			 r.setOpacity(.10);
			 if (isLight)
			 r.setFill(Color.BLACK);
			 else
			 r.setFill(Color.DARKGREY);
			 isLight = !isLight;
			 board.getChildren().add(r);
			 }
			 isLight = !isLight;
			}
		
	}
	
	public void newGeneratedOrcMob() {
		
			String name ="Orc";
			int level = 1;
			CharacterClass cClass = new CharacterClass(CharClass.ORC_FIGHTER);
			Race race = new Race(RaceEnum.ORC);
			WorldEntity orcEntity= new WorldEntity(Entities.MOB);
			Mob createdMob = new Mob(name,level,race,cClass,orcEntity,this.world);
			orcEntity.setMob(createdMob);
			Random rand = new Random();
			boolean notValidSpot = true;
			while (notValidSpot) {
				int xLoc = rand.nextInt(38);
				int yLoc = rand.nextInt(18);
				
				if ((player.getEntity().getxLoc() == xLoc)&&(player.getEntity().getyLoc() == yLoc)) {
					//not spawning orc on player...
				} else {
					//creates the orcEntity
					this.world.getDungeonlevel(player.getDepth()).getTile(xLoc, yLoc).add(orcEntity);
					this.world.getDungeonlevel(player.getDepth()).getTile(xLoc, yLoc);			
					orcEntity.setDepth(player.getDepth());
					orcEntity.setxLoc(xLoc);
					orcEntity.setyLoc(yLoc);
					System.out.println(orcEntity.getxLoc() +":" + orcEntity.getyLoc());
					// assign it a weapon
					LootTables lootTable = new LootTables();
					orcEntity.getMob().getInventory().setMainHandItem(lootTable.getRandomCommonWeapon());
					orcEntity.getMob().getInventory().addCopperCoin(rand.nextInt(25));
					orcEntity.getMob().setDamageMod(2);
					
					//create its visualForm	
	
					orcEntity.getMob().getCClass().getEntityForm().getBody().setTranslateX(world.getXPixel(orcEntity.getxLoc()));
					orcEntity.getMob().getCClass().getEntityForm().getBody().setTranslateY(world.getYPixel(orcEntity.getyLoc()));
					orcEntity.getMob().getCClass().getEntityForm().setEntityColor(Color.GREEN);
					centerWindow.getChildren().add(orcEntity.getMob().getCClass().getEntityForm().getBody());
					
					//create its weponForm	
					
					createdMob.getInventory().getMainHandItem().getWeaponType().getMainhandHeldItemForm().getItemForm()
								.setTranslateX(world.getXPixel(orcEntity.getxLoc())+orcEntity.getMob().getCClass().getEntityForm().getRightHandX());
					createdMob.getInventory().getMainHandItem().getWeaponType().getMainhandHeldItemForm().getItemForm()
								.setTranslateY(world.getXPixel(orcEntity.getyLoc())+orcEntity.getMob().getCClass().getEntityForm().getRightHandY());
					centerWindow.getChildren().add(createdMob.getInventory().getMainHandItem().getWeaponType().getMainhandHeldItemForm().getItemForm());
					
					//in  Mob()
					/*
					orcEntity.getMob().addEntityVisualBody(Color.GREEN,orcEntity.getxLoc(),orcEntity.getyLoc());
					
					orcEntity.getMob().addEntityVisualWeapon();
					centerWindow.getChildren().add(orcEntity.getMob().getMobVisual());
				    */
					mobQue.add(orcEntity);
					notValidSpot = false;
				}
			}
		
	}
	
	public HBox CharacterSheetPane() {
		HBox characterPane =new HBox();
		characterPane.setAlignment(Pos.CENTER);
		Button cSheetButton = new Button("Character Sheet");
		characterPane.getChildren().add(cSheetButton);
		cSheetButton.setOnAction(e -> {
			cSheet.CharaterSheetOpen();
		});
		//root.setTop(topPane);
		return characterPane;
	}
	

	
}
