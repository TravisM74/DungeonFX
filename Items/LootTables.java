package Items;

import java.util.ArrayList;
import java.util.Random;

import Gfx.HeldItem;

public class LootTables {
	private ArrayList<Item> loot;
	private ArrayList<Item> weaponsCommon;
	
	private ArrayList<Item> armour;
	

	public LootTables() {
		
		loot = new ArrayList<>();
		weaponsCommon = new ArrayList<>();
		armour = new ArrayList<>();
		
		
		Item starterRustySword = new Item (" forgotten ",ItemTypeEnum.WEAPON,WeaponTypeEnum.SHORT_SWORD ,QualityEnum.POOR,UsedEnum.MAIN_HAND, 0, 1, 25);
		weaponsCommon.add(starterRustySword);
		Item starterRustyDagger = new Item (" long lost ",ItemTypeEnum.WEAPON,WeaponTypeEnum.DAGGER ,QualityEnum.POOR,UsedEnum.MAIN_HAND, 0, 1, 25);
		weaponsCommon.add(starterRustyDagger);
		Item starterRustyShortSword = new Item (" long lost ",ItemTypeEnum.WEAPON,WeaponTypeEnum.SHORT_SWORD ,QualityEnum.POOR,UsedEnum.MAIN_HAND, 0, 1, 25);
		weaponsCommon.add(starterRustyShortSword);
		Item starterRustyAxe = new Item (" long lost ",ItemTypeEnum.WEAPON,WeaponTypeEnum.AXE ,QualityEnum.POOR,UsedEnum.MAIN_HAND, 0, 1, 25);
		weaponsCommon.add(starterRustyAxe);
		Item orcAxe = new Item (" orc forged ",ItemTypeEnum.WEAPON,WeaponTypeEnum.AXE ,QualityEnum.COMMON,UsedEnum.MAIN_HAND, 0, 1, 7);
		weaponsCommon.add(orcAxe);
		Item axe = new Item (" forged ",ItemTypeEnum.WEAPON,WeaponTypeEnum.AXE ,QualityEnum.COMMON,UsedEnum.MAIN_HAND, 0, 1, 7);
		weaponsCommon.add(axe);
		
		Item leatherhelm = new Item (" leather helm ",ItemTypeEnum.ARMOUR,ArmourTypeEnum.LEATHER,QualityEnum.COMMON,UsedEnum.HEAD, 0, 1, 85);
		armour.add(leatherhelm);
		
	}
	public Item getRandomCommonWeapon() {
		System.out.println("RandomCommonWeapon size =" +weaponsCommon.size());
		Random rand = new Random();
		int select = rand.nextInt(weaponsCommon.size());
		return weaponsCommon.get(select);
	}
	
	
}	
