package application;

import java.util.ArrayList;
import java.util.Random;

import Items.ArmourTypeEnum;
import Items.HumanoidInventory;
import Items.Item;
import Items.ItemTypeEnum;
import Items.QualityEnum;
import Items.UsedEnum;
import Items.WeaponTypeEnum;
import Race.Race;
import Race.RaceEnum;
import World.WorldArray;
import attributes.Stats;
import attributes.Status;
import attributes.StatusState;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import old_code.PlayerInventory;

public class Mob {
	private int level;
	private int experiance;
	
	private int hitPoints;
	private int maxHitPoints;
	private HumanoidInventory inventory;
	
	private boolean alive;
	private String name;
	private Stats stats;
	private CharacterClass cClass;
	
	
	private int damageMod;
	
	private Status currentStatus;
	private Race race;
	
	WorldArray world;
	private Random rand;
	private Status status;
	private StatusState statusState;
	private boolean restrictMove;
	private WorldEntity entity;
	
	
	private Group entityVisual;
	
	public Mob(String name,int level ,Race race,CharacterClass cClass,WorldEntity entity,WorldArray world ) {
		
		this.entity = entity;
		this.alive = true;
		this.world = world;
		this.name = name;
		this.level = level;
		this.experiance = 0;
		this.race = race;
		this.cClass = cClass;
		this.stats = new Stats();
		this.restrictMove = false;
		inventory = new HumanoidInventory();
		init();
		this.damageMod = 0;
		this.entityVisual = new Group();
		
		
		if (this.entity.getEntity().equals(Entities.PLAYER)) {
			setPlayerSetup();
		}
		
		//addEntityVisualBody(entity.getxLoc(),entity.getyLoc());
		//addEntityVisualWeapon();
	}
	/* test code 
	 * trying to simplify the updating of visuals
	 * 
	 * concept failed initially loot for other solutions
	public void addEntityVisual(Group visual) {
		entityVisual.getChildren().add(visual);
	}
	public void addEntityVisualBody(Color color,int x, int y) {
		entityVisual.setTranslateX(world.getXPixel(x));
		entityVisual.setTranslateY(world.getYPixel(y));
		getCClass().getEntityForm().setEntityColor(color);
		this.entityVisual.getChildren().add(getCClass().getEntityForm());
	}
	public void addEntityVisualWeapon() {
		getInventory().getMainHandItem().getWeaponType().getMainhandHeldItemForm().getItemForm().setTranslateX(world.getXPixel(entity.getxLoc())+entity.getMob().getCClass().getEntityForm().getRightHandX());
		getInventory().getMainHandItem().getWeaponType().getMainhandHeldItemForm().getItemForm().setTranslateY(world.getYPixel(entity.getxLoc())+entity.getMob().getCClass().getEntityForm().getRightHandY());
		this.entityVisual.getChildren().add(getInventory().getMainHandItem().getWeaponType().getMainhandHeldItemForm().getItemForm());
	}
	public void clearEntityVisual() {
		entityVisual = new Group();
	}
	public Group getMobVisual() {
		return this.entityVisual;
	}
	 */
	
	public Stats getStats() {
		return this.stats;
	}
	public void restrictPlayerMovementOn() {
		this.restrictMove = true;
	}
	public void restrictPlayerMovementOff() {
		this.restrictMove = false;
	}
	
