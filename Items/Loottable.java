package Items;

import java.util.ArrayList;

public class Loottable {
	private ArrayList<Item> loot;
	private ArrayList<Item> weaponsCommon;
	
	private ArrayList<Item> armour;
	

	private  Loottable() {
		
		loot = new ArrayList<>();
		weaponsCommon = new ArrayList<>();
		armour = new ArrayList<>();
		
		
		Item starterRustySword = new Item (" short sword ",ItemTypeEnum.WEAPON,WeaponTypeEnum.SHORT_SWORD ,QualityEnum.POOR,UsedEnum.MAIN_HAND, 0, 1, 25);
		weaponsCommon.add(starterRustySword);
		Item orcAxe = new Item (" orc forged ",ItemTypeEnum.WEAPON,WeaponTypeEnum.AXE ,QualityEnum.COMMON,UsedEnum.MAIN_HAND, 0, 1, 7);
		weaponsCommon.add(orcAxe);
		
		Item leatherhelm = new Item (" leather helm ",ItemTypeEnum.ARMOUR,ArmourTypeEnum.LEATHER,QualityEnum.COMMON,UsedEnum.HEAD, 0, 1, 85);
		armour.add(leatherhelm);
		
	}

	public ArrayList<Item> getItemsLoot() {
		return loot;
	}
	public ArrayList<Item> getWeaponsLoot() {
		return weaponsCommon;
	}
	public ArrayList<Item> getArmourLoot() {
		return armour;
	}
	
	
	public void setLoot(ArrayList<Item> loot) {
		this.loot = loot;
	}
}	
