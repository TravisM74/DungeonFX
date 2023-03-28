package Items;

import java.awt.Color;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HumanoidInventory {
	private Item mainHand;
	private Item offHand;
	private Item head;
	private Item body;
	private Item arms;
	private Item legs;
	private Item feet;
	private Item gloves;
	private Item mainHandRing;
	private Item offHandRing;
	private Item amulate;
	
	public boolean gearSet;
	
	ChoiceBox<Item> headGear;
	ChoiceBox<Item> armsGear;
	ChoiceBox<Item> torsoGear;
	ChoiceBox<Item> mainHandGear ;
	ChoiceBox<Item> offHandGear;
	ChoiceBox<Item> legsGear;
	
	
	private ArrayList<Item> backpack;
	private int goldCoin;
	private int silverCoin;
	private int copperCoin;
	private int platCoin;
	private boolean hasBackPack;
	private boolean hasBeltPouch;
	private boolean hasCoinPouch;
	private boolean hasImprovedFirstAid;
	private int weightCarried;
	
	public HumanoidInventory(){
		this.backpack = new ArrayList<Item>();
		
		
		
		
	}
	public VBox DisplayCoin() {
		VBox playersInventory = new VBox();
		Label platCoinLabel = new Label ("Platinum\t Coin:" +this.platCoin);
		Label goldCoinLabel = new Label ("Gold    \t Coin:"+this.goldCoin);
		Label silverCoinLabel = new Label("Silver  \t Coin:"+this.silverCoin);
		Label copperCoinLabel = new Label("Copper   \t Coin:"+this.copperCoin);
		playersInventory.getChildren().addAll(platCoinLabel,goldCoinLabel,silverCoinLabel,copperCoinLabel);
		return playersInventory;
	}
	public void setHeadItem(Item item) {
		this.head = item;
	}
	public void setChestItem(Item item) {
		this.body = item;
	}
	public void setArmsItem(Item item) {
		this.arms = item;
	}
	public void setLegsItem(Item item) {
		this.legs = item;
	}
	public void setGlovesItem(Item item) {
		this.gloves = item;
	}
	public void setBootsItem(Item item) {
		this.feet = item;
	}
	public void setMainHandItem(Item item) {
		this.mainHand = item;
	}
	public void setOffHandItem(Item item) {
		this.offHand = item;
	}
	public int getPlatCoin() {
		return this.platCoin;
	}
	public int getGoldCoin() {
		return this.goldCoin;
	}
	public int getSilverCoin() {
		return this.silverCoin;
	}
	public int getCopperCoin() {
		return this.copperCoin;
	}
	public String toString() {
		return mainHand.toString();
	}
	public void addCopperCoin(int value) {
		this.copperCoin += value;
	}
	public void addSilverCoin(int value) {
		this.silverCoin+= value;
	}
	public void addGoldCoin(int value) {
		this.goldCoin += value;
	}
	public void addPlatCoin(int value) {
		this.platCoin += value;
	}
	public ArrayList<Item> getBackPackContents(){
		return this.backpack;
	}
	public void AddItemToBackPack(Item item) {
	
		if(item.getItemTypeEnum().equals(ItemTypeEnum.WEAPON)) {
			
		}
		this.backpack.add(item);
		
	}
	public int armourClass() {
		return 5;
	}

	public  Item getMainHandItem(){
		
		return this.mainHand;
	}
	public Item getOffhandItem(){
		return this.offHand;
	}
	public BorderPane displayCharacterInventoryPane() {
		
		BorderPane playerGear = new BorderPane();
		VBox itemsInUse = new VBox(8);
		itemsInUse.setPadding(new Insets(10));
		itemsInUse.setSpacing(10);
		Label mainHandLabel = new Label("Main hand:"+mainHand);
		Label offHandLabel = new Label("offHand:"+offHand);
		Label headgearLabel = new Label("head gear:"+head);
		Label chestLabel = new Label("chest gear:"+body);
		Label handsLabel = new Label("hands gear:"+arms);
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
	
	public VBox characterGear() {
		VBox bodyBoxes = new VBox(20);
		bodyBoxes.setAlignment(Pos.TOP_CENTER);
		
		HBox head = new HBox();
		Label headGearLabel = new Label("On Head:\n "+this.head.getDescription()+this.head.getArmourtype());
		headGear = new ChoiceBox<Item>();
		for (Item item:getBackPackContents()) {
			if(item.locationUsed().equals(UsedEnum.HEAD)) {
				
				headGear.getItems().add(item);
				
			}
		}
		headGear.getItems().add(this.head);
		
		head.getChildren().addAll(headGearLabel,headGear);
		
		HBox arms = new HBox();
		Label armsGearLabel = new Label("On Arms:\n "+this.arms.getDescription()+this.arms.getArmourtype());
		armsGear = new ChoiceBox<Item>();
		for (Item item:getBackPackContents()) {
			if(item.locationUsed().equals(UsedEnum.ARMS)) {
				armsGear.getItems().add(item);
				
			}
		}
		armsGear.getItems().add(this.arms);
		
		arms.getChildren().addAll(armsGearLabel,armsGear);
		
		VBox torso = new VBox();
		Label torsoGearLabel = new Label("On Body:\n "+this.body.getDescription()+this.body.getArmourtype());
		torsoGear = new ChoiceBox<Item>();
		for (Item item:getBackPackContents()) {
			if(item.locationUsed().equals(UsedEnum.BODY)) {
				torsoGear.getItems().add(item);
				
			}
		}
		torsoGear.getItems().add(this.body);
		
		torso.getChildren().addAll(torsoGearLabel,torsoGear);
		
		VBox mainHand = new VBox();
		Label mainHandLabel = new Label("in Mainhand:\n "+this.mainHand.getDescription()+this.mainHand.getWeaponType());
		mainHandGear = new ChoiceBox<Item>();
		for (Item item:getBackPackContents()) {
			if(item.locationUsed().equals(UsedEnum.MAIN_HAND) && (item.getItemTypeEnum().equals(ItemTypeEnum.WEAPON))) {
				mainHandGear.getItems().add(item);
				
			}
		}
		
		//mainHandGear.getItems().add(this.mainHand);
		mainHand.getChildren().addAll(mainHandLabel,mainHandGear);
		gearSet = false;
		
		VBox offHand = new VBox();
		Label offHandLabel = new Label("in offhand:\n "+this.offHand.getDescription());
		offHandGear = new ChoiceBox<Item>();
		for (Item item:getBackPackContents()) {
			if(item.locationUsed().equals(UsedEnum.OFF_HAND)) {
				offHandGear.getItems().add(item);
				
			}
		}
		
		offHandGear.getItems().add(this.offHand);
		
		offHand.getChildren().addAll(offHandLabel,offHandGear);
		
		HBox legs = new HBox();
		Label legsGearLabel = new Label("On Legs:\n "+this.legs.getDescription()+this.legs.getArmourtype());
		legsGear = new ChoiceBox<Item>();
		for (Item item:getBackPackContents()) {
			if(item.locationUsed().equals(UsedEnum.LEGS)) {
				legsGear.getItems().add(item);
				
			}
		}
		legsGear.getItems().add(this.legs);
		
		legs.getChildren().addAll(legsGearLabel,legsGear);
		
		Button setGearButton = new Button("Equip selected gear");
		if (!gearSet) { 
			setGearButton.setText("EQUIP SELECTED GEAR");
		} else {
			setGearButton.setText("gear equiped");
		}
		setGearButton.setOnAction(e -> setGearButton_Click());
		bodyBoxes.getChildren().addAll(head,arms,mainHand,offHand,torso,legs,setGearButton);
				
		return bodyBoxes;
	}
	public void setGearButton_Click() {
		gearSet = true;
		this.head = headGear.getValue();
		this.arms = armsGear.getValue();
		this.body = torsoGear.getValue();
		this.offHand = offHandGear.getValue();
		this.mainHand.setVisabilityFalse();
		this.mainHand = mainHandGear.getValue();
		this.mainHand.setVisabilityTrue();
		this.legs = legsGear.getValue();
		displayCharacterInventoryPane();
	}
	
}