	private void init() {
		int hitDice = cClass.getHitDice();
		this.stats = new Stats();
		int value = 0;
		rand = new Random();
		for (int i = 0; i < this.level; i++) {
			
			value+= rand.nextInt(hitDice)+1;
			//System.out.println("looping");
			this.hitPoints = value;
			this.maxHitPoints= value;
			
		}
		//System.out.println(value);
		this.currentStatus = new Status(StatusState.HIDING);
	}
	public void addHealth(int value) {
		if (this.hitPoints < this.maxHitPoints) {
			this.hitPoints = this.hitPoints +value;
		}
		if (this.hitPoints > this.maxHitPoints) {
			this.hitPoints= this.maxHitPoints;
		}
	}
	public void setDamageMod(int value) {
		this.damageMod = value;
	}
	public int getExperiance() {
		return this.experiance;
	}
	public int  getDamageMod() {
		return this.damageMod;
	}
	public CharacterClass getCClass() {
		return this.cClass;
	}
	public HumanoidInventory getInventory() {
		return this.inventory;
	}
	public void takeDamage(int damage) {
		this.hitPoints = this.hitPoints- damage;
	}
	public void addHitPointModifier(int modifier) {
		this.maxHitPoints+= this.maxHitPoints +(modifier * this.level);
		this.hitPoints = this.getMaxHitPoints();
	}
	public int getHitPoints() {
		return this.hitPoints;
	}
	public int getMaxHitPoints() {
		return this.maxHitPoints;
	}
	public boolean isAlive() {
		return this.alive;
	}
	public boolean setDead() {
		this.alive = false;
		return true;
	}
	public String getName() {
		return this.name;
	}
	public String status() {
		if (this.alive) {
			return currentStatus.getDescription();
		} else if (this.hitPoints < 0 && this.hitPoints > -10 ){
			currentStatus = new Status(StatusState.UNCONCEOUS);
			
			return currentStatus.getDescription();
		} else {
			currentStatus = new Status(StatusState.DEAD);
			this.alive = false;
			return currentStatus.getDescription();
		}
	}
	public Pane getStatsPane() {
		Pane stats = new Pane();
		Label nameLabel = new Label(this.name);
		
		stats.getChildren().add(nameLabel);
		return stats;
	}
	public String toString() {
		return ("Character:"+ getName() + 
				"\n    Class:"+ cClass.getClassName()+ 
				"\n Hit Dice:"+ cClass.getHitDice() + 
				"\n       HP: "+getHitPoints() +"/" +getMaxHitPoints() +
				"\n----------------------------" +
				"\n "+ this.stats.toSting() +
				"Currently:" + status()
				 );
	}
	
