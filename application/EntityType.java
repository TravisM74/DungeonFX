package application;

import Items.Item;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class EntityType {
	
	//move these to WorldEntity
	
	private boolean canMove;
	private String desciption;
	private int detectRange;
	private Entities entity;
	private WorldEntity mob;
	
	private Item item;
	private Group entityVisual;
	
	public EntityType(Entities entity) {
		
		this.entity = entity;

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
			this.canMove = false;
			this.desciption = "...";
			break;
		case SAFE_FLOOR:
			this.canMove = false;
			this.desciption = ".";
	
		}
	}

	
	
	public void setItem (Item newItem) {
		this.item = newItem;
	}
	
	public String toString() {
		return this.desciption;
	}
	public Item getItem() {
		return this.item;
	}

	public Entities getEntity() {
		return this.entity;
	}

	public void setEntity(Entities entity) {
		this.entity = entity;
	}

	public void setMob(WorldEntity mob) {
		this.mob = mob;
	}
	public WorldEntity getMob() {
		return this.mob;
	}
	
	public String getDescription() {
		return this.desciption;
	}

	
	
}
