package old_code;


import java.util.ArrayList;

import Items.Item;
import Items.ItemOfType;
import Items.QualityEnum;
import Items.Used;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class PlayerInventory {
	private Item mainHand;
	private Item offHand;
	private Item headgear;
	private Item chest;
	private Item hands;
	private Item legs;
	private Item feet;
	
	private ArrayList<Item> backpack;
	private boolean hasBackPack;
	private boolean hasBeltPouch;
	private boolean hasCoinPouch;
	private boolean hasImprovedFirstAid;
	private int weight;
	
	public PlayerInventory(){
		Item rustySword = new Item (" rusty old ",5,6,ItemOfType.SHORT_SWORD,QualityEnum.POOR,Used.MAIN_HAND,0);
		this.mainHand = rustySword;
		this.backpack =new ArrayList<Item>();
		
	}
	public Item holdMainHand() {
		return this.mainHand;
		
	}
	
	public int armourClass() {
		return 5;
	}
	public Item getInMainHand(){
		return this.mainHand ;
	}
	
	public BorderPane displayInventoryPane() {
		
		BorderPane playerGear = new BorderPane();
		VBox itemsInUse = new VBox(8);
		itemsInUse.setPadding(new Insets(10));
		itemsInUse.setSpacing(10);
		Label mainHandLabel = new Label("Main hand:"+mainHand);
		Label offHandLabel = new Label("offHand:"+offHand);
		Label headgearLabel = new Label("head gear:"+headgear);
		Label chestLabel = new Label("chest gear:"+chest);
		Label handsLabel = new Label("hands gear:"+hands);
		Label legsLabel = new Label("legs gear:"+legs);
		Label feetLabel = new Label("feet gear:"+feet);
		itemsInUse.getChildren().addAll(mainHandLabel,offHandLabel,headgearLabel,chestLabel,handsLabel,legsLabel,feetLabel);
		playerGear.setLeft(itemsInUse);
		
		
		BorderPane backPackPane = new BorderPane();
		Pane leftBackPackPane = new Pane();
		Pane centreBackPack = new Pane();
		for(Item item : backpack) {
			Label itemLabel = new Label(item.toString());
			centreBackPack.getChildren().add(itemLabel);
		}
		backPackPane.setCenter(centreBackPack);
		backPackPane.setLeft(leftBackPackPane);
		playerGear.setCenter(backPackPane);
		
		return playerGear;
	}
}
