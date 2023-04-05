package application;



import Items.ArmourTypeEnum;
import Items.Item;
import Items.ItemTypeEnum;
import Items.QualityEnum;
import Items.UsedEnum;
import Items.WeaponTypeEnum;
import Race.Race;
import World.WorldArray;
import attributes.Stats;
import attributes.Status;
import attributes.StatusState;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import old_code.PlayerInventory;

public class Player extends Mob {
		
	private Stats stats;
		
	//private Image fighter;
	private CharacterClass cClass;
	
	private WorldArray world;
	private Status status;
	private StatusState statusState;
	private WorldEntity entity; 
	private Race race;
	
	//private PlayerInventory inventory;
	
	private boolean restrictMove;
		
	
	public Player(String name,int level ,WorldArray world, Race race, CharacterClass cClass, WorldEntity enity) {
		super(name,level,race,cClass);
		addStarterItems();
		this.cClass  =cClass;
		this.race = race;
		this.status = new Status(StatusState.ADVENTURING);
		this.statusState =StatusState.ADVENTURING;
		this.entity = new WorldEntity(Entities.PLAYER);
		
		this.world = world;
		this.stats = new Stats();
		this.restrictMove = false;
		
		
	}
	private void addStarterItems() {
		Item starterRustySword = new Item (" cheaply made ",ItemTypeEnum.WEAPON,WeaponTypeEnum.SHORT_SWORD ,QualityEnum.POOR,UsedEnum.MAIN_HAND, 0, 1, 25);
		Item emptyMainHand = new Item (" main hand ", ItemTypeEnum.WEAPON,WeaponTypeEnum.HANDS, QualityEnum.COMMON, UsedEnum.MAIN_HAND, 0, 0, 0);
		Item emptyOffHand = new Item (" off hand ",ItemTypeEnum.EMPTY,UsedEnum.OFF_HAND,QualityEnum.COMMON,0,0,0);
		Item cap = new Item(" cap",ItemTypeEnum.ARMOUR,ArmourTypeEnum.CLOTH,QualityEnum.COMMON,UsedEnum.HEAD,0,0,2);
		Item shirt = new Item("shirt",ItemTypeEnum.ARMOUR,ArmourTypeEnum.CLOTH,QualityEnum.COMMON,UsedEnum.BODY,0,0,2);
		Item pants = new Item("pants",ItemTypeEnum.ARMOUR,ArmourTypeEnum.CLOTH,QualityEnum.COMMON,UsedEnum.LEGS,0,0,2);
		Item sleves = new Item("sleves",ItemTypeEnum.ARMOUR,ArmourTypeEnum.CLOTH,QualityEnum.COMMON,UsedEnum.ARMS,0,0,2);
		this.getInventory().AddItemToBackPack(starterRustySword);
		this.getInventory().setMainHandItem(starterRustySword);
		this.getInventory().AddItemToBackPack(emptyMainHand);
		this.getInventory().AddItemToBackPack(emptyOffHand);
		this.getInventory().setOffHandItem(emptyOffHand);
		this.getInventory().AddItemToBackPack(cap);
		this.getInventory().setHeadItem(cap);
		this.getInventory().AddItemToBackPack(shirt);
		this.getInventory().setChestItem(shirt);
		this.getInventory().AddItemToBackPack(pants);
		this.getInventory().setLegsItem(pants);
		this.getInventory().AddItemToBackPack(sleves);
		this.getInventory().setArmsItem(sleves);
		
	}
	
