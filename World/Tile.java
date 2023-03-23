package World;

import java.util.ArrayList;

import Items.Item;
import application.Entities;
import application.WorldEntity;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class Tile {
	private ArrayList<WorldEntity> tile;
	private int x;
	private int y;
	
	
	public Tile(int i , int j) {
		
		this.x=i;
		this.y= j;
		this.tile = new ArrayList<WorldEntity>();
		
		
	}
	public void createWorldEntityItem(WorldEntity newEntity) {
		this.tile.add(newEntity);
	}

	public void add(WorldEntity entity) {
		this.tile.add(entity);
	}

	public void remove(WorldEntity entity) {
		this.tile.remove(entity);
	}
	public int tileSize() {
		return this.tile.size();
		
	}
	public boolean contains(WorldEntity entity) {
		return this.tile.contains(entity);
	}
	public boolean fightTest() {
		for(WorldEntity entity : this.tile) {
			if (entity.getEntity().equals(Entities.MOB)) {
				
					System.out.println("mob found");
				return true;
			}
		}
		return false;
	}
	public boolean interactableTest() {
		for(WorldEntity entity : this.tile) {
			if (entity.getEntity().equals(Entities.ITEM)) {
				System.out.println("Item found");
				return true;
			}
			if (entity.getEntity().equals(Entities.PORTAL)) {
				System.out.println("Portal found");
				return true;
			}
			if (entity.getEntity().equals(Entities.STAIRS)) {
				System.out.println("Stairs found");
				return true;
			}
			if (entity.getEntity().equals(Entities.SHOP)) {
				System.out.println("shop found");
				return true;
			}
		}
		return false;
	}
	public ArrayList<WorldEntity> getLootItems(){
		ArrayList<WorldEntity> lootItems = new ArrayList<>();
		for(int i = 0 ; i < this.tile.size(); i++) {
			if (this.tile.get(i).getEntity().equals(Entities.ITEM)) {
				lootItems.add(this.tile.get(i));
			}
		}
		return lootItems;
	}
	public ArrayList<WorldEntity> getFightingMobEntitys(){
		ArrayList<WorldEntity> fightingMobs = new ArrayList<>();
	
		for(int i = 0 ; i < this.tile.size(); i++) {
			if ((this.tile.get(i).getEntity().equals(Entities.MOB))&& (this.tile.get(i).getMob().isAlive())) {
				fightingMobs.add(this.tile.get(i));
			}
		}
		return fightingMobs;
	}
	
	
	public String descAround() {
		String aroundYou = "Interacatable things near you. \n ";
		String desc= "";
		aroundYou = aroundYou.concat(desc);
		if (!this.tile.isEmpty()) {
			for(int i = 0 ; i < this.tile.size(); i++) {
				if (this.tile.get(i).getEntity().equals(Entities.MOB)) {
					desc =(tile.get(i).getMob().getName()+ " \n");
				} else if(this.tile.get(i).getEntity().equals(Entities.ITEM)) {
					desc= ("an  "+tile.get(i).getItem().toString());
				} else  {
				//	desc= (" "+tile.get(i).getEntity().toString();
				}
				
				aroundYou = aroundYou.concat(desc);
			}
		}
		return aroundYou;
	}

	public Pane displayAround() {
		Pane displayAroundPane = new Pane();
		VBox paneContent = new VBox(8);
		Label displayaroundLabel = new Label(descAround());
		paneContent.getChildren().add(displayaroundLabel);
		displayAroundPane.getChildren().add(paneContent);
		return displayAroundPane;
	}
	public String toString() {
		return "";
	}
	
}
