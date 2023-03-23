package application;

import java.util.ArrayList;
import java.util.Random;

import Items.HumanoidInventory;
import Race.Race;
import Race.RaceEnum;
import attributes.Stats;
import attributes.Status;
import attributes.StatusState;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
	
	private Random rand;
	
	
	
	public Mob(String name,int level ,Race race,CharacterClass cClass) {
		
		this.alive = true;
		this.name = name;
		this.level = level;
		this.experiance = 0;
		this.race = race;
		this.cClass = cClass;
		inventory = new HumanoidInventory();
		init();
		this.damageMod = 0;
	
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
	public boolean isDead() {
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
		return ("Creature:"+ this.name + 
				
				"\n Hit Dice:"+this.level + 
				"\n Exp :" + this.experiance +
				"\n       HP:"+this.hitPoints +"/" +this.maxHitPoints +
				"\n "+ this.stats.toSting() +
				"Currently:" + currentStatus.getDescription()
				 );
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

}
/*
	public ImageView setEnitityImageXY(double d,double e) {
		ImageView EntityView = this.cClass.getImageView();
		EntityView.setY(e);
		EntityView.setX(d);
		return EntityView;
	}
 */