	public VBox statsPane() {
		VBox statsPanel = new VBox();
		statsPanel.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
		statsPanel.setSpacing(2);
		Label characterName = new Label("Character :" + getName());
		Label characterClass = new Label("Class:"+ cClass.getClassName()+"\t Level:"+getLevel());
		Label expLabel = new Label("Exp :"+getExperiance() +" / " + " ??????");
		Label characterHitPoints = new Label ("HP: \t "+getHitPoints()+" / "+getMaxHitPoints());
		//progress bar not working yet
		ProgressBar playerHP = new ProgressBar();
		playerHP.setProgress(getHitPoints()/getMaxHitPoints()*1.0);
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
	

	public Pane mobStatsPane() {
		VBox statsPane = new VBox();
		Label nameLabel = new Label(this.name);
		Label hitDiceLabel = new Label("HitDice:"+this.level+"");
		Label hpLabel = new Label("HP:"+this.hitPoints +" / " +this.maxHitPoints);
		statsPane.getChildren().addAll(nameLabel,hitDiceLabel,hpLabel);
		return statsPane;
	}
	public void setClass(CharClass value) {
		this.cClass = new CharacterClass(value);
		
	}
	public void setRace(RaceEnum value) {
		this.race = new Race(value);
	}
	public int getLevel() {
		return this.level;
	}
	public void gainLeve() {
		this.level++;
	}
	public void gainExperiance(int value) {
		this.experiance += value; 
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
	
	public void moveLeft() {
		if(!restrictMove) {
			 if (this.getCClass().getEntityForm().getBody().getTranslateX() > 0) {
				this.world.getDungeonlevel(getDepth()).MoveLeft(this.entity.getxLoc(), this.entity.getyLoc(), this.entity);
				this.entity.setxLoc(this.entity.getxLoc()-1);
				//moves the body
				this.getCClass().getEntityForm().getBody().setTranslateX(world.getXPixel(this.getEntity().getxLoc()));	
				//moves the weapon
				this.getInventory().getMainHandItem().getWeaponType().getMainhandHeldItemForm().getItemForm()
					.setTranslateX((world.getXPixel(this.getEntity().getxLoc())+getCClass().getEntityForm().getRightHandX()));
				this.getInventory().getMainHandItem().getWeaponType().getMainhandHeldItemForm().getItemForm()
				.setTranslateY((world.getYPixel(this.getEntity().getyLoc())+getCClass().getEntityForm().getRightHandY()));
				
			 }
		}
	}
	
	public void moveRight() {
		if(!restrictMove) {
			if (this.getCClass().getEntityForm().getBody().getTranslateX() < 1900) {
				this.world.getDungeonlevel(getDepth()).MoveRight(this.entity.getxLoc(), this.entity.getyLoc(), this.entity);
				this.entity.setxLoc(this.entity.getxLoc()+1);
				this.getCClass().getEntityForm().getBody().setTranslateX(world.getXPixel(this.getEntity().getxLoc()));
				
				this.getInventory().getMainHandItem().getWeaponType().getMainhandHeldItemForm().getItemForm()
					.setTranslateX((world.getXPixel(this.getEntity().getxLoc())+getCClass().getEntityForm().getRightHandX()));
				this.getInventory().getMainHandItem().getWeaponType().getMainhandHeldItemForm().getItemForm()
				.setTranslateY((world.getYPixel(this.getEntity().getyLoc())+getCClass().getEntityForm().getRightHandY()));
			}
		}
	}
	public void moveUp() {
		if(!restrictMove) {
			if (this.getEntity().getyLoc() > 0) {
				this.world.getDungeonlevel(getDepth()).MoveUp(this.entity.getxLoc(), this.entity.getyLoc(), this.entity);
				this.entity.setyLoc(this.entity.getyLoc()-1);
				this.getCClass().getEntityForm().getBody().setTranslateY(world.getXPixel(this.getEntity().getyLoc()));	
				
				this.getInventory().getMainHandItem().getWeaponType().getMainhandHeldItemForm().getItemForm()
				.setTranslateX((world.getXPixel(this.getEntity().getxLoc())+getCClass().getEntityForm().getRightHandX()));
				this.getInventory().getMainHandItem().getWeaponType().getMainhandHeldItemForm().getItemForm()
					.setTranslateY((world.getYPixel(this.getEntity().getyLoc())+getCClass().getEntityForm().getRightHandY()));
			}
		}
	}
	public void moveDown() {
		if(!restrictMove) {
			if (this.getCClass().getEntityForm().getBody().getTranslateY() < 875) {
				this.world.getDungeonlevel(getDepth()).MoveDown(this.entity.getxLoc(), this.entity.getyLoc(), this.entity);
				this.entity.setyLoc(this.entity.getyLoc()+1);
				this.getCClass().getEntityForm().getBody().setTranslateY(world.getXPixel(this.getEntity().getyLoc()));	
				
				this.getInventory().getMainHandItem().getWeaponType().getMainhandHeldItemForm().getItemForm()
				.setTranslateX((world.getXPixel(this.getEntity().getxLoc())+getCClass().getEntityForm().getRightHandX()));
				this.getInventory().getMainHandItem().getWeaponType().getMainhandHeldItemForm().getItemForm()
					.setTranslateY((world.getYPixel(this.getEntity().getyLoc())+getCClass().getEntityForm().getRightHandY()));
			
			}
		}
	}
	
	private void setPlayerSetup() {
		this.status = new Status(StatusState.ADVENTURING);
		this.statusState =StatusState.ADVENTURING;
		this.cClass  =cClass;
		this.race = race;
		this.stats = new Stats();
		this.restrictMove = false;
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

}
/*
	public ImageView setEnitityImageXY(double d,double e) {
		ImageView EntityView = this.cClass.getImageView();
		EntityView.setY(e);
		EntityView.setX(d);
		return EntityView;
	}
 */