	public String toString() {
		return ("Character:"+ super.getName() + 
				"\n    Class:"+ cClass.getClassName()+ 
				"\n Hit Dice:"+ cClass.getHitDice() + 
				"\n       HP: "+super.getHitPoints() +"/" +super.getMaxHitPoints() +
				"\n----------------------------" +
				"\n "+ this.stats.toSting() +
				
				"Currently:" + super.status()
				 );
		
	}
	public VBox statsPane() {
		VBox statsPanel = new VBox();
		statsPanel.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
		statsPanel.setSpacing(2);
		Label characterName = new Label("Character :" + super.getName());
		Label characterClass = new Label("Class:"+ cClass.getClassName()+"\t Level:"+super.getLevel());
		Label expLabel = new Label("Exp :"+super.getExperiance() +" / " + " ??????");
		Label characterHitPoints = new Label ("HP: \t "+super.getHitPoints()+" / "+super.getMaxHitPoints());
		//progress bar not working yet
		ProgressBar playerHP = new ProgressBar();
		playerHP.setProgress(super.getHitPoints()/super.getMaxHitPoints()*1.0);
		statsPanel.getChildren().addAll(characterName,characterClass,expLabel,characterHitPoints);
		
		VBox locationData = new VBox(this.entity.dispayLocation());
		locationData.setBackground(new Background(new BackgroundFill(Color.DARKGREY, null, null)));
		statsPanel.getChildren().add(locationData);
		Label characterStatus = new Label("Currently");
		Label statusDesc = new Label(this.status.getDescription());
		statsPanel.getChildren().addAll(characterStatus,statusDesc,stats.displayStatsBox());
		
		VBox spaceCont = new VBox();
		
		Pane displayAround = new Pane(world.getDungeonlevel(getDepth())
									.getTile(entity.getxLoc(), entity.getyLoc())
									.displayAround());
		Label aroundCountLabel = new Label("count:"+world.getDungeonlevel(getDepth())
									.getTile(entity.getxLoc(), entity.getyLoc()).tileSize());
		spaceCont.getChildren().addAll(displayAround);
		statsPanel.getChildren().add(spaceCont);
		
		return statsPanel;
	}
	public void moveLeft() {
		if(!restrictMove) {
			 if (this.getCClass().getEntityForm().getBody().getTranslateX() > 0) {
				this.world.getDungeonlevel(getDepth()).MoveLeft(this.entity.getxLoc(), this.entity.getyLoc(), this.entity);
				this.entity.setxLoc(this.entity.getxLoc()-1);
				this.getCClass().getEntityForm().getBody().setTranslateX(world.getXPixel(this.getEntity().getxLoc()));				
			 }
		}
	}
	
	public void moveRight() {
		if(!restrictMove) {
			if (this.getCClass().getEntityForm().getBody().getTranslateX() < 1900) {
				this.world.getDungeonlevel(getDepth()).MoveRight(this.entity.getxLoc(), this.entity.getyLoc(), this.entity);
				this.entity.setxLoc(this.entity.getxLoc()+1);
				this.getCClass().getEntityForm().getBody().setTranslateX(world.getXPixel(this.getEntity().getxLoc()));	
			}
		}
	}
	public void moveUp() {
		if(!restrictMove) {
			if (this.getEntity().getyLoc() > 0) {
				this.world.getDungeonlevel(getDepth()).MoveUp(this.entity.getxLoc(), this.entity.getyLoc(), this.entity);
				this.entity.setyLoc(this.entity.getyLoc()-1);
				this.getCClass().getEntityForm().getBody().setTranslateY(world.getXPixel(this.getEntity().getyLoc()));	
			}
		}
	}
	public void moveDown() {
		if(!restrictMove) {
			if (this.getCClass().getEntityForm().getBody().getTranslateY() < 875) {
				this.world.getDungeonlevel(getDepth()).MoveDown(this.entity.getxLoc(), this.entity.getyLoc(), this.entity);
				this.entity.setyLoc(this.entity.getyLoc()+1);
				this.getCClass().getEntityForm().getBody().setTranslateY(world.getXPixel(this.getEntity().getyLoc()));	
			}
		}
	}
	
	
	
	public WorldEntity getEntity() {
		return this.entity;
	}
	public int getDepth() {
		return this.entity.getDepth();
	}
	public void goDownDepth() {
		this.entity.goDownOneDepth();;
	}
	public void goUpDepth() {
		this.entity.goUpOneDepth();;
	}
	public String getDescAroundPlayer() {
		String desc;
		desc = this.world.getDungeonlevel(this.entity.getDepth()).getTile(this.entity.getxLoc(), this.entity.getyLoc()).toString();
		return desc;
	}
	public void restrictPlayerMovementOn() {
		this.restrictMove = true;
	}
	public void restrictPlayerMovementOff() {
		this.restrictMove = false;
	}
	/*
	public PlayerInventory getPlayerInventory() {
		return this.inventory;
	}
	*/
	public Stats getStats() {
		return this.stats;
	}
	public void setStatusUnconcious() {
		this.status =new Status(StatusState.UNCONCEOUS);
		this.statusState = StatusState.UNCONCEOUS;
	}
	public void setStatusAdventuring() {
		this.status =new Status(StatusState.ADVENTURING);
		this.statusState = StatusState.ADVENTURING;
	}
	public void setStatusResting() {
		this.status =new Status(StatusState.RESTING);
		this.statusState = StatusState.RESTING;
	}
	public void setHealing() {
		this.status =new Status(StatusState.HEALING);
	}
	public void setStateDead() {
		this.status = new Status(StatusState.DEAD);
		this.statusState = StatusState.DEAD;
	}
	public StatusState getStatusState() {
		return this.statusState;
	}
	public Status getStatus() {
		return this.status;
	}
	public boolean getmoveRestriction() {
		return restrictMove;
	}
}

