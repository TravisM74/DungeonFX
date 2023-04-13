package application;

import Items.Item;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class WorldEntity {
	
	private int xLoc;
	private int yLoc;
	private int depth;
	private boolean canMove;
	private String desciption;
	private int detectRange;
	private Entities entity;
	private Mob mob;
	
	private Item item;
	private Group entityVisual;
	
	public WorldEntity(Entities entity) {
		
		this.entity = entity;
		this.depth = 0;
		
	
		switch(entity) {
		case MOB:
			this.canMove = true;
			this.detectRange = 5;
			this.desciption = "Monsters approch.";
			break;
		case STAIRS:
			this.canMove = false;
			this.detectRange = 3;
			this.desciption = "Stairs leading to a new level.";
			break;
		case SHOP:
			this.canMove = false;
			this.detectRange = 4;
			this.desciption = "a happy trade Goblin.";
			break;
		case ITEM:
			this.canMove = false;
			this.detectRange = 2;
			//this.desciption = "on the floor";
			break;
		case PORTAL:
			this.canMove = false;
			
			this.detectRange = 4;
			this.desciption = "A portal leading somewhere.";
			break;
		case PLAYER:
			this.detectRange = 6;
			this.canMove = true;
			this.desciption = "...";
			break;
		case SAFE_FLOOR:
			this.canMove = false;
			this.desciption = ".";
	
		}
	}

	
	public Pane dispayLocation() {
		VBox locationData = new VBox(2);
		Label xyLoc = new Label ("Location X:" +this.xLoc + " Y:"+this.yLoc );
		Label displayDepth = new Label("Depth :"+this.depth);
		locationData.getChildren().addAll(xyLoc,displayDepth);
		return locationData;
	}
	public void setItem (Item newItem) {
		this.item = newItem;
	}
	
	public String toString() {
		return "Location X:" +this.xLoc + " Y:"+this.yLoc +" \nDepth :"+this.depth + " \n"+this.desciption;
	}
	public Item getItem() {
		return this.item;
	}

	public Entities getEntityEnum() {
		return this.entity;
	}

	public void setEntity(Entities entity) {
		this.entity = entity;
	}

	public int getxLoc() {
		return this.xLoc;
	}

	public void setxLoc(int xLoc) {
		this.xLoc = xLoc;
	}

	public int getyLoc() {
		return this.yLoc;
	}

	public void setyLoc(int yLoc) {
		this.yLoc = yLoc;
	}

	public int getDepth() {
		return this.depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
	public void setMob(Mob mob) {
		this.mob = mob;
	}
	public Mob getMob() {
		return this.mob;
	}
	
	public String getDescription() {
		return this.desciption;
	}

	public void goDownOneDepth() {
		this.depth++;
	}
	public void goUpOneDepth() {
		this.depth--;
	}
	
}
